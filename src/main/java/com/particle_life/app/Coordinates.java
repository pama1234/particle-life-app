package com.particle_life.app;

import org.joml.Matrix4d;
import org.joml.Vector2f;
import org.joml.Vector3f;

class Coordinates{
  float width;
  float height;
  Vector3f shift;
  float zoom;
  Coordinates(float width,float height,Vector3f shift,float zoom) {
    this.width=width;
    this.height=height;
    this.shift=shift;
    this.zoom=zoom;
  }
  /**
   * Maps x: [-1, 1] -> [0, width], y: [-1, 1] -> [0, height]
   */
  private Vector2f map(Vector2f vector) {
    return vector
      .add(1.0f,1.0f)
      .div(2.0f)
      .mul(width,height);
  }
  /**
   * Scales square to fit smaller dimension (will be scaled along larger dimension of screen)
   */
  private Vector2f quad(Vector2f vector) {
    if(width>=height) {
      vector.mul(height/width,1);
    }else {
      vector.mul(1,width/height);
    }
    return vector;
  }
  /**
   * screen(x) = map(quad(zoom(shift(x)))) = map(quad(zoom * (x + shift)))
   */
  Vector2f screen(Vector3f vector) {
    vector
      .add(shift)
      .mul(zoom);
    return map(quad(new Vector2f(vector.x,vector.y)));
  }
  Vector3f world(float screenX,float screenY) {
    Vector2f screenTopLeft=screen(new Vector3f(-1,-1,0));
    Vector2f screenBottomRight=screen(new Vector3f(1,1,0));
    return new Vector3f(new Vector2f(screenX,screenY)
      .sub(screenTopLeft)
      .div(screenBottomRight
        .sub(screenTopLeft))
      .mul(2.0f)
      .sub(1.0f,1.0f),0);
  }
  public void apply(Matrix4d transform) {
    transform.scale(1,-1,1); // flip y
    // quad(x)
    if(width>=height) {
      transform.scale(height/width,1,1); // fit width
    }else {
      transform.scale(1,width/height,1); // fit height
    }
    transform.scale(zoom);
    transform.translate(shift);
  }
  // SETTER METHODS
  Coordinates mouseShift(Vector2f mouseBefore,Vector2f mouseAfter) {
    Vector3f w1=world(mouseBefore.x,mouseBefore.y);
    Vector3f w2=world(mouseAfter.x,mouseAfter.y);
    shift.add(w2).sub(w1);
    return this;
  }
  Coordinates zoomInOnMouse(Vector2f mouse,float newZoom) {
    float zoomFactor=newZoom/zoom;
    Vector3f w=world(mouse.x,mouse.y);
    shift.set(new Vector3f(w)
      .add(shift)
      .div(zoomFactor)
      .sub(w));
    zoom=newZoom;
    return this;
  }
}

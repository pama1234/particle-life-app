package com.particle_life.app.cursors;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4d;
import org.joml.Vector3f;

import com.particle_life.Particle;
import com.particle_life.Physics;
import com.particle_life.app.shaders.CursorShader;

public class Cursor{
  public Vector3f position=new Vector3f(0,0,0);
  public float size=0.1f;
  public CursorShape shape;
  private CursorShader cursorShader;
  public boolean isInside(Particle particle,Physics physics) {
    if(size==0.0f) return false;
    return shape.isInside(physics.connection(position,particle.position).div(size));
  }
  public List<Particle> getSelection(Physics physics) {
    List<Particle> selectedParticles=new ArrayList<>();
    for(Particle particle:physics.particles) {
      if(isInside(particle,physics)) selectedParticles.add(particle);
    }
    return selectedParticles;
  }
  public void draw(Matrix4d transform) {
    if(cursorShader==null) cursorShader=new CursorShader(); // lazy load shader
    cursorShader.use();
    cursorShader.setTransform(transform
      .translate(position)
      .scale(size));
    if(!shape.isInitialized()) shape.initialize(); // lazy initialize shapes (register VBOs etc. for drawing)
    shape.draw();
  }
  public Vector3f sampleRandomPoint() {
    return new Vector3f(shape.sampleRandomPoint().mul(size).add(position));
  }
  public Cursor copy() {
    Cursor c=new Cursor();
    c.position.set(position);
    c.size=size;
    c.shape=shape.copy();
    return c;
  }
}

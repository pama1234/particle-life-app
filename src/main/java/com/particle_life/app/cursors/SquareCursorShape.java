package com.particle_life.app.cursors;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import org.joml.Vector3f;

import pama1234.math.UtilMath;

public class SquareCursorShape extends CursorShape{
  private int vertexArray;
  @Override
  public void onInitialize() {
    final float[] vertexData=new float[] {
      -1,-1,
      1,-1,
      1,1,
      -1,1,
    };
    int vertexBuffer=glGenBuffers();
    glBindBuffer(GL_ARRAY_BUFFER,vertexBuffer);
    glBufferData(GL_ARRAY_BUFFER,vertexData,GL_STATIC_DRAW);
    vertexArray=glGenVertexArrays();
    glBindVertexArray(vertexArray);
    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0,2,GL_FLOAT,false,0,0);
  }
  @Override
  boolean isInside(Vector3f connection) {
    Vector3f diff=connection.absolute();
    return diff.x<=1.0&&diff.y<=1.0;
  }
  @Override
  void draw() {
    glBindVertexArray(vertexArray);
    glDrawArrays(GL_LINE_LOOP,0,4);
  }
  @Override
  Vector3f sampleRandomPoint() {
    return new Vector3f(2*UtilMath.random()-1,2*UtilMath.random()-1,0);
  }
}

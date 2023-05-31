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

public class CircleCursorShape extends CursorShape{
  private static final int NUM_SEGMENTS=96;
  private int vertexArray;
  @Override
  protected void onInitialize() {
    final float[] vertexData=new float[NUM_SEGMENTS*2];
    for(int i=0;i<NUM_SEGMENTS;i++) {
      float angle=2*UtilMath.PI*i/(float)NUM_SEGMENTS;
      float x=UtilMath.cos(angle);
      float y=UtilMath.sin(angle);
      vertexData[2*i]=(float)x;
      vertexData[2*i+1]=(float)y;
    }
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
    return connection.length()<=1f;
  }
  @Override
  void draw() {
    glBindVertexArray(vertexArray);
    glDrawArrays(GL_LINE_LOOP,0,NUM_SEGMENTS);
  }
  @Override
  Vector3f sampleRandomPoint() {
    float angle=UtilMath.random()*2*UtilMath.PI;
    return new Vector3f(UtilMath.cos(angle),UtilMath.sin(angle),0)
      .mul(UtilMath.sqrt(UtilMath.random()));
  }
}
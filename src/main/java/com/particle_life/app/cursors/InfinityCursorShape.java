package com.particle_life.app.cursors;

import java.util.Random;

import org.joml.Vector3f;

public class InfinityCursorShape extends CursorShape{
  Random random=new Random();
  @Override
  boolean isInside(Vector3f connection) {
    return true;
  }
  @Override
  void draw() {}
  @Override
  Vector3f sampleRandomPoint() {
    return new Vector3f(
      (float)random.nextGaussian(),
      (float)random.nextGaussian(),
      0);
  }
}

package com.particle_life.app.cursors;

import java.lang.reflect.InvocationTargetException;

import org.joml.Vector3f;

/**
 * Describes the shape of a cursor.
 */
public abstract class CursorShape{
  private boolean initialized=false;
  final boolean isInitialized() {
    return initialized;
  }
  final void initialize() {
    onInitialize();
    initialized=true;
  }
  /**
   * When this is called, the implementation can assume that the cursor shader is active. This
   * method should create VBOs and VAOs that can be used in draw().
   */
  protected void onInitialize() {
    // may be overridden
  }
  abstract boolean isInside(Vector3f connection);
  abstract void draw();
  abstract Vector3f sampleRandomPoint();
  public CursorShape copy() {
    return makeNewInstance();
  }
  private CursorShape makeNewInstance() {
    try {
      // assume this has a default constructor that takes no arguments
      return (CursorShape)this.getClass().getConstructors()[0].newInstance();
    }catch(InstantiationException|IllegalAccessException|InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}

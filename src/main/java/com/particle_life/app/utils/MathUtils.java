package com.particle_life.app.utils;

public final class MathUtils{
  public static int constrain(int value,int min,int max) {
    if(value<min) {
      return min;
    }else if(value>max) {
      return max;
    }else {
      return value;
    }
  }
  public static float constrain(float value,float min,float max) {
    if(value<min) {
      return min;
    }else if(value>max) {
      return max;
    }else {
      return value;
    }
  }
  public static int modulo(int a,int b) {
    if(a<0) {
      do {
        a+=b;
      }while(a<0);
      return a;
    }else if(a>=b) {
      do {
        a-=b;
      }while(a>=b);
      return a;
    }
    return a;
  }
  public static float lerp(float a,float b,float f) {
    return a+(b-a)*f;
  }
  /**
   * Returns <code>Math.round(value)</code> instead of <code>Math.floor(value)</code> if
   * <code>value</code> is closer to the next integer than <code>threshold</code>.
   *
   * @param value
   * @param threshold some positive value like 0.001
   * @return an integer
   */
  public static float tolerantFloor(float value,float threshold) {
    float x=Math.round(value);
    if(Math.abs(x-value)<threshold) {
      return x;
    }
    return (float)Math.floor(value);
  }
  /**
   * See comment on {@link #tolerantFloor(float, float)}.
   *
   * @param value
   * @param threshold
   * @return
   */
  public static float tolerantCeil(float value,float threshold) {
    float x=Math.round(value);
    if(Math.abs(x-value)<threshold) return x;
    return (float)Math.ceil(value);
  }
}

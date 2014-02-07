package com.tankformers.math;

public class Vector {
  public float x;
  public float y;

  public static Vector vector(float x, float y) {
    Vector vector = new Vector();
    vector.x = x;
    vector.y = y;
    return vector;
  }
}

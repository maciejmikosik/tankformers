package com.tankformers.model;

public class Calculations {
  public static Vector vectorPolar(float angleInDegrees, float length) {
    float angleInRadians = (float) (angleInDegrees / 360 * 2 * Math.PI);
    Vector vector = new Vector();
    vector.x = (float) (Math.cos(angleInRadians) * length);
    vector.y = (float) (Math.sin(angleInRadians) * length);
    return vector;
  }

  public static Vector add(Vector vectorA, Vector vectorB) {
    Vector vector = new Vector();
    vector.x = vectorA.x + vectorB.x;
    vector.y = vectorA.y + vectorB.y;
    return vector;
  }
}

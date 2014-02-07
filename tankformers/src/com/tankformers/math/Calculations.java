package com.tankformers.math;

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

  public static Vector minus(Vector vector) {
    Vector minusVector = new Vector();
    minusVector.x = -vector.x;
    minusVector.y = -vector.y;
    return minusVector;
  }

  public static float length(Vector vector) {
    return (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
  }

  public static float distance(Vector vectorA, Vector vectorB) {
    return length(add(vectorA, minus(vectorB)));
  }

  public static Vector multiply(float value, Vector vector) {
    Vector result = new Vector();
    result.x = value * vector.x;
    result.y = value * vector.y;
    return result;
  }

  public static Vector unit(Vector vector) {
    Vector unit = new Vector();
    float length = length(vector);
    unit.x = vector.x / length;
    unit.y = vector.y / length;
    return unit;
  }
}

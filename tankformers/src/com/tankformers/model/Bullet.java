package com.tankformers.model;

import static com.tankformers.model.Calculations.add;
import static com.tankformers.model.Calculations.vectorPolar;

public class Bullet {
  public static final float size = 0.025f;
  public static float speed = 0.15f;
  public Vector position = new Vector();
  public float direction;
  public float age;

  public void fly(float time) {
    position = add(position, vectorPolar(direction, time * speed));
  }
}

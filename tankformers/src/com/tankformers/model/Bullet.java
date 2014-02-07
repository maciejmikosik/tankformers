package com.tankformers.model;

import static com.tankformers.math.Calculations.add;
import static com.tankformers.math.Calculations.vectorPolar;

import com.tankformers.math.Vector;

public class Bullet {
  public static final float size = 0.025f;
  public static float speed = 0.15f;
  public Vector position = new Vector();
  public float direction;
  public float age;

  public void fly(float time) {
    age += time;
    position = add(position, vectorPolar(direction, time * speed));
  }
}

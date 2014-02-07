package com.tankformers.model;

import static com.tankformers.model.Calculations.add;
import static com.tankformers.model.Calculations.vectorPolar;

public class Tank {
  public static final float size = 0.05f;
  public Vector position = new Vector();
  public float direction;

  private static float turnSpeed = 60f;
  private static final float speed = 0.1f;

  public void drive(float time) {
    position = add(position, vectorPolar(direction, time * speed));
  }

  public void turn(float delta) {
    direction += delta * turnSpeed;
  }

  public Bullet fire() {
    Bullet bullet = new Bullet();
    bullet.age = 0f;
    bullet.direction = direction;
    bullet.position = add(position, vectorPolar(direction, size / 2));
    return bullet;
  }
}

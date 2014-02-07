package com.tankformers.model;

import static com.tankformers.model.Calculations.add;
import static com.tankformers.model.Calculations.vectorPolar;

public class Tank {
  public Vector position = new Vector();
  public float direction;

  public static final float size = 0.05f;

  private static final float turnSpeed = 60f;
  private static final float forwardSpeed = 0.1f;
  private static final float backwardSpeed = forwardSpeed * 0.5f;

  public void driveForward(float time) {
    position = add(position, vectorPolar(direction, time * forwardSpeed));
  }

  public void driveBackward(float time) {
    position = add(position, vectorPolar(direction, time * -backwardSpeed));
  }

  public void turnLeft(float time) {
    direction += turnSpeed * time;
  }

  public void turnRight(float time) {
    direction += -turnSpeed * time;
  }

  public Bullet fire() {
    Bullet bullet = new Bullet();
    bullet.age = 0f;
    bullet.direction = direction;
    bullet.position = add(position, vectorPolar(direction, size / 2));
    return bullet;
  }
}

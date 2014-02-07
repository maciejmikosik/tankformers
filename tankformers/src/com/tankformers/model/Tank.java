package com.tankformers.model;

import static com.tankformers.model.Calculations.add;
import static com.tankformers.model.Calculations.vectorPolar;

public class Tank {
  public Vector position = new Vector();
  public float direction;
  public float lastFired;

  public static final float size = 0.05f;

  private static final float turnSpeed = 60f;
  private static final float forwardSpeed = 0.1f;
  private static final float backwardSpeed = forwardSpeed * 0.5f;

  private static final float reloadTime = 2;

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

  public boolean isReloaded() {
    return lastFired >= reloadTime;
  }

  public Bullet fire() {
    if (!isReloaded()) {
      throw new IllegalArgumentException();
    }
    Bullet bullet = new Bullet();
    bullet.age = 0f;
    bullet.direction = direction;
    bullet.position = add(position, vectorPolar(direction, size / 2));
    lastFired = 0;
    return bullet;
  }

  public void tick(float time) {
    lastFired += time;
  }
}

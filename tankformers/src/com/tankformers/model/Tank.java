package com.tankformers.model;

public class Tank {
  public Point position;
  public float direction;

  private static float turnSpeed = 2f;
  private static final float speed = 0.2f / 60f;

  public void drive(float time) {
    position.x += Math.cos(direction / 360 * 2 * Math.PI) * time * speed;
    position.y += Math.sin(direction / 360 * 2 * Math.PI) * time * speed;
  }

  public void turn(int delta) {
    direction += delta * turnSpeed;
  }
}

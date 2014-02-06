package com.tankformers.model;

public class Tank {
  public Point position;
  public float direction;

  private static float turnSpeed = 60f;
  private static final float speed = 0.1f;

  public void drive(float time) {
    position.x += Math.cos(direction / 360 * 2 * Math.PI) * time * speed;
    position.y += Math.sin(direction / 360 * 2 * Math.PI) * time * speed;
  }

  public void turn(float delta) {
    direction += delta * turnSpeed;
  }
}

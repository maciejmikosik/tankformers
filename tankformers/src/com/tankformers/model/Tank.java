package com.tankformers.model;

public class Tank {
  public Point position;
  public float direction;

  private static final float speed = 1f;

  public static void drive(Tank tank, float time) {
    tank.position.x += Math.sin(tank.direction) * time * speed;
    tank.position.y += Math.cos(tank.direction) * time * speed;
  }
}

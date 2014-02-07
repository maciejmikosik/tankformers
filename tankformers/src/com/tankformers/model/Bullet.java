package com.tankformers.model;

public class Bullet {
  public static final float size = 0.025f;
  public static float speed = 0.15f;
  public Vector position = new Vector();
  public float direction;
  public float age;

  public void fly(float time) {
    position.x += Math.cos(direction / 360 * 2 * Math.PI) * time * speed;
    position.y += Math.sin(direction / 360 * 2 * Math.PI) * time * speed;
  }
}

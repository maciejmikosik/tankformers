package com.tankformers;

import static com.tankformers.model.Calculations.distance;

import java.util.ArrayList;
import java.util.List;

import com.tankformers.model.Ground;
import com.tankformers.model.Bullet;
import com.tankformers.model.Tank;

public class Collider {
  public static void solveCollision(Ground ground) {
    List<Bullet> hits = new ArrayList<Bullet>();
    for (Bullet bullet : ground.bullets) {
      boolean areColliding = areColliding(bullet, ground.tanks.get(0))
          || areColliding(bullet, ground.tanks.get(1));
      if (areColliding) {
        hits.add(bullet);
      }
    }
    for (Bullet hit : hits) {
      ground.bullets.remove(hit);
    }
  }

  private static boolean areColliding(Bullet bullet, Tank tank) {
    return bullet.age > 0.25f
        && distance(bullet.position, tank.position) - 0.4f * Bullet.size - 0.4f * Tank.size < 0f;
  }
}

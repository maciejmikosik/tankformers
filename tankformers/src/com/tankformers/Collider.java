package com.tankformers;

import static com.tankformers.model.Calculations.distance;

import java.util.ArrayList;
import java.util.List;

import com.tankformers.model.Board;
import com.tankformers.model.Bullet;
import com.tankformers.model.Tank;

public class Collider {
  public static void solveCollision(Board board) {
    List<Bullet> hits = new ArrayList<Bullet>();
    for (Bullet bullet : board.bullets) {
      boolean areColliding = areColliding(bullet, board.tankA) || areColliding(bullet, board.tankB);
      if (areColliding) {
        hits.add(bullet);
      }
    }
    for (Bullet hit : hits) {
      board.bullets.remove(hit);
    }
  }

  private static boolean areColliding(Bullet bullet, Tank tank) {
    return bullet.age > 0.25f
        && distance(bullet.position, tank.position) - 0.4f * Bullet.size - 0.4f * Tank.size < 0f;
  }
}

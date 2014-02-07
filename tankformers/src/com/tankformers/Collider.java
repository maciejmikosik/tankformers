package com.tankformers;

import static com.tankformers.math.Calculations.add;
import static com.tankformers.math.Calculations.distance;
import static com.tankformers.math.Calculations.length;
import static com.tankformers.math.Calculations.minus;
import static com.tankformers.math.Calculations.multiply;
import static com.tankformers.math.Calculations.unit;

import java.util.ArrayList;
import java.util.List;

import com.tankformers.math.Vector;
import com.tankformers.model.Bullet;
import com.tankformers.model.Ground;
import com.tankformers.model.Tank;
import com.tankformers.model.Wall;

public class Collider {
  public static void solveCollision(Ground ground) {
    solveTankWithBullet(ground);
    solveTankWithTank(ground);
    solveTankWithWall(ground);
    solveBulletWithWall(ground);
  }

  private static void solveBulletWithWall(Ground ground) {
    List<Bullet> hitBullets = new ArrayList<Bullet>();
    List<Wall> hitWalls = new ArrayList<Wall>();

    for (Bullet bullet : ground.bullets) {
      for (Wall wall : ground.walls) {
        Vector diff = add(wall.position, minus(bullet.position));
        float space = length(diff) - 0.4f * Bullet.size - 0.4f * Wall.size;
        if (space < 0f) {
          hitBullets.add(bullet);
          hitWalls.add(wall);
        }
      }
    }
    ground.bullets.removeAll(hitBullets);
    ground.walls.removeAll(hitWalls);
  }

  private static void solveTankWithWall(Ground ground) {
    for (Wall wall : ground.walls) {
      for (Tank tank : ground.tanks) {
        Vector diff = add(wall.position, minus(tank.position));
        float space = length(diff) - 0.4f * Tank.size - 0.4f * Wall.size;
        if (space < 0f) {
          Vector correction = multiply(space, unit(diff));
          tank.position = add(tank.position, correction);
        }
      }
    }
  }

  private static void solveTankWithTank(Ground ground) {
    List<Tank> tanks = ground.tanks;
    for (int i = 0; i < tanks.size(); i++) {
      for (int j = i + 1; j < tanks.size(); j++) {
        Tank tankA = tanks.get(i);
        Tank tankB = tanks.get(j);
        Vector vector = add(tankB.position, minus(tankA.position));

        float space = length(vector) - 0.4f * Tank.size * 2;
        if (space < 0f) {
          Vector correction = multiply(0.5f * -space, unit(vector));
          tankA.position = add(tankA.position, minus(correction));
          tankB.position = add(tankB.position, correction);
        }
      }
    }
  }

  private static void solveTankWithBullet(Ground ground) {
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

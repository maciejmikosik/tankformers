package com.tankformers;

import static com.tankformers.math.Calculations.add;
import static com.tankformers.math.Calculations.multiply;
import static com.tankformers.math.Calculations.vectorPolar;
import static com.tankformers.math.Vector.vector;

import java.util.ArrayList;
import java.util.List;

import com.tankformers.math.Vector;
import com.tankformers.model.Ground;
import com.tankformers.model.Tank;
import com.tankformers.model.Wall;

public class Setup {
  public static Ground setupGround() {
    Ground ground = new Ground();

    Tank tank = new Tank();
    tank.position.x = 0.1f;
    tank.position.y = 0.1f;
    tank.direction = 127;
    ground.tanks.add(tank);

    tank = new Tank();
    tank.position.x = -0.3f;
    tank.position.y = -0.3f;
    tank.direction = 290;
    ground.tanks.add(tank);

    ground.walls.addAll(walls(10, vector(-0.3f, 0.3f), 290));
    ground.walls.addAll(walls(20, vector(-0.1f, 0.15f), 280));
    ground.walls.addAll(walls(10, vector(0.1f, -0.1f), 0));

    return ground;
  }

  private static List<Wall> walls(int number, Vector position, float angle) {
    List<Wall> walls = new ArrayList<Wall>();
    Vector diff = vectorPolar(angle, Wall.size);
    for (int i = 0; i < number; i++) {
      Wall wall = new Wall();
      wall.position = add(position, multiply(i, diff));
      walls.add(wall);
    }
    return walls;
  }
}

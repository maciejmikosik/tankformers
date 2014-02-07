package com.tankformers;

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

    Wall wall = new Wall();
    wall.position.x = -0.2f;
    wall.position.y = 0.1f;

    ground.walls.add(wall);
    return ground;
  }
}

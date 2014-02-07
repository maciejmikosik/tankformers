package com.tankformers;

import com.tankformers.model.Board;
import com.tankformers.model.Tank;
import com.tankformers.model.Wall;

public class Game {
  public static Board newGameBoard() {
    Board board = new Board();

    Tank tank = new Tank();
    tank.position.x = 0.1f;
    tank.position.y = 0.1f;
    tank.direction = 127;
    board.tanks.add(tank);

    tank = new Tank();
    tank.position.x = -0.3f;
    tank.position.y = -0.3f;
    tank.direction = 290;
    board.tanks.add(tank);

    Wall wall = new Wall();
    wall.first.x = -0.2f;
    wall.first.y = 0.1f;
    wall.second.x = 0.2f;
    wall.second.y = -0.1f;

    board.walls.add(wall);
    return board;
  }
}

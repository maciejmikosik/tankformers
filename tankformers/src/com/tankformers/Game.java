package com.tankformers;

import com.tankformers.model.Board;
import com.tankformers.model.Tank;
import com.tankformers.model.Wall;

public class Game {
  public static Board newGameBoard() {
    Board board = new Board();

    board.tankA = new Tank();
    board.tankA.position.x = 0.1f;
    board.tankA.position.y = 0.1f;
    board.tankA.direction = 127;

    board.tankB = new Tank();
    board.tankB.position.x = -0.3f;
    board.tankB.position.y = -0.3f;
    board.tankB.direction = 290;

    Wall wall = new Wall();
    wall.first.x = -0.2f;
    wall.first.y = 0.1f;
    wall.second.x = 0.2f;
    wall.second.y = -0.1f;

    board.walls.add(wall);
    return board;
  }
}

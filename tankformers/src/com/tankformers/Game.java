package com.tankformers;

import com.tankformers.model.Board;
import com.tankformers.model.Point;
import com.tankformers.model.Tank;
import com.tankformers.model.Wall;

public class Game {
  public static Board newGameBoard() {
    Board board = new Board();

    board.tankA = new Tank();
    Point tankAPotision = new Point();
    tankAPotision.x = 0.1f;
    tankAPotision.y = 0.1f;
    board.tankA.position = tankAPotision;
    board.tankA.direction = 127;

    board.tankB = new Tank();
    Point tankBPotision = new Point();
    tankBPotision.x = -0.3f;
    tankBPotision.y = -0.3f;
    board.tankB.position = tankBPotision;
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

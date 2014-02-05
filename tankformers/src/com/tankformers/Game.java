package com.tankformers;

import com.tankformers.model.Board;
import com.tankformers.model.Point;
import com.tankformers.model.Tank;

public class Game {
  public static Board newGameBoard() {
    Board board = new Board();
    board.tank = newTank();
    return board;
  }

  private static Tank newTank() {
    Point center = new Point();
    center.x = 0.5f;
    center.y = 0.5f;

    Tank tank = new Tank();
    tank.position = center;
    return tank;
  }
}

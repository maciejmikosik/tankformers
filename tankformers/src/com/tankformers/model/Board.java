package com.tankformers.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
  public List<Tank> tanks = new ArrayList<Tank>();
  public List<Wall> walls = new ArrayList<Wall>();
  public List<Bullet> bullets = new ArrayList<Bullet>();
}

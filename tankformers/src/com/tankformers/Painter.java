package com.tankformers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tankformers.model.Bullet;
import com.tankformers.model.Tank;
import com.tankformers.model.Vector;
import com.tankformers.model.Wall;

public class Painter {
  private final List<Texture> disposables = new ArrayList<Texture>();
  private Sprite tankGreenSprite, tankOrangeSprite, wallSprite, bulletSprite;

  private Painter() {}

  public static Painter newPainter() {
    Painter painter = new Painter();
    TextureRegion region;

    region = new TextureRegion(linearTexture("data/tank_green_64.png"), 0, 0, 64, 64);
    painter.tankGreenSprite = new Sprite(region);

    region = new TextureRegion(linearTexture("data/tank_orange_64.png"), 0, 0, 64, 64);
    painter.tankOrangeSprite = new Sprite(region);

    region = new TextureRegion(linearTexture("data/wall_16.png"), 0, 0, 16, 16);
    painter.wallSprite = new Sprite(region);

    region = new TextureRegion(linearTexture("data/bullet_32.png"), 0, 0, 32, 32);
    painter.bulletSprite = new Sprite(region);

    painter.disposables.add(painter.tankGreenSprite.getTexture());
    painter.disposables.add(painter.tankOrangeSprite.getTexture());
    painter.disposables.add(painter.wallSprite.getTexture());
    painter.disposables.add(painter.bulletSprite.getTexture());
    return painter;
  }

  private static Texture linearTexture(String fileName) {
    Texture texture = new Texture(Gdx.files.internal(fileName));
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    return texture;
  }

  public void dispose() {
    for (Texture disposable : disposables) {
      disposable.dispose();
    }
  }

  public void drawTankFirst(Tank tank, SpriteBatch batch) {
    draw(tank, batch, tankGreenSprite);
  }

  public void drawTankSecond(Tank tank, SpriteBatch batch) {
    draw(tank, batch, tankOrangeSprite);
  }

  public static void draw(Tank tank, SpriteBatch batch, Sprite sprite) {
    sprite.setSize(Tank.size, Tank.size);
    sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    sprite.setRotation(tank.direction);
    sprite.setPosition(tank.position.x, tank.position.y);
    draw(sprite, batch);
  }

  public void drawWall(Wall wall, SpriteBatch batch) {
    Vector vector = new Vector();
    vector.x = wall.second.x - wall.first.x;
    vector.y = wall.second.y - wall.first.y;
    float angle = angle(vector);
    float length = length(vector);
    float numberOfBricks = length / (Wall.size * 0.95f);
    Vector unit = new Vector();
    unit.x = vector.x / numberOfBricks;
    unit.y = vector.y / numberOfBricks;

    wallSprite.setSize(Wall.size, Wall.size);
    wallSprite.setOrigin(wallSprite.getWidth() / 2, wallSprite.getHeight() / 2);
    wallSprite.setRotation(angle);

    for (int i = 0; i < (int) numberOfBricks + 1; i++) {
      wallSprite.setPosition(wall.first.x + i * unit.x, wall.first.y + i * unit.y);
      draw(wallSprite, batch);
    }
    wallSprite.setPosition(wall.second.x, wall.second.y);
    draw(wallSprite, batch);
  }

  private static float angle(Vector vector) {
    return (float) (Math.atan2(vector.y, vector.x) / 2 / (float) Math.PI * 360f);
  }

  private static float length(Vector vector) {
    return (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
  }

  public void drawBullet(Bullet bullet, SpriteBatch batch) {
    bulletSprite.setSize(Bullet.size, Bullet.size);
    bulletSprite.setOrigin(bulletSprite.getWidth() / 2, bulletSprite.getHeight() / 2);
    bulletSprite.setRotation(bullet.direction);
    bulletSprite.setPosition(bullet.position.x, bullet.position.y);
    draw(bulletSprite, batch);
  }

  private static void draw(Sprite sprite, SpriteBatch batch) {
    sprite.translate(-sprite.getOriginX(), -sprite.getOriginY());
    sprite.draw(batch);
  }
}

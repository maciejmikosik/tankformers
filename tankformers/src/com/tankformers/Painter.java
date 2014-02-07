package com.tankformers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tankformers.model.Tank;

public class Painter {
  private final List<Texture> disposables = new ArrayList<Texture>();
  private Sprite tankGreenSprite, tankOrangeSprite;

  private Painter() {}

  public static Painter newPainter() {
    Painter painter = new Painter();
    TextureRegion region;

    region = new TextureRegion(linearTexture("data/tank_green_64.png"), 0, 0, 64, 64);
    painter.tankGreenSprite = new Sprite(region);

    region = new TextureRegion(linearTexture("data/tank_orange_64.png"), 0, 0, 64, 64);
    painter.tankOrangeSprite = new Sprite(region);

    painter.disposables.add(painter.tankGreenSprite.getTexture());
    painter.disposables.add(painter.tankOrangeSprite.getTexture());
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
    sprite.draw(batch);
  }
}

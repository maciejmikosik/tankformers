package com.tankformers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tankformers.model.Tank;

public class Painting {
  private static Texture tankGreenTexture, tankOrangeTexture;
  private static Sprite tankGreenSprite, tankOrangeSprite;

  static {
    tankGreenTexture = tankTexture("data/tank_green_64.png");
    tankGreenSprite = tankSprite(tankGreenTexture);
    tankOrangeTexture = tankTexture("data/tank_orange_64.png");
    tankOrangeSprite = tankSprite(tankOrangeTexture);
  }

  private static Sprite tankSprite(Texture texture) {
    Sprite sprite = new Sprite(new TextureRegion(texture, 0, 0, 64, 64));
    sprite.setSize(0.05f, 0.05f * sprite.getHeight() / sprite.getWidth());
    sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
    return sprite;
  }

  private static Texture tankTexture(String fileName) {
    Texture texture = new Texture(Gdx.files.internal(fileName));
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    return texture;
  }

  public static void dispose() {
    tankGreenTexture.dispose();
    tankOrangeTexture.dispose();
  }

  public static void drawTankFirst(Tank tank, SpriteBatch batch) {
    draw(tank, batch, tankGreenSprite);
  }

  public static void drawTankSecond(Tank tank, SpriteBatch batch) {
    draw(tank, batch, tankOrangeSprite);
  }

  public static void draw(Tank tank, SpriteBatch batch, Sprite sprite) {
    sprite.setRotation(tank.direction);
    sprite.setPosition(tank.position.x, tank.position.y);
    sprite.draw(batch);
  }
}

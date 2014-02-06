package com.tankformers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tankformers.model.Tank;

public class Painting {
  private static Texture tankTexture;
  private static Sprite tankSprite;

  static {
    tankTexture = tankTexture("data/tank_green_64.png");
    tankSprite = tankSprite(tankTexture);
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
    tankTexture.dispose();
  }

  public static void draw(Tank tank, SpriteBatch batch) {
    tankSprite.setRotation(tank.direction);
    tankSprite.setPosition(tank.position.x, tank.position.y);
    tankSprite.draw(batch);
  }

}

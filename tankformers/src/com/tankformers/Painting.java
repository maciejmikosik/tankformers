package com.tankformers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tankformers.model.Tank;

public class Painting {
  private static Texture texture;
  private static Sprite sprite;

  static {
    texture = new Texture(Gdx.files.internal("data/tank_green_64.png"));
    texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

    sprite = new Sprite(new TextureRegion(texture, 0, 0, 64, 64));
    sprite.setSize(0.05f, 0.05f * sprite.getHeight() / sprite.getWidth());
    sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
  }

  public static void dispose() {
    texture.dispose();
  }

  public static void draw(Tank tank, SpriteBatch batch) {
    sprite.setRotation(tank.direction);
    sprite.setPosition(tank.position.x, tank.position.y);
    sprite.draw(batch);
  }

}

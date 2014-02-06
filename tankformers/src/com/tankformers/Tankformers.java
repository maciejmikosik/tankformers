package com.tankformers;

import static com.tankformers.Game.newGameBoard;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankformers.model.Board;

public class Tankformers implements ApplicationListener {
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Board board;

  @Override
  public void create() {
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    camera = new OrthographicCamera(1, h / w);
    batch = new SpriteBatch();

    board = newGameBoard();
  }

  @Override
  public void dispose() {
    batch.dispose();
    Painting.dispose();
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    Painting.draw(board.tank, batch);
    batch.end();
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}
}

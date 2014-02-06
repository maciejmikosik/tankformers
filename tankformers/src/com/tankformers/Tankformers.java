package com.tankformers;

import static com.tankformers.Game.newGameBoard;
import static com.tankformers.Painter.newPainter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.tankformers.model.Board;

public class Tankformers implements ApplicationListener {
  private static final float tick = 1f / 30f;
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Board board;
  private Painter painter;

  @Override
  public void create() {
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    camera = new OrthographicCamera(1, h / w);
    batch = new SpriteBatch();

    board = newGameBoard();
    painter = newPainter();
    scheduleClock();
  }

  private void scheduleClock() {
    Timer.schedule(new Task() {
      @Override
      public void run() {
        if (Gdx.input.isKeyPressed(Keys.UP)) {
          board.tankA.drive(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
          board.tankA.drive(tick / 2f);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
          board.tankA.turn(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
          board.tankA.turn(-tick);
        }
      }
    }, 0, tick);
  }

  @Override
  public void dispose() {
    batch.dispose();
    painter.dispose();
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    painter.drawTankFirst(board.tankA, batch);
    painter.drawTankSecond(board.tankB, batch);
    batch.end();
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}
}

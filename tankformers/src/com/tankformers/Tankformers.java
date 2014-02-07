package com.tankformers;

import static com.tankformers.Game.newGameBoard;
import static com.tankformers.Painter.newPainter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.tankformers.model.Board;
import com.tankformers.model.Bullet;
import com.tankformers.model.Wall;

public class Tankformers implements ApplicationListener {
  private static final float tick = 1f / 30f;
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Board board;
  private Painter painter;
  private Sound fire01Sound;
  private Sound fire02Sound;
  private Music engine01Music;
  private Music engine02Music;

  @Override
  public void create() {
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    camera = new OrthographicCamera(1, h / w);
    batch = new SpriteBatch();

    fire01Sound = Gdx.audio.newSound(Gdx.files.internal("data/fire01.wav"));
    fire02Sound = Gdx.audio.newSound(Gdx.files.internal("data/fire02.wav"));
    engine01Music = Gdx.audio.newMusic(Gdx.files.internal("data/engine01.wav"));
    engine01Music.setLooping(true);
    engine02Music = Gdx.audio.newMusic(Gdx.files.internal("data/engine02.wav"));
    engine02Music.setLooping(true);

    board = newGameBoard();
    painter = newPainter();
    scheduleClock();
  }

  private void scheduleClock() {
    Timer.schedule(new Task() {
      @Override
      public void run() {
        if (Gdx.input.isKeyPressed(Keys.W)) {
          engine01Music.play();
          board.tankA.driveForward(tick);
        } else {
          engine01Music.pause();
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
          board.tankA.driveBackward(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
          board.tankA.turnLeft(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
          board.tankA.turnRight(tick);
        }

        if (Gdx.input.isKeyPressed(Keys.O)) {
          engine02Music.play();
          board.tankB.driveForward(tick);
        } else {
          engine02Music.pause();
        }
        if (Gdx.input.isKeyPressed(Keys.L)) {
          board.tankB.driveBackward(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.K)) {
          board.tankB.turnLeft(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.SEMICOLON)) {
          board.tankB.turnRight(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
          fire02Sound.play();
          Bullet bullet = board.tankB.fire();
          board.bullets.add(bullet);
        }
        if (Gdx.input.isKeyPressed(Keys.TAB)) {
          fire01Sound.play();
          Bullet bullet = board.tankA.fire();
          board.bullets.add(bullet);
        }

        for (Bullet bullet : board.bullets) {
          bullet.fly(tick);
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
    for (Wall wall : board.walls) {
      painter.drawWall(wall, batch);
    }
    for (Bullet bullet : board.bullets) {
      painter.drawBullet(bullet, batch);
    }
    batch.end();
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}
}

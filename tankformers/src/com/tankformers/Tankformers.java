package com.tankformers;

import static com.tankformers.Collider.solveCollision;
import static com.tankformers.Painter.newPainter;
import static com.tankformers.Setup.setupGround;

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
import com.tankformers.model.Bullet;
import com.tankformers.model.Ground;
import com.tankformers.model.Tank;
import com.tankformers.model.Wall;

public class Tankformers implements ApplicationListener {
  private static final float tick = 1f / 30f;
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Ground ground;
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

    ground = setupGround();
    painter = newPainter();
    scheduleClock();
  }

  private void scheduleClock() {
    Timer.schedule(new Task() {
      @Override
      public void run() {
        if (Gdx.input.isKeyPressed(Keys.W)) {
          engine01Music.play();
          ground.tanks.get(0).driveForward(tick);
        } else {
          engine01Music.pause();
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
          ground.tanks.get(0).driveBackward(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
          ground.tanks.get(0).turnLeft(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
          ground.tanks.get(0).turnRight(tick);
        }

        if (Gdx.input.isKeyPressed(Keys.O)) {
          engine02Music.play();
          ground.tanks.get(1).driveForward(tick);
        } else {
          engine02Music.pause();
        }
        if (Gdx.input.isKeyPressed(Keys.L)) {
          ground.tanks.get(1).driveBackward(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.K)) {
          ground.tanks.get(1).turnLeft(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.SEMICOLON)) {
          ground.tanks.get(1).turnRight(tick);
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
          if (ground.tanks.get(1).isReloaded()) {
            fire02Sound.play();
            Bullet bullet = ground.tanks.get(1).fire();
            ground.bullets.add(bullet);
          }
        }
        if (Gdx.input.isKeyPressed(Keys.TAB)) {
          if (ground.tanks.get(0).isReloaded()) {
            fire01Sound.play();
            Bullet bullet = ground.tanks.get(0).fire();
            ground.bullets.add(bullet);
          }
        }

        for (Bullet bullet : ground.bullets) {
          bullet.fly(tick);
        }
        for (Tank tank : ground.tanks) {
          tank.tick(tick);
        }

        solveCollision(ground);
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
    for (int i = 0; i < ground.tanks.size(); i++) {
      painter.drawTank(ground.tanks.get(i), i, batch);
    }

    for (Wall wall : ground.walls) {
      painter.drawWall(wall, batch);
    }
    for (Bullet bullet : ground.bullets) {
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

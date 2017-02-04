package com.ivan;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class ManyBalls extends ApplicationAdapter {
	private static final int NUMBER_OF_BALLS = 60;
	private static final float GRAVEDAD_Y = -90f;
	public static final int FactorZoomCamara = 2;
	SpriteBatch batch;
	World world;
	float timeElapsed = 0, timeLapse = .0f;

	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	ArrayList<Ball> balls;
	ArrayList<Ball> deathBalls;
	ArrayList<Ground> tablas;
	Ground ground;

	@Override
	public void create() {
		batch = new SpriteBatch();
		world = new World(new Vector2(0, GRAVEDAD_Y), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * FactorZoomCamara,
				Gdx.graphics.getHeight() * FactorZoomCamara);
		balls = new ArrayList<Ball>();
		tablas = new ArrayList<Ground>();
		ground = new Ground(world, -Gdx.graphics.getWidth() / 2, -Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(),
				50, BodyType.StaticBody);
		tablas.add(new Ground(world, 150, -Gdx.graphics.getHeight() / 2 + 100, 5, 50, BodyType.DynamicBody));
		tablas.add(new Ground(world, 250, -Gdx.graphics.getHeight() / 2 + 100, 5, 50, BodyType.DynamicBody));
		tablas.add(new Ground(world, 200, -Gdx.graphics.getHeight() / 2 + 155, 55, 5, BodyType.DynamicBody));
	}

	@Override
	public void render() {
		timeElapsed += Gdx.graphics.getDeltaTime();
		if (balls.size() < NUMBER_OF_BALLS && timeElapsed > timeLapse) {
			timeElapsed = 0;
			balls.add(new Ball(world));
		}
		world.step(1f / 60f, 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Utiles.PIXELS_TO_METERS, Utiles.PIXELS_TO_METERS, 0);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Ground tabla : tablas) {
			tabla.update();
//			tabla.draw(batch);
		}
		ground.draw(batch);
		for (Iterator iterator = balls.iterator(); iterator.hasNext();) {
			Ball ball = (Ball) iterator.next();
			ball.update();
			ball.draw(batch);
			if (ball.isDead())
				iterator.remove();
		}
		batch.end();
		debugRenderer.render(world, debugMatrix);
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
	}
}

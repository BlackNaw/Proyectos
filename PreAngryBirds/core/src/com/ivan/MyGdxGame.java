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

public class MyGdxGame extends ApplicationAdapter {
	private static final int NUMBER_OF_BALLS = 1;
	private static final float GRAVEDAD_Y = -9.8f;
	public static final float FactorZoomCamara = 1f;
	SpriteBatch batch;
	World world;
	float timeElapsed = 0, timeLapse = .0f;

	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	Ball ball;
	ArrayList<Ground> tablas;
	Ground suelo,paredDer,paredIzq,techo;

	@Override
	public void create() {
		tablas=new ArrayList<Ground>();
		batch = new SpriteBatch();
		world = new World(new Vector2(0, GRAVEDAD_Y), true);
		ball = new Ball(world);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * FactorZoomCamara, Gdx.graphics.getHeight()
				* FactorZoomCamara);
		suelo=new Ground(world,-camera.viewportWidth/4, -camera.viewportHeight/2,camera.viewportWidth, 50,BodyType.StaticBody);
		paredDer=new Ground(world,camera.viewportWidth/2+10, 0,10,camera.viewportHeight,BodyType.StaticBody);
		paredIzq=new Ground(world,-camera.viewportWidth/2-10, 0,10,camera.viewportHeight,BodyType.StaticBody);
		techo=new Ground(world,-camera.viewportWidth/4, camera.viewportHeight/2+10,camera.viewportWidth, 10,BodyType.StaticBody);
		tablas.add(new Ground(world, 150, -Gdx.graphics.getHeight()/2+100, 5, 50,BodyType.DynamicBody));
		tablas.add(new Ground(world, 250, -Gdx.graphics.getHeight()/2+100, 5, 50,BodyType.DynamicBody));
		tablas.add(new Ground(world,200, -Gdx.graphics.getHeight()/2+155, 55, 5,BodyType.DynamicBody));
		Gdx.input.setInputProcessor(ball);
	}

	@Override
	public void render() {
		timeElapsed += Gdx.graphics.getDeltaTime();
		world.step(1f / 60f, 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Utiles.PIXELS_TO_METERS, Utiles.PIXELS_TO_METERS, 0);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//Al sacar la iteracion de la estructura de arraylist ya
		//podemos modificar el arraylist porque no es el encargado de recorrerlo
		ball.update();
		ball.draw(batch);
		suelo.draw(batch);
		for (Ground tabla : tablas) {
			tabla.update();
			tabla.draw(batch);
		}
	
		
		batch.end();
//		debugRenderer.render(world, debugMatrix);
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
	}
}

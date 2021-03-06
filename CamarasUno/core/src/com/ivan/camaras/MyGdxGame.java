package com.ivan.camaras;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter  {
	SpriteBatch batch;
	// es una imagen sin comportamiento
	Texture img;
	// Que es la imagen sobre la que podemos establecer un
	// comportamiento mas complejo
	Sprite sprite;
	OrthographicCamera camera;
	Stage stage;
	ActorTexture actor;

	@Override
	public void create() {
		stage=new Stage();
		actor=new ActorTexture();
		stage.addActor(actor);
		batch = new SpriteBatch();
		img = new Texture("2048.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(0, 0);
//		sprite.setOrigin(0, 0);
		System.out.println("pantalla ancho:"+Gdx.graphics.getWidth()+" alto:"+Gdx.graphics.getHeight());
		camera=new OrthographicCamera(640, 480);
		//obtenemos la camara del escenario
		camera=(OrthographicCamera) stage.getViewport().getCamera();
		System.out.println("camar posicion x:"+camera.position.x+" y:"+camera.position.y);
		Gdx.input.setInputProcessor(actor.miInput);
		System.out.println(Gdx.graphics.getWidth()+"x"+Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//antes del begin
//		sprite.setX(sprite.getX()-1);
		batch.setProjectionMatrix(camera.combined);
		//Asocio el movimiento de la camara al del actor
		camera.position.x=actor.getX();
		camera.position.y=actor.getY();
		//esto es solo para cuando trabajamos con una camara creada por nosotros
//		camera.update();
		batch.begin();
		sprite.draw(batch);
		batch.end();
//		System.out.println("camar posicion x:"+camera.position.x+" y:"+camera.position.y);
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}

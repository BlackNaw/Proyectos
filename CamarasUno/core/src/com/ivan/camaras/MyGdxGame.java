package com.ivan.camaras;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	// Es una imagen sin comportamiento
	Texture img;

	// Que es la imagen sobre la que podemos establer un comportamiento mas
	// complejo
	Sprite sprite;
	OrthographicCamera camera;
	Stage stage;
	ActorTexture actor;

	@Override
	public void create() {
		batch = new SpriteBatch();
		stage=new Stage();
		img = new Texture("2048.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(0f, 0f);
		System.out.println("pantalla ancho: "+Gdx.graphics.getWidth()+" alto: "+Gdx.graphics.getHeight());
//		camera=new OrthographicCamera(640,480);
		camera=(OrthographicCamera) stage.getViewport().getCamera();
		actor=new ActorTexture();
		stage.addActor(actor);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		sprite.setX(sprite.getX()-1);
		camera.translate(new Vector2(1,0));
		batch.setProjectionMatrix(camera.combined);
//		camera.update();
		batch.begin();
		sprite.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
		System.out.println("camera posicion x: "+camera.position.x+" y: "+camera.position.y);
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
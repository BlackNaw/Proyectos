package com.ivan.escenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor{

	Texture texture=new Texture(Gdx.files.internal("badlogic.jpg"));
	//Algo en lo que pondremos mas enfasis es la maquina de estados
	public boolean started=false;
	
	public MyActor() {
		// darle las dimensiones
		setBounds(0, 0, texture.getWidth(), texture.getHeight());
	}
	//Aqui nos encargamos de dibujar
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(texture,this.getX(),this.getY());
	}
	
	//Aqui nos encargamos del comportamiento
	@Override
	public void act(float delta) {
		super.act(delta);
		moveBy(0, 1);
	}
}

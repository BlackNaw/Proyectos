package com.ivan.escenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor{

	Texture texture=new Texture(Gdx.files.internal("badlogic.jpg"));
	
	//Aqui nos encargamos de dibujar
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}
	
	//Aqui nos encargamos del comportamiento
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}
}
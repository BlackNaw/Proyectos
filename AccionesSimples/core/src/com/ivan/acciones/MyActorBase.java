package com.ivan.acciones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

public class MyActorBase extends Actor {

	Texture texture = new Texture(Gdx.files.internal("halcon.png"));
	RotateToAction rotar = new RotateToAction();
	MoveToAction mover=new MoveToAction();
	MoveToAction moverDos=new MoveToAction();

	public MyActorBase() {
		// darle las dimensiones
		setBounds(200,150, texture.getWidth(), texture.getHeight());
	}

	// Aqui nos encargamos de dibujar
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		// batch.draw(texture,this.getX(),this.getY());
		batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
				this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				texture.getWidth(), texture.getHeight(), false, false);
	}

	// Aqui nos encargamos del comportamiento
	@Override
	public void act(float delta) {
		super.act(delta);
//		for(Iterator<Action> iterator = this.getActions().iterator();iterator.hasNext();){
//			iterator.next().act(delta);
//		}
	}
}

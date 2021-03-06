package com.ivan.acciones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MyActorTres extends Actor {
	Texture texture = new Texture(Gdx.files.internal("halcon.png"));
	RotateToAction rotar = new RotateToAction();
	MoveToAction mover=new MoveToAction();
	MoveToAction moverDos=new MoveToAction();

	public MyActorTres() {
		// darle las dimensiones
		setBounds(200,150, texture.getWidth(), texture.getHeight());

		rotar.setRotation(1200f);
		rotar.setDuration(5f);
		mover.setPosition(100f, 300f);
		mover.setDuration(5f);
		
		//Secuencia
		SequenceAction secuencia=new SequenceAction(mover,Actions.moveTo(200f, 150f,5f));
		SequenceAction secuenciaRotado=new SequenceAction(rotar,Actions.rotateTo(0.5f));
		ParallelAction paralelo=new ParallelAction(secuenciaRotado,secuencia);
		RepeatAction repetir=new RepeatAction();
		//Tienes que poner las vexes que debe repetir
		repetir.setAction(paralelo);
		repetir.setCount(RepeatAction.FOREVER);
		this.addAction(repetir);
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
	}
}

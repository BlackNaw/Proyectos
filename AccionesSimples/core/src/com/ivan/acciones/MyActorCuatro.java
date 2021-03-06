package com.ivan.acciones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.utils.Pool;

public class MyActorCuatro extends Actor{
	Texture texture = new Texture(Gdx.files.internal("halcon.png"));
	FloatAction numero=new FloatAction(1,300);
	
	BalanceAction balance=new BalanceAction(20, 50,5);

	Pool<BalanceAction> pool=new Pool() {
		 @Override
		protected Object newObject(){
			return new BalanceAction(20, 20, 5);
		}
	};
	public void MyActorCuatro() {
		// darle las dimensiones
		setBounds(200,150, texture.getWidth(), texture.getHeight());
		addAction(pool.obtain());
		numero.setDuration(6f);
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
		for(Iterator<Action> iter=this.getActions().iterator();iter.hasNext();){
			iter.next().act(delta);
		}
		System.out.println(numero.getValue());
	}
}

package com.ivan.camaras;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorTexture extends Actor  {
	Texture image = new Texture(Gdx.files.internal("001.png"));
	  float actorX = Gdx.graphics.getWidth()/2, actorY = Gdx.graphics.getHeight()/2;
	  float avanceX=0, avanceY;
	  
	  InputAdapter miInput=new InputAdapter(){
		  public boolean keyDown(int keycode) {
			  switch (keycode) {
				case Keys.UP:
					avanceY = 1;
					break;
				case Keys.DOWN:
					avanceY=-1;break;
				case Keys.LEFT:
					avanceX=-1;break;
				case Keys.RIGHT:
					avanceX=2;break;
				default:
					break;
				}
				return true;
		  };
		  @Override
		public boolean keyUp(int keycode) {
				avanceX = 0;
				avanceY = 0;
				return true;
		}
	  };
	  	  
	  public ActorTexture() {
		  setBounds(actorX,actorY,image.getWidth(),image.getHeight());
	  }
	  @Override
	public void act(float delta) {
		if(getY()>=0){
			moveBy(avanceX, avanceY);
		}
		else {
			moveBy(1, 1);
		}
	}
	  @Override
	public void draw(Batch batch, float parentAlpha) {
		  batch.draw(image, this.getX(), getY(), this.getOriginX(), this.getOriginY(), this.getWidth(), this.getHeight(),
					this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0, image.getWidth(), image.getHeight(),
					true, false);
	}
}

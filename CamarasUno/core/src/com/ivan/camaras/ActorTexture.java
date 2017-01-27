package com.ivan.camaras;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ActorTexture extends Actor {

	Texture texture = new Texture(Gdx.files.internal("001.png"));
	float actorX = 0, actorY = 0;
	float avanceX = 0, avanceY = 0;

	public ActorTexture() {
		setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
		addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch (keycode) {
				case Keys.UP:
					avanceY = 1;
					break;
				case Keys.DOWN:
					avanceY = -1;
					break;
				case Keys.LEFT:
					avanceX = 1;
					break;
				case Keys.RIGHT:
					avanceX = 2;
					break;

				default:
					break;
				}
				return true;
			}

			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				avanceX=1;
				avanceY=1;
				return true;
			}
		});
	}

	@Override
	public void act(float delta) {
		if(getY()>=0){
			moveBy(avanceX, avanceY);
		}else {
			moveBy(1, 1);
		}
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
				this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
				texture.getWidth(), texture.getHeight(), true, false);
	}
}

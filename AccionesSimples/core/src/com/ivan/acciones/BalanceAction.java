package com.ivan.acciones;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BalanceAction extends SequenceAction{
	
	public BalanceAction(float offsetX,float offsetY,float duration) {
		this.addAction(Actions.moveBy(offsetX, offsetY,duration));
		this.addAction(Actions.moveBy(-offsetX, -offsetY,duration));
	}

}

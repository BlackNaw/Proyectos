package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Actor extends Elemento implements Moveable, Actualizable,Pintable,Colisionable {

	Direccion actual = Direccion.norte;
	Avance avance;

	public Actor(Posicion posicion, Texture imagen) {
		super(posicion, imagen);
		avance= new Avance(actual);
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover();
		return comprobarColision(cuerpo);
	}

	@Override
	public void mover() {
		avance.direccionar(actual);
		posicion.x += avance.x;
		posicion.y += avance.y;
	}

	@Override
	public void pinta(SpriteBatch batch) {
		batch.draw(imagen,posicion.x,posicion.y/*,50,50*/);
	}

	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		return this.cuerpo.solapa(cuerpo);
	}

	public boolean isViva(Rectangulo pantalla) {
		return cuerpo.contiene(pantalla);
	}
}

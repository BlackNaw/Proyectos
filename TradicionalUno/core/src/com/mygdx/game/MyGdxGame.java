package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Actor culebra;
	Alimento alimento;
	Rectangulo pantalla;

	@Override
	public void create() {
		batch = new SpriteBatch();
		culebra = new Actor(new Posicion(0, 0), new Texture(Gdx.files.internal("cabeza32.png")));
		alimento = new Alimento(new Texture(Gdx.files.internal("alimento32.png")));
		pantalla = new Rectangulo(new Posicion(0, 0), Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
	}

	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Inputs
		// Sonde obtiene la direccion pulsado, estabelce direccion valida la
		// direccion obtenido
		culebra.actual = culebra.actual.validar(Sondeo.detectar(culebra.actual));
		// Logica
		if (culebra.isViva(pantalla)) {
			if (culebra.actualizar(alimento.cuerpo))
				alimento.cambiar();
		}
		// Dibujo
		batch.begin();
		culebra.pinta(batch);
		alimento.pinta(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		culebra.dispose();
		alimento.dispose();
	}
}

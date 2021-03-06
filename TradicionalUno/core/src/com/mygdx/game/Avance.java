package com.mygdx.game;

public class Avance extends Posicion implements Direccionable {

	public Avance(Direccion direccion) {
		direccionar(direccion);
	}

	public Avance(int x, int y) {
		super(x, y);
	}

	@Override
	public void direccionar(Direccion direccion) {
		ponerACero();
		switch (direccion) {
		case norte:
			y = Constantes.SALTO;
			break;
		case sur:
			y = -Constantes.SALTO;
			break;
		case este:
			x = Constantes.SALTO;
			break;
		case oeste:
			x = -Constantes.SALTO;
			break;
		}
	}

	private void ponerACero() {
		x = 0;
		y = 0;
	}
}

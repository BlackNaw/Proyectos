package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import comun.Constantes;
import comun.Puntuacion;

public class Tie extends Enemigos {
	public int numeroVidas = Constantes.VIDA_TIE;

	public Tie(Texture imagen,String explosion) {
		super(imagen,explosion);
	}

	@Override
	public void explotar(SpriteBatch batch) {
		numeroVidas--;
		if (numeroVidas == 0) {
			numeroVidas=Constantes.VIDA_TIE;
			this.viva = false;
			this.posicionExploX = posicion.x;
			this.posicionExploY = posicion.y;
			reiniciar();
			aux = 0;
			Puntuacion.puntuaciones += 100;
			Puntuacion.nivel = true;
			if (Puntuacion.puntuaciones > Puntuacion.highScore) {
				Puntuacion.highScore = Puntuacion.puntuaciones;
			}
		}
	}

}

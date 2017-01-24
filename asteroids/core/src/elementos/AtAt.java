package elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import comun.Posicion;
import comun.Rectangulo;
import interfaces.Actualizable;
import interfaces.Cambiable;
import interfaces.Colisionable;
import interfaces.Moveable;
import interfaces.Pintable;

public class AtAt extends Elemento implements Cambiable, Pintable, Moveable, Actualizable, Colisionable {

	int velocidad = 1;
	float frameCounter;
	Animation<Object> atAtAnimation;
	TextureAtlas textureAtlas;

	private static final float FRAME_DURARTION = 0.3f;

	public AtAt() {
		super(new Posicion(),new TextureRegion());
		// atAtAnimation = GifDecoder.loadGIF("at-at-izq.gif");
		textureAtlas = new TextureAtlas(Gdx.files.internal("atat.atlas"));
		atAtAnimation = new Animation<Object>(FRAME_DURARTION, textureAtlas.findRegions("atat_derecha"));
		textureRegion.setRegion(((AtlasRegion)atAtAnimation.getKeyFrame(0.1f)).getTexture());

		// Si dejamos el codigo solo de arriba, la imagen nos coge el tama�o del
		// png del atlas, si queremos que nos coja el tama�o de una de las
		// imagenes individuales
		// del atlas.png tendremos que hacer lo siguiente
		// Converimos la imagen en una texture region y le cambiamos su alto y
		// su ancho por los de el fotograma que hay en el 0.1 segundo
//		textureRegion.setRegionWidth(((AtlasRegion) atAtAnimation.getKeyFrame(0.1f)).getRegionWidth());
//		textureRegion.setRegionHeight(((AtlasRegion) atAtAnimation.getKeyFrame(0.1f)).originalHeight);
		cuerpo.ancho=textureRegion.getRegionWidth();
		cuerpo.alto=textureRegion.getRegionHeight();
		imagen = textureRegion.getTexture();
		Gdx.app.log("Ancho", String.valueOf(imagen.getWidth()));
		Gdx.app.log("Alto", String.valueOf(imagen.getHeight()));
		cambiar();
	}

	@Override
	public boolean actualizar(Rectangulo cuerpo) {
		mover();
		return comprobarColision(cuerpo);
	}

	@Override
	public void mover() {
		posicion.x += 1;

	}

	@Override
	public void pintar(SpriteBatch batch) {

	}

	@Override
	public void cambiar() {
		posicion.x = 0;
		posicion.y = 0;
		cuerpo.calculaLados();
	}

	public void pintar(SpriteBatch batch, float frameCounter2) {
		if (posicion.x >= Gdx.graphics.getWidth()) {
			posicion.x = 0;
		}
		batch.draw((TextureRegion) atAtAnimation.getKeyFrame(frameCounter2, true), posicion.x, posicion.y);
//		System.out.println("Ancho:" + imagen.getWidth() + "\n"+ 
//				"Alto:" + imagen.getHeight() + "\n" +
//				"Posicion X: "+ String.valueOf(posicion.x) + " - Posicion Y: "+ String.valueOf(posicion.y) );
		System.out.println("Izquierda: " + cuerpo.izquierda + " Derecha: " + cuerpo.derecha + " Arriba: " + cuerpo.arriba + " Abajo: " + cuerpo.abajo);
		// Gdx.app.log("Ancho:", String.valueOf(imagen.getWidth()));
		// Gdx.app.log("Alto:", String.valueOf(imagen.getHeight()));
	}

	@Override
	public boolean comprobarColision(Rectangulo cuerpo) {
		return this.cuerpo.solapa(cuerpo);
	}

}

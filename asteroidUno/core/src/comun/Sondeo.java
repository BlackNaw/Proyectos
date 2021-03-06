package comun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class Sondeo {

	public static Direccion detectar(Direccion actual) {
		if (Gdx.input.isKeyJustPressed(Keys.LEFT))
			return Direccion.oeste;
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
			return Direccion.este;
		if (Gdx.input.isKeyJustPressed(Keys.UP))
			return Direccion.norte;
		if (Gdx.input.isKeyJustPressed(Keys.DOWN))
			return Direccion.sur;
		return actual;
	}
}

package comun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import interfaces.Pintable;

public class Puntuacion implements Pintable,Disposable {
	private BitmapFont font;
	public static boolean nivel=true;
	public static int puntuaciones=0;
	public static Integer highScore;
	private File file;
	private Texture azul,verde;
	float tamanoVida=90f;
	
	
	public Puntuacion(String fon)
	{	verde=new Texture(Gdx.files.internal("verde.png"));
		azul=new Texture(Gdx.files.internal("azul.png"));
		this.font=new BitmapFont(Gdx.files.internal(fon));
		try {
			highScore=leerArchivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pintar(SpriteBatch batch, float f) {
		batch.draw(verde, 10, 10,100,30);
		batch.draw(azul, 15, 15,tamanoVida*(elementos.Actor.vidas/Constantes.VIDA_HALCON),20);
		  font.draw(batch, "Puntos : "+puntuaciones, 0, Gdx.graphics.getHeight()-10);
		  font.draw(batch, "HighScore : "+highScore, Gdx.graphics.getWidth()-250, Gdx.graphics.getHeight()-10);
	}

	@Override
	public void dispose() {
		font.dispose();
	}
	
	@SuppressWarnings("resource")
	private Integer leerArchivo() throws Exception {
		file=new File("score.txt");
		if(!file.exists()){
			file.createNewFile();
			return 0;
		}
		return  (Integer) new ObjectInputStream(new FileInputStream(file)).readObject();
	}
	
	@SuppressWarnings("resource")
	public void guardarArchivo() throws  Exception{
		
		new ObjectOutputStream(new FileOutputStream(file)).writeObject(puntuaciones);
	}
}

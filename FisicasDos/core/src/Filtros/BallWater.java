package Filtros;

import java.util.Random;

import ManyBalls.Utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class BallWater {
	private static final int MORTAL_BOUND_Y = 1500;
	Sprite sprite;
	TextureAtlas textureMapa;
	TextureRegion textura;
	Body body;
	boolean aleatoria = true;
	boolean dead = false;
	final short GROUP_BALL = -1;


	public BallWater(World world) {
		textureMapa = new TextureAtlas(Gdx.files.internal("bola.atlas"));
		textura = new TextureRegion(textureMapa.findRegion("bola" + getRandomColor()));
		sprite = new Sprite(textura);
		if (aleatoria)
			sprite.setPosition(-Utiles.getEnteroIntervalo(Gdx.graphics.getWidth() / 2),
					Utiles.getEnteroIntervalo(Gdx.graphics.getHeight() / 2));
		else {
			sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		}
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Utiles.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Utiles.PIXELS_TO_METERS);
		this.body = world.createBody(bodyDef);
		CircleShape shape = new CircleShape();
		shape.setRadius(sprite.getWidth() / 2 / Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		//antes de darsela al body
		fixtureDef.filter.groupIndex=GROUP_BALL;
		body.createFixture(fixtureDef);
		shape.dispose();
		body.setFixedRotation(false);
	}

	public boolean isAleatoria() {
		return aleatoria;
	}

	public void setAleatoria(boolean aleatoria) {
		this.aleatoria = aleatoria;
	}

	public boolean isDead() {
		return dead;
	}

	public void update() {
		sprite.setPosition((body.getPosition().x * Utiles.PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * Utiles.PIXELS_TO_METERS) - sprite.getHeight() / 2);
		if (sprite.getY() < -MORTAL_BOUND_Y)
			dead = true;
	}

	
	public static String getRandomColor() {
		switch (new Random().nextInt(6)) {
		default:
		case 0:
			return "amarilla";
		case 1:
			return "azul";
		case 2:
			return "morada";
		case 3:
			return "naranja";
		case 4:
			return "roja";
		case 5:
			return "verde";
		}
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
}

package Filtros;


import ManyBalls.Ground;
import ManyBalls.Utiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LimpiaFiltros extends ApplicationAdapter{
	private static final float GRAVEDAD_Y = -9.8f;
	public static final int FactorZoomCamara = 5;
	SpriteBatch batch;
	Stage stage;
	World world;
	Sprite sprite,spriteDos;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	Ground ground;
	Body body,bodyDos;
	final float PIXELS_TO_METERS = 100f;
	Body bodyEdgeScreen;
	Viewport viewport;

	@Override
	public void create() {
		batch = new SpriteBatch();
		world = new World(new Vector2(0, GRAVEDAD_Y), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() * FactorZoomCamara, Gdx.graphics.getHeight()
				* FactorZoomCamara);
		viewport = new ScreenViewport(camera);
		stage = new Stage(viewport, batch);

		
		//Creamos la primera caja
		sprite=new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		spriteDos=new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		sprite.setPosition(0,0);
		spriteDos.setPosition(0, 300);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Utiles.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Utiles.PIXELS_TO_METERS);
		body = world.createBody(bodyDef);
		bodyDos = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / Utiles.PIXELS_TO_METERS, sprite.getHeight() / 2 / Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.3f;
		fixtureDef.restitution = 0.5f;
		body.createFixture(fixtureDef);
		bodyDos.createFixture(fixtureDef);
		
		BodyDef bodyDef3 = new BodyDef();
		bodyDef3.type = BodyDef.BodyType.StaticBody;

		float w = Gdx.graphics.getWidth() / PIXELS_TO_METERS;
		float h = Gdx.graphics.getHeight() / PIXELS_TO_METERS;

		bodyDef3.position.set(0, 0);
		FixtureDef fixtureDef3 = new FixtureDef();
//		fixtureDef3.filter.categoryBits = WORLD_ENTITY;
//		fixtureDef3.filter.maskBits = PHYSICS_ENTITY;

		EdgeShape edgeShape = new EdgeShape();
		edgeShape.set(-w / 2, -h / 2, w / 2, -h / 2);
		fixtureDef3.shape = edgeShape;
		bodyEdgeScreen = world.createBody(bodyDef3);
		bodyEdgeScreen.createFixture(fixtureDef3);
		edgeShape.dispose();
		
		shape.dispose();

	}

	@Override
	public void render() {
		world.step(1f / 60f, 6, 2);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Utiles.PIXELS_TO_METERS, Utiles.PIXELS_TO_METERS, 0);
		stage.act();
		//corregir la posicion de la caja
		sprite.setPosition((body.getPosition().x * Utiles.PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * Utiles.PIXELS_TO_METERS) - sprite.getHeight() / 2);
		spriteDos.setPosition((body.getPosition().x * Utiles.PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * Utiles.PIXELS_TO_METERS) - sprite.getHeight() / 2);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
//			sprite.draw(batch);
//			spriteDos.draw(batch);
		batch.end();

		debugRenderer.render(world, debugMatrix);

	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
	}

}

package com.ivan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {
	Body body;
	Texture texture;
	Sprite sprite;
	public Ground(World world,float x,float y, float width,float height,BodyType bodyType) {
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(x/Utiles.PIXELS_TO_METERS,y/Utiles.PIXELS_TO_METERS);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/Utiles.PIXELS_TO_METERS,height/Utiles.PIXELS_TO_METERS);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		body=world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		shape.dispose();
		
		
		texture=new Texture(Gdx.files.internal("tabla.png"));
		sprite=new Sprite(texture);
		sprite.setSize(width*2, height*2);
		sprite.setPosition(body.getPosition().x*Utiles.PIXELS_TO_METERS-width,body.getPosition().y*Utiles.PIXELS_TO_METERS-height);
		
		body.setFixedRotation(false);
		sprite.setOrigin(width, height);
		
	}
	
	public void update() {
		sprite.setPosition((body.getPosition().x * Utiles.PIXELS_TO_METERS) - sprite.getWidth() / 2,
				(body.getPosition().y * Utiles.PIXELS_TO_METERS) - sprite.getHeight() / 2);
		sprite.setRotation((float)Math.toDegrees(body.getAngle()));
		
	}
	
	
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
	
}

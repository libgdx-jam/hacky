package co.porkopolis.hacky.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player implements Entity {
	private Vector2 position;
	private Body body;
	private float baseSpeed = 1f;
	private float speed = 1f;

	public Player(Vector2 startPosition, World world) {
		this.position = startPosition;
		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.DynamicBody;
		def.angularDamping = 1.0f;
		def.linearDamping = 1.2f;

		body = world.createBody(def);

		CircleShape cir = new CircleShape();
		cir.setRadius(0.48f);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = cir;
		fDef.density = 0.60f;
		fDef.friction = 0.0f;
		fDef.restitution = 0.01f;

		Fixture fixture = body.createFixture(fDef);
		body.setUserData(this);
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return body;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touch(Entity e) {
		if(e instanceof Coin){
			System.out.println("player touches a coin");
		}
		if(e instanceof Bomb){
			System.out.println("player touches a bomb");
		}
		if(e instanceof MapBody){
			System.out.println("player touches a map body");
		}
		
	}

	@Override
	public void touched(Entity e) {
		if(e instanceof Coin){
			System.out.println("player touched a coin");
		}
		if(e instanceof Bomb){
			System.out.println("player touched a bomb");
		}
		if(e instanceof MapBody){
			System.out.println("player touched a map body");
		}
	}

	@Override
	public void endTouch() {
		// TODO Auto-generated method stub
		
	}

}

package co.porkopolis.hacky.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import co.porkopolis.hacky.EntityManager;
import co.porkopolis.hacky.untils.Box2dConstants;

public class Warp implements Entity {

	private Vector2 position;
	private Body body;

	float targetX, targetY;

	public Warp(Vector2 startPosition, World world, float targetX, float targetY) {
		this.position = startPosition;

		this.targetX = targetX;
		this.targetY = targetY;

		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.StaticBody;
		def.angularDamping = 1.0f;
		def.linearDamping = 1.2f;
		body = world.createBody(def);

		PolygonShape rect = new PolygonShape();
		rect.setAsBox(0.5f, 0.5f);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 0.60f;
		fDef.friction = 0f;
		fDef.restitution = 0.01f;
		fDef.filter.categoryBits = Box2dConstants.WORLD;
		fDef.filter.maskBits = Box2dConstants.PLAYER | Box2dConstants.DEFUALT | Box2dConstants.ENEMIES;
		
		Fixture fixture = body.createFixture(fDef);
		fixture.setUserData(this);
		body.setUserData(this);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return body;
	}

	@Override
	public void touch(Entity e) {
		e.getBody().setTransform(targetX, targetY, 0);

	}

	@Override
	public void endTouch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

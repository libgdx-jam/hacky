package co.porkopolis.hacky.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import co.porkopolis.hacky.untils.Box2dConstants;

public class Door implements Entity {

	private Vector2 position;
	private Body body;

	public Door(Vector2 startPosition, World world) {
		this.position = startPosition;
		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.DynamicBody;
		def.angularDamping = 1.0f;
		def.linearDamping = 1.2f;

		body = world.createBody(def);

		PolygonShape rect = new PolygonShape();
		rect.setAsBox(.5f, .125f);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 0.60f;
		fDef.friction = 0.0f;
		fDef.restitution = 0.01f;
		fDef.filter.categoryBits = Box2dConstants.WORLD;
		fDef.filter.maskBits = Box2dConstants.PLAYER | Box2dConstants.DEFUALT | Box2dConstants.ENEMIES
				| Box2dConstants.PLAYER_BLOCKER | Box2dConstants.WORLD;

		Fixture fixture = body.createFixture(fDef);
		
		RevoluteJointDef  joint = new RevoluteJointDef();
		joint.localAnchorA.set(startPosition.x, startPosition.y);
		joint.bodyA = body;
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
		return null;
	}

	@Override
	public void touch(Entity e) {
		// TODO Auto-generated method stub
		
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

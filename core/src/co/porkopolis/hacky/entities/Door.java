package co.porkopolis.hacky.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;

import co.porkopolis.hacky.untils.Box2dConstants;

public class Door implements Entity {
	private Vector2 position;
	private Body body;
	private float baseSpeed = 1f;
	private float speed = 1f, angle;
	
	private Jam jam;

	public Door(Vector2 startPosition, World world, float angel, float lowerTranslation, float upperTranslation) {
		this.position = startPosition;
		this.angle = angel;
		
		jam = new Jam(new Vector2(startPosition.x, startPosition.y), world);
		
		BodyDef def = new BodyDef();
		def.position.set(startPosition);
		def.type = BodyType.DynamicBody;
		def.angularDamping = 1.0f;
		def.linearDamping = 1.2f;
		def.angle = angel;

		body = world.createBody(def);
		body.setUserData(this);
		

		PolygonShape rect = new PolygonShape();
		rect.setAsBox(0.5f, 0.25f);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = rect;
		fDef.density = 0.60f;
		fDef.friction = 0.0f;
		fDef.restitution = 0.01f;
		fDef.filter.categoryBits = Box2dConstants.WORLD;
		fDef.filter.maskBits = Box2dConstants.DEFUALT | Box2dConstants.ENEMIES  | Box2dConstants.PLAYER;

		Fixture fixture = body.createFixture(fDef);
		fixture.setUserData(this);
		
		PrismaticJointDef jDef = new PrismaticJointDef();
		jDef.bodyA = body;
		jDef.bodyB = jam.getBody();
		jDef.collideConnected = true;
		jDef.referenceAngle = angle;
		jDef.enableLimit = true;
		jDef.lowerTranslation = lowerTranslation;
		jDef.upperTranslation = upperTranslation;
		world.createJoint(jDef);
		
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
	}

	@Override
	public void endTouch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {

	}


}

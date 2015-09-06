package co.porkopolis.hacky.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class MapBody implements Entity{
	
	private Body body;
	
	public MapBody(Body body){
		this.body = body;
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
	@Override
	public String toString(){
		return "mapBody";
	}

}

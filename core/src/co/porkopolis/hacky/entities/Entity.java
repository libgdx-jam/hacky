package co.porkopolis.hacky.entities;

import com.badlogic.gdx.physics.box2d.Body;

public interface Entity {
	public void update(float delta);
	public Body getBody();
	public void touch(Entity e);
	public void endTouch();
	public void destroy();

}

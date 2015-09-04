package co.porkopolis.hacky.entities;

import com.badlogic.gdx.physics.box2d.Body;

public interface Entity {
	public void update();
	public Body getBody();

}

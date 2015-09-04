package co.porkopolis.hacky;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import co.porkopolis.hacky.entities.Entity;

public class EntityManager {
	private static Array<Entity> entities = new Array<Entity>();
	
	public static Entity getEntity(int index){
		return entities.get(index);
	}
	
	public static void addEntity(Entity e){
		entities.add(e);
	}
	public static void wakeAll(){
		for(Entity e: entities){
			if(e.getBody() != null){
				e.getBody().setAwake(true);
			}
		}
	}
	public static Entity findEntityByBody(Body body){
		return null;
	}

}

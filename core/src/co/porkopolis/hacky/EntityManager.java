package co.porkopolis.hacky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import co.porkopolis.hacky.entities.Entity;

public class EntityManager {
	public static Array<Entity> entities = new Array<Entity>();
	private static Array<Body> destroyBodyies = new Array<Body>();
	
	public static Entity getEntity(int index){
		return entities.get(index);
	}
	public static void addEntity(Entity e){
		entities.add(e);
	}
	public static void removeEntity(Entity e){
		if( entities.contains(e, true)){
			destroyBodyies.add(e.getBody());
			entities.removeValue(e, true);
		}
		else{
			Gdx.app.log("Warning", "Entity was not in enity array");
		}
		
	}
	public static void update(float delta){
		for(Entity e: entities){
			e.update(delta);
		}
	}
	public static void wakeAll(){
		for(Entity e: entities){
			if(e.getBody() != null){
				e.getBody().setAwake(true);
			}
		}
	}
	public static void destroyBodies(){
		for(Body b: destroyBodyies){
			if(!b.getWorld().isLocked()){
				b.getWorld().destroyBody(b);
				destroyBodyies.removeValue(b, true);
			}
		}
	}

}

package co.porkopolis.hacky.untils;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import co.porkopolis.hacky.EntityManager;
import co.porkopolis.hacky.entities.Bomb;
import co.porkopolis.hacky.entities.Coin;
import co.porkopolis.hacky.entities.MapBody;
import co.porkopolis.hacky.entities.Player;
import co.porkopolis.hacky.entities.PlayerBlocker;
import co.porkopolis.hacky.entities.Warp;

public class EntityBuilder {

	public static void buildEntities(Map map, World world, Array<Body> mapBodies) {

		MapObjects objects = (MapObjects) map.getLayers().get("entityLayer").getObjects();
		for(MapObject o: objects){
			if(o.getName() != null){
				Float x = o.getProperties().get("x", Float.class);
				Float y = o.getProperties().get("y", Float.class);
				if(o.getName().equals("playerStart")){
					EntityManager.addEntity(new Player(new Vector2(x/32+0.5f, y/32+1.5f), world));
				}
				if(o.getName().equals("coin")){
					EntityManager.addEntity(new Coin(new Vector2(x/32+0.5f, y/32+1.5f), world));
				}
				if(o.getName().equals("bomb")){
					System.out.print("this happened");
					EntityManager.addEntity(new Bomb(new Vector2(x/32+0.5f, y/32+1.5f), world));
				}
				if(o.getName().equals("playerBlocker")){
					EntityManager.addEntity(new PlayerBlocker(new Vector2(x/32+0.5f, y/32+1.5f), world));
				}
				if(o.getName().equals("warp")){
					Float targetX = Float.parseFloat(o.getProperties().get("targetX", String.class));
					Float targetY = Float.parseFloat(o.getProperties().get("targetY", String.class));
					System.out.println(targetX+" "+targetY);
					EntityManager.addEntity(new Warp(new Vector2(x/32+0.5f, y/32+1.5f), world, targetX/32+1.5f, targetY/32+1.5f));
					EntityManager.addEntity(new Warp(new Vector2(targetX/32+1.5f, targetY/32+1.5f), world, x/32+0.5f, y/32+1.5f));
				}

				
			}
		}



		

		for (Body b : mapBodies) {
			EntityManager.addEntity(new MapBody(b));
		}
}

}

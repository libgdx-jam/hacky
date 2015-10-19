package co.porkopolis.hacky.untils;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import co.porkopolis.hacky.EntityManager;
import co.porkopolis.hacky.GameManager;
import co.porkopolis.hacky.entities.Bomb;
import co.porkopolis.hacky.entities.Coin;
import co.porkopolis.hacky.entities.Door;
import co.porkopolis.hacky.entities.MapBody;
import co.porkopolis.hacky.entities.Player;
import co.porkopolis.hacky.entities.PlayerBlocker;
import co.porkopolis.hacky.entities.Saw;
import co.porkopolis.hacky.entities.Warp;

public class EntityBuilder {

	public static void buildEntities(Map map, World world, Array<Body> mapBodies) {

		MapObjects objects = (MapObjects) map.getLayers().get("entityLayer").getObjects();
		for(MapObject o: objects){
			if(o.getName() != null){
				Float x = o.getProperties().get("x", Float.class)/32+0.5f;
				Float y = o.getProperties().get("y", Float.class)/32+1.5f;
				if(o.getName().equals("playerStart")){
					EntityManager.addEntity(new Player(new Vector2(x, y), world));
				}
				if(o.getName().equals("coin")){
					EntityManager.addEntity(new Coin(new Vector2(x, y), world));
					GameManager.coins = GameManager.coins + 1;
				}
				if(o.getName().equals("bomb")){
					EntityManager.addEntity(new Bomb(new Vector2(x, y), world));
				}
				if(o.getName().equals("playerBlocker")){
					EntityManager.addEntity(new PlayerBlocker(new Vector2(x, y), world));
				}
				if(o.getName().equals("saw")){
					EntityManager.addEntity(new Saw(new Vector2(x, y), world));
				}
				if(o.getName().equals("door")){
					Float angle = Float.parseFloat(o.getProperties().get("angle", String.class))*MathUtils.degreesToRadians;
					float lowerTranslation = Float.parseFloat(o.getProperties().get("lowerTranslation", String.class));
					float upperTranslation = Float.parseFloat(o.getProperties().get("upperTranslation", String.class));
					
					EntityManager.addEntity(new Door(new Vector2(x, y), world, angle, lowerTranslation, upperTranslation));
				}
				if(o.getName().equals("warp")){
					Float targetX = Float.parseFloat(o.getProperties().get("targetX", String.class))/32+0.5f;
					Float targetY = Float.parseFloat(o.getProperties().get("targetY", String.class))/32+1.5f;
					float shiftX = 0;
					float shiftY = 0;
					
					if(x < targetX)
						shiftX = -1;
					else if(x > targetX)
						shiftX = 1;
					if(y < targetY)
						shiftY = -1;
					else if(y > targetY)
						shiftY = 1;			
					
					EntityManager.addEntity(new Warp(new Vector2(x, y), world, targetX+shiftX, targetY+shiftY));
					EntityManager.addEntity(new Warp(new Vector2(targetX, targetY), world, x-shiftX, y-shiftY));
				}

				
			}
		}
		for (Body b : mapBodies) {
			EntityManager.addEntity(new MapBody(b));
		}
}

}

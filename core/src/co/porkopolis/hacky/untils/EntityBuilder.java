package co.porkopolis.hacky.untils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import co.porkopolis.hacky.EntityManager;
import co.porkopolis.hacky.entities.Bomb;
import co.porkopolis.hacky.entities.Coin;
import co.porkopolis.hacky.entities.Door;
import co.porkopolis.hacky.entities.MapBody;
import co.porkopolis.hacky.entities.Player;
import co.porkopolis.hacky.entities.PlayerBlocker;

public class EntityBuilder {

	public static void buildEntities(Map map, World world, Array<Body> mapBodies) {

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(
				"entityLayer");

		for (int x = 0; x < layer.getTileWidth(); x++) {
			for (int y = 0; y < layer.getTileWidth(); y++) {
				String name = "";
				if (layer.getCell(x, y) != null)
					if (layer.getCell(x, y).getTile() != null)
						if (layer.getCell(x, y).getTile().getProperties() != null)
							if (layer.getCell(x, y).getTile().getProperties()
									.get("name") != null)
								name = (String) layer.getCell(x, y).getTile()
										.getProperties().get("name");
				// TODO A string switch statement is a feature not supported by
				// older JRE's. Try using a for-loop instead.
				switch (name) {
				case "playerStart":
					EntityManager.addEntity(new Player(new Vector2(x + 0.5f,
							y + 0.5f), world));
					break;
				case "coin":
					EntityManager.addEntity(new Coin(new Vector2(x + 0.5f,
							y + 0.5f), world));
					break;
				case "bomb":
					EntityManager.addEntity(new Bomb(new Vector2(x + 0.5f,
							y + 0.5f), world));
					break;
				case "block":
					EntityManager.addEntity(new PlayerBlocker(new Vector2(
							x + 0.5f, y + 0.5f), world));
					break;
				case "door":
					EntityManager.addEntity(new Door(new Vector2(x + 0.5f, y + 0.5f), world));
					break;
				default:
					Gdx.app.log("EntityBuilder",
							"Warning: Unknow entity at: x " + x + " y " + y
									+ " name " + name);
					break;
				}

				for (Body b : mapBodies) {
					EntityManager.addEntity(new MapBody(b));
				}
			}
		}

	}

}

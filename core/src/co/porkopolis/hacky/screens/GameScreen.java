package co.porkopolis.hacky.screens;

import co.porkopolis.hacky.EntityManager;
import co.porkopolis.hacky.entities.Entity;
import co.porkopolis.hacky.entities.Player;
import co.porkopolis.hacky.untils.EntityBuilder;
import co.porkopolis.hacky.untils.MapBodyBuilder;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

	private OrthographicCamera camera;

	private ShapeRenderer shapeRenderer = new ShapeRenderer();

	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	private Box2DDebugRenderer renderer;

	private Vector2 gravity = new Vector2(0, 0);

	private World world = new World(gravity, true);

	Player player;

	private short width = 46, height = 50;

	private int tileSize;
	private float mapWidth, mapHeight, angle = MathUtils.PI * 1.25f, dist = 27;

	private boolean available;

	@Override
	public void show() {
		available = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.translate(-11.5f, -12.5f);
		camera.update();

		createCollisionListener();

		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);

		tiledMap = new TmxMapLoader().load("maps/pacman3.tmx");

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 0.03125f);

		TiledMapTileLayer mainLayer = (TiledMapTileLayer) tiledMap.getLayers()
				.get(0);
		tileSize = (int) mainLayer.getTileWidth();
		mapWidth = mainLayer.getWidth();
		mapHeight = mainLayer.getHeight();

		Array<Body> mapBodies = MapBodyBuilder.buildShapes(tiledMap, 32, world);
		EntityBuilder.buildEntities(tiledMap, world, mapBodies);
		mapBodies = null;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		EntityManager.destroyBodies();

		if (Gdx.app.getType() == ApplicationType.Desktop) {

			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				angle -= 0.01;
				EntityManager.wakeAll();
			}

			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				angle += 0.01;
				EntityManager.wakeAll();
			}

			gravity.x = MathUtils.cos(angle) * (dist - mapWidth / 2)
					- MathUtils.sin(angle) * (dist - mapHeight / 2) + mapWidth
					/ 2;
			gravity.y = MathUtils.sin(angle) * (dist - mapWidth / 2)
					+ MathUtils.cos(angle) * (dist - mapHeight / 2) + mapHeight
					/ 2;

		}

		else if (Gdx.app.getType() == ApplicationType.Android) {
			if (available) {
				// Work-in-progress see:
				// https://github.com/libgdx/libgdx/wiki/Accelerometer
				gravity.y = -Gdx.input.getAccelerometerX();
				gravity.x = Gdx.input.getAccelerometerY();

			} else {
				// You have an Android phone that does not have an
				// accelerometer?
			}
		}

		world.setGravity(gravity);

		// camera.rotate((((MathUtils.atan2(gravity.y - mapHeight / 2, gravity.x
		// - mapWidth / 3)- MathUtils.atan2(camera.up.x, camera.up.y))) +
		// MathUtils.PI * 0.45f) * MathUtils.radiansToDegrees);

		world.step(1 / 60f, 6, 2);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		renderer.render(world, camera.combined);
		camera.update();

		shapeRenderer.setProjectionMatrix(camera.combined);

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.circle(gravity.x, gravity.y, 0.5f);
		shapeRenderer.end();

	}

	private void createCollisionListener() {
		world.setContactListener(new ContactListener() {

			@Override
			public void beginContact(Contact contact) {
				Entity entityA = (Entity) contact.getFixtureA().getBody()
						.getUserData();
				Entity entityB = (Entity) contact.getFixtureB().getBody()
						.getUserData();
				if (entityA != null && entityB != null) {
					entityA.touch(entityB);
					entityB.touch(entityA);
				} else {
					Gdx.app.log("Warning:",
							"Body is not an entity. All bodies must be entitys");
				}

			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}

		});
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
		tiledMap.dispose();
		renderer.dispose();
		world.dispose();
		player.destroy();
	}

}

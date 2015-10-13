package co.porkopolis.hacky.screens;

import com.badlogic.gdx.Application.ApplicationType;

import java.awt.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import co.porkopolis.hacky.AssetsManager;
import co.porkopolis.hacky.EntityManager;
import co.porkopolis.hacky.GameManager;
import co.porkopolis.hacky.Menus;
import co.porkopolis.hacky.entities.Bomb;
import co.porkopolis.hacky.entities.Coin;
import co.porkopolis.hacky.entities.Entity;
import co.porkopolis.hacky.entities.Player;
import co.porkopolis.hacky.entities.Saw;
import co.porkopolis.hacky.untils.Emuns.GameState;
import co.porkopolis.hacky.untils.EntityBuilder;
import co.porkopolis.hacky.untils.MapBodyBuilder;

public class GameScreen implements Screen {

	private OrthographicCamera camera;


	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	private Box2DDebugRenderer renderer;

	private Vector2 gravity = new Vector2(0, 0);

	private World world = new World(gravity, true);

	private short width = 25, height = 25;

	private float mapWidth, mapHeight, angle = MathUtils.PI * 1.25f, dist = 27;

	private boolean available;
	
	private String map;

	private SpriteBatch batch;
    private Skin skin;
    private Stage pulsedStage;
    private Stage gameOverStage;
    private Stage optionsStage;


	@Override
	public void show() {
		available = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);

		createCollisionListener();

		batch = new SpriteBatch(100);

		renderer = new Box2DDebugRenderer();
		renderer.setDrawBodies(true);
		
		tiledMap = new TmxMapLoader().load("maps/level001.tmx");

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 0.03125f);

		TiledMapTileLayer mainLayer = (TiledMapTileLayer) tiledMap.getLayers()
				.get(0);
		mapWidth = mainLayer.getWidth();
		mapHeight = mainLayer.getHeight();

		EntityBuilder.buildEntities(tiledMap, world, MapBodyBuilder.buildShapes(tiledMap, 32, world));
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        pulsedStage = Menus.createPaluseMenu(skin);
        gameOverStage = Menus.createGameOverMenu(skin);
        optionsStage = Menus.createOptionsMenu(skin);
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.translate(-width/4,-height/4);
		camera.update();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		
		EntityManager.update(delta);

		if (Gdx.app.getType() == ApplicationType.Desktop) {
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				GameManager.setGameState(GameState.PULSED);
			}
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
		
		if(GameManager.getGameState() != GameState.PULSED && GameManager.getGameState() != GameState.OPTIONS){
			world.step(1 / 60f, 6, 2);
		}
		
		//render map
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		//render box2d debug
		renderer.render(world, camera.combined);
		//render sprites
		batch.setProjectionMatrix(camera.combined);
		batch.begin();;
		for(Entity e: EntityManager.entities){
			if(e instanceof Coin){
				batch.draw(AssetsManager.coin.reg, e.getBody().getPosition().x-0.5f, e.getBody().getPosition().y-0.5f, -1f, 0, 1, 1, 1, 1, 0);
			}
			if(e instanceof Player){
				batch.draw(AssetsManager.player.reg, e.getBody().getPosition().x-0.5f, e.getBody().getPosition().y-0.5f, -1f, 0, 1, 1, 1, 1, 0);
			}
			if(e instanceof Bomb){
				batch.draw(AssetsManager.bomb.reg, e.getBody().getPosition().x-0.5f, e.getBody().getPosition().y-0.5f, -1f, 0, 1, 1, 1, 1, 0);
			}
			if(e instanceof Saw){
				batch.draw(AssetsManager.saw.reg, e.getBody().getPosition().x-0.5f, e.getBody().getPosition().y-0.5f, -1f, 0, 1, 1, 1, 1, 0);
			}
			batch.draw(AssetsManager.bomb.reg, gravity.x, gravity.y, -1f, 0, 1, 1, 1, 1, 0);
			batch.draw(AssetsManager.bomb.reg, 0, 6, -1f, 0, 1, 1, 1, 1, 0);
		}
		batch.end();
		//Render HUD
		if(GameManager.getGameState() == GameState.PULSED){
	        Gdx.input.setInputProcessor(pulsedStage);
			batch.begin();
			pulsedStage.draw();
			batch.end();
		}
		else if(GameManager.getGameState() == GameState.GAMEOVER){
	        Gdx.input.setInputProcessor(gameOverStage);
			batch.begin();
			gameOverStage.draw();
			batch.end();
		}
		else if(GameManager.getGameState() == GameState.OPTIONS){
	        Gdx.input.setInputProcessor(optionsStage);
			batch.begin();
			optionsStage.draw();
			batch.end();
		}


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
		batch.dispose();
		tiledMap.dispose();
		renderer.dispose();
		world.dispose();
		optionsStage.dispose();
		pulsedStage.dispose();
		gameOverStage.dispose();
		EntityManager.dispose();
	}

}

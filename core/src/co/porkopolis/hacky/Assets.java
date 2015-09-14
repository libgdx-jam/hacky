package co.porkopolis.hacky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

	public static final String TAG = Assets.class.getSimpleName();

	public static boolean rebuildAtlas = true;
	public static boolean drawDebugOutline = false;

	public static boolean loaded = false;

	public static AssetManager manager;
	
	public static AssetPlayer player;
	public static AssetCoin coin;
	public static AssetBomb bomb;


	private static TextureAtlas atlas;

	public static AssetManager getManager() {
		if (manager == null) {
			manager = new AssetManager();
		}
		return manager;
	}

	public static final String TEXTURE_ATLAS_OBJECTS = "assets.atlas";

	public static void load() {
		Gdx.app.log("Assets", "Loading Textures");
		getManager(); // Insure the manager exists
		manager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		manager.finishLoading();
		loaded = true;
	}

	public static void create() {
		Gdx.app.log("Assest", "Asset manager loaded");
		atlas = manager.get(TEXTURE_ATLAS_OBJECTS);
		player = new AssetPlayer(atlas);
		coin = new AssetCoin(atlas);
		bomb = new AssetBomb(atlas);
	}
	
	public static class AssetPlayer {
		public AtlasRegion reg;

		public AssetPlayer(TextureAtlas atlas) {
			reg = atlas.findRegion("player01");
		}
	}
	
	public static class AssetCoin {
		public AtlasRegion reg;

		public AssetCoin(TextureAtlas atlas) {
			reg = atlas.findRegion("goldCoin01");
		}
	}
	
	public static class AssetBomb {
		public AtlasRegion reg;

		public AssetBomb(TextureAtlas atlas) {
			reg = atlas.findRegion("bomb01");
		}
	}
	@Override
	public void dispose() {
		manager.dispose();
	}

	public static TextureAtlas getAtlas() {
		return atlas;
	}
}
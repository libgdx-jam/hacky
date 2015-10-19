package co.porkopolis.hacky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import co.porkopolis.hacky.untils.Emuns.GameState;

public class GameManager {
	private static Game game;
	private static GameState gameState = GameState.PLAY;
	private static String map;
	public static int coins;
	
	public static GameState getGameState(){
		return gameState;
	}
	
	public static void setGameState(GameState gameState){
		GameManager.gameState = gameState;
		Gdx.app.log("GameManager", "gameState is now: "+gameState.toString());
	}

	public static void init(Game game) {
		GameManager.game = game;
	}

	public static void setScreen(Screen screen) {
		Gdx.app.log("GameManager", "Loading new screen");
		if(game.getScreen() != null){
			Gdx.app.log("GameManager", "Disposing previous screen");
			game.getScreen().dispose();
		}
		game.setScreen(screen);
		Gdx.app.log("GameManager", "Screen loaded");
	}
	
	public static void setMap(String map){
		GameManager.map = map;
	}
	public static String getMap(){
		return map;
	}

}

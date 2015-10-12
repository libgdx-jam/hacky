package co.porkopolis.hacky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import co.porkopolis.hacky.untils.Emuns.GameState;

public class GameManager {
	private static Game game;
	private static GameState gameState = GameState.PLAY;
	
	public static GameState getGameState(){
		return gameState;
	}
	
	public static void setGameState(GameState gameState){
		GameManager.gameState = gameState;
	}

	public static void init(Game game) {
		GameManager.game = game;
	}

	public static void setScreen(Screen screen) {
		if(game.getScreen() != null){
			game.getScreen().dispose();
		}
		game.setScreen(screen);
	}

}

package co.porkopolis.hacky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class GameManager {
	private static Game game;

	public static void init(Game game) {
		GameManager.game = game;
	}

	public static void setScreen(Screen screen) {
		game.setScreen(screen);
	}

}

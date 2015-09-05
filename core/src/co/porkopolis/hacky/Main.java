package co.porkopolis.hacky;

import com.badlogic.gdx.Game;

import co.porkopolis.hacky.screens.GameScreen;

public class Main extends Game {

	@Override
	public void create() {
		GameManager.init(this);
		GameManager.setScreen(new GameScreen());
	}
}

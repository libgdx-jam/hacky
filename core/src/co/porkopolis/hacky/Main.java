package co.porkopolis.hacky;

import com.badlogic.gdx.Game;

import co.porkopolis.hacky.screens.TitleScreen;

public class Main extends Game {

	@Override
	public void create() {
		AssetsManager.load();
		AssetsManager.create();
		GameManager.init(this);
		GameManager.setScreen(new TitleScreen());
	}
}

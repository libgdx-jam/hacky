package co.porkopolis.hacky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import co.porkopolis.hacky.screens.GameScreen;
import co.porkopolis.hacky.screens.TitleScreen;
import co.porkopolis.hacky.untils.Emuns.GameState;

public class Menus {
	public static Stage createPaluseMenu(Skin skin){
		Stage stage = new Stage();
		
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		
		Table buttonsTable = new Table();
		
		buttonsTable.add(createContinueButton(skin));
		buttonsTable.row();
		buttonsTable.add(createRestartButton(skin));
		buttonsTable.row();
		buttonsTable.add(createMainMenutButton(skin));
		buttonsTable.row();
		buttonsTable.add(createOptionsButton(skin));
		buttonsTable.row();
		mainTable.add(buttonsTable);
		
		return stage;
	}
	
	public static Stage createGameOverMenu(Skin skin){
		Stage stage = new Stage();
		
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		
		Table buttonsTable = new Table();
		
		buttonsTable.add(createRestartButton(skin));
		buttonsTable.row();
		buttonsTable.add(createMainMenutButton(skin));
		buttonsTable.row();
		buttonsTable.add(createOptionsButton(skin));
		buttonsTable.row();
		mainTable.add(buttonsTable);
		
		return stage;
	}
	
	public static Stage createOptionsMenu(Skin skin){
		Stage stage = new Stage();
		
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		
		Table buttonsTable = new Table();
		
		buttonsTable.add(createOption1Button(skin));
		buttonsTable.row();
		buttonsTable.add(createOption1Button(skin));
		buttonsTable.row();
		buttonsTable.add(createBackButton(skin));
		buttonsTable.row();
		mainTable.add(buttonsTable);
		
		return stage;
	}
	
	public static Stage createMainMenu(Skin skin){
		Stage stage = new Stage();
		
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		
		final TextField mapName = new TextField("level001", skin);
		mapName.setWidth(400);
		mapName.setHeight(200);
		
		TextButton button = new TextButton("Load map", skin, "default");
		button.setWidth(200f);
		button.setHeight(20f);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameManager.setGameState(GameState.PLAY);
				GameManager.setMap(mapName.getText());
				GameManager.setScreen(new GameScreen());
			}
		});
		
		mainTable.add(mapName);
		mainTable.add(button);
		
		stage.addActor(mainTable);
		
		return stage;
	}

	public static TextButton createRestartButton(Skin skin) {

		TextButton button = new TextButton("Restate level", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);
		
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameManager.setGameState(GameState.PLAY);
				GameManager.setScreen(new GameScreen());
			}
		});

		return button;
	}

	public static TextButton createMainMenutButton(Skin skin) {

		TextButton button = new TextButton("Main menu", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);

		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO: change to title screen
				GameManager.setScreen(new TitleScreen());
				Gdx.app.log("Menus", "Main menu selection");
			}
		});

		return button;
	}

	public static TextButton createOptionsButton(Skin skin) {

		TextButton button = new TextButton("Options", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);

		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameManager.setGameState(GameState.OPTIONS);
			}
		});
		return button;
	}

	public static TextButton createContinueButton(Skin skin) {

		TextButton button = new TextButton("Continue", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);
		
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameManager.setGameState(GameState.PLAY);
			}
		});
		return button;
	}

	public static TextButton createOption1Button(Skin skin) {

		TextButton button = new TextButton("This Does nothing yet", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);

		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			}
		});
		return button;
	}

	public static TextButton createBackButton(Skin skin) {

		TextButton button = new TextButton("Back", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);

		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameManager.setGameState(GameState.PLAY);
			}
		});
		return button;
	}

}

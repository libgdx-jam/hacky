package co.porkopolis.hacky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import co.porkopolis.hacky.screens.GameScreen;
import co.porkopolis.hacky.untils.Emuns.GameState;

public class Menus {
	public static void createRestartButton(Stage stage, Skin skin){
		
		TextButton button = new TextButton("Restate level", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
               GameManager.setScreen(new GameScreen());
            }
        });
		stage.addActor(button);
	}
	
	public static void createMainMenutButton(Stage stage, Skin skin){
		
		TextButton button = new TextButton("Main menu", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
               //TODO: change to title screen
            	GameManager.setScreen(new GameScreen());
            }
        });
		stage.addActor(button);
	}
	
	public static void createOptionsButton(Stage stage, Skin skin){
		
		TextButton button = new TextButton("Options", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
            	GameManager.setGameState(GameState.OPTIONS);
            }
        });
		stage.addActor(button);
	}
	public static void createContinueButton(Stage stage, Skin skin){
		
		TextButton button = new TextButton("Continue", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
               GameManager.setGameState(GameState.PLAY);
            }
        });
		stage.addActor(button);
	}
	
	public static void createOption1Button(Stage stage, Skin skin){
		
		TextButton button = new TextButton("This Does nothing yet", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
            }
        });
		stage.addActor(button);
	}
	
	public static void createBackButton(Stage stage, Skin skin){
		
		TextButton button = new TextButton("Back", skin, "default");
        
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 10f);
        
        button.addListener(new ClickListener(){
            @Override 
            public void clicked(InputEvent event, float x, float y){
               GameManager.setGameState(GameState.PLAY);
            }
        });
		stage.addActor(button);
	}
    	

}

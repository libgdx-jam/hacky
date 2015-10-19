package co.porkopolis.hacky.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import co.porkopolis.hacky.Menus;

public class TitleScreen implements Screen {
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    

	@Override
	public void show() {
        batch = new SpriteBatch(100);
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = Menus.createMainMenu(skin);
        Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);

		batch.begin();
		stage.draw();
		batch.end();
		
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
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        skin.dispose();
    }
}


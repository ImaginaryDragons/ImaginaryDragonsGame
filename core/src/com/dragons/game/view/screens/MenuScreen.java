package com.dragons.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragons.game.model.gameWorld.GameWorld;
import com.dragons.game.utilities.Constants;

import net.dermetfan.gdx.assets.AnnotationAssetManager;

public class MenuScreen implements Screen {
    private GameWorld gameWorld;
    private AnnotationAssetManager manager;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private Texture background;
    private Texture playbutton;

    public MenuScreen(){
        gameWorld = new GameWorld();
        manager = new AnnotationAssetManager();
        spriteBatch = new SpriteBatch();

        background = new Texture("grey_background.jpeg");
        playbutton = new Texture("playbutton.png");

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH / 2, Constants.HEIGHT / 2);
    }

    @Override
    public void show() {

    }

    // TRENGER EN HANDLE INPUT

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.draw(playbutton, camera.position.x - playbutton.getWidth() / 2, camera.position.y);
        spriteBatch.end();

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

    }

}

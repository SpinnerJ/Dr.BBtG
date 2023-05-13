package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game extends ApplicationAdapter {
	private static int gameWidth = 800;
	private static int gameHeight = 600;
	private OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	private Mob test;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,gameWidth,gameHeight);
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("background.png"));
		test = new Mob(100,100,0,10,new Texture(Gdx.files.internal("playerShip.png")));
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(img, 0, 0, gameWidth, gameHeight);
		test.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game extends ApplicationAdapter{
	private static final int GAMEWIDTH = 100;
	private static final int GAMEHEIGHT = 100;
	private OrthographicCamera camera;
	
	SpriteBatch batch;
	Texture background;
	private Mob test;
	private float aspect;
	
	@Override
	public void create () {
		background = new Texture(Gdx.files.internal("background.png"));
		
		aspect = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		camera = new OrthographicCamera(GAMEWIDTH,GAMEHEIGHT*aspect);
		camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0);
		batch = new SpriteBatch();
		test = new Mob(0,0,10,10,new Texture(Gdx.files.internal("playerShip.png")),10);
		Gdx.input.setInputProcessor(new Input());
	}

	float testF = 0; //used for testing rotation
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //use openGL to clear the screen in hardware
		
		
		batch.begin(); //start drawing graphics
		batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight);
		test.setAngle(testF);
		test.draw(batch);
		batch.end();
		testF += 1;
	}
	
	@Override
	public void dispose () {
		background.dispose();
		test.dispose();
		batch.dispose();
	}
}

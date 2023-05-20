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
	private Player player;
	
	@Override
	public void create () {
		background = new Texture(Gdx.files.internal("background.png"));
		
		aspect = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		camera = new OrthographicCamera(GAMEWIDTH,GAMEHEIGHT*aspect);
		camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0);
		batch = new SpriteBatch();
		test = new Mob(49,49,10,10,new Texture(Gdx.files.internal("playerShip.png")),10);
		player = new Player(test);
		Gdx.input.setInputProcessor(new Input(player));
	}

	float testF = 0; //used for testing rotation
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //use openGL to clear the screen in hardware
		
		Mob m = player.getMob();
		if(player.getRotating() != 0)
		{
			m.setAngle(m.getAngle()+(player.getRotating()*5));
		}
		if(player.getAccelerating() != 0)
		{
			player.setAcceleration((player.getAcceleration()+(player.getAccelerating()*0.001f)));
		}
		m.setSpeed(m.getSpeed()+player.getAcceleration());
		if(m.getSpeed()<0)
		{
			m.setSpeed(0);
		}
		if(m.getSpeed() > player.getMaxSpeed())
		{
			m.setSpeed(player.getMaxSpeed());
		}
		else if(m.getSpeed() < (player.getMaxSpeed()*-1))
		{
			m.setSpeed(player.getMaxSpeed()*-1);
		}
		float speedX = (float)Math.cos(Math.toRadians(m.getAngle()+90));
		speedX = speedX*m.getSpeed();
		float speedY = (float)Math.sin(Math.toRadians(m.getAngle()+90));
		speedY = speedY*m.getSpeed();
		m.setX(speedX+m.getX());
		m.setY(speedY+m.getY());
		
		batch.begin(); //start drawing graphics
		batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight);
		test.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		background.dispose();
		test.dispose();
		batch.dispose();
	}
}

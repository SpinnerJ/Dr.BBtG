package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game extends ApplicationAdapter{
	private static final int GAMEWIDTH = 100; //game width in spatial coordinates, doesn't rely on amount of pixels on screen
	private static final int GAMEHEIGHT = 100; //game height in spatial coordinates, doesn't rely on amount of pixels on screen
	private OrthographicCamera camera;
	
	SpriteBatch batch;
	Texture background;
	private Mob test;
	private float aspect;
	private Player player;
	private Mob m;
	private Database db;
	private ArrayList<Mob> enemies = new ArrayList<Mob>();
	private ArrayList<Texture> graphics = new ArrayList<Texture>();
	
	@Override
	public void create () {
		loadGraphics();
		background = graphics.get(0);
		
		aspect = Gdx.graphics.getWidth()/Gdx.graphics.getHeight(); //width to height ratio
		camera = new OrthographicCamera(GAMEWIDTH,GAMEHEIGHT*aspect); //2D camera
		camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0); //mapping camera position to screen viewport
		batch = new SpriteBatch(); //required to draw in libGDX
		test = new Mob(49,49,6,6,graphics.get(1),10,0); //mob given to player
		player = new Player(test); //player object
		m = player.getMob(); //reference to player's mob
		Gdx.input.setInputProcessor(new Input(player)); //links libGDX input to our input class
		db = new Database();
		db.connect();
		String test = db.queryAll();
		System.out.println(test);
		spawnEnemy(0);
		spawnEnemy(0);
	}

	float testF = 0; //used for testing rotation
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //use openGL to clear the screen in hardware
		
		//if player is pressing rotating key, rotate the player
		if(player.getRotating() != 0)
		{
			m.setAngle(m.getAngle()+(player.getRotating()*5));
		}
		
		//accelerate player if acceleration key is pressed
		if(player.getAccelerating() != 0)
		{
			player.setAcceleration((player.getAcceleration()+(player.getAccelerating()*0.0001f)));
			player.setAceAngle(m.getAngle());
		}
		
		//set the speed of the player based on acceleration
		m.setSpeed(m.getSpeed()+player.getAcceleration()*1f);
		if(m.getSpeed()<0)
		{
			m.setSpeed(0);
		}
		
		//keep player to max speed
		if(m.getSpeed() > player.getMaxSpeed())
		{
			m.setSpeed(player.getMaxSpeed());
		}
		else if(m.getSpeed() < (player.getMaxSpeed()*-1))
		{
			m.setSpeed(player.getMaxSpeed()*-1);
		}
		//get how fast the player is moving in the x direction
		//+90 is to compensate for sprite images pointing up instead of to the right
		//ace angle is the angle the ship is pointing at when accelerating, this allows to be able to move in
		//a direction while still being able to rotate freely
		float speedX = (float)Math.cos(Math.toRadians(player.getAceAngle()+90));
		speedX = speedX*m.getSpeed();
		
		//get how fast the player is moving in the y direction
		float speedY = (float)Math.sin(Math.toRadians(player.getAceAngle()+90));
		speedY = speedY*m.getSpeed();
		
		//update the position of the player
		m.setX(speedX+m.getX());
		m.setY(speedY+m.getY());
		
		//check if player is at a boundary, move to opposite side of screen if true
		if(m.getX()<0)
		{
			m.setX(100);
		}
		if(m.getY()<0)
		{
			m.setY(100);
		}
		if(m.getX()>100)
		{
			m.setX(0);
		}
		if(m.getY()>100)
		{
			m.setY(0);
		}
		
		batch.begin(); //start drawing graphics
		batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight); //draw camera viewport
		for(int i=0;i<enemies.size();i++)
		{
			enemies.get(i).draw(batch);
		}
		test.draw(batch);//draw player's mob
		batch.end();
	}
	
	@Override
	public void dispose () {
		background.dispose();
		test.dispose();
		batch.dispose();
	}
	
	public void spawnEnemy(int type)
	{
		//figure out what borders the player is closest to and randomly choose a border
		//that is far away from the player to spawn an enemy
		float px = m.getX();
		float py = m.getY();
		px = px - 50;
		py = py - 50;
		
		float rx = 0;
		float ry = 0;
		
		boolean side = Math.random() < 0.5;
		if(side == false)
		{
			ry = (float)Math.random()*100;
			if(px < 0)
			{
				rx = 94;
			}
			else
			{
				rx = 0;
			}
			
		}
		else
		{
			rx = (float)Math.random()*100;
			if(py < 0)
			{
				ry = 94;
			}
			else
			{
				ry = 0;
			}
		}
		
		Mob enemy = new Mob(rx,ry,6,6,graphics.get(type+2),10,type);
		enemies.add(enemy);
		
	}
	
	public void loadGraphics()
	{
		graphics.add(new Texture(Gdx.files.internal("background.png")));
		graphics.add(new Texture(Gdx.files.internal("playerShip.png")));
		graphics.add(new Texture(Gdx.files.internal("enemy1.png")));
	}
}

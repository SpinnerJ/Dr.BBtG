package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;

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

	private int score;
	private int scoreMultiplier;

	private ArrayList<Mob> enemies = new ArrayList<Mob>();
	private ArrayList<Texture> graphics = new ArrayList<Texture>();
	private ArrayList<Sound> sounds = new ArrayList<Sound>();
	private Sound music;
	private ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
	private ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
	private enum gameStates{MENU,START,PLAYING,GAMEOVER,HIGHSCORE};
	private gameStates currentState = gameStates.MENU;
	private int soundChanged = 0;
	private int time = 0;
	private int frames = 0;
	private int spawns = 1;
	
	@Override
	public void create () {
		loadGraphics();
		loadSounds();
		background = graphics.get(0);
		
		aspect = Gdx.graphics.getWidth()/Gdx.graphics.getHeight(); //width to height ratio
		camera = new OrthographicCamera(GAMEWIDTH,GAMEHEIGHT*aspect); //2D camera
		camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0); //mapping camera position to screen viewport
		batch = new SpriteBatch(); //required to draw in libGDX
		test = new Mob(49,49,6,6,graphics.get(1),10,4); //mob given to player
		player = new Player(test); //player object
		m = player.getMob(); //reference to player's mob
		Gdx.input.setInputProcessor(new Input(player,camera)); //links libGDX input to our input class
		
	    music = sounds.get(0);
	    music.play();
	    
		db = new Database();
		db.connect();
		String test = db.queryAll();
		System.out.println(test);
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //use openGL to clear the screen in hardware
		
		switch(currentState)
		{
		case MENU:
			//initial menu on loading the game
			//start/resume Game
			//highscores
			//exit

			if(soundChanged == 0 || soundChanged == 2)

			if(soundChanged == 0 || soundChanged == 2 || soundChanged == 3)

			{
				soundChanged = 1;
				music.stop();
				music = sounds.get(0);
				music.loop();
			}
			if(player.getClick().x >= 31.56 && player.getClick().x <= 67.96) //poll mouse if last place clicked was a button
			{
				if(player.getClick().y >= 65.0 && player.getClick().y <= 74.79)
				{
					//clicked play button
					currentState = gameStates.PLAYING;
					player.setClick(new Vector3(0,0,0));
				}
			}
			
			if(player.getClick().x >= 31.40 && player.getClick().x <= 67.96) //poll mouse if last place clicked was a button
			{
				if(player.getClick().y >= 48.33 && player.getClick().y <= 58.54)
				{
					//clicked highscore button
					currentState = gameStates.HIGHSCORE;
					player.setClick(new Vector3(0,0,0));
				}
			}
			
			if(player.getClick().x >= 31.56 && player.getClick().x <= 67.81) //poll mouse if last place clicked was a button
			{
				if(player.getClick().y >= 32.08 && player.getClick().y <= 41.66)
				{
					Gdx.app.exit();//quit
				}
			}
			batch.begin(); //start drawing graphics
			batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight); //draw camera viewport
			batch.draw(graphics.get(5),camera.viewportWidth*.25f,camera.viewportHeight*.1f,camera.viewportWidth*.5f,camera.viewportHeight*.8f); //draw camera viewport
			batch.end();
			break;
		case START:
			//reset state of game so that health is maxed, time is zero, etc
			//then go straight into playing state
			time = 0;
			frames = 0;
			spawns = 1;

			score = 0;
			scoreMultiplier = 1;

			batch.begin(); //start drawing graphics
			batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight); //draw camera viewport
			batch.end();
			break;
		case PLAYING:
			frames++;
			if(frames >= 60)
			{
				frames = 0;
				time++;
				int spawnType = 0;
				if(time%10 == 0)
				{
					for(int i=0;i<spawns;i++)
					{
						double r = Math.random();
						if(r < 0.33)
						{
							spawnType = 0;
						}
						else if(r < 0.66)
						{
							spawnType = 1;
						}
						else
						{
							spawnType = 2;
						}
						spawnEnemy(spawnType);
					}
					spawns++;
				}

			}
			
			if(soundChanged == 0 || soundChanged == 1)
			{	
				score++;
				scoreMultiplier = 1;
			}
			
			if(soundChanged == 0 || soundChanged == 1|| soundChanged == 3)

			{
				soundChanged = 2;
				music.stop();
				music = sounds.get(1);
				music.loop();
			}
			if(player.getEscape() == true)
			{
				currentState = gameStates.MENU;
				player.setEscape(false);
			}
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

				player.setAceAngle(m.getAngle());
			}
			
			//accelerate player if acceleration key is pressed
			if(player.getAccelerating() > 0) {
				if(player.getAcceleration() < 0f) {
					player.setAcceleration(0f);
				}
				player.setAcceleration((player.getAcceleration()+(player.getAccelerating()*0.0005f)));
			} else if(player.getAccelerating() < 0) {
				if(player.getAcceleration() > 0f) {
					player.setAcceleration(0f);
				}
				player.setAcceleration((player.getAcceleration()+(player.getAccelerating()*0.0005f)));

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
			
			if(player.getTimer() > 0)
			{
				player.setTimer(player.getTimer()-1);
			}
			if(player.getShooting() == true && player.getTimer() <= 0)
			{
				Bullet bullet = new Bullet(m.getX()+2f,m.getY()+2f,m.getAngle(),graphics.get(7),1f+m.getSpeed());
				playerBullets.add(bullet);
				player.setTimer(15);

				sounds.get(3).play();

			}
			
			for(int i=0;i<enemies.size();i++)
			{
				enemies.get(i).update(m, enemyBullets, graphics.get(6));
			}
			
			for(int i=0;i<playerBullets.size();i++)
			{
				playerBullets.get(i).move();
			}
			for(int i=0;i<enemyBullets.size();i++)
			{
				enemyBullets.get(i).move();
			}
			
			batch.begin(); //start drawing graphics
			batch.draw(background,0,0,camera.viewportWidth,camera.viewportHeight); //draw camera viewport
			for(int i=0;i<enemies.size();i++)
			{
				boolean collision = enemies.get(i).checkCollision(m);
				if(collision == true)
				{
					if(player.getInvulnerable()<=0 && enemies.get(i).getAlive() == true)
					{
						player.setInvulnerable(60);
						player.setHealth(player.getHealth()-1);
						enemies.get(i).kill(graphics.get(8));

						sounds.get(4).play();
						score += (100 * scoreMultiplier);
						scoreMultiplier++;

						if(player.getHealth() <= 0)
						{
							currentState = gameStates.GAMEOVER;
							frames = 180;
						}
					}
				}
				enemies.get(i).draw(batch);
				if(enemies.get(i).getAlive() == false && enemies.get(i).getTimer() <= 0)
				{
					enemies.remove(i);
				}
			}
			for(int i=0;i<playerBullets.size();i++)
			{
				for(int k=0;k<enemies.size();k++)
				{
					boolean collision = playerBullets.get(i).checkCollision(enemies.get(k));
					if(collision == true && enemies.get(k).getAlive() == true)
					{
						playerBullets.get(i).setAlive(false);
						enemies.get(k).kill(graphics.get(8));

						sounds.get(6).play();
						score += (100 * scoreMultiplier);
						scoreMultiplier++;

					}
				}
				if(playerBullets.get(i).getAlive() == false)
				{
					playerBullets.remove(i);
				}
				else
				{
					playerBullets.get(i).draw(batch);
				}
			}
			for(int i=0;i<enemyBullets.size();i++)
			{
				boolean collision = enemyBullets.get(i).checkCollision(m);
				if(collision == true)
				{
					enemyBullets.get(i).setAlive(false);
					player.setInvulnerable(60);
					player.setHealth(player.getHealth()-1);

					sounds.get(4).play();

					if(player.getHealth() <= 0)
					{
						currentState = gameStates.GAMEOVER;
					}
				}
				if(enemyBullets.get(i).getAlive() == false)
				{
					enemyBullets.remove(i);
				}
				else
				{
					enemyBullets.get(i).draw(batch);
				}
			}
			if(player.getInvulnerable() > 0)
			{
				player.setInvulnerable(player.getInvulnerable()-1);
			}
			test.draw(batch);//draw player's mob
			batch.end();
			break;
		case GAMEOVER:
			frames--;
			if(frames <= 0)
			{
				frames = 0;
				if(player.getShooting() == true)
				{
					currentState = gameStates.HIGHSCORE;
				}
			}
			if(soundChanged == 0 || soundChanged == 1|| soundChanged == 2)
			{
				soundChanged = 3;
				music.stop();
				music = sounds.get(2);
				music.play();
			}
			batch.begin();
			batch.draw(graphics.get(9),0,0,camera.viewportWidth,camera.viewportHeight); //draw camera viewport
			batch.end();
			break;
		case HIGHSCORE:
			break;
		}
	}
	
	@Override
	public void dispose () {
		//cleaning up memory by getting rid of textures
		for(int i = 0;i<graphics.size();i++)
		{
			graphics.get(i).dispose();
		}
		
		for(int k=0;k<sounds.size();k++)
		{
			sounds.get(k).dispose();
		}
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
			ry = (float)Math.random()*95;
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
			rx = (float)Math.random()*95;
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
		graphics.add(new Texture(Gdx.files.internal("background.png")));//0
		graphics.add(new Texture(Gdx.files.internal("playerShip.png")));//1
		graphics.add(new Texture(Gdx.files.internal("enemy1.png")));//2
		graphics.add(new Texture(Gdx.files.internal("enemy2.png")));//3
		graphics.add(new Texture(Gdx.files.internal("enemy3.png")));//4
		graphics.add(new Texture(Gdx.files.internal("menu.png")));//5
		graphics.add(new Texture(Gdx.files.internal("dotBlue.png")));//6
		graphics.add(new Texture(Gdx.files.internal("dotRed.png")));//7
		graphics.add(new Texture(Gdx.files.internal("explosion.png")));//8
		graphics.add(new Texture(Gdx.files.internal("gameover.png")));//9
	}
	
	public void loadSounds()
	{
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("menu.mp3"));
		sounds.add(sound);
		Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("btg.mp3"));
		sounds.add(sound2);

		Sound sound3 = Gdx.audio.newSound(Gdx.files.internal("Game_over_music.mp3"));
		sounds.add(sound3);
		Sound sound4 = Gdx.audio.newSound(Gdx.files.internal("LaserGun.mp3"));
		sounds.add(sound4);
		Sound sound5 = Gdx.audio.newSound(Gdx.files.internal("LongExplosion.mp3"));
		sounds.add(sound5);
		Sound sound6 = Gdx.audio.newSound(Gdx.files.internal("PlasmaGun.mp3"));
		sounds.add(sound6);
		Sound sound7 = Gdx.audio.newSound(Gdx.files.internal("SpaceyExplosion.mp3"));
		sounds.add(sound7);
	}
}

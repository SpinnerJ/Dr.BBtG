package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mob {
	private float x;
	private float y;
	private int width;
	private int height;
	private float angle;
	private float speed;
	private int health;
	private Sprite sprite;
	private boolean alive;
	private int timer;
	private static enum states{IDLE,HUNT,FIRE};
	private states currentState = states.IDLE;
	private int type;
	
	public Mob(float x,float y,int width,int height,Texture img,int health,int type)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = 0;
		this.sprite = new Sprite(img);
		this.sprite.setPosition(x,y);
		this.sprite.setSize(width,height);
		this.sprite.setOrigin((width/2f), (height/2f));
		this.health = health;
		this.speed = 0;
		this.alive = true;
		this.timer = 0;
		this.type = type;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public void setX(float x)
	{
		this.x = x;
		this.sprite.setX(x);
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void setY(float y)
	{
		this.y = y;
		this.sprite.setY(y);
	}
	
	public float getAngle()
	{
		return this.angle;
	}
	
	public void setAngle(float angle)
	{
		this.angle = angle;
		this.sprite.setRotation(angle);
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void draw(SpriteBatch batch)
	{
		this.sprite.draw(batch);
	}
	
	public void dispose()
	{
		this.sprite.getTexture().dispose();
	}
	
	public Sprite getSprite()
	{
		return this.sprite;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void update(Mob player)
	{
		boolean nearPlayer = false;
		
		switch(currentState)
		{
		case IDLE:
			break;
		case HUNT:
			break;
		case FIRE:
			break;
		
		}
	}
	
}

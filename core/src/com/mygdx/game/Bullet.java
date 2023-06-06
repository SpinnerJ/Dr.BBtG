package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
	private float x;
	private float y;
	private int width;
	private int height;
	private float angle;
	private float speed;
	private Sprite sprite;
	private boolean alive;
	private int timer;
	
	public Bullet(float x, float y, float angle, Texture img, float speed)
	{
		this.x = x;
		this.y = y;
		this.angle = angle;
		width = 2;
		height = 2;
		this.speed = speed;
		this.sprite = new Sprite(img);
		this.sprite.setPosition(x,y);
		this.sprite.setSize(width,height);
		this.sprite.setOrigin((width/2f), (height/2f));
		this.alive = true;
	}
	
	public boolean checkCollision(Mob m)
	{
		boolean collision = false;
		float bx = this.x+this.sprite.getOriginX();
		float by = this.y+this.sprite.getOriginY();
		float br = this.width/2f;
		
		float mx = m.getSprite().getOriginX()+m.getX();
		float my = m.getSprite().getOriginY()+m.getY();
		float mr = m.getWidth()/2f;
		
		float dx = bx - mx;
		float dy = by - my;
		
		float distance = (float)Math.sqrt(dx*dx+dy*dy);
		if(distance < br+mr)
		{
			collision = true;
		}
		else
		{
			collision = false;
		}
		
		return collision;
	}
	
	public void move()
	{
		float speedX = (float)Math.cos(Math.toRadians(this.angle+90));
		speedX = speedX*this.speed;
		
		//get how fast the player is moving in the y direction
		float speedY = (float)Math.sin(Math.toRadians(this.angle+90));
		speedY = speedY*this.speed;
		
		setX(this.x+speedX);
		setY(this.y+speedY);
		
		if(this.x < -2 || this.x > 102 || this.y < -2 || this.y > 102)
		{
			alive = false;
		}
			
	}
	
	public void setX(float x)
	{
		this.x = x;
		this.sprite.setX(x);
	}
	
	public void setY(float y)
	{
		this.y = y;
		this.sprite.setY(y);
	}
	
	public void draw(SpriteBatch batch)
	{
		this.sprite.draw(batch);
	}
	
	public void remove()
	{
		this.sprite.getTexture().dispose();
	}
	
	public boolean getAlive()
	{
		return this.alive;
	}
	
	public void setAlive(boolean a)
	{
		this.alive = a;
	}
}

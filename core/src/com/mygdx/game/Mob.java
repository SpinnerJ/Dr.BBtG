package com.mygdx.game;
import java.util.ArrayList;

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
	private static enum states{HUNT,FIRE,DEAD};
	private states currentState = states.FIRE;
	private int type;
	private int between = 0;
	
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
		if(this.type == 0)
		{
			currentState = states.HUNT;
			this.speed = 1;
		}
		
		if(this.type == 2)
		{
			currentState = states.HUNT;
			this.speed = 1.3f;
			this.between = 100;
		}
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
	
	public void update(Mob player,ArrayList<Bullet> bullets, Texture tex)
	{
		this.timer--;
		boolean nearPlayer = false;
		
		float px = player.getX();
		float py = player.getY();
		float mx = this.x;
		float my = this.y;
		
		float dx = mx - px;
		float dy = my - py;
		
		int quad = 0;//0 top left, 1 top right, 2 bottom right, 3 bottom left
		
		switch(currentState)
		{
		case HUNT:
			if(this.timer <= 0)
			{
				if(dx < 0)
				{
					if(dy < 0)
					{
						quad = 3;
					}
					else
					{
						quad = 0;
					}
				}
				else
				{
					if(dy > 0)
					{
						quad = 1;
					}
					else
					{
						quad = 2;
					}
				}
				
				float r = 0;
				switch(quad)
				{
				case 0:
					r = (float)Math.random()*90f + 90;
					break;
				case 1:
					r = (float)Math.random()*90f;
					break;
				case 2:
					r = (float)Math.random()*90f + 270;
					break;
				case 3:
					r = (float)Math.random()*90f + 180;
					break;
				}
				this.setAngle(r+90);
				this.timer = 20;
			}
			if(this.timer %2 == 0)
			{
				move();
			}
			if(type == 2)
			{
				between--;
				if(between <= 0)
				{
					between = 100;
					this.currentState = states.FIRE;
				}
			}
			break;
		case FIRE:
			//turn toward player, fire
			if(this.timer <= 0)
			{
				if(dx < 0)
				{
					if(dy < 0)
					{
						quad = 3;
					}
					else
					{
						quad = 0;
					}
				}
				else
				{
					if(dy > 0)
					{
						quad = 1;
					}
					else
					{
						quad = 2;
					}
				}
				
				float r = 0;
				switch(quad)
				{
				case 0:
					r = (float)Math.random()*90f + 90;
					break;
				case 1:
					r = (float)Math.random()*90f;
					break;
				case 2:
					r = (float)Math.random()*90f + 270;
					break;
				case 3:
					r = (float)Math.random()*90f + 180;
					break;
				}
				this.setAngle(r+90);
				this.timer = 40+(int)(Math.random()*40);
				Bullet bullet = new Bullet(mx+3,my+3,this.angle,tex,1);
				bullets.add(bullet);
			}
			if(type == 2)
			{
				between--;
				if(between <= 0)
				{
					this.currentState = states.HUNT;
					between = 100;
				}
			}
			break;
		case DEAD:
			break;
		}
	}
	
	public void kill(Texture tex)
	{
		this.currentState = states.DEAD;
		this.alive = false;
		this.sprite = new Sprite(tex);
		this.sprite.setPosition(x,y);
		this.sprite.setSize(width,height);
		this.sprite.setOrigin((width/2f), (height/2f));
		this.timer = 60;
	}
	
	public boolean getAlive()
	{
		return this.alive;
	}
	
	public int getTimer()
	{
		return this.timer;
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
			
	}
}

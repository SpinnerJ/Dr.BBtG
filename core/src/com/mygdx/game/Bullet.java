package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
	
	public Bullet(float x, float y, float angle, Texture img)
	{
		this.x = x;
		this.y = y;
		this.angle = angle;
		width = 1;
		height = 1;
		speed = 0.008333f; //travel half the screen in 1 second
		this.sprite = new Sprite(img);
		this.sprite.setPosition(x,y);
		this.sprite.setSize(width,height);
		this.sprite.setOrigin((width/2f), (height/2f));
	}
	
	public boolean checkCollision(Mob m)
	{
		boolean collision = false;
		float bx = this.sprite.getOriginX();
		float by = this.sprite.getOriginY();
		float br = this.width/2f;
		
		float mx = m.getSprite().getOriginX();
		float my = m.getSprite().getOriginY();
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
}

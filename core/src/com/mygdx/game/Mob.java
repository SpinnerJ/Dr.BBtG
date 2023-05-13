package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mob {
	private int x;
	private int y;
	private float angle;
	private int health;
	private Texture img;
	
	public Mob(int x,int y,float angle,int health,Texture img)
	{
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.health = health;
		this.img = img;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public float getAngle()
	{
		return this.angle;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(this.img, this.x, this.y);
	}
}

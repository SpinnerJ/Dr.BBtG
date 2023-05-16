package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Mob {
	private int x;
	private int y;
	private int width;
	private int height;
	private float angle;
	private int health;
	private Sprite sprite;
	
	public Mob(int x,int y,int width,int height,Texture img,int health)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = 0;
		this.sprite = new Sprite(img);
		this.sprite.setPosition(x,y);
		this.sprite.setSize(width,height);
		this.sprite.setOrigin(x+(width/2), y+(height/2));
		this.health = health;
		
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
	
	public void setAngle(float angle)
	{
		this.angle = angle;
		this.sprite.setRotation(angle);
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
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
	private float x;
	private float y;
	private float width;
	private float length;
	private String text;
	private Sprite img;
	
	public Button(float x, float y,String text,Texture tex)
	{
		this.x = x;
		this.y = y;
		this.text = text;
		this.width = 60;
		this.length = 10;
		this.img = new Sprite(tex);
	}
	
	public void draw(SpriteBatch batch)
	{
		this.img.draw(batch);
	}
}

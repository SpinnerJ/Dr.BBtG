package com.mygdx.game;

public class Player {
	private int rotating;
	private float acceleration;
	private int accelerating;
	private Mob mob;
	private final float MAXSPEED = 1;
	
	public Player(Mob m)
	{
		rotating = 0;
		mob = m;
		acceleration = 0;
		accelerating = 0;
	}
	
	public int getRotating()
	{
		return rotating;
	}
	
	public void setRotating(int r)
	{
		rotating = r;
	}
	
	public int getAccelerating()
	{
		return accelerating;
	}
	
	public void setAccelerating(int a)
	{
		accelerating = a;
	}
	
	public Mob getMob()
	{
		return mob;
	}
	
	public float getAcceleration()
	{
		return acceleration;
	}
	
	public void setAcceleration(float a)
	{
		this.acceleration = a;
	}
	
	public float getMaxSpeed()
	{
		return MAXSPEED;
	}
}

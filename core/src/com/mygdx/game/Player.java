package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;

public class Player {
	private int rotating;
	private float acceleration;
	private int accelerating;
	private Mob mob;
	private final float MAXSPEED = 1;
	private float aceAngle;
	private int score;
	private Vector3 clicked;
	private boolean escape = false;
	private int invulnerable = 0;
	private boolean shooting = false;
	private int timer = 0;
	private int health = 10;
	
	public Player(Mob m)
	{
		rotating = 0;
		mob = m;
		acceleration = 0;
		accelerating = 0;
		aceAngle = 0;
		score = 0;
		clicked = new Vector3(0,0,0);
	}
	
	public int getRotating()
	{
		return rotating;
	}
	
	public void setRotating(int r)
	{
		rotating = r;
	}
	
	public float getAceAngle()
	{
		return aceAngle;
	}
	
	public void setAceAngle(float ace)
	{
		aceAngle = ace;
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
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int s)
	{
		score = s;
	}
	
	public void setClick(Vector3 v)
	{
		clicked = v;
	}
	
	public Vector3 getClick()
	{
		return clicked;
	}
	
	public boolean getEscape()
	{
		return escape;
	}
	
	public void setEscape(boolean e)
	{
		escape = e;
	}
	
	public int getInvulnerable()
	{
		return invulnerable;
	}
	
	public void setInvulnerable(int inv)
	{
		this.invulnerable = inv;
	}
	
	public void setShooting(boolean s)
	{
		shooting = s;
	}
	
	public boolean getShooting()
	{
		return shooting;
	}
	
	public void setTimer(int t)
	{
		timer = t;
	}
	
	public int getTimer()
	{
		return timer;
	}

	public void setHealth(int h)
	{
		health = h;
	}

	public int getHealth()
	{
		return health;
	}
}

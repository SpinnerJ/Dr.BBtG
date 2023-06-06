package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Input implements InputProcessor{
	private Player player;
	private OrthographicCamera camera;
	public Input(Player p, OrthographicCamera camera)
	{
		player = p;
		this.camera = camera;
	}
	public boolean keyDown(int keycode) {
		switch(keycode)
		{
		case 51://w
			player.setAccelerating(1);
			break;
		case 47://s
			player.setAccelerating(-1);
			break;
		case 29://a
			player.setRotating(1);
			break;
		case 32://d
			player.setRotating(-1);
			break;
		case 62://space
			player.setShooting(true);
			break;
		}
		return false;
	}

	public boolean keyUp(int keycode) {
		switch(keycode)
		{
		case 51://w
			player.setAccelerating(0);
			break;
		case 47://s
			player.setAccelerating(0);
			break;
		case 29://a
			player.setRotating(0);
			break;
		case 32://d
			player.setRotating(0);
			break;
		case 62://space
			player.setShooting(false);
			break;
		case 111://escape
			player.setEscape(true);
			break;
		}
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector3 vec = camera.unproject(new Vector3(x,y,0));
		player.setClick(vec);
		player.setShooting(true);
		return false;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		player.setShooting(false);
		return false;
	}

	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	public boolean mouseMoved(int x, int y) {
		return false;
	}

	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}

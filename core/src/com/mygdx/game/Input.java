package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor{
	private Player player;
	public Input(Player p)
	{
		player = p;
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
			break;
		}
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
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

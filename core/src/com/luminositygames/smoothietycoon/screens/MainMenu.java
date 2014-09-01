package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Songs;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class MainMenu implements Screen2 {

	private GameTween logoTween;

	@Override
	public void load() {
		logoTween = new GameTween(25, GameTween.LOGO);
		Songs.play("mainmenu");
	}

	@Override
	public void render(float delta) {
		Image.draw("mainmenu", 0, 0);
		Image.draw("stand", 50, 175);
		Image.draw("stand", Constants.WIDTH - 50 - Image.get("stand").getWidth(), 175);
		Image.draw("player", 325, 300);
		Image.draw("logotext", Constants.WIDTH / 2 - Image.get("logotext").getWidth() / 2, logoTween.getValue());
		Image.draw("playbutton", Constants.WIDTH / 2 - Image.get("playbutton").getWidth() / 2, 325);
		Image.draw("credits", 725, 600);
	}

	@Override
	public void update(float delta) {
		logoTween.update(delta);
		handleTouch();
	}

	private void handleTouch() {
		if (Image.get("playbutton").isTouched()){
			SmoothieTycoon.setScreen(SmoothieTycoon.tutorial);
		} else if (Image.get("credits").isTouched()){
			Gdx.net.openURI("http://luminositygames.com/");
		}
	}

	@Override
	public void keyPress(int keycode) {

	}
}

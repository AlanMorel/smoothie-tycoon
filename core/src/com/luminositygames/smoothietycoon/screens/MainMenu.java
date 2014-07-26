package com.luminositygames.smoothietycoon.screens;

import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

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

	private boolean tutorial;

	@Override
	public void load() {
		logoTween = new GameTween(25, GameTween.LOGO);
		tutorial = Constants.TUTORIAL;
	}

	@Override
	public void render(float delta) {
		Image.draw("salesBackground", 0, 0);
		Image.draw("stand", 50, 175);
		Image.draw("stand", Constants.WIDTH - 50 - Image.get("stand").getWidth(), 175);
		Image.draw("player", 325, 300);
		Image.draw("logotext", Constants.WIDTH / 2 - Image.get("logotext").getWidth() / 2, logoTween.getValue());
		Image.draw("playbutton", Constants.WIDTH / 2 - Image.get("playbutton").getWidth() / 2, 325);
	}

	@Override
	public void update(float delta) {
		logoTween.update(delta);
		if (Image.get("playbutton").isTouched()){
			if (tutorial){
				SmoothieTycoon.setScreen(SmoothieTycoon.tutorial);
			} else {
				SmoothieTycoon.setScreen(SmoothieTycoon.gameplay);
			}
		}
	}

	@Override
	public void keyPress(int keycode) {

	}
}

package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
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

public class Splash implements Screen2 {

	private GameTween splashAlpha;

	@Override
	public void load() {
		splashAlpha = new GameTween(0, GameTween.SPLASH);
	}

	@Override
	public void render(float delta) {
		Image.batch.begin();
		Image.get("splash").getSprite().setAlpha(splashAlpha.getValue());
		Image.get("splash").getSprite().draw(Image.batch);
		Image.batch.end();
	}

	@Override
	public void update(float delta) {
		splashAlpha.update(delta);
		handleTouch();
	}

	private void handleTouch() {
		if (Gdx.input.justTouched()){
			Gdx.net.openURI("http://luminositygames.com/");
		}
	}

	@Override
	public void keyPress(int keycode) {

	}
}

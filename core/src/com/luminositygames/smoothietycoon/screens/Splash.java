package com.luminositygames.smoothietycoon.screens;

import com.luminositygames.smoothietycoon.Screen2;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

public class Splash implements Screen2{

	private GameTween splashAlpha;
	
	@Override
	public void load() {
		splashAlpha = new GameTween(0, GameTween.SPLASH);
		SmoothieTycoon.setScreen(SmoothieTycoon.mainMenu);
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
	}

	@Override
	public void keyPress(int keycode) {
		
	}
}

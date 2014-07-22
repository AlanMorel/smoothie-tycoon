package com.luminositygames.smoothietycoon.screens;

import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.Screen2;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

public class MainMenu implements Screen2 {
	
	private GameTween logoTween;
	private boolean firstTime = true;
	
	@Override
	public void load() {
		logoTween = new GameTween(25, GameTween.LOGO);
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
			if (firstTime){
				//SmoothieTycoon.setScreen(SmoothieTycoon.gameplay);
				SmoothieTycoon.setScreen(SmoothieTycoon.tutorial);
			} else {
				//GAMEPLAY
			}
		}
	}

	@Override
	public void keyPress(int keycode) {
		
	}
}

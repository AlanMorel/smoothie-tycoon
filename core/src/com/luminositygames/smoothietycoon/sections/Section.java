package com.luminositygames.smoothietycoon.sections;

import com.luminositygames.smoothietycoon.util.GameTween;

public interface Section {

//Stand
//Kitchen
//Market
//Office
	
	public void render (float delta);

	public void update(float delta);
	
	public void renderTutorial (GameTween tween, int stage);

	public void handleTouch();
}

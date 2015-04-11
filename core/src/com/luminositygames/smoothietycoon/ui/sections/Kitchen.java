package com.luminositygames.smoothietycoon.ui.sections;

import com.luminositygames.smoothietycoon.ui.windows.Window;
import com.luminositygames.smoothietycoon.util.Image;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Kitchen extends Section {

	@Override
	public void render(float delta) {
		Image.draw("kitchen", 0, 0);
		Image.draw("refridgerator", 150, 94);
		Image.draw("juicer", 750, 224);
		Image.draw("blender", 975, 208);
	}

	@Override
	public void handle() {
		if (Image.get("refridgerator").isTouched()){
			Window.open(Window.REFRIDGERATOR);
		} else if (Image.get("juicer").isTouched()){
			Window.open(Window.JUICER);
		} else if (Image.get("blender").isTouched()){
			Window.open(Window.BLENDER);
		}
	}

}

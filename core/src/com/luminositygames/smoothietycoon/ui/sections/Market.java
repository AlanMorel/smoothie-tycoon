package com.luminositygames.smoothietycoon.ui.sections;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.ui.windows.Window;
import com.luminositygames.smoothietycoon.util.Fonts;
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

public class Market extends Section {

	@Override
	public void render(float delta) {
		Image.draw("market", 0, 0);
		Image.draw("fruitSeller", 215, 250);
		Image.draw("yogurtSeller", 575, 250);
		Image.draw("cupSeller", 950, 250);
		Image.draw("fruitstand", 165, 170);
		Image.draw("yogurtstand", 520, 170);
		Image.draw("cupstand", 895, 170);
		Fonts.left("Fruits", 220, 425, Color.WHITE, 36);
		Fonts.left("Yogurt", 570, 425, Color.WHITE, 36);
		Fonts.left("Cups", 965, 425, Color.WHITE, 36);
	}

	@Override
	public void handle() {
		if (Image.get("fruitstand").isTouched()){
			Window.open(Window.FRUIT);
		} else if (Image.get("yogurtstand").isTouched()){
			Window.open(Window.YOGURT);
		} else if (Image.get("cupstand").isTouched()){
			Window.open(Window.CUPS);
		}
	}

}

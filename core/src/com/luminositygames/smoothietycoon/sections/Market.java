package com.luminositygames.smoothietycoon.sections;

import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.util.Fonts;
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

public class Market implements Section {
	
	public static Rectangle FRUIT_RECT = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300); 
	public static Rectangle YOGURT_RECT = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300); 
	public static Rectangle CUPS_RECT = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300); 

	@Override
	public void render(float delta) {
		Image.draw("market", 0, 0);
		Image.draw("fruitstand", 165, 170);
		Image.draw("yogurtstand", 520, 170);
		Image.draw("cupstand", 895, 170);
		
		Fonts.left("Fruits", 220, 425, Fonts.WHITE_36);
		Fonts.left("Yogurt", 570, 425, Fonts.WHITE_36);
		Fonts.left("Cups", 965, 425, Fonts.WHITE_36);
	}
}

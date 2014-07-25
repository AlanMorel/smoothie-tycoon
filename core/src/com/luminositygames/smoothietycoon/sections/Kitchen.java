package com.luminositygames.smoothietycoon.sections;

import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
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

public class Kitchen implements Section {

	public static Rectangle REFRIDGERATOR_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 300);
	public static Rectangle JUICER_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 300);
	public static Rectangle BLENDER_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 125);

	@Override
	public void render(float delta) {
		Image.draw("kitchen", 0, 0);
		Image.draw("refridgerator", 150, 94);
		Image.draw("juicer", 750, 224);
		Image.draw("blender", 975, 208);
	}
}

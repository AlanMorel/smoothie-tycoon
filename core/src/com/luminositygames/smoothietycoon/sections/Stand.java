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

public class Stand implements Section {

	public static Rectangle STAND_RECT = new Rectangle(Constants.WIDTH / 2 - 500 / 2, 150, 500, 300); 

	@Override
	public void render(float delta) {
		Image.draw("salesBackground", 0, 0);
		Image.draw("player", Constants.WIDTH /2 - Image.get("player").getWidth() / 2, 250);
		Image.draw("stand", Constants.WIDTH /2 - Image.get("stand").getWidth() / 2, 175);
		Image.draw("slantedsign", 650, 175);
	}
}

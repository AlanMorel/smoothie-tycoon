package com.luminositygames.smoothietycoon.sections;

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

public class Office implements Section {
	
	@Override
	public void render(float delta) {
		Image.draw("office", 0, 0);
	}
}

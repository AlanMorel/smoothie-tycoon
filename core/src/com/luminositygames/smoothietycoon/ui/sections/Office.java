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

public class Office extends Section {

	@Override
	public void render(float delta) {
		Image.draw("office", 0, 0);
		Image.draw("advertise", 130, 213);
		Image.draw("statistics", 520, 203);
		Image.draw("upgrades", 910, 188);
	}

	@Override
	public void handle() {
		if (Image.get("advertise").isTouched()){
			Window.open(Window.ADVERTISE);
		} else if (Image.get("statistics").isTouched()){
			Window.open(Window.STATISTICS);
		} else if (Image.get("upgrades").isTouched()){
			Window.open(Window.UPGRADES);
		}
	}

}

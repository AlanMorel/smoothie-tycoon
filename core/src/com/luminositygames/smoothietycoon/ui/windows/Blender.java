package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
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

public class Blender extends Window {

	public Blender(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		Image.rectangle(370, 195, 34, 34, 0.9f, Color.PINK);
		Fonts.left("Refill smoothie container", 425, 200, Color.BLACK, 36);
	}

	@Override
	public void handle(Game game) {
		if (Fonts.isTouched("Refill smoothie container", 425, 200, Color.BLACK, 36)){
			game.getContainer().refill(game);
			close();
		}
	}

}

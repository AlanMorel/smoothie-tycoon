package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.entities.Advertisements;
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

public class Advertise extends Window {

	public Advertise(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		for (int i = 0; i < 5; i++){
			Image.draw("ad", 300, i * 85 + 195);
			Fonts.left(Advertisements.getString(i), 390, i * 85 + 200, Color.BLACK, 36);
		}
	}

	@Override
	public void handle(Game game) {
		for (int i = 0; i < 5; i++){
			if (Fonts.isTouched(Advertisements.getString(i), 390, i * 85 + 200, Color.BLACK, 36)){
				Advertisements.getById(i).buy(game.getPlayer());
				game.setMaxCustomers();
				close();
			}
		}
	}
}

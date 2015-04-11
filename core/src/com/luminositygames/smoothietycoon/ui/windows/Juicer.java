package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.ui.Shop;
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

public class Juicer extends Window {

	public Juicer(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		for (int i = 0; i < 3; i++){
			Image.draw("juice", 385, i * 85 + 195);
			Fonts.left(Shop.JUICE.getString(i), 435, i * 85 + 200, Color.BLACK, 36);
		}
	}

	@Override
	public void handle(Game game) {
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Shop.JUICE.getString(i), 435, i * 85 + 200, Color.BLACK, 36)){
				game.getPlayer().makeJuice(Shop.JUICE.getAmount(i), Shop.JUICE.getPrice(i));
				close();
			}
		}
	}

}

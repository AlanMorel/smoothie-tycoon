package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.Main;
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

public class GameOver extends Window {

	public GameOver(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		Fonts.center("You have made mom proud!", 200, Color.WHITE, 36);
		Fonts.center("You finished with " + Main.format(game.getPlayer().getMoney()) + "!", 285, Color.WHITE, 48);
		Fonts.center("Close to keep playing!", 400, Color.WHITE, 48);
	}

	@Override
	public void handle(Game game) {
	}

}

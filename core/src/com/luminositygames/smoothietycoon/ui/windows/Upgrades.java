package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.entities.Upgrade;
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

public class Upgrades extends Window {

	public Upgrades(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		for (int i = 0; i < 1; i++){
			Image.draw("upgradesIcon", 340, i * 85 + 190);
		}
		String refillExtra = Upgrade.DOUBLE_CONTAINER.hasPurchased() ? "(Purchased!)" : "($" + Upgrade.DOUBLE_CONTAINER.getPrice() + ")";
		Fonts.left("Double Container " + refillExtra, 410, 200, Color.BLACK, 36);
	}

	@Override
	public void handle(Game game) {
		String refillExtra = Upgrade.DOUBLE_CONTAINER.hasPurchased() ? "(Purchased!)" : "($" + Upgrade.DOUBLE_CONTAINER.getPrice() + ")";
		if (Fonts.isTouched("Double container " + refillExtra, 460, 200, Color.BLACK, 36)){
			if (!Upgrade.DOUBLE_CONTAINER.hasPurchased() && game.getPlayer().canPay(Upgrade.DOUBLE_CONTAINER.getPrice())){
				Upgrade.DOUBLE_CONTAINER.buy(game.getPlayer());
				close();
			}
		}
	}

}

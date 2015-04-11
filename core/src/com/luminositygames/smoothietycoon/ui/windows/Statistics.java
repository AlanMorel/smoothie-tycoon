package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.entities.Statistics.StatisticsEntry;
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

public class Statistics extends Window {

	private int offset;

	public Statistics(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		Image.draw("upArrow", 850, 175);
		Image.draw("downArrow", 850, 350);
		int lowerRange = getLowerRange(game);
		for (int i = lowerRange; i < lowerRange + 3; i++){
			StatisticsEntry entry = game.getStats().getEntryByDay(i);
			if (entry != null){
				int relative = i - lowerRange + 1;
				Image.draw("statisticsicon", 360, relative * 85 + 110);
				Fonts.left(entry.toString(), 450, relative * 85 + 115, Color.BLACK, 36);
			}
		}
	}

	private int getLowerRange(Game game) {
		int base = game.getDay() - 3 + offset;
		return base < 1 ? 1 : base;
	}

	@Override
	public void handle(Game game) {
		if (Image.get("upArrow").isTouched()){
			if (offset > - game.getDay() + 4){
				offset--;
			}
		} else if (Image.get("downArrow").isTouched()){
			if (offset < 0){
				offset++;
			}
		}
	}

}

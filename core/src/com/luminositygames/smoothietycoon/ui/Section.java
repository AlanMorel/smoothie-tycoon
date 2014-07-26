package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.util.Fonts;
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

public class Section {

	public static final Rectangle STAND_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 500 / 2, 150, 500, 300);
	public static final Rectangle REFRIDGERATOR_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 300);
	public static final Rectangle JUICER_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 300);
	public static final Rectangle BLENDER_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 125);
	public static final Rectangle FRUIT_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300);
	public static final Rectangle YOGURT_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300);
	public static final Rectangle CUPS_RECTANGLE = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300);

	public static final byte STAND = 1;
	public static final byte KITCHEN = 2;
	public static final byte MARKET = 3;
	public static final byte OFFICE = 4;

	private int section;

	public Section(){
		this.section = STAND;
	}

	public boolean isStand(){
		return section == STAND;
	}

	public boolean isKitchen(){
		return section == KITCHEN;
	}

	public boolean isMarket(){
		return section == MARKET;
	}

	public boolean isOffice(){
		return section == OFFICE;
	}

	public void setSection(int sec){
		section = sec;
	}

	public void render(float delta){
		if (section == STAND){
			Image.draw("salesBackground", 0, 0);
			Image.draw("player", Constants.WIDTH /2 - Image.get("player").getWidth() / 2, 250);
			Image.draw("stand", Constants.WIDTH /2 - Image.get("stand").getWidth() / 2, 175);
			Image.draw("slantedsign", 650, 175);
		} else if (section == KITCHEN){
			Image.draw("kitchen", 0, 0);
			Image.draw("refridgerator", 150, 94);
			Image.draw("juicer", 750, 224);
			Image.draw("blender", 975, 208);
		} else if (section == MARKET){
			Image.draw("market", 0, 0);
			Image.draw("fruitstand", 165, 170);
			Image.draw("yogurtstand", 520, 170);
			Image.draw("cupstand", 895, 170);

			Fonts.left("Fruits", 220, 425, Fonts.WHITE_36);
			Fonts.left("Yogurt", 570, 425, Fonts.WHITE_36);
			Fonts.left("Cups", 965, 425, Fonts.WHITE_36);
		} else if (section == OFFICE){
			Image.draw("office", 0, 0);
		}
	}
}

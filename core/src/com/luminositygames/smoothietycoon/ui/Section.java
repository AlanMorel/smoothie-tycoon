package com.luminositygames.smoothietycoon.ui;

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

	public static final byte STAND = 0;
	public static final byte KITCHEN = 1;
	public static final byte MARKET = 2;
	public static final byte OFFICE = 3;

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
		if (isStand()){
			renderStand();
		} else if (isKitchen()){
			renderKitchen();
		} else if (isMarket()){
			renderMarket();
		} else if (isOffice()){
			renderOffice();
		}
	}

	private void renderStand() {
		Image.draw("salesBackground", 0, 0);
		Image.draw("player", Constants.WIDTH /2 - Image.get("player").getWidth() / 2, 250);
		Image.draw("stand", Constants.WIDTH /2 - Image.get("stand").getWidth() / 2, 175);
		Image.draw("slantedsign", 650, 175);
	}

	private void renderKitchen() {
		Image.draw("kitchen", 0, 0);
		Image.draw("refridgerator", 150, 94);
		Image.draw("juicer", 750, 224);
		Image.draw("blender", 975, 208);
	}

	private void renderMarket() {
		Image.draw("market", 0, 0);
		Image.draw("fruitSeller", 215, 250);
		Image.draw("yogurtSeller", 575, 250);
		Image.draw("cupSeller", 950, 250);
		Image.draw("fruitstand", 165, 170);
		Image.draw("yogurtstand", 520, 170);
		Image.draw("cupstand", 895, 170);
		Fonts.left("Fruits", 220, 425, Fonts.WHITE_36);
		Fonts.left("Yogurt", 570, 425, Fonts.WHITE_36);
		Fonts.left("Cups", 965, 425, Fonts.WHITE_36);
	}

	private void renderOffice() {
		Image.draw("office", 0, 0);
		Image.draw("advertise", 130, 213);
		Image.draw("statistics", 520, 203);
		Image.draw("upgrades", 910, 188);
	}

	public void handleTouch() {
		if (isStand()){
			handleStand();
		} else if (isKitchen()){
			handleKitchen();
		} else if (isMarket()){
			handleMarket();
		} else if (isOffice()){
			handleOffice();
		}
	}

	private void handleStand() {
		if (Image.get("stand").isTouched()){
			Windows.open(Windows.STAND);
		}
	}

	private void handleKitchen() {
		if (Image.get("refridgerator").isTouched()){
			Windows.open(Windows.REFRIDGERATOR);
		} else if (Image.get("juicer").isTouched()){
			Windows.open(Windows.JUICER);
		} else if (Image.get("blender").isTouched()){
			Windows.open(Windows.BLENDER);
		}
	}

	private void handleMarket() {
		if (Image.get("fruitstand").isTouched()){
			Windows.open(Windows.FRUIT);
		} else if (Image.get("yogurtstand").isTouched()){
			Windows.open(Windows.YOGURT);
		} else if (Image.get("cupstand").isTouched()){
			Windows.open(Windows.CUPS);
		}
	}

	private void handleOffice() {
		if (Image.get("advertise").isTouched()){
			Windows.open(Windows.ADVERTISE);
		} else if (Image.get("statistics").isTouched()){
			Windows.open(Windows.STATISTICS);
		} else if (Image.get("upgrades").isTouched()){
			Windows.open(Windows.UPGRADES);
		}
	}
}

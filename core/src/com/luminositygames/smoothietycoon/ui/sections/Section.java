package com.luminositygames.smoothietycoon.ui.sections;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public abstract class Section {

	public static Section STAND = new Stand();
	public static Section KITCHEN = new Kitchen();
	public static Section MARKET = new Market();
	public static Section OFFICE = new Office();

	private static Section section;

	public static void setSection(Section s){
		section = s;
	}

	public static Section getSection(){
		return section;
	}

	public abstract void render(float delta);

	public abstract void handle();

}

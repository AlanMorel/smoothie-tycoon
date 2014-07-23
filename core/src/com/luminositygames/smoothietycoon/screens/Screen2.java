package com.luminositygames.smoothietycoon.screens;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public interface Screen2 {

	public void load();
	
	public void render (float delta);

	public void update(float delta);

	public void keyPress(int keycode);

}

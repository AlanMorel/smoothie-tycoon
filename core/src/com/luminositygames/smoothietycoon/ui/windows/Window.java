package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.Main;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public abstract class Window {

	private static Window window;

	public static Window STAND = new Stand(150, 500, 300);
	public static Window REFRIDGERATOR = new Refridgerator(150, 625, 300);
	public static Window JUICER = new Juicer(150, 625, 300);
	public static Window BLENDER = new Blender(150, 625, 125);
	public static Window FRUIT = new Fruit(150, 600, 300);
	public static Window YOGURT = new Yogurt(150, 600, 300);
	public static Window CUPS = new Cups(150, 600, 300);
	public static Window ADVERTISE = new Advertise(150, 775, 475);
	public static Window STATISTICS = new Statistics(150, 600, 300);
	public static Window UPGRADES = new Upgrades(150, 700, 300);
	public static Window GAME_OVER = new GameOver(150, 700, 300);

	private Rectangle rectangle;

	public Window(int y, int width, int height){
		int x = (Constants.WIDTH - width) / 2;
		this.rectangle = new Rectangle(x, y, width, height);
	}

	public Rectangle getRectangle(){
		return rectangle;
	}

	public abstract void render(Game game);

	public abstract void handle(Game game);

	public static Window getWindow(){
		return window;
	}

	public static void close(){
		window = null;
	}

	public static void open(Window w){
		window = w;
	}

	public static boolean isOpen() {
		return window != null;
	}

	public static boolean isTouched(){
		float x = Main.getX();
		float y = Main.getY();
		return window.getRectangle().contains(x, y) && Gdx.input.justTouched();
	}

}
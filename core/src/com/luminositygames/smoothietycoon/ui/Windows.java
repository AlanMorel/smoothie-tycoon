package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.Shops.Cups;
import com.luminositygames.smoothietycoon.Shops.Fruit;
import com.luminositygames.smoothietycoon.Shops.Ice;
import com.luminositygames.smoothietycoon.Shops.Juice;
import com.luminositygames.smoothietycoon.Shops.Yogurt;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.entities.Advertisements;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.entities.Statistics.StatisticsEntry;
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

public class Windows {

	public static Window NOTHING;
	public static Window STAND = new Window(150, 500, 300);
	public static Window REFRIDGERATOR = new Window(150, 625, 300);//25
	public static Window JUICER = new Window(150, 625, 300);// 25
	public static Window BLENDER = new Window(150, 625, 125);// 25
	public static Window FRUIT = new Window(150, 600, 300);// 25
	public static Window YOGURT = new Window(150, 600, 300);// 25
	public static Window CUPS = new Window(150, 600, 300);// 25
	public static Window ADVERTISE = new Window(150, 775, 475);//25
	public static Window STATISTICS = new Window(150, 600, 300);// 25
	public static Window SAVELOAD = new Window(150, 600, 300);// 25

	private static Window window;
	private static int OFFSET;

	public static void open(Window win){
		window = win;
	}

	public static void close(){
		window = NOTHING;
	}

	public static boolean isOpen() {
		return window != NOTHING;
	}

	public static boolean isTouched(){
		float x = SmoothieTycoon.getX();
		float y = SmoothieTycoon.getY();
		return window.getRectangle().contains(x, y) && Gdx.input.justTouched();
	}

	public static void render(Game game){
		if (window == STAND){
			Recipe recipe = game.getRecipe();
			renderStandWindow(recipe.getPrice(), recipe.getFruit(), recipe.getIce(), recipe.getYogurt(), recipe.getJuice());
		} else if (window == REFRIDGERATOR){
			renderRefridgeratorWindow();
		} else if (window == JUICER){
			renderJuicerWindow();
		} else if (window == BLENDER){
			renderBlenderWindow();
		} else if (window == FRUIT){
			renderFruitWindow();
		} else if (window == YOGURT){
			renderYogurtWindow();
		} else if (window == CUPS){
			renderCupsWindow();
		} else if (window == ADVERTISE){
			renderAdvertiseWindow(game.getAds());
		} else if (window == STATISTICS){
			renderStatisticsWindow(game);
		} else if (window == SAVELOAD){
			renderSaveLoadWindow();
		}
	}

	private static void renderStandWindow(double price, int fruits, int ice, int yogurt, int juice){
		Image.window(Windows.STAND);
		String options [] = {"Price", "Fruits", "Ice Cubes", "Yogurt", "Juice"};
		String values [] = {SmoothieTycoon.format(price), fruits + "", ice + "", yogurt + "", juice + ""};
		for (int i = 0; i < 5; i++){
			Fonts.left("<", 410, i * 50 + 190, Fonts.BLACK_36);
			Fonts.left(">", 850, i * 50 + 190, Fonts.BLACK_36);
			Fonts.left(values[i] + "", Constants.WIDTH / 2, i * 50 + 190, Fonts.BLACK_36);
			Fonts.right(options[i] + ": ", Constants.WIDTH / 2, i * 50 + 190, Fonts.BLACK_36);
		}
	}

	private static void renderRefridgeratorWindow(){
		Image.window(Windows.REFRIDGERATOR);
		for (int i = 0; i < 3; i++){
			Image.draw("ice", 400, i * 85 + 195);
			Fonts.left(Ice.getOptionString(i), 450, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderJuicerWindow(){
		Image.window(Windows.JUICER);
		for (int i = 0; i < 3; i++){
			Image.draw("juice", 385, i * 85 + 195);
			Fonts.left(Juice.getOptionString(i), 435, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderBlenderWindow(){
		Image.window(Windows.BLENDER);
		Image.rectangle(370, 195, 34, 34, 0.9f, Color.PINK);
		Fonts.left("Refill smoothie container", 425, 200, Fonts.BLACK_36);
	}

	private static void renderFruitWindow(){
		Image.window(Windows.FRUIT);
		for (int i = 0; i < 3; i++){
			Image.draw("fruit", 410, i * 85 + 195);
			Fonts.left(Fruit.getOptionString(i), 460, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderYogurtWindow(){
		Image.window(Windows.YOGURT);
		for (int i = 0; i < 3; i++){
			Image.draw("yogurtinverted", 410, i * 85 + 195);
			Fonts.left(Yogurt.getOptionString(i), 460, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderCupsWindow(){
		Image.window(Windows.CUPS);
		for (int i = 0; i < 3; i++){
			Image.draw("cup", 410, i * 85 + 195);
			Fonts.left(Cups.getOptionString(i), 460, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderAdvertiseWindow(Advertisements ads) {
		Image.window(Windows.ADVERTISE);
		for (int i = 0; i < 5; i++){
			Image.draw("ad", 300, i * 85 + 195);
			Fonts.left(Advertisements.getOptionString(ads, i), 390, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderStatisticsWindow(Game game) {
		Image.window(Windows.STATISTICS);
		Image.draw("upArrow", 850, 175);
		Image.draw("downArrow", 850, 350);
		int lowerRange = getLowerRange(game);
		for (int i = lowerRange; i < lowerRange + 3; i++){
			StatisticsEntry entry = game.getStats().getEntry(i);
			if (entry != null){
				int relative = i - lowerRange + 1;
				Image.draw("statisticsicon", 360, relative * 85 + 110);
				Fonts.left(entry.toString(), 450, relative * 85 + 115, Fonts.BLACK_36);
			}
		}
	}

	private static int getLowerRange(Game game) {
		int base = game.getDay() - 3 + OFFSET;
		if (base < 1){
			base = 1;
		}
		return base;
	}

	private static void renderSaveLoadWindow() {
		Image.window(Windows.SAVELOAD);
	}

	public static void handleTouch(Game game) {
		if (window == STAND){
			handleStandWindow(game);
		} else if (window == REFRIDGERATOR){
			handleRefridgeratorWindow(game);
		} else if (window == JUICER){
			handleJuicerWindow(game);
		} else if (window == BLENDER){
			handleBlenderWindow(game);
		} else if (window == FRUIT){
			handleFruitWindow(game);
		} else if (window == YOGURT){
			handleYogurtWindow(game);
		} else if (window == CUPS){
			handleCupsWindow(game);
		} else if (window == ADVERTISE){
			handleAdvertiseWindow(game);
		} else if (window == STATISTICS){
			handleStatisticsWindow(game);
		} else if (window == SAVELOAD){
			handleSaveLoadWindow(game);
		}
	}

	private static void handleStandWindow(Game game){
		boolean downPrice = Fonts.isTouched("<", 410, 190, Fonts.BLACK_36);
		boolean upPrice = Fonts.isTouched(">", 850, 190, Fonts.BLACK_36);
		boolean downFruits = Fonts.isTouched("<", 410, 240, Fonts.BLACK_36);
		boolean upFruits = Fonts.isTouched(">", 850, 240, Fonts.BLACK_36);
		boolean downIce = Fonts.isTouched("<", 410, 290, Fonts.BLACK_36);
		boolean upIce = Fonts.isTouched(">", 850, 290, Fonts.BLACK_36);
		boolean downYogurt = Fonts.isTouched("<", 410, 340, Fonts.BLACK_36);
		boolean upYogurt = Fonts.isTouched(">", 850, 340, Fonts.BLACK_36);
		boolean downJuice = Fonts.isTouched("<", 410, 390, Fonts.BLACK_36);
		boolean upJuice = Fonts.isTouched(">", 850, 390, Fonts.BLACK_36);

		Recipe recipe = game.getRecipe();
		double price = recipe.getPrice();
		int fruits = recipe.getFruit();
		int ice = recipe.getIce();
		int yogurt = recipe.getYogurt();
		int juice = recipe.getJuice();
		double priceChange = 0.05;

		if(downPrice){
			recipe.setPrice(price - priceChange);
		} else if (upPrice){
			recipe.setPrice(price + priceChange);
		} else if (downFruits){
			//recipe.setFruit(fruits - 1);
		} else if (upFruits){
			//recipe.setFruit(fruits + 1);
		} else if (downIce){
			//recipe.setIce(ice - 1);
		} else if (upIce){
			//recipe.setIce(ice + 1);
		} else if (downYogurt){
			//recipe.setYogurt(yogurt - 1);
		} else if (upYogurt){
			//recipe.setYogurt(yogurt + 1);
		} else if (downJuice){
			//recipe.setJuice(juice - 1);
		} else if (upJuice){
			//recipe.setJuice(juice + 1);
		}
	}

	private static void handleRefridgeratorWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Ice.getOptionString(i), 450, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyIce(Ice.getAmount(i), Ice.getPrice(i));
				close();
			}
		}
	}

	private static void handleJuicerWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Juice.getOptionString(i), 435, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().makeJuice(Juice.getAmount(i), Juice.getPrice(i));
				close();
			}
		}
	}

	private static void handleBlenderWindow(Game game){
		if (Fonts.isTouched("Refill smoothie container", 425, 175, Fonts.BLACK_36)){
			game.getContainer().refill(game);
			close();
		}
	}

	private static void handleFruitWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Fruit.getOptionString(i), 465, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyFruits(Fruit.getAmount(i), Fruit.getPrice(i));
				close();
			}
		}
	}

	private static void handleYogurtWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Yogurt.getOptionString(i), 465, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyYogurt(Yogurt.getAmount(i), Yogurt.getPrice(i));
				close();
			}
		}
	}

	private static void handleCupsWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Cups.getOptionString(i), 465, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyCups(Cups.getAmount(i), Cups.getPrice(i));
				close();
			}
		}
	}

	private static void handleAdvertiseWindow(Game game) {
		for (int i = 0; i < 5; i++){
			if (Fonts.isTouched(Advertisements.getOptionString(game.getAds(), i), 390, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyAd(game.getAds(), i);
				game.setNewMaxCustomers();
				close();
			}
		}
	}

	private static void handleStatisticsWindow(Game game) {
		if (Image.get("upArrow").isTouched()){
			if (OFFSET > - game.getDay() + 4){
				OFFSET--;
			}
		}
		if (Image.get("downArrow").isTouched()){
			if (OFFSET < 0){
				OFFSET++;
			}
		}
	}

	private static void handleSaveLoadWindow(Game game) {

	}

	public static class Window {

		private Rectangle rectangle;

		public Window(int y, int width, int height){
			int x = (Constants.WIDTH - width) /2;
			this.rectangle = new Rectangle(x, y, width, height);
		}

		public Rectangle getRectangle(){
			return rectangle;
		}
	}
}

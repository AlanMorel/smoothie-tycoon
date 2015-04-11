package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.entities.Advertisements;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.entities.Statistics.StatisticsEntry;
import com.luminositygames.smoothietycoon.entities.Upgrades;
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

	public static Window STAND = new Window(150, 500, 300);
	public static Window REFRIDGERATOR = new Window(150, 625, 300);
	public static Window JUICER = new Window(150, 625, 300);
	public static Window BLENDER = new Window(150, 625, 125);
	public static Window FRUIT = new Window(150, 600, 300);
	public static Window YOGURT = new Window(150, 600, 300);
	public static Window CUPS = new Window(150, 600, 300);
	public static Window ADVERTISE = new Window(150, 775, 475);
	public static Window STATISTICS = new Window(150, 600, 300);
	public static Window UPGRADES = new Window(150, 600, 300);
	public static Window GAME_OVER = new Window(150, 700, 300);

	private static Window window;
	private static int OFFSET;

	public static void open(Window win){
		window = win;
	}

	public static void close(){
		window = null;
	}

	public static boolean isOpen() {
		return window != null;
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
			renderAdvertiseWindow();
		} else if (window == STATISTICS){
			renderStatisticsWindow(game);
		} else if (window == UPGRADES){
			renderUpgradesWindow();
		} else if (window == GAME_OVER){
			renderGameOverWindow(game.getPlayer());
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
			Fonts.left(Shop.ICE.getString(i), 450, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderJuicerWindow(){
		Image.window(Windows.JUICER);
		for (int i = 0; i < 3; i++){
			Image.draw("juice", 385, i * 85 + 195);
			Fonts.left(Shop.JUICE.getString(i), 435, i * 85 + 200, Fonts.BLACK_36);
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
			Fonts.left(Shop.FRUIT.getString(i), 460, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderYogurtWindow(){
		Image.window(Windows.YOGURT);
		for (int i = 0; i < 3; i++){
			Image.draw("yogurtinverted", 410, i * 85 + 195);
			Fonts.left(Shop.YOGURT.getString(i), 460, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderCupsWindow(){
		Image.window(Windows.CUPS);
		for (int i = 0; i < 3; i++){
			Image.draw("cup", 410, i * 85 + 195);
			Fonts.left(Shop.CUPS.getString(i), 460, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderAdvertiseWindow() {
		Image.window(Windows.ADVERTISE);
		for (int i = 0; i < 5; i++){
			Image.draw("ad", 300, i * 85 + 195);
			Fonts.left(Advertisements.getString(i), 390, i * 85 + 200, Fonts.BLACK_36);
		}
	}

	private static void renderStatisticsWindow(Game game) {
		Image.window(Windows.STATISTICS);
		Image.draw("upArrow", 850, 175);
		Image.draw("downArrow", 850, 350);
		int lowerRange = getLowerRange(game);
		for (int i = lowerRange; i < lowerRange + 3; i++){
			StatisticsEntry entry = game.getStats().getEntryByDay(i);
			if (entry != null){
				int relative = i - lowerRange + 1;
				Image.draw("statisticsicon", 360, relative * 85 + 110);
				Fonts.left(entry.toString(), 450, relative * 85 + 115, Fonts.BLACK_36);
			}
		}
	}

	private static int getLowerRange(Game game) {
		int base = game.getDay() - 3 + OFFSET;
		return base < 1 ? 1 : base;
	}

	private static void renderUpgradesWindow() {
		Image.window(Windows.UPGRADES);
		for (int i = 0; i < 1; i++){
			Image.draw("upgradesIcon", 410, i * 85 + 195);
		}
		String refillExtra = Upgrades.HAS_DOUBLE_CONTAINER ? "(Purchased!)" : "($" + Upgrades.DOUBLE_CONTAINER_PRICE + ")";
		Fonts.left("Double Container " + refillExtra, 460, 200, Fonts.BLACK_36);
	}

	private static void renderGameOverWindow(Player player) {
		Image.window(Windows.GAME_OVER);
		Fonts.center("You have made mom proud!", 200, Fonts.WHITE_36);
		Fonts.center("You finished with " + SmoothieTycoon.format(player.getMoney()) + "!", 285, Fonts.WHITE_48);
		Fonts.center("Close to keep playing!", 400, Fonts.WHITE_36);
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
		} else if (window == UPGRADES){
			handleUpgradesWindow(game);
		} else if (window == GAME_OVER){
			handleGameOverWindow(game);
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
			recipe.setFruit(fruits - 1);
		} else if (upFruits){
			recipe.setFruit(fruits + 1);
		} else if (downIce){
			recipe.setIce(ice - 1);
		} else if (upIce){
			recipe.setIce(ice + 1);
		} else if (downYogurt){
			recipe.setYogurt(yogurt - 1);
		} else if (upYogurt){
			recipe.setYogurt(yogurt + 1);
		} else if (downJuice){
			recipe.setJuice(juice - 1);
		} else if (upJuice){
			recipe.setJuice(juice + 1);
		}
	}

	private static void handleRefridgeratorWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Shop.ICE.getString(i), 450, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyIce(Shop.ICE.getAmount(i), Shop.ICE.getPrice(i));
				close();
			}
		}
	}

	private static void handleJuicerWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Shop.JUICE.getString(i), 435, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().makeJuice(Shop.JUICE.getAmount(i), Shop.JUICE.getPrice(i));
				close();
			}
		}
	}

	private static void handleBlenderWindow(Game game){
		if (Fonts.isTouched("Refill smoothie container", 425, 200, Fonts.BLACK_36)){
			game.getContainer().refill(game);
			close();
		}
	}

	private static void handleFruitWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Shop.FRUIT.getString(i), 465, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyFruits(Shop.FRUIT.getAmount(i), Shop.FRUIT.getPrice(i));
				close();
			}
		}
	}

	private static void handleYogurtWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Shop.YOGURT.getString(i), 465, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyYogurt(Shop.YOGURT.getAmount(i), Shop.YOGURT.getPrice(i));
				close();
			}
		}
	}

	private static void handleCupsWindow(Game game){
		for (int i = 0; i < 3; i++){
			if (Fonts.isTouched(Shop.CUPS.getString(i), 465, i * 85 + 200, Fonts.BLACK_36)){
				game.getPlayer().buyCups(Shop.CUPS.getAmount(i), Shop.CUPS.getPrice(i));
				close();
			}
		}
	}

	private static void handleAdvertiseWindow(Game game) {
		for (int i = 0; i < 5; i++){
			if (Fonts.isTouched(Advertisements.getString(i), 390, i * 85 + 200, Fonts.BLACK_36)){
				Advertisements.getById(i).buy(game.getPlayer());
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
		} else if (Image.get("downArrow").isTouched()){
			if (OFFSET < 0){
				OFFSET++;
			}
		}
	}

	private static void handleUpgradesWindow(Game game) {
		String refillExtra = Upgrades.HAS_DOUBLE_CONTAINER ? "(Purchased!)" : "($" + Upgrades.DOUBLE_CONTAINER_PRICE + ")";
		if (Fonts.isTouched("Double container " + refillExtra, 460, 200, Fonts.BLACK_36)){
			if (!Upgrades.HAS_DOUBLE_CONTAINER && game.getPlayer().canPay(Upgrades.DOUBLE_CONTAINER_PRICE)){
				game.getPlayer().payMoney(Upgrades.DOUBLE_CONTAINER_PRICE);
				Notifications.show("You've purchased a double container!", Notifications.ACHIEVEMENT);
				Upgrades.HAS_DOUBLE_CONTAINER = true;
				close();
			}
		}
	}

	private static void handleGameOverWindow(Game game) {
		//Nothing to handle
	}

	public static class Window {

		private Rectangle rectangle;

		private Window(int y, int width, int height){
			int x = (Constants.WIDTH - width) / 2;
			this.rectangle = new Rectangle(x, y, width, height);
		}

		public Rectangle getRectangle(){
			return rectangle;
		}
	}
}

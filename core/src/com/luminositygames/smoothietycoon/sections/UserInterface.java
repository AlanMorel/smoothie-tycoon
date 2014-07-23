package com.luminositygames.smoothietycoon.sections;

import java.text.NumberFormat;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.util.Countdown;
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

public class UserInterface {

	public static int STAND = 0;
	public static int REFRIDGERATOR = 1;
	public static int JUICER = 2;
	public static int BLENDER = 3;
	public static int FRUIT = 4;
	public static int YOGURT = 5;
	public static int CUPS = 6;
	public static int ADVERTISE = 7;
	public static int STATISTICS = 8;
	public static int SAVELOAD = 9;

	public void render(Game game){
		Image.rectangle(0, 0, Constants.WIDTH, 90, 0.1f, Color.BLACK);
		renderMoney(game.getPlayer().getMoney());
		renderDay(game);
		Player player = game.getPlayer();
		renderIngredients(player.getFruits(), player.getIce(), player.getYogurt(), player.getJuice(), player.getCups());
		renderThermometer(game.getTemperature());
		Image.draw("leftArrow", 50, 650);
		Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		renderContainer(game.getContainer().getServings());
	}

	public void renderMoney(double amount){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		Fonts.left(formatter.format(amount), 25, 20, Fonts.BLACK_48);
	}

	public void renderDay(Game game){
		Countdown intermission = game.getIntermission();
		if (!intermission.hasStarted()){
			renderDay(game.getDay());
		} else {
			Fonts.left("Night " + intermission.getPercentage() + "%", 275, 25, Fonts.BLACK_36);
		}
	}

	public void renderDay(int day) {
		Fonts.left("Day " + day, 300, 25, Fonts.BLACK_36);
	}

	public void renderIngredients(int fruits, int ice, int yogurt, int juice, int cups){
		Image.draw("fruit", 550, 16);
		Image.draw("ice", 700, 20);
		Image.draw("yogurt", 850, 20);
		Image.draw("juice", 1000, 20);
		Image.draw("cup", 1150, 20);

		Fonts.left(fruits + "", 600, 25, Fonts.BLACK_36);
		Fonts.left(ice + "", 750, 25, Fonts.BLACK_36);
		Fonts.left(yogurt + "", 900, 25, Fonts.BLACK_36);
		Fonts.left(juice + "", 1050, 25, Fonts.BLACK_36);
		Fonts.left(cups + "", 1200, 25, Fonts.BLACK_36);
	}

	public void renderThermometer(int temperature){
		Image.draw("thermometer", Constants.WIDTH - 60, 200);
		Fonts.center(temperature + "°", 1240, 370, Fonts.BLACK_36);
	}

	public void renderContainer(int servings){
		
		int percentage = servings * 5;
		int realPercent = 100 - percentage;

		int height = realPercent * 2 + 200;
		int length = percentage * 2;

		Image.rectangle(28, 150, 24, 200, 0.7f, Color.BLACK);
		Image.rectangle(28, height - 50, 24, length, 1.0f, Color.PINK);
		Fonts.center(servings + "", 40, 370, Fonts.BLACK_36);
	}
}

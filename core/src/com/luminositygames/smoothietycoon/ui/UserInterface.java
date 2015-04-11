package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.entities.Upgrades;
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

	public void render(Game game){
		Image.rectangle(0, 0, Constants.WIDTH, 90, 0.1f, Color.BLACK);
		renderMoney(game.getPlayer().getMoney());
		renderDay(game);
		renderIngredients(game.getPlayer());
		renderThermometer(game.getTemperature());
		renderArrows();
		renderContainer(game.getContainer().getServings());
		Windows.render(game);
	}

	public void renderMoney(double amount){
		Fonts.left(SmoothieTycoon.format(amount), 25, 20, Fonts.BLACK_48);
	}

	private void renderDay(Game game){
		Countdown nightTime = game.getNight();
		if (!nightTime.hasStarted()){
			renderDay(game.getDay());
		} else {
			Fonts.left("Night " + nightTime.getPercentage() + "%", 275, 25, Fonts.BLACK_36);
		}
	}

	public void renderDay(int day) {
		Fonts.left("Day " + day, 300, 25, Fonts.BLACK_36);
	}

	private void renderIngredients(Player player){
		renderIngredients(player.getFruits(), player.getIce(), player.getYogurt(), player.getJuice(), player.getCups());
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
		int y = 310 - temperature;
		int height = 15 + temperature;
		Image.rectangle(1230, y, 15, height, 1.0f, Color.RED);
		Image.draw("thermometer", Constants.WIDTH - 60, 200);
		Fonts.center(temperature + "°", 1240, 370, Fonts.BLACK_36);
	}

	private void renderArrows() {
		Image.draw("leftArrow", 50, 650);
		Image.draw("rightArrow", Constants.WIDTH - 100, 650);
	}

	public void renderContainer(int servings){
		int percentage = Upgrades.HAS_DOUBLE_CONTAINER ? servings * 5 : servings * 10;
		int realPercent = 100 - percentage;
		int height = realPercent * 2 + 200;
		int length = percentage * 2;

		Image.rectangle(28, 150, 24, 200, 0.7f, Color.BLACK);
		Image.rectangle(28, height - 50, 24, length, 1.0f, Color.PINK);
		Fonts.center(servings + "", 40, 370, Fonts.BLACK_36);
	}
}

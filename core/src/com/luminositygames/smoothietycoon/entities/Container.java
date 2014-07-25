package com.luminositygames.smoothietycoon.entities;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
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

public class Container {

	private int servings;
	private int fruit;
	private int ice;
	private int yogurt;
	private int juice;

	public Container() {
		this.servings = 5;
	}

	public int getFruit() {
		return fruit;
	}

	public int getIce() {
		return ice;
	}

	public int getYogurt() {
		return yogurt;
	}

	public int getJuice() {
		return juice;
	}

	public int getServings() {
		return servings;
	}

	public void serve(){
		servings --;
	}

	public void refill(Game game) {

		Recipe recipe = game.getRecipe();
		Player player = game.getPlayer();

		boolean enoughFruits = player.getFruits() >= recipe.getFruit();
		boolean enoughIce = player.getIce() >= recipe.getIce();
		boolean enoughYogurt = player.getYogurt() >= recipe.getYogurt();
		boolean enoughJuice = player.getJuice() >= recipe.getJuice();

		if (enoughFruits && enoughIce && enoughYogurt && enoughJuice){
			servings = 10;
			this.fruit = game.getRecipe().getFruit();
			this.ice = game.getRecipe().getIce();
			this.yogurt = game.getRecipe().getYogurt();
			this.juice = game.getRecipe().getJuice();
			game.getPlayer().makeContainer(game.getRecipe());
		}
	}

	public void render() {

		int percentage = servings * 10;
		int realPercent = 100 - percentage;

		int height = realPercent * 2 + 200;
		int length = percentage * 2;

		Image.rectangle(28, 150, 24, 200, 0.7f, Color.BLACK);
		Image.rectangle(28, height - 50, 24, length, 1.0f, Color.PINK);
		Fonts.center(servings + "", 40, 370, Fonts.BLACK_36);
	}
}

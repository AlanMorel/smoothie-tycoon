package com.luminositygames.smoothietycoon.entities;

import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.ui.Achievements;

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
		this.fruit = 10;
		this.ice = 10;
		this.yogurt = 10;
		this.juice = 10;
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
			this.servings = 10;
			this.fruit = game.getRecipe().getFruit();
			this.ice = game.getRecipe().getIce();
			this.yogurt = game.getRecipe().getYogurt();
			this.juice = game.getRecipe().getJuice();
			game.getPlayer().makeContainer(game.getRecipe());
			Achievements.progress(Achievements.REFILLS, 1);
		}
	}
}

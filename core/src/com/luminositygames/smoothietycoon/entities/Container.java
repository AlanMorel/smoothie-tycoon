package com.luminositygames.smoothietycoon.entities;

import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.ui.Achievements;
import com.luminositygames.smoothietycoon.ui.Notifications;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
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
		servings = 10;
		fruit = 10;
		ice = 10;
		yogurt = 10;
		juice = 10;
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
		servings -= 1;
	}

	public void refill(Game game) {
		if(canRefill(game)){
			servings = Upgrade.DOUBLE_CONTAINER.hasPurchased() ? 20 : 10;
			fruit = game.getRecipe().getFruit();
			ice = game.getRecipe().getIce();
			yogurt = game.getRecipe().getYogurt();
			juice = game.getRecipe().getJuice();
			game.getPlayer().refillContainer(game.getRecipe());
			Achievements.progress(Achievements.REFILLS, 1);
		} else {
			Notifications.show("You are missing some ingredients!", Notifications.TIP);
		}
	}

	private boolean canRefill(Game game){
		Recipe recipe = game.getRecipe();
		Player player = game.getPlayer();

		boolean enoughFruits = player.getFruits() >= recipe.getFruit();
		boolean enoughIce = player.getIce() >= recipe.getIce();
		boolean enoughYogurt = player.getYogurt() >= recipe.getYogurt();
		boolean enoughJuice = player.getJuice() >= recipe.getJuice();

		return enoughFruits && enoughIce && enoughYogurt && enoughJuice;
	}
}

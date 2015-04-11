package com.luminositygames.smoothietycoon.ui.windows;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.Main;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.Image;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Stand extends Window {

	public Stand(int y, int width, int height) {
		super(y, width, height);
	}

	@Override
	public void render(Game game) {
		Image.window(this);
		Recipe recipe = game.getRecipe();
		double price = recipe.getPrice();
		int fruits = recipe.getFruit();
		int ice = recipe.getIce();
		int yogurt = recipe.getYogurt();
		int juice = recipe.getJuice();
		String options [] = {"Price", "Fruits", "Ice Cubes", "Yogurt", "Juice"};
		String values [] = {Main.format(price), fruits + "", ice + "", yogurt + "", juice + ""};
		for (int i = 0; i < 5; i++){
			Fonts.left("<", 410, i * 50 + 190, Color.BLACK, 36);
			Fonts.left(">", 850, i * 50 + 190, Color.BLACK, 36);
			Fonts.left(values[i] + "", Constants.WIDTH / 2, i * 50 + 190, Color.BLACK, 36);
			Fonts.right(options[i] + ": ", Constants.WIDTH / 2, i * 50 + 190, Color.BLACK, 36);
		}
	}

	@Override
	public void handle(Game game) {
		boolean downPrice = Fonts.isTouched("<", 410, 190, Color.BLACK, 36);
		boolean upPrice = Fonts.isTouched(">", 850, 190, Color.BLACK, 36);
		boolean downFruits = Fonts.isTouched("<", 410, 240, Color.BLACK, 36);
		boolean upFruits = Fonts.isTouched(">", 850, 240, Color.BLACK, 36);
		boolean downIce = Fonts.isTouched("<", 410, 290, Color.BLACK, 36);
		boolean upIce = Fonts.isTouched(">", 850, 290, Color.BLACK, 36);
		boolean downYogurt = Fonts.isTouched("<", 410, 340, Color.BLACK, 36);
		boolean upYogurt = Fonts.isTouched(">", 850, 340, Color.BLACK, 36);
		boolean downJuice = Fonts.isTouched("<", 410, 390, Color.BLACK, 36);
		boolean upJuice = Fonts.isTouched(">", 850, 390, Color.BLACK, 36);

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
}

package com.luminositygames.smoothietycoon.ui;

import java.text.NumberFormat;

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
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.sections.Kitchen;
import com.luminositygames.smoothietycoon.sections.Market;
import com.luminositygames.smoothietycoon.sections.Stand;
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

	public enum Window {NOTHING, STAND, REFRIDGERATOR, JUICER, BLENDER, FRUIT, YOGURT, CUPS, ADVERTISE, STATISTICS, SAVELOAD};

	private Game game;
	private Window window;

	public Windows(Game game){
		this.game = game;
	}

	public void open(Window id){
		window = id;
	}

	public void close(){
		window = Window.NOTHING;
	}

	public boolean isOpen() {
		return window != Window.NOTHING;
	}

	public boolean isTouched(Rectangle rect){
		float x = SmoothieTycoon.getX();
		float y = SmoothieTycoon.getY();
		if (rect.contains(x, y) && Gdx.input.justTouched()){
			return true;
		}
		return false;
	}

	public void render(){
		if (window == Window.STAND){

			Image.rectangle(Stand.STAND_RECTANGLE, 0.85f, Color.WHITE);

			String options [] = {"Price", "Fruits", "Ice Cubes", "Yogurt", "Juice"};

			String values [] = {NumberFormat.getCurrencyInstance().format(game.getRecipe().getPrice()),
					game.getRecipe().getFruit() + "",
					game.getRecipe().getIce() + "",
					game.getRecipe().getYogurt() + "",
					game.getRecipe().getJuice() + ""};

			for (int i = 0; i < 5; i++){
				Fonts.left("<", 410, i * 50 + 190, Fonts.BLACK_36);
				Fonts.left(">", 850, i * 50 + 190, Fonts.BLACK_36);
				Fonts.left(values[i] + "", Constants.WIDTH / 2, i * 50 + 190, Fonts.BLACK_36);
				Fonts.right(options[i] + ": ", Constants.WIDTH / 2, i * 50 + 190, Fonts.BLACK_36);
			}
		} else if (window == Window.REFRIDGERATOR){

			Image.rectangle(Kitchen.REFRIDGERATOR_RECTANGLE, 0.85f, Color.WHITE);

			for (int i = 0; i < 3; i++){
				Image.draw("ice", 400, i * 85 + 170);
				Fonts.left(Ice.getOptionString(i), 450, i * 85 + 175, Fonts.BLACK_36);
			}
		} else if (window == Window.JUICER){

			Image.rectangle(Kitchen.JUICER_RECTANGLE, 0.85f, Color.WHITE);

			for (int i = 0; i < 3; i++){
				Image.draw("juice", 385, i * 85 + 170);
				Fonts.left(Juice.getOptionString(i), 435, i * 85 + 175, Fonts.BLACK_36);
			}
		} else if (window == Window.BLENDER){

			Image.rectangle(Kitchen.BLENDER_RECTANGLE, 0.85f, Color.WHITE);
			Image.rectangle(370, 170, 34, 34, 0.9f, Color.PINK);

			Fonts.left("Refill smoothie container", 425, 175, Fonts.BLACK_36);

		} else if (window == Window.FRUIT){

			Image.rectangle(Kitchen.REFRIDGERATOR_RECTANGLE, 0.85f, Color.WHITE);

			for (int i = 0; i < 3; i++){
				Image.draw("fruit", 410, i * 85 + 170);
				Fonts.left(Fruit.getOptionString(i), 460, i * 85 + 175, Fonts.BLACK_36);
			}
		} else if (window == Window.YOGURT){

			Image.rectangle(Kitchen.REFRIDGERATOR_RECTANGLE, 0.85f, Color.WHITE);

			for (int i = 0; i < 3; i++){
				Image.draw("yogurtinverted", 410, i * 85 + 170);
				Fonts.left(Yogurt.getOptionString(i), 460, i * 85 + 175, Fonts.BLACK_36);
			}
		} else if (window == Window.CUPS){

			Image.rectangle(Kitchen.REFRIDGERATOR_RECTANGLE, 0.85f, Color.WHITE);

			for (int i = 0; i < 3; i++){
				Image.draw("cup", 410, i * 85 + 170);
				Fonts.left(Cups.getOptionString(i), 460, i * 85 + 175, Fonts.BLACK_36);
			}
		}
	}

	public boolean isTouched() {
		if (window == Window.STAND){
			return isTouched(Stand.STAND_RECTANGLE);
		} else if (window == Window.REFRIDGERATOR){
			return isTouched(Kitchen.REFRIDGERATOR_RECTANGLE);
		} else if (window == Window.JUICER){
			return isTouched(Kitchen.JUICER_RECTANGLE);
		} else if (window == Window.BLENDER){
			return isTouched(Kitchen.BLENDER_RECTANGLE);
		} else if (window == Window.FRUIT){
			return isTouched(Market.FRUIT_RECTANGLE);
		} else if (window == Window.YOGURT){
			return isTouched(Market.YOGURT_RECTANGLE);
		} else if (window == Window.CUPS){
			return isTouched(Market.CUPS_RECTANGLE);
		}
		return false;
	}

	public void handleTouch() {
		if (window == Window.STAND){

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
		} else if (window == Window.REFRIDGERATOR){
			for (int i = 0; i < 3; i++){
				if (Fonts.isTouched(Ice.getOptionString(i), 450, i * 85 + 175, Fonts.BLACK_36)){
					game.getPlayer().buyIce(Ice.getAmount(i), Ice.getPrice(i));
					close();
				}
			}
		} else if (window == Window.JUICER){
			for (int i = 0; i < 3; i++){
				if (Fonts.isTouched(Juice.getOptionString(i), 435, i * 85 + 175, Fonts.BLACK_36)){
					game.getPlayer().makeJuice(Juice.getAmount(i), Juice.getPrice(i));
					close();
				}
			}
		} else if (window == Window.BLENDER){
			if (Fonts.isTouched("Refill smoothie container", 425, 175, Fonts.BLACK_36)){
				game.getContainer().refill(game);
				close();
			}
		} else if (window == Window.FRUIT){
			for (int i = 0; i < 3; i++){
				if (Fonts.isTouched(Fruit.getOptionString(i), 465, i * 85 + 175, Fonts.BLACK_36)){
					game.getPlayer().buyFruits(Fruit.getAmount(i), Fruit.getPrice(i));
					close();
				}
			}
		} else if (window == Window.YOGURT){
			for (int i = 0; i < 3; i++){
				if (Fonts.isTouched(Yogurt.getOptionString(i), 465, i * 85 + 175, Fonts.BLACK_36)){
					game.getPlayer().buyYogurt(Yogurt.getAmount(i), Yogurt.getPrice(i));
					close();
				}
			}
		} else if (window == Window.CUPS){
			for (int i = 0; i < 3; i++){
				if (Fonts.isTouched(Cups.getOptionString(i), 465, i * 85 + 175, Fonts.BLACK_36)){
					game.getPlayer().buyCups(Cups.getAmount(i), Cups.getPrice(i));
					close();
				}
			}
		}
	}
}

package com.luminositygames.smoothietycoon.util;

import java.text.NumberFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.sections.Kitchen;
import com.luminositygames.smoothietycoon.sections.Market;
import com.luminositygames.smoothietycoon.sections.Stand;

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
			Image.rectangle(Stand.STAND_RECT, 0.85f, Color.WHITE);

			Fonts.left("<", 410, 190, Fonts.BLACK_36);
			Fonts.left("<", 410, 240, Fonts.BLACK_36);
			Fonts.left("<", 410, 290, Fonts.BLACK_36);
			Fonts.left("<", 410, 340, Fonts.BLACK_36);
			Fonts.left("<", 410, 390, Fonts.BLACK_36);

			Fonts.left(">", 850, 190, Fonts.BLACK_36);
			Fonts.left(">", 850, 240, Fonts.BLACK_36);
			Fonts.left(">", 850, 290, Fonts.BLACK_36);
			Fonts.left(">", 850, 340, Fonts.BLACK_36);
			Fonts.left(">", 850, 390, Fonts.BLACK_36);

			Recipe recipe = game.getRecipe();

			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String price = formatter.format(recipe.getPrice());

			Fonts.right("Price: ", Constants.WIDTH / 2, 190, Fonts.BLACK_36);
			Fonts.left(price, Constants.WIDTH / 2, 190, Fonts.BLACK_36);

			Fonts.right("Fruits: ", Constants.WIDTH / 2, 240, Fonts.BLACK_36);
			Fonts.left(recipe.getFruit() + "", Constants.WIDTH / 2, 240, Fonts.BLACK_36);

			Fonts.right("Ice Cubes: ", Constants.WIDTH / 2, 290, Fonts.BLACK_36);
			Fonts.left(recipe.getIce() + "", Constants.WIDTH / 2, 290, Fonts.BLACK_36);

			Fonts.right("Yogurt: ", Constants.WIDTH / 2, 340, Fonts.BLACK_36);
			Fonts.left(recipe.getYogurt() + "", Constants.WIDTH / 2, 340, Fonts.BLACK_36);

			Fonts.right("Juice: ", Constants.WIDTH / 2, 390, Fonts.BLACK_36);
			Fonts.left(recipe.getJuice() + "", Constants.WIDTH / 2, 390, Fonts.BLACK_36);
		} else if (window == Window.REFRIDGERATOR){
			Image.rectangle(Kitchen.REFRIDGERATOR_RECT, 0.85f, Color.WHITE);
			Image.draw("ice", 400, 170);
			Image.draw("ice", 400, 255);
			Image.draw("ice", 400, 340);
			Fonts.left("Buy 10 ice cubes for $1.00", 450, 175, Fonts.BLACK_36);
			Fonts.left("Buy 25 ice cubes for $2.00", 450, 260, Fonts.BLACK_36);
			Fonts.left("Buy 100 ice cubes for $5.00", 450, 345, Fonts.BLACK_36);
		} else if (window == Window.JUICER){
			Image.rectangle(Kitchen.JUICER_RECT, 0.85f, Color.WHITE);
			Image.draw("juice", 385, 170);
			Image.draw("juice", 385, 255);
			Image.draw("juice", 385, 340);
			Fonts.left("Make 10 juice using 15 fruits", 435, 175, Fonts.BLACK_36);
			Fonts.left("Make 25 juice using 30 fruits", 435, 260, Fonts.BLACK_36);
			Fonts.left("Make 50 juice using 50 fruits", 435, 345, Fonts.BLACK_36);
		} else if (window == Window.BLENDER){
			Image.rectangle(Kitchen.BLENDER_RECT, 0.85f, Color.WHITE);
			Image.rectangle(370, 170, 34, 34, 0.9f, Color.PINK);
			Image.rectangle(370, 255, 34, 34, 0.9f, Color.GRAY);
			Fonts.left("Refill smoothie container", 425, 175, Fonts.BLACK_36);
			Fonts.left("Empty out smoothie container", 425, 260, Fonts.BLACK_36);
		} else if (window == Window.FRUIT){
			Image.rectangle(Kitchen.REFRIDGERATOR_RECT, 0.85f, Color.WHITE);
			Image.draw("fruit", 410, 170);
			Image.draw("fruit", 410, 255);
			Image.draw("fruit", 410, 340);
			Fonts.left("Buy 25 fruits for $1.00", 465, 175, Fonts.BLACK_36);
			Fonts.left("Buy 50 fruits for $2.00", 465, 260, Fonts.BLACK_36);
			Fonts.left("Buy 100 fruits for $5.00", 465, 345, Fonts.BLACK_36);
		} else if (window == Window.YOGURT){
			Image.rectangle(Kitchen.REFRIDGERATOR_RECT, 0.85f, Color.WHITE);
			Image.draw("yogurtinverted", 410, 170);
			Image.draw("yogurtinverted", 410, 255);
			Image.draw("yogurtinverted", 410, 340);
			Fonts.left("Buy 10 yogurt for $1.00", 465, 175, Fonts.BLACK_36);
			Fonts.left("Buy 25 yogurt for $2.00", 465, 260, Fonts.BLACK_36);
			Fonts.left("Buy 100 yogurt for $5.00", 465, 345, Fonts.BLACK_36);
		} else if (window == Window.CUPS){
			Image.rectangle(Kitchen.REFRIDGERATOR_RECT, 0.85f, Color.WHITE);
			Image.draw("cup", 410, 170);
			Image.draw("cup", 410, 255);
			Image.draw("cup", 410, 340);
			Fonts.left("Buy 25 cups for $1.00", 465, 175, Fonts.BLACK_36);
			Fonts.left("Buy 50 cups for $1.50", 465, 260, Fonts.BLACK_36);
			Fonts.left("Buy 100 cups for $2.00", 465, 345, Fonts.BLACK_36);
		}
	}

	public boolean isTouched() {
		if (window == Window.STAND){
			return isTouched(Stand.STAND_RECT);
		} else if (window == Window.REFRIDGERATOR){
			return isTouched(Kitchen.REFRIDGERATOR_RECT);
		} else if (window == Window.JUICER){
			return isTouched(Kitchen.JUICER_RECT);
		} else if (window == Window.BLENDER){
			return isTouched(Kitchen.BLENDER_RECT);
		} else if (window == Window.FRUIT){
			return isTouched(Market.FRUIT_RECT);
		} else if (window == Window.YOGURT){
			return isTouched(Market.YOGURT_RECT);
		} else if (window == Window.CUPS){
			return isTouched(Market.CUPS_RECT);
		}
		return false;
	}

	public void handleTouch() {

		Player player = game.getPlayer();

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
		} else if (window == Window.REFRIDGERATOR){

			boolean option1 = Fonts.isTouched("Buy 10 ice cubes for $1.00", 450, 175, Fonts.BLACK_36);
			boolean option2 = Fonts.isTouched("Buy 25 ice cubes for $2.00", 450, 260, Fonts.BLACK_36);
			boolean option3 = Fonts.isTouched("Buy 100 ice cubes for $5.00", 450, 345, Fonts.BLACK_36);

			if (option1){
				player.buyIce(10, 1.00);
			} else if (option2){
				player.buyIce(25, 2.00);
			} else if (option3){
				player.buyIce(100, 5.00);
			}

		} else if (window == Window.JUICER){

			boolean option1 = Fonts.isTouched("Make 10 juice using 15 fruits", 435, 175, Fonts.BLACK_36);
			boolean option2 = Fonts.isTouched("Make 25 juice using 30 fruits", 435, 260, Fonts.BLACK_36);
			boolean option3 = Fonts.isTouched("Make 50 juice using 50 fruits", 435, 345, Fonts.BLACK_36);

			if (option1){
				player.makeJuice(10, 15);
			} else if (option2){
				player.makeJuice(25, 30);
			} else if (option3){
				player.makeJuice(50, 50);
			}

		} else if (window == Window.BLENDER){

			boolean option1 = Fonts.isTouched("Refill smoothie container", 425, 175, Fonts.BLACK_36);
			boolean option2 = Fonts.isTouched("Empty out smoothie container", 425, 260, Fonts.BLACK_36);

			if (option1){
				game.getContainer().refill(game);
			} else if (option2){
				game.getContainer().empty();
			}

		} else if (window == Window.FRUIT){

			boolean option1 = Fonts.isTouched("Buy 25 fruits for $1.00", 465, 175, Fonts.BLACK_36);
			boolean option2 = Fonts.isTouched("Buy 50 fruits for $2.00", 465, 260, Fonts.BLACK_36);
			boolean option3 = Fonts.isTouched("Buy 100 fruits for $5.00", 465, 345, Fonts.BLACK_36);

			if (option1){
				player.buyFruits(25, 1.00);
			} else if (option2){
				player.buyFruits(50, 2.00);
			} else if (option3){
				player.buyFruits(100, 5.00);
			}

		} else if (window == Window.YOGURT){

			boolean option1 = Fonts.isTouched("Buy 10 yogurt for $1.00", 465, 175, Fonts.BLACK_36);
			boolean option2 = Fonts.isTouched("Buy 25 yogurt for $2.00", 465, 260, Fonts.BLACK_36);
			boolean option3 = Fonts.isTouched("Buy 100 yogurt for $5.00", 465, 345, Fonts.BLACK_36);

			if (option1){
				player.buyYogurt(10, 1.00);
			} else if (option2){
				player.buyYogurt(25, 2.00);
			} else if (option3){
				player.buyYogurt(100, 5.00);
			}

		} else if (window == Window.CUPS){

			boolean option1 = Fonts.isTouched("Buy 25 cups for $1.00", 465, 175, Fonts.BLACK_36);
			boolean option2 = Fonts.isTouched("Buy 50 cups for $1.50", 465, 260, Fonts.BLACK_36);
			boolean option3 = Fonts.isTouched("Buy 100 cups for $2.00", 465, 345, Fonts.BLACK_36);

			if (option1){
				player.buyCups(25, 1.00);
			} else if (option2){
				player.buyCups(50, 1.50);
			} else if (option3){
				player.buyCups(100, 2.00);
			}
		}
	}
}

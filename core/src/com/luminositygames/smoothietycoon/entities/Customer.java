package com.luminositygames.smoothietycoon.entities;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Main;
import com.luminositygames.smoothietycoon.util.Countdown;
import com.luminositygames.smoothietycoon.util.GameTween;
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

public class Customer {

	private static final byte LEFT = 0;
	private static final byte RIGHT = 1;

	private int x;
	private int speed;
	private int facing;
	private boolean buying;
	private Color color;
	private Color indicator;
	private Hat hat;
	private Countdown purchase;

	public Customer(boolean b) {
		buying = b;
		indicator = buying ? Color.YELLOW : Color.RED;
		facing = Main.random.nextInt(2);
		hat = new Hat(facing);
		purchase = new Countdown(getPurchaseDelay(), false);
		color = Image.getRandomColor();
		if (facing == LEFT){
			x = -102;
			speed = 4;
		} else if (facing == RIGHT){
			x = Constants.WIDTH + 2;
			speed = -4;
		}
	}

	private int getPurchaseDelay(){
		return Main.random.nextInt(1500) + 1000;
	}

	public boolean isWaiting(){
		return purchase.hasStarted() && !purchase.isCompleted();
	}

	public boolean hasPurchased(){
		if (atStand() && purchase.isCompleted()){
			speed = 4 * (facing == LEFT ? 1 : -1);
			indicator = Color.GREEN;
			return true;
		}
		return false;
	}

	public boolean hasExited(){
		return x > Constants.WIDTH + 50 || x < -150;
	}

	private boolean atStand(){
		int center = Constants.WIDTH / 2 - 100 / 2;
		return x == center && buying;
	}

	public void render() {
		if (hasExited()){
			return;
		}
		renderCustomer();
		hat.render(x);

	}

	private void renderCustomer(){
		int tweenValue = (int) hat.getTween().getValue();
		int length = 240 - tweenValue;
		int height = 300 + tweenValue;

		Image.rectangle(x, height, 100, length, 1.0f, color);
		Image.rectangle(x, 540, 100, 10, 1.0f, indicator);
		Image.draw("customerOverlay", x, 300);
	}

	public void update(float delta, boolean canBuy) {
		hat.update(delta);
		x += speed;
		if (atStand() && !purchase.hasStarted() && canBuy){
			purchase.start();
			speed = 0;
		}
	}

	private class Hat {

		private static final byte SWAG = 0;
		private static final byte COWBOY = 1;
		private static final byte WINTER = 2;
		private static final byte POLICE = 3;
		private static final byte PIRATE = 4;
		private static final byte BROWN = 5;

		private GameTween tween;
		private String hat;
		private int x;
		private int y;

		private Hat(int side){
			this.tween = new GameTween(-10, GameTween.HAT);
			int random = Main.random.nextInt(6);
			if (random == SWAG){
				this.hat = "swag";
				this.x = -22;
				this.y = 240;
			} else if (random == COWBOY){
				this.hat = "cow";
				this.x = -22;
				this.y = 260;
			} else if (random == WINTER){
				this.hat = "winter";
				this.x = -4;
				this.y = 238;
			} else if (random == POLICE){
				this.hat = "police";
				this.x = -22;
				this.y = 238;
			} else if (random == PIRATE){
				this.hat = "pirate";
				this.x = -12;
				this.y = 259;
			} else if (random == BROWN){
				this.hat = "brownHat";
				this.x = -20;
				this.y = 240;
			}
			this.hat += side == 0 ? "L" : "R";
		}

		public GameTween getTween(){
			return tween;
		}

		private void render(int customerX){
			Image.draw(hat, customerX + x, y + tween.getValue());
		}

		private void update(float delta){
			tween.update(delta);
		}
	}
}

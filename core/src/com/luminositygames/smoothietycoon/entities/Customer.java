package com.luminositygames.smoothietycoon.entities;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.Countdown;
import com.luminositygames.smoothietycoon.util.GameTween;
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

public class Customer {

	private static final byte LEFT = 0;
	private static final byte RIGHT = 1;

	private int x;
	private int speed;
	private int side;
	private boolean buying;
	private Color color;
	private Hat hat;
	private Countdown purchase;

	public Customer(boolean buying) {
		this.buying = buying;
		this.side = SmoothieTycoon.random.nextInt(2);
		this.hat = new Hat(side);
		this.purchase = new Countdown(getPurchaseDelay(), false);
		this.color = Image.getRandomColor();
		if (side == LEFT){
			this.x = -102;
			this.speed = 4;
		} else if (side == RIGHT){
			this.x = Constants.WIDTH + 2;
			this.speed = -4;
		}
	}

	private int getPurchaseDelay(){
		return SmoothieTycoon.random.nextInt(1500) + 1000;
	}

	public boolean isWaiting(){
		return purchase.hasStarted() && !purchase.isCompleted();
	}

	public boolean isBuying(){
		if (atStand() && purchase.isCompleted()){
			speed = side == LEFT ? 4 : -4;
			return true;
		}
		return false;
	}

	public boolean hasLeftScreen(){
		return x > Constants.WIDTH + 50 || x < -150;
	}

	public boolean atStand(){
		int center = Constants.WIDTH / 2 - 100 / 2;
		return x == center && buying;
	}

	public void render() {
		if (!hasLeftScreen()){
			renderCustomer();
			hat.render(x);
		}
	}

	public void renderCustomer(){
		double tweenValue = hat.getHatTween().getValue();
		int length = 240 - (int) tweenValue;
		int height = 300 + (int) tweenValue;

		Image.rectangle(x, height, 100, length, 1.0f, color);
		Image.rectangle(x, 540, 100, 10, 1.0f, buying ? Color.GREEN : Color.RED);
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


	public class Hat {

		private static final byte SWAG = 0;
		private static final byte COWBOY = 1;
		private static final byte WINTER = 2;
		private static final byte POLICE = 3;
		private static final byte PIRATE = 4;
		private static final byte BROWN = 5;

		private GameTween hatTween;
		private String hat;
		private int x;
		private int y;

		public Hat(int side){
			this.hatTween = new GameTween(-10, GameTween.HAT);
			int random = SmoothieTycoon.random.nextInt(6);
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

		public GameTween getHatTween(){
			return hatTween;
		}

		public void render(int customerX){
			Image.draw(hat, customerX + x, y + hatTween.getValue());
		}

		public void update(float delta){
			hatTween.update(delta);
		}
	}
}

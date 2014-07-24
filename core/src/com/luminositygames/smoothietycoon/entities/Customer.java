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

	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	private Color color;
	private Countdown purchase;
	private GameTween hat;

	private int x;
	private int speed;
	private int side;

	private boolean buying;

	public Customer(boolean buying) {
		this.side = SmoothieTycoon.rand.nextInt(2);
		this.buying = buying;
		this.hat = new GameTween(-2, GameTween.HAT);

		int purchaseDelay = SmoothieTycoon.rand.nextInt(1500) + 1000;
		this.purchase = new Countdown(purchaseDelay, false);

		if (side == LEFT){
			this.x = -102;
		} else if (side == RIGHT){
			this.x = Constants.WIDTH + 2;
		}

		setSpeed();

		float r = SmoothieTycoon.rand.nextFloat();
		float g = SmoothieTycoon.rand.nextFloat();
		float b = SmoothieTycoon.rand.nextFloat();

		color = new Color(r, g, b, b);
	}

	private void setSpeed() {
		if (side == LEFT){
			speed = 4;
		} else if (side == RIGHT){
			speed = -4;
		}
	}

	public boolean isWaiting(){
		if (purchase.hasStarted() && !purchase.isCompleted()){
			return true;
		}
		return false;
	}

	public boolean isBuying(){
		if (atStand() && purchase.isCompleted()){
			setSpeed();
			return true;
		}
		return false;
	}

	public boolean hasLeft(){
		if (x > Constants.WIDTH + 10 || x < -110){
			return true;
		}
		return false;
	}

	public boolean atStand(){
		int center = Constants.WIDTH / 2 - 100 / 2;
		if (x == center && buying){
			return true;
		}
		return false;
	}

	public void render() {
		Image.rectangle(x, 300, 100, 240, 1.0f, color);
		Image.rectangle(x, 540, 100, 10, 1.0f, buying ? Color.GREEN : Color.RED);
		if (side == LEFT){
			Image.draw("hatL", x - 22, 227 + hat.getValue());
		} else if (side == RIGHT){
			Image.draw("hatR", x - 20, 227 + hat.getValue());
		}
	}

	public void update(float delta, boolean canBuy) {
		hat.update(delta);
		x += speed;
		if (atStand() && !purchase.hasStarted() && canBuy){
			purchase.start();
			speed = 0;
		}
	}
}

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

	private Color color;
	private GameTween hat;
	private Countdown purchase;

	private int x;
	private int speed;
	private int side;
	private boolean buying;

	public Customer(boolean buying) {
		this.buying = buying;
		this.side = SmoothieTycoon.random.nextInt(2);
		this.hat = new GameTween(-2, GameTween.HAT);
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

	public boolean hasLeft(){
		return x > Constants.WIDTH + 10 || x < -110;
	}

	public boolean atStand(){
		int center = Constants.WIDTH / 2 - 100 / 2;
		return x == center && buying;
	}

	public void render() {
		Image.rectangle(x, 300, 100, 240, 1.0f, color);
		Image.rectangle(x, 540, 100, 10, 0.5f, buying ? Color.GREEN : Color.RED);
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

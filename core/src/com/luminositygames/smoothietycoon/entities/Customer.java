package com.luminositygames.smoothietycoon.entities;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Game;
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
	
	private static final int CENTER = Constants.WIDTH / 2 - 100 / 2;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	
	private Game game;
	
	private int x;
	private int speed;
	private boolean buying;
	
	private Color color;
	private Color buyColor;
	private int side;
	private Countdown purchase;
	private GameTween hat;
	
	public Customer(Game game) {
		this.game = game;
		this.purchase = new Countdown(getPurchaseDelay(), false);
		this.side = game.getCustomers().getSide();
		this.buying = game.getCustomers().getBuy();
		this.hat = new GameTween(-2, GameTween.HAT);
		setX();
		setSpeed();
		setBuyColor();
		setColor();
	}

	private void setColor() {
		float r = SmoothieTycoon.rand.nextFloat();
		float g = SmoothieTycoon.rand.nextFloat();
		float b = SmoothieTycoon.rand.nextFloat();
		
		Color randomColor = new Color(r, g, b, b);
		color = randomColor;
	}
	
	public int getPurchaseDelay(){
		return SmoothieTycoon.rand.nextInt(1500) + 1000;
	}

	private void setBuyColor() {
		buyColor = new Color(buying ? Color.GREEN : Color.RED);
	}

	private void setX() {
		if (side == LEFT){
			x = -102;
		} else if (side == RIGHT){
			x = Constants.WIDTH + 2;
		}
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
		if (x == CENTER && purchase.isCompleted()){
			setSpeed();
			System.out.println("IS BUYING");
			return true;
		} 
		return false;
	}
	
	public boolean hasLeft(){
		if (x > Constants.WIDTH + 10){
			return true;
		} else if (x < -110){
			return true;
		} else {
			return false;
		}
	}
	
	public void render() {
		Image.rectangle(x, 300, 100, 250, 1.0f, buyColor);
		Image.rectangle(x, 300, 100, 240, 1.0f, color);
		if (side == LEFT){
			Image.draw("hatL", x - 22, 227 + hat.getValue());
		} else if (side == RIGHT){
			Image.draw("hatR", x - 20, 227 + hat.getValue());
		}
	}
	
	public void update(float delta) {
		hat.update(delta);
		x += speed;
		if (x == CENTER && buying && game.isEnoughSmoothie()){
			if (!purchase.hasStarted()){
				purchase.start();
				speed = 0;
			}
		} 
	}
}

package com.luminositygames.smoothietycoon.entities;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Recipe {

	private double price;
	private int fruit;
	private int ice;
	private int yogurt;
	private int juice;

	public Recipe(){
		setPrice(0.25);
		setFruit(10);
		setIce(10);
		setYogurt(10);
		setJuice(10);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price >= 0.05){
			this.price = price;
		}
	}

	public int getFruit() {
		return fruit;
	}

	public void setFruit(int fruit) {
		if (fruit > 0){
			this.fruit = fruit;
		}
	}

	public int getIce() {
		return ice;
	}

	public void setIce(int ice) {
		if (ice > 0){
			this.ice = ice;
		}
	}

	public int getYogurt() {
		return yogurt;
	}

	public void setYogurt(int yogurt) {
		if (yogurt > 0){
			this.yogurt = yogurt;
		}
	}

	public int getJuice() {
		return juice;
	}

	public void setJuice(int juice) {
		if (juice > 0){
			this.juice = juice;
		}
	}
}

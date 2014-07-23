package com.luminositygames.smoothietycoon.entities;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
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
		this.price = price;
	}

	public int getFruit() {
		return fruit;
	}

	public void setFruit(int fruit) {
		this.fruit = fruit;
	}

	public int getIce() {
		return ice;
	}

	public void setIce(int ice) {
		this.ice = ice;
	}

	public int getYogurt() {
		return yogurt;
	}

	public void setYogurt(int yogurt) {
		this.yogurt = yogurt;
	}

	public int getJuice() {
		return juice;
	}

	public void setJuice(int juice) {
		this.juice = juice;
	}
}

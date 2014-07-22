package com.luminositygames.smoothietycoon.game;

public class Recipe {

	private static final double DEFAULT_PRICE = 0.25;
	private static final int DEFAULT_FRUIT = 10;
	private static final int DEFAULT_ICE = 10;
	private static final int DEFAULT_YOGURT = 10;
	private static final int DEFAULT_JUICE = 10;
	
	private double price;
	private int fruit;
	private int ice;
	private int yogurt;
	private int juice;

	public Recipe(){
		setPrice(DEFAULT_PRICE);
		setFruit(DEFAULT_FRUIT);
		setIce(DEFAULT_ICE);
		setYogurt(DEFAULT_YOGURT);
		setJuice(DEFAULT_JUICE);
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

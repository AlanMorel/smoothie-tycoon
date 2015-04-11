package com.luminositygames.smoothietycoon.ui;

import com.luminositygames.smoothietycoon.Main;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public enum Shop {

	ICE(new int[]{10, 25, 50}, new double[]{1.00, 2.00, 3.00}),
	JUICE(new int[]{10, 25, 50}, new double[]{15, 30, 50}),
	FRUIT(new int[]{25, 50, 100}, new double[]{2.00, 3.00, 5.00}),
	YOGURT(new int[]{10, 25, 50}, new double[]{1.00, 2.00, 3.00}),
	CUPS(new int[]{10, 25, 50}, new double[]{1.00, 2.00, 3.00});

	private int[] amount;
	private double[] price;

	private Shop(int[] a, double[] p) {
		amount = a;
		price = p;
	}

	public int getAmount(int option) {
		return amount[option];
	}

	public double getPrice(int option) {
		return price[option];
	}

	public String getString(int option) {
		if (this == ICE){
			return "Buy " + getAmount(option) + " ice cubes for " + Main.format(getPrice(option));
		} else if (this == JUICE){
			return "Make " + getAmount(option) + " juice using " + (int) getPrice(option) + " fruits";
		} else if (this == FRUIT){
			return "Buy " + getAmount(option) + " fruits for " + Main.format(getPrice(option));
		} else if (this == YOGURT){
			return "Buy " + getAmount(option) + " yogurt for " + Main.format(getPrice(option));
		} else if (this == CUPS){
			return "Buy " + getAmount(option) + " cups for " + Main.format(getPrice(option));
		}
		return null;
	}
};


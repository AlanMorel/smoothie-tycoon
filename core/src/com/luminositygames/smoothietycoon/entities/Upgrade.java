package com.luminositygames.smoothietycoon.entities;

import com.luminositygames.smoothietycoon.ui.Notifications;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public enum Upgrade {

	DOUBLE_CONTAINER(10);

	private double price;
	private boolean purchased;

	private Upgrade(double p) {
		price = p;
		purchased = false;
	}

	public boolean hasPurchased(){
		return purchased;
	}

	public double getPrice(){
		return price;
	}

	public void buy(Player player){
		if (player.getMoney() >= price){
			player.payMoney(price);
			Notifications.show("You've purchased a double container!", Notifications.ACHIEVEMENT);
			purchased = true;
		}
	}
}

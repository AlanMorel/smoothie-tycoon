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

public class Tips {

	private static final String [] TIP = {
		"People like more ice on hotter days",
		"Lower the price for more customers",
		"Buy in bulk to save money",
		"Advertise to draw in customers",
		"Track progress by viewing your statistics",
		"Avoid too much ice on cold days",
		"Buy upgrades for a more efficient business",
		"Keep an eye on your supply of ingredients",
		"You will need cups to sell smoothies in",
		"Use the night to restock",
		"People are drawn to high-quality smoothies",
		"Be prepared to refill your container at all times",
		"Don't be cheap on ingredients",
		"Advertisements last 5 days each",
		"Advertisements begin immediately",
		"Happy customers will Instagram their drinks",
		"Buyers that can't buy a drink will be upset",
		"Have some excess money? Buy an ad",
		"Keep your eyes on the servings left",
		"Keep your customers cool on hot days",
	};

	public static void displayTip(){
		int random = Main.random.nextInt(TIP.length);
		Notifications.show(TIP[random], Notifications.TIP);
	}
}
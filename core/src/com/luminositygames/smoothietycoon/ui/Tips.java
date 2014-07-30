package com.luminositygames.smoothietycoon.ui;

import java.util.ArrayList;

import com.luminositygames.smoothietycoon.SmoothieTycoon;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Tips {

	public static ArrayList<Tip> tips;

	public static void load(){
		tips = new ArrayList<Tip>();
		tips.add(new Tip("People like more ice on hotter days"));
		tips.add(new Tip("Lower the price for more customers"));
		tips.add(new Tip("Buy in bulk to save money"));
		tips.add(new Tip("Advertise to draw in customers"));
		tips.add(new Tip("Track progress by viewing your statistics"));
		tips.add(new Tip("Avoid too much ice on cold days"));
		tips.add(new Tip("Buy upgrades for a more efficient business"));
		tips.add(new Tip("Keep an eye on your supply of ingredients"));
		tips.add(new Tip("You will need cups to sell smoothies in"));
		tips.add(new Tip("Use the night to restock"));
	}

	public static void displayRandomTip(){
		int random = SmoothieTycoon.random.nextInt(tips.size());
		tips.get(random).show();
	}

	public static class Tip {

		private String message;

		public Tip(String message){
			this.message = message;
		}

		public void show(){
			Notifications.show(message, Notifications.TIP);
		}
	}
}
package com.luminositygames.smoothietycoon.entities;

import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.ui.Achievements;
import com.luminositygames.smoothietycoon.util.Sounds;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public enum Advertisements {

	FLYERS(5, 20.00, "Put up flyers for "),
	SOCIAL(10, 35.00, "Buy social media ads for "),
	NEWSPAPER(15, 55.00, "Buy newspaper ads for "),
	RADIO(20, 80.00, "Buy radio ads for "),
	TV(25, 100.00, "Buy TV ads for ");

	public static Advertisements[] ALL = {FLYERS, SOCIAL, NEWSPAPER, RADIO, TV};

	private int customers;
	private double price;
	private int days;
	private String prefix;

	private Advertisements(int customers, double price, String prefix) {
		this.customers = customers;
		this.price = price;
		this.prefix = prefix;
		this.days = 0;
	}

	public int getCustomers(){
		return customers;
	}

	public int getDays(){
		return days;
	}

	public void use(){
		if (days > 0){
			days -= 1;
		}
	}

	public void buy(Player player){
		if (player.getMoney() >= price && days < 5){
			player.addMoney(-price);
			Sounds.play("adPurchase", 0.2f);
			days = 5;
			Achievements.progress(Achievements.FIRST_AD, 1);
			if (getTotalCustomers() == 75){
				Achievements.progress(Achievements.ALL_ADS, 1);
			}
		}
	}

	public String getString() {
		String string = prefix + SmoothieTycoon.format(price);
		if (days > 0){
			string += " (" + days + "d)";
		}
		return string;
	}

	public static Advertisements getById(int option){
		for (int i = 0; i < ALL.length; i++){
			if (i == option){
				return ALL[i];
			}
		}
		return null;
	}

	public static int getTotalCustomers(){
		int total = 0;
		for (int i = 0; i < ALL.length; i++){
			if (ALL[i].getDays() > 0){
				total += ALL[i].getCustomers();
			}
		}
		return total;
	}

	public static void useAds(){
		for (int i = 0; i < ALL.length; i++){
			ALL[i].use();
		}
	}

	public static String getString(int option){
		Advertisements ad = Advertisements.getById(option);
		return ad.getString();
	}
}


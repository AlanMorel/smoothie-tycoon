package com.luminositygames.smoothietycoon.entities;

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

public class Advertisements {

	private static final int[] CUSTOMERS = {5, 10, 15, 20, 25};
	private static final double[] PRICE = {5.00, 10.00, 25.00, 50.00, 100.00};

	private byte flyers;
	private byte social;
	private byte newspapers;
	private byte radio;
	private byte tv;

	public Advertisements(){
		flyers = 0;
		social = 0;
		newspapers = 0;
		radio = 0;
		tv = 0;
	}

	public void use(){
		if (flyers > 0){
			flyers --;
		}
		if (social > 0){
			social --;
		}
		if (newspapers > 0){
			newspapers --;
		}
		if (radio > 0){
			radio --;
		}
		if (tv > 0){
			tv --;
		}
	}

	public void buyFlyers(){
		flyers = 5;
	}

	public void buySocialAds(){
		social = 5;
	}

	public void buyNewspaperAds(){
		newspapers = 5;
	}

	public void buyRadioAds(){
		radio = 5;
	}

	public void buyTVAds(){
		tv = 5;
	}

	public byte getFlyersDuration(){
		return flyers;
	}

	public byte getSocialAds(){
		return social;
	}

	public byte getNewspaperAds(){
		return newspapers;
	}

	public byte getRadioAds(){
		return radio;
	}

	public byte getTVAds(){
		return tv;
	}

	public boolean hasFlyers(){
		return flyers > 0;
	}

	public boolean hasSocialAds(){
		return social > 0;
	}

	public boolean hasNewspaperAds(){
		return newspapers > 0;
	}

	public boolean hasRadioAds(){
		return radio > 0;
	}

	public boolean hasTVAds(){
		return tv > 0;
	}

	public static String getOptionString(Advertisements ads, int option) {
		String string = "";
		if (option == 0){
			string = "Put up flyers for " + SmoothieTycoon.format(PRICE[option]);
			if (ads.hasFlyers()){
				string += " (" + ads.getFlyersDuration() + "d)";
			}
		} else if (option == 1){
			string = "Buy social media ads for " + SmoothieTycoon.format(PRICE[option]);
			if (ads.hasSocialAds()){
				string += " (" + ads.getSocialAds() + "d)";
			}
		} else if (option == 2){
			string = "Buy newspaper ads for " + SmoothieTycoon.format(PRICE[option]);
			if (ads.hasNewspaperAds()){
				string += " (" + ads.getNewspaperAds() + "d)";
			}
		} else if (option == 3){
			string = "Buy radio ads for " + SmoothieTycoon.format(PRICE[option]);
			if (ads.hasRadioAds()){
				string += " (" + ads.getRadioAds() + "d)";
			}
		} else if (option == 4){
			string = "Buy TV ads for " + SmoothieTycoon.format(PRICE[option]);
			if (ads.hasTVAds()){
				string += " (" + ads.getTVAds() + "d)";
			}
		}
		return string;
	}

	public static int getCustomers(int option) {
		return CUSTOMERS[option];
	}

	public static double getPrice(int option) {
		return PRICE[option];
	}

	public int getTotalCustomers(){
		int total = 0;
		if (flyers > 0){
			total += getCustomers(0);
		}
		if (social > 0){
			total += getCustomers(1);
		}
		if (newspapers > 0){
			total += getCustomers(2);
		}
		if (radio > 0){
			total += getCustomers(3);
		}
		if (tv > 0){
			total += getCustomers(4);
		}
		return total;
	}
}

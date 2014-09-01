package com.luminositygames.smoothietycoon.ui;

import java.util.HashMap;

import com.luminositygames.smoothietycoon.Constants;


/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Achievements {

	public static HashMap<Integer, Achievement> achievements;

	public static int PRESS_START = 0;
	public static int BUSINESS = 1;
	public static int COMPANY = 2;
	public static int CORPORATION = 3;
	public static int MONOPOLY = 4;

	public static int FRUIT_NINJA_I = 5;
	public static int FRUIT_NINJA_II = 6;
	public static int FRUIT_NINJA_III = 7;

	public static int ICEMAN_I = 8;
	public static int ICEMAN_II = 9;
	public static int ICEMAN_III = 10;

	public static int GREEK_I = 11;
	public static int GREEK_II = 12;
	public static int GREEK_III = 13;

	public static int JUICE_I = 14;
	public static int JUICE_II = 15;
	public static int JUICE_III = 16;

	public static int CUPS_I = 17;
	public static int CUPS_II = 18;
	public static int CUPS_III = 19;

	public static int REFILL_I = 20;
	public static int REFILL_II = 21;
	public static int REFILL_III = 22;

	public static int EARNINGS_I = 23;
	public static int EARNINGS_II = 24;
	public static int EARNINGS_III = 25;

	public static int MONEY_I = 26;
	public static int MONEY_II = 27;
	public static int MONEY_III = 28;

	public static int DAY_I = 29;
	public static int DAY_II = 30;
	public static int DAY_III = 31;

	public static int UPGRADE_1 = 32;
	public static int UPGRADE_2 = 33;
	public static int UPGRADE_3 = 34;
	public static int HIGH_QUALITY = 35;
	public static int HIGH_PRICE = 36;
	public static int FIRST_AD = 37;
	public static int ALL_ADS = 38;

	public static int COMPLETED = 39;

	public static int [] CUP_SOLD = {PRESS_START, BUSINESS, COMPANY, CORPORATION, MONOPOLY};
	public static int [] FRUIT_PURCHASED = {FRUIT_NINJA_I, FRUIT_NINJA_II, FRUIT_NINJA_III};
	public static int [] ICE_PURCHASED = {ICEMAN_I, ICEMAN_II, ICEMAN_III};
	public static int [] YOGURT_PURCHASED = {GREEK_I, GREEK_II, GREEK_III};
	public static int [] JUICE_MADE = {JUICE_I, JUICE_II, JUICE_III};
	public static int [] CUPS_PURCHASED = {CUPS_I, CUPS_II, CUPS_III};
	public static int [] REFILLS = {REFILL_I, REFILL_II, REFILL_III};
	public static int [] EARNINGS = {EARNINGS_I, EARNINGS_II, EARNINGS_III};
	public static int [] MONEY = {MONEY_I, MONEY_II, MONEY_III};
	public static int [] DAY = {DAY_I, DAY_II, DAY_III};

	public static void load() {
		achievements = new HashMap<Integer, Achievement>();

		achievements.put(PRESS_START, new Achievement("Press Start: Sold 50 cups", 50));
		achievements.put(BUSINESS, new Achievement("Business: Sold 250 cups", 250));
		achievements.put(COMPANY, new Achievement("Company: Sold 500 cups", 500));
		achievements.put(CORPORATION, new Achievement("Corporation: Sold 1000 cups", 1000));
		achievements.put(MONOPOLY, new Achievement("Monopoly: Sold 5000 cups", 5000));

		achievements.put(FRUIT_NINJA_I, new Achievement("Fruit Ninja I: Purchased 1000 fruit", 1000));
		achievements.put(FRUIT_NINJA_II, new Achievement("Fruit Ninja II: Purchased 2500 fruit", 2500));
		achievements.put(FRUIT_NINJA_III, new Achievement("Fruit Ninja III: Purchased 10000 fruit", 10000));

		achievements.put(ICEMAN_I, new Achievement("Iceman I: Purchased 1000 ice", 1000));
		achievements.put(ICEMAN_II, new Achievement("Iceman II: Purchased 2500 ice", 2500));
		achievements.put(ICEMAN_III, new Achievement("Iceman III: Purchased 10000 ice", 10000));

		achievements.put(GREEK_I, new Achievement("Greek I: Purchased 1000 yogurt", 1000));
		achievements.put(GREEK_II, new Achievement("Greek II: Purchased 2500 yogurt", 2500));
		achievements.put(GREEK_III, new Achievement("Greek III: Purchased 10000 yogurt", 10000));

		achievements.put(JUICE_I, new Achievement("Juice I: Made 1000 juice", 1000));
		achievements.put(JUICE_II, new Achievement("Juice II: Made 2500 juice", 2500));
		achievements.put(JUICE_III, new Achievement("Juice III: Made 10000 juice", 10000));

		achievements.put(CUPS_I, new Achievement("Cup I: Purchased 1000 cups", 1000));
		achievements.put(CUPS_II, new Achievement("Cup II: Purchased 2500 cups", 2500));
		achievements.put(CUPS_III, new Achievement("Cup III: Purchased 10000 cups", 10000));

		achievements.put(REFILL_I, new Achievement("Refill I: Refilled 10 containers", 10));
		achievements.put(REFILL_II, new Achievement("Refill II: Refilled 25 containers", 25));
		achievements.put(REFILL_III, new Achievement("Refill III: Refilled 100 containers", 100));

		achievements.put(EARNINGS_I, new Achievement("Earnings I: $10 in lifetime earnings", 10));
		achievements.put(EARNINGS_II, new Achievement("Earnings II: $100 in lifetime earnings", 100));
		achievements.put(EARNINGS_III, new Achievement("Earnings III: $1000 in lifetime earnings", 1000));

		achievements.put(MONEY_I, new Achievement("Money I: Reached $100", 100));
		achievements.put(MONEY_II, new Achievement("Money II: Reached $1,000", 1000));
		achievements.put(MONEY_III, new Achievement("Money III: Reached $10,000", 10000));

		achievements.put(DAY_I, new Achievement("Day I: Reached day 10", 10));
		achievements.put(DAY_II, new Achievement("Day II: Reached day 25", 25));
		achievements.put(DAY_III, new Achievement("Day III: Reached day 50", 50));

		achievements.put(UPGRADE_1, new Achievement("Upgrade 1: Purchased Upgrade 1", 1));
		achievements.put(UPGRADE_2, new Achievement("Upgrade 2: Purchased Upgrade 2", 1));
		achievements.put(UPGRADE_3, new Achievement("Upgrade 3: Purchased Upgrade 3", 1));
		achievements.put(HIGH_QUALITY, new Achievement("High Quality: Sold high quality smoothie", 60));
		achievements.put(HIGH_PRICE, new Achievement("Smucci: Sold expensive smoothie", 5));
		achievements.put(FIRST_AD, new Achievement("Limited-time Offer: Purchased first ad", 1));
		achievements.put(ALL_ADS, new Achievement("Advertising King: Have all ads active", 1));

		achievements.put(COMPLETED, new Achievement("Happy Mom: You've reached Day " + Constants.COMPLETION_DAY + "!", Constants.COMPLETION_DAY));

	}

	public static void progress(int [] ids, double progress){
		for (int i = 0; i < ids.length; i++){
			achievements.get(ids[i]).progress(progress);
		}
	}

	public static void progress(int id, double progress){
		achievements.get(id).progress(progress);
	}

	public static void check(int [] ids, double toCheck){
		for (int i = 0; i < ids.length; i++){
			achievements.get(ids[i]).check(toCheck);
		}
	}

	public static void check(int id, double toCheck){
		achievements.get(id).check(toCheck);
	}

	public static class Achievement {

		private String message;
		private double goal;
		private double progression;
		private boolean completed;

		public Achievement(String message, double goal){
			this.message = message;
			this.goal = goal;
			this.progression = 0;
			this.completed = false;
		}

		private void check (double toCheck){
			if (toCheck >= goal && isUncompleted()){
				complete();
			}
		}

		private void progress (double progress){
			progression += progress;
			if (progression >= goal && isUncompleted()){
				complete();
			}
		}

		private boolean isUncompleted(){
			return !completed;
		}

		private void complete(){
			Notifications.show(message, Notifications.ACHIEVEMENT);
			completed = true;
		}
	}
}
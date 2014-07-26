package com.luminositygames.smoothietycoon;

import java.text.NumberFormat;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Shops {

	private static final NumberFormat money = NumberFormat.getCurrencyInstance();

	public static class Ice {

		private static final int[] AMOUNT = {10, 25, 50};
		private static final double[] PRICE = {1.00, 2.00, 3.00};

		public static String getOptionString(int option) {
			return "Buy " + AMOUNT[option] + " ice cubes for " + money.format(PRICE[option]);
		}

		public static int getAmount(int option) {
			return AMOUNT[option];
		}

		public static double getPrice(int option) {
			return PRICE[option];
		}
	}

	public static class Juice {

		private static final int[] AMOUNT = {10, 25, 50};
		private static final int[] PRICE = {15, 30, 50};

		public static String getOptionString(int option) {
			return "Make " + AMOUNT[option] + " juice using " + PRICE[option] + " fruits";
		}

		public static int getAmount(int option) {
			return AMOUNT[option];
		}

		public static int getPrice(int option) {
			return PRICE[option];
		}

	}

	public static class Fruit {

		private static final int[] AMOUNT = {25, 50, 100};
		private static final double[] PRICE = {2.00, 3.00, 5.00};

		public static String getOptionString(int option) {
			return "Buy " + AMOUNT[option] + " fruits for " + money.format(PRICE[option]);
		}

		public static int getAmount(int option) {
			return AMOUNT[option];
		}

		public static double getPrice(int option) {
			return PRICE[option];
		}
	}

	public static class Yogurt {

		private static final int[] AMOUNT = {10, 25, 50};
		private static final double[] PRICE = {1.00, 2.00, 3.00};

		public static String getOptionString(int option) {
			return "Buy " + AMOUNT[option] + " yogurt for " + money.format(PRICE[option]);
		}

		public static int getAmount(int option) {
			return AMOUNT[option];
		}

		public static double getPrice(int option) {
			return PRICE[option];
		}
	}

	public static class Cups {

		private static final int[] AMOUNT = {10, 25, 50};
		private static final double[] PRICE = {1.00, 2.00, 3.00};

		public static String getOptionString(int option) {
			return "Buy " + AMOUNT[option] + " cups for " + money.format(PRICE[option]);
		}

		public static int getAmount(int option) {
			return AMOUNT[option];
		}

		public static double getPrice(int option) {
			return PRICE[option];
		}
	}
}

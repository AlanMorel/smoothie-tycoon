package com.luminositygames.smoothietycoon;

import java.text.NumberFormat;

public class Shops {

	private static NumberFormat money = NumberFormat.getCurrencyInstance();

	public static class Ice {

		private static int[] AMOUNT = {10, 25, 50};
		private static double[] PRICE = {1.00, 2.00, 3.00};

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

		private static int[] AMOUNT = {10, 25, 50};
		private static int[] PRICE = {15, 30, 50};

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

		private static int[] AMOUNT = {25, 50, 100};
		private static double[] PRICE = {2.00, 3.00, 5.00};

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

		private static int[] AMOUNT = {10, 25, 50};
		private static double[] PRICE = {1.00, 2.00, 3.00};

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

		private static int[] AMOUNT = {10, 25, 50};
		private static double[] PRICE = {1.00, 2.00, 3.00};

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

package com.luminositygames.smoothietycoon.entities;

import java.util.ArrayList;

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

public class Statistics {

	private ArrayList<StatisticsEntry> entries;
	private double lastBalance;

	public Statistics(){
		entries = new ArrayList<StatisticsEntry>();
		lastBalance = 0;
	}

	public void addEntry(int day, double balance){
		StatisticsEntry entry = new StatisticsEntry(day, balance - lastBalance);
		entries.add(entry);
		lastBalance = balance;
	}

	public StatisticsEntry getEntryByDay(int day){
		for (StatisticsEntry entry : entries){
			if(entry.getDay() == day){
				return entry;
			}
		}
		return null;
	}

	public class StatisticsEntry {

		private int day;
		private double profit;

		private StatisticsEntry(int day, double profit){
			this.day = day;
			this.profit = profit;
		}

		public int getDay(){
			return day;
		}

		@Override
		public String toString(){
			return "Day " + getDay() + " | Profit: " + Main.format(profit);
		}
	}
}

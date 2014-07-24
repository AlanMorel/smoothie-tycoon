package com.luminositygames.smoothietycoon;

import java.util.ArrayList;

import com.luminositygames.smoothietycoon.entities.Container;
import com.luminositygames.smoothietycoon.entities.Customer;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.util.Countdown;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Game {

	private Player player;
	private Recipe recipe;
	private Container container;
	private Countdown intermission;
	private Countdown lastSpawn;
	private ArrayList<Customer> customers;

	private int day;
	private int temperature;
	private int maxCustomers;
	private int totalCustomers;

	public Game() {
		this.player = new Player();
		this.recipe = new Recipe();
		this.container = new Container();
		this.day = 0;
		setNewDay();
	}

	public void setNewDay() {
		this.intermission = new Countdown(10 * 1000, false);
		this.temperature = SmoothieTycoon.rand.nextInt(100);
		this.customers = new ArrayList<Customer>();
		this.maxCustomers = getDay() * 5 + 20;
		this.lastSpawn = new Countdown(getSpawnDelay(), true);
		this.totalCustomers = 0;
		this.day ++;
	}

	public Player getPlayer() {
		return player;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public Countdown getIntermission() {
		return intermission;
	}

	public Container getContainer() {
		return container;
	}

	public int getDay() {
		return day;
	}

	public int getTemperature() {
		return temperature;
	}

	public void renderCustomers() {
		for (Customer customer : customers){
			customer.render();
		}
	}

	public void update(float delta) {

		boolean canBuy = getContainer().getServings() - getWaiting() > 0 && player.getCups() > 0;

		for (Customer customer : customers) {
			customer.update(delta, canBuy);
		}

		if (totalCustomers < maxCustomers) {
			addNewCustomer();
		} else if (!customersVisible()) {
			if (!getIntermission().hasStarted()) {
				getIntermission().start();
			} else if(getIntermission().isCompleted()) {
				setNewDay();
			}
		}

		for (int i = 0; i < getPurchases(); i++) {
			getPlayer().addMoney(getRecipe().getPrice());
			getContainer().serve();
			getPlayer().useCup();
		}
	}

	private void addNewCustomer() {
		if (lastSpawn.isCompleted()){
			totalCustomers++;
			customers.add(new Customer(getWillBuy()));
			lastSpawn = new Countdown(getSpawnDelay(), true);
		}
	}

	private int getSpawnDelay() {
		return SmoothieTycoon.rand.nextInt(500) + 1000;
	}

	private boolean customersVisible() {
		for (Customer customer : customers){
			if (!customer.hasLeft()){
				return true;
			}
		}
		return false;
	}

	private int getPurchases() {
		int purchases = 0;
		for (Customer customer : customers){
			if (customer.isBuying()){
				purchases++;
			}
		}
		return purchases;
	}

	private int getWaiting() {
		int waiting = 0;
		for (Customer customer : customers){
			if (customer.isWaiting()){
				waiting++;
			}
		}
		return waiting;
	}

	private boolean getWillBuy(){
		int random = SmoothieTycoon.rand.nextInt(100);
		if (random < getBuyPercentage()){
			return true;
		}
		return false;
	}

	private int getPricePercentageChange(){
		double optimalPrice = 0.25 + day * 0.05;
		int delta = (int) Math.round((optimalPrice - recipe.getPrice()) * 25);
		int percentage = delta * 5 + 75;

		return SmoothieTycoon.fixPercentage(percentage);
	}

	private int getTemperatureIceChange(){
		int optimalIce = temperature / 5;
		int delta = Math.abs(optimalIce - container.getIce());
		int percentage = 100 - delta * 10;

		return SmoothieTycoon.fixPercentage(percentage);
	}

	private int getBuyPercentage(){
		int percentage = 10;

		int pricePerc = getPricePercentageChange() * 70 / 100;
		int tempPerc = getTemperatureIceChange() * 20 / 100;

		percentage += pricePerc;
		percentage += tempPerc;

		//System.out.println("Current Total: " + percentage + "%");

		return percentage;
	}
}

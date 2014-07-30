package com.luminositygames.smoothietycoon;

import java.util.ArrayList;

import com.luminositygames.smoothietycoon.entities.Advertisements;
import com.luminositygames.smoothietycoon.entities.Container;
import com.luminositygames.smoothietycoon.entities.Customer;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.entities.Statistics;
import com.luminositygames.smoothietycoon.ui.Effect;
import com.luminositygames.smoothietycoon.ui.Section;
import com.luminositygames.smoothietycoon.util.Countdown;
import com.luminositygames.smoothietycoon.util.Fonts;

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
	private Advertisements ads;
	private Statistics stats;
	private Countdown night;
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
		this.ads = new Advertisements();
		this.stats = new Statistics();
		this.day = 0;
		startNewDay();
	}

	private void startNewDay() {
		this.stats.addEntry(day, player.getMoney());
		this.day ++;
		this.night = new Countdown(10 * 1000, false);
		this.temperature = SmoothieTycoon.random.nextInt(100);
		this.customers = new ArrayList<Customer>();
		this.maxCustomers = getMaxCustomers();
		this.lastSpawn = new Countdown(getSpawnDelay(), true);
		this.totalCustomers = 0;
		this.ads.use();
	}

	public Player getPlayer() {
		return player;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public Container getContainer() {
		return container;
	}

	public Advertisements getAds(){
		return ads;
	}

	public Statistics getStats(){
		return stats;
	}

	public Countdown getNight() {
		return night;
	}

	public int getDay() {
		return day;
	}

	public int getTemperature() {
		return temperature;
	}

	public void render(Section section, float delta) {
		renderEffects();
		if (section.isStand()){
			renderStand();
		}
	}

	private void renderStand() {
		for (Customer customer : customers){
			customer.render();
		}
		if (!night.hasStarted()){
			Fonts.center(getBuyPercentage() + "% will buy", 660, Fonts.BLACK_36);
		}
	}

	private void renderEffects() {
		if (container.getServings() <= 1){
			Effect.render(Effect.SMOOTHIE);
		}
		if (player.getFruits() <= 9){
			Effect.render(Effect.FRUIT);
		}
		if (player.getIce() <= 9){
			Effect.render(Effect.ICE);
		}
		if (player.getYogurt() <= 9){
			Effect.render(Effect.YOGURT);
		}
		if (player.getJuice() <= 9){
			Effect.render(Effect.JUICE);
		}
		if (player.getCups() <= 3){
			Effect.render(Effect.CUPS);
		}
	}

	public void update(float delta) {
		Effect.update(delta);
		updateCustomers(delta);
		processPurchases();
		if (totalCustomers < maxCustomers) {
			addNewCustomer();
		} else if (!dayStillRunning()) {
			if (!getNight().hasStarted()) {
				getNight().start();
			} else if(getNight().isCompleted()) {
				startNewDay();
			}
		}
	}

	private void updateCustomers(float delta){
		boolean canBuy = getContainer().getServings() - getWaiting() > 0 && player.getCups() > 0;
		for (Customer customer : customers) {
			customer.update(delta, canBuy);
		}
	}

	private void processPurchases(){
		for (int i = 0; i < getPurchases(); i++) {
			getPlayer().addMoney(getRecipe().getPrice());
			getContainer().serve();
			getPlayer().useCup();
		}
	}

	private int getMaxCustomers(){
		int base = 20;
		int dayBonus = day * 4;
		int adBonus = getAds().getTotalCustomers();
		int max = base + dayBonus + adBonus;
		return max;
	}

	public void setNewMaxCustomers() {
		maxCustomers = getMaxCustomers();
	}

	private void addNewCustomer() {
		if (lastSpawn.isCompleted()){
			totalCustomers++;
			customers.add(new Customer(getWillBuy()));
			lastSpawn = new Countdown(getSpawnDelay(), true);
		}
	}

	private int getSpawnDelay() {
		int base = 1000 - getAds().getTotalCustomers() * 10;
		int variable = 500;
		int spawnDelay = SmoothieTycoon.random.nextInt(variable) + base;
		return spawnDelay;
	}

	private boolean dayStillRunning() {
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
		return SmoothieTycoon.random.nextInt(100) < getBuyPercentage();
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
		int percentage = 0;
		int pricePerc = getPricePercentageChange() * 70 / 100;
		int tempPerc = getTemperatureIceChange() * 20 / 100;
		percentage += pricePerc;
		percentage += tempPerc;
		return percentage;
	}
}

package com.luminositygames.smoothietycoon;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.entities.Advertisements;
import com.luminositygames.smoothietycoon.entities.Container;
import com.luminositygames.smoothietycoon.entities.Customer;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.entities.Recipe;
import com.luminositygames.smoothietycoon.entities.Statistics;
import com.luminositygames.smoothietycoon.ui.Achievements;
import com.luminositygames.smoothietycoon.ui.Effects;
import com.luminositygames.smoothietycoon.ui.Events;
import com.luminositygames.smoothietycoon.ui.Notifications;
import com.luminositygames.smoothietycoon.ui.Tips;
import com.luminositygames.smoothietycoon.ui.sections.Section;
import com.luminositygames.smoothietycoon.ui.windows.Window;
import com.luminositygames.smoothietycoon.util.Countdown;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.Sounds;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Game {

	private Player player;
	private Recipe recipe;
	private Container container;
	private Statistics stats;
	private Countdown night;
	private Countdown lastSpawn;
	private ArrayList<Customer> customers;
	private boolean paused;
	private int day;
	private int temperature;
	private int maxCustomers;
	private int totalCustomers;
	private boolean gameOverWindowOpened;

	public Game() {
		player = new Player();
		recipe = new Recipe();
		container = new Container();
		stats = new Statistics();
		day = 0;
		paused = false;
		gameOverWindowOpened = false;
		Notifications.load();
		Achievements.load();
		Events.load();
		startNewDay();
	}

	private void startNewDay() {
		stats.addEntry(day, player.getMoney());
		day += 1;
		player.addMoney(1); //Thanks, Mom!
		night = new Countdown(10 * 1000, false);
		temperature = Main.random.nextInt(100);
		customers = new ArrayList<Customer>();
		lastSpawn = new Countdown(getSpawnDelay(), true);
		totalCustomers = 0;
		setMaxCustomers();
		Advertisements.useAds();
		Achievements.progress(Achievements.DAY, 1);
		Sounds.play("morning", 0.10f);
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

	public void render(float delta) {
		Effects.render(this);
		Notifications.render();
		if (Section.getSection().equals(Section.STAND)){
			renderCustomers();
			renderPercentage();
		}
	}

	private void renderCustomers() {
		for (Customer customer : customers){
			customer.render();
		}
	}
	private void renderPercentage(){
		if (!night.hasStarted()){
			Fonts.center(getBuyPercentage() + "% will purchase", 660, Color.BLACK, 36);
		}
	}

	public void update(float delta) {
		if (paused){
			return;
		}
		Effects.update(delta);
		Notifications.update();
		Events.handle(player);
		updateCustomers(delta);
		processPurchases();
		updateGame();
	}

	private void updateGame() {
		if (totalCustomers < maxCustomers) {
			addNewCustomer();
		} else if (!dayStillRunning()) {
			if (day == Constants.COMPLETION_DAY && (Window.isOpen() || !gameOverWindowOpened)){
				gameOver();
			} else if (!getNight().hasStarted()) {
				night.start();
				Tips.displayTip();
			} else if(getNight().isCompleted()) {
				Events.nextEvent();
				startNewDay();
			}
		}
	}

	private void gameOver() {
		Window.open(Window.GAME_OVER);
		gameOverWindowOpened = true;
		Achievements.check(Achievements.COMPLETED, day);
	}

	private void updateCustomers(float delta){
		boolean canBuy = getContainer().getServings() - getWaiting() > 0 && player.getCups() > 0;
		for (Customer customer : customers) {
			customer.update(delta, canBuy);
		}
	}

	private void processPurchases(){
		for (int i = 0; i < getPurchases(); i++) {
			if (container.getServings() <= 0 || player.getCups() <= 0){
				return;
			}
			int quality = container.getFruit() + container.getJuice() + container.getYogurt();
			Achievements.check(Achievements.HIGH_PRICE, recipe.getPrice());
			Achievements.check(Achievements.HIGH_QUALITY, quality);
			getPlayer().addMoney(getRecipe().getPrice());
			getContainer().serve();
			getPlayer().useCup();
			Sounds.play("purchase", 0.5f);
			Achievements.progress(Achievements.CUP_SOLD, 1);
		}
	}

	public void setMaxCustomers(){
		int base = 20;
		int dayBonus = day * 2;
		int adBonus = Advertisements.getTotalCustomers();
		int max = base + dayBonus + adBonus;
		maxCustomers = max;
	}

	private void addNewCustomer() {
		if (lastSpawn.isCompleted()){
			totalCustomers++;
			customers.add(new Customer(getWillBuy()));
			lastSpawn = new Countdown(getSpawnDelay(), true);
		}
	}

	private int getSpawnDelay() {
		int base = 1250 - Advertisements.getTotalCustomers() * 5;
		int variable = 500;
		int spawnDelay = Main.random.nextInt(variable) + base;
		return spawnDelay;
	}

	private boolean dayStillRunning() {
		for (Customer customer : customers){
			if (!customer.hasExited()){
				return true;
			}
		}
		return false;
	}

	private int getPurchases() {
		int purchases = 0;
		for (Customer customer : customers){
			if (customer.hasPurchased()){
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
		return Main.random.nextInt(100) < getBuyPercentage();
	}

	//No Max
	private int getPricePercentageChange(){
		double optimalPrice = 0.25 + day * 0.05;
		int delta = (int) Math.round((optimalPrice - recipe.getPrice()) * 25);
		int percentage = delta * 10 + 25;
		return percentage;
	}

	//Max +10%
	private int getTemperatureIceChange(){
		int optimalIce = temperature / 5;
		int delta = Math.abs(optimalIce - container.getIce());
		int percentage = 10 - delta;
		return percentage;
	}

	//Max +10%
	private int getQualityChange(){
		int standardQuality = 30;
		int currentQuality = container.getFruit() + container.getJuice() + container.getYogurt();
		int percentage = (currentQuality - standardQuality) / 3;
		return percentage;
	}

	//0% - 100%
	private int getBuyPercentage(){
		int percentage = 0;
		int pricePerc = getPricePercentageChange();
		int tempPerc = getTemperatureIceChange();
		int qualityPerc = getQualityChange();
		percentage += pricePerc;
		percentage += tempPerc;
		percentage += qualityPerc;
		return Main.fixPercentage(percentage);
	}

	public void togglePause() {
		paused = !paused;
	}
}

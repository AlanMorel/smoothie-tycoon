package com.luminositygames.smoothietycoon.entities;

import java.util.ArrayList;

import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
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

public class Customers {

	private Game game;
	
	private ArrayList<Customer> customers;
	
	private Countdown lastSpawn;
	
	private int maxCustomers;
	private int totalCustomers;

	public Customers(Game game){
		this.game = game;
		this.lastSpawn = new Countdown(getDelay(), true);
		this.lastSpawn.start();
		this.customers = new ArrayList<Customer>();
		setMaxCustomers();
		setTotalCustomers(0);
	}

	public void render(){
		for (Customer customer : customers){
			customer.render();
		}
	}

	public void update(float delta) {
		for (Customer customer : customers){
			customer.update(delta);
		}
		if (totalCustomers < maxCustomers){
			addCustomer();
		} else if (!areCustomersVisible()){
			if (!game.getIntermission().hasStarted()){
				game.getIntermission().start();
			} else if(game.getIntermission().isCompleted()){
				game.newDay();
			}
		}
		handlePurchaes();
	}

	public int getPurchases() {
		int purchases = 0;
		for (Customer customer : customers){
			if (customer.isBuying() && game.canServe()){
				purchases++;
			}
		}
		return purchases;
	}

	public int getWaiting() {
		int waiting = 0;
		for (Customer customer : customers){
			if (customer.isWaiting()){
				waiting++;
			}
		}
		return waiting;
	}
	
	public boolean areCustomersVisible() {
		for (Customer customer : customers){
			if (!customer.hasLeft()){
				return true;
			}
		}
		return false;
	}

	public void addCustomer() {
		if (lastSpawn.isCompleted()){
			totalCustomers++;
			Customer customer = new Customer(game);
			customers.add(customer);
			lastSpawn = new Countdown(getDelay(), true);
		}
	}

	public int getDelay(){
		int delay = SmoothieTycoon.rand.nextInt(500) + 1000;
		return delay;
	}

	public int getSide(){
		int side = SmoothieTycoon.rand.nextInt(2);
		return side;
	}

	public int getMaxCustomers(){
		return maxCustomers;
	}

	public void setMaxCustomers(){
		int day = game.getDay();
		int calculatedMaxCustomers = 20 + (day * 5);
		maxCustomers = calculatedMaxCustomers;
	}

	public int getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public boolean getBuy(){
		int percentage = game.getBuyPercentage();
		int random = SmoothieTycoon.rand.nextInt(100);
		if (random < percentage){
			return true;
		}
		return false;
	}

	private void handlePurchaes() {
		
		double price = game.getRecipe().getPrice();
		
		for (int i = 0; i < getPurchases(); i++){
			game.getContainer().serve();
			game.getPlayer().addMoney(price);
		}
	}
}

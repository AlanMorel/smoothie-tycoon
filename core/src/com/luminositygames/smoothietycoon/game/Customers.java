package com.luminositygames.smoothietycoon.game;

import java.util.ArrayList;

import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.Countdown;


public class Customers {

	private ArrayList<Customer> customers;

	private Game game;

	private int maxCustomers;
	private int totalCustomers;
	private Countdown lastSpawn;

	public Customers(Game g){
		this.game = g;
		lastSpawn = new Countdown(getDelay(), true);
		lastSpawn.start();
		customers = new ArrayList<Customer>();
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
		} else if (!isCustomersVisible()){
			if (!game.getIntermission().hasStarted()){
				game.getIntermission().start();
			} else if(game.getIntermission().isCompleted()){
				game.newDay();
			}
			//NEW DAY
		}
		handlePurchaes();
	}

	public int getPurchases() {
		int purchases = 0;
		for (Customer customer : customers){
			if (customer.isBuying()){
				purchases++;
			}
		}
		return purchases;
	}

	public boolean isCustomersVisible() {
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
			Customer customer = new Customer(getBuy(), getSide());
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
		int percentage = getBuyPercentage();
		int random = SmoothieTycoon.rand.nextInt(100);
		if (random < percentage){
			return true;
		}
		return false;
	}

	public int getBuyPercentage() {
		return game.getBuyPercentage();
	}

	private void handlePurchaes() {
		if (game.canServe()){
			double price = game.getRecipe().getPrice();
			int purchases =  game.getCustomers().getPurchases();
			for (int i = 0; i < purchases; i++){
				game.getContainer().serve();
				game.getPlayer().setMoney(game.getPlayer().getMoney() + price);
			}
		}
	}
}

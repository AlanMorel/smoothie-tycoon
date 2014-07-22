package com.luminositygames.smoothietycoon.game;

import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.Countdown;

public class Game {

	private static final int NIGHT = 15000;

	private Player player;
	private Recipe recipe;
	private Customers customers;
	private Container container;
	private int day;
	private int temperature;
	private Countdown intermission;

	public Game(){
		this.player = new Player(this);
		this.recipe = new Recipe();
		this.container = new Container(this);
		this.day = 1;
		setNewTemperature();
		customers = new Customers(this);
		intermission = new Countdown(NIGHT, false);
	}

	public int getTemperature(){
		return temperature;
	}

	public void setNewTemperature() {
		//this.temperature = 0;
		this.temperature = SmoothieTycoon.rand.nextInt(100);
	}

	public Player getPlayer() {
		return player;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public int getDay(){
		return day;
	}

	public Customers getCustomers() {
		return customers;
	}

	public Countdown getIntermission(){
		return intermission;
	}

	public Container getContainer() {
		return container;
	}

	public void update(float delta){
		getCustomers().update(delta);
	}

	public void newDay() {
		intermission = new Countdown(NIGHT, false);
		day++;
		setNewTemperature();
		customers = new Customers(this);
	}

	public int adjustPerc(int perc){
		if (perc < 0){
			return 0;
		} else if (perc > 100) {
			return 100;
		} else {
			return perc;
		}
	}
	
	public int getPricePercentageChange(){

		double optimalPrice = 0.25 + (day * 0.05);
		double price = recipe.getPrice();
		
		int x = (int) Math.round(((optimalPrice - price) * 25));

		
		int percentage = (x * 5 + 75);
		
		return adjustPerc(percentage);
	}

	public int getTemperatureIceChange(){
		int ice = container.getIce();
		int idealIce = temperature / 5;
		int delta = Math.abs(ice - idealIce);
		int percentage = 100 - (delta * 10);
		return adjustPerc(percentage);
	}

	public int getBuyPercentage(){
		int percentage = 10;
		
		int pricePerc = getPricePercentageChange() * 70 / 100;
		int tempPerc = getTemperatureIceChange() * 20 / 100;
		
		percentage += pricePerc;
		percentage += tempPerc;
		
		//System.out.println("Current Price: " + pricePerc + "%");
		//System.out.println("Current Temp: " + tempPerc + "%");
		//System.out.println("Current Total: " + percentage + "%");
		//System.out.println("");
		return 75;
	}

	public boolean canServe() {
		if (container.hasServings() && player.getCups() > 0){
			return true;
		}
		return false;
	}
}

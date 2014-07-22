package com.luminositygames.smoothietycoon.game;

public class Container {

	private Game game;
	private int servings;
	private int fruit;
	private int ice;
	private int yogurt;
	private int juice;

	public Container(Game g) {
		this.game = g;
		this.servings = 0;
		this.fruit = game.getRecipe().getFruit();
		this.ice = game.getRecipe().getIce();
		this.yogurt = game.getRecipe().getYogurt();
		this.juice = game.getRecipe().getJuice();
	}

	public void refill() {
		Recipe recipe = game.getRecipe();
		Player player = game.getPlayer();
		boolean enoughFruits = player.getFruits() >= recipe.getFruit();
		boolean enoughIce = player.getIce() >= recipe.getIce();
		boolean enoughYogurt = player.getYogurt() >= recipe.getYogurt();
		boolean enoughJuice = player.getJuice() >= recipe.getJuice();
		if (enoughFruits && enoughIce && enoughYogurt && enoughJuice){
			servings = 20;
			this.fruit = game.getRecipe().getFruit();
			this.ice = game.getRecipe().getIce();
			this.yogurt = game.getRecipe().getYogurt();
			this.juice = game.getRecipe().getJuice();
			game.getPlayer().makeContainer();
		}
	}

	public void empty(){
		servings = 0;
	}

	public void serve(){
		servings --;
		game.getPlayer().useCup();
	}

	public int getFruit() {
		return fruit;
	}

	public void setFruit(int fruit) {
		this.fruit = fruit;
	}

	public int getIce() {
		return ice;
	}

	public void setIce(int ice) {
		this.ice = ice;
	}

	public int getYogurt() {
		return yogurt;
	}

	public void setYogurt(int yogurt) {
		this.yogurt = yogurt;
	}

	public int getJuice() {
		return juice;
	}

	public void setJuice(int juice) {
		this.juice = juice;
	}

	public int getServings() {
		return servings;
	}

	public boolean hasServings(){
		return servings > 0;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}
}

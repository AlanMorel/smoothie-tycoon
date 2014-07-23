package com.luminositygames.smoothietycoon.entities;

import com.luminositygames.smoothietycoon.Game;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Player {
	
	private Game game;
	
	private double money;
	private int fruits;
	private int ice;
	private int yogurt;
	private int juice;
	private int cups;
	
	public Player(Game game){
		this.game = game;
		this.money = 20.00;
		this.fruits = 0;
		this.ice = 0;
		this.yogurt = 0;
		this.juice = 0;
		this.cups = 0;
	}
	
	public int getFruits() {
		return fruits;
	}
	
	public boolean canPay(double price){
		if (money >= price){
			return true;
		}
		return false;
	}
	
	public void makeContainer(){
		Recipe recipe = game.getRecipe();
		fruits -= recipe.getFruit();
		ice -= recipe.getIce();
		yogurt -= recipe.getYogurt();
		juice -= recipe.getJuice();
	}

	public void buyFruits(int amount, double price){
		if (canPay(price)){
			fruits += amount;
			money -= price;
		}
	}

	public void buyIce(int amount, double price){
		if (canPay(price)){
			ice += amount;
			money -= price;
		}
	}
	
	public void buyYogurt(int amount, double price){
		if (canPay(price)){
			yogurt += amount;
			money -= price;
		}
	}

	public void makeJuice(int amount, double price){
		if (fruits >= price){
			juice += amount;
			fruits -= price;
		}
	}	
	
	public void buyCups(int amount, double price){
		if (canPay(price)){
			cups += amount;
			money -= price;
		}
	}
	
	public int getIce() {
		return ice;
	}

	public int getYogurt() {
		return yogurt;
	}

	public int getCups() {
		return cups;
	}

	public int getJuice() {
		return juice;
	}

	public double getMoney() {
		return money;
	}

	public void addMoney(double money) {
		this.money += money;
	}

	public void useCup() {
		cups --;
	}
}

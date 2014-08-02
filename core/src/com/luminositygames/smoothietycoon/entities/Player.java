package com.luminositygames.smoothietycoon.entities;

import com.luminositygames.smoothietycoon.ui.Achievements;
import com.luminositygames.smoothietycoon.util.Sounds;


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

	private double money;
	private int fruits;
	private int ice;
	private int yogurt;
	private int juice;
	private int cups;

	public Player(){
		this.money = 20.00;
		this.fruits = 0;
		this.ice = 0;
		this.yogurt = 0;
		this.juice = 0;
		this.cups = 5;
	}

	public int getFruits() {
		return fruits;
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
		if (money > 0){
			Achievements.progress(Achievements.MONEY, money);
		}
	}

	public void useCup() {
		cups --;
	}

	public boolean canPay(double price){
		if (money >= price){
			Sounds.play("supplies", 0.5f);
			return true;
		}
		return false;
	}

	public void buyFruits(int amount, double price){
		if (canPay(price)){
			fruits += amount;
			money -= price;
			Achievements.progress(Achievements.FRUIT_PURCHASED, amount);
		}
	}

	public void buyIce(int amount, double price){
		if (canPay(price)){
			ice += amount;
			money -= price;
			Achievements.progress(Achievements.ICE_PURCHASED, amount);
		}
	}

	public void buyYogurt(int amount, double price){
		if (canPay(price)){
			yogurt += amount;
			money -= price;
			Achievements.progress(Achievements.YOGURT_PURCHASED, amount);
		}
	}

	public void makeJuice(int amount, double price){
		if (fruits >= price){
			juice += amount;
			fruits -= price;
			Achievements.progress(Achievements.JUICE_MADE, amount);
		}
	}

	public void buyCups(int amount, double price){
		if (canPay(price)){
			cups += amount;
			money -= price;
			Achievements.progress(Achievements.CUPS_PURCHASED, amount);
		}
	}

	public void makeContainer(Recipe recipe){
		fruits -= recipe.getFruit();
		ice -= recipe.getIce();
		yogurt -= recipe.getYogurt();
		juice -= recipe.getJuice();
		Sounds.play("refill", 0.5f);
	}
}

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

	public void setFruits(int f) {
		fruits = f;;
	}

	public void setIce(int i) {
		ice = i;
	}

	public void setYogurt(int y) {
		yogurt = y;
	}

	public void setCups(int c) {
		cups = c;
	}

	public void setJuice(int j) {
		juice = j;
	}

	public void setMoney(double m) {
		money = m;
	}

	public void addMoney(double money) {
		setMoney(getMoney() + money);
		if (money > 0){
			Achievements.progress(Achievements.EARNINGS, money);
		}
		Achievements.check(Achievements.MONEY, getMoney());
	}

	public boolean canPay(double price){
		if (money >= price){
			Sounds.play("supplies", 0.5f);
			return true;
		}
		return false;
	}

	public void useCup() {
		setCups(getCups() - 1);
	}

	public void buyFruits(int amount, double price){
		if (canPay(price)){
			setFruits(getFruits() + amount);
			setMoney(getMoney() - price);
			Achievements.progress(Achievements.FRUIT_PURCHASED, amount);
		}
	}

	public void buyIce(int amount, double price){
		if (canPay(price)){
			setIce(getIce() + amount);
			setMoney(getMoney() - price);
			Achievements.progress(Achievements.ICE_PURCHASED, amount);
		}
	}

	public void buyYogurt(int amount, double price){
		if (canPay(price)){
			setYogurt(getYogurt() + amount);
			setMoney(getMoney() - price);
			Achievements.progress(Achievements.YOGURT_PURCHASED, amount);
		}
	}

	public void makeJuice(int amount, double price){
		if (getFruits() >= price){
			setJuice(getJuice() + amount);
			setFruits(getFruits() - (int) price);
			Achievements.progress(Achievements.JUICE_MADE, amount);
			Sounds.play("supplies", 0.5f);
		}
	}

	public void buyCups(int amount, double price){
		if (canPay(price)){
			setCups(getCups() + amount);
			setMoney(getMoney() - price);
			Achievements.progress(Achievements.CUPS_PURCHASED, amount);
		}
	}

	public void makeContainer(Recipe recipe){
		setFruits(getFruits() - recipe.getFruit());
		setIce(getIce() - recipe.getIce());
		setYogurt(getYogurt() - recipe.getYogurt());
		setJuice(getJuice() - recipe.getJuice());
		Sounds.play("refill", 0.5f);
	}
}

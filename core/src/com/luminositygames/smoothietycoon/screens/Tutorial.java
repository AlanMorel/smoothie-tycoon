package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.sections.Kitchen;
import com.luminositygames.smoothietycoon.sections.Market;
import com.luminositygames.smoothietycoon.sections.Office;
import com.luminositygames.smoothietycoon.sections.Section;
import com.luminositygames.smoothietycoon.sections.Stand;
import com.luminositygames.smoothietycoon.ui.UserInterface;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Tutorial implements Screen2 {

	private UserInterface ui;
	private GameTween arrow;
	private Section section;
	private Stand stand;
	private Kitchen kitchen;
	private Market market;
	private Office office;

	private int stage;

	@Override
	public void load() {
		this.ui = new UserInterface();
		this.stand = new Stand();
		this.kitchen = new Kitchen();
		this.market = new Market();
		this.office = new Office();
		this.section = stand;	
		this.stage = TutorialStage.INTRO;
		this.arrow = new GameTween(-20, GameTween.ARROW);
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		renderTutorial();
		renderTutorialUI();
	}

	private void renderTutorial(){
		renderTutorialStage();
		if (stage == TutorialStage.INTRO){
			Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		} else {
			Image.draw("leftArrow", 50, 650);
			Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		}
	}
	
	private void renderTutorialUI() {

		double money = 12.50;

		int day = 16;

		int fruits = 999;
		int ice = 999;
		int yogurt = 999;
		int juice = 999;
		int cups = 999;

		int temperature = 74;

		int servings = 9;
		
		Image.rectangle(0, 0, Constants.WIDTH, 90, 0.1f, Color.BLACK);

		ui.renderDay(day);
		ui.renderMoney(money);
		ui.renderIngredients(fruits, ice, yogurt, juice, cups);
		ui.renderThermometer(temperature);
		ui.renderContainer(servings);
	}

	@Override
	public void update(float delta) {
		arrow.update(delta);
		if (Image.get("leftArrow").isTouched()){
			goBack();
		} else if (Image.get("rightArrow").isTouched()){
			advance();
		}
	}

	private void advance() {
		stage++;
		if (stage == TutorialStage.KITCHEN){
			section = kitchen;
		} else if (stage == TutorialStage.MARKET){
			section = market;
		} else if (stage == TutorialStage.OFFICE){
			section = office;
		} else if (stage == TutorialStage.JOB){
			section = stand;
		} else if (stage > TutorialStage.START){
			SmoothieTycoon.setScreen(SmoothieTycoon.gameplay);
		}
	}

	private void goBack() {
		stage--;
		if (stage == TutorialStage.PLAYER){
			section = stand;
		} else if (stage == TutorialStage.BLENDER){
			section = kitchen;
		} else if (stage == TutorialStage.BUY_CUPS){
			section = market;
		} else if (stage == TutorialStage.SAVELOAD){
			section = office;
		}
		if (stage < TutorialStage.INTRO){
			stage = TutorialStage.INTRO;
		}
	}

	@Override
	public void keyPress(int keycode) {
		
	}
	
	public void renderTutorialStage(){
		float textHeight = 620;
		
		if (stage == TutorialStage.INTRO){
			Fonts.center("This is you. You run a smoothie stand.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.MONEY){
			Image.draw("upArrow", 50, 90 + arrow.getValue());
			Fonts.center("This is your money.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.DAY){
			Image.draw("upArrow", 300, 90 + arrow.getValue());
			Fonts.center("Here is what day it is.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.FRUIT){
			Image.draw("upArrow", 541, 90 + arrow.getValue());
			Fonts.center("This is how much fruit you have.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.ICE){
			Image.draw("upArrow", 692, 90 + arrow.getValue());
			Fonts.center("This is how much ice you have.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.YOGURT){
			Image.draw("upArrow", 843, 90 + arrow.getValue());
			Fonts.center("This is how much yogurt you have.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.JUICE){
			Image.draw("upArrow", 993, 90 + arrow.getValue());
			Fonts.center("This is how much juice you have.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.CUP){
			Image.draw("upArrow", 1142, 90 + arrow.getValue());
			Fonts.center("This is how many cups you have.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.THERMOMETER){
			Image.draw("rightArrow", 1100 + arrow.getValue(), 250);
			Fonts.center("This is the thermometer.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.TEMPERATURE){
			Image.draw("rightArrow", 1100 + arrow.getValue(), 355);
			Fonts.center("It tells you the temperature.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.CONTAINER){
			Image.draw("leftArrow", 100 + arrow.getValue(), 225);
			Fonts.center("This is your smoothie container.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.SERVINGS){
			Image.draw("leftArrow", 90 + arrow.getValue(), 355);
			Fonts.center("It tells you how many servings you have left.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.PLAYER){
			Image.draw("rightArrow", 450 + arrow.getValue(), 250);
			Fonts.center("You can adjust things by clicking on stand.", textHeight, Fonts.BLACK_48);	
		} else if (stage == TutorialStage.KITCHEN){
			Fonts.center("This is your kitchen.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.REFRIDGERATOR){
			Fonts.center("You can get ice from your refridgerator.", textHeight, Fonts.BLACK_48);
			Image.draw("leftArrow", 400 + arrow.getValue(), 200);
		} else if (stage == TutorialStage.JUICER){
			Image.draw("upArrow", 820, 425 + arrow.getValue());
			Fonts.center("You make juice by juicing your fruit.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.BLENDER){
			Image.draw("upArrow", 1015, 425 + arrow.getValue());
			Fonts.center("You make smoothie with this blender.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.MARKET){
			Fonts.center("This is the market.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.BUY_FRUITS){
			Fonts.center("You can buy fruit here.", textHeight, Fonts.BLACK_48);
			Image.draw("upArrow", 238, 500 + arrow.getValue());
		} else if (stage == TutorialStage.BUY_YOGURT){
			Image.draw("upArrow", 614, 500 + arrow.getValue());
			Fonts.center("You can buy yogurt here.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.BUY_CUPS){
			Image.draw("upArrow", 975, 500 + arrow.getValue());
			Fonts.center("You can buy cups here.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.OFFICE){
			Fonts.center("This is your office.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.ADVERTISE){
			Fonts.center("You can advertise here.", textHeight, Fonts.BLACK_48);
			Image.draw("upArrow", 230, 500 + arrow.getValue());
		} else if (stage == TutorialStage.STATISTICS){
			Image.draw("upArrow", 614, 500 + arrow.getValue());
			Fonts.center("You can check statistics here.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.SAVELOAD){
			Image.draw("upArrow", 980, 500 + arrow.getValue());
			Fonts.center("You can save and load your game here.", textHeight, Fonts.BLACK_48);
		} else if (stage == TutorialStage.JOB){
			Fonts.center("Your job is simple. Make money.", textHeight, Fonts.BLACK_48);			
		} else if (stage == TutorialStage.START){
			Fonts.center("Let's get started! Good luck.", textHeight, Fonts.BLACK_48);			
		}
	}
	
	public class TutorialStage {
		
		//STAND
		public static final int INTRO = 0;
		public static final int MONEY = 1;
		public static final int DAY = 2;
		public static final int FRUIT = 3;
		public static final int ICE = 4;
		public static final int YOGURT = 5;
		public static final int JUICE = 6;
		public static final int CUP = 7;
		public static final int THERMOMETER = 8;
		public static final int TEMPERATURE = 9;
		public static final int CONTAINER = 10;
		public static final int SERVINGS = 11;
		public static final int PLAYER = 12;

		//KITCHEN
		public static final int KITCHEN = 13;
		public static final int REFRIDGERATOR = 14;
		public static final int JUICER = 15;
		public static final int BLENDER = 16;

		//MARKET
		public static final int MARKET = 17;
		public static final int BUY_FRUITS = 18;
		public static final int BUY_YOGURT = 19;
		public static final int BUY_CUPS = 20;

		//OFFICE
		public static final int OFFICE = 21;
		public static final int ADVERTISE = 22;
		public static final int STATISTICS = 23;
		public static final int SAVELOAD = 24;
		
		//STAND
		public static final int JOB = 25;
		public static final int START = 26;
	}
}

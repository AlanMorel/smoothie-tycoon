package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.ui.Section;
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

	private Section section;
	private UserInterface ui;
	private GameTween arrow;
	private byte currentStage;

	@Override
	public void load() {
		this.section = new Section();
		this.ui = new UserInterface();
		this.arrow = new GameTween(-20, GameTween.ARROW);
		this.currentStage = TutorialStage.INTRO;
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		renderTutorialStage();
		renderTutorialUI();
		renderArrows();
	}

	private void renderTutorialUI() {
		double money = 20.00;
		int day = 0;
		int fruits = 0;
		int ice = 0;
		int yogurt = 0;
		int juice = 0;
		int cups = 0;
		int temperature = 74;
		int servings = 10;

		Image.rectangle(0, 0, Constants.WIDTH, 90, 0.1f, Color.BLACK);
		ui.renderDay(day);
		ui.renderMoney(money);
		ui.renderIngredients(fruits, ice, yogurt, juice, cups);
		ui.renderThermometer(temperature);
		ui.renderContainer(servings);
	}

	private void renderArrows(){
		if (currentStage == TutorialStage.INTRO){
			Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		} else {
			Image.draw("leftArrow", 50, 650);
			Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		}
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
		currentStage++;
		if (currentStage == TutorialStage.KITCHEN){
			section.setSection(Section.KITCHEN);
		} else if (currentStage == TutorialStage.MARKET){
			section.setSection(Section.MARKET);
		} else if (currentStage == TutorialStage.OFFICE){
			section.setSection(Section.OFFICE);
		} else if (currentStage == TutorialStage.MOM){
			section.setSection(Section.STAND);
		} else if (currentStage > TutorialStage.START){
			SmoothieTycoon.setScreen(SmoothieTycoon.gameplay);
		}
	}

	private void goBack() {
		currentStage--;
		if (currentStage == TutorialStage.PLAYER){
			section.setSection(Section.STAND);
		} else if (currentStage == TutorialStage.BLENDER){
			section.setSection(Section.KITCHEN);
		} else if (currentStage == TutorialStage.BUY_CUPS){
			section.setSection(Section.MARKET);
		} else if (currentStage == TutorialStage.UPGRADES){
			section.setSection(Section.OFFICE);
		}
		if (currentStage < TutorialStage.INTRO){
			currentStage = TutorialStage.INTRO;
		}
	}

	@Override
	public void keyPress(int keycode) {
		if (keycode == Keys.LEFT){
			goBack();
		} else if (keycode == Keys.RIGHT){
			advance();
		}
	}

	private void renderTutorialStage(){
		float textHeight = 620;
		if (currentStage == TutorialStage.INTRO){
			Fonts.center("This is you. You run a smoothie stand.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.MONEY){
			Image.draw("upArrow", 50, 90 + arrow.getValue());
			Fonts.center("This is your money.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.DAY){
			Image.draw("upArrow", 300, 90 + arrow.getValue());
			Fonts.center("Here is what day it is.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.FRUIT){
			Image.draw("upArrow", 541, 90 + arrow.getValue());
			Fonts.center("This is how much fruit you have.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.ICE){
			Image.draw("upArrow", 692, 90 + arrow.getValue());
			Fonts.center("This is how much ice you have.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.YOGURT){
			Image.draw("upArrow", 843, 90 + arrow.getValue());
			Fonts.center("This is how much yogurt you have.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.JUICE){
			Image.draw("upArrow", 993, 90 + arrow.getValue());
			Fonts.center("This is how much juice you have.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.CUP){
			Image.draw("upArrow", 1142, 90 + arrow.getValue());
			Fonts.center("This is how many cups you have.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.THERMOMETER){
			Image.draw("rightArrow", 1100 + arrow.getValue(), 250);
			Fonts.center("This is the thermometer.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.TEMPERATURE){
			Image.draw("rightArrow", 1100 + arrow.getValue(), 355);
			Fonts.center("It tells you the temperature.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.CONTAINER){
			Image.draw("leftArrow", 100 + arrow.getValue(), 225);
			Fonts.center("This is your smoothie container.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.SERVINGS){
			Image.draw("leftArrow", 90 + arrow.getValue(), 355);
			Fonts.center("It tells you how many servings you have left.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.PLAYER){
			Image.draw("rightArrow", 450 + arrow.getValue(), 250);
			Fonts.center("You can adjust things by clicking on stand.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.KITCHEN){
			Fonts.center("This is your kitchen.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.REFRIDGERATOR){
			Fonts.center("You can get ice from your refridgerator.", textHeight, Fonts.BLACK_48);
			Image.draw("leftArrow", 400 + arrow.getValue(), 200);
		} else if (currentStage == TutorialStage.JUICER){
			Image.draw("upArrow", 815, 425 + arrow.getValue());
			Fonts.center("You make juice by juicing your fruit.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.BLENDER){
			Image.draw("upArrow", 1005, 420 + arrow.getValue());
			Fonts.center("You make smoothie with this blender.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.MARKET){
			Fonts.center("This is the market.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.BUY_FRUITS){
			Fonts.center("You can buy fruit here.", textHeight, Fonts.BLACK_48);
			Image.draw("upArrow", 240, 500 + arrow.getValue());
		} else if (currentStage == TutorialStage.BUY_YOGURT){
			Image.draw("upArrow", 605, 500 + arrow.getValue());
			Fonts.center("You can buy yogurt here.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.BUY_CUPS){
			Image.draw("upArrow", 975, 500 + arrow.getValue());
			Fonts.center("You can buy cups here.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.OFFICE){
			Fonts.center("This is your office.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.ADVERTISE){
			Fonts.center("You can advertise here.", textHeight, Fonts.BLACK_48);
			Image.draw("upArrow", 245, 525 + arrow.getValue());
		} else if (currentStage == TutorialStage.STATISTICS){
			Image.draw("upArrow", 635, 525 + arrow.getValue());
			Fonts.center("You can check statistics here.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.UPGRADES){
			Image.draw("upArrow", 1030, 525 + arrow.getValue());
			Fonts.center("(Upgrades are coming soon!)", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.MOM){
			Fonts.center("Your mom gives you a dollar every morning.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.COUNTING){
			Image.draw("upArrow", 50, 90 + arrow.getValue());
			Fonts.center("She's counting on you to help her with the bills.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.JOB){
			Image.draw("upArrow", 300, 90 + arrow.getValue());
			Fonts.center("You have " + Constants.COMPLETION_DAY + " days to make money and her proud.", textHeight, Fonts.BLACK_48);
		} else if (currentStage == TutorialStage.START){
			Image.draw("rightArrow", 450 + arrow.getValue(), 250);
			Fonts.center("Let's get started! Good luck.", textHeight, Fonts.BLACK_48);
		}
	}

	private class TutorialStage {
		//STAND
		public static final byte INTRO = 0;
		public static final byte MONEY = 1;
		public static final byte DAY = 2;
		public static final byte FRUIT = 3;
		public static final byte ICE = 4;
		public static final byte YOGURT = 5;
		public static final byte JUICE = 6;
		public static final byte CUP = 7;
		public static final byte THERMOMETER = 8;
		public static final byte TEMPERATURE = 9;
		public static final byte CONTAINER = 10;
		public static final byte SERVINGS = 11;
		public static final byte PLAYER = 12;
		//KITCHEN
		public static final byte KITCHEN = 13;
		public static final byte REFRIDGERATOR = 14;
		public static final byte JUICER = 15;
		public static final byte BLENDER = 16;
		//MARKET
		public static final byte MARKET = 17;
		public static final byte BUY_FRUITS = 18;
		public static final byte BUY_YOGURT = 19;
		public static final byte BUY_CUPS = 20;
		//OFFICE
		public static final byte OFFICE = 21;
		public static final byte ADVERTISE = 22;
		public static final byte STATISTICS = 23;
		public static final byte UPGRADES = 24;
		//STAND
		public static final byte MOM = 25;
		public static final byte COUNTING = 26;
		public static final byte JOB = 27;
		public static final byte START = 28;
	}
}

package com.luminositygames.smoothietycoon.screens;

import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Screen2;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.sections.Kitchen;
import com.luminositygames.smoothietycoon.sections.Market;
import com.luminositygames.smoothietycoon.sections.Office;
import com.luminositygames.smoothietycoon.sections.Section;
import com.luminositygames.smoothietycoon.sections.Stand;
import com.luminositygames.smoothietycoon.sections.UserInterface;
import com.luminositygames.smoothietycoon.util.Animation;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

public class Tutorial implements Screen2 {

	private UserInterface ui;
	private Animation test;
	private GameTween arrow;
	private Section section;

	private Stand stand;
	private Kitchen kitchen;
	private Market market;
	private Office office;

	private int stage;

	@Override
	public void load() {
		stage = TutorialStage.INTRO;
		arrow = new GameTween(-20, GameTween.ARROW);

		ui = new UserInterface();
		stand = new Stand(ui);
		kitchen = new Kitchen(ui);
		market = new Market(ui);
		office = new Office(ui);

		section = stand;

		test = new Animation();
		test.addFrame("fruit", 100);
		test.addFrame("ice", 100);
		test.addFrame("yogurt", 100);
		test.addFrame("juice", 100);
		test.addFrame("cup", 100);
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		//test.draw(100,100);
		ui.renderTutorialUI();
		renderTutorial();
	}

	private void renderTutorial(){
		section.renderTutorial(arrow, stage);
		if (stage == TutorialStage.INTRO){
			Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		} else {
			Image.draw("leftArrow", 50, 650);
			Image.draw("rightArrow", Constants.WIDTH - 100, 650);
		}
	}

	@Override
	public void update(float delta) {
		arrow.update(delta);
		test.update();
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
		} else if (stage == TutorialStage.START){
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
	
	public class TutorialStage {
		//STAND
		public static final int INTRO = 0;
		public static final int MONEY = 1;
		public static final int FRUIT = 2;
		public static final int ICE = 3;
		public static final int YOGURT = 4;
		public static final int JUICE = 5;
		public static final int CUP = 6;
		public static final int THERMOMETER = 7;
		public static final int TEMPERATURE = 8;
		public static final int PLAYER = 9;

		//KITCHEN
		public static final int KITCHEN = 10;
		public static final int REFRIDGERATOR = 11;
		public static final int JUICER = 12;
		public static final int BLENDER = 13;

		//MARKET
		public static final int MARKET = 14;
		public static final int BUY_FRUITS = 15;
		public static final int BUY_YOGURT = 16;
		public static final int BUY_CUPS = 17;

		//OFFICE
		public static final int OFFICE = 18;
		public static final int ADVERTISE = 19;
		public static final int STATISTICS = 20;
		public static final int SAVELOAD = 21;
		
		//STAND
		public static final int JOB = 22;
		public static final int START = 23;
	}
}

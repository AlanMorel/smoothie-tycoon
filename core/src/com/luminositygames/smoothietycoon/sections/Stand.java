package com.luminositygames.smoothietycoon.sections;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.screens.Tutorial.TutorialStage;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Window;

public class Stand implements Section {

	public static Rectangle STAND_RECT = new Rectangle(Constants.WIDTH / 2 - 500 / 2, 150, 500, 300); 
	
	private UserInterface ui;
	
	public Stand(UserInterface ui){
		this.ui = ui;
	}

	@Override
	public void render(float delta) {
		Image.draw("salesBackground", 0, 0);
	    //renderNight();
		Image.draw("player", Constants.WIDTH /2 - Image.get("player").getWidth() / 2, 250);
		Image.draw("stand", Constants.WIDTH /2 - Image.get("stand").getWidth() / 2, 175);
		Image.draw("slantedsign", 650, 175);
	}

	private void renderNight() {
		int percentage = ui.getGame().getIntermission().getPercentage() * 5 / 2;
		float alpha = 0;
		if (percentage < 256/2){
			alpha = 256 - percentage;
		} else {
			int percentage2 = 250 - percentage;
			alpha = 256 - percentage2;
		}
		//System.out.println(alpha);
		Image.rectangle(0, 0, Constants.WIDTH, 489, alpha, Color.BLACK);	
	}

	@Override
	public void update(float delta) {

	}

	public void renderTutorial(GameTween arrow, int stage) {
		float height = 90 + arrow.getValue();
		if (stage == TutorialStage.INTRO){
			Fonts.center("This is you. You run a smoothie stand.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.MONEY){
			Image.draw("upArrow", 12, height);
			Fonts.center("This is your money.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.FRUIT){
			Image.draw("upArrow", 541, height);
			Fonts.center("This is how much fruit you have.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.ICE){
			Image.draw("upArrow", 692, height);
			Fonts.center("This is how much ice you have.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.YOGURT){
			Image.draw("upArrow", 843, height);
			Fonts.center("This is how much yogurt you have.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.JUICE){
			Image.draw("upArrow", 993, height);
			Fonts.center("This is how much juice you have.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.CUP){
			Image.draw("upArrow", 1142, height);
			Fonts.center("This is how many cups you have.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.THERMOMETER){
			Image.draw("rightArrow", 1100 + arrow.getValue(), 250);
			Fonts.center("This is the thermometer.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.TEMPERATURE){
			Image.draw("rightArrow", 1100 + arrow.getValue(), 355);
			Fonts.center("It tells you the temperature.", 600, Fonts.BLACK_48);
		} else if (stage == TutorialStage.PLAYER){
			Image.draw("rightArrow", 450 + arrow.getValue(), 250);
			Fonts.center("You can adjust recipe/price by clicking on yourself.", 600, Fonts.BLACK_48);		
		} else if (stage == TutorialStage.JOB){
			Fonts.center("Your job is simple. Make money.", 600, Fonts.BLACK_48);			
		} else if (stage == TutorialStage.START){
			Fonts.center("Let's get started! Good luck.", 600, Fonts.BLACK_48);			
		}
	}

	@Override
	public void handleTouch() {
		if (ui.getWindow().isOpen()){
			if (ui.getWindow().isTouched()){
				ui.getWindow().handleTouch();
			} else {
				ui.getWindow().close();
			}
		} else {
			if (Image.get("stand").isTouched()){
				ui.getWindow().open(Window.STAND);
				//System.out.println("Stand hit.");
			}
		}
	}
}

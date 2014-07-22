package com.luminositygames.smoothietycoon.sections;

import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.screens.Tutorial.TutorialStage;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Window;

public class Market implements Section {
	
	public static Rectangle FRUIT_RECT = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300); 
	public static Rectangle YOGURT_RECT = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300); 
	public static Rectangle CUPS_RECT = new Rectangle(Constants.WIDTH / 2 - 600 / 2, 125, 600, 300); 
	
	private UserInterface ui;
	
	public Market (UserInterface ui){
		this.ui = ui;
	}
	
	@Override
	public void render(float delta) {
		Image.draw("market", 0, 0);
		Image.draw("fruitstand", 165, 170);
		Image.draw("yogurtstand", 520, 170);
		Image.draw("cupstand", 895, 170);
		
		Fonts.left("Fruits", 220, 425, Fonts.WHITE_36);
		Fonts.left("Yogurt", 570, 425, Fonts.WHITE_36);
		Fonts.left("Cups", 965, 425, Fonts.WHITE_36);
	}

	@Override
	public void update(float delta) {
		
	}
	
	public void renderTutorial(GameTween arrow, int stage) {
		if (stage == TutorialStage.MARKET){
			Fonts.center("This is the market.", 620, Fonts.BLACK_48);
		} else if (stage == TutorialStage.BUY_FRUITS){
			Fonts.center("You can buy fruit here.", 620, Fonts.BLACK_48);
			Image.draw("upArrow", 238, 500 + arrow.getValue());
		} else if (stage == TutorialStage.BUY_YOGURT){
			Image.draw("upArrow", 614, 500 + arrow.getValue());
			Fonts.center("You can buy yogurt here.", 620, Fonts.BLACK_48);
		} else if (stage == TutorialStage.BUY_CUPS){
			Image.draw("upArrow", 975, 500 + arrow.getValue());
			Fonts.center("You can buy cups here.", 620, Fonts.BLACK_48);
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
			if (Image.get("fruitstand").isTouched()){
				ui.getWindow().open(Window.FRUIT);
				//System.out.println("Fruit stand hit.");
			} else if (Image.get("yogurtstand").isTouched()){
				ui.getWindow().open(Window.YOGURT);
				//System.out.println("Yogurt stand hit.");
			} else if (Image.get("cupstand").isTouched()){
				ui.getWindow().open(Window.CUPS);
				//System.out.println("Cup stand hit.");
			}
		}
	}
}

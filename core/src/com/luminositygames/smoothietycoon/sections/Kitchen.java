package com.luminositygames.smoothietycoon.sections;

import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.screens.Tutorial.TutorialStage;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Window;

public class Kitchen implements Section {
	
	public static Rectangle REFRIDGERATOR_RECT = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 300); 
	public static Rectangle JUICER_RECT = new Rectangle(Constants.WIDTH / 2 - 625 / 2, 125, 625, 300); 
	public static Rectangle BLENDER_RECT = new Rectangle(Constants.WIDTH / 2 - 650 / 2, 125, 650, 225); 
	
	private UserInterface ui;
	
	public Kitchen(UserInterface ui){
		this.ui = ui;
	}
	
	@Override
	public void render(float delta) {
		Image.draw("kitchen", 0, 0);
		Image.draw("refridgerator", 150, 94);
		Image.draw("juicer", 750, 224);
		Image.draw("blender", 975, 208);
	}

	@Override
	public void update(float delta) {
		
	}
	
	public void renderTutorial(GameTween arrow, int stage) {
		if (stage == TutorialStage.KITCHEN){
			Fonts.center("This is your kitchen.", 620, Fonts.BLACK_48);
		} else if (stage == TutorialStage.REFRIDGERATOR){
			Fonts.center("You can get ice from your refridgerator.", 620, Fonts.BLACK_48);
			Image.draw("leftArrow", 400 + arrow.getValue(), 200);
		} else if (stage == TutorialStage.JUICER){
			Image.draw("upArrow", 820, 425 + arrow.getValue());
			Fonts.center("You make juice by juicing your fruit.", 620, Fonts.BLACK_48);
		} else if (stage == TutorialStage.BLENDER){
			Image.draw("upArrow", 1015, 425 + arrow.getValue());
			Fonts.center("You make smoothie with this blender.", 620, Fonts.BLACK_48);
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
			if (Image.get("refridgerator").isTouched()){
				ui.getWindow().open(Window.REFRIDGERATOR);
				//System.out.println("Refridgerator hit.");
			} else if (Image.get("juicer").isTouched()){
				ui.getWindow().open(Window.JUICER);
				//System.out.println("Juicer hit.");
			} else if (Image.get("blender").isTouched()){
				ui.getWindow().open(Window.BLENDER);
				//System.out.println("Blender hit.");
			}
		}
	}
}

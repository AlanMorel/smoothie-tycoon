package com.luminositygames.smoothietycoon.sections;

import com.luminositygames.smoothietycoon.screens.Tutorial.TutorialStage;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

public class Office implements Section {

	private UserInterface ui;
	
	public Office (UserInterface ui){
		this.ui = ui;
	}
	
	@Override
	public void render(float delta) {
		Image.draw("office", 0, 0);
	}

	@Override
	public void update(float delta) {
		
	}
	
	public void renderTutorial(GameTween arrow, int stage) {
		if (stage == TutorialStage.OFFICE){
			Fonts.center("This is your office.", 620, Fonts.BLACK_48);
		} else if (stage == TutorialStage.ADVERTISE){
			Fonts.center("You can advertise here.", 630, Fonts.BLACK_48);
			Image.draw("upArrow", 230, 500 + arrow.getValue());
		} else if (stage == TutorialStage.STATISTICS){
			Image.draw("upArrow", 614, 500 + arrow.getValue());
			Fonts.center("You can check statistics here.", 620, Fonts.BLACK_48);
		} else if (stage == TutorialStage.SAVELOAD){
			Image.draw("upArrow", 980, 500 + arrow.getValue());
			Fonts.center("You can save and load your game here.", 620, Fonts.BLACK_48);
		}
	}

	@Override
	public void handleTouch() {
	}
}

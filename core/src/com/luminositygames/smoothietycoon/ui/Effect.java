package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

public class Effect {

	public static int MISSING_SMOOTHIE = 0;
	public static int MISSING_FRUIT = 1;
	public static int MISSING_ICE = 2;
	public static int MISSING_YOGURT = 3;
	public static int MISSING_JUICE = 4;
	public static int MISSING_CUPS = 5;

	public static GameTween effect = new GameTween(0, GameTween.EFFECT);

	public static void update(float delta){
		effect.update(delta);
	}

	public static void render(int id){
		Color color = new Color(Color.RED);
		float alpha = 0.4f;
		float radius = effect.getValue() * 15 / 100 + 18;
		if (id == MISSING_SMOOTHIE){
			Image.circle(40, 382, radius, color, alpha);
		} else if (id == MISSING_FRUIT){
			Image.circle(609, 37, radius, color, alpha);
		} else if (id == MISSING_ICE){
			Image.circle(759, 37, radius, color, alpha);
		} else if (id == MISSING_YOGURT){
			Image.circle(909, 37, radius, color, alpha);
		} else if (id == MISSING_JUICE){
			Image.circle(1059, 37, radius, color, alpha);
		} else if (id == MISSING_CUPS){
			Image.circle(1209, 37, radius, color, alpha);
		}
	}
}

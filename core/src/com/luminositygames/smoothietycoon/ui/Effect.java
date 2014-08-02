package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.graphics.Color;
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

public class Effect {

	private static GameTween effect = new GameTween(0, GameTween.EFFECT);

	public static final byte SMOOTHIE = 0;
	public static final byte FRUIT = 1;
	public static final byte ICE = 2;
	public static final byte YOGURT = 3;
	public static final byte JUICE = 4;
	public static final byte CUPS = 5;

	public static void update(float delta){
		effect.update(delta);
	}

	public static void render(byte id){
		Color color = Color.RED;
		float alpha = effect.getValue() * 3 /1000;
		float radius = effect.getValue() * 14 / 100 + 18;

		if (id == SMOOTHIE){
			Image.circle(40, 382, radius, color, alpha);
		} else if (id == FRUIT){
			Image.circle(610, 37, radius, color, alpha);
		} else if (id == ICE){
			Image.circle(760, 37, radius, color, alpha);
		} else if (id == YOGURT){
			Image.circle(910, 37, radius, color, alpha);
		} else if (id == JUICE){
			Image.circle(1060, 37, radius, color, alpha);
		} else if (id == CUPS){
			Image.circle(1210, 37, radius, color, alpha);
		}
	}
}

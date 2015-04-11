package com.luminositygames.smoothietycoon.ui;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.entities.Container;
import com.luminositygames.smoothietycoon.entities.Player;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Effects {

	private static GameTween effect = new GameTween(0, GameTween.EFFECT);

	private static final byte SMOOTHIE = 0;
	private static final byte FRUIT = 1;
	private static final byte ICE = 2;
	private static final byte YOGURT = 3;
	private static final byte JUICE = 4;
	private static final byte CUPS = 5;

	public static void update(float delta){
		effect.update(delta);
	}

	public static void render(Game game) {
		Player player = game.getPlayer();
		Container container = game.getContainer();
		if (container.getServings() <= 1){
			Effects.render(Effects.SMOOTHIE);
		}
		if (player.getFruits() <= 9){
			Effects.render(Effects.FRUIT);
		}
		if (player.getIce() <= 9){
			Effects.render(Effects.ICE);
		}
		if (player.getYogurt() <= 9){
			Effects.render(Effects.YOGURT);
		}
		if (player.getJuice() <= 9){
			Effects.render(Effects.JUICE);
		}
		if (player.getCups() <= 3){
			Effects.render(Effects.CUPS);
		}
	}

	private static void render(byte id){
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

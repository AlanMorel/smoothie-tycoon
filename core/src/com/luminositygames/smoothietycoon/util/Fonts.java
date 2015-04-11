package com.luminositygames.smoothietycoon.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Main;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Fonts {

	public static HashMap<Integer, BitmapFont> fonts;

	public static BitmapFont get(Color color, int size) {
		BitmapFont font = fonts.get(size);
		if (font == null){
			font = addNewFont(size);
		}
		font.setColor(color);
		return font;
	}

	private static BitmapFont addNewFont(int size){
		FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont font = fontGen.generateFont(parameter);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.setScale(1, -1);
		fonts.put(size, font);
		return font;
	}

	public static void left(String str, float x, float y, Color color, int size){
		BitmapFont font = get(color, size);
		Fonts.draw(str, x, y, font);
	}

	public static void right(String str, float x, float y, Color color, int size){
		BitmapFont font = get(color, size);
		float width = font.getBounds(str).width;
		Fonts.draw(str, x - width, y, font);
	}

	public static void center(String str, float y, Color color, int size){
		BitmapFont font = get(color, size);
		float width = font.getBounds(str).width;
		Fonts.draw(str, Constants.WIDTH / 2 - width / 2, y, font);
	}

	public static void center(String str, float about, float y, Color color, int size){
		BitmapFont font = get(color, size);
		float width = font.getBounds(str).width;
		Fonts.draw(str, about - width / 2, y, font);
	}

	private static void draw(String str, float x, float y, BitmapFont font){
		Image.batch.begin();
		font.draw(Image.batch, str, x, y);
		Image.batch.end();
	}

	public static boolean isTouched(String str, float x, float y, Color color, int size){
		if (!Gdx.input.justTouched()){
			return false;
		}
		TextBounds bounds = get(color, size).getBounds(str);
		Rectangle rect = new Rectangle(x, y, bounds.width, -bounds.height);
		return rect.contains(Main.getX(), Main.getY());
	}
}

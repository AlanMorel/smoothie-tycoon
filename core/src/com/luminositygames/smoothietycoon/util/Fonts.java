package com.luminositygames.smoothietycoon.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Fonts {

	public static HashMap<Integer, BitmapFont> fonts;

	public static final byte BLACK_36 = 0;
	public static final byte WHITE_36 = 1;

	public static final byte BLACK_48 = 2;
	public static final byte WHITE_48 = 3;


	public static void load(int ID, Color color, int size){
		FreeTypeFontGenerator font_gen = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont font = font_gen.generateFont(parameter);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear,TextureFilter.Linear);
		font.setColor(new Color(color));
		font.setScale(1, -1);
		fonts.put(ID, font);
	}

	public static BitmapFont get(int key) {
		return fonts.get(key);
	}

	public static void left(String str, float x, float y, int fontSize){
		BitmapFont font = get(fontSize);
		Fonts.draw(Image.batch, str, x, y, font);
	}

	public static void center(String str, float y, int fontSize){
		BitmapFont font = get(fontSize);
		float width = font.getBounds(str).width;
		Fonts.draw(Image.batch, str, Constants.WIDTH / 2 - width / 2, y, font);
	}

	public static void center(String str, float about, float y, int fontSize){
		BitmapFont font = get(fontSize);
		float width = font.getBounds(str).width;
		Fonts.draw(Image.batch, str, about - width / 2, y, font);
	}

	public static void right(String str, float x, float y, int fontSize){
		BitmapFont font = get(fontSize);
		float width = font.getBounds(str).width;
		Fonts.draw(Image.batch, str, x - width, y, font);
	}

	public static void draw(Batch batch, CharSequence str, float x, float y, BitmapFont font){
		Image.batch.begin();
		font.draw(Image.batch, str, x, y);
		Image.batch.end();
	}

	public static boolean isTouched (String str, float x, float y, int fontSize){
		if (!Gdx.input.justTouched()){
			return false;
		}

		BitmapFont font = get(fontSize);
		float width = font.getBounds(str).width;
		float height = font.getBounds(str).height;
		Rectangle rect = new Rectangle(x, y, width, -height);

		float touchedX = SmoothieTycoon.getX();
		float touchedY = SmoothieTycoon.getY();

		if (rect.contains(touchedX, touchedY)){
			return true;
		}
		return false;
	}
}

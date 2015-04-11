package com.luminositygames.smoothietycoon;

import java.util.HashMap;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Songs;
import com.luminositygames.smoothietycoon.util.Sounds;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Assets {

	public static void load(){
		loadImages();
		loadMusic();
		loadSounds();
		loadFonts();
	}

	private static void loadImages() {
		Image.images = new HashMap<String, Image>();
		Image.batch = new SpriteBatch();
		Image.renderer = new ShapeRenderer();

		Image.load("splash");
		Image.load("mainmenu");
		Image.load("credits");
		Image.load("salesBackground");
		Image.load("stand");
		Image.load("slantedsign");
		Image.load("logotext");
		Image.load("player");
		Image.load("playbutton");
		Image.load("playbutton2");
		Image.load("upArrow");
		Image.load("downArrow");
		Image.load("fruit");
		Image.load("ice");
		Image.load("yogurt");
		Image.load("juice");
		Image.load("cup");
		Image.load("leftArrow");
		Image.load("rightArrow");
		Image.load("dollarSign");
		Image.load("thermometer");
		Image.load("kitchen");
		Image.load("market");
		Image.load("office");
		Image.load("advertise");
		Image.load("statistics");
		Image.load("upgrades");
		Image.load("upgradesIcon");
		Image.load("refridgerator");
		Image.load("juicer");
		Image.load("blender");
		Image.load("fruitstand");
		Image.load("yogurtstand");
		Image.load("cupstand");
		Image.load("yogurtinverted");
		Image.load("ad");
		Image.load("statisticsicon");
		Image.load("swagR");
		Image.load("swagL");
		Image.load("achievement");
		Image.load("tip");
		Image.load("customerOverlay");
		Image.load("event");
		Image.load("cowR");
		Image.load("cowL");
		Image.load("winterR");
		Image.load("winterL");
		Image.load("policeR");
		Image.load("policeL");
		Image.load("pirateR");
		Image.load("pirateL");
		Image.load("brownHatR");
		Image.load("brownHatL");
		Image.load("fruitSeller");
		Image.load("yogurtSeller");
		Image.load("cupSeller");
		Image.addHover("playbutton", "playbutton2");
	}

	private static void loadMusic() {
		Songs.songs = new HashMap<String, Music>();
		Songs.load("gameplay", 0.25f);
		Songs.load("mainmenu", 0.25f);
	}

	private static void loadSounds() {
		Sounds.sounds = new HashMap<String, Sound>();
		Sounds.load("achievement", "mp3");
		Sounds.load("purchase", "mp3");
		Sounds.load("supplies", "wav");
		Sounds.load("refill", "wav");
		Sounds.load("morning", "mp3");
		Sounds.load("tip", "wav");
		Sounds.load("event", "wav");
		Sounds.load("adPurchase", "mp3");
	}

	private static void loadFonts() {
		Fonts.fonts = new HashMap<Integer, BitmapFont>();
	}
}

package com.luminositygames.smoothietycoon;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.luminositygames.smoothietycoon.screens.Gameplay;
import com.luminositygames.smoothietycoon.screens.MainMenu;
import com.luminositygames.smoothietycoon.screens.Screen2;
import com.luminositygames.smoothietycoon.screens.Splash;
import com.luminositygames.smoothietycoon.screens.Tutorial;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.KeyProcessor;
import com.luminositygames.smoothietycoon.util.Songs;
import com.luminositygames.smoothietycoon.util.Sounds;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class SmoothieTycoon extends ApplicationAdapter {

	public static OrthographicCamera camera;
	public static Screen2 screen;
	public static Screen2 mainMenu;
	public static Screen2 splash;
	public static Screen2 tutorial;
	public static Screen2 gameplay;
	public static Random random;
	public static NumberFormat formatter;

	@Override
	public void create () {
		loadGame();
		loadAssets();
		loadScreens();
		setScreen(splash);
		Gdx.input.setInputProcessor(new KeyProcessor());
		Gdx.input.setCatchBackKey(true);
	}

	private void loadGame(){
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.WIDTH, Constants.HEIGHT);
		random = new Random();
		formatter = NumberFormat.getCurrencyInstance();
	}

	private void loadAssets() {
		loadImages();
		loadMusic();
		loadSounds();
		loadFonts();
	}

	private void loadImages(){
		Image.images = new HashMap<String, Image>();
		Image.batch = new SpriteBatch();
		Image.renderer = new ShapeRenderer();

		Image.load("splash");
		Image.load("mainmenu");
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
		Image.load("cowR");
		Image.load("cowL");
		Image.load("winterR");
		Image.load("winterL");
		Image.load("policeR");
		Image.load("policeL");
		Image.load("pirateR");
		Image.load("pirateL");
		Image.load("fruitSeller");
		Image.load("yogurtSeller");
		Image.load("cupSeller");
		Image.addHover("playbutton", "playbutton2");
	}

	private void loadMusic(){
		Songs.songs = new HashMap<String, Music>();
		Songs.load("gameplay", 0.5f);
	}

	private void loadSounds(){
		Sounds.sounds = new HashMap<String, Sound>();
		Sounds.load("achievement", "mp3");
		Sounds.load("purchase", "mp3");
		Sounds.load("supplies", "wav");
		Sounds.load("refill", "wav");
		Sounds.load("morning", "mp3");
		Sounds.load("tip", "wav");
		Sounds.load("adPurchase", "mp3");
	}

	private void loadFonts() {
		Fonts.fonts = new HashMap<Integer, BitmapFont>();

		Fonts.load(Fonts.BLACK_36, Color.BLACK, 36);
		Fonts.load(Fonts.WHITE_36, Color.WHITE, 36);

		Fonts.load(Fonts.BLACK_48, Color.BLACK, 48);
		Fonts.load(Fonts.WHITE_48, Color.WHITE, 48);
	}

	private void loadScreens() {
		splash = new Splash();
		mainMenu = new MainMenu();
		tutorial = new Tutorial();
		gameplay = new Gameplay();
	}

	public static void setScreen(Screen2 s){
		screen = s;
		screen.load();
	}

	public static float getX(){
		Vector3 vector = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(vector);
		return vector.x;
	}

	public static float getY(){
		Vector3 vector = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(vector);
		return vector.y;
	}

	public static int fixPercentage(int percentage){
		if (percentage < 0){
			return 0;
		} else if (percentage > 100) {
			return 100;
		}
		return percentage;
	}

	public static String format(double amount){
		return formatter.format(amount);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		Image.batch.setProjectionMatrix(camera.combined);
		Image.renderer.setProjectionMatrix(SmoothieTycoon.camera.combined);
		Gdx.graphics.setTitle(Constants.NAME + " - " + Gdx.graphics.getFramesPerSecond() + " FPS");
		screen.update(1 / Constants.FRAMES_PER_SECOND);
		screen.render(1 / Constants.FRAMES_PER_SECOND);
	}
}

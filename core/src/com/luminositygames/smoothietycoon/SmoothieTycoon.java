package com.luminositygames.smoothietycoon;

import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.luminositygames.smoothietycoon.screens.Gameplay;
import com.luminositygames.smoothietycoon.screens.Screen2;
import com.luminositygames.smoothietycoon.screens.Tutorial;
import com.luminositygames.smoothietycoon.screens.MainMenu;
import com.luminositygames.smoothietycoon.screens.Splash;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.KeyProcessor;

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
	
	public static Random rand;
	
	public static Screen2 screen;
	public static Screen2 mainMenu;
	public static Screen2 splash;
	public static Screen2 tutorial;
	public static Screen2 gameplay;
	
	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		loadGame();
		loadAssets();
		loadScreens();
		setScreen(splash);
	}
	
	private void loadGame(){
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.WIDTH, Constants.HEIGHT);
		rand = new Random();
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
		Image.load("refridgerator");
		Image.load("juicer");
		Image.load("blender");
		Image.load("hatR");
		Image.load("hatL");
		Image.load("fruitstand");
		Image.load("yogurtstand");
		Image.load("cupstand");
		Image.load("yogurtinverted");
		Image.addHover("playbutton", "playbutton2");
	}
	
	private void loadMusic(){
		
	}
	
	private void loadSounds(){

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
		Gdx.input.setInputProcessor(new KeyProcessor());
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

	public static void setScreen(Screen2 scr){
		screen = scr;
		screen.load();
	}
	
	public static float getX(){
		Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(pos);
		return pos.x;
	}
	
	public static float getY(){
		Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(pos);
		return pos.y;
	}
}

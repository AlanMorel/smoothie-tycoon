package com.luminositygames.smoothietycoon;

import java.text.NumberFormat;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.luminositygames.smoothietycoon.screens.Gameplay;
import com.luminositygames.smoothietycoon.screens.MainMenu;
import com.luminositygames.smoothietycoon.screens.Screen2;
import com.luminositygames.smoothietycoon.screens.Splash;
import com.luminositygames.smoothietycoon.screens.Tutorial;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.KeyProcessor;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Main extends ApplicationAdapter {

	public static OrthographicCamera camera;
	public static Random random;
	public static NumberFormat formatter;

	public static Screen2 screen;
	public static Screen2 splash;
	public static Screen2 mainMenu;
	public static Screen2 tutorial;
	public static Screen2 gameplay;

	@Override
	public void create () {
		loadGame();
		Assets.load();
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
		String formatted = formatter.format(amount);
		if (amount > 0){
			return formatted;
		} else {
			return "-" + formatted.substring(1, formatted.length() - 1);
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		Image.batch.setProjectionMatrix(camera.combined);
		Image.renderer.setProjectionMatrix(camera.combined);
		Gdx.graphics.setTitle(Constants.NAME + " - " + Gdx.graphics.getFramesPerSecond() + " FPS");
		screen.handleTouch();
		screen.update(1 / Constants.FRAMES_PER_SECOND);
		screen.render(1 / Constants.FRAMES_PER_SECOND);
	}
}

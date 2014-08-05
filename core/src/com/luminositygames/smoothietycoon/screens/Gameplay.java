package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.ui.Section;
import com.luminositygames.smoothietycoon.ui.UserInterface;
import com.luminositygames.smoothietycoon.ui.Windows;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Songs;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Gameplay implements Screen2 {

	private Game game;
	private Section section;
	private UserInterface ui;

	@Override
	public void load() {
		this.game = new Game();
		this.section = new Section();
		this.ui = new UserInterface();
		Songs.play("gameplay");
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		game.render(section, delta);
		ui.render(game);
	}

	@Override
	public void update(float delta) {
		handleTouch();
		game.update(delta);
		if (Image.get("leftArrow").isTouched()){
			handleLeft();
		} else if (Image.get("rightArrow").isTouched()){
			handleRight();
		}
	}

	@Override
	public void keyPress(int keycode) {
		if (keycode == Keys.LEFT){
			handleLeft();
		} else if (keycode == Keys.RIGHT){
			handleRight();
		} else if (keycode == Keys.SPACE || keycode == Keys.ESCAPE){
			game.togglePause();
		}
	}

	private void handleTouch() {
		if (!Gdx.input.justTouched()){
			return;
		}
		if (Windows.isOpen()){
			if (Windows.isTouched()){
				Windows.handleTouch(game);
			} else {
				Windows.close();
			}
		} else {
			section.handleTouch();
		}
	}

	public void handleLeft(){
		Windows.close();
		if (section.isStand()){
			section.setSection(Section.OFFICE);
		} else if (section.isKitchen()){
			section.setSection(Section.STAND);
		} else if (section.isMarket()){
			section.setSection(Section.KITCHEN);
		} else if (section.isOffice()){
			section.setSection(Section.MARKET);
		}
	}

	public void handleRight(){
		Windows.close();
		if (section.isStand()){
			section.setSection(Section.KITCHEN);
		} else if (section.isKitchen()){
			section.setSection(Section.MARKET);
		} else if (section.isMarket()){
			section.setSection(Section.OFFICE);
		} else if (section.isOffice()){
			section.setSection(Section.STAND);
		}
	}
}

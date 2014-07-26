package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.ui.Section;
import com.luminositygames.smoothietycoon.ui.UserInterface;
import com.luminositygames.smoothietycoon.ui.Windows;
import com.luminositygames.smoothietycoon.ui.Windows.Window;
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

public class Gameplay implements Screen2 {

	private Game game;
	private Section section;
	private UserInterface ui;
	private Windows window;

	@Override
	public void load() {
		this.game = new Game();
		this.section = new Section();
		this.ui = new UserInterface();
		this.window = new Windows();
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		game.render(section, delta);
		ui.render(game);
		window.render(game);
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

	private void handleTouch() {
		if (!Gdx.input.justTouched()){
			return;
		}
		if (window.isOpen()){
			if (window.isTouched()){
				window.handleTouch(game);
			} else {
				window.close();
			}
		} else {
			if (section.isStand()){
				if (Image.get("stand").isTouched()){
					window.open(Window.STAND);
				}
			} else if (section.isKitchen()){
				if (Image.get("refridgerator").isTouched()){
					window.open(Window.REFRIDGERATOR);
				} else if (Image.get("juicer").isTouched()){
					window.open(Window.JUICER);
				} else if (Image.get("blender").isTouched()){
					window.open(Window.BLENDER);
				}
			} else if (section.isMarket()){
				if (Image.get("fruitstand").isTouched()){
					window.open(Window.FRUIT);
				} else if (Image.get("yogurtstand").isTouched()){
					window.open(Window.YOGURT);
				} else if (Image.get("cupstand").isTouched()){
					window.open(Window.CUPS);
				}
			}
		}
	}

	@Override
	public void keyPress(int keycode) {
		if (keycode == Keys.LEFT){
			handleLeft();
		} else if (keycode == Keys.RIGHT){
			handleRight();
		}
	}

	public void handleLeft(){
		window.close();
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
		window.close();
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

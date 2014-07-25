package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.sections.Kitchen;
import com.luminositygames.smoothietycoon.sections.Market;
import com.luminositygames.smoothietycoon.sections.Office;
import com.luminositygames.smoothietycoon.sections.Section;
import com.luminositygames.smoothietycoon.sections.Stand;
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

public class Gameplay implements Screen2{

	private Game game;
	private UserInterface ui;
	private Windows window;
	private Section section;
	private Stand stand;
	private Kitchen kitchen;
	private Market market;
	private Office office;

	@Override
	public void load() {
		this.game = new Game();
		this.ui = new UserInterface();
		this.window = new Windows(game);
		this.stand = new Stand();
		this.kitchen = new Kitchen();
		this.market = new Market();
		this.office = new Office();
		this.section = stand;
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		if (section.equals(stand)){
			game.renderCustomers();
		}
		game.renderEffects();
		ui.render(game);
		window.render();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched()){
			handleTouch();
		}
		game.update(delta);
		if (Image.get("leftArrow").isTouched()){
			handleLeft();
		} else if (Image.get("rightArrow").isTouched()){
			handleRight();
		}
	}

	private void handleTouch() {
		if (window.isOpen()){
			if (window.isTouched()){
				window.handleTouch();
			} else {
				window.close();
			}
		} else {
			if (section == stand){
				if (Image.get("stand").isTouched()){
					window.open(Window.STAND);
				}
			} else if (section == kitchen){
				if (Image.get("refridgerator").isTouched()){
					window.open(Window.REFRIDGERATOR);
				} else if (Image.get("juicer").isTouched()){
					window.open(Window.JUICER);
				} else if (Image.get("blender").isTouched()){
					window.open(Window.BLENDER);
				}
			} else if (section == market){
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
		if (section.equals(stand)){
			section = office;
		} else if (section.equals(kitchen)){
			section = stand;
		} else if (section.equals(market)){
			section = kitchen;
		} else if (section.equals(office)){
			section = market;
		}
	}

	public void handleRight(){
		window.close();
		if (section.equals(stand)){
			section = kitchen;
		} else if (section.equals(kitchen)){
			section = market;
		} else if (section.equals(market)){
			section = office;
		} else if (section.equals(office)){
			section = stand;
		}
	}
}

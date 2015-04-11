package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.luminositygames.smoothietycoon.Game;
import com.luminositygames.smoothietycoon.ui.UserInterface;
import com.luminositygames.smoothietycoon.ui.sections.Section;
import com.luminositygames.smoothietycoon.ui.windows.Window;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Songs;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Gameplay implements Screen2 {

	private Game game;
	private UserInterface ui;

	@Override
	public void load() {
		game = new Game();
		ui = new UserInterface();
		Songs.play("gameplay");
		Section.setSection(Section.STAND);
	}

	@Override
	public void render(float delta) {
		Section.getSection().render(delta);
		game.render(delta);
		ui.render(game);
	}

	@Override
	public void update(float delta) {
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

	@Override
	public void handleTouch() {
		if (!Gdx.input.justTouched()){
			return;
		}
		if (Window.isOpen()){
			if (Window.isTouched()){
				Window.getWindow().handle(game);
			} else {
				Window.close();
			}
		} else {
			Section.getSection().handle();
		}
	}

	private void handleLeft(){
		Window.close();
		if (Section.getSection().equals(Section.STAND)){
			Section.setSection(Section.OFFICE);
		} else if (Section.getSection().equals(Section.KITCHEN)){
			Section.setSection(Section.STAND);
		} else if (Section.getSection().equals(Section.MARKET)){
			Section.setSection(Section.KITCHEN);
		} else if (Section.getSection().equals(Section.OFFICE)){
			Section.setSection(Section.MARKET);
		}
	}

	private void handleRight(){
		Window.close();
		if (Section.getSection().equals(Section.STAND)){
			Section.setSection(Section.KITCHEN);
		} else if (Section.getSection().equals(Section.KITCHEN)){
			Section.setSection(Section.MARKET);
		} else if (Section.getSection().equals(Section.MARKET)){
			Section.setSection(Section.OFFICE);
		} else if (Section.getSection().equals(Section.OFFICE)){
			Section.setSection(Section.STAND);
		}
	}
}

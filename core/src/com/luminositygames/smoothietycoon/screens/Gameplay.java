package com.luminositygames.smoothietycoon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.luminositygames.smoothietycoon.Screen2;
import com.luminositygames.smoothietycoon.game.Game;
import com.luminositygames.smoothietycoon.sections.Kitchen;
import com.luminositygames.smoothietycoon.sections.Market;
import com.luminositygames.smoothietycoon.sections.Office;
import com.luminositygames.smoothietycoon.sections.Section;
import com.luminositygames.smoothietycoon.sections.Stand;
import com.luminositygames.smoothietycoon.sections.UserInterface;
import com.luminositygames.smoothietycoon.util.Image;

public class Gameplay implements Screen2{

	private Game game;
	private UserInterface ui;
	private Section section;
	private Stand stand;
	private Kitchen kitchen;
	private Market market;
	private Office office;
	
	@Override
	public void load() {
		game = new Game();
		ui = new UserInterface(game);
		stand = new Stand(ui);
		kitchen = new Kitchen(ui);
		market = new Market(ui);
		office = new Office(ui);
		section = stand;
	}

	@Override
	public void render(float delta) {
		section.render(delta);
		if (section.equals(stand)){
			game.getCustomers().render();
		}
		ui.render();
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched()){
			section.handleTouch();
		}
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
		}
	}

	public void handleLeft(){
		//System.out.println("Left arrow hit.");
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
		//System.out.println("Right arrow hit.");
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

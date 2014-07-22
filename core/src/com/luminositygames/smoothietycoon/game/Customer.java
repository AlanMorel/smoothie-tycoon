package com.luminositygames.smoothietycoon.game;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.util.Countdown;
import com.luminositygames.smoothietycoon.util.GameTween;
import com.luminositygames.smoothietycoon.util.Image;

public class Customer {
	
	private static final int CENTER = Constants.WIDTH / 2 - 100 / 2;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	
	private int x;
	private int dx;
	private boolean buying;
	private Color color;
	private Color buyColor;
	private int side;
	private Countdown purchase;
	private GameTween hat;
	
	public Customer(boolean b, int s){	
		hat = new GameTween(-2, GameTween.HAT);
		this.purchase = new Countdown(getPurchaseDelay(), false);
		this.side = s;
		this.buying = b;
		setX();
		setdX();
		setBuyColor();
		setColor();
	}
	
	private void setColor() {
		float r = SmoothieTycoon.rand.nextFloat();
		float g = SmoothieTycoon.rand.nextFloat();
		float b = SmoothieTycoon.rand.nextFloat();
		
		Color randomColor = new Color(r, g, b, b);
		color = randomColor;
	}
	
	public int getPurchaseDelay(){
		return SmoothieTycoon.rand.nextInt(1500) + 500;
	}

	private void setBuyColor() {
		buyColor = new Color(buying ? Color.GREEN : Color.RED);
	}

	private void setX() {
		if (side == LEFT){
			x = -102;
		} else if (side == RIGHT){
			x = Constants.WIDTH + 2;
		}
	}

	private void setdX() {
		if (side == LEFT){
			dx = 4;
		} else if (side == RIGHT){
			dx = -4;
		}
	}

	public void render() {
		Image.rectangle(x, 300, 100, 250, 1.0f, buyColor);
		Image.rectangle(x, 300, 100, 240, 1.0f, color);
		if (side == LEFT){
			Image.draw("hatL", x - 22, 227 + hat.getValue());
		} else if (side == RIGHT){
			Image.draw("hatR", x - 20, 227 + hat.getValue());
		}
	}
	
	public void update(float delta) {
		hat.update(delta);
		x += dx;
	}
	
	public boolean isBuying(){
		if (x == CENTER && buying){
			if (!purchase.hasStarted()){
				purchase.start();
				dx = 0;
			} else if (purchase.isCompleted()){
				setdX();
				return true;
			}
		} 
		return false;
	}
	
	public boolean hasLeft(){
		if (x > Constants.WIDTH + 10){
			return true;
		} else if (x < -110){
			return true;
		} else {
			return false;
		}
	}
}

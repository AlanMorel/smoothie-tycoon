package com.luminositygames.smoothietycoon.util;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.screens.Screen2;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class GameTween {

	public static final int SPLASH = 1;
	public static final int LOGO = 2;
	public static final int ARROW = 3;
	public static final int HAT = 4;
	public static final int EFFECT = 5;

	private TweenManager tweenManager;

	private float value;

	public GameTween(float value, int type){
		this.value = value;
		this.tweenManager = new TweenManager();
		Tween.registerAccessor(GameTween.class, new GameTweenAccessor());
		setTweenTo(type);
	}

	public float getValue(){
		return value;
	}

	public void setValue(float v){
		value = v;
	}

	public void update(float delta) {
		tweenManager.update(delta);
	}

	public void setTweenTo(int type){
		if (type == GameTween.SPLASH){
			Tween.to(this, GameTweenAccessor.VALUE, 1f).target(1).repeatYoyo(1, Constants.SPLASH_DURATION).setCallback(setScreenTo(SmoothieTycoon.mainMenu)).start(tweenManager);
		} else if (type == GameTween.LOGO){
			Tween.to(this, GameTweenAccessor.VALUE, 1f).target(75).repeatYoyo(-1, 0).start(tweenManager);
		} else if (type == GameTween.ARROW){
			Tween.to(this, GameTweenAccessor.VALUE, 0.5f).target(20).repeatYoyo(-1, 0).start(tweenManager);
		} else if (type == GameTween.HAT){
			Tween.to(this, GameTweenAccessor.VALUE, 0.5f).target(2).repeatYoyo(-1, 0).start(tweenManager);
		} else if (type == GameTween.EFFECT){
			Tween.to(this, GameTweenAccessor.VALUE, 0.5f).target(100).repeatYoyo(Tween.INFINITY, 0).start(tweenManager);
		}
	}

	private TweenCallback setScreenTo(final Screen2 scr){
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				SmoothieTycoon.setScreen(scr);
			}
		};
		return tweenCallback;
	}
}

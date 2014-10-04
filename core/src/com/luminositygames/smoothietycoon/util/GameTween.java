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

	public static final byte SPLASH = 0;
	public static final byte LOGO = 1;
	public static final byte ARROW = 2;
	public static final byte HAT = 3;
	public static final byte EFFECT = 4;

	private TweenManager tweenManager;
	private float value;

	public GameTween(float value, byte type){
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

	public void setTweenTo(byte type){
		if (type == GameTween.SPLASH){
			Tween.to(this, 0, 1f).target(1).repeatYoyo(1, Constants.SPLASH_DURATION).setCallback(setScreenTo(SmoothieTycoon.mainMenu)).start(tweenManager);
		} else if (type == GameTween.LOGO){
			Tween.to(this, 0, 1f).target(75).repeatYoyo(-1, 0).start(tweenManager);
		} else if (type == GameTween.ARROW){
			Tween.to(this, 0, 0.5f).target(20).repeatYoyo(-1, 0).start(tweenManager);
		} else if (type == GameTween.HAT){
			Tween.to(this, 0, 0.5f).target(10).repeatYoyo(-1, 0).start(tweenManager);
		} else if (type == GameTween.EFFECT){
			Tween.to(this, 0, 0.5f).target(100).repeatYoyo(Tween.INFINITY, 0).start(tweenManager);
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

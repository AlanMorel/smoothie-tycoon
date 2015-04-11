package com.luminositygames.smoothietycoon.util;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class GameTweenAccessor implements TweenAccessor<GameTween> {

	@Override
	public int getValues(GameTween target, int tweenType, float[] returnValues) {
		returnValues[0] = target.getValue();
		return 1;
	}

	@Override
	public void setValues(GameTween target, int tweenType, float[] newValues) {
		target.setValue(newValues[0]);
	}
}
package com.luminositygames.smoothietycoon.util;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Countdown {

	private boolean started;
	private long duration;
	private long start;

	public Countdown (long duration, boolean startImmediately){
		this.duration = duration;
		if (startImmediately){
			this.start = TimeUtils.millis();
			this.started = true;
		}
	}

	public boolean isCompleted(){
		return TimeUtils.millis() - start >= duration && started;
	}

	public boolean hasStarted() {
		return started;
	}

	public void start() {
		start = TimeUtils.millis();
		started = true;
	}

	public int getPercentage(){
		if (!hasStarted()){
			return 0;
		} else if (isCompleted()){
			return 100;
		} else {
			int percentage = (int)((TimeUtils.millis() - start) * 100 / duration);
			return percentage;
		}
	}
}

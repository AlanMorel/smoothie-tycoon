package com.luminositygames.smoothietycoon.util;

import com.badlogic.gdx.utils.TimeUtils;

public class Countdown {

	private long duration; 
	private long start;

	public Countdown (long duration, boolean startImmediately){
		this.duration = duration;
		if (startImmediately){
			this.start = TimeUtils.millis();
		}
	}

	public boolean isCompleted(){
		long time = TimeUtils.millis();
		if (time - start >= duration){
			return true;
		}
		return false;
	}

	public void start() {
		this.start = TimeUtils.millis();
	}
	
	public boolean hasStarted() {
		if (start != 0){
			return true;
		}
		return false;
	}
	
	public int getPercentage(){
		int percentage =  (int)((TimeUtils.millis() - start) * 100 / duration);
		if (!hasStarted()){
			return 0;
		} else if (isCompleted()){
			return 100;
		} else {
			return percentage;
		}
	}
}

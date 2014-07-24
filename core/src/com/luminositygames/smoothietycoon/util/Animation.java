package com.luminositygames.smoothietycoon.util;

import java.util.ArrayList;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Animation {

	private ArrayList<Frame> frames;
	private Frame frame;
	
	private	int counter;
	
	public Animation (){
		frames = new ArrayList<Frame>();
		counter = 0;
	}
	
	public void addFrame(String image, long delay){
		Frame newFrame = new Frame(image, delay);
		if (frame == null){
			frame = newFrame;
			frame.getDelay().start();
		}
		frames.add(newFrame);
	}
	
	public void draw(float x, float y){
		Image.draw(frame.getImage(), x, y);
	}
	
	public void update(){
		if (frame.getDelay().isCompleted()){
			counter += 1;
			frame = frames.get(counter % frames.size());
			frame.getDelay().start();
		}
	}

	private class Frame {
		
		private String image;
		private Countdown delay;
		
		public Frame(String image, long delay){
			this.image = image;
			this.delay = new Countdown(delay, false);
		}
		
		public String getImage(){
			return image;
		}
		
		public Countdown getDelay(){
			return delay;
		}
	}
}

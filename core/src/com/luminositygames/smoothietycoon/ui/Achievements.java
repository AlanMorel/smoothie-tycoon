package com.luminositygames.smoothietycoon.ui;

import java.util.HashMap;


/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Achievements {

	public static HashMap<Integer, Achievement> achievements;

	public static int BUSINESSMAN = 0;

	public static void load() {
		achievements = new HashMap<Integer, Achievement>();
		achievements.put(BUSINESSMAN, new Achievement("Businessman: First cup sold"));
	}

	public static void unlock(int id){
		Achievement a = achievements.get(id);
		if (a.isUncompleted()){
			a.complete();
		}
	}

	public static class Achievement {

		private String message;
		private boolean completed;

		public Achievement(String message){
			this.message = message;
			this.completed = false;
		}

		public boolean isUncompleted(){
			return !completed;
		}

		public void complete(){
			completed = true;
			Notifications.show(message, Notifications.ACHIEVEMENT);
		}
	}
}
package com.luminositygames.smoothietycoon.ui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.util.Countdown;
import com.luminositygames.smoothietycoon.util.Fonts;
import com.luminositygames.smoothietycoon.util.Image;
import com.luminositygames.smoothietycoon.util.Sounds;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Notifications {

	public static final String TIP = "tip";
	public static final String ACHIEVEMENT = "achievement";
	public static final String EVENT = "event";

	private static ArrayList<Notification> notifications;

	public static void load(){
		notifications = new ArrayList<Notification>();
	}

	public static void show(String message, String type){
		notifications.add(new Notification(message, type));
	}

	public static void render(){
		for (Notification notif : notifications){
			notif.render();
			return;
		}
	}

	public static void update(){
		for (int i = 0; i < notifications.size(); i++) {
			Notification notif = notifications.get(i);
			if (!notif.keepDisplaying()){
				notifications.remove(notif);
			}
		}
	}

	private static class Notification {

		private static final int DURATION = 7 * 1000;
		private static final float ALPHA = 0.75f;
		private static final Color COLOR = Color.WHITE;

		private String message;
		private Countdown duration;
		private String type;

		private Notification(String message, String type){
			this.message = message;
			this.type = type;
			this.duration = new Countdown(DURATION, false);
		}

		private boolean keepDisplaying(){
			return !duration.isCompleted();
		}

		public void render() {
			if (!duration.hasStarted()){
				duration.start();
				Sounds.play(type, 1.0f);
			}
			float width = Fonts.get(Fonts.BLACK_36).getBounds(message).width + 125;
			Image.rectangle(Constants.WIDTH / 2 - width / 2, 90, width - 50, 50, ALPHA, COLOR);
			Image.draw(type, Constants.WIDTH / 2 - width / 2 + 10, 95);
			Fonts.center(message, 100, Fonts.BLACK_36);
		}
	}
}
package com.luminositygames.smoothietycoon.ui;

import com.luminositygames.smoothietycoon.Main;
import com.luminositygames.smoothietycoon.entities.Player;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Events {

	private static boolean ROBBERY;
	private static boolean FRUIT_EXPIRED;
	private static boolean YOGURT_EXPIRED;
	private static boolean JUICE_SPILLED;

	public static void load(){
		ROBBERY = false;
		FRUIT_EXPIRED = false;
		YOGURT_EXPIRED = false;
		JUICE_SPILLED = false;
	}

	public static void nextEvent(){
		int chance = 15;
		boolean initiate = Main.random.nextInt(100) < chance;
		if (initiate){
			int event = Main.random.nextInt(4);
			if (event == 0){
				ROBBERY = true;
			} else if (event == 1){
				FRUIT_EXPIRED = true;
			} else if (event == 2){
				YOGURT_EXPIRED = true;
			} else if (event == 3){
				JUICE_SPILLED = true;
			}
		}
	}

	public static void handle(Player player) {
		if(Events.ROBBERY){
			player.payMoney(player.getMoney() / 5);
			Notifications.show("You were robbed of some of your money.", Notifications.EVENT);
			Events.ROBBERY = false;
		} else if(Events.FRUIT_EXPIRED){
			player.setFruits(0);
			Notifications.show("All your fruit spoiled overnight.", Notifications.EVENT);
			Events.FRUIT_EXPIRED = false;
		} else if(Events.YOGURT_EXPIRED){
			player.setYogurt(0);
			Notifications.show("All your yogurt spoiled overnight.", Notifications.EVENT);
			Events.YOGURT_EXPIRED = false;
		} else if(Events.JUICE_SPILLED){
			player.setJuice(0);
			Notifications.show("You spilled your juice container.", Notifications.EVENT);
			Events.JUICE_SPILLED = false;
		}
	}
}

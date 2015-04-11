package com.luminositygames.smoothietycoon.ui;

import com.luminositygames.smoothietycoon.SmoothieTycoon;
import com.luminositygames.smoothietycoon.entities.Player;

public class Event {

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
		boolean initiate = SmoothieTycoon.random.nextInt(100) < chance;
		if (initiate){
			int event = SmoothieTycoon.random.nextInt(4);
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
		if(Event.ROBBERY){
			player.payMoney(player.getMoney() / 5);
			Notifications.show("You were robbed of some of your money.", Notifications.EVENT);
			Event.ROBBERY = false;
		} else if(Event.FRUIT_EXPIRED){
			player.setFruits(0);
			Notifications.show("All your fruit spoiled overnight.", Notifications.EVENT);
			Event.FRUIT_EXPIRED = false;
		} else if(Event.YOGURT_EXPIRED){
			player.setYogurt(0);
			Notifications.show("All your yogurt spoiled overnight.", Notifications.EVENT);
			Event.YOGURT_EXPIRED = false;
		} else if(Event.JUICE_SPILLED){
			player.setJuice(0);
			Notifications.show("You spilled your juice container.", Notifications.EVENT);
			Event.JUICE_SPILLED = false;
		}
	}
}

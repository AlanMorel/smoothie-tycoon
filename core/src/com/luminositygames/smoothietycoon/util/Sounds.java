package com.luminositygames.smoothietycoon.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Sounds {

	public static HashMap<String, Sound> sounds;

	public static void load(String name, String format) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(name + "." + format));
		sounds.put(name, sound);
	}

	public static void play(String key, float volume){
		Sound sound = sounds.get(key);
		sound.play(volume);
	}
}

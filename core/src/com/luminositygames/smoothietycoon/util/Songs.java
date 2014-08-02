package com.luminositygames.smoothietycoon.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Songs {

	public static HashMap<String, Music> songs;

	public static void load(String name, float volume) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(name + ".mp3"));
		music.setLooping(true);
		music.setVolume(volume);
		songs.put(name, music);
	}

	public static void play(String name){
		songs.get(name).play();
	}
}

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

public class Song {

	public static HashMap<String, Song> songs;
	private Music music;

	public Song (Music m){
		this.music = m;
	}

	public static void load(String path, String key) {
		Music song = Gdx.audio.newMusic(Gdx.files.internal(path));
		song.setLooping(true);
		Song s = new Song(song);
		songs.put(key, s);
	}

	public static Music get(String key) {
		return songs.get(key).getMusic();
	}

	private Music getMusic(){
		return music;
	}
}

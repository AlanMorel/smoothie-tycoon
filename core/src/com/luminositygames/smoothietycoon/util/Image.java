package com.luminositygames.smoothietycoon.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.luminositygames.smoothietycoon.Main;
import com.luminositygames.smoothietycoon.ui.windows.Window;

/**
 * This file is part of Smoothie Tycoon
 * 
 * Copyright (c) 2013 - 2015 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Image {

	public static HashMap<String, Image> images;
	public static SpriteBatch batch;
	public static ShapeRenderer renderer;

	private Sprite sprite;
	private Sprite hover;
	private boolean hasHover;

	private Image(Sprite s){
		this.sprite = s;
		this.hasHover = false;
	}

	public Sprite getSprite(){
		if (hasHover && isHoveredOver()){
			return hover;
		}
		return sprite;
	}

	public float getHeight(){
		return sprite.getHeight();
	}

	public float getWidth(){
		return sprite.getWidth();
	}

	public boolean isHoveredOver(){
		Rectangle spriteRect = sprite.getBoundingRectangle();
		float x = Main.getX();
		float y = Main.getY();
		return spriteRect.contains(x, y);
	}

	public boolean isTouched(){
		Rectangle spriteRect = sprite.getBoundingRectangle();
		float x = Main.getX();
		float y = Main.getY();
		return spriteRect.contains(x, y) && Gdx.input.justTouched();
	}

	public static void addHover(String image, String hover) {
		Image image1 = Image.get(image);
		Image image2 = Image.get(hover);

		image1.hover = image2.getSprite();
		image1.hasHover = true;
	}

	public static void load(String name) {
		Texture texture = new Texture(name + ".png");
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Sprite sprite = new Sprite(texture);
		sprite.flip(false, true);
		Image image = new Image(sprite);
		images.put(name, image);
	}

	public static Image get(String name){
		return images.get(name);
	}

	public static void draw(String image, float x, float y){
		Image.batch.begin();
		Sprite sprite = get(image).getSprite();
		batch.draw(sprite, x, y);
		sprite.setX(x);
		sprite.setY(y);
		Image.batch.end();
	}

	public static Color getRandomColor(){
		float red = Main.random.nextFloat();
		float green = Main.random.nextFloat();
		float blue = Main.random.nextFloat();

		return new Color(red, green, blue, 1f);
	}

	public static void window(Window window){
		float alpha = 0.85f;
		Color color = Color.WHITE;
		if (window.equals(Window.GAME_OVER)){
			color = Color.BLACK;
		}
		rectangle(window.getRectangle(), alpha, color);
	}

	private static void rectangle(Rectangle rect, float alpha, Color color){
		rectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), alpha, color);
	}

	public static void rectangle(float x, float y, float width, float height, float alpha, Color color){
		Image.batch.begin();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Image.renderer.begin(ShapeType.Filled);
		Image.renderer.identity();
		color = new Color(color);
		color.a = alpha;
		Image.renderer.setColor(color);
		Image.renderer.rect(x, y, width, height);
		Image.renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		Image.batch.end();
	}

	public static void circle(float x, float y, float radius, Color color, float alpha){
		Image.batch.begin();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Image.renderer.setProjectionMatrix(Main.camera.combined);
		Image.renderer.begin(ShapeType.Filled);
		Image.renderer.identity();
		color.a = alpha;
		Image.renderer.setColor(color);
		Image.renderer.circle(x, y, radius);
		Image.renderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		Image.batch.end();
	}
}

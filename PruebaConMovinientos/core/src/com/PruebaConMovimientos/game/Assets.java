package com.PruebaConMovimientos.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion fondo;
	public static TextureRegion mosca3;
	public static TextureRegion mosca3verde;
	public static TextureRegion exit_down;
	public static TextureRegion exit_up;
	public static TextureRegion play_down;
	public static TextureRegion play_up;
	public static BitmapFont whiteFont;
	public static BitmapFont blackFont;
	public static TextureRegion blood_sprite_sheet;
	
	
	public static void load() {
		atlas = new TextureAtlas("images/juegoMosca.pack");
		
		fondo = atlas.findRegion("fondo");
		mosca3 = atlas.findRegion("mosca3");
		mosca3verde = atlas.findRegion("mosca3verde");
		exit_down = atlas.findRegion("exit_down");
		exit_up = atlas.findRegion("exit_up");
		play_down = atlas.findRegion("play_down");
		play_up = atlas.findRegion("play_up");
		blood_sprite_sheet = atlas.findRegion("blood_sprite_sheet");
		whiteFont = new BitmapFont(Gdx.files.internal("fnt/comic_white.fnt"),false);
	    blackFont = new BitmapFont(Gdx.files.internal("fnt/comic_black.fnt"),false);
		
	}

	public static void dispose() {
		atlas.dispose();
	}
}

package com.PruebaConMovimientos.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class SpriteMoscaBlanca {
	
	 SpriteBase spriteAnimadoBase ;


	public SpriteMoscaBlanca (float x, float y){
	
	    spriteAnimadoBase = new SpriteBase(Assets.mosca3,Assets.mosca3.getRegionWidth(),Assets.mosca3.getRegionHeight(), 150f); 
		spriteAnimadoBase.sprite.setPosition(x-spriteAnimadoBase.sprite.getWidth()/2,y-spriteAnimadoBase.sprite.getHeight()/2);
	}
	
	public SpriteMoscaBlanca (Vector2 vector){
		
	    spriteAnimadoBase = new SpriteBase(Assets.mosca3,Assets.mosca3.getRegionWidth(),Assets.mosca3.getRegionHeight(), 150f); 
		spriteAnimadoBase.sprite.setPosition(vector.x-spriteAnimadoBase.sprite.getWidth()/2,vector.y-spriteAnimadoBase.sprite.getHeight()/2);
	}

	public void draw(SpriteBatch batch, float delta) {
		
		spriteAnimadoBase.draw(batch,delta);
	
	}
    
	
}
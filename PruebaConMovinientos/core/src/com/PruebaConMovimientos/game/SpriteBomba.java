package com.PruebaConMovimientos.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.PruebaConMovimientos.game.Assets;

public class SpriteBomba {

    private static final int    FRAME_COLS = 4;     
    private static final int    FRAME_ROWS = 1;
	private static final float FRAME = 1/20f;    
    private Animation           bombaAnimation;   
    private Texture             bombaSheet;    
    private TextureRegion[]     bombaFrames;   
    private float stateTime;                    

	private SpriteBase spriteAnimadoBase;



	public SpriteBomba (float x, float y, int width, int height){
	    
		bombaSheet = new Texture(Gdx.files.internal("sheetmina4x1.png")); 
        TextureRegion[][] tmp = TextureRegion.split(bombaSheet, bombaSheet.getWidth()/FRAME_COLS, bombaSheet.getHeight()/FRAME_ROWS);      
        bombaFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
      
        int index = 0;
        
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                bombaFrames[index++] = tmp[i][j];
                
            }
        }
      bombaAnimation = new Animation(FRAME, bombaFrames);    
         
        stateTime = 0f;         
       
		spriteAnimadoBase = new SpriteBase(bombaAnimation.getKeyFrame(stateTime, true), 45, 45, 40f);
		spriteAnimadoBase.sprite.setPosition(x-spriteAnimadoBase.sprite.getWidth()/2,y-spriteAnimadoBase.sprite.getHeight()/2);
	}


	public void draw(SpriteBatch batch, float delta) {
		
		updateAnimation(delta);
		spriteAnimadoBase.draw(batch, delta);;
	}
    
private void updateAnimation(float delta) {
	stateTime += delta; 
	
	spriteAnimadoBase.sprite.setRegion(bombaAnimation.getKeyFrame(stateTime, true));;
		
	}



    
}
package com.PruebaConMovimientos.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SpriteMosca {

    private static final int    FRAME_COLS = 4;     
    private static final int    FRAME_ROWS = 1;
    private static final float FRAME = 1/25f; 
	private Animation           moscaAnimation;   
    private Texture             moscaSheet;    
    private TextureRegion[]     moscaFrames;   
    private float stateTime;                    
	private SpriteBase spriteAnimadoBase ;


	public SpriteMosca (float x, float y){
	
		moscaSheet = new Texture(Gdx.files.internal("sheetMosca4x1.png")); 
        TextureRegion[][] tmp = TextureRegion.split(moscaSheet, moscaSheet.getWidth()/FRAME_COLS, moscaSheet.getHeight()/FRAME_ROWS);  
        moscaFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        
        int index = 0;
        
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                moscaFrames[index++] = tmp[i][j];
                
            }
        }
      moscaAnimation = new Animation(FRAME, moscaFrames);    
         
        stateTime = 0f;         
   
        spriteAnimadoBase = new SpriteBase(moscaAnimation.getKeyFrame(stateTime, true),45, 45, 150f); 
		spriteAnimadoBase.sprite.setPosition(x-spriteAnimadoBase.sprite.getWidth()/2,y-spriteAnimadoBase.sprite.getHeight()/2);
	}


	public void draw(SpriteBatch batch, float delta) {
		
		updateAnimation(delta);
		spriteAnimadoBase.draw(batch,delta);
	
	}
    
private void updateAnimation(float delta) {
	stateTime += delta; 
	
	spriteAnimadoBase.sprite.setRegion(moscaAnimation.getKeyFrame(stateTime, true));;
		
	}
    
}
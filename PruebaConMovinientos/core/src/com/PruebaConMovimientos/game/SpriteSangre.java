package com.PruebaConMovimientos.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.PruebaConMovimientos.game.Assets;

public class SpriteSangre {

    private static final int    FRAME_COLS = 4;     
    private static final int    FRAME_ROWS = 4;
	private static final float FRAME = 20;    
    private Animation           bloodAnimation;   
    private Texture             bloodSheet;    
    private TextureRegion[]     bloodFrames;   
    private TextureRegion       currentFrame;     
    private float stateTime;                    
	private float posY;
	private float posX;
	private int tamaño;
	
	 public float getStateTime() {
			return stateTime;
		}



	public SpriteSangre (){
        bloodSheet = new Texture(Gdx.files.internal("blood_sprite_sheet2.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(bloodSheet, bloodSheet.getWidth()/FRAME_COLS, bloodSheet.getHeight()/FRAME_ROWS);              // #10
        bloodFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        tamaño= tmp[0][0].getRegionHeight();
        int index = 0;
        
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                bloodFrames[index++] = tmp[i][j];
                
            }
        }
      bloodAnimation = new Animation(1/20f, bloodFrames);    
         
        stateTime = 0f;         
        }

    
    public SpriteSangre(float x, float y) {
		this();
		this.posY = y-tamaño/2;
		this.posX = x-tamaño/2;
		
		
	}


	public void draw(SpriteBatch batch, float delta) {
		stateTime += delta; 
	//	stateTime += Gdx.graphics.getDeltaTime();           // #15
         // currentFrame = bloodAnimation.getKeyFrame(stateTime, true); 
          batch.draw(bloodAnimation.getKeyFrame(stateTime, true), posX, posY);   // 20 = SPRITE_SIZE

	}
    
    
}
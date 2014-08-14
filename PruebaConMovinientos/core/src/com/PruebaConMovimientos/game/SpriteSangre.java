package com.PruebaConMovimientos.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.PruebaConMovimientos.game.Assets;

public class SpriteSangre {

    private static final int    FRAME_COLS = 4;     
    private static final int    FRAME_ROWS = 4;
	private static final float FRAME = 1/20f;    
    private Animation           bloodAnimation;   
    private Texture             bloodSheet;    
    private TextureRegion[]     bloodFrames;   
   
    private float stateTime;                    
	private float posY;
	private float posX;
	private int tamaño;
	
	 public float getStateTime() {
			return stateTime;
		}



	public SpriteSangre (Vector2 vector){
		
		this.posX = vector.x-tamaño/2;
		this.posY = vector.y-tamaño/2;
		
		bloodSheet = new Texture(Gdx.files.internal("blood_sprite_sheet2.png")); 
        TextureRegion[][] tmp = TextureRegion.split(bloodSheet, bloodSheet.getWidth()/FRAME_COLS, bloodSheet.getHeight()/FRAME_ROWS);     
        bloodFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        tamaño= tmp[0][0].getRegionHeight();
        int index = 0;
        
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                bloodFrames[index++] = tmp[i][j];
                
            }
        }
      bloodAnimation = new Animation(FRAME, bloodFrames);    
         
        stateTime = 0f;         
        }

    
	public void draw(SpriteBatch batch, float delta) {
		stateTime += delta; 
        batch.draw(bloodAnimation.getKeyFrame(stateTime, true), posX, posY);   

	}
    
    
}
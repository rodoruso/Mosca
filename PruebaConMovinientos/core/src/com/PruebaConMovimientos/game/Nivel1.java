package com.PruebaConMovimientos.game;

import java.util.ArrayList;

import com.PruebaConMovimientos.game.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class Nivel1 extends NivelBase {
	SpriteMoscaVerde moscaVerde;
	ArrayList<SpriteMoscaBlanca> moscas = new ArrayList<SpriteMoscaBlanca>();
	ArrayList<SpriteSangre> manchas = new ArrayList<SpriteSangre>();
	private int origX=20;
	private int origY=20;
	private boolean muerteMoscaVerde=false;
	
	public Nivel1(PruebaConMovimientos game) {
		super(game);
		
	}
	
	protected void drawChild (SpriteBatch batch2, float delta){
		if(!muerteMoscaVerde)
			moscaVerde.draw(batch2, delta);
		
		for (SpriteSangre mancha : manchas) { 
			mancha.draw(batch2, delta);
			
			if(mancha.getStateTime()>12/20f)
				{manchas.remove(mancha);
				break;
				}
		}
		
		for (SpriteMoscaBlanca mosca : moscas) {
			
			mosca.spriteAnimadoBase.draw(batch2,delta);
		}
		evaluaGameOver();
	}
	
	
	protected void evaluaGameOver() {
		if(moscas.size()==0&&muerteMoscaVerde)	{
			System.out.println("toque algopara continuar, nivel terminado!!!!!!!");
			if(Gdx.input.isTouched())	{
				game.setScreen(new PantallaMenu(game));
				dispose();
		}}
	}

	
	protected void showChild(){
		Vector2 vectorAleat = new Vector2((float) (Math.random() * (game.WIDTH - (origX*2))), (float) (Math.random() * (game.HEIGHT - (origY*2))));
		moscaVerde= new SpriteMoscaVerde(vectorAleat);
	
	}
	
	
	protected  boolean hitDetection(Rectangle punteroRectangulo) {
// evalua la mosca verde
	if(	!muerteMoscaVerde && moscaVerde.spriteAnimadoBase.contains(punteroRectangulo)){
		
			if (moscaVerde.spriteAnimadoBase.getGolpe()<2)	{
					
					moscaVerde.spriteAnimadoBase.setGolpe();
					moscas.add(new SpriteMoscaBlanca(moscaVerde.spriteAnimadoBase.getPos()));//// traigo la posicion de la mosca y no uso el punto de touch para que nazca la nueva
				}																	//// donde va la antigua mosca
				else{
					manchas.add(new SpriteSangre(moscaVerde.spriteAnimadoBase.getPos()));
					muerteMoscaVerde = true;
				}
				return true;
			}
	// evalua las moscas blancas 		
	
	if(moscas!=null){
		for (SpriteMoscaBlanca mosca : moscas) 
			if(	 mosca.spriteAnimadoBase.contains(punteroRectangulo)){
				//// traigo la posicion de la mosca y no uso el punto de touch para que nazca la nueva
							//// donde va la antigua mosca
				
					manchas.add(new SpriteSangre(mosca.spriteAnimadoBase.getPos()));
					moscas.remove(mosca);
					return true;	
			}
				
			
	}

	return false;

	}


}

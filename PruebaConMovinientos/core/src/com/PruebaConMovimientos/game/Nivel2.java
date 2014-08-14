package com.PruebaConMovimientos.game;


import java.util.ArrayList;

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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Nivel2 extends NivelBase {

	ArrayList<SpriteMoscaBlanca> moscasBlancas;
	ArrayList<SpriteMoscaVerde> moscasVerdes;
	SpriteMoscaAnimada moscaAnimada;
	ArrayList<SpriteBomba> bombas;
	SpriteBomba bombaAnimada;
	ArrayList<SpriteSangre> manchas ;
	private int origX=20;
	private int origY=20;

	private boolean muerteMoscaAnimada=false;
	
	
	public Nivel2(PruebaConMovimientos game) {
		super(game);
			
	}
	
	protected void drawChild (SpriteBatch batch, float delta){

		if(!muerteMoscaAnimada)
		moscaAnimada.draw(batch, delta);
		
		for (SpriteMoscaVerde moscaVerde : moscasVerdes) {
			moscaVerde.draw(batch,delta);
		}
		
		for (SpriteMoscaBlanca moscaBlanca : moscasBlancas) {
			moscaBlanca.draw(batch,delta);
		}	
		
		for (SpriteBomba bomba : bombas) {
			bomba.draw(batch,delta);
		}
		for (SpriteSangre mancha : manchas) { 
			mancha.draw(batch, delta);
			
			if(mancha.getStateTime()>12/20f)
				{manchas.remove(mancha);
				break;
				}
		}
	
		
	}
	

	protected void showChild(){
		
		moscasVerdes = new ArrayList<SpriteMoscaVerde>();
		moscasBlancas = new ArrayList<SpriteMoscaBlanca>();
		moscaAnimada = new SpriteMoscaAnimada((float) (Math.random() * (game.WIDTH - (origX*2))), (float) (Math.random() * (game.HEIGHT - (origY*2))));
		manchas = new ArrayList<SpriteSangre>();
		bombas = new ArrayList<SpriteBomba>();
		
		for (int i = 0; i<3 ;++i){
			bombas.add(new SpriteBomba((float) (Math.random() * (game.WIDTH - (origX*2))), (float) (Math.random() * (game.HEIGHT - (origY*2))),game.WIDTH,game.HEIGHT));
		}
		
		
	
	}

	@Override
	protected void evaluaGameOver() {
		
			
		if (muerteMoscaAnimada && moscasBlancas.size()==0 && moscasVerdes.size() ==0)		{
			
		//	antes de salir podriamos mostrar un resumen de j juego y poner que toque una tecla para continuar
			if(Gdx.input.isKeyPressed(Input.Keys.P)){
			game.setScreen(new PantallaMenu(game));
			dispose();
			System.out.println("final");
			
			}
		}
	}

	@Override
	protected boolean hitDetection( Rectangle punteroRectangulo) {
		///////////----------------------evaluo si toco bombas
		
		for (SpriteBomba bomba : bombas) {
			
			if (bomba.spriteAnimadoBase.contains(punteroRectangulo))	{
			
				//// TODO que descuente una vida
				System.out.println("perdiste la vida pete ");
				return true;
			}
			
		}		
		
		//------------------si el rectangulo que devuelve el sprite contiene el rectangulo que envia el puntero desde NivelBase
			
		if (!muerteMoscaAnimada && moscaAnimada.spriteAnimadoBase.sprite.getBoundingRectangle().contains(punteroRectangulo))	{
				//----------- me guardo en vector la posicion corregida del centro del sprite
				//vector.set(moscaAnimada.spriteAnimadoBase.getPos());  
				buildStars(moscaAnimada.spriteAnimadoBase.getPos());
				muerteMoscaAnimada=true;
				for (int i=0;i<3;++i) {
					moscasVerdes.add( new SpriteMoscaVerde(moscaAnimada.spriteAnimadoBase.getPos()));
					moscasVerdes.get(i).spriteAnimadoBase.setGolpe(1);
						
				}
		return true;
		}
		/// cuando muere la animada    aparecen 3 verde y recien arranca a evaluar este termino
		if (muerteMoscaAnimada)	{
			for (SpriteMoscaVerde mosca : moscasVerdes) {
				if (mosca.spriteAnimadoBase.contains(punteroRectangulo))	{
					if(mosca.spriteAnimadoBase.getGolpe()<2){
						mosca.spriteAnimadoBase.setGolpe();
						moscasBlancas.add( new SpriteMoscaBlanca(mosca.spriteAnimadoBase.getPos()));
									//// traigo la posicion de la mosca y no uso el punto de touch para que nazca la nueva
									//// donde va la antigua mosca
					       
					}
					else	{
						moscasVerdes.remove(mosca);
						manchas.add(new SpriteSangre(mosca.spriteAnimadoBase.getPos()));
					}
				return true;
				}
			}
				
		}	
		/// cuando muere la animada    aparecen 3 verde y de cada verde aparecen dos blncas recien arranca a evaluar este termino
		if (muerteMoscaAnimada && moscasBlancas!=null)	{
			for (SpriteMoscaBlanca mosca : moscasBlancas) {
				if (mosca.spriteAnimadoBase.contains(punteroRectangulo))	{
					if(mosca.spriteAnimadoBase.getGolpe()<1)
						mosca.spriteAnimadoBase.setGolpe();
										       
					
					else	{
						moscasBlancas.remove(mosca);
						manchas.add(new SpriteSangre(mosca.spriteAnimadoBase.getPos()));
					}
				return true;
				}
			}
				
		}
	
	return false;
}
	
	
	private void buildStars(Vector2 vectorPos) {
		for (int i = 0; i < 6; i++) {
			textureStar = new Texture("star.png");
			imageStar = new Image(textureStar);
			// posicion inicial
			imageStar.setCenterPosition(vectorPos.x, vectorPos.y);

			// posicion final
			int fx = (int) (imageStar.getCenterX() + Math.sin(Math
					.toRadians(i * 60)) * 100);
			int fy = (int) (imageStar.getCenterY() + Math.cos(Math
					.toRadians(i * 60)) * 100);

			// Movimiento, decelerado tambien
			MoveToAction action = new MoveToAction();
			action.setPosition(fx, fy);
			action.setDuration(0.2f);
			// action.setInterpolaion(Interpolation.exp10);

			imageStar.addAction(Actions.sequence(
					Actions.parallel(action, Actions.fadeOut(0.5f)),
					(Actions.removeActor())));

			// y lo añadimos a este Stage
			stage.addActor(imageStar);
		}
	}



}

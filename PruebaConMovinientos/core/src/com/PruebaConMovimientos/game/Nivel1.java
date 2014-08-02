package com.PruebaConMovimientos.game;

import java.util.ArrayList;

import com.PruebaConMovimientos.game.Assets;
import com.badlogic.gdx.Gdx;
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


public class Nivel1 extends NivelBase  {
	
	ArrayList<Mosca> moscas = new ArrayList<Mosca>();
	ArrayList<SpriteSangre> manchas = new ArrayList<SpriteSangre>();
	
	
	public Nivel1(PruebaConMovimientos game) {
		super(game);
		
	}
	
	protected void drawChild (SpriteBatch batch2, float delta){
		
		for (SpriteSangre mancha : manchas) { 
			mancha.draw(batch2, delta);
			
			if(mancha.getStateTime()>12/20f)
				{manchas.remove(mancha);
				break;
				}
		
		}
		
		for (Mosca mosca : moscas) {
			
			mosca.MovAleat(delta);
			mosca.draw(batch2);
		}
	}
	
	
	protected void evaluaGameOver() {
		if(moscas.size()==0)
		{
			System.out.println("nivel terminado!!!!!!!");
			game.setScreen(new PantallaMenu(game));
			dispose();
		}
	}

	
	protected void showChild(){
		
		Mosca mosca = new Mosca(game);
		
		moscas.add(mosca);
	}
	
	
	protected  boolean hitDetection(int screenX, int screenYCorregido) {

		for (Mosca mosca : moscas) {

			if (mosca.contains(screenX,  screenYCorregido))	{
				vector.set(mosca.getPos());  
				if (mosca.getGolpe()<2)	{
					mosca.setGolpe();
				//// traigo la posicion de la mosca y no uso el punto de touch para que nazca la nueva
					//// donde va la antigua mosca
					       
					moscas.add(new Mosca(game,vector.x,vector.y,2));
				}
				else{
					moscas.remove(mosca);
					manchas.add(new SpriteSangre(vector.x,vector.y));
					}
				return true;
			}
					
		}
		return false;

	}

	private void buildStars(int x, int y) {
		for (int i = 0; i < 6; i++) {
			textureStar = new Texture("star.png");
			imageStar = new Image(textureStar);
			// posicion inicial
			imageStar.setCenterPosition(x, y);

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

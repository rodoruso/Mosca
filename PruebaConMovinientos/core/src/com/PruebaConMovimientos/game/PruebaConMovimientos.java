package com.PruebaConMovimientos.game;

import com.PruebaConMovimientos.game.Assets;
import com.badlogic.gdx.Game;


 
public class PruebaConMovimientos extends Game 
    {
	static public int WIDTH = 800;
	static public int HEIGHT = 600;
	@Override
	public void create() {
		Assets.load();
		//setScreen(new PantallaJuego());
		setScreen(new PantallaMenu(this));
	}
   
 
}
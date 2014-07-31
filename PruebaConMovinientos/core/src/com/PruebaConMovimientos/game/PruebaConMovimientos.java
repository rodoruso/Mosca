package com.PruebaConMovimientos.game;

import com.PruebaConMovimientos.game.Assets;
import com.badlogic.gdx.Game;


 
public class PruebaConMovimientos extends Game 
    {

	@Override
	public void create() {
		Assets.load();
		//setScreen(new PantallaJuego());
		setScreen(new PantallaMenu(this));
	}
   
 
}
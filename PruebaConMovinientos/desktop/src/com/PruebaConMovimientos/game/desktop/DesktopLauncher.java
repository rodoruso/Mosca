package com.PruebaConMovimientos.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import com.PruebaConMovimientos.game.CopyOfSpriteSangre;
import com.PruebaConMovimientos.game.Nivel1;
import com.PruebaConMovimientos.game.PruebaConMovimientos;
import com.PruebaConMovimientos.game.SpriteSangre;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		boolean rebuildAtlas=true;
		if (rebuildAtlas) {
			Settings settings = new Settings();
	
			TexturePacker2.process(settings, "tema",
			"../android/assets/images",
			"juegoMosca.pack");
			}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = PruebaConMovimientos.WIDTH;
		config.height = PruebaConMovimientos.HEIGHT;
		//config.resizable = false;
		new LwjglApplication(new PruebaConMovimientos(), config);
		
		//new LwjglApplication(new CopyOfSpriteSangre(), config);
		
	}
}

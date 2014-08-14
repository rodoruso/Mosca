package com.PruebaConMovimientos.game.desktop;

import com.PruebaConMovimientos.game.CameraDemo;
import com.PruebaConMovimientos.game.PinchToZoom;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
   public static void main(String[] args) {
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "camera";
      cfg.width = 1280;
      cfg.height = 720;
 
      new LwjglApplication(new PinchToZoom(), cfg);
   }
}
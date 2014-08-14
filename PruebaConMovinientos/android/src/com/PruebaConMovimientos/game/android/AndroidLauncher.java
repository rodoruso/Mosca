package com.PruebaConMovimientos.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.PruebaConMovimientos.game.PinchToZoom;
import com.PruebaConMovimientos.game.PruebaConMovimientos;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new PinchToZoom(), config);
		initialize(new PruebaConMovimientos(), config);
	}
}

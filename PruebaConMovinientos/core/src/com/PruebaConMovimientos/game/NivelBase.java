package com.PruebaConMovimientos.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public abstract class NivelBase implements Screen, InputProcessor{

	 static final ShapeType ShapeType = null;
	 Texture  textureStar;
	 Image  imageStar;
	 Stage stage;
	Vector3 touchPos1;
	 SpriteBatch batch;
	
	Image fondo;
	Vector2 vectorOrig, vectorDest;
	 OrthographicCamera camera;
	Rectangle punteroRectangulo;
	 ShapeRenderer cuadradroBoubds;
	 Vector2 vector= null;
	 float delta2 = 1/30f ;
	 PruebaConMovimientos game;
	 Label timeLabel;
	 float tiempTot;
	
	
	public NivelBase (PruebaConMovimientos game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		cuadradroBoubds.setProjectionMatrix(camera.combined);	
	
		ajusteTimeLabel(delta);
		
		batch.begin();
		fondo.draw(batch, 1);
		timeLabel.draw(batch, 1);
		
		drawChild(batch,delta);

		batch.end();
		
		evaluaGameOver();
		
		cuadradroBoubds.begin(ShapeType.Line);
		cuadradroBoubds.setColor(1, 1, 0, 1);
		cuadradroBoubds.rect(45,45, game.WIDTH-90, game.HEIGHT-90);
		cuadradroBoubds.end();
	}
	
	abstract protected void evaluaGameOver();

	abstract protected void drawChild (SpriteBatch batch2, float delta);

	private void ajusteTimeLabel(float delta) {
		tiempTot += delta;
		String textoTimeLabel = "Tiempo = "+ Float.toString(tiempTot);
		timeLabel.setText(textoTimeLabel);
	}
	
	@Override
	public void resize(int width, int height) {

		game.WIDTH = width;
		game.HEIGHT = height;
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
		fondo.setBounds(0, 0, game.WIDTH, game.HEIGHT);

	}


	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
		//
		// stage = new Stage();

		Gdx.input.setInputProcessor(this);
		
		
		LabelStyle labelStyle = new LabelStyle(Assets.whiteFont, Color.RED);
		
		
	    timeLabel = new Label("Tiempo = ", labelStyle);
		timeLabel.setPosition(25f, game.HEIGHT-25f);
		//timeLabel.setCenterPosition(PantallaJuego.WIDTH/2, PantallaJuego.HEIGHT-50f);
		
				
		vector = new Vector2();
		
		batch = new SpriteBatch();
		
		fondo = new Image(Assets.fondo);
		fondo.setBounds(0, 0, game.WIDTH, game.HEIGHT);

		cuadradroBoubds = new ShapeRenderer();
	
			
		showChild();

	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (hitDetection(screenX, game.HEIGHT - screenY))   /// HEIGHT - screenY para corregir el draw
			System.out.println("le pegaste en pos: X" + screenX + " ,Y"	+ (game.HEIGHT - screenY));
		 else	
			System.out.println("NO le pegaste en pos: X" + screenX + " ,Y" + (game.HEIGHT - screenY));
				
		return true;
	}

		
	abstract protected boolean hitDetection(int screenX, int i) ;

	abstract protected void showChild();
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}

package com.PruebaConMovimientos.game;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Nivel2 extends InputAdapter implements Screen{

	ArrayList<SpriteMosca> moscas;
	SpriteMosca moscaAnimada;
	ArrayList<SpriteBomba> bombas;
	SpriteBomba bombaAnimada;
	private int origX=20;
	private int origY=20;
	private PruebaConMovimientos game;
	private SpriteBatch batch;
	private float tiempTot;
	private Label timeLabel;
	private Image fondo;
	private OrthographicCamera camera;
	
	public Nivel2(PruebaConMovimientos game) {
		this.game = game;
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	//	camera.update();
	//	batch.setProjectionMatrix(camera.combined);
		
		ajusteTimeLabel(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		fondo.draw(batch, 1);
		timeLabel.draw(batch, 1);
		
		moscaAnimada.draw(batch, delta);
		
		for (SpriteBomba bomba : bombas) {
			bomba.draw(batch,delta);
		}
		batch.end();
		
	//	evaluaSprites(moscas);
		
		//cuadradroBoubds.begin(ShapeType.Line);
	//	cuadradroBoubds.setColor(1, 1, 0, 1);
	//	cuadradroBoubds.rect(45,45, WIDTH-90, HEIGHT-90);
	//	cuadradroBoubds.end();
		
		
	}
	
	private void ajusteTimeLabel(float delta) {
		tiempTot += delta;
		String textoTimeLabel = "Tiempo = "+ Float.toString(tiempTot);
		timeLabel.setText(textoTimeLabel);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
		
		moscaAnimada = new SpriteMosca((float) (Math.random() * (game.WIDTH - (origX*2))), (float) (Math.random() * (game.HEIGHT - (origY*2))));
	
		bombas = new ArrayList<SpriteBomba>();
		for (int i = 0; i<3 ;++i){
		bombas.add(new SpriteBomba((float) (Math.random() * (game.WIDTH - (origX*2))), (float) (Math.random() * (game.HEIGHT - (origY*2))),game.WIDTH,game.HEIGHT));
		}
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
		
		Gdx.input.setInputProcessor(this);
		
		
		LabelStyle labelStyle = new LabelStyle(Assets.whiteFont, Color.RED);
		timeLabel = new Label("Tiempo = ", labelStyle);
		timeLabel.setPosition(25f, game.HEIGHT-25f);
			
		
		batch = new SpriteBatch();
		
		fondo = new Image(Assets.fondo);
		fondo.setBounds(0, 0, game.WIDTH, game.HEIGHT);
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

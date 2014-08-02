package com.PruebaConMovimientos.game;

import java.util.ArrayList;

import com.PruebaConMovimientos.game.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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


public class Nivel1 extends InputAdapter implements Screen {

	private static final ShapeType ShapeType = null;
	private Texture  textureStar;
	private Image  imageStar;
	private Stage stage;
	Vector3 touchPos1;
	public SpriteBatch batch;
	ArrayList<Mosca> moscas = new ArrayList<Mosca>();
	Image fondo;
	Vector2 vectorOrig, vectorDest;
	private OrthographicCamera camera;
	Rectangle punteroRectangulo;
	private ShapeRenderer cuadradroBoubds;
	private Vector2 vector= null;
	private float delta2 = 1/30f ;
	private PruebaConMovimientos game;
	private Label timeLabel;
	private float tiempTot;
	ArrayList<SpriteSangre> manchas = new ArrayList<SpriteSangre>();

	
	public Nivel1(PruebaConMovimientos game) {
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
		
		for (SpriteSangre mancha : manchas) { 
			mancha.draw(batch, delta);
			
			if(mancha.getStateTime()>12/20f)
				{manchas.remove(mancha);
				break;
				}
		
		}
		
		for (Mosca mosca : moscas) {
			
			mosca.MovAleat(delta2);
			mosca.draw(batch);
		}

		batch.end();
		
		evaluaSprites(moscas);
		
		cuadradroBoubds.begin(ShapeType.Line);
		cuadradroBoubds.setColor(1, 1, 0, 1);
		cuadradroBoubds.rect(45,45, game.WIDTH-90, game.HEIGHT-90);
		cuadradroBoubds.end();
	}

	private void ajusteTimeLabel(float delta) {
		tiempTot += delta;
		String textoTimeLabel = "Tiempo = "+ Float.toString(tiempTot);
		timeLabel.setText(textoTimeLabel);
	}


	private void evaluaSprites(ArrayList<Mosca> moscas2) {
		if(moscas.size()==0)
		{
			System.out.println("nivel terminado!!!!!!!");
			game.setScreen(new PantallaMenu(game));
			dispose();
		}
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
	
	//	SpriteSangre mancha = new SpriteSangre();
			
	//	manchas.add(mancha);
		Mosca mosca = new Mosca(game);
			
		moscas.add(mosca);
		

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
		moscas.removeAll(moscas);

	}

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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (hitDetection(screenX, game.HEIGHT - screenY))   /// HEIGHT - screenY para corregir el draw
			System.out.println("le pegaste en pos: X" + screenX + " ,Y"	+ (game.HEIGHT - screenY));
		 else	
			System.out.println("NO le pegaste en pos: X" + screenX + " ,Y" + (game.HEIGHT - screenY));
				
		return true;
	}

	private boolean hitDetection(int screenX, int screenYCorregido) {

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

}

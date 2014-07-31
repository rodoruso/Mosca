package com.PruebaConMovimientos.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;



public class PantallaMenu implements Screen {

	private Stage stage;
	private Table table;
	private Button buttonPlay;
	private Button buttonExit;
	private PruebaConMovimientos game;
	public SpriteBatch batch;
	private Label nameLabel;

	
	public PantallaMenu(PruebaConMovimientos game) {
		this.game = game;
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Table.drawDebug(stage);														// borrar cuando se termine
		
		batch.begin();
		nameLabel.draw(batch, 1);
		batch.end();
		
		stage.act(delta);
		stage.draw(); 
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		stage = new Stage();			
		Gdx.input.setInputProcessor(stage);
		batch = new SpriteBatch();
  
		table = new Table();
	//	table.setFillParent(true);
		table.setBounds(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);


		
	    LabelStyle labelStyle = new LabelStyle(Assets.whiteFont, Color.RED);
		
		
	    nameLabel = new Label("Juego de las Moscas", labelStyle);
		//nameLabel.setPosition(400f, PantallaJuego.HEIGHT-50f);
		nameLabel.setCenterPosition(PantallaJuego.WIDTH/2, PantallaJuego.HEIGHT-50f);
		
		
		
		// Button Play ------------------------------------------------------------------
		TextButtonStyle ButtonStylePlay= new TextButtonStyle();          
		
		Image imagenButtonPlay_Up = new Image(Assets.play_up);
		ButtonStylePlay.up = imagenButtonPlay_Up.getDrawable();
		
		Image imagenButtonPlay_Down = new Image(Assets.play_down);
		ButtonStylePlay.down = imagenButtonPlay_Down.getDrawable();
		
		buttonPlay = new Button(ButtonStylePlay);
		buttonPlay.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PantallaJuego(game));
				dispose();
			}
		});
		// Button Exit ------------------------------------------------------------------
		TextButtonStyle ButtonStyleExit= new TextButtonStyle(); 
		
		Image imagenButtonExit_Up = new Image(Assets.exit_up);  
		ButtonStyleExit.up = imagenButtonExit_Up.getDrawable();
		
		Image imagenButtonExit_Down = new Image(Assets.exit_down);
		ButtonStyleExit.down = imagenButtonExit_Down.getDrawable();
		
		buttonExit = new Button(ButtonStyleExit);
		buttonExit.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit(); 
				dispose();
			}
		});
				
		//--agrego a la tabla--------------------------------------------
		
	
		
		table.row().height(50).width(150);
		table.add(buttonPlay);

		 table.row().height(50).width(150);
		table.add(buttonExit); 
				
		table.debug();              												 // borrar cuando se termine
		stage.addActor(table);
		
		
		
		
		
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

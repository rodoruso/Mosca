package com.PruebaConMovimientos.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * extension de la clase Sprite
 * 
 * @author Usuario
 * 
 */
public class Mosca {

	private Sprite sprite;
	private int bandera = 0;
	private float destX;
	private float destY;
	private Vector2 puntoDest;
	private float angulo;
	private PantallaJuego pantallaJuego;
	private float vel = 50f; /// pixeles por second
	private float origX;
	private float origY;
	 float distRec=0;
	private Vector2 position=null;
	private float recorridoX;
	private float recorridoY;
	private float disTot;
	private float porcentaje;
	private float origenX;
	private float origenY;
	private int golpe=0;
	

	public Mosca(PantallaJuego pantallaJuego) {

		//Texture texture = new Texture("mosca3.png");
		sprite = new Sprite(Assets.mosca3verde);

		this.pantallaJuego = pantallaJuego;  /// le paso la pantalla del juego para poder aaceder al ancho y alto respondiendo 
											// al encubrimiento de objeto
		sprite.setSize(sprite.getWidth() / 2, sprite.getHeight() / 2); /// en vez de usar escala uso esto porq la escala mantiene el tamaño original
		
		origX = sprite.getWidth(); /// hasta que distancia del lado izq llega el movimiento
		origY = sprite.getHeight();/// hasta que distancia del lado inferior llega el movimiento
	
		sprite.setBounds(0, 0, sprite.getWidth(), sprite.getHeight());// para poder detercar choque se le define un rectangulo
		
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);// donde se centra el el sprite (la rotacion la hace con este centro)
		
		//sprite.setPosition(sprite.getWidth() -sprite.getWidth() / 2, sprite.getHeight()-sprite.getHeight() / 2);// donde nace el sprite
		sprite.setPosition((float) (Math.random() * (pantallaJuego.WIDTH - (origX*2))), (float) (Math.random() * (pantallaJuego.HEIGHT - (origX*2))));
	}

	public Mosca(PantallaJuego pantallaJuego2, float x, float y,int i) {   //// mandar numero de texture
		this(pantallaJuego2);
		sprite.setPosition(x, y);
		setGolpe(i);
		sprite.setRegion(Assets.mosca3);
		
	}

	public void draw(SpriteBatch batch) {
		batch.draw(sprite.getTexture(), sprite.getVertices(), 0, 20); // 20 = SPRITE_SIZE

	}
//	public class Sprite extends TextureRegion { traido de Sprite.draw
//		static final int VERTEX_SIZE = 2 + 1 + 2;
//		static final int SPRITE_SIZE = 4 * VERTEX_SIZE;

	/**
	 * Realiza movimiento aleatorio
	 * 
	 * @param arraySprite
	 *            es la imagen que queremos mostrar realizando movimiento
	 *            aleatorio
	 * @param delta
	 *            es el tiempo entre impresiones de pantalla
	 * @param vel
	 *            es la velocidad que queremos que tenga
	 * @param WIDTH
	 *            define el ancho donde se podra mover nuestro sprite
	 * @param HEIGHT
	 *            define el alto donde se podra mover nuestro sprite
	 */

	public void MovAleat(float delta) {
		
		
		if (bandera == 0) {
			destX = origX/2 + (float) (Math.random() * (pantallaJuego.WIDTH - (origX*2)));
			destY = origY/2 + (float) (Math.random() * (pantallaJuego.HEIGHT - (origY*2)));

			origenX= sprite.getX();
			origenY = sprite.getY();
			
			puntoDest = new Vector2(destX, destY);

			puntoDest.sub(origenX,origenY); // al vectorTayectoria le resto el origen y queda la diferencia ya
															// guardada en el mismo
			angulo = puntoDest.angle();
			sprite.setRotation(angulo); // calcula el angulo
			disTot=puntoDest.len();
			bandera = 1;
			distRec=0;

		}
		else{
			distRec = distRec + (delta * vel);
			if (Math.abs(distRec) < Math.abs(disTot)) {
				
				porcentaje =distRec/disTot;
		
				recorridoX = origenX+porcentaje * puntoDest.x ; /// para mantener la posicion y mandarla cuando la req
				recorridoY = origenY+porcentaje * puntoDest.y ;
				
				sprite.setX(recorridoX);
				sprite.setY(recorridoY);
		
		
		} else {
			sprite.setX(destX);
			sprite.setY(destY);
			bandera=0;
			
		}
		}
	}

	public boolean contains(int x, int y) {

		return sprite.getBoundingRectangle().contains(x, y);
	}

	public Vector2 getPos() {
		position = new Vector2(Math.abs(recorridoX),Math.abs(recorridoY));
		return position;
	}

	public int getGolpe() {
		return golpe;
		
	}

	public void setGolpe() {
		golpe++;
		return ;
	}
	public void setGolpe(int i) {
		golpe=i;
		return ;
	}

}

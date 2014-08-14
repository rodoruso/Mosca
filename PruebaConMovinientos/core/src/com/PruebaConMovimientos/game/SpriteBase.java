package com.PruebaConMovimientos.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SpriteBase {
	public float posY;
	public float posX;
	public int bandera;
	public float destX;
	public float origY;
	public float origenX;
	public float origenY;
	public float origX;
	public float destY;
	public Vector2 puntoDest;
	public float angulo;
	public float recorridoY;
	public Vector2 position;
	public TextureRegion textureRegion;
	public float recorridoX;
	public Sprite sprite;
	public float porcentaje;
	public float disTot;
	public float distRec;
	public float vel;
	public int golpe=0;


public SpriteBase(TextureRegion keyFrame, float origX, float origY, float vel) {
	this.origY = origY;
	this.origX = origX;
	this.vel = vel;
	sprite = new Sprite(keyFrame);
	}

public void draw(SpriteBatch batch, float delta) {
	

	MovAleat(delta);
	batch.draw(sprite.getTexture(), sprite.getVertices(), 0, 20);

}
public void MovAleat(float delta) {
		
		
		if (bandera == 0) {
			destX = origX/2 + (float) (Math.random() * (PruebaConMovimientos.WIDTH - (origX*2)));
			destY = origY/2 + (float) (Math.random() * (PruebaConMovimientos.HEIGHT - (origY*2)));

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

public Vector2 getPos() {
	position = new Vector2(Math.abs(recorridoX),Math.abs(recorridoY));
	return position;
}
public int getGolpe() {
	return golpe;
	
}
public boolean contains(Rectangle punteroRectangulo) {

	return sprite.getBoundingRectangle().contains(punteroRectangulo);
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
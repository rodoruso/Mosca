package com.PruebaConMovimientos.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
 
public class CameraDemo implements ApplicationListener, GestureListener {
private OrthographicCamera camera;
private SpriteBatch batch;
private Texture texture;
private Sprite sprite;
private float totDeltaX=0;
 
@Override
public void create() {
   camera = new OrthographicCamera(1280, 720);
 
   batch = new SpriteBatch();
 
   texture = new Texture(Gdx.files.internal("fondo2048.jpg"));
//   texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
 
   sprite = new Sprite(texture);
   sprite.setOrigin(0,0);
   sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);
 
   Gdx.input.setInputProcessor(new GestureDetector(this));
}
 
@Override
public void dispose() {
   batch.dispose();
   texture.dispose();
}
 
@Override
public void render() {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

 
   batch.setProjectionMatrix(camera.combined);
   batch.begin();
   sprite.draw(batch);
   batch.end();
}
 
@Override
public void resize(int width, int height) {
}
 
@Override
public void pause() {
}
 
@Override
public void resume() {
}
 
@Override
public boolean touchDown(float x, float y, int pointer, int button) {
// TODO Auto-generated method stub
return false;
}
 
@Override
public boolean tap(float x, float y, int count, int button) {
// TODO Auto-generated method stub
return false;
}
 
@Override
public boolean longPress(float x, float y) {
// TODO Auto-generated method stub
return false;
}
 
@Override
public boolean fling(float velocityX, float velocityY, int button) {
// TODO Auto-generated method stub
return false;
}
 
@Override
public boolean pan(float x, float y, float deltaX, float deltaY) {
 
   totDeltaX += deltaX;
   
   if (-384 <= totDeltaX && totDeltaX <= 384 && -384 <= deltaX && deltaX <= 384)
	   camera.translate(-deltaX,0);
 //  else
//	   if (-640 > totDeltaX)
//		   camera.translate(-640,0);
//	   else
//		   camera.translate(640,0);
   

   camera.update();
   return false;
}
 
@Override
public boolean zoom(float initialDistance, float distance) {
// TODO Auto-generated method stub
return false;
}
 
@Override
public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
Vector2 pointer1, Vector2 pointer2) {
// TODO Auto-generated method stub
return false;
}

@Override
public boolean panStop(float x, float y, int pointer, int button) {
	// TODO Auto-generated method stub
	return false;
}
}
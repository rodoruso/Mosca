package com.PruebaConMovimientos.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
 
public class PinchToZoom implements ApplicationListener {
 
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
 
    private InputMultiplexer inputMultiplexer;
    private MyInputProcessor inputProcessor;
    private MyGestureHandler gestureHandler;
 
    public float zoom = 1.0f;
 
    @Override
    public void create() {        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
 
        initaliseInputProcessors();
 
        camera = new OrthographicCamera(1, h/w);
        batch = new SpriteBatch();
 
        texture = new Texture(Gdx.files.internal("fondo2048.jpg"));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
 
        TextureRegion region = new TextureRegion(texture, 0, 0, 2048, 1024);
 
        sprite = new Sprite(region);
        sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
        sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
    }
 
    public void initaliseInputProcessors() {
 
        inputMultiplexer = new InputMultiplexer();
 
        Gdx.input.setInputProcessor(inputMultiplexer);
 
        inputProcessor = new MyInputProcessor();
        gestureHandler = new MyGestureHandler();
 
        inputMultiplexer.addProcessor(new GestureDetector(gestureHandler));
        inputMultiplexer.addProcessor(inputProcessor);
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
 
        camera.zoom = zoom;
        camera.update();
 
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }
 
    class MyInputProcessor implements InputProcessor {
 
        @Override
        public boolean scrolled(int amount) {
 
            //Zoom out
            if (amount > 0 && zoom < 1) {
                zoom += 0.1f;
            }
 
            //Zoom in
            if (amount < 0 && zoom > 0.1) {
                zoom -= 0.1f;
            }
 
            return true;
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
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
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
 
    }
 
    class MyGestureHandler implements GestureListener {
 
        public float initialScale = 1.0f;
 
        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
 
            initialScale = zoom;
 
            return false;
        }
 
        @Override
        public boolean zoom(float initialDistance, float distance) {
 
            //Calculate pinch to zoom
            float ratio = initialDistance / distance;
 
            //Clamp range and set zoom
            zoom = MathUtils.clamp(initialScale * ratio, 0.1f, 1.0f);
 
            return true;
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
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
				Vector2 pointer1, Vector2 pointer2) {
			// TODO Auto-generated method stub
			return false;
		}
 
    }

	@Override
	public void resize(int width, int height) {
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
}
package com.independentgames.porter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	static final String ASSET_DIRECTORY = "core/assets/";
	static final int WIDTH = 32;
	static final int HEIGHT = 16;
	static final float MAX_VELOCITY = 100f;
	static final float DECLERATION_RATE = 0.5f;
	static final float SCALE_MULTIPLIER = 3f;
	static final float STOP_THRESHHOLD = 5f;
	static final float AGGRO_RANGE = 100f;
	static final float PROXIMITY_TOUCHING = 32f;
	SpriteBatch batch;
	Texture img;

	PlayerShip ship;
	float time;

	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(ASSET_DIRECTORY + "ParsecTiles.png");
		ship = new PlayerShip();
		ship.create();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.update();
		tiledMap = new TmxMapLoader().load(ASSET_DIRECTORY + "space1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,SCALE_MULTIPLIER);
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			Gdx.app.exit();
		}
		time += Gdx.graphics.getDeltaTime();
		TextureRegion speed = ship.animationTile(time);
		TextureRegion craft = ship.getShipTile();
		ship.moveShip();
		Gdx.gl.glClearColor(0.5f, 0.65f, 0.5f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.begin();
		batch.draw(craft, ship.getX(), ship.getY());
		batch.draw(speed, (ship.getX()-(WIDTH/2)), ship.getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}

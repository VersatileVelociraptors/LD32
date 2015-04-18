package io.github.versatilevelociraptors.ld32.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class PlayState extends State {

	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;

	public PlayState(GameStateManager manager){
		super(manager);
		map = new TmxMapLoader().load("assets/maps/test.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	protected void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		mapRenderer.setView(cam);
		mapRenderer.render();
	}
	@Override
	protected void update(float dt){

	}

	@Override
	protected void dispose() {

	}
}


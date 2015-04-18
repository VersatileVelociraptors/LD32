package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class PlayState extends State {

	private Level level;

	public PlayState(GameStateManager manager){
		super(manager);
		level = new Level("assets/levels/level01.lvl");
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
	}

	@Override
	protected void render() {
		cam.update();
        batch.setProjectionMatrix(cam.combined);
        
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.396f, 0.612f, 0.937f, 1.0f);
		level.render(batch);
	}

	@Override
	protected void update(float dt){
		level.update(dt);
	}

	@Override
	protected void dispose() {
		level.dispose();
	}

}


package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

	protected GameStateManager manager;
	protected SpriteBatch batch;
	protected OrthographicCamera cam;

	protected State(GameStateManager manager) {
		this.manager = manager;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, LudumDare32.getWidth(), LudumDare32.getHeight());
		batch = manager.getSpriteBatch();
	}

	protected abstract void render(SpriteBatch sb);
	protected abstract void update(float dt);
	protected abstract void dispose();

}

package io.github.versatilevelociraptors.ld32.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class PlayState extends State {

	public PlayState(GameStateManager manager){
		super(manager);
	}

	@Override
	protected void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

	}
	
	@Override
	protected void update(float dt){

	}

	@Override
	protected void dispose() {

	}
}


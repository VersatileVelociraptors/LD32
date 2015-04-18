package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PlayState extends State {

	Level level;
	
	public PlayState(GameStateManager manager){
		super(manager);
		level = new Level("assets/levels/level01.lvl");
	}

	@Override
	protected void render(SpriteBatch sb) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		level.render(sb);
	}
	
	@Override
	protected void update(float dt){

	}

	@Override
	protected void dispose() {

	}

}


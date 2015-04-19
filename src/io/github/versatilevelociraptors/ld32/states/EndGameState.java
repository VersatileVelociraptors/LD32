package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;
import io.github.versatilevelociraptors.ld32.audio.AudioManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EndGameState extends State{

	private Sprite rekt;
	private AudioManager music;
	
	public EndGameState(GameStateManager manager) {
		super(manager);
		rekt = new Sprite(new Texture("assets/images/Rekt.png"));
		rekt.flip(false, true);
		music = new AudioManager();
		music.addAllMusicInAssets();
	}

	@Override
	protected void render() {
        batch.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		batch.begin();
		batch.draw(rekt, LudumDare32.getWidth()/2 - rekt.getWidth()/2, LudumDare32.getHeight()/2 - rekt.getHeight()/2);
		batch.end();
	}

	@Override
	protected void update(float dt) {
		music.play(0);
	}

	@Override
	public void dispose() {
		
	}

}

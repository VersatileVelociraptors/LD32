package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.ui.ImageButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuState extends State{
	
	private ImageButton playButton;
	
	public MainMenuState(GameStateManager manager) {
		super(manager);
		
		playButton = new ImageButton(new Texture("assets/images/play.png"), 100, 100);
	}

	@Override
	protected void render() {
        batch.setProjectionMatrix(cam.combined);
        
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		batch.begin();
		playButton.render(batch);
		batch.end();
		
	}

	@Override
	protected void update(float dt) {
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			playButton.update(Gdx.input.getX(), Gdx.input.getY());
		if(playButton.isClicked()){
			manager.pop();
			manager.push(new PlayState(manager));
		}
	}

	@Override
	protected void dispose() {
		
	}

}

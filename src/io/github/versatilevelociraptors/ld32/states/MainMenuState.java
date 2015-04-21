package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.ui.ExitButton;
import io.github.versatilevelociraptors.ld32.ui.StartButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

public class MainMenuState extends State{
	
	private StartButton playButton;
	private ExitButton exitButton;
	
	public MainMenuState(GameStateManager manager) {
		super(manager);
		
		playButton = new StartButton(100, 100);
		exitButton = new ExitButton(100 , 200);
		
	}

	@Override
	protected void render() {
        batch.setProjectionMatrix(cam.combined);
        
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		batch.begin();
		playButton.render(batch);
		exitButton.render(batch);
		batch.end();
		
	}

	@Override
	protected void update(float dt) {
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			playButton.update(Gdx.input.getX(), Gdx.input.getY());
			exitButton.update(Gdx.input.getX(), Gdx.input.getY());
		}
		
		if(playButton.isClicked()){
			manager.pop();
			manager.push(new PlayState(manager));
		}
		if(exitButton.isClicked()){
			manager.pop();
			System.exit(0);
		}
	}

	@Override
	public void dispose() {
		
	}

}

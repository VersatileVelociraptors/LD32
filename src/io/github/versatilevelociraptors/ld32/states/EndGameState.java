package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;
import io.github.versatilevelociraptors.ld32.audio.MusicManager;
import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.ui.ExitButton;
import io.github.versatilevelociraptors.ld32.ui.MainMenuButton;
import io.github.versatilevelociraptors.ld32.ui.RestartButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EndGameState extends State{

	private Sprite rekt;
	private MusicManager music;
	private BitmapFont score;
	private Level level;
	
	private RestartButton restartButton;
	private ExitButton exitButton;
	private MainMenuButton mainMenuButton;
	
	public EndGameState(GameStateManager manager, Level level) {
		super(manager);
		rekt = new Sprite(new Texture("assets/images/Rekt.png"));
		rekt.flip(false, true);
		music = new MusicManager();
		music.addAllMusicInAssets();
		score = new BitmapFont(true);
		this.level = level;
		score.setColor(Color.GREEN);
		
		restartButton = new RestartButton( LudumDare32.getWidth()/2 - new Texture("assets/images/playagain.png").getWidth()/2 , 150 + rekt.getHeight());
		exitButton = new ExitButton(LudumDare32.getWidth()/2 - new Texture("assets/images/exitbutton.png").getWidth()/2 , 250 + rekt.getHeight());
		mainMenuButton = new MainMenuButton(LudumDare32.getWidth()/2 - new Texture("assets/images/exitbutton.png").getWidth()/2 , 350 + rekt.getHeight());
		
	}

	@Override
	protected void render() {
        batch.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		batch.begin();
		batch.draw(rekt, LudumDare32.getWidth()/2 - rekt.getWidth()/2, 100);
		score.draw(batch, "Score: " + level.getKills(), LudumDare32.getWidth()/2 - score.getXHeight()*2, 100 + rekt.getHeight());
		exitButton.render(batch);
		restartButton.render(batch);
		mainMenuButton.render(batch);
		batch.end();
	}

	@Override
	protected void update(float dt) {
		music.play("endscreen");
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			restartButton.update(Gdx.input.getX(), Gdx.input.getY());
			exitButton.update(Gdx.input.getX(), Gdx.input.getY());
			mainMenuButton.update(Gdx.input.getX(), Gdx.input.getY());
		}
		if(restartButton.isClicked()){
			music.stop("endscreen");
			manager.pop();
			manager.push(new PlayState(manager));
		}
		if(exitButton.isClicked()){
			music.stop("endscreen");
			manager.pop();
			System.exit(0);
		}
		if(mainMenuButton.isClicked()){
			music.stop("endscreen");
			manager.pop();
			manager.push(new MainMenuState(manager));
		}
		
	}

	@Override
	public void dispose() {
		music.dispose();
	}

}

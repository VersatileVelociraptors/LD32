package io.github.versatilevelociraptors.ld32;

import io.github.versatilevelociraptors.ld32.states.GameStateManager;
import io.github.versatilevelociraptors.ld32.states.PlayState;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class LudumDare32  implements ApplicationListener{

	public static final String TITLE = "LudumDare32";
	private static final int WIDTH = 640;
	private static final int HEIGHT = 360;
	public static final double SCALE = 1.5;

	public static final float STEP = 1/60f;
	private static float frameTime;
	

	private GameStateManager manager;

	public static void main(String[] args) {
		LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();

		//set window options
		configuration.title = TITLE;
		configuration.width = getWidth();
		configuration.height = getHeight();
		
		//set fps options
		configuration.vSyncEnabled = false;
		configuration.foregroundFPS = 0;
		configuration.backgroundFPS = 0;

		new LwjglApplication(new LudumDare32() , configuration);
	}

	@Override
	public void create() {	
		manager = new GameStateManager();
		manager.push(new PlayState(manager));
	}

	@Override
	public void render() {
		frameTime +=  Gdx.graphics.getDeltaTime();
		while(frameTime >= STEP){
			frameTime -= STEP;
			manager.update(STEP);
			manager.render();
		}
	}

	@Override
	public void dispose() {
		manager.dispose();
	}

	public static int getHeight(){
		return (int)(HEIGHT*SCALE);
	}

	public static int getWidth(){
		return (int)(WIDTH*SCALE);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}

}

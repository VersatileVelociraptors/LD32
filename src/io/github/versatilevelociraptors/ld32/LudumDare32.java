package io.github.versatilevelociraptors.ld32;

import io.github.versatilevelociraptors.ld32.states.GameStateManager;
import io.github.versatilevelociraptors.ld32.states.PlayState;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class LudumDare32  implements ApplicationListener{

	public static final String TITLE = "LudumDare32";
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	public static final double SCALE = 1;

	public static final double NS = 1000000000.0 / 60.0;
	
	private GameStateManager manager;
	
	private double delta;
	private long lastTime;
	private int updates;
	private long timer;

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
		delta = 0;
		lastTime = System.nanoTime();
		timer = System.currentTimeMillis();
	}

	@Override
	public void render() {
		long now = System.nanoTime();
		delta += now - lastTime;
		lastTime = now;
		
		// update if it is time to
		if(delta >= NS){
			manager.update((float) delta);
			updates++;
			delta -= NS;
		}
		manager.render();
		
		if(System.currentTimeMillis() - timer > 1000){
			timer += 1000;
			System.out.println(updates + "UPS");
			updates = 0;
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

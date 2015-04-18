package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {

	private SpriteBatch batch;
	private Stack<State> states;
	private BitmapFont fps;

	public GameStateManager(){
		batch = new SpriteBatch();
		states = new Stack<State>();
		fps = new BitmapFont();
	}

	public SpriteBatch getSpriteBatch() { 
		return batch; 
	}

	public void set(State state) {
		states.pop();
		states.push(state);
	}

	public void push(State state) {
		states.push(state);
	}

	public void pop() {
		states.pop();
	}

	public void peek() {
		states.peek();
	}

	public void render() {
		states.peek().render();
		batch.begin();
		fps.draw(batch, Gdx.graphics.getFramesPerSecond() + " FPS", 0, LudumDare32.getHeight());
		batch.end();
	}
	
	public void update(float dt){
		states.peek().update(dt);
	}

	public void dispose() {
		states.peek().dispose();
		batch.dispose();
		fps.dispose();
	}
}

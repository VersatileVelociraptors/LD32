package io.github.versatilevelociraptors.ld32.states;

import io.github.versatilevelociraptors.ld32.LudumDare32;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {

	private SpriteBatch batch;
	private Stack<State> states;

	public GameStateManager(){
		batch = new SpriteBatch();
		states = new Stack<State>();
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

	public State peek() {
		return states.peek();
	}

	public void render() {
		peek().render();
	}
	
	public void update(float dt){
		states.peek().update(dt);
	}

	public void dispose() {
		for(State state : states)
			state.dispose();
		batch.dispose();
	}
}

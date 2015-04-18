package io.github.versatilevelociraptors.ld32.states;

import java.util.Stack;

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

	public void peek() {
		states.peek();
	}

	public void render() {
		states.peek().render();
	}

	public void dispose() {
		states.peek().dispose();
	}
}

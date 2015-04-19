package io.github.versatilevelociraptors.ld32.states;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class GameStateManager implements Disposable {

	private SpriteBatch batch;
	private Stack<State> states;
	private BitmapFont fps;

	public GameStateManager(){
		batch = new SpriteBatch();
		states = new Stack<State>();
		fps = new BitmapFont(true);
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
		batch.begin();
		fps.draw(batch, Gdx.graphics.getFramesPerSecond() + " FPS", 0, 0);
		batch.end();
	}
	
	public void update(float dt){
		states.peek().update(dt);
	}

	public void dispose() {
		for(State state : states)
			state.dispose();
		batch.dispose();
		fps.dispose();
	}
}

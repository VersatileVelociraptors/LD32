package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity extends Sprite{

	protected Level level;
	
	protected int speed = 10;
	protected int diag;
	
	private boolean isAlive = true;
	
	/**
	 * @param image
	 */
	public Entity(Texture image, Level level) {
		super(image);
		this.level = level;
		
		diag = Math.round((float) ((speed * (Math.sqrt(2))/4.0)));
	}

	public abstract void update(float dt);

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
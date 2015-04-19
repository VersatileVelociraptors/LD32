package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity extends Sprite{

	protected Level level;
	
	protected int speed;
	protected int diag;
	
	protected boolean isAlive;
	
	/**
	 * @param image
	 */
	public Entity(Texture image, Level level) {
		super(image);
		this.level = level;
		
		speed = 10;
		diag = Math.round((float) ((speed * (Math.sqrt(2))/2)));
	}

	public abstract void update(float dt);
	
}
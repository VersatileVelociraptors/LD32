package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public abstract class Entity extends Sprite implements Disposable{

	protected Texture image;
	
	protected Level level;
	
	protected int speed;
	protected int diag;
	
	protected boolean isAlive;
	
	/**
	 * @param image
	 */
	public Entity(Texture image, Level level) {
		super(image);
		this.setImage(image);
		this.level = level;
		
		speed = 10;
		diag = Math.round((float) ((speed * (Math.sqrt(2)))/2.0f));
	}
	
	/**
	 * @return the image
	 */
	protected Texture getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	protected void setImage(Texture image) {
		this.image = image;
	}

	public abstract void update(float dt);
	
	
	@Override
	public void dispose() {
		image.dispose();
	}
}
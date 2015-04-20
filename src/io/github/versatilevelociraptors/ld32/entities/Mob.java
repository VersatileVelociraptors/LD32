package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;

public abstract class Mob extends Entity {
	
	private double health = 100;

	public Mob(Texture image, Level level) {
		super(image, level);
	}
	
	public void takeDamage(double damage){
		health -= damage;
		if(health == 0){
			// we're dead!
			setAlive(false);
		}
	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	protected void setHealth(double health) {
		this.health = health;
	}

}

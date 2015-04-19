package io.github.versatilevelociraptors.ld32.weapons;

import io.github.versatilevelociraptors.ld32.entities.Entity;
import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Weapon {
	
	private Sprite sprite;
	private final int damage;
	private final int projectileSpeed;
	private final int rateOfFire;
	private final int range;
	private int ammo;

	public Weapon(int damage, int projectileSpeed, int ammo, int rateOfFire, int range) {
		this.damage = damage;
		this.projectileSpeed = projectileSpeed;
		this.ammo = ammo;
		this.rateOfFire = rateOfFire;
		this.range = range;
	}
	
	public class Projectile extends Entity{
		
		public Projectile(Texture image, Level level) {
			super(image, level);
		}

		public int getDamage(){
			return damage;
		}

		@Override
		public void update(float dt) {
			
		}
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	protected void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the projectileSpeed
	 */
	public int getProjectileSpeed() {
		return projectileSpeed;
	}

	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * @param ammo the ammo to set
	 */
	protected void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	/**
	 * @return the ammo
	 */
	public int getAmmo() {
		return ammo;
	}

	/**
	 * @return the rateOfFire
	 */
	public int getRateOfFire() {
		return rateOfFire;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

}

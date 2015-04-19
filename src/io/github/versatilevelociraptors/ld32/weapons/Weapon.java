package io.github.versatilevelociraptors.ld32.weapons;

import io.github.versatilevelociraptors.ld32.entities.Entity;
import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon {
	
	private Texture image;
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
		
		private Vector2 vector;
		private float distanceTraveled;
		
		public Projectile(Texture image, Level level, Vector2 vector, float distanceTravelled) {
			super(image, level);
			this.vector = vector;
			this.distanceTraveled = distanceTravelled;
			ammo--;
		}

		public int getDamage(){
			return damage;
		}

		@Override
		public void update(float dt) {
			if(distanceTraveled < range){
				setVector(getVector().add(vector));
				distanceTraveled += speed;
			}else{
				isAlive = false;
			}
		}
		
		/**
		 * @return the vector
		 */
		public Vector2 getVector() {
			return vector;
		}
		
		/**
		 * @param vector the vector to set
		 */
		public void setVector(Vector2 vector) {
			this.vector = vector;
		}
		
	}
	
	/**
	 * @return the image
	 */
	public Texture getImage() {
		return image;
	}
	
	/**
	 * @param image the image to set
	 */
	public void setImage(Texture image) {
		this.image = image;
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

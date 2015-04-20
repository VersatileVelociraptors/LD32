package io.github.versatilevelociraptors.ld32.weapons;

import io.github.versatilevelociraptors.ld32.entities.Entity;
import io.github.versatilevelociraptors.ld32.entities.Velociraptor;
import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon {
	
	private Sprite image;
	private Texture projectileImage;
	private String name;
	private final int damage;
	private final int projectileSpeed;
	private final int rateOfFire;
	private final int range;
	private int ammo;

	public Weapon(int damage, int projectileSpeed, int ammo, int rateOfFire, int range, Texture projectileImage, Sprite image, String name) {
		this.damage = damage;
		this.projectileSpeed = projectileSpeed;
		this.ammo = ammo;
		this.rateOfFire = rateOfFire;
		this.range = range;
		this.projectileImage = projectileImage;
		this.image = image;
		this.name = name;
	}

	public class Projectile extends Entity{
		
		private Vector2 vector;
		private float distanceTravelled;
		private int dir;
		
		public Projectile(Texture image, Level level, Vector2 vector, int bulletSpeed) {
			super(image, level);
			this.vector = vector;
			this.speed = bulletSpeed;
			ammo--;
		}

		public int getDamage(){
			return damage;
		}
		
		public void setDir(int dir){
			this.dir = dir;
		}

		@Override
		public void update(float dt) {
			setRotation(getRotation() - 10);
			
			if(distanceTravelled < range){
				if(dir == 0)
					setY(getY() - speed);
				if(dir == 2)
					setX(getX() + speed);
				if(dir == 4)
					setY(getY() + speed);
				if(dir == 6)
					setX(getX() - speed);
				
				distanceTravelled += speed;
			}else{
				setAlive(false);
			}
			
			// pwn the noobs (damage velociraptors)
			for(Velociraptor velociraptor : level.getEnemies()){
				if(getBoundingRectangle().overlaps(velociraptor.getBoundingRectangle())){
					velociraptor.takeDamage(damage);
				}
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
	public Sprite getImage() {
		return image;
	}
	
	/**
	 * @param image the image to set
	 */
	public void setImage(Sprite image) {
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

	/**
	 * @return the projectileImage
	 */
	public Texture getProjectileImage() {
		return projectileImage;
	}

	/**
	 * @param projectileImage the projectileImage to set
	 */
	protected void setProjectileImage(Texture projectileImage) {
		this.projectileImage = projectileImage;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}

}

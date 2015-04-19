package io.github.versatilevelociraptors.ld32.weapons;

import java.util.ArrayList;

import io.github.versatilevelociraptors.ld32.entities.Entity;
import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.level.Tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon {
	
	private Texture image;
	private Texture projectileImage;
	private final int damage;
	private final int projectileSpeed;
	private final int rateOfFire;
	private final int range;
	private final int wallOffset = 1540;
	private int ammo;

	public Weapon(int damage, int projectileSpeed, int ammo, int rateOfFire, int range, Texture projectileImage) {
		this.damage = damage;
		this.projectileSpeed = projectileSpeed;
		this.ammo = ammo;
		this.rateOfFire = rateOfFire;
		this.range = range;
		this.projectileImage = projectileImage;
	}

	public class Projectile extends Entity{
		
		private Vector2 vector;
		private float distanceTravelled;
		private int dir;
		private int speed;
		private ArrayList<Vector2> walls;
		
		public Projectile(Texture image, Level level, Vector2 vector, int bulletSpeed) {
			super(image, level);
			this.vector = vector;
			this.speed = bulletSpeed;
			isAlive = true;
			walls = level.getWalls();
			ammo--;
		}

		public int getDamage(){
			return damage;
		}
		
		public boolean alive(){
			return isAlive;
		}
		
		public void setDir(int dir){
			this.dir = dir;
		}

		@Override
		public void update(float dt) {
			
			for(Vector2 pos : walls){
				Rectangle potatoRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
				Rectangle tileRect = new Rectangle(pos.x, pos.y, Tile.TILE_SIZE, Tile.TILE_SIZE);

				if(potatoRect.getY() >= tileRect.getY() + level.getYOffset() - wallOffset && potatoRect.getY() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE){
					if(potatoRect.getX() >= tileRect.getX() + level.getXOffset() - wallOffset && potatoRect.getX() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE)
						setY(getY() -speed);
				}else
					if(potatoRect.getY() + potatoRect.getHeight()>= tileRect.getY() + level.getYOffset() - wallOffset && potatoRect.getY() + potatoRect.getHeight() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE){
						if(potatoRect.getX() >= tileRect.getX() + level.getXOffset() - wallOffset && potatoRect.getX() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE)
							setY(getY() + speed);
					}
				if(potatoRect.getX() >= tileRect.getX() + level.getXOffset() - wallOffset && potatoRect.getX() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE){
					if(potatoRect.getY() >= tileRect.getY() + level.getYOffset() - wallOffset && potatoRect.getY() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE)
						setX(getX()-speed);
				}else{
					if(potatoRect.getX() + potatoRect.getWidth()>= tileRect.getX() + level.getXOffset() - wallOffset && potatoRect.getX() + potatoRect.getWidth() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE){
						if(potatoRect.getY() >= tileRect.getY() + level.getYOffset() - wallOffset && potatoRect.getY() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE)
							setX(getX() + speed);
					}
				}
			}
			
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

}

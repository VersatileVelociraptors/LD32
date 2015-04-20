package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.level.Tile;
import io.github.versatilevelociraptors.ld32.weapons.PotatoGun;
import io.github.versatilevelociraptors.ld32.weapons.Weapon;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Mob{

	private ArrayList<Vector2> walls;
	private Weapon weapon;
	private final int wallOffset = 1540;
	private int dir;
	private long lastShotTime;

	public Player(Level level) {
		super(new Texture("assets/images/player.png"), level);
		walls = level.getWalls();
		weapon = new PotatoGun();
	}

	public void move(int x, int y){
		level.setXOffset(level.getXOffset() + x);
		level.setYOffset(level.getYOffset() + y);
	}

	public int getDir(){
		return dir;
	}

	@Override
	public void update(float dt) {
		for(Vector2 pos : walls){
			Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
			Rectangle tileRect = new Rectangle(pos.x, pos.y, Tile.TILE_SIZE, Tile.TILE_SIZE);

			if(playerRect.getY() >= tileRect.getY() + level.getYOffset() - wallOffset && playerRect.getY() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE){
				if(playerRect.getX() >= tileRect.getX() + level.getXOffset() - wallOffset && playerRect.getX() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE)
					move(0,-speed);
			}else
				if(playerRect.getY() + playerRect.getHeight()>= tileRect.getY() + level.getYOffset() - wallOffset && playerRect.getY() + playerRect.getHeight() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE){
					if(playerRect.getX() >= tileRect.getX() + level.getXOffset() - wallOffset && playerRect.getX() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE)
						move(0,speed);
				}
			if(playerRect.getX() >= tileRect.getX() + level.getXOffset() - wallOffset && playerRect.getX() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE){
				if(playerRect.getY() >= tileRect.getY() + level.getYOffset() - wallOffset && playerRect.getY() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE)
					move(-speed,0);
			}else{
				if(playerRect.getX() + playerRect.getWidth()>= tileRect.getX() + level.getXOffset() - wallOffset && playerRect.getX() + playerRect.getWidth() <= tileRect.getX() + level.getXOffset() - wallOffset + Tile.TILE_SIZE){
					if(playerRect.getY() >= tileRect.getY() + level.getYOffset() - wallOffset && playerRect.getY() <= tileRect.getY() + level.getYOffset() - wallOffset + Tile.TILE_SIZE)
						move(speed,0);
				}
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
				this.setRotation(315);
				move(diag, diag);
				dir = 7;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				this.setRotation(225);
				move(diag, -diag);
				dir = 5;
			}
			else if(!(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) 
					&& !(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))){
				move(speed, 0);
				this.setRotation(270);
				dir = 6;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
				this.setRotation(45);
				move(-diag, diag);
				dir = 1;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				this.setRotation(135);
				move(-diag, -diag);
				dir = 3;
			}
			else if(!(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) 
					&& !(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))){
				move(-speed, 0);
				this.setRotation(90);
				dir = 2;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				move(diag,diag);
				this.setRotation(315);
				dir = 7;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				move(-diag,diag);
				this.setRotation(45);
				dir = 1;
			}
			else if(!(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
					&& !(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))){
				move(0,speed);
				this.setRotation(0);
				dir = 0;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				move(diag,-diag);
				this.setRotation(225);
				dir = 5;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				move(-diag,-diag);
				this.setRotation(135);
				dir = 3;
			}
			else if(!(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
					&& !(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))){
				move(0,-speed);
				this.setRotation(180);
				dir = 4;
			}
		}

		// shoot shit
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && System.currentTimeMillis() - lastShotTime >= 1000 / weapon.getRateOfFire()){
			Weapon.Projectile potato = weapon.new Projectile(new Texture("assets/images/potato.png"), level, new Vector2(3, 0), 20);
			potato.setX(this.getX() + 20);
			potato.setY(this.getY() + 16);
			potato.setDir(getDir());

			level.getProjectiles().add(potato);

			lastShotTime = System.currentTimeMillis();
		}
	}

	/**
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
	 */
	protected void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}

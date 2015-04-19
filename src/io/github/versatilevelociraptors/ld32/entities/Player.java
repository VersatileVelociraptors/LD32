package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.level.Tile;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{

	private int dir;
	private ArrayList<Vector2> walls;
	private final int wallOffset = 1540;

	public Player(Level level) {
		super(new Texture("assets/images/player.png"), level);
		walls = level.getWalls();
	}

	public void move(int x, int y){
		level.setXOffset(level.getXOffset() + x);
		level.setYOffset(level.getYOffset() + y);
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
			move(speed, 0);
			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
				this.rotate(-this.getRotation() + 315);
				move(-diag, 0);
				dir = 7;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				this.rotate(-this.getRotation() + (this.getRotation() + 270)/2);
				move(-diag, 0);
				dir = 5;
			}
			else if(this.getRotation() != 270){
				this.rotate(-this.getRotation() + 270);
				dir = 6;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			move(-speed, 0);
			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
				this.rotate(-this.getRotation() + 315);
				move(diag, 0);
				dir = 1;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				this.rotate(-this.getRotation() + (this.getRotation() + 270)/2);
				move(diag, 0);
				dir = 3;
			}
			else if(this.getRotation() != 90){
				this.rotate(-this.getRotation() + 90);	
				dir = 2;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			move(0,speed);
			if(this.getRotation() != 0){
				this.rotate(-this.getRotation() + 0);
				dir = 0;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			move(0,-speed);

			if(this.getRotation() != 180){
				this.rotate(-this.getRotation() + 180);		
				dir = 4;
			}
		}
	}


}

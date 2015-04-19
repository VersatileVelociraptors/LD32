package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.level.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity{
	
	private int dir = 0;

	public Player(Level level) {
		super(new Texture("assets/images/player.png"), level);
	}

	public boolean isColliding(int x, int y){
		boolean colliding = false;
		if(level.tileType(((int)(this.getX() - x/speed*(dir==6?5:64))), (int)this.getY()-y/speed*(dir==0?5:64)) == Tile.WALL_TILE) colliding = true;
		return colliding;
	}
	
	public void move(int x, int y){
				
		if(!isColliding(x,y)){
			level.setXOffset(level.getXOffset() + x);
			level.setYOffset(level.getYOffset() + y);
		}
	}
	
	@Override
	public void update(float dt) {
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			move(speed, 0);
				if(this.getRotation() != 270){
					this.rotate(-this.getRotation() + 270);
					dir = 6;
				}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			move(-speed, 0);
				if(this.getRotation() != 90){
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

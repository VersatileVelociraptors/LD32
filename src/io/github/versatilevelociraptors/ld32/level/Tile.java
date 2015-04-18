package io.github.versatilevelociraptors.ld32.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
	
	protected int x,y;
	protected int xOffset, yOffset;
	protected Texture sprite;
	
	public static final int GRASS_TILE = 0;
	public static final int DIRT_TILE = 1;
	public static final int FLOOR_TILE = 2;
	public static final int WALL_TILE = 3;
	public static final int WATER_TILE = 4;
	
	public Tile(){
		
	}
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Tile(Texture sprite){
		this.sprite = sprite;
	}
	
	public void offset(int x, int y){
		this.xOffset = x;
		this.yOffset = y;
	}
	
	public abstract void render(SpriteBatch sb);
	
	public boolean solid(){
		return false;
	}
	
	public void dispose(){
		sprite.dispose();
	}
}

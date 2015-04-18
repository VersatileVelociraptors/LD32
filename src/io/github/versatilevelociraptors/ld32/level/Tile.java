package io.github.versatilevelociraptors.ld32.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
	
	protected int xOffset, yOffset;
	
	public static final int GRASS_TILE = 0;
	public static final int DIRT_TILE = 1;
	public static final int FLOOR_TILE = 2;
	public static final int WALL_TILE = 3;
	public static final int WATER_TILE = 4;
	
	private static ArrayList<Texture> textures;
	
	public Tile(){
		textures = new ArrayList<Texture>();
		textures.add(new Texture("assets/images/grass.png"));
		textures.add(new Texture("assets/images/dirt.png"));
		textures.add(new Texture("assets/images/floor.png"));
	}
	
	public void offset(int x, int y){
		this.xOffset = x;
		this.yOffset = y;
	}
	
	public void render(SpriteBatch sb, int texture, int x, int y){
		sb.draw(textures.get(texture), x, y);
	}
	
	public boolean solid(){
		return false;
	}
	
	public void dispose(){
		for(int i = 0; i < textures.size(); i++){
			textures.get(i).dispose();
		}
	}
}

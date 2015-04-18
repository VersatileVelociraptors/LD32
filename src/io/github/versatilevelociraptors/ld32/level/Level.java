package io.github.versatilevelociraptors.ld32.level;

import io.github.versatilevelociraptors.ld32.LudumDare32;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	
	private int width, height;
	private int[] tileMap; 
	
	private int xOffset, yOffset;
	
	private int speed;
	private Tile tiles;
	
	public Level(String path){
		speed = 5;
		tiles = new Tile();
		loadLevel(path);
		
		xOffset = -getWidthInPixels()/2;
		yOffset = -getHeightInPixels()/2;
}
	
	public void loadLevel(String path){
		String line = null;
		try (BufferedReader levelInput = new BufferedReader(new FileReader(path))){
			width = Integer.parseInt(levelInput.readLine());
			height = Integer.parseInt(levelInput.readLine());
			
			tileMap = new int[width * height];
			
			line = levelInput.readLine();
			
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					tileMap[i*width + j] = Integer.parseInt(line.charAt(j) + "");
				}
				line = levelInput.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void update(float dt){
		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
			xOffset+=speed;
		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			xOffset-=speed;
		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))
			yOffset-=speed;
		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))
			yOffset+=speed;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		int xp, yp;
		for(int i = 0; i < tileMap.length; i++){
			xp = Tile.TILE_SIZE*(i%width) + xOffset;
			yp = Tile.TILE_SIZE*(i/width) + yOffset;
			if(xp + Tile.TILE_SIZE < 0 || xp >= LudumDare32.getWidth()) continue;
			if(yp + Tile.TILE_SIZE < 0 || yp >= LudumDare32.getHeight()) continue;
			switch(tileMap[i]){
				case Tile.GRASS_TILE:						
					tiles.render(sb, Tile.GRASS_TILE, xp, yp);
				break;
				case Tile.DIRT_TILE:				
					tiles.render(sb, Tile.DIRT_TILE, xp, yp);
				break;
				case Tile.FLOOR_TILE:
					tiles.render(sb, Tile.FLOOR_TILE, xp, yp);
				break;
				case Tile.WALL_TILE:
					tiles.render(sb, Tile.WALL_TILE, xp, yp);
				break;
			}
		}
		sb.end();
	}
	
	public int getWidthInPixels(){
		return width * Tile.TILE_SIZE;
	}
	
	public int getHeightInPixels(){
		return height * Tile.TILE_SIZE;
	}
		
	public int getWidthInTiles(){
		return width;
	}
	
	public int getHeightInTiles(){
		return height;
	}
	
	public void dispose(){
		tiles.dispose();
	}
}

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
	}
	
	public void loadLevel(String path){
		String line = null;
		try (BufferedReader levelInput = new BufferedReader(new FileReader(path))){
			width = Integer.parseInt(levelInput.readLine());
			height = Integer.parseInt(levelInput.readLine());
			
			tileMap = new int[width * height];
			
			line = levelInput.readLine();
			
			for(int i = 0; i < line.length(); i++){
				tileMap[i] = Integer.parseInt(line.charAt(i) + "");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void update(float dt){
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			xOffset+=speed;
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			xOffset-=speed;
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			yOffset-=speed;
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			yOffset+=speed;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		int xp, yp;
		for(int i = 0; i < tileMap.length; i++){
			xp = 64*(i%width);
			yp = 64*(i/width);
			if(xp < 0 || xp >= LudumDare32.getWidth()) break;
			if(yp < 0 || yp >= LudumDare32.getHeight()) break;
			switch(tileMap[i]){
				case Tile.GRASS_TILE:						
					tiles.render(sb, Tile.GRASS_TILE, xp + xOffset, yp + yOffset);
				break;
				case Tile.DIRT_TILE:				
					tiles.render(sb, Tile.DIRT_TILE, xp + xOffset, yp + yOffset);
				break;
				case Tile.FLOOR_TILE:
					tiles.render(sb, Tile.FLOOR_TILE, xp + xOffset, yp + yOffset);
				break;
			}
		}
		sb.end();
	}
	
	public void dispose(){
		tiles.dispose();
	}
}

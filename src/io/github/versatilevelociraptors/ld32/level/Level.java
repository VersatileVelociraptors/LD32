package io.github.versatilevelociraptors.ld32.level;

import io.github.versatilevelociraptors.ld32.LudumDare32;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	
	private int width, height;
	private int[] tileMap; 

	private ArrayList<Tile> tiles;
	
	private int xOffset, yOffset;
	
	private int speed;
	
	public Level(String path){
		tiles = new ArrayList<Tile>();
		speed = 5;
		loadLevel(path);
	}
	
	public void loadLevel(String path){
		String line = null;
		try (BufferedReader levelInput = new BufferedReader(new FileReader(path))){
			width = Integer.parseInt(levelInput.readLine());
			height = Integer.parseInt(levelInput.readLine());
			
			tileMap = new int[width * height];
			
			line = levelInput.readLine();
			int xp, yp;
			
			for(int i = 0; i < line.length(); i++){
				tileMap[i] = Integer.parseInt(line.charAt(i) + "");
				xp = 64*(i%width);
				yp = 64*(i/width);
				if(xp < 0 || xp >= LudumDare32.getWidth()) break;
				if(yp < 0 || yp >= LudumDare32.getHeight()) break;
				switch(tileMap[i]){
					case Tile.GRASS_TILE:						
						tiles.add(new GrassTile(xp, yp));
					break;
					case Tile.DIRT_TILE:						
						tiles.add(new DirtTile(xp, yp ));
					break;
					case Tile.FLOOR_TILE:
						tiles.add(new FloorTile(xp, yp));
					default:
						System.out.println(Integer.parseInt(line.charAt(i)+""));
					break;
				}
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
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).offset(xOffset, yOffset);
			tiles.get(i).render(sb);
		}
		sb.end();
	}
	
	public void dispose(){
		for(int i = 0; i < tiles.size(); i++)
			tiles.get(i).dispose();
	}
}

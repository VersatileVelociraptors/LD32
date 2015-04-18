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
	private int[] tile_map; 

	private ArrayList<Tile> tiles;
	
	private int xOffset, yOffset;
	
	public Level(String path){
		tiles = new ArrayList<Tile>();
		loadLevel(path);
	}
	
	public void loadLevel(String path){
		String line = null;
		try (BufferedReader levelInput = new BufferedReader(new FileReader(path))){
			width = Integer.parseInt(levelInput.readLine());
			height = Integer.parseInt(levelInput.readLine());
			
			tile_map = new int[width * height];
			
			line = levelInput.readLine();
			int xp, yp;
			
			for(int i = 0; i < line.length(); i++){
				tile_map[i] = Integer.parseInt(line.charAt(i) + "");
				switch(tile_map[i]){
					case Tile.GRASS_TILE:
						xp = 64*(i%width);
						yp = 64*(i/width);
						if(xp < 0 || xp >= LudumDare32.getWidth()) break;
						if(yp < 0 || yp >= LudumDare32.getHeight()) break;
						
						tiles.add(new GrassTile(xp, yp));
					break;
					case Tile.DIRT_TILE:
						xp = 64*(i%width);
						yp = 64*(i/width);
						if(xp < 0 || xp >= LudumDare32.getWidth()) break;
						if(yp < 0 || yp >= LudumDare32.getHeight()) break;
						
						tiles.add(new DirtTile(xp, yp ));
					break;
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
			xOffset++;
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			xOffset--;
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			yOffset--;
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			yOffset++;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).offset(xOffset, yOffset);
			tiles.get(i).render(sb);
		}
		sb.end();
	}
}

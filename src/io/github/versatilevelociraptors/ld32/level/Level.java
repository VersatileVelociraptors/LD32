package io.github.versatilevelociraptors.ld32.level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	
	private int width, height;
	private int[] tile_map; 

	private ArrayList<Tile> tiles;

	private BufferedReader levelInput;
	
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
			
			for(int i = 0; i < line.length(); i++){
				tile_map[i] = Integer.parseInt(line.charAt(i) + "");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		for(int i = 0; i < width * height; i++){
			switch(Integer.parseInt(line.charAt(i)+"")){
				case Tile.GRASS_TILE:
					tiles.add(new GrassTile((i%width)*64, 64*(i/width)));
				break;
				case Tile.DIRT_TILE:
					tiles.add(new DirtTile((i%width)*64, 64*(i/width)));
				break;
				default:
					System.out.println(Integer.parseInt(line.charAt(i)+""));
				break;
			}
		}
	}
	
	public void render(SpriteBatch sb){
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).render(sb);
		}
	}
}

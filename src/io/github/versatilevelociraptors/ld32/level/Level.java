package io.github.versatilevelociraptors.ld32.level;

import io.github.versatilevelociraptors.ld32.LudumDare32;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	
	private int width, height;
	private int[] tileMap; 
	
	private int xOffset, yOffset;
	
	private int speed;
	private Tile tiles;
	private Sprite player;
	
	private int diag;
	
	public Level(String path){
		speed = 10;
		tiles = new Tile();
		loadLevel(path);
		
		xOffset = -getWidthInPixels()/2;
		yOffset = -getHeightInPixels()/2;

		diag = Math.round((float) ((speed * (Math.sqrt(2)))/2.0f));
	}
	
	public void loadLevel(String path){
		String line = null;
		try (BufferedReader levelInput = new BufferedReader(new FileReader(path))){
			levelInput.readLine();
			line = levelInput.readLine();
			width = Integer.parseInt(line.substring(line.indexOf("=")+1));
			line = levelInput.readLine();
			height = Integer.parseInt(line.substring(line.indexOf("=")+1));
			
			tileMap = new int[width * height];
			
			while(!levelInput.readLine().equals("data=")){}
			line = levelInput.readLine();
			
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width*2; j+=2){
					tileMap[i*width + j/2] = Integer.parseInt(line.charAt(j) + "") - 1;
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
		int oldXOffset = xOffset, oldYOffset = yOffset;

		if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			xOffset+=speed;
			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
				player.rotate(-player.getRotation() + 315);
				xOffset-=speed-diag;
			}

			else if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				player.rotate(-player.getRotation() + (player.getRotation() + 270)/2);
				xOffset-=speed-diag;
			}

			else{

				if(player.getRotation() != 270){
					player.rotate(-player.getRotation() + 270);
				}
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			xOffset-=speed;

			if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)
					|| Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				player.rotate(-player.getRotation() + (player.getRotation() + 90)/2);
				xOffset+=speed-diag;
			}

			else{

				if(player.getRotation() != 90){
					player.rotate(-player.getRotation() + 90);				
				}
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
			yOffset+=speed;

			if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				player.rotate(-player.getRotation() + 315);
				yOffset-=speed-diag;
			}

			else if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				player.rotate(-player.getRotation() + (player.getRotation() + 0)/2);
				yOffset-=speed-diag;
			}

			else{

				if(player.getRotation() != 0){
					player.rotate(-player.getRotation() + 0);				
				}
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			yOffset-=speed;

			if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)
					|| Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				player.rotate(-player.getRotation() + (player.getRotation() + 180)/2);
				yOffset+=speed-diag;
			}

			else{

				if(player.getRotation() != 180){
					player.rotate(-player.getRotation() + 180);				
				}
			}
		}

		// if we now have a vertex of the player in a wall or a vertex is going out of bounds go back to previous offsets
		if (inSolid(Math.round(player.getVertices()[0]), Math.round(player.getVertices()[1]))
				&& xOffset > oldXOffset
				|| inSolid(Math.round(player.getVertices()[5]), Math.round(player.getVertices()[6]))
				&& xOffset > oldXOffset
				|| inSolid(Math.round(player.getVertices()[10]), Math.round((player.getVertices()[11])))
				&& xOffset < oldXOffset
				|| inSolid(Math.round(player.getVertices()[15]), Math.round(player.getVertices()[16]))
				&& xOffset < oldXOffset){
			xOffset = oldXOffset;
		}
		if(inSolid(Math.round(player.getVertices()[0]), Math.round(player.getVertices()[1]))
				&& yOffset < oldYOffset
				|| inSolid(Math.round(player.getVertices()[15]), Math.round(player.getVertices()[16]))
				&& yOffset > oldYOffset
				|| inSolid(Math.round(player.getVertices()[5]), Math.round((player.getVertices()[6])))
				&& yOffset < oldYOffset
				|| inSolid(Math.round(player.getVertices()[10]), Math.round(player.getVertices()[11]))
				&& yOffset > oldYOffset){
			yOffset = oldYOffset;
		}
	}
	
	public void render(SpriteBatch sb){
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
	}
	
	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @return if the coordinates are in a solid tile or out of bounds
	 */
	public boolean inSolid(int xCoordinate, int yCoordinate){
		int[] solids = {Tile.WALL_TILE, -1};
		for(int solidTile : solids){
			if (tileType(xCoordinate, yCoordinate) == solidTile)
				return true;
		}
		return false;
	}
	
	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @return the type of tile on those coordinates or -1 if you are out of bounds
	 */
	public int tileType(int xCoordinate, int yCoordinate){
		int index = (xCoordinate - xOffset) / Tile.TILE_SIZE % width + (yCoordinate - yOffset) / Tile.TILE_SIZE * width;
		if(index < 0 || index >= tileMap.length)
			return -1;
		else
			return tileMap[index];
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

	/**
	 * @return the player
	 */
	public Sprite getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Sprite player) {
		this.player = player;
	}

	public void dispose(){
		tiles.dispose();
	}
}

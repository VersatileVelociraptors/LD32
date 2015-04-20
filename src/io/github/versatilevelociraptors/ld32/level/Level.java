package io.github.versatilevelociraptors.ld32.level;

import io.github.versatilevelociraptors.ld32.LudumDare32;
import io.github.versatilevelociraptors.ld32.entities.Player;
import io.github.versatilevelociraptors.ld32.entities.Velociraptor;
import io.github.versatilevelociraptors.ld32.weapons.Weapon;
import io.github.versatilevelociraptors.ld32.weapons.Weapon.Projectile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Level implements Disposable{
	
	private int width, height;
	private int[] tileMap; 
	
	private int xOffset, yOffset;
	
	private Tile tiles;
	private Player player;
	private Velociraptor enemyTest;
	private final ArrayList<Weapon.Projectile> projectiles = new ArrayList<Weapon.Projectile>();
	
	public Level(String path){
		tiles = new Tile();
		loadLevel(path);
		
		xOffset = -getWidthInPixels()/2;
		yOffset = -getHeightInPixels()/2;
	}
	
	public int getXOffset(){
		return xOffset;
	}
	
	public int getYOffset(){
		return yOffset;
	}
	
	public void setXOffset(int offset){
		xOffset = offset;
	}
	
	public void setYOffset(int offset){
		yOffset = offset;
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
		player.update(dt);
		enemyTest.update(dt);
		for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			Weapon.Projectile projectile = iterator.next();
			if(projectile.alive())
				projectile.update(dt);
			else
				iterator.remove();
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
		for(Weapon.Projectile projectile : projectiles){
			projectile.draw(sb);
		}
		enemyTest.setX(1500 + xOffset);
		enemyTest.setY(1500 + yOffset);
		enemyTest.draw(sb);
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
	
	public ArrayList<Vector2> getWalls(){
		ArrayList<Vector2> indexes = new ArrayList<Vector2>();
		
		for(int i = 0; i < width*height; i++){
			if(tileMap[i] == Tile.WALL_TILE)
				indexes.add(getTileCoordinates(i));
		}
		return indexes;
	}
	
	public Vector2 getTileCoordinates(int index){
		Vector2 position = new Vector2();
		int x = index%width;
		int y = index/width;
		
		position.x = x*Tile.TILE_SIZE-xOffset;
		position.y = y*Tile.TILE_SIZE-yOffset;
		return position;
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
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setVelociraptor(Velociraptor dino){
		enemyTest = dino;
	}
	
	/**
	 * @return the projectiles
	 */
	public ArrayList<Weapon.Projectile> getProjectiles() {
		return projectiles;
	}

	public void dispose(){
		tiles.dispose();
	}
}

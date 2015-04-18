package io.github.versatilevelociraptors.ld32.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class SpriteSheet {

	private String path;
	private final int frameDuration;
	private final int tileWidth;
	private final int tileHeight;
	private Animation animation;
	
	/**
	 * @param path
	 * @param size
	 */
	public SpriteSheet(String path, int frameDuration, int tileWidth, int tileHeight) {
		this.path = path;
		this.frameDuration = frameDuration;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	/**
	 * make an amimation out of the sprite sheet
	 */
	protected void createAnimation(){
		// first let's split the sprite sheet into regions
		Texture sheet = new Texture(Gdx.files.internal(path));
		TextureRegion[][] regions = TextureRegion.split(sheet, tileWidth, tileHeight);
		Array<TextureRegion> array = new Array<TextureRegion>();
		// fill the libgdx array with the contents of the 2d array
		for(int row = 0; row < regions.length; row++){
			for (int column = 0; column < regions[row].length; column++) {
				array.add(regions[row][column]);
			}
		}
		setAnimation(new Animation(frameDuration, array));
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}

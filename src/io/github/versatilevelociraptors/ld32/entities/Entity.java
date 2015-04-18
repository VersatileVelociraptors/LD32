package io.github.versatilevelociraptors.ld32.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public class Entity implements Disposable{

	private Rectangle rectangle;
	private Texture image;
	
	/**
	 * @param rectangle
	 * @param image
	 */
	public Entity(Rectangle rectangle, Texture image) {
		this.setRectangle(rectangle);
		this.setImage(image);
	}
	
	/**
	 * @return the texture which should be rendered
	 */
	public TextureRegion texture(){
		return new TextureRegion(image);
	}
	
	@Override
	public void dispose() {
		image.dispose();
	}

	/**
	 * @return the image
	 */
	protected Texture getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	protected void setImage(Texture image) {
		this.image = image;
	}

	/**
	 * @return the rectangle
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

	/**
	 * @param rectangle the rectangle to set
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

}

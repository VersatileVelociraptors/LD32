package io.github.versatilevelociraptors.ld32.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Button {
	
	protected Sprite buttonTexture;
	protected float x,y;
	protected boolean clicked;
	
	public Button(Texture texture, float x, float y){
		this.x = x;
		this.y = y;
		this.buttonTexture = new Sprite(texture);
		clicked = false;
		buttonTexture.flip(false, true);
	}
	

	public void update(float x, float y) {
		if(x > this.x && x < this.x + buttonTexture.getWidth()
				&& y > this.y && y < this.y + buttonTexture.getHeight()){
			clicked = true;
		}
	}

	
	public void render(SpriteBatch sb) {
		sb.draw(buttonTexture, x, y);
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
}

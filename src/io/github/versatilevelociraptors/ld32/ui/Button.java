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
	
	public abstract void update(float x, float y);
	public abstract void render(SpriteBatch sb);
	
	public boolean isClicked(){
		return clicked;
	}
	
}

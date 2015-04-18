package io.github.versatilevelociraptors.ld32.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageButton extends Button{

	public ImageButton(Texture texture, float x, float y) {
		super(texture, x, y);
	}

	@Override
	public void update(float x, float y) {
		clicked = false;
		if(x > this.x && x < this.x + buttonTexture.getWidth()){
			if(y > this.y && y < this.y + buttonTexture.getHeight()){
				clicked = true;
			}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(buttonTexture, x, y);
	}

}

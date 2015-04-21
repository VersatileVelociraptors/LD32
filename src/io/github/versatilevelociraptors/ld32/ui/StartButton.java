package io.github.versatilevelociraptors.ld32.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartButton extends Button{

	public StartButton(Texture texture, float x, float y) {
		super(texture, x, y);
	}

	@Override
	public void update(float x, float y) {
		if(x > this.x && x < this.x + buttonTexture.getWidth()
				&& y > this.y && y < this.y + buttonTexture.getHeight()){
			clicked = true;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(buttonTexture, x, y);
	}

}
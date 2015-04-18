package io.github.versatilevelociraptors.ld32.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GrassTile extends Tile{

	public GrassTile(int x, int y) {
		super(x,y);
		sprite = new Texture("assets/images/grass.png");
	}
	
	public void render(SpriteBatch sb){
		sb.draw(sprite, x + xOffset, y + yOffset);
	}

}

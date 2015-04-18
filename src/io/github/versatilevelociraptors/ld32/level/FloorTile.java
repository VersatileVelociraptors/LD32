package io.github.versatilevelociraptors.ld32.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FloorTile extends Tile{
	
	public FloorTile(int x, int y){
		super(x,y);
		sprite = new Texture("assets/images/floor.png");
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(sprite, x + xOffset, y + yOffset);
	}

}

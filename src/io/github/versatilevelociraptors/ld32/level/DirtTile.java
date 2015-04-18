package io.github.versatilevelociraptors.ld32.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DirtTile extends Tile{

	private int x,y;
	private Texture sprite;

	public DirtTile(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = new Texture("assets/images/dirt.png");
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(sprite, x, y);
		sb.end();
	}


}

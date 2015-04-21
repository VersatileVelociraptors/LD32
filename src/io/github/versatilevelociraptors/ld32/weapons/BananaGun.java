package io.github.versatilevelociraptors.ld32.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BananaGun extends Weapon {

	public BananaGun() {
		super(45, 90, 80, 9, 250, new Texture("assets/images/banana.png"), new Sprite(new Texture("assets/images/bananagun.png")), "Banana Gun", new Texture("assets/images/bananaplayer.png"));
	}

}

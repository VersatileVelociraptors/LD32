package io.github.versatilevelociraptors.ld32.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NyanGun extends Weapon {

	public NyanGun() {
		super(100, 20, 10, 1, 500,
				new Texture("assets/images/nyancat.png"), new Sprite(new Texture("assets/images/nyancat.png")), "Nyan Gun");
		getImage().flip(false, true);
	}

}

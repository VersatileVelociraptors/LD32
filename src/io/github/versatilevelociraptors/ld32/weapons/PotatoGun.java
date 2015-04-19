package io.github.versatilevelociraptors.ld32.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PotatoGun extends Weapon {

	public PotatoGun() {
		super(50, 30, 30, 3, 300, new Texture(Gdx.files.internal("assets/images/potato.png")));
	}

}

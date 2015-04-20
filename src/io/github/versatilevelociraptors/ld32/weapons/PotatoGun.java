package io.github.versatilevelociraptors.ld32.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PotatoGun extends Weapon {

	public PotatoGun() {
		super(50, 30, 30, 3, 900,
				new Texture(Gdx.files.internal("assets/images/potato.png")),
				new Sprite(new Texture(Gdx.files.internal("assets/images/potatogun.png"))),
				"Potato Gun");
		getImage().flip(false, true);
	}

}

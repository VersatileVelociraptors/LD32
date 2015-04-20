package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;

public class Velociraptor extends Mob{

	public Velociraptor(Level level) {
		super(new Texture("assets/images/dino.png"), level);
		this.flip(false, true);
	}

	@Override
	public void update(float dt) {
		
	}

}

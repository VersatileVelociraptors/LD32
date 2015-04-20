package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;

public class Velociraptor extends Mob{	
	
	private double angle;
	
	public Velociraptor(Level level) {
		super(new Texture("assets/images/dino.png"), level);
		this.flip(false, true);
	}

	@Override
	public void update(float dt) {
		angle = Math.atan((level.getPlayer().getY() - getY())/(level.getPlayer().getX() - getX()));
		setRotation((float) (angle*180/(2*Math.PI)));
		setX((float) (getX() + speed*Math.cos(angle)));
		setY((float) (getY() + speed*Math.sin(angle)));
		System.out.println(getX() + " " + getY());
	//	setX(1500 + level.getXOffset());
	//	setY(1500 + level.getYOffset());
	}

}

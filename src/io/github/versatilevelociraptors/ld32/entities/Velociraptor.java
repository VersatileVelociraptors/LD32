package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Velociraptor extends Mob{
	
	public static final float DAMAGE = 5;
	public static final int HIT_DELAY = 300;
	
	private double angle;
	private long lastHitTime;
	private int x,y;
	
	public Velociraptor(Level level, int x, int y) {
		super(new Texture("assets/images/dino.png"), level);
		this.flip(false, true);
		this.x = x;
		this.y = y;
		setX(this.x + level.getXOffset()); 
		setY(this.y + level.getYOffset());
		speed = 9;
	}

	@Override
	public void update(float dt) {
		Vector2 player = new Vector2(level.getPlayer().getX(), level.getPlayer().getY());
		Vector2 pos = new Vector2(getX(), getY());
		angle = Math.atan2(player.y - pos.y, player.x - pos.x);
		setRotation((float) (angle*180/(Math.PI)));
		x+=speed*Math.cos(angle);
		y+=speed*Math.sin(angle);
		setX((float) (x + level.getXOffset()));
		setY((float) (y + level.getYOffset()));
		
		if(level.getPlayer().getBoundingRectangle().overlaps(getBoundingRectangle()) && System.currentTimeMillis() - lastHitTime >= HIT_DELAY){
			// damage the player
			level.getPlayer().takeDamage(DAMAGE);
			lastHitTime = System.currentTimeMillis();
		}
	}

}

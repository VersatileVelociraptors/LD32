package io.github.versatilevelociraptors.ld32.entities;

import io.github.versatilevelociraptors.ld32.level.Level;
import io.github.versatilevelociraptors.ld32.util.Node;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Velociraptor extends Mob{
	
	public ArrayList<Node> open;
	public ArrayList<Node> closed;
	
	public ArrayList<Node> getPath(Vector2 start, Vector2 end){
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		
		ArrayList<Node> path = new ArrayList<Node>();
		
		//Node currentNode = new Node(getX(), getY());
		
		
		return path;
	}

	public Velociraptor(Level level) {
		super(new Texture("assets/images/dino.png"), level);
		this.flip(false, true);
		
	}

	@Override
	public void update(float dt) {
		setRotation(getRotation() - 10);
	}

}

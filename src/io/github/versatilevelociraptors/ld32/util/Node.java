package io.github.versatilevelociraptors.ld32.util;

import com.badlogic.gdx.math.Vector2;

public class Node {
	public Vector2 pos;
	
	public Node parent;
	public double fCost, gCost, hCost;
	
	public Node(Vector2 pos, Node parent, double gCost, double hCost){
		this.pos = pos;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = gCost;
		fCost = this.gCost + this.hCost;
	}
	
	public double getFCost(){
		return fCost;
	}
}

package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class FountainCollisionTopRight extends GameObject{

	public FountainCollisionTopRight(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

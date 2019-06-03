package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class StoneFloor extends GameObject{

	private BufferedImage stone_floor;
	
	public StoneFloor(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		stone_floor = ss.grabImage(2, 1, 32, 32);
		
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(stone_floor, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 0, 0); // TOP FOUNTAIN COLLISION
			
	}
}

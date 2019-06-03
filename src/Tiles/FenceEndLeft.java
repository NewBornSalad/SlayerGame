package Tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class FenceEndLeft extends GameObject{
	
	private BufferedImage fence_end;

	public FenceEndLeft(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		fence_end = ss.grabImage(2, 9, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(fence_end, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

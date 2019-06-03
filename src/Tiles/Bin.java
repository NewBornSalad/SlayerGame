package Tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class Bin extends GameObject{

	private BufferedImage bin;
	
	public Bin(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		bin = ss.grabImage(7, 1, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(bin, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

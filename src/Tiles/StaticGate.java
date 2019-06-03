package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class StaticGate extends GameObject{
	
	public BufferedImage gate;

	public StaticGate(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		gate = ss.grabImage(3, 2, 128, 80);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(gate, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 128, 80);
	}
}

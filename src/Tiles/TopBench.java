package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class TopBench extends GameObject{

	private BufferedImage bench;
	
	public TopBench(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		bench = ss.grabImage(4, 1, 96, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(bench, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 32);
	}
}

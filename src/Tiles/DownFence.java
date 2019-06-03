package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class DownFence extends GameObject{
	
	private BufferedImage down_fence;

	public DownFence(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		down_fence = ss.grabImage(2, 3, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(down_fence, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

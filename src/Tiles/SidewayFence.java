package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class SidewayFence extends GameObject{

	private BufferedImage sideway_fence;
	
	public SidewayFence(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		sideway_fence = ss.grabImage(1, 3, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(sideway_fence, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class LeftCornerFence extends GameObject{

	private BufferedImage left_corner_fence;
	
	public LeftCornerFence(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		left_corner_fence = ss.grabImage(2, 4, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(left_corner_fence, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

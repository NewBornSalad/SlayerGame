package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class RightCornerFence extends GameObject{

	private BufferedImage right_corner_fence;
	
	public RightCornerFence(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		right_corner_fence = ss.grabImage(1, 4, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(right_corner_fence, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

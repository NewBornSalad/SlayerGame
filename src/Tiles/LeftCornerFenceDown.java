package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;

public class LeftCornerFenceDown extends GameObject{

	private BufferedImage left_corner_fence_down;
	
	public LeftCornerFenceDown(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		left_corner_fence_down = ss.grabImage(2, 2, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(left_corner_fence_down, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

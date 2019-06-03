package Tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.Animation;
import Main.ID;
import Main.SpriteSheet;

public class WaterTile extends GameObject{
	private Animation anim;
	
	private BufferedImage[] water_tile = new BufferedImage[2];

	public WaterTile(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		water_tile[0] = ss.grabImage(1, 7, 32, 32);
		water_tile[1] = ss.grabImage(2, 7, 32, 32);
		
		anim = new Animation(8, water_tile[0], water_tile[1]);
	}

	public void tick() {
		
		anim.runAnimation();
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

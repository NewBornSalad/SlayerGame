package Drops;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import GameObject.GameObject;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class HealthPack extends GameObject{
	
	private BufferedImage health_pack = null;
	private boolean spawned = false;

	public HealthPack(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		health_pack = ss.grabImage(2, 6, 32, 32);
	}

	public void tick() {
		
		if(spawned == false){
			Random rand = new Random();
			int result = rand.nextInt(4);
			
			if(result == 0){
				x = 200;
				y = 200;
				spawned = true;
				
			}else if(result == 1){
				x = 1700;
				y = 200;
				spawned = true;
				
			}else if(result == 2){
				x = 200;
				y = 700;
				spawned = true;
				
			}else if(result == 3){
				x = 1700;
				y = 900;
				spawned = true;
			}
			
		}else{
			spawned = true;
		}
	}

	public void render(Graphics g) {
		g.drawImage(health_pack, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

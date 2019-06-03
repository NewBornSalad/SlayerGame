package player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import GameObject.GameObject;
import GameObject.Handler;
import Main.ID;
import Main.SpriteSheet;


public class Bullet extends GameObject {

	private Handler handler;
	
	public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		
		velX = (mx - x) / 15;
		velY = (my - y) / 15;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Fence || tempObject.getId() == ID.Block || tempObject.getId() == ID.TombStone || tempObject.getId() == ID.Gate || tempObject.getId() == ID.FountainBottomLeft || tempObject.getId() == ID.FountainBottomRight || tempObject.getId() == ID.FountainTopLeft || tempObject.getId() == ID.FountainTopRight){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}
			}
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval((int)x, (int)y, 5, 5);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}
}


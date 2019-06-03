package EnemyAttacks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class AcidPoolBall extends GameObject{
	
	private Game game;
	private Handler handler;
	private GameObject player;

	private BufferedImage acid_pool_ball = null;

	public AcidPoolBall(float x, float y, ID id, Game game, Handler handler, SpriteSheet enemy_ss) {
		super(x, y, id, enemy_ss);
		this.game = game;
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
	        if(handler.object.get(i).getId() == ID.Player){
	        	player = handler.object.get(i);
	        }
	      }
		
		acid_pool_ball = enemy_ss.grabImage(18, 12, 32, 32);
		getPlayerPosition();
	}

	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Fence || tempObject.getId() == ID.TombStone || tempObject.getId() == ID.Gate || tempObject.getId() == ID.Block || tempObject.getId() == ID.FountainBottomLeft || tempObject.getId() == ID.FountainBottomRight || tempObject.getId() == ID.FountainTopLeft || tempObject.getId() == ID.FountainTopRight){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
				}
			}
			
			if(tempObject.getId() == ID.Bullet){
				return; // (Do nothing)
			}
		}
	}
	
	private void getPlayerPosition(){
		velX = (player.getX() - x) / 35;
		velY = (player.getY() - y) / 35;
	}

	public void render(Graphics g) {
		g.drawImage(acid_pool_ball, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x ,(int)y, 32, 32);
	}
}

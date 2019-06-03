package EnemyAttacks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class BallAttack extends GameObject{
	
	private Game game;
	private Handler handler;
	private GameObject player;
	private SpriteSheet enemy_ss;

	private BufferedImage ball = null;
	private int direction;

	public BallAttack(float x, float y, ID id, Game game, Handler handler, SpriteSheet enemy_ss, int num, int direction) {
		super(x, y, id, enemy_ss);
		this.game = game;
		this.handler = handler;
		this.enemy_ss = enemy_ss;
		this.direction = direction;
		
	      for(int i = 0; i < handler.object.size(); i++){
	        if(handler.object.get(i).getId() == ID.Player){
	        	player = handler.object.get(i);
	        }
	      }
		
	      if(num == 1){
	    	  ball = enemy_ss.grabImage(13, 12, 32, 32);
	    	  
	      }else if(num == 2){
	    	  ball = enemy_ss.grabImage(9, 17, 32, 32);
	      }
	     
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
		
		if(direction == 0){
			velX = (player.getX() - x) / 35;
			velY = (player.getY() - y) / 35;
			
		}else if(direction == 1){
			double direction = Math.random() * 2.0 * Math.PI;
		    double speed = 5.0 / 0.10;
		    int speedX = (int) (speed * Math.cos(direction));
		    int speedY = (int) (speed * Math.sin(direction));	
		    
		    velX = (player.getX() - x) / speedX;
			velY = (player.getY() - y) / speedY;
		}
	}

	public void render(Graphics g) {
		g.drawImage(ball, (int)x, (int)y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x ,(int)y, 32, 32);
	}
}

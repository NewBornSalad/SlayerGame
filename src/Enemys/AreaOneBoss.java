package Enemys;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Animation;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;
import player.Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class AreaOneBoss extends GameObject{
	
	private BufferedImage zombie_boss[] = new BufferedImage[2];
	private Handler handler, handler2;
	private Animation anim;
	private GameObject player;
	private Camera camera;
	private Game game;
	
	private long startTime = -1;
	private long rushingTime = -1;
	private boolean fiveSecondsPast = false;
	private double speedChange = 0;
	
	private boolean cutscene_finished = false;
	private int boss_health = 150;

	public AreaOneBoss(float x, float y, ID id, Handler handler, Handler handler2, Game game, SpriteSheet enemy_ss){
		super(x, y, id, enemy_ss);
		this.handler = handler;
		this.handler2 = handler2;
		this.game = game;
		
		zombie_boss[0] = enemy_ss.grabImage(3, 3, 100, 128);
		zombie_boss[1] = enemy_ss.grabImage(6, 3, 100, 128);
		
	       for(int i = 0; i < handler.object.size(); i++){
	        	if(handler.object.get(i).getId() == ID.Player){
	        		player = handler.object.get(i);
	        	}
	        }
		
		anim = new Animation(6, zombie_boss[0], zombie_boss[1]);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 300 & cutscene_finished == false){
			velY += 0.01;
			
		}else if(y >= 300){
			cutscene_finished = true;
		}
		
		if(cutscene_finished == true){
			
			float diffX = x - player.getX() - 5;
			float diffY = y - player.getY() - 5;
			float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
			
			if(startTime == -1){
				startTime = System.currentTimeMillis();
			}
			
			long timePast = System.currentTimeMillis() - startTime;
			
			if(timePast >= 4000 && fiveSecondsPast == false){
				
				speedChange = -5;
			
				fiveSecondsPast = true;
				
			}else if(timePast < 6000 && fiveSecondsPast == false){
				speedChange = -2.3;
			}
			
			if(fiveSecondsPast == true){
				if(rushingTime == -1){
					rushingTime = System.currentTimeMillis();
				}
				
				timePast = System.currentTimeMillis() - rushingTime;
				
				if(timePast >= 1950){
					startTime = -1;
					rushingTime = -1;
					fiveSecondsPast = false;
				}
			}
			
			velX = (float) ((speedChange / distance * diffX));
			velY = (float) ((speedChange / distance * diffY)); 
				
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())){
					if(boss_health > 0){
						boss_health -= 0.01;
						handler.removeObject(tempObject);
					}
				}
			}
			
			if(tempObject.getId() == ID.FountainTopLeft){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					velY -= 2.0;
					velX -= 2.0;
					
				}
				
			}else if(tempObject.getId() == ID.FountainBottomLeft){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					velY += 2.0;
					velX -= 1.0;
					
				}
				
			}else if(tempObject.getId() == ID.FountainTopRight){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					velY -= 2.0;
					velX += 1.0;
				}
				
			}else if(tempObject.getId() == ID.FountainBottomRight){
				
				if(getBounds().intersects(tempObject.getBounds())){
					
					velY += 2.0;
					velX += 1.0;
					
				}
			}
		}
		
		if(boss_health <= 0){
			game.deadBoss += 1;
			handler2.removeObject(this);
		}
		
		anim.runAnimation();
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
		
		// Boss Health Bar Below
		
		g.setColor(Color.BLACK);
		g.fillRect((int)getX() - + 33, (int)getY() - 21, 150 + 4, 14);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX() - 31, (int)getY() - 19, boss_health, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 100, 128);
	}

}

package Enemys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Animation;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;
import Tiles.SidewayFence;
import player.Camera;
import EnemyAttacks.AcidBall;
import player.Player;

public class AreaTwoBoss extends GameObject{

	private BufferedImage shroomer_boss[] = new BufferedImage[2];
	private Handler handler, handler2;
	private Animation anim;
	private GameObject player;
	private SpriteSheet enemy_ss;
	private Camera camera;
	private Game game;
	
	private long startTime = -1;
	private boolean fiveSecondsPast = false;
	
	private boolean cutscene_finished = false;
	private int boss_health = 180;
	
	public AreaTwoBoss(float x, float y, ID id, Handler handler, Handler handler2, Game game, SpriteSheet enemy_ss){
		super(x, y, id, enemy_ss);
		this.handler = handler;
		this.handler2 = handler2;
		this.enemy_ss = enemy_ss;
		this.game = game;
		
		shroomer_boss[0] = enemy_ss.grabImage(3, 7, 128, 128);
		shroomer_boss[1] = enemy_ss.grabImage(7, 7, 128, 128);
		
	       for(int i = 0; i < handler.object.size(); i++){
	        	if(handler.object.get(i).getId() == ID.Player){
	        		player = handler.object.get(i);
	        	}
	        }
		
		anim = new Animation(6, shroomer_boss[0], shroomer_boss[1]);
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
			
			velX = (float) ((-2.3 / distance * diffX));
			velY = (float) ((-2.3 / distance * diffY));
			
			if(startTime == -1){
				startTime = System.currentTimeMillis();
			}
			
			long timePast = System.currentTimeMillis() - startTime;
			
			if(timePast >= 2000 && fiveSecondsPast == false){
				handler.addObject(new AcidBall(x + 43, y + 38, ID.AcidBall, game, handler, enemy_ss));
				startTime = -1;
			}
			
			// Collision
			
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
		g.fillRect((int)getX()  - 30, (int)getY() - 21, 180 + 4, 14);
		g.setColor(Color.GREEN);
		g.fillRect((int)getX() - 28, (int)getY() - 19, boss_health, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 128, 128);
	}
}

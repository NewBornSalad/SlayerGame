package Enemys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import EnemyAttacks.AcidBall;
import EnemyAttacks.BallAttack;
import GameObject.GameObject;
import GameObject.Handler;
import Main.Animation;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class Spitter extends GameObject{
	
	private BufferedImage spitter[] = new BufferedImage[2];
	private BufferedImage enemy_death_sprite;
	private GameObject player;
	private SpriteSheet enemy_ss;
	private Handler handler, handler2;
	private Game game;
	private Animation anim;
	
	private long startTime = -1;
	private boolean fiveSecondsPast = false;
	
	private int spitterHP = 100;

	public Spitter(float x, float y, ID id, Handler handler, Handler handler2, Game game, SpriteSheet enemy_ss) {
		super(x, y, id, enemy_ss);
		this.handler = handler;
		 this.handler2 = handler2;
		this.game = game;
		this.enemy_ss = enemy_ss;
		
		spitter[0] = enemy_ss.grabImage(1, 12, 64, 32);
		spitter[1] = enemy_ss.grabImage(3, 12, 64, 32);
		
		for(int i = 0; i < handler.object.size(); i++){
        	if(handler.object.get(i).getId() == ID.Player){
        		player = handler.object.get(i);
        	}
        }
        
        anim = new Animation(3, spitter[0], spitter[1]);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 5;
		float diffY = y - player.getY() - 5;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velX = (float) ((-2.3 / distance * diffX));
		velY = (float) ((-2.3 / distance * diffY)); 
		
		if(startTime == -1){
			startTime = System.currentTimeMillis();
		}
		
		long timePast = System.currentTimeMillis() - startTime;
		
		if(timePast >= 4000 && fiveSecondsPast == false){
			handler.addObject(new BallAttack(x, y, ID.WaterBall, game, handler, enemy_ss, 1, 0));
			startTime = -1;
		}
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())){
					if(spitterHP > 0){
						spitterHP -= 25;
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
		
		if(spitterHP <= 0){
			handler.object.addFirst(new EnemyDeathSprite(x, y, ID.DeathSprite, handler, enemy_ss, 3));
			game.deadEnemys += 1;
			handler2.removeObject(this);
		}
		
		anim.runAnimation();
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x , (int)y, 64, 32);
	}
}

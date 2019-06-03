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

public class Slimer extends GameObject{
	
	private BufferedImage slimer[] = new BufferedImage[2];
	private BufferedImage mist[] = new BufferedImage[3];
	private BufferedImage enemy_death_sprite;
	private GameObject player;
	private SpriteSheet enemy_ss;
	private Handler handler, handler2;
	private Game game;
	private Animation anim, anim2;
	
	protected int slimerHP = 150;

	public Slimer(float x, float y, ID id, Handler handler, Handler handler2, Game game, SpriteSheet enemy_ss) {
		super(x, y, id, enemy_ss);
		this.handler = handler;
		this.handler2 = handler2;
        this.game = game;
        this.enemy_ss = enemy_ss;
        
        mist[0] = enemy_ss.grabImage(12, 6, 200, 170);
        mist[1] = enemy_ss.grabImage(18, 6, 200, 170);
        mist[2] = enemy_ss.grabImage(25, 6, 200, 170);
        slimer[0] = enemy_ss.grabImage(1, 9, 32, 64);
        slimer[1] = enemy_ss.grabImage(2, 9, 32, 64);
        
        for(int i = 0; i < handler.object.size(); i++){
        	if(handler.object.get(i).getId() == ID.Player){
        		player = handler.object.get(i);
        	}
        }
        
        anim2 = new Animation(23, mist[0], mist[1], mist[2]);
        anim = new Animation(3, slimer[0], slimer[1]);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 5;
		float diffY = y - player.getY() - 5;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		velX = (float) ((-4.1 / distance * diffX));
		velY = (float) ((-4.1 / distance * diffY)); // make spaz out if player is dead and zombies are at players x
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Bullet){
				if(getBoundsNormal().intersects(tempObject.getBounds())){
					if(slimerHP > 0){
						slimerHP -= 25;
						handler.removeObject(tempObject);
					}
				}
			}
			
			if(tempObject.getId() == ID.FountainTopLeft){
				
				if(getBoundsNormal().intersects(tempObject.getBounds())){
					
					velY -= 2.0;
					velX -= 2.0;
					
				}
				
			}else if(tempObject.getId() == ID.FountainBottomLeft){
				
				if(getBoundsNormal().intersects(tempObject.getBounds())){
					
					velY += 2.0;
					velX -= 1.0;
					
				}
				
			}else if(tempObject.getId() == ID.FountainTopRight){
				
				if(getBoundsNormal().intersects(tempObject.getBounds())){
					
					velY -= 2.0;
					velX += 1.0;
				}
				
			}else if(tempObject.getId() == ID.FountainBottomRight){
				
				if(getBoundsNormal().intersects(tempObject.getBounds())){
					
					velY += 2.0;
					velX += 1.0;
					
				}
			}
		}
		
		if(slimerHP <= 0){
			handler.object.addFirst(new EnemyDeathSprite(x, y, ID.DeathSprite, handler, enemy_ss, 2));
			game.deadEnemys += 1;
			handler2.removeObject(this);
		}
		
		anim2.runAnimation();
		anim.runAnimation();
		
	}

	public void render(Graphics g) {
		if(slimerHP > 0){
			anim2.drawAnimation(g, x - 80, y- 48, 0);
			anim.drawAnimation(g, x, y, 0);
		}else if(slimerHP <= 0){
			return;
		}
	}
	
	public Rectangle getBoundsNormal() {
		return new Rectangle((int)x, (int)y, 32, 64);
			
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x -72, (int)y - 64, 200, 200);
			
	}
}


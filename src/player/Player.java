package player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import EnemyAttacks.AcidPoolBall;
import EnemyAttacks.AcidPool;
import GameObject.GameObject;
import GameObject.Handler;
import Main.Animation;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class Player extends GameObject{
	
	Handler handler, handler2;
	SpriteSheet enemy_ss;
	Game game;
	Camera camera;
	Animation anim;
	private BufferedImage[] player_sprite = new BufferedImage[3];

	public Player(float x, float y, ID id, Handler handler, Handler handler2, Game game, Camera camera, SpriteSheet enemy_ss) {
		super(x, y, id, enemy_ss);
		this.enemy_ss = enemy_ss;
		this.handler = handler;
		this.handler2 = handler2;
		this.game = game;
		this.camera = camera;
		
		player_sprite[0] = enemy_ss.grabImage(1, 1, 32, 64);
		player_sprite[1] = enemy_ss.grabImage(2, 1, 32, 64);
		player_sprite[2] = enemy_ss.grabImage(3, 1, 32, 64);
		
		anim = new Animation(3, player_sprite[0], player_sprite[1]);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		//movement
		
		if(game.hp >= 1){
			
			if(handler.isUp()) velY = -3;
			else if(!handler.isDown()) velY = 0;
		
			if(handler.isDown()) velY = 3;
			else if(!handler.isUp()) velY = 0;
		
			if(handler.isLeft()) velX = -3;
			else if(!handler.isRight()) velX = 0;
		
			if(handler.isRight()) velX = 3;
			else if(!handler.isLeft()) velX = 0;
			
			for(int i = 0; i < handler2.object.size(); i++){
				
				GameObject tempObject = handler2.object.get(i);
				
				if(tempObject.getId() == ID.Slimer){
					if(getBounds().intersects(tempObject.getBounds())){
						
						if(handler.isUp()) velY = -0.5f;
						else if(!handler.isDown()) velY = 0;
					
						if(handler.isDown()) velY = 0.5f;
						else if(!handler.isUp()) velY = 0;
					
						if(handler.isLeft()) velX = -0.5f;
						else if(!handler.isRight()) velX = 0;
					
						if(handler.isRight()) velX = 0.5f;
						else if(!handler.isLeft()) velX = 0;
						
					}
				}
			}
			
		}else if(game.hp <= 0){
			
			if(handler.isUp()) velY = -0;
			else if(!handler.isDown()) velY = 0;
		
			if(handler.isDown()) velY = 0;
			else if(!handler.isUp()) velY = 0;
		
			if(handler.isLeft()) velX = -0;
			else if(!handler.isRight()) velX = 0;
		
			if(handler.isRight()) velX = 0;
			else if(!handler.isLeft()) velX = 0;
		}
		
		anim.runAnimation();
		
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Fence || tempObject.getId() == ID.TombStone || tempObject.getId() == ID.Gate || tempObject.getId() == ID.Block || tempObject.getId() == ID.FountainBottomLeft || tempObject.getId() == ID.FountainBottomRight || tempObject.getId() == ID.FountainTopLeft || tempObject.getId() == ID.FountainTopRight){
				if(getBounds().intersects(tempObject.getBounds())){
					x += velX * -1;
					y += velY * -1;
				}
			}
			
			if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.ZombieBoss || tempObject.getId() == ID.ShroomerBoss || tempObject.getId() == ID.ChomperBoss || tempObject.getId() == ID.AcidPool){
				if(getBounds().intersects(tempObject.getBounds())){
					game.hp -= 0.01;
				}
			}
			
			if(tempObject.getId() == ID.AcidBall){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					game.hp -= 15;
				}
			}
			
			if(tempObject.getId() == ID.AcidPoolBall){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					handler.object.add(2, new AcidPool(x - 48, y, ID.AcidPool, game, handler, enemy_ss));
				}
			}
			
			if(tempObject.getId() == ID.WaterBall){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(tempObject);
					game.hp -= 10;
				}
			}
			
			if(tempObject.getId() == ID.Transition){
				if(getBounds().intersects(tempObject.getBounds())){
					if(game.round == 11){
						game.currentArea += 1;
						game.boss_activate = false;
						camera.enemy_activate = false;
						handler.object.clear();
						handler2.object.clear();
						game.round = 1;
						game.loadSprites();
						game.setArea();
					}else{
						return;
					}
				}
			}
			
			if(y <= 0){
				y = 0;
			}
		}
		
		for(int i = 0; i < handler2.object.size(); i++){
			
			GameObject tempObject = handler2.object.get(i);
			
			if(tempObject.getId() == ID.WaterTile){
				if(getBounds().intersects(tempObject.getBounds())){
					x += velX * -1;
					y += velY * -1;
				}
			}
			
			if(tempObject.getId() == ID.Enemy){
				if(getBounds().intersects(tempObject.getBounds())){
					game.hp -= 0.01;
				}
			}
			
			if(tempObject.getId() == ID.HealthPack){
				if(getBounds().intersects(tempObject.getBounds())){
						game.hp += 50;
						handler2.removeObject(tempObject);
					
					 if(game.hp >= 100){
						 game.hp = 100;
					 }
				}
			}
		}
	}

	public void render(Graphics g) {
		
		if(velX == 0 && velY == 0){
			g.drawImage(player_sprite[2], (int)x, (int)y, null);
			
		}else{
			anim.drawAnimation(g, x, y, 0);
		}
	}

	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 64);
	}
}

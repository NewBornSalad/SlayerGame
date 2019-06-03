package Enemys;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class EnemyDeathSprite extends GameObject{
	
	private Handler handler;
	private Game game;
	private GameObject death_sprite;
	private BufferedImage enemy_death_sprite;
	TimerTask task;
	Timer timer = new Timer();

	public EnemyDeathSprite(float x, float y, ID id, Handler handler, SpriteSheet enemy_ss, int sprite) {
		super(x, y, id, enemy_ss);
		this.handler = handler;
		this.game = game;
		
		if(sprite == 1){
			enemy_death_sprite = enemy_ss.grabImage(9, 6, 32, 32); // Zombie and Yeti
			
		}else if(sprite == 2){
			enemy_death_sprite = enemy_ss.grabImage(11, 10, 32, 32); // Shroomer
			
		}else if(sprite == 3){
			enemy_death_sprite = enemy_ss.grabImage(13, 13, 32, 32); // Chomper
			
		}else if(sprite == 4){
			enemy_death_sprite = enemy_ss.grabImage(13, 13, 32, 32); // Chomper
			
		}
	}

	public void tick() {
		timer.schedule(new TimerTask(){
			public void run(){
				removeClass();
			}
		}, 5000);
	}
	
	private void removeClass(){
		handler.removeObject(this);
	}
	
	public void render(Graphics g) {
		g.drawImage(enemy_death_sprite, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return null;
	}

}

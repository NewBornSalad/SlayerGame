package Tiles;

import GameObject.GameObject;
import Main.Animation;
import Main.ID;
import Main.SpriteSheet;
import Main.Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Fountain extends GameObject{

	private BufferedImage[] fountain = new BufferedImage[2];
	private Animation anim;
	private Game game;
	
	public Fountain(float x, float y, ID id, SpriteSheet ss, Game game) {
		super(x, y, id, ss);
		
		if(game.currentArea == 1){
			fountain[0] = ss.grabImage(8, 1, 263, 167);
			fountain[1] = ss.grabImage(16, 1, 263, 167);
			
		}else if(game.currentArea == 2){
			fountain[0] = ss.grabImage(8, 7, 263, 167);
			fountain[1] = ss.grabImage(16, 7, 263, 167);
			
		}else if(game.currentArea == 3){
			fountain[0] = ss.grabImage(8, 13, 263, 167);
			fountain[1] = ss.grabImage(16, 13, 263, 167);
			
		}else if(game.currentArea == 4){
			fountain[0] = ss.grabImage(8, 19, 263, 167);
			fountain[1] = ss.grabImage(8, 19, 263, 167);
			
		}else if(game.currentArea == 5){
			fountain[0] = ss.grabImage(8, 25, 263, 167);
			fountain[1] = ss.grabImage(16, 25, 263, 167);
			
		}
		
		anim = new Animation(8, fountain[0], fountain[1]);
	}

	public void tick() {
		anim.runAnimation();
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y + 32, 32, 32); // TOP FOUNTAIN COLLISION
			
	}
}

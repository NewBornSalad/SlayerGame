package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.ID;
import Main.SpriteSheet;
import Main.Game;

public class TombStone extends GameObject{
	
	private BufferedImage tombstone;
	private Game game;

	public TombStone(float x, float y, ID id, SpriteSheet ss, Game game) {
		super(x, y, id, ss);
		this.game = game;
		
		if(game.currentArea == 1){
			tombstone = ss.grabImage(3, 1, 32, 32);
			
		}else if(game.currentArea == 2){
			tombstone = ss.grabImage(2, 5, 32, 32);
			
		}else if(game.currentArea == 3){
			tombstone = ss.grabImage(2, 8, 32, 32);
			
		}else if(game.currentArea == 4){
			tombstone = ss.grabImage(3, 11, 32, 32);
			
		}else if(game.currentArea == 5){
			tombstone = ss.grabImage(2, 12, 32, 32);
		}
	}

	public void tick() {
		
		
	}

	public void render(Graphics g) {
		g.drawImage(tombstone, (int)x, (int)y, null);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}

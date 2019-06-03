package Tiles;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class Gate extends GameObject{

	public BufferedImage gate;
	private Game game;
	
	public Gate(float x, float y, ID id, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.game = game;
		
	}

	public void tick() {
		if(game.round == 10 || game.round == 11){
			gate = ss.grabImage(3, 6, 128, 100);
		}else{
			gate = ss.grabImage(3, 2, 128, 80);
		}
		
	}

	public void render(Graphics g) {
		if(game.round == 10 ||game.round == 11){
			g.drawImage(gate, (int)x, (int)y - 20, null);
			
		}else{
			g.drawImage(gate, (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		if(game.round == 10 || game.round == 11){
			return new Rectangle((int)x, (int)y, 0, 0);
		}else{
			return new Rectangle((int)x, (int)y, 128, 80);
		}
	}
}

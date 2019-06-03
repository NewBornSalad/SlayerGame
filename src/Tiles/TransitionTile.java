package Tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import GameObject.GameObject;
import Main.ID;
import Main.Game;
import Main.SpriteSheet;

public class TransitionTile extends GameObject{
	
	private Game game;

	public TransitionTile(float x, float y, ID id, SpriteSheet ss, Game game) {
		super(x, y, id, ss);
		this.game = game;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}

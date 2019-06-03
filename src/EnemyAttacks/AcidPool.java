package EnemyAttacks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class AcidPool extends GameObject{
	
	private Game game;
	private Handler handler;
	private GameObject player;

	private BufferedImage acid_pool = null;

	public AcidPool(float x, float y, ID id, Game game, Handler handler, SpriteSheet enemy_ss) {
		super(x, y, id, enemy_ss);
		this.game = game;
		this.handler = handler;
		
		acid_pool = enemy_ss.grabImage(14, 12, 120, 64);
	}

	public void tick() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		g.drawImage(acid_pool, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x ,(int)y, 120, 64);
	}
}

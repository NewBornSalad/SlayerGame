package GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.ID;
import Main.SpriteSheet;

public abstract class GameObject {

	protected float x, y, w, h;
	public float velX, velY;
	protected ID id;
	protected SpriteSheet ss;
	
	public GameObject(float x, float y, ID id, SpriteSheet ss){
		this.x = x;
		this.y = y;
		this.id = id;
		this.ss = ss;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}

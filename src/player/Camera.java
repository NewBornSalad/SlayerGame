package player;
import java.util.Timer;
import java.util.TimerTask;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;

public class Camera {
	
	private float x, y;
	public boolean enemy_activate = false;
	private Game game;
	private Handler handler;
	private GameObject enemy_boss;
	
	private int enemy_x, enemy_y;
	TimerTask task;
	Timer timer = new Timer();
	
	public Camera(float x, float y, Handler handler, Game game){
		this.game = game;
		this.handler = handler;
		this.x = x;
		this.y = y;
		
	}
	
	public void tick(GameObject object){
		
		if(game.round == 10 & enemy_activate == false){
			
				x += ((1000 - x) - 1500/2) * 0.05f;
				y += ((500 - y) - 1000/2 +60) * 0.05f;
			
				object.velX = 0;
				object.velY = 0;
			
			timer.schedule(new TimerTask(){
				public void run(){
					enemy_activate = true;
					return;
				}
			}, 5000);
			
		}else{
			x += ((object.getX() - x) - 1500/2) * 0.05f;
			y += ((object.getY() - y) - 1000/2 +60) * 0.05f;
		}
		
		if(x <= 0) x = 0;
		if(x >= 522) x = 522;
		if(y <= 0) y = 0;
		if(y >= 500) y = 500;
		
	}

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
}

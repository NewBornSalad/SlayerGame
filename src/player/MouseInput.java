package player;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;

public class MouseInput extends MouseAdapter {

	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheet ss;
	
	private boolean cutscene_finished;
	
	public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss){
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.ss = ss;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = (int) (e.getX() + camera.getX()); // add camera so you can translate 
		int my = (int) (e.getY() + camera.getY());
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				
				if(game.hp > 0){
					handler.addObject(new Bullet((int)tempObject.getX()+16, (int)tempObject.getY()+24, ID.Bullet, handler, mx, my, ss));
					
				}else{
					return;
				}
			}
		}
	}
	
	public void cutscene_finished(boolean cutscene_finished){
		this.cutscene_finished = cutscene_finished;
		System.out.println(this.cutscene_finished);
	}
}

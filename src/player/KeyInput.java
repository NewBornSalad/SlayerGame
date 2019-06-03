package player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import GameObject.GameObject;
import GameObject.Handler;
import Main.Game;
import Main.ID;

public class KeyInput extends KeyAdapter{
	
	private Handler handler, handler2;
	private Game game;
	
	public KeyInput(Game game, Handler handler, Handler handler2){
		this.handler = handler;
		this.handler2 = handler2;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_W) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_A) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
				
				if(game.hp <= 0){
					if(key == KeyEvent.VK_ENTER){
						if(game.checkpoint == true){
							game.hp += 100;
							game.round = 5;
							tempObject.setX(1050);
							tempObject.setY(1250);
							
							game.spawnedEnemys = 0;
	    	            	game.deadEnemys = 0;
							
	    	            	game.respawned = true;
							handler2.object.clear();
							
						}
						
						if(game.final_checkpoint == true){
							game.hp += 100;
							game.round = 9;
							tempObject.setX(1050);
							tempObject.setY(1250);
							
							game.spawnedEnemys = 0;
	    	            	game.deadEnemys = 0;
	    	            	game.boss_activate = false;
							
	    	            	game.respawned = true;
							handler2.object.clear();
						
						}else{
							game.hp += 100;
							game.round = 1;
							tempObject.setX(1050);
							tempObject.setY(1250);
							
							game.spawnedEnemys = 0;
	    	            	game.deadEnemys = 0;
							
	    	            	game.respawned = true;
							handler2.object.clear();
						}
					}
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
			}
		} 
	}
}

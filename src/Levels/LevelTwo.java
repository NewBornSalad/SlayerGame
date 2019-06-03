package Levels;

import java.awt.image.BufferedImage;

import GameObject.Handler;
import Main.Game;
import Main.ID;
import Main.SpriteSheet;
import Tiles.Bin;
import Tiles.BottomBench;
import Tiles.DownFence;
import Tiles.Fountain;
import Tiles.FountainCollisionBottomLeft;
import Tiles.FountainCollisionBottomRight;
import Tiles.FountainCollisionTopLeft;
import Tiles.FountainCollisionTopRight;
import Tiles.Gate;
import Tiles.LeftCornerFence;
import Tiles.LeftCornerFenceDown;
import Tiles.RightCornerFence;
import Tiles.RightCornerFenceDown;
import Tiles.SidewayFence;
import Tiles.StaticGate;
import Tiles.StoneFloor;
import Tiles.TombStone;
import Tiles.TopBench;
import Tiles.TransitionTile;
import player.Player;
import player.Camera;

public class LevelTwo {
	
	private BufferedImage level2;
	private Handler handler, handler2;
	private SpriteSheet ss, enemy_ss;
	private Game game;
	private Camera camera;

	public LevelTwo(BufferedImage image, Handler handler, Handler handler2, SpriteSheet ss, Camera camera, SpriteSheet enemy_ss, Game game){
		this.handler = handler;
		this.handler2 = handler2;
		this.ss = ss;
		this.enemy_ss = enemy_ss;
		this.game = game;
		this.camera = camera;
		level2 = image;
		
		loadLevelTwo(level2);
		
	}
	
	private void loadLevelTwo(BufferedImage level2){
		int w = level2.getWidth();
		int h = level2.getHeight();
		
		for(int xx = 0; xx < w; xx++){
			for(int yy = 0; yy < h; yy++){
				int pixel = level2.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 0){
					handler.addObject(new SidewayFence(xx*32, yy*32, ID.Block, ss));
				}
				
				if(blue == 255 && green == 0){
					handler.addObject(new DownFence(xx*32, yy*32, ID.Block, ss));
				}
				
				if(green == 255 && red == 0 && blue == 0){
					handler.addObject(new Gate(xx*32, yy*32, ID.Gate, game, ss));
				}
				
				if(green == 255 && blue == 255){
					handler.addObject(new TombStone(xx*32, yy*32, ID.TombStone, ss, game));
				}
				
				if(red == 49){
					handler.addObject(new FountainCollisionTopLeft(xx*32, yy*32, ID.FountainTopLeft, ss));
				}
				
				if(red == 50){
					handler.addObject(new Fountain(xx*32, yy*32, ID.Block, ss, game));
				}
				
				if(red == 51){
					handler.addObject(new RightCornerFence(xx*32, yy*32, ID.Block, ss));
				}
				
				if(red == 52){
					handler.addObject(new LeftCornerFence(xx*32, yy*32, ID.Block, ss));
				}
				
				if(red == 53){
					handler.addObject(new RightCornerFenceDown(xx*32, yy*32, ID.Block, ss));
				}
				
				if(red == 54){
					handler.addObject(new LeftCornerFenceDown(xx*32, yy*32, ID.Block, ss));
				}
				
				if(red == 55){
					handler.addObject(new StoneFloor(xx*32, yy*32, ID.Null, ss));
				}
				
				if(red == 56){
					handler.addObject(new TopBench(xx*32, yy*32, ID.Block, ss));
				}
				
				if(red == 57){
					handler.addObject(new BottomBench(xx*32, yy*32, ID.Block, ss));
				}
				
				if(red == 58){
					handler.addObject(new Bin(xx*32, yy*32, ID.Block, ss));
				}
				if(red == 59){
					handler.addObject(new FountainCollisionBottomLeft(xx*32, yy*32, ID.FountainBottomLeft, ss));
				}
				if(red == 60){
					handler.addObject(new FountainCollisionTopRight(xx*32, yy*32, ID.FountainTopRight, ss));
				}
				if(red == 61){
					handler.addObject(new FountainCollisionBottomRight(xx*32, yy*32, ID.FountainBottomRight, ss));
				}
				if(red == 62){
					handler.addObject(new StaticGate(xx*32, yy*32, ID.Gate, ss));
				}
				if(red == 63){
					handler.addObject(new TransitionTile(xx*32, yy*32, ID.Transition, ss, game));
				}
			}
		}
		
		handler.object.addLast(new Player(1050, 1250, ID.Player, handler, handler2,  game, camera, enemy_ss));
	}
}

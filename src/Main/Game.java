package Main;

import GameObject.GameObject;
import GameObject.Handler;

import Levels.LevelOne;
import Levels.LevelTwo;
import Levels.LevelThree;
import Levels.LevelFive;
import Levels.LevelFour;

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
import Enemys.AreaFourBoss;
import Enemys.AreaOneBoss;
import Enemys.AreaTwoBoss;
import Enemys.AreaThreeBoss;
import Enemys.WalkerZombie;
import Enemys.Yeti;
import Enemys.YetiSpecial;
import Enemys.RunnerZombie;
import Enemys.Shroomer;
import Enemys.Slimer;
import Enemys.Chomper;
import Enemys.Spitter;
import Enemys.EnemyDeathSprite;

import Drops.HealthPack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import player.Camera;
import player.KeyInput;
import player.MouseInput;
import player.Player;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1500, HEIGHT = 1000;
	public static String title = "Slayer";
	
	private Thread thread;
	private boolean isRunning = false;

	private Handler handler;
	private Handler handler2;
	private Camera camera;
	private TextSprites textClass;
	private SpriteSheet ss, enemy_ss, text_sprites_ss, chosen_sheet;
	protected Game game;
	
	private LevelOne l1;
	private LevelTwo l2;
	private LevelThree l3;
	private LevelFour l4;
	private LevelFive l5;
	
	private BufferedImage level1 = null;
	private BufferedImage level2 = null;
	private BufferedImage level3 = null;
	private BufferedImage level4 = null;
	private BufferedImage level5 = null;
	
	private BufferedImage sprite_sheet = null;
	private BufferedImage enemy_sheet = null;
	private BufferedImage text_sprites = null;
	private BufferedImage floor = null;
	private BufferedImage stone_grass = null;
	
	public int currentArea = 4;
	public int round = 9;
	public int hp = 100;
	public int numOfEnemys = 60;
	public int deadEnemys;
	public int deadBoss;
	public boolean boss_activate = false;
	public boolean checkpoint = false;
	public boolean final_checkpoint = false;
	public boolean respawned = false;
	
	TimerTask task;
	Timer timer = new Timer();
	
	private int enemys[];
	protected int EnemyX, EnemyY;

	public int spawnedEnemys;
	
	public Game(){
		
		new Window(WIDTH, HEIGHT, title, this);
		start();
		
		game = this;
		handler = new Handler();
		handler2 = new Handler();
		camera = new Camera(0,0, handler, game);
		textClass = new TextSprites(game);
		this.addKeyListener(new KeyInput(this, handler, handler2));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/ZombieFirstLevel.png");
		level2 = loader.loadImage("/ZombieSecondLevel.png");
		level3 = loader.loadImage("/ZombieThirdLevel.png");
		level4 = loader.loadImage("/ZombieFourthLevel.png");
		level5 = loader.loadImage("/ZombieFithLevel.png");
		sprite_sheet = loader.loadImage("/ZombieGameSprites.png");
		enemy_sheet = loader.loadImage("/ZombieGameEnemys.png");
		text_sprites = loader.loadImage("/ZombieGameTextSprites.png");
		
		ss = new SpriteSheet(sprite_sheet);
		enemy_ss = new SpriteSheet(enemy_sheet);
		text_sprites_ss = new SpriteSheet(text_sprites);
		
		this.addMouseListener(new MouseInput(handler, camera, this, ss));
		loadSprites();
		
		setArea();
		
		timer.schedule(task, 0, 1000);
	}
	
	private void setEnemys() { 
		
		task = new TimerTask() {
			
	        public void run() {
	        	
	        	textClass.setRoundText();
	        	
	        	if(currentArea == 1){
	        		
	        		if(respawned == true){
	        			textClass.setRoundText();
	        			respawned = false;
	        		}
	        		
	        		if(round == 1){
	        			enemys = new int[60];
	        		}
	        		
	        		if(spawnedEnemys < enemys.length){
	    	        	
	    	            Random rand = new Random();
	    	            int result = rand.nextInt(6);
	    	            
	    	            Random rand2 = new Random();
	    	            
	    	            if(result == 0){
	    	            	EnemyX = 160;
	    	            	EnemyY = 100;
	    	            }

	    	            if(result == 1){
	    	            	EnemyX = 415;
	    	            	EnemyY = 738;
	    	            }
	    	            
	    	            if(result == 2){
	    	            	EnemyX = 545;
	    	            	EnemyY = 1250;
	    	            }
	    	            
	    	            if(result == 3){
	    	            	EnemyX = 1825;
	    	            	EnemyY = 97;
	    	            }
	    	            
	    	            if(result == 4){
	    	            	EnemyX = 1600;
	    	            	EnemyY = 670;
	    	            }
	    	            
	    	            if(result == 5){
	    	            	EnemyX = 1697;
	    	            	EnemyY = 1153;
	    	            }
	    	             
	    	            int runner_zombie_result = rand2.nextInt(9);
	    	            
	    	            	if(runner_zombie_result == 2 || runner_zombie_result == 6){
	    	            		handler2.addObject(new RunnerZombie(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	
	    	            	}else if(runner_zombie_result != 1 || runner_zombie_result != 4){
	    	            
	    	            		handler2.addObject(new WalkerZombie(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	}
	    	            }
	    	        	
	    	        	if(spawnedEnemys <= enemys.length && deadEnemys < spawnedEnemys){
	    	            	game.round = game.round;
	    	            	
	    	            }else if(deadEnemys >= spawnedEnemys){
	    	            	game.round += 1;
	    	            	numOfEnemys += 40;
	    	            	spawnedEnemys = 0;
	    	            	deadEnemys = 0;
	    	            	
	    	            	handler2.addObject(new HealthPack(0, 0, ID.HealthPack, ss));
	    	            	enemys = new int[numOfEnemys];
	    	            	
	    	            	pauseTimer();
	    	            	resumeTimer(5000, 1000);
	    	            	
	    	            }
	    	        	
	    	        	if(round >= 5){
	    	        		checkpoint = true;
	    	        	}
	    	        	
	    	        	if(round >= 9){
	    	        		checkpoint = false;
	    	        		final_checkpoint = true;
	    	        	}
	    	        	
	    	        	////////// BOSS CODE BELOW //////////
	    	        	
	    	        	if(game.round == 10){
	    	        		if(deadBoss < 1 & deadEnemys <= spawnedEnemys){
	    	        			game.round = game.round;
	    	        			
	    	        		}else if(deadBoss >= 1 & deadEnemys >= spawnedEnemys){
	    	        			game.round += 1;
	    	        		}
	    	        	}
	    	            
	    	            if(game.round == 10){
	    	            	if(boss_activate == false){
	    	            		handler2.addObject(new AreaOneBoss(1010, -130, ID.ZombieBoss, handler, handler2, game, enemy_ss));
	    		            	
	    	            		numOfEnemys = 30;
	    		            	enemys = new int[numOfEnemys];
	    		            	boss_activate = true;
	    	            	}
	    		        }
	    	            
	    	            if(game.round == 11){
	    	            	pauseTimer(); // end area
	    	            } 
	        	}
	        	
	        	if(currentArea == 2){
	        		textClass.setRoundText();
	        		
	        		if(respawned == true){
	        			textClass.setRoundText();
	        			respawned = false;
	        		}
	        		
	        		if(round == 1){
	        			enemys = new int[60];
	        		}
	            	
	        		if(spawnedEnemys < enemys.length){
	    	        	
	    	            Random rand = new Random();
	    	            int result = rand.nextInt(6);
	    	            
	    	            Random rand2 = new Random();
	    	            
	    	            if(result == 0){
	    	            	EnemyX = 670;
	    	            	EnemyY = 235;
	    	            }

	    	            if(result == 1){
	    	            	EnemyX = 415;
	    	            	EnemyY = 738;
	    	            }
	    	            
	    	            if(result == 2){
	    	            	EnemyX = 760;
	    	            	EnemyY = 1120;
	    	            }
	    	            
	    	            if(result == 3){
	    	            	EnemyX = 1450;
	    	            	EnemyY = 300;
	    	            }
	    	            
	    	            if(result == 4){
	    	            	EnemyX = 1605;
	    	            	EnemyY = 680;
	    	            }
	    	            
	    	            if(result == 0){
	    	            	EnemyX = 1405;
	    	            	EnemyY = 990;
	    	            }
	    	            
	    	            int slimer_result = rand2.nextInt(9);
	    	            
	    	            	if(slimer_result == 2 || slimer_result == 6){
	    	            		handler2.addObject(new Slimer(EnemyX, EnemyY, ID.Slimer, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	
	    	            	}else if(slimer_result != 1 || slimer_result != 4){
	    	            
	    	            		handler2.addObject(new Shroomer(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	}
	    	            }
	    	        	
	    	        	if(spawnedEnemys <= enemys.length && deadEnemys < spawnedEnemys){
	    	            	game.round = game.round;
	    	            	
	    	            }else if(deadEnemys >= spawnedEnemys){
	    	            	game.round += 1;
	    	            	numOfEnemys += 40;
	    	            	spawnedEnemys = 0;
	    	            	deadEnemys = 0;
	    	            	
	    	            	handler2.addObject(new HealthPack(0, 0, ID.HealthPack, ss));
	    	            	enemys = new int[numOfEnemys];
	    	            	
	    	            	pauseTimer();
	    	            	resumeTimer(5000, 1000);
	    	            	
	    	            }
	    	        	
	    	        	if(round >= 5){
	    	        		checkpoint = true;
	    	        	}
	    	        	
	    	        	if(round >= 9){
	    	        		checkpoint = false;
	    	        		final_checkpoint = true;
	    	        	}
	    	        	
	    	        	////////// BOSS CODE BELOW //////////
	    	        	
	    	        	if(game.round == 10){
	    	        		if(deadBoss < 1 & deadEnemys <= spawnedEnemys){
	    	        			game.round = game.round;
	    	        			
	    	        		}else if(deadBoss >= 1 & deadEnemys >= spawnedEnemys){
	    	        			game.round += 1;
	    	        		}
	    	        	}
	    	            
	    	            if(game.round == 10){
	    	            	if(boss_activate == false){
	    	            		handler2.addObject(new AreaTwoBoss(990, -130, ID.ShroomerBoss, handler, handler2, game, enemy_ss));
	    		            	
	    	            		numOfEnemys = 30;
	    		            	enemys = new int[numOfEnemys];
	    		            	boss_activate = true;
	    	            	}
	    		        }
	    	            
	    	            if(game.round == 11){
	    	            	pauseTimer(); // end area
	    	            } 
	        	}
	        	
	        	if(currentArea == 3){
	        		textClass.setRoundText();
	        		
	        		if(respawned == true){
	        			textClass.setRoundText();
	        			respawned = false;
	        		}
	        		
	        		if(round == 1){
	        			enemys = new int[60];
	        		}
	            	
	        		if(spawnedEnemys < enemys.length){
	    	        	
	    	            Random rand = new Random();
	    	            int result = rand.nextInt(6);
	    	            
	    	            Random rand2 = new Random();

	    	            if(result == 0){
	    	            	EnemyX = 80;
	    	            	EnemyY = 200;
	    	            }

	    	            if(result == 1){
	    	            	EnemyX = 80;
	    	            	EnemyY = 600;
	    	            }
	    	            
	    	            if(result == 2){
	    	            	EnemyX = 80;
	    	            	EnemyY = 1000;
	    	            }
	    	            
	    	            if(result == 3){
	    	            	EnemyX = 1900;
	    	            	EnemyY = 250;
	    	            }
	    	            
	    	            if(result == 4){
	    	            	EnemyX = 1900;
	    	            	EnemyY = 650;
	    	            }
	    	            
	    	            if(result == 5){
	    	            	EnemyX = 1900;
	    	            	EnemyY = 1000;
	    	            }
	    	            
	    	            int spitter_result = rand2.nextInt(9);
	    	            
	    	            	if(spitter_result == 2 || spitter_result == 6){
	    	            		handler2.addObject(new Spitter(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	
	    	            	}else if(spitter_result != 1 || spitter_result != 4){
	    	            
	    	            		handler2.addObject(new Chomper(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	}
	    	            }
	    	        	
	    	        	if(spawnedEnemys <= enemys.length && deadEnemys < spawnedEnemys){
	    	            	game.round = game.round;
	    	            	
	    	            }else if(deadEnemys >= spawnedEnemys){
	    	            	game.round += 1;
	    	            	numOfEnemys += 40;
	    	            	spawnedEnemys = 0;
	    	            	deadEnemys = 0;
	    	            	
	    	            	handler2.addObject(new HealthPack(0, 0, ID.HealthPack, ss));
	    	            	enemys = new int[numOfEnemys];
	    	            	
	    	            	pauseTimer();
	    	            	resumeTimer(5000, 1000);
	    	            	
	    	            }
	    	        	
	    	        	if(round >= 5){
	    	        		checkpoint = true;
	    	        	}
	    	        	
	    	        	if(round >= 9){
	    	        		checkpoint = false;
	    	        		final_checkpoint = true;
	    	        	}
	    	        	
	    	        	////////// BOSS CODE BELOW //////////
	    	        	
	    	        	if(game.round == 10){
	    	        		if(deadBoss < 1 & deadEnemys <= spawnedEnemys){
	    	        			game.round = game.round;
	    	        			
	    	        		}else if(deadBoss >= 1 & deadEnemys >= spawnedEnemys){
	    	        			game.round += 1;
	    	        		}
	    	        	}
	    	            
	    	            if(game.round == 10){
	    	            	if(boss_activate == false){
	    	            		handler2.addObject(new AreaThreeBoss(990, -130, ID.ChomperBoss, handler, handler2, game, enemy_ss));
	    		            	
	    	            		numOfEnemys = 30;
	    		            	enemys = new int[numOfEnemys];
	    		            	boss_activate = true;
	    	            	}
	    		        }
	    	            
	    	            if(game.round == 11){
	    	            	pauseTimer(); // end area
	    	            } 
	        	}
	        	
	        	
	        	if(currentArea == 4){
	        		textClass.setRoundText();
	        		
	        		if(respawned == true){
	        			textClass.setRoundText();
	        			respawned = false;
	        		}
	        		
	        		if(round == 9){
	        			enemys = new int[1];
	        		}
	            	
	        		if(spawnedEnemys < enemys.length){
	    	        	
	    	            Random rand = new Random();
	    	            int result = rand.nextInt(6);
	    	            
	    	            Random rand2 = new Random();
	    	            
	    	            if(result == 0){
	    	            	EnemyX = 600;
	    	            	EnemyY = 170;
	    	            }

	    	            if(result == 1){
	    	            	EnemyX = 170;
	    	            	EnemyY = 520;
	    	            }
	    	            
	    	            if(result == 2){
	    	            	EnemyX = 275;
	    	            	EnemyY = 1140;
	    	            }
	    	            
	    	            if(result == 3){
	    	            	EnemyX = 1780;
	    	            	EnemyY = 180;
	    	            }
	    	            
	    	            if(result == 4){
	    	            	EnemyX = 1420;
	    	            	EnemyY = 650;
	    	            }
	    	            
	    	            if(result == 5){
	    	            	EnemyX = 1710;
	    	            	EnemyY = 1230;
	    	            }
	    	            
	    	            int spitter_result = rand2.nextInt(9);
	    	            
	    	            	if(spitter_result == 2 || spitter_result == 6){
	    	            		handler2.addObject(new YetiSpecial(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	
	    	            	}else if(spitter_result != 1 || spitter_result != 4){
	    	            		handler2.addObject(new Yeti(EnemyX, EnemyY, ID.Enemy, handler, handler2, game, enemy_ss));
	    	            		spawnedEnemys++;
	    	            	}
	    	            }
	    	        	
	    	        	if(spawnedEnemys <= enemys.length && deadEnemys < spawnedEnemys){
	    	            	game.round = game.round;
	    	            	
	    	            }else if(deadEnemys >= spawnedEnemys){
	    	            	game.round += 1;
	    	            	numOfEnemys += 40;
	    	            	spawnedEnemys = 0;
	    	            	deadEnemys = 0;
	    	            	
	    	            	handler2.addObject(new HealthPack(0, 0, ID.HealthPack, ss));
	    	            	enemys = new int[numOfEnemys];
	    	            	
	    	            	pauseTimer();
	    	            	resumeTimer(5000, 1000);
	    	            	
	    	            }
	    	        	
	    	        	if(round >= 5){
	    	        		checkpoint = true;
	    	        	}
	    	        	
	    	        	if(round >= 9){
	    	        		checkpoint = false;
	    	        		final_checkpoint = true;
	    	        	}
	    	        	
	    	        	////////// BOSS CODE BELOW //////////
	    	        	
	    	        	if(game.round == 10){
	    	        		if(deadBoss < 1 & deadEnemys <= spawnedEnemys){
	    	        			game.round = game.round;
	    	        			
	    	        		}else if(deadBoss >= 1 & deadEnemys >= spawnedEnemys){
	    	        			game.round += 1;
	    	        		}
	    	        	}
	    	            
	    	            if(game.round == 10){
	    	            	if(boss_activate == false){
	    	            		handler2.addObject(new AreaFourBoss(1010, -130, ID.Enemy, handler, handler2, game, enemy_ss));
	    		            	
	    	            		numOfEnemys = 30;
	    		            	enemys = new int[numOfEnemys];
	    		            	boss_activate = true;
	    	            	}
	    		        }
	    	            
	    	            if(game.round == 11){
	    	            	pauseTimer(); // end area
	    	            } 
	        	}
	            
	            System.out.println(spawnedEnemys);
	            System.out.println("Dead zombies are " + deadEnemys + " and round is " + game.round);
			}
		};
	}
	
	public void setArea(){
		
		if(currentArea == 1){
			l1 = new LevelOne(level1, handler, handler2,ss, camera, enemy_ss, game);
			
		}else if(currentArea == 2){
			handler.object.clear();
			resumeTimer(5000, 1000);
			l2 = new LevelTwo(level2, handler, handler2, ss, camera, enemy_ss, game);
			
		}else if(currentArea == 3){
			handler.object.clear();
			resumeTimer(5000, 2000);
			l3 = new LevelThree(level3, handler, handler2,  ss, camera, enemy_ss, game);
			
		}else if(currentArea == 4){
			handler.object.clear();
			resumeTimer(5000, 1000);
			l4 = new LevelFour(level4, handler, handler2,  ss, camera, enemy_ss, game);
			
		}else if(currentArea == 5){
			handler.object.clear();
			resumeTimer(5000, 1000);
			l5 = new LevelFive(level5, handler, handler2,  ss, camera, enemy_ss, game);
		}
	}

	public void loadSprites(){
		
		if(currentArea == 1){
			floor = ss.grabImage(1, 1, 32, 32);
			
		}else if(currentArea == 2){
			floor = ss.grabImage(1, 5, 32, 32);
			
		}else if(currentArea == 3){
			floor = ss.grabImage(1, 9, 32, 32);
			
		}else if(currentArea == 4){
			floor = ss.grabImage(1, 11, 32, 32);
			
		}else if(currentArea == 5){
			floor = ss.grabImage(1, 12, 32, 32);
		}
		
		stone_grass = ss.grabImage(2, 1, 32, 32);
		textClass.setTextSpriteSheet(text_sprites_ss);
	}
	
	private synchronized void start(){
		if(isRunning == true) 
			return; 
	
		thread = new Thread(this, "thread");
		isRunning = true;
		thread.start();
		
	}
	
	private synchronized void stop(){
		if(isRunning == false) 
			return; 
		
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//game loop
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(isRunning){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				lastTime += 1000;
				System.out.println(amountOfTicks + ":ups  " + frames + ":fps " + " Thread is alive");
				timer += 1000;
				frames = 0;
			}
		}
		stop();	// incase it doesn't stop the first time
		
	}
	
	private void tick() {
		// updates the game
		
		for(int i = 0; i < handler.object.size(); i++){				
			if(handler.object.get(i).getId() == ID.Player){    		// Use ID.Player to access players x and y in camera class
				camera.tick(handler.object.get(i));												
			}
		}
		
		setEnemys();
		handler.tick();
		handler2.tick(); 
	}
	
	public void pauseTimer(){
		this.timer.cancel();
	}
	
	public void resumeTimer(int pauseTime, int repeatTime){
		this.timer = new Timer();
		this.timer.schedule(task, pauseTime, repeatTime);
	}
	
	private void render() {
		// renders the game
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////////////////////////
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		for(int xx = 0; xx < 2000; xx+=32){
			for(int yy = 0; yy < 1500; yy+=32){
				g.drawImage(floor, xx, yy, null);
			}
		}
		
		for(int xx = 992; xx < 1120; xx+=32){
			for(int yy = 0; yy < 4000; yy+=32){
				g.drawImage(stone_grass, xx, yy, null);
			}
		}
		
		for(int xx = 928; xx < 1170; xx+=32){
			for(int yy = 576; yy < 760; yy+=32){
				g.drawImage(stone_grass, xx, yy, null);
			}
		}
		
		handler.render(g);
		handler2.render(g);
		
		g2d.translate(camera.getX(), camera.getY());
		
		g.drawImage(textClass.getTextImage(2), 5, 40, null);
		g.drawImage(textClass.getTextImage(1), 145, 42, null);
		
		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(5, 5, hp*2, 32);
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 32);
		
		if(hp <= 0){
			g.drawImage(textClass.getTextImage(3), WIDTH/2 - 100, HEIGHT/2 - 32, null);
			hp = 0;
		}
		
		bs.show();
		g.dispose();
		
	}
	
	public static void main(String[] args){
		new Game();
	}
}

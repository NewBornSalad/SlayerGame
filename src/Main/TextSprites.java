package Main;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class TextSprites {
	
	private Game game;
	private SpriteSheet text_sprites_ss;
	private BufferedImage number_text = null;
	private BufferedImage game_over_text = null;
	private BufferedImage general_text = null;
	private BufferedImage chosen_image = null;
	
	public TextSprites(Game game){
		this.game = game;
	}
	
	public void setTextSpriteSheet(SpriteSheet text_sprites_ss){
		this.text_sprites_ss = text_sprites_ss;
	}
	
	public void setRoundText(){
		game_over_text = text_sprites_ss.grabImage(1, 2, 252, 32);
		
		if(game.round == 1){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(1, 4, 32, 32);
			
		}else if(game.round == 2){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(2, 4, 32, 32);
			
		}else if(game.round == 3){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(3, 4, 32, 32);
			
		}else if(game.round == 4){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(4, 4, 32, 32);
			
		}else if(game.round == 5){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(5, 4, 32, 32);
			
		}else if(game.round == 6){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(6, 4, 32, 32);
			
		}else if(game.round == 7){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(7, 4, 32, 32);
			
		}else if(game.round == 8){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(8, 4, 32, 32);
			
		}else if(game.round == 9){
			general_text = text_sprites_ss.grabImage(1, 1, 160, 32);
			number_text = text_sprites_ss.grabImage(9, 4, 32, 32);
			
		}
		
		if(game.round == 10){
			general_text = text_sprites_ss.grabImage(1, 3, 260, 32);
			number_text = text_sprites_ss.grabImage(10, 10, 32, 32); // Make render nothing so just "Final" appears 
		}
	}
	
	protected BufferedImage getTextImage(int imageWanted){
		if(imageWanted == 1){
			chosen_image = number_text;
			
		}else if(imageWanted == 2){
			chosen_image = general_text;
			
		}else if(imageWanted == 3){
			chosen_image = game_over_text;
		}
		
		return chosen_image;
	}
}

package Main;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window {

	public Window(int width, int height, String title, Game game){
		
		JFrame frame = new JFrame(title);
		
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SlayerLogo.png")));
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
	}
}

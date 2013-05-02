import java.awt.Graphics;

import javax.swing.JFrame;


public class Window {
	public static void main(String[] args){
		JFrame frame = new JFrame("Game");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Game(frame);
	}
}

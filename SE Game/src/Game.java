import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;


public class Game {
	public Game(JFrame frame){

		GamePanel gm = new GamePanel(frame){

			@Override
			public void draw(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				
				MenuButton mb1 = new MenuButton("New Game",150,75,100,30);
				mb1.draw(g2d);
				MenuButton mb2 = new MenuButton("Continue",150,125,100,30);
				mb2.draw(g2d);
				MenuButton mb3 = new MenuButton("Options",150,175,100,30);
				mb3.draw(g2d);
				MenuButton mb4 = new MenuButton("Quit",150,225,100,30);
				mb4.draw(g2d);
				
				DrawText title = new DrawText("Best Game Ever",new Font("Serif",Font.BOLD,30),200,40);
				title.draw(g2d);
				//g2d.drawRect(200,100,100,30);
				//g.drawString("Start Game", 215 ,120);
				//g2d.drawRect()
			}

			@Override
			public void action() {
				
				
			}

			@Override
			public void init() {
				
				
			}
			
		};
		
		GameMenuPanel gmp = new GameMenuPanel(frame,gm);
		gmp.run();
		frame.add(gmp);
		
	}
}

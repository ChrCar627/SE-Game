import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fun.*;



public class GameInterFace extends GamePanel{

	private static final long serialVersionUID = 1L;
	
	final int W = 100;
	final int H = 40; 
	final Font font = new Font("SEREF", Font.BOLD, 25);
	
	final Rectangle button1 = new Rectangle(100, 100, W, H) ;
	final Rectangle button2 = new Rectangle(100, 150, W, H) ;
	
	int fw;//frame width
	int fh;//frame height
	
	Color color1; 
	Color color2; 
	
	Animation ani; 
	
	Animation immune; 
	
	Animation immune2; 
	
	boolean b = false; 
	
	Animation bubble; 
	
	Music mainMusic; 
	
	public GameInterFace(JFrame f) {
		super(f);
		fw = f.getWidth();
		fh = f.getHeight();
		
		color1 = Color.PINK;
		color2 = Color.PINK;
		
		//setting animation 
		PurifiedImage pure = new PurifiedImage();
		Image[] im = {pure.TranseptBufferedImage("res/ball/normal/ball1.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball2.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball3.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball4.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball5.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball6.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball7.png")
		};
		
		ani  = new Animation(im,1);
		
		Image[] im2 = {pure.TranseptBufferedImage("res/ball/bubble/ball1.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball2.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball3.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball4.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball5.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball6.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball7.png")
		};
		
		
		bubble = new Animation(im2, 1);
		
		
		Image[] im3 = {pure.TranseptBufferedImage("res/ball/immune/ball1.png"),
				pure.TranseptBufferedImage("res/ball/immune/ball2.png"),
				pure.TranseptBufferedImage("res/ball/immune/ball3.png"),
				
		};
		
		immune = new Animation(im3,2);
		
		Image[] im4 = {pure.TranseptBufferedImage("res/ball/immune/ball_1.png"),
				pure.TranseptBufferedImage("res/ball/immune/ball_2.png"),
				pure.TranseptBufferedImage("res/ball/immune/ball_3.png"),
				
		};
		
		immune2 = new Animation(im4,2);
		
		mainMusic = new Music("res/DigitalStream.wav");
		
		//mainMusic.loop();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2d.setRenderingHints(rh);
		
		g2d.setColor(color1);
		g2d.fill(button1);
		g2d.setColor(color2);
		g2d.fill(button2);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.drawString("Play", 125, 130);
		g2d.drawString("Quit", 120, 180);
		
		g2d.setColor(Color.red.darker());
		g2d.setStroke(new BasicStroke(5f));
		g2d.draw(button1);
		g2d.draw(button2);
		
		
		//Animation test
		
		g.drawImage(ani.getAnimation(), 250, 250, null); 
		
		g.drawImage(bubble.getAnimation(), 200, 250, null); 
		
		if(b){
			g.drawImage(immune.getAnimation(), 250, 250, null);
			g.drawImage(immune2.getAnimation(), 200, 250, null); 
		}
		
	}
	@Override
	public void action() {
		if(input.mouseIsClicked())
			if(button1.contains(input.point))
				setStage(1);
			else if(button2.contains(input.point)){
				if (JOptionPane.showConfirmDialog(
					    null,
					    "Fix me please :'(",
					    "I'm broken",
					    JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION);
					System.exit(0);
			}
		
		if(button1.contains(input.point))
			color1 = Color.RED.brighter();
		else
			color1 = Color.PINK;
		
		if(button2.contains(input.point))
			color2 = Color.RED.brighter();
		else 
			color2 = Color.PINK;
		
		if(input.keyIsClicked(KeyEvent.VK_SPACE))
			b = !b;
		
	}//end action

	@Override
	public void init() {
		
	}

}

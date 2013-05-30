import java.awt.Graphics;
import java.awt.Image;

import fun.*;


public class BubbleObstacle extends Obstacle {

	private static final PurifiedImage pure = new PurifiedImage();
	private static final Image[] im = { pure.TranseptBufferedImage("res/shield/shield_1.png"),
		pure.TranseptBufferedImage("res/shield/shield_2.png"),pure.TranseptBufferedImage("res/shield/shield_3.png"),
		pure.TranseptBufferedImage("res/shield/shield_4.png"),pure.TranseptBufferedImage("res/shield/shield_5.png"),
		pure.TranseptBufferedImage("res/shield/shield_6.png"),pure.TranseptBufferedImage("res/shield/shield_7.png")
	};
	
	
	
	public static Animation bubbleAnimation = new Animation(im);
	
	public BubbleObstacle() {
		super(20, 26);
		
		//animation = new Animation(m);//no animation, just an image. 
		image = pure.TranseptBufferedImage("res/bottle.png");
	}

	
	@Override
	public void drawMe(Graphics g){
		g.drawImage(image, x, y, null);
	}
	
	
}



import java.awt.Image;
import java.awt.Rectangle;
 
import fun.Animation;
import fun.Obstacle;


public class FireBallObstacle extends Obstacle {

	public static Animation fireBallUpAnimation; 
	public static Animation fireBallLeftAnimation; 
	public static Animation fireBallRightAnimation; 
	
	
	
	
	public FireBallObstacle() {
		super( 0, 0);
		
		PurifiedImage pure = new PurifiedImage();
		Image[] im = { pure.TranseptBufferedImage("res/fireBalls/fire_right/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_right/fire2.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_right/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_right/fire4.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_right/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_right/fire2.png")
				};
		fireBallRightAnimation = new Animation(im, 1);
		
		Image[] im2 = { pure.TranseptBufferedImage("res/fireBalls/fire_up/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_up/fire2.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_up/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_up/fire4.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_up/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_up/fire2.png")
				};
		
		fireBallUpAnimation = new Animation(im2, 1);
		
		
		
		animation = fireBallRightAnimation;
		width =  20;
		height = 26;
	}
	@Override
	public Rectangle getShape(){
		return new Rectangle(x+80,y+2,width,height);
	}
	
	
}

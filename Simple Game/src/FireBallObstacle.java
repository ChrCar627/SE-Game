

import java.awt.Image;
import java.awt.Rectangle;
 
import fun.Animation;
import fun.Obstacle;


public class FireBallObstacle extends Obstacle {

	private static PurifiedImage pure = new PurifiedImage();
	private static Image[] im = { pure.TranseptBufferedImage("res/fireBalls/fire_right/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_right/fire2.png"),
			pure.TranseptBufferedImage("res/fireBalls/fire_right/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_right/fire4.png"),
			pure.TranseptBufferedImage("res/fireBalls/fire_right/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_right/fire2.png")
			};
	private static Image[] im2 = { pure.TranseptBufferedImage("res/fireBalls/fire_up/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_up/fire2.png"),
			pure.TranseptBufferedImage("res/fireBalls/fire_up/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_up/fire4.png"),
			pure.TranseptBufferedImage("res/fireBalls/fire_up/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_up/fire2.png")
			};
	
	public static Animation fireBallUpAnimation  = new Animation(im2, 1); 
	public static Animation fireBallLeftAnimation; 
	public static Animation fireBallRightAnimation = new Animation(im, 1);

	public int speed = 0; 
	
	public FireBallObstacle(int s) {
		super( 14, 18); //set width and height

		speed = s; 
		
		animation = new Animation(im);
	}
	@Override
	public Rectangle getShape(){  // the shape is at the end of the fire ball 
		return new Rectangle(x+81,y+7,width,height);
	}
	
	
}

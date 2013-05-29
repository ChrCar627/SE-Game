

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
	private static Image[] im3 = { pure.TranseptBufferedImage("res/fireBalls/fire_left/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_left/fire2.png"),
		pure.TranseptBufferedImage("res/fireBalls/fire_left/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_left/fire4.png"),
		pure.TranseptBufferedImage("res/fireBalls/fire_left/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_left/fire2.png")
		};
	private static Image[] im4 = { pure.TranseptBufferedImage("res/fireBalls/fire_down/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_down/fire2.png"),
		pure.TranseptBufferedImage("res/fireBalls/fire_down/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_down/fire4.png"),
		pure.TranseptBufferedImage("res/fireBalls/fire_down/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_down/fire2.png")
		};
	
	public static Animation fireBallRightAnimation = new Animation(im);
	public static Animation fireBallUpAnimation  = new Animation(im2); 
	public static Animation fireBallLeftAnimation = new Animation(im3);
	public static Animation fireBallDownAnimation = new Animation(im4);

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



import java.awt.Image;

import fun.Animation;
import fun.Obstacle;


public class FireBallObstacle extends Obstacle {

	
	public FireBallObstacle() {
		super( 0, 0);
		
		PurifiedImage pure = new PurifiedImage();
		Image[] im = { pure.TranseptBufferedImage("res/fireBalls/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire2.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire4.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire2.png")
				};
		
		animation = new Animation(im, 1);
		
		width =  60;
		height = 30;
	}

}

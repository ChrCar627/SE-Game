import java.awt.Image;


import fun.Animation;
import fun.Obstacle;


public class Coin extends Obstacle {

	
	
	private static PurifiedImage pure = new PurifiedImage();
	private static Image[] im = {
			pure.TranseptBufferedImage("res/coin/coin_1.png"),
			pure.TranseptBufferedImage("res/coin/coin_2.png"),
			pure.TranseptBufferedImage("res/coin/coin_3.png"),
			pure.TranseptBufferedImage("res/coin/coin_4.png")
			
	};
	
	private static int[] d = {5,1,1,1};
	
	public static Animation coinAnimation  = new Animation(im , d); 
	
	public Coin() {
		super(0, 0);
	
		
		
		
		
		animation = coinAnimation; 
		
		width = animation.getCurrentImage().getWidth(null);
		height = animation.getCurrentImage().getHeight(null);
	}

}
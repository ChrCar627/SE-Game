import java.awt.Image;


import fun.Animation;
import fun.Music;
import fun.Obstacle;


public class CoinObstacle extends Obstacle {

	
	
	private static PurifiedImage pure = new PurifiedImage();
	public Music coinSound = new Music("res/coin/coin-04.wav");
	
	private static Image[] im = {
			pure.TranseptBufferedImage("res/coin/coin_1.png"),
			pure.TranseptBufferedImage("res/coin/coin_2.png"),
			pure.TranseptBufferedImage("res/coin/coin_3.png"),
			pure.TranseptBufferedImage("res/coin/coin_4.png")
			
	};
	
	private static int[] d = {5,1,1,1};
	
	public static Animation coinAnimation  = new Animation(im , 1); 
	
	public CoinObstacle() {
		super(0, 0);

		animation = new Animation(im, d); 
		
		width = animation.getCurrentImage().getWidth(null);
		height = animation.getCurrentImage().getHeight(null);
	}

}
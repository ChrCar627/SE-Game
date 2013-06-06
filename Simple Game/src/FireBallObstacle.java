

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
import fun.Animation;
import fun.GamePanel;
import fun.Music;
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
	public Clip fireSound ;
	public int speed = 0; 
	
	public FireBallObstacle(int s) {
		super( 14, 18); //set width and height
		
		
		 try {
			 File soundFile = new File("res/fireBalls/Large Fireball-SoundBible.com-301502490.wav");
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			 fireSound = AudioSystem.getClip();
			 
			 fireSound.open(audioIn);
			 
	      } catch (FileNotFoundException e){
	    	 // System.err.println(e);
	    	  Formatter output;
			try {
				output = new Formatter("errorLog.txt");
				 output.format("Error!! %s", e.toString());
				 output.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	      } catch (UnsupportedAudioFileException e) {
	         //e.printStackTrace();
	      } catch (IOException e) {
	         //e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         //e.printStackTrace();
	      }
		
		speed = s; 
		
		animation = new Animation(im);
	}
	@Override
	public Rectangle getShape(){  // the shape is at the end of the fire ball 
		return new Rectangle(x+81,y+7,width,height);
	}
	
	
}

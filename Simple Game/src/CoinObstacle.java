import java.awt.Image;
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


public class CoinObstacle extends Obstacle {

	
	
	private static PurifiedImage pure = new PurifiedImage();
	public Clip coinSound ;
	
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

		
		 try {
			 File soundFile = new File("res/coin/coin-04.wav");
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			 coinSound = AudioSystem.getClip();
			 
			 coinSound.open(audioIn);
			 
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
		
		animation = new Animation(im, d); 
		
		width = animation.getCurrentImage().getWidth(null);
		height = animation.getCurrentImage().getHeight(null);
	}

}
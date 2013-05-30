package fun;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
 

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {

	Clip clip; 
	public Music(String fileName){
		
		 try {
	        
			 File soundFile = new File(fileName);
			 //System.out.println(soundFile.getAbsolutePath());
			 //System.out.println(getClass().getResource("coin-04.wav"));
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			 clip = AudioSystem.getClip();
			 
			 clip.open(audioIn);
			 
	      } catch (FileNotFoundException e){
	    	  System.err.println(e);
	    	  
	      } catch (UnsupportedAudioFileException e) {
	         //e.printStackTrace();
	      } catch (IOException e) {
	         //e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         //e.printStackTrace();
	      }
	}//end music  
	
	public void play(){
		if(clip!=null&&clip.isOpen())
			clip.start(); 
	}
	
	public void stop(){
		if(clip!=null&&clip.isOpen())
			clip.stop();
	}
	
	public void setFramePosition(int frames){
		clip.setFramePosition(frames);
	}
	
	public void restart(){
		if(clip!=null&&clip.isOpen())
			clip.setFramePosition(0);
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public int getFramePosition(){
		return clip.getFramePosition();
	}
	
	
	
	
	
	
}//end 

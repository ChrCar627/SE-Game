package fun;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Formatter;
 

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {

	Clip clip; 
	public Music(Clip c){
		clip = c;
		
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
		if(clip!=null && clip.isOpen())
			clip.setFramePosition(0);
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public int getFramePosition(){
		return clip.getFramePosition();
	}
	
	
	
	
	
	
}//end 

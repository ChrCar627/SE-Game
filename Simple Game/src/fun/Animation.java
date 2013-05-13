package fun;

import java.awt.Image;


public class Animation {

	private Image[] image; 
	private int[] delay; 
	private int frame =0; 
	private int timer=-1; 
	
	
	public Animation(Image[] im, int[] d){
		image = im; 
		delay = d; 
	}
	
	public Animation(Image[] im, int d){
		int[] delay = new int[im.length];
		
		for(int i=0;i<delay.length;i++)
			delay[i] = d; 
		
		image = im; 
		this.delay = delay; 
	}
	
	
	// ##### GET~TERS #####
	
	public Image getAnimation(){
		timer++;
		
		if(timer == delay[frame]){
			timer = 0;
			if(frame+1 == image.length)
				frame =0;
			else
				frame++;
		}
		 
		return image[frame];
	}
	
	public Image getImageAt(int frame){
		return image[frame];
	}
	
	public Image getCurrentImage(){
		return image[frame];
	}
	
	public int getImageLength(){
		return image.length;
	}
	
	public Image[] getImageArray(){
		return image.clone();
	}
	
	// ##### SET~TERS ######
	
	public void setImages(Image[] im) throws Exception{
		if(im.length == image.length)
			image = im; 
		else 
			throw new Exception(String.format("Array length doesn't " +
					"match: input %d != current %d", im.length, image.length));
	}
	
	// #### Something else
	public void nextFrame(){
		if(frame + 1 == image.length )
			frame =0;
		else frame ++; 
	}
	
	public void previousFrame(){
		if(frame-1 == -1)
			frame = image.length-1;
		else 
			frame--; 
	}
	
	
}//end animation class

package fun;

import java.awt.Image;


public class Animation {

	private Image[] image; 
	private int[] delay; 
	private int frame =0; 
	private int timer=-1; 
	
	private boolean stop; 
	
	public Animation(Image[] im, int[] d){
		image = im; 
		delay = d; 
		stop = false;
	}
	
	public Animation(Image[] im, int d){
		if(d<=0)
			d=1; 
		int[] delay = new int[im.length];
		
		for(int i=0;i<delay.length;i++)
			delay[i] = d; 
		
		image = im; 
		this.delay = delay; 
	}
	
	public Animation(Image[] im){
		this(im, 1);
	}
	
	// ##### GET~TERS #####
	
	public Image getAnimationImage(){
		if(!stop)
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
	
	public Animation getAnimation(){
		try {
			return (Animation) this.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("cannot clone animation");
		}
		return null; 
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
	
	public void setFrame(int frame){
		this.frame = frame; 
	}
	
	public void setStop(boolean b){
		stop = b;
	}
	
}//end animation class

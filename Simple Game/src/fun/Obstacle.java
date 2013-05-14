package fun;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
 
public class Obstacle {

	public int x; 
	public int y; 
	public int width; 
	public int height;
	
	public Animation animation; 
	public Image image; 

	public Obstacle(int w, int h){
		this.x = 0; 
		this.y = 0;
		width = w; 
		height = h; 
	}
	
	public Image getAnimation(){
		return animation.getAnimation();
	}
	
	public Rectangle getShape(){
		return new Rectangle(x, y, width, height);
	}
	
	public void drawMe(Graphics g){
		Graphics2D g2d = (Graphics2D) g; 
		g2d.fill(getShape());
	}
	
	public void reset(int startPoint){
		x = startPoint;
	}
	
	
}

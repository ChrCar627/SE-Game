import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class DrawText {

	static Font mainfont;
	private int x,y;
	String message;
	public DrawText(String s,Font f,int xx, int yy){
		x = xx;
		y = yy;
		message = s;
		mainfont = f;
	}
	
	public void draw(Graphics g){
		FontMetrics fm = g.getFontMetrics(mainfont);
		int textwidth = fm.stringWidth(message);
		
		
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(mainfont);
		g.drawString(message, x-(textwidth/2) ,y);
	}
}

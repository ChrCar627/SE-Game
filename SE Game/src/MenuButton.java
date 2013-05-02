import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class MenuButton {
	static Font menufont = new Font("Serif",Font.PLAIN,12);
	private int x,y,width,height;
	String message;
	public MenuButton(String s,int xx, int yy, int w, int h){
		x = xx;
		y = yy;
		width = w;
		height = h;
		message = s;
	}
	
	public void draw(Graphics g){
		FontMetrics fm = g.getFontMetrics(menufont);
		int textwidth = fm.stringWidth(message);
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect(x,y,width,height);
		g.setFont(menufont);
		g.drawString(message, (x+(width/2))-(textwidth/2) ,y+20);
	}
}

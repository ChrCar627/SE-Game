package fun;

//READ ME:

	/* Using this class will allow you to interact with keys without using keyListeners
	 * ###############################################################
	 * This class is mainly used for games that uses keys
	 * Feel free to use it
	 * It is created by KLD and I got the idea from Slick 
	 * ###############################################################
	 * Three main methods were created : 
	 * keyIsDown(int keyCode), keyIsClicked(int keyCode), keyIsUp(int keyCode) : boolean
	 * #########################3#####################################
	 * One additional function is also provided which is : setListener(Component c): void
	 *  use Event.KeyCode(VK_"anyKey") : int    and pass it to the three main functions. 
	 */
	


import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;





public class Input implements KeyListener, MouseListener, MouseMotionListener {

	
	private static boolean[] thePressedKey;
	private static boolean[] clicked;
	private static boolean mouseClicked;  
	private static boolean mousePressed; 
	public static Point point; 
	
	
	public Input(Component c) {
		this();
		c.addKeyListener(this);	
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
	}
	public Input() {
		//400 is just a random number. You could replace it with any other big number.  
		thePressedKey = new boolean[400];
		clicked = new boolean[400];
		
		point = new Point(0, 0); 
		//Initializing 
		for (int i = 0; i < 400; i++) {
			thePressedKey[i] = false;
			clicked[i] = false;
		}
	
		mouseClicked = false;  
		mousePressed = false; 
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!thePressedKey[e.getKeyCode()])
			clicked[e.getKeyCode()] = true;
		thePressedKey[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		thePressedKey[e.getKeyCode()] = false;
		clicked[e.getKeyCode()] = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	//Returns true with is key is down and false with the key is up. 
	//This method is usable for motion in games
	public boolean keyIsDown(int keyCode) {
		return thePressedKey[keyCode];
	}
	//Returns true with is key is up and false with the key is down. 
	public boolean keyIsUp(int keyCode) {
		return !thePressedKey[keyCode];
	}

	
	//This method returns true only the first time the key is hit.
	//It's useful when you what to play a sound with a key
	public boolean keyIsClicked(int keyCode) {

		if (clicked[keyCode]) {
			clicked[keyCode] = false;
			return true;
		}

		return false;
	}
	//You can use this method to add Listener
	 public void addListenerTo(Component c){
		c.addKeyListener(this);	
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
	 }
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {
		point = e.getPoint();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (!mousePressed)
			mouseClicked = true;
			mousePressed= true;
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked = false; 
		mousePressed = false;
	}
	
	public boolean mouseIsClicked(){
		if(mouseClicked){	
			mouseClicked = false; 
			return true;
		}
		else 
			return false; 
		
		
	}
}//end class

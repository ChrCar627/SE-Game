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

	
	private static boolean[] keyPressed;
	private static boolean[] keyClicked;
	private static boolean mouseClicked;  
	private static boolean mousePressed;
	
	private static boolean anyKeyPressed; 
	private static boolean anyKeyClicked; 
	
	
	
	
	public Point point; 
	
	
	public Input(Component c) {
		this();
		c.addKeyListener(this);	
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
	}
	public Input() {
		//400 is just a random number. You could replace it with any other big number.  
		keyPressed = new boolean[400];
		keyClicked = new boolean[400];
		
		point = new Point(0, 0); 
		//Initializing 
		for (int i = 0; i < 400; i++) {
			keyPressed[i] = false;
			keyClicked[i] = false;
		}
	
		mouseClicked = false;  
		mousePressed = false; 
	
		anyKeyPressed = false; 
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!keyPressed[e.getKeyCode()])
			keyClicked[e.getKeyCode()] = true;
		if(!anyKeyPressed)
			anyKeyClicked = true;
		
		keyPressed[e.getKeyCode()] = true;
		anyKeyPressed = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed[e.getKeyCode()] = false;
		keyClicked[e.getKeyCode()] = false;
		anyKeyPressed = false; 
		anyKeyClicked = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	//Returns true with is key is down and false with the key is up. 
	//This method is usable for motion in games
	public boolean keyIsDown(int keyCode) {
		return keyPressed[keyCode];
	}
	//Returns true with is key is up and false with the key is down. 
	public boolean keyIsUp(int keyCode) {
		return !keyPressed[keyCode];
	}

	
	//This method returns true only the first time the key is hit.
	//It's useful when you what to play a sound with a key
	public boolean keyIsClicked(int keyCode) {

		if (keyClicked[keyCode]) {
			keyClicked[keyCode] = false;
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
	
	public boolean anyKeyIsPressed(){
		return anyKeyPressed;
	}
		
	public boolean anyKeyIsClicked(){
		return anyKeyClicked;
	}
		
		
	
}//end class

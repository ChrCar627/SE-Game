package fun;
import java.awt.Graphics;

import javax.swing.JFrame;


public class GameMenuPanel extends GamePanel {
	//ignore this
	private static final long serialVersionUID = 1L;
	
	GamePanel[] gp;
	final int DEFAULT =0; 
	private int currentStage; 
	int time; 
	private boolean showTime ;
	private int xTime; 
	private int yTime; 
	
	
	
	public GameMenuPanel(JFrame jk, GamePanel... gamePanel){
		super(jk); 
		gp = gamePanel;
		input.addListenerTo(this);
		currentStage = DEFAULT; 
		init();
	}
	

	@Override
	public void init() {
		time = 0 ;
		showTime = false; 
		xTime =0; 
		yTime =0; 
	}
	
	@Override
	public void draw(Graphics g) {
		gp[currentStage].draw(g);
		
		if(showTime)
			g.drawString(getRealTime(time), xTime, yTime);
	}

	@Override
	public void action() {
		gp[currentStage].action();
		
		if(isStageChanged())
			switchTo(getStage());
		time++; 
		
	}

	public void switchTo(int gamepanel){
		gp[currentStage].stop();
		currentStage = getStage(); 
		gp[currentStage].run();
		
	}


	public void showRealTimeAt(int x, int y) {
		showTime = true;
		xTime = x; 
		yTime = y;
	}

	
	
	
	
	
	
	
}

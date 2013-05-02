import java.awt.Graphics;

import javax.swing.JFrame;

public class GameMenuPanel extends GamePanel {
	//ignore this
	private static final long serialVersionUID = 1L;
	
	GamePanel[] gp;
	final int DEFAULT =0; 
	private int currentStage; 
	
	public GameMenuPanel(JFrame jk, GamePanel... gamePanel){
		super(jk); 
		gp = gamePanel;
		input.addListenerTo(this);
		currentStage = DEFAULT; 
		init();
		
	}
	

	@Override
	public void init() {
	
	}
	
	
	
	
	@Override
	public void draw(Graphics g) {
		gp[currentStage].draw(g);
		//g.drawString("stage "+ currentStage, 0 ,160);
		//g.drawString("Time in gmp "+ this.getRealTime(), 0 ,170);
		
		
	}

	@Override
	public void action() {
		gp[currentStage].action();
		
		if(isStageChanged()){
			switchTo(getStage());
		}
		
	}

	public void switchTo(int gamepanel){
		gp[currentStage].stop();
		currentStage = getStage(); 
		gp[currentStage].run();
		
	}

	
	
	
}

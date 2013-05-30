package fun;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class GamePanel extends JPanel {
	//ignore this
	
	private static final long serialVersionUID = 1L;

	public final int MAIN_MENU = 0; 
	public final int HIGHSCORE = 1; 
	public final int QUIT = 2; 
	//public final int TOTUTIAL = 0; 
	
	
	public static int SECOND = 1000; 
	private static Timer timer; 
	private boolean showFPS; 
	//protected int time; 
	protected static int stage = 0; 
	protected static boolean stageChanged = true; 
	protected static int MAX_WIDTH; 
	protected static int MAX_HEIGHT; 
	protected static JFrame jk; 
	protected static Input input; 
	public Music music; 
	
	
	public GamePanel(JFrame f){
		jk = f; 
		MAX_WIDTH = jk.getWidth() - 15; 
		MAX_HEIGHT = jk.getHeight() - 38;
		input = new Input(jk); 
		input.addListenerTo(this);
		showFPS = false; 
		init();
		 timer = new Timer(SECOND/20, null); 
		 timer.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				action();
				
				repaint(); 
			}
		});
		 
	}
	
	
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		if(showFPS)
			g.drawString(""+ (SECOND/timer.getDelay()), 0, 10);
		
		draw(g);
		
	}
	public abstract void init();
	
	public abstract void draw(Graphics g);
	
	public abstract void action();
	
	
	
	public void run(){
		timer.start();
		init();
		
	}
	public void stop(){
		timer.stop();
	}
		
	public void setFPS(int fps){

		if(fps >1000)
			fps = 1000;
			
		timer.setDelay(SECOND/fps);
		
	}
	
	public int getFPS(){
		return 	timer.getDelay();
	}

	public Timer getTimer(){
		return timer; 
	}

	public  String getRealTime(int time){
		return String.format("%2d:%2d", time/(60*SECOND/timer.getDelay()),((time/(SECOND/timer.getDelay()))%60)).replace(' ', '0');
	}

	public void setStage(int i){
		stage = i ;
		stageChanged = true; 	
	}

	public int getStage() {
		stageChanged = false; 
		return stage; 
	}
	
	public boolean isStageChanged(){
		return stageChanged;
	}
	
	
}//end class
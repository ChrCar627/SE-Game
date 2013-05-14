import javax.swing.JFrame;

import fun.GameMenuPanel;

/*READ ME 
 * Last Time Modified: April 19
 *
 */

public class test extends JFrame{
	//ignore this
	private static final long serialVersionUID = 1L;
 
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test"); 
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		MainGame mg = new MainGame(frame);
		GameInterFace gif = new GameInterFace(frame);
		
		GameMenuPanel gmp = new GameMenuPanel(frame, gif,mg);
		
		frame.add(gmp);
		gmp.run();
		frame.setVisible(true);
		
		
		
		
		
		/*
		final JFrame frame = new JFrame("Dodge it"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(216, 238);
		
		//Level1 lvl1 = new Level1(frame);
		
		final Map map = new Map(frame);  
		frame.setVisible(true);
		frame.add(map);
		frame.setResizable(false);
		map.run();
		
	
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				map.stop();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				map.pause();
				map.run();
			}
		});
		
		frame.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				
				
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {
				map.pause();
				
			}
			
			@Override
			public void componentHidden(ComponentEvent arg0) {
				map.pause();
			}
		});
		
		//frame.setResizable(false);
		//frame.add(lvl1);
		//lvl1.run();
	
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

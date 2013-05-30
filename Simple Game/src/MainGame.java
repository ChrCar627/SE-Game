import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.prefs.BackingStoreException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fun.*;

public class MainGame extends GamePanel {
  
	private static final long serialVersionUID = 1L;

	final Font font = new Font("serial", Font.BOLD + Font.ITALIC, 25);
	
	
	boolean isPaused; 
	
	int maxWidth;
	int maxHeight;
	int upperBound;
	int bottomBound; 
	int leftBound;
	int rightBound;
	
	HighScores highscore;
	
	
	int rockWallTrachers=0;
	
	int waTracker =0;
	int time;
	

	BallPlayer player; 
	
	Rectangle death;
	
	Rectangle rightSide; 
	Rectangle leftSide; 
	Rectangle topSide; 
	Rectangle botSide; 
	
	final PurifiedImage pure = new PurifiedImage();
	
	
	
	final Image heart = pure.TranseptBufferedImage("res/heart.png");
	final Image rockwall = pure.TranseptBufferedImage("res/rockwallwithholes.png");
	final Image wa = pure.TranseptBufferedImage("res/wow.png");
	final Image rock = pure.TranseptBufferedImage("res/rock.png");
	
	
	final Image treasure = pure.TranseptBufferedImage("res/background.jpg");
	final Image layer = pure.TranseptBufferedImage("res/support.png");
	int lol = layer.getHeight(null);
	
	final Image[] im = { 
			pure.TranseptBufferedImage("res/volcano/volcano_1.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_2.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_3.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_4.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_5.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_6.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_7.png"),
			pure.TranseptBufferedImage("res/volcano/volcano_8.png"),
		
	};
	
	final Animation volcano = new Animation(im, 3);
	
	
	
	Animation firewall ;
	
	ObstacleSpawner swamper; 
	
	
	public MainGame(JFrame f) {
		super(f);
		
		isPaused = false; 
		
		maxWidth = f.getWidth();
		maxHeight = f.getHeight();
		death = new Rectangle(50, 100, 10, maxHeight - 200);
		
		upperBound = 100;
		bottomBound = maxHeight -100;
		leftBound = 50; 
		rightBound = maxWidth -50; 
		
		rightSide = new Rectangle(0, 0, maxWidth, upperBound);
		leftSide = new Rectangle(0, bottomBound, maxWidth, upperBound );
		topSide = new Rectangle(0, 0, leftBound, maxHeight);
		botSide = new Rectangle(rightBound, 0, leftBound, maxHeight);
		
		PurifiedImage pure = new PurifiedImage();
		
		Image[] im = { pure.TranseptBufferedImage("res/fireBalls/fire_left/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_left/fire2.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_left/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_left/fire4.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_left/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_left/fire2.png")
				};
		
		firewall = new Animation(im,20);
		
		
		
		
		
		
	/*
		g.fillRect(0, 0, maxWidth, 100);// Upper Frame
		g.fillRect(0, maxHeight - 100, maxWidth, 100);// Bottom Frame

		g.fillRect(0, 0, 50, maxHeight);// Left Frame
		g.fillRect(maxWidth - 50, 0, 50, maxHeight);// Right Frame
		*/
	}

	@Override
	public void init() {
		time = -250;
		
		
		isPaused = false;
		player = new BallPlayer(input, jk);
		
		player.animation.setStop(true);
		player.speed = 3;
		
		player.y  = maxHeight - (lol + player.height);// TODO replace lol with the height of layer 
		player.x = 0-player.width;
		
		swamper = new ObstacleSpawner(rightBound, leftBound, upperBound, bottomBound);
		swamper.createNewWall(100, 50);
	}

	@Override
	public void draw(Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(font);
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		
		g.setColor(Color.BLACK);
		g.fillRect(leftBound, upperBound, rightBound,bottomBound);
		
		//back ground; 
			g.drawImage(volcano.getAnimationImage(), leftBound, upperBound*2-45 , null);
			
			g.drawImage(volcano.getAnimationImage(), leftBound + volcano.getImageAt(0).getWidth(null) -2, upperBound*2-45, null);
			
			
			int waWidth = wa.getWidth(null);
			int waHieght = upperBound - 190;
			g.drawImage(wa, leftBound - waTracker ,  waHieght, null);
			g.drawImage(wa, leftBound - waTracker + waWidth ,  waHieght, null);
			//g.drawImage(wa, leftBound - waTracker + waWidth*2 ,  waHieght, null);
			
			
			int rockWidth = rock.getWidth(null) ; 
			int rockHieght =  maxHeight - (rock.getHeight(null)+ upperBound/2 ); 
			
			g.drawImage(rock, leftBound - rockWallTrachers ,  rockHieght , null);
			g.drawImage(rock, leftBound - rockWallTrachers + rockWidth,  rockHieght , null);
			g.drawImage(rock, leftBound - rockWallTrachers + rockWidth*2,  rockHieght , null);
			g.drawImage(rock, leftBound - rockWallTrachers + rockWidth*3,  rockHieght , null);
			
			
		// draw player 
		//g2d.drawImage(player.getAnimationImage(),player.x,player.y, null);
		player.drawMe(g);
		//g2d.fill(new Ellipse2D.Double(player.x, player.y, player.width,
				//player.height));
		g.setColor(Color.RED);
		//draw obstacles 
		swamper.draw(g);
		
		// Drawing death
		g2d.setColor(Color.RED);
		g2d.fill(death);
		
		for(int i=0; i<16; i++)
			g.drawImage(firewall.getAnimationImage(), 50, (i*20)+90 , null);

		if(player.immuneCD -10 > 0){
			showFireBalls(g);
		}
		// Drawing frames
		g.setColor(Color.BLACK);

		//g.fillRect(0, 0, maxWidth, 100);// Upper Frame
		//g.fillRect(0, maxHeight - 100, maxWidth, 100);// Bottom Frame

		//g.fillRect(0, 0, 50, maxHeight);// Left Frame
		//g.fillRect(maxWidth - 50, 0, 50, maxHeight);// Right Frame
		
		
		g2d.fill(rightSide);
		g2d.fill(leftSide);
		g2d.fill(topSide);
		g2d.fill(botSide);
		
		// drawing time and score and live
		g2d.setColor(Color.WHITE.darker());
		
	 	if(time<-100){
			
			g.drawImage(treasure, 0, 0, null);
			
			player.drawMe(g);
			
			g.drawImage(layer, 0, maxHeight-layer.getHeight(null), null);
			

			g.setColor(Color.pink);
			g.fillRect(100, 30, 300, 200);
			g.setColor(Color.red.darker());
			g2d.setStroke(new BasicStroke(7f));
			g.drawRect(100, 30, 300, 200);
			g.setColor(Color.black);
			g.setFont(new Font("something", Font.BOLD, 18));
			g.drawString("Controls:", 200, 50);
			g.setFont(new Font("something", Font.PLAIN, 14));
			g.drawString("Spacebar - Jump", 110, 70);
			g.drawString("Shift - Float", 110, 90);
			g.drawString("Left - Move Left", 110, 110);
			
			g.setFont(new Font("something", Font.BOLD, 18));
			g.drawString("How to Play:", 190, 140);
			g.setFont(new Font("something", Font.PLAIN, 14));
			g.drawString("Dodge fireballs", 110, 70+90);
			g.drawString("Jump over obstacles", 110, 90+90);
			g.drawString("Collect coins", 110, 110+90);
			
			g.setFont(new Font("something", Font.BOLD, 16));
			g.drawString("Press Spacebar to skip", 110, 130+90);
			
			
		//ANIMATION HERE
			
		}//end time -100
		else if (time<0){
			g.setColor(Color.WHITE);
			
			if(time > -40){
			if(time <-30)
				g.drawString("3..", maxWidth/2, maxHeight/2);
			else if(time <-20)
				g.drawString("2..", maxWidth/2, maxHeight/2);
			else if(time <-10)
				g.drawString("1", maxWidth/2, maxHeight/2);
			
			}//end big if
			
		}
		else if (time >= 0){
			
			if(time <10){
				g.setColor(Color.RED);
				g.drawString("GO!!", maxWidth/2, maxHeight/2);
			}
			
		g2d.drawString(getRealTime(time), (maxWidth / 2)
				- (getRealTime(time).length()*7), 20);
		g2d.drawString(String.format("Score_:_%4d", player.score).replaceAll(" ", "0").replaceAll("_", " "),  (maxWidth / 2)
				- ("Score : " .length()*10), 45) ;
		
		//DRAW HEALTH BAR
		g2d.drawString("Health: ", 80 ,75);  
	
		for(int i=0;i!=player.hp;i++) 
				g2d.drawImage(heart, 140+ heart.getWidth(null)+("Health".length()*3)+(i*heart.getWidth(null)) ,80-heart.getHeight(null),null);
		
		g.setColor(Color.YELLOW);
		g2d.drawString("Energy: ", 70 ,100);  
		g.fillRect(160, 80, player.energy*5, 20);
		}
		//Guides X & Y 
		//g2d.drawString(String.format("x: %d, y: %d", input.point.x, input.point.y), 100, 100);
		//g2d.drawString("secondJump "+player.secondJump, 100, 120);
		g.setColor(Color.RED.darker().darker());
		if(isPaused){
			//TODO
			screenMenu(g, "GAME IS PAUSED", "P - resume    R to restart    Q - quit");
			
		}
		else if(!player.isAlive){
			
			screenMenu(g, "     YOU LOST", "R - restart    Q - quit    S - show high scores");
			
		}else if(time>0 && !player.isPushed){ 
			waTracker+= player.speed/2;
			if(waTracker >= waWidth)
				waTracker=0;
			
			rockWallTrachers+=player.speed -1;
			if(rockWallTrachers >= rockWidth)
				rockWallTrachers=0;
		}
			
	}//end draw

	@Override
	public void action() {
		if(time <0){
			// ANIMATION BEFORE GAME STARTS!!! 
			
			if(player.x < 205){
				player.x+=2;
				player.animation.nextFrame();
			}
			else if(player.x < 216){
				player.x+=2;
				player.animation.nextFrame();
				player.y++;
			}
			else 
				player.y+=3;
			
			if(player.y >= maxHeight){
				player.y = 0; 
			}
			
			if(input.anyKeyIsClicked() && time < -150)
			{
				player.x = 218; 
				player.y = maxHeight-1;
				time = -100;
				volcano.setStop(false);
			}
			
			time++;
			if(time==0)
				player.animation.setStop(false);
		}
		else
		if (player.isAlive && !isPaused) {
			player.speed = 2 + player.score/100;
			
			player.action();
			swamper.lunch();
		
			swamper.action(player, time);
			
			time++;
			//DEATH
			if(player.x < 60){
				player.x = 65;
				player.hp--;
				player.setImmune(50);
				player.isSheilded = false;
			}
			
			if(player.hp <=0){
				player.hp =0;
				player.isAlive = false; 
				if(highscore.CheckScore(player.score)==true){
					String s =  JOptionPane.showInputDialog ( "New high score! Enter your name: " );
					highscore.AddScore(s, player.score);
					highscore.SaveFile();
				}
			}	
			if(input.keyIsClicked(KeyEvent.VK_P)){
				isPaused = true;
				volcano.setStop(true);
				player.animation.setStop(true);
			}
		}// end if isAlive
		else if(isPaused){
			if(input.keyIsClicked(KeyEvent.VK_P)){
				isPaused = false;
				volcano.setStop(false);
				player.animation.setStop(false);
			}
			else if (input.keyIsClicked(KeyEvent.VK_R))
				init();
			else if(input.keyIsClicked(KeyEvent.VK_Q))
				setStage(0);
		}
		else {
			
			
			if(input.keyIsClicked(KeyEvent.VK_R))
				init();
			else if(input.keyIsClicked(KeyEvent.VK_Q))
				setStage(0);
			else if(input.keyIsClicked(KeyEvent.VK_S))
				setStage(2);
		}//player is dead

	}//end action
	
	private void showFireBalls(Graphics g){
		Animation fireBall = FireBallObstacle.fireBallUpAnimation;
		Random ran = new Random();
		fireBall.setFrame(ran.nextInt(4));
		
		for(int i=0; i< 91; i++){
g.drawImage(fireBall.getAnimationImage(), leftBound + ((i%13)*30), upperBound - 10 + ((i/13)*40), null);
			
		}
		
	}
	
	void screenMenu(Graphics g, String title, String text){
		Graphics2D g2d = (Graphics2D) g; 
		g2d.fillRect(leftBound*3, upperBound*2, 250, 100);
		
		for(int i=0;i <10; i++){
			g.drawImage(FireBallObstacle.fireBallUpAnimation.getAnimationImage(), leftBound*3 + i*25  - 4, upperBound*2 - 40, null);
			g.drawImage(FireBallObstacle.fireBallDownAnimation.getAnimationImage(), leftBound*3 + i*25   - 2, upperBound*2 + 80, null);
			if(i%2==0){
				g.drawImage(FireBallObstacle.fireBallLeftAnimation.getAnimationImage(), leftBound*8 - 10 , upperBound*2 + (i*10), null);
				g.drawImage(FireBallObstacle.fireBallRightAnimation.getAnimationImage(), leftBound*2 + 5 , upperBound*2 + (i*10), null);
			}//end if
		}//end for
		g.setColor(Color.BLACK);
		g.drawString(title, maxWidth/3, maxHeight/2);
		
		g.setFont(new Font("something", Font.PLAIN, 10));
		
		
		g.drawString(text, maxWidth/3 + 2 ,  maxHeight/2 + 20);
		
		g.setFont(font);
		
	}
	
	
	
}//end class

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;

import fun.*;

public class MainGame extends GamePanel {
  
	private static final long serialVersionUID = 1L;

	final Font font = new Font("serial", Font.BOLD + Font.ITALIC, 25);

	boolean isOn; 
	boolean isPaused; 
	
	int MAX_WIDTH;
	int MAX_HEIGHT;
	int upperBound;
	int bottomBound; 
	int leftBound;
	int rightBound;
	

	int immuneCD; 
	
	
	int score;
	int speed;
	int time;
	

	BallPlayer player; 
	
	Rectangle death;
	
	Rectangle rightSide; 
	Rectangle leftSide; 
	Rectangle topSide; 
	Rectangle botSide; 
	
	PurifiedImage pure = new PurifiedImage();
	
	Animation firewall ;
	
	ObstacleSpawner swamper; 
	
	
	Item healthPot; 
	Item slow; 
	
	public MainGame(JFrame f) {
		super(f);
		
		isOn = true; 
		isPaused = false; 
		
		MAX_WIDTH = f.getWidth();
		MAX_HEIGHT = f.getHeight();
		death = new Rectangle(50, 100, 10, MAX_HEIGHT - 200);
		
		upperBound = 100;
		bottomBound = MAX_HEIGHT -100;
		leftBound = 50; 
		rightBound = MAX_WIDTH -50; 
		
		rightSide = new Rectangle(0, 0, MAX_WIDTH, upperBound);
		leftSide = new Rectangle(0, bottomBound, MAX_WIDTH, upperBound );
		topSide = new Rectangle(0, 0, leftBound, MAX_HEIGHT);
		botSide = new Rectangle(rightBound, 0, leftBound, MAX_HEIGHT);
		
		PurifiedImage pure = new PurifiedImage();
		
		Image[] im = { pure.TranseptBufferedImage("res/fireBalls/fire_left/fire1.png"), pure.TranseptBufferedImage("res/fireBalls/fire_left/fire2.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_left/fire3.png"), pure.TranseptBufferedImage("res/fireBalls/fire_left/fire4.png"),
				pure.TranseptBufferedImage("res/fireBalls/fire_left/fire3.png"),pure.TranseptBufferedImage("res/fireBalls/fire_left/fire2.png")
				};
		
		firewall = new Animation(im,20);
		swamper = new ObstacleSpawner(rightBound, leftBound, upperBound, bottomBound);
		
		swamper.createNewWall(100, 50);
		
		healthPot = new Item() {
			
			@Override
			public void init() {
				//animation = Animation();
				
				//icomImage =
			}
			
			@Override
			public void effect(Player p) {
				
			}
			
			@Override
			public void consume(Player p) {
				p.hp++;
			}
		};
		
		
		
	/*
		g.fillRect(0, 0, MAX_WIDTH, 100);// Upper Frame
		g.fillRect(0, MAX_HEIGHT - 100, MAX_WIDTH, 100);// Bottom Frame

		g.fillRect(0, 0, 50, MAX_HEIGHT);// Left Frame
		g.fillRect(MAX_WIDTH - 50, 0, 50, MAX_HEIGHT);// Right Frame
		*/
	}

	@Override
	public void init() {
		speed = 1;
		time = 0;
		score = 0;
	
		player = new BallPlayer(input, jk);
		
		
	}

	@Override
	public void draw(Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(font);
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		
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

		if(player.isImmune){
			showFireBalls(g);
		}
		// Drawing frames
		g.setColor(Color.BLACK);

		//g.fillRect(0, 0, MAX_WIDTH, 100);// Upper Frame
		//g.fillRect(0, MAX_HEIGHT - 100, MAX_WIDTH, 100);// Bottom Frame

		//g.fillRect(0, 0, 50, MAX_HEIGHT);// Left Frame
		//g.fillRect(MAX_WIDTH - 50, 0, 50, MAX_HEIGHT);// Right Frame
		
		
		g2d.fill(rightSide);
		g2d.fill(leftSide);
		g2d.fill(topSide);
		g2d.fill(botSide);
		
		// drawing time and score and live
		g2d.setColor(Color.WHITE.darker());

		g2d.drawString(getRealTime(time), (MAX_WIDTH / 2)
				- (getRealTime(time).length()*7), 20);
		g2d.drawString(String.format("Score_:_%4d", score).replaceAll(" ", "0").replaceAll("_", " "),  (MAX_WIDTH / 2)
				- ("Score : " .length()*10), 45) ;
		
		//DRAW HEALTH BAR
		g2d.drawString("Health: ", 80 ,70);  
	
		for(int i=0;i!=player.hp;i++) 
				g2d.drawString("<3", 170+(i*32) ,70);
		//Guides X & Y TODO
		//g2d.drawString(String.format("x: %d, y: %d", input.point.x, input.point.y), 100, 100);
		//g2d.drawString("secondJump "+player.secondJump, 100, 120);
		g.setColor(Color.black);
		if(!player.isAlive){
			g.drawString("You lost", MAX_WIDTH/2 - 80, MAX_HEIGHT/2);
			
		}
			
	}//end draw

	@Override
	public void action() {
		if (player.isAlive) {
			player.speed = speed*3;
			player.action();
		
			
			swamper.lunch();
			
			swamper.action(player, time);
			
			time++;
		
			//DEATH
			if(player.x < 60){
				player.x = 65;
				player.hp--;
				player.setImmune(50);
				
			}
			
			if(player.hp <=0){
				player.hp =0;
				player.isAlive = false; 
			}	
			
		}// end if isAlive
		else{
			if(input.keyIsClicked(KeyEvent.VK_R))
				init();
			
		}//player is dead

	}//end action
	
	private void showFireBalls(Graphics g){
		Animation fireBall = FireBallObstacle.fireBallUpAnimation;
		Random ran = new Random();
		fireBall.setFrame(ran.nextInt(4));
		
		for(int i=0; i< 96; i++){
g.drawImage(fireBall.getAnimationImage(), leftBound + ((i%13)*30), upperBound - 10 + ((i/13)*40), null);
			
		}
		
	}
	
}//end class

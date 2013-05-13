import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import fun.Animation;
import fun.Input;
import fun.Player;


public class BallPlayer extends Player {
	
	int MAX_WIDTH;
	int MAX_HEIGHT;
	int upperBound;
	int bottomBound; 
	int leftBound;
	int rightBound;
	
	Animation immuneAni; 
	Animation bubbleAni; 
	
	
	public BallPlayer(Input in, JFrame frame) {
		super(in);
		
		 MAX_WIDTH = frame.getWidth();
		 MAX_HEIGHT = frame.getHeight();
		 
		 upperBound = MAX_HEIGHT/5;
		 bottomBound = MAX_HEIGHT - upperBound ;  // 4/5
		 leftBound = MAX_WIDTH/5;
		 rightBound =  MAX_WIDTH - leftBound;// 4/5
		 
		 resetPoint = MAX_WIDTH/2;
	}

	@Override
	public void init(){
		x = 100;
		y = 300;
		width = 30;
		height = 30;
		isFalling = true;
		hp = 3;
		PurifiedImage pure = new PurifiedImage();
		Image[] im = {pure.TranseptBufferedImage("res/ball/normal/ball1.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball2.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball3.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball4.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball5.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball6.png"),
				pure.TranseptBufferedImage("res/ball/normal/ball7.png")
		};
		animation  = new Animation(im,1);
		
		Image[] im2 = {pure.TranseptBufferedImage("res/ball/bubble/ball1.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball2.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball3.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball4.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball5.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball6.png"),
				pure.TranseptBufferedImage("res/ball/bubble/ball7.png")
		};
		
		
		bubbleAni = new Animation(im2, 1);
		
		Image[] im3 = {pure.TranseptBufferedImage("res/ball/immune/ball_1.png"),
				pure.TranseptBufferedImage("res/ball/immune/ball_2.png"),
				pure.TranseptBufferedImage("res/ball/immune/ball_3.png"),
				
		};
		
		immuneAni = new Animation(im3,2);
		
		width = im[0].getWidth(null)-2;
		height = im[0].getHeight(null)-2;
		
		isPushed = false;
		
		
	}
	
	
	@Override
	public Image getAnimation(){
		if(isSheilded)
			return bubbleAni.getAnimation();
		else 
			return animation.getAnimation();
	}
	
	public void drawMe(Graphics g){
		g.drawImage(getAnimation(), x, y, null);
		if(isImmune)
			g.drawImage(immuneAni.getAnimation(), x, y, null);
		
	}
	
	@Override
	public void action() {
		
		applyImmuneCD();
		
		if(! input.keyIsDown(KeyEvent.VK_SHIFT)){
			if(secondJump && input.keyIsClicked(KeyEvent.VK_SPACE)){
				isFalling = false;
				isJumping = true;
			}
		
			applyJumpingAndFalling(KeyEvent.VK_SPACE, speed*2, upperBound + 150 - jumpingLimit, bottomBound - height  );
		}
		
		
		
	//	applyMovingRight(KeyEvent.VK_RIGHT, speed, 50 + 200);
	//	applyMovingLeft(KeyEvent.VK_LEFT, speed, 50 + 20); 
		if(isPushed){
			applyGravityLeft(speed, 50);
			animation.previousFrame();
		}
		else {
			applyGravityRight(speed, (MAX_WIDTH)/2);
			if(x < MAX_WIDTH/2)
				animation.nextFrame();
		}
		  
		if(!isFalling && !isJumping)
			jumpingLimit = 0;
		
		
		isPushed = (input.keyIsDown(KeyEvent.VK_A));
			
		
	}

}

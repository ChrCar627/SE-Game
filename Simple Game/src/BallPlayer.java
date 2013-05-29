import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import fun.Animation;
import fun.Input;
import fun.Player;


public class BallPlayer extends Player {
	
	final int MAX_ENERGY = 50; 
	
	int MAX_WIDTH;
	int MAX_HEIGHT;
	int upperBound;
	int bottomBound; 
	int leftBound;
	int rightBound;
	
	Animation immuneAni; 
	Animation bubbleAni; //shield 
	
	
	public BallPlayer(Input in, JFrame frame) {
		super(in);
		
		 MAX_WIDTH = frame.getWidth();
		 MAX_HEIGHT = frame.getHeight();
		 
		 upperBound = MAX_HEIGHT/5;
		 bottomBound = MAX_HEIGHT - upperBound ;  // 4/5
		 leftBound = MAX_WIDTH/5;
		 rightBound =  MAX_WIDTH - leftBound;// 4/5
		 
		 resetPoint = MAX_WIDTH/2;
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
	}

	@Override
	public void init(){
		x = 100;
		y = 300;
		width = 30;
		height = 30;
		isFalling = true;
		hp = 3;
		
		energy = MAX_ENERGY; 
		
		slowEffect = 0; 		
		
		isPushed = false;
	}
	
	
	@Override
	public Image getAnimationImage(){
		if(isSheilded)
			return bubbleAni.getAnimationImage();
		else 
			return animation.getAnimationImage();
	}
	
	public void drawMe(Graphics g){
		g.drawImage(getAnimationImage(), x, y, null);
		if(isImmune)
			g.drawImage(immuneAni.getAnimationImage(), x, y, null);
		
		if(coinsCD > 0){
			//int coinYpos = (coinsCD>10)? coinsCD*2 : 10;
			g.drawImage(CoinObstacle.coinAnimation.getAnimationImage() , x+(width/2) - 6, (y+height/2) - (70 -coinsCD*2) , null);
		}
		
	}
	
	@Override
	public void action() {
		
		applyImmuneCD();
		applyCoinCD();
		
		
		if( input.keyIsUp(KeyEvent.VK_SHIFT)){
			
			if(secondJump && input.keyIsClicked(KeyEvent.VK_SPACE)){
				isFalling = false;
				isJumping = true;
			}
		
			applyJumpingAndFalling(KeyEvent.VK_SPACE, 4-slowEffect, upperBound + 150 - jumpingLimit, bottomBound - height  );
		}else if (energy <=0){
			
			isFalling = true; 
			applyJumpingAndFalling(KeyEvent.VK_SPACE, 4-slowEffect, upperBound + 150 - jumpingLimit, bottomBound - height);
		}else{
			energy--; 
		}
			
		if(y+height == bottomBound){
			if(energy+1 <= MAX_ENERGY)
				energy++;
		}
		
		
	//	applyMovingRight(KeyEvent.VK_RIGHT, speed, 50 + 200);
	//	applyMovingLeft(KeyEvent.VK_LEFT, speed, 50 + 20); 
		if(isPushed){
			applyGravityLeft(speed, 50);
			animation.previousFrame();
		}
		else {
			applyGravityRight(2, (MAX_WIDTH) - (2*leftBound));
			if(x < MAX_WIDTH/2)
				animation.nextFrame();
		}
		  
		if(!isFalling && !isJumping)
			jumpingLimit = 0;
		
		
		isPushed = (input.keyIsDown(KeyEvent.VK_A));
			
		
	}

}

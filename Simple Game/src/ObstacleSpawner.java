

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.util.Random;
 

import fun.Obstacle;
import fun.Player;

public class ObstacleSpawner {

	Obstacle wall; 
	
	FireBallObstacle[] fireBalls; 
	
	CoinObstacle[] coins; 
	
	BubbleObstacle bubberducky; //BABABUBBERDUCKYBUBBERDUCKYBUBBERDUCK
	
	Random ran = new Random(); 
	
	PurifiedImage pure = new PurifiedImage();
	Image brick = pure.TranseptBufferedImage("res/brick.png");
			
	
	boolean isActive; 
	
	int leftBound; 
	int rightBound; 
	int upperBound; 
	int botBound; 
	
	int nextBottle = 1; 
	
	
	
	public ObstacleSpawner(int right, int left, int up, int bottum){
		
		isActive = false; 
		
		 leftBound  = left; 
		 rightBound = right ; 
		 upperBound  = up ; 
		 botBound   = bottum; //screw spelling
		
		 wall = new Obstacle(50, 20);
		 
		fireBalls = new FireBallObstacle[5];
		
		for(int i=0; i<fireBalls.length;i++)
			fireBalls[i]= new FireBallObstacle(0); 
		
		coins = new CoinObstacle[5];
		
		for(int i=0; i<coins.length;i++){
			coins[i]= new CoinObstacle(); 
			coins[i].x = rightBound+ (i*50);
			coins[i].y = 3*upperBound;
		}
		
		bubberducky = new BubbleObstacle();
		
		
	}
	
	
	public void createNewWall(int w, int h){
		wall = new Obstacle(w, h);
		wall.x = rightBound; 
		wall.y = botBound - h;
	}
	
	


	public void draw(Graphics g) {
		g.setColor(Color.green);
		wall.drawMe(g);
		
		for(int i=0; i < 1+wall.height/6; i++){
			for(int j=0; j < 1+ i%2 +  wall.width/10; j++){
				g.drawImage(brick, wall.x+ (j*10) - ((i%2) * 5), wall.y+ (i*6), null);
				
			}
			
			
		}
		
		Graphics2D g2d = (Graphics2D) g; 
		
		
		for(int i=0; i<coins.length;i++){
			//g2d.draw(coins[i].getShape());
			g.drawImage(coins[i].animation.getAnimationImage(), coins[i].x, coins[i].y, null);
		}
		
		for(int i=0; i<fireBalls.length;i++){
			g.drawImage(fireBalls[i].animation.getAnimationImage(), fireBalls[i].x+ 40, fireBalls[i].y+2, null);
			//g2d.draw(fireBalls[i].getShape());
		}
		// for(int i=0; i<spike.length;i++)
		//	 spike[i].drawMe(g);
		 
		bubberducky.drawMe(g);
		
		
	}

	public void action(Player p, int time) {
		int speed = p.speed;
		int level = 2+(time/200) - (( 3- p.hp)*2); 
		if(isActive){
			//time++; 
			
			
			//WALL 
			if(p.isImmune)
				wall.x = -wall.width; 
			else
			moveWallBack(speed);
			//WALL : allow second jump
			if(p.y+ p.height >= wall.y && p.x+(3*p.width/4) > wall.x && p.x - p.speed < wall.x+wall.width && !p.isImmune ){
				p.y = wall.y - p.height;
				p.jumpingLimit = wall.height;
				p.secondJump  = true; 
				
			}	
			else 
				p.secondJump  = false; 
			//WALL : push player
			if(p.getEllipse2DDouble().intersects(wall.getShape()) && !p.isImmune)
				p.isPushed = true;
			else 
				p.isPushed = false;
			
			//FIREBALLS
			
			// throw
			
			for(int i=0; i< ((level > 5)? 5: level); i++)	
				throwFireBall(i, p.speed + ran.nextInt(level));
			
			
			//MOVE THE FIREBALLS  (SPEED 0 WILL NOT MOVE)
			if(!p.isImmune)
				for(int i=0; i< fireBalls.length;i++){
					fireBalls[i].x += fireBalls[i].speed;
					
					if(fireBalls[i].x +fireBalls[i].width >= rightBound){
						fireBalls[i].reset(leftBound - (ran.nextInt(100) + 50 ));
						fireBalls[i].y = upperBound + (((botBound-upperBound)/6)*ran.nextInt(6));
					}
				}
			
			
				for(int i=0;i<coins.length; i++)
					if(coins[i].x == 0){
						coins[i].x = rightBound + 10 + (i*20) + ran.nextInt(20);
						coins[i].y = 3*upperBound   - coins[0].height + ran.nextInt(((botBound-upperBound)/3))  ;//(ran.nextInt((botBound-upperBound)/4));
					}
			
			for(int i=0; i<coins.length;i++){
				if(coins[i].x != 0)
					coins[i].x--;
			}
				
			
			//GET COIN WHEN TOUCH
			for(int i=0; i<coins.length;i++){
			  if(p.getEllipse2DDouble().intersects(coins[i].getShape())){
				  coins[i].coinSound.setFramePosition(0);
				  coins[i].coinSound.start();
				  p.addCoins(1);
				  p.score+= p.speed*level; 
				  coins[i].x=0;
				  
			  }	  
			}
			
			
			
			//BURN IF TOUCH
			if(!p.isImmune){
			for(int i=0; i<fireBalls.length;i++)
				if(p.getEllipse2DDouble().intersects(fireBalls[i].getShape())){
					fireBalls[i].fireSound.setFramePosition(0);
					fireBalls[i].fireSound.start();
					playerRecieveDamage(p);
					
				}//end if
		}//end for
		}//end if isImmune
		
		//THROWING BUBBLES
		if(time > 20 && bubberducky.y >= botBound){
			nextBottle++; 
			bubberducky.x = leftBound*2 + ran.nextInt(leftBound*2); 
			bubberducky.y = 0;
		}
		//Falling 
		if(bubberducky.y <= botBound)
			bubberducky.y+=p.speed;
		//get bubbled
		if(p.getEllipse2DDouble().intersects(bubberducky.getShape())){
			p.isSheilded = true; 
			bubberducky.y = botBound;
		}
			
	}

	private void moveWallBack(int delta){
		wall.x -= delta; 
		Random ran = new Random(); 
		if(wall.x +wall.width <= (leftBound-20)) {
			createNewWall(ran.nextInt(100)+10,ran.nextInt(40)+25);
		}
	}
	
	private void throwFireBall(int i, int delta){  // TODO
		if(fireBalls[i].speed==0){
			fireBalls[i] = new FireBallObstacle(delta);
		}
	}
	
	public void playerRecieveDamage(Player p){
		for(int j=0; j<fireBalls.length;j++)
			fireBalls[j].x = rightBound-50 ;
		
		
		if(p.isSheilded)
			p.isSheilded = false;
		else {
			
			p.hp--;
			p.setImmune(25);
		}//end else
			
	}//end playerReciveS=Damgage 
	
	public void lunch(){
		isActive = true;
	}
	
	
	
	
}



import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;
 

import fun.Obstacle;
import fun.Player;

public class ObstacleSpawner {

	Obstacle wall; 
	
	Obstacle[] fireBalls; 
	
	Random ran = new Random(); 
	
	boolean isActive; 
	
	int leftBound; 
	int rightBound; 
	int upperBound; 
	int botBound; 
	
	
	
	
	
	public ObstacleSpawner(int right, int left, int up, int bottum){
		
		
		 leftBound  = left; 
		 rightBound = right ; 
		 upperBound  = up ; 
		 botBound   = bottum; //screw spelling
		
		fireBalls = new Obstacle[20];
		
		for(int i=0; i<fireBalls.length;i++)
			fireBalls[i]= new FireBallObstacle(); 
		
	}
	
	
	public void createNewWall(int w, int h){
		wall = new Obstacle(w, h);
		wall.x = rightBound; 
		wall.y = botBound - h;
	}
	
	
	public void init() {
		
		isActive = false; 
		
		wall = new Obstacle(50, 20);
	}

	public void draw(Graphics g) {
		g.setColor(Color.green);
		wall.drawMe(g);
		
		//Graphics2D g2d = (Graphics2D) g; 
		
		for(int i=0; i<fireBalls.length;i++){
			g.drawImage(fireBalls[i].animation.getAnimationImage(), fireBalls[i].x+ 40, fireBalls[i].y+2, null);
			//g2d.draw(fireBalls[i].getShape());
		}
		// for(int i=0; i<spike.length;i++)
		//	 spike[i].drawMe(g);
		 
	}

	public void action(Player p, int time) {
		int speed = p.speed;
		int level = 2+time/200; 
		if(isActive){
			time++; 
			
			
			//WALL 
			if(p.isImmune)
				wall.x = -wall.width; 
			else
			moveWallBack(speed);
			//WALL : allow second jump
			if(p.y+ p.height >= wall.y && p.x+(3*p.width/4) > wall.x && p.x + p.speed*2 < wall.x+wall.width && !p.isImmune ){
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
			if(!p.isImmune)
			for(int i=0; i< ((level > 20)? 20: level); i++)	
				throwFireBall(i, speed+ (ran.nextInt(5)));
			
			
			
			//BURN IF TOUCH
			if(!p.isImmune){
			for(int i=0; i<fireBalls.length;i++)
				if(p.getEllipse2DDouble().intersects(fireBalls[i].getShape())){
					playerRecieveDamage(p);
					
				}//end if
					
		}//end for
		}//end if isImmune
	}

	private void moveWallBack(int delta){
		wall.x -= delta; 
		Random ran = new Random(); 
		if(wall.x +wall.width <= (leftBound-20)) {
			//wall = new Obstacle(ran.nextInt(30)+20,ran.nextInt(20)+10);
			//wall.width = 100;
			createNewWall(ran.nextInt(100)+10,ran.nextInt(40)+25);
		}
	}
	
	private void throwFireBall(int i, int delta){
		fireBalls[i].x += delta; 
		if(fireBalls[i].x +fireBalls[i].width >= rightBound){
			fireBalls[i].reset(leftBound - ran.nextInt(100));
			fireBalls[i].y = upperBound + (((botBound-upperBound)/8)*ran.nextInt(8));
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

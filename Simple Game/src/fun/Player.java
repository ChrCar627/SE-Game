package fun;

import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public abstract class Player {

	public static Player empty = new Player(null) {
		public void action() {}
	};

	
	
	public boolean isJumping; 
	public boolean isFalling;
	public boolean isAlive; 
	public boolean isPushed; 
	public boolean isImmune; 
	public boolean isSheilded;
	public boolean secondJump;
	
	//public Shape shape;
	
	public int x; 
	public int y; 
	public int hp; 
	public int speed; 
	public int width; 
	public int height; 
	public int jumpingLimit;
	public int resetPoint; 
	
	public int immuneCD; 
	protected int coinsCD=0; 
	
	public int coins;
	public int score; 
	
	public Item item; 
	
	public Image iconImage; 
	public Image image; 
	
	public Animation animation; 
	
	public String name; 
	
	protected Input input; 
	
	public Player(Input in){
		input = in; 
		
		isJumping = false; 
		isFalling = false;
		isAlive = true; 
		isImmune = false;
		secondJump = false;
		
		jumpingLimit  = 0; 
		init(); 
	}//end constructor 
	
	public void init() {
	
	}
	
	public abstract void action(); 
	
	// ### SET ###
	
	
	
	// ##### GET.TERS #####
	
	public Image getAnimationImage(){

		return animation.getAnimationImage();
	}
	
	public Ellipse2D.Double getEllipse2DDouble(){
		return new Ellipse2D.Double(x, y, width, height);
	}
	
	public int getScore(){
		return score; 
	}
	
	public int getCoin(){
		return coins; 
	}
	
	// ###### SET TERS ##### 
		public void setImmune(int CD) {
			isImmune = true; 
			immuneCD = CD; 
			
		}
		
		public void setCoins(int n){
			coins = n; 
		}
		public void setScore(int n){
		score = n;
		}
		
	
	// ##### applying action ######
	
	public void applyMovingLeft(int keyCode, int delta, int limit){
		if(input.keyIsDown(keyCode) && x > limit){
			if(x - delta > limit)
				x -= delta;
			else 
				x = limit;		
		}
	}
	public void applyMovingLeft(int keyCode, int delta){
		applyMovingLeft(keyCode, delta ,Integer.MIN_VALUE);
	}
	public void applyMovingLeft(int keyCode){
		applyMovingLeft(keyCode, 1);
	}
	
 	public void applyMovingRight(int keyCode, int delta, int limit){
		
 		if(input.keyIsDown(keyCode) && x < limit){
			if(x + delta < limit)
				x += delta;
			else 
				x = limit;	
		}
	}
	public void applyMovingRight(int keyCode, int delta){
		applyMovingRight(keyCode, delta,  Integer.MAX_VALUE);
	}
	
	public void applyMovingUp(int keyCode, int delta, int limit){
		if(input.keyIsDown(keyCode) && y > limit){
			if(y - delta < limit)
				y -= delta; 
			else
				y = limit; 
		}
	}
	public void applyMovingUp(int keyCode, int delta){
		applyMovingUp(keyCode, delta, Integer.MIN_VALUE);
	}
	
	public void applyMovingDown(int keyCode, int delta, int limit){
		if(input.keyIsDown(keyCode) && y < limit){
			if(y + delta > limit)
				y += delta; 
			else
				y = limit;  
		}
	}
	public void applyMovingDown(int keyCode, int delta){
		applyMovingDown(keyCode, delta, Integer.MAX_VALUE);
	}
	
	public void applyJumpingAndFalling(int keyCode,int delta, int max, int min){
       if(input.keyIsClicked(keyCode) && !isJumping && !isFalling)
			isJumping = true;
		
		if(isJumping){
			if( y - delta > max){
				y -= delta;
				if(!input.keyIsDown(KeyEvent.VK_SPACE)){
					isJumping = false;
					isFalling = true;
				}
			}
			else {
				y = max;
				isJumping = false; 
				isFalling = true; 
			}
		}//end jump
		
		if(isFalling){
			if(y+delta < min )
				y += delta;
			else{
				y = min;
				isFalling = false; 
			}
		}//end falling

	}

	public void applyGravityRight(int delta, int limit){
		if(x < limit){
			if(x+delta < limit)
				x+=delta;
			else
				x = limit;
				
		}
	}
	public void applyGravityRight(int delta){
		applyGravityRight(delta, Integer.MAX_VALUE);
	}
	public void applyGravityLeft(int delta, int limit){
		if(x > limit){
			if(x-delta > limit)
				x-=delta;
			else
				x = limit;	
		}
	}
	public void applyGravityLeft(int delta){
		applyGravityLeft(delta, Integer.MIN_VALUE);
	}
	
	public void aplyGravityUp(int delta, int limit){
		if(y < limit){
			if(y+delta < limit)
				y+=delta;
			else
				y = limit;
		}
	}
	public void aplyGravityUp(int delta){
		aplyGravityUp( delta,  Integer.MAX_VALUE);
	}
	public void aplyGravityDown(int delta, int limit){
		if(y > limit){
			if(y-delta > limit)
				y-=delta;
			else
				y = limit;	
		}
	}
	public void aplyGravityDown(int delta){
		aplyGravityDown(delta, Integer.MIN_VALUE);
	}
	
	public void applyJumpAndMove(int keyCode, int deltaJump, int deltaMove, int max, int min){
		if(input.keyIsClicked(keyCode) && !isJumping && !isFalling)
			isJumping = true;
		
		if(isJumping){
			x += deltaMove;
			if( y - deltaJump > max)
				y -= deltaJump; 
			else {
				y = max;
				isJumping = false; 
				isFalling = true; 
			}
		}//end jump
		
		if(isFalling){
			x += deltaMove;
			if(y+deltaJump < min )
				y += deltaJump;
			else{
				y = min;
				isFalling = false; 
			}
		}//end falling
	}
	
	public void applyImmuneCD(){
		if(immuneCD > 0)
		immuneCD--; 
		else 
			isImmune = false;
	}
	
	public void applyCoinCD(){
		if(coinsCD > 0)
			coinsCD--; 
	}
	
	
	public void addCoins(int n){
		coinsCD = 20; 
		coins += n; 
	}
	
	
	
	public void consumeItemOnce(){
		if(item.isNotUsed){
			item.consume(this);
			item.isNotUsed = false;
			item = Item.empty;
		}
			
	}
	
	public void consumeItem(){
		item.consume(this);
	}
	
	public void pickUpItem(Item i){
		item = i;
		i.isNotPicked = false;
	}
	
}//end Player class

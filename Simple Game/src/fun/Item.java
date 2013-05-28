package fun;

import java.awt.Image;

public abstract class Item {

	public int x;
	public int y;
	public int width;
	public int height; 
	
	public boolean isNotUsed; 
	public boolean isNotPicked; 
	
	public Image image; 
	public Image icomImage; 
	
	public Animation animation; 
	
	public static final Item empty = new Item(){
		public void init(){}
		public void effect(Player p) {}
		public void consume(Player p) {}		
	};

	public Item(){
		isNotUsed = true; 
		isNotPicked = true; 
		init();
	}
	
	public abstract void init();
	
	public abstract void effect(Player p);
	
	public abstract void consume(Player p);
	
	
}//end Item

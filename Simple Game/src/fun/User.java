package fun;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name; 
	public int score; 
	
	public User(String n,int s){
		name = n;
		score = s;
	}
}

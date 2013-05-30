import java.io.Serializable;


public class User implements Serializable {
	public String name;
	public int score;
	
	public User(String n, int s){
		name = n;
		score = s;
	}
}

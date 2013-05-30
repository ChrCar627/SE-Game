import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import fun.GamePanel;
import fun.User;


public class HighScores extends GamePanel {

	final Rectangle button1;

	Color color1;
//	ArrayList<String> names;
//	ArrayList scores;
//	ArrayList coins;
	public static String[] names = new String[10];
	public static int[] scores = new int[10];
	//public static int[] coins = new int[10];
	//long[] times = new long[10];
	//public static String[] times = new String[10];
	public static User[] users = new User[10];

	final PurifiedImage pure = new PurifiedImage();
	final Image treasure = pure.TranseptBufferedImage("res/background.jpg");
	
	int fh;
	int size1 = 25; 
	boolean up1 = false; 
	
	public HighScores(JFrame f) {
		super(f);
		fh = f.getHeight();
		button1 = new Rectangle(15, fh-90, 100, 40) ;
		color1 = Color.PINK;

		if(!ReadFile()){
			Random r = new Random();
			AddScore("Khaled",2000);
			AddScore("Chris",3329);
			AddScore("Ryan",1664);
			AddScore("Empty",0);
			AddScore("Empty",0);
			AddScore("Empty",0);
			AddScore("Empty",0);
			AddScore("Empty",0);
			AddScore("Empty",0);
			AddScore("Empty",0);
		}
		
		
	}

	public void init() {

		music = new fun.Music("res/325810_ZeldaOoTShopTheme.wav");

	}

	public void draw(Graphics g) {

		g.drawImage(treasure, 0, 0, null);
		
		
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.pink);
		g.fillRect(100, 30, 300, 360);
		g.setColor(Color.red.darker());
		g2d.setStroke(new BasicStroke(7f));
		g.drawRect(100, 30, 300, 360);
		
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2d.setRenderingHints(rh);
		
		g2d.setColor(color1);
		g2d.fill(button1);
		
		g2d.setColor(Color.BLACK);
		
		g2d.setFont(new Font("italic", Font.BOLD, size1));
		g2d.drawString("<-Back", 20, fh-60);
		
		g2d.setColor(Color.red.darker());
		g2d.setStroke(new BasicStroke(5f));
		g2d.draw(button1);
		g2d.setFont(new Font("italic", Font.BOLD, 25));
		g2d.setColor(Color.black);
		g2d.drawString("Name", 50+90, 60);
		g2d.drawString("Score", 200+90, 60);
//		g2d.drawString("Coins", 300, 60);
//		g2d.drawString("Time", 400, 60);

		g2d.setFont(new Font("italic", Font.PLAIN, 18));
		int curx = 50;
		int cury = 100;
		for(int i=0;i<names.length;i++){
			g2d.drawString(""+(i+1), curx-30+90, cury);
			g2d.drawString(names[i], curx+90, cury);
			g2d.drawString(""+scores[i], curx+150+90, cury);
//			g2d.drawString(""+coins[i], curx+250, cury);
			//int seconds = (int) ((times[i] / 1000) % 60);
			//int minutes = (int) ((times[i] / 1000) / 60);
			//g2d.drawString(""+minutes+":"+((seconds<10)?"0"+seconds:""+seconds), curx+350, cury);
//			g2d.drawString(times[i], curx+350, cury);
			cury+=30;
		}
		
	}


	public void action() {
		music.play();
		
		if(input.mouseIsClicked())
			if(button1.contains(input.point))
			{
				setStage(0);
				music.stop();
			}
		
		if(button1.contains(input.point)){
			color1 = Color.RED.brighter();
			
			if(up1)
				size1++;
			else
				size1--;
			
			if(size1==27 || size1==23)
				up1= !up1;
			
				
		}
		else{
			color1 = Color.PINK;
			size1 = 25; 
		}
	}
	
	public static boolean CheckScore(int score){
		boolean result = false;
		for(int i : scores){
			if(score>i){
				result = true;
				break;
			}
			
		}
		return result;
	}
	public static void AddScore(String name, int score){
		AddScore(name, score, 0);
	}
	public static void AddScore(String name, int score, int position){
		if(position>=names.length) return;
		else if(score>=scores[position]){
			String tempname = names[position];
			int tempscore = scores[position];
	//		int tempcoins = coins[position];
	//		String temptime = times[position];
			names[position] = name;
			scores[position] = score;
			users[position] = new User(name,score);
			
			
	//		coins[position] = coin;
	//		times[position] = time;
			//AddScore(tempname,tempscore,tempcoins,temptime, position+1);
			AddScore(tempname,tempscore, position+1);
			}
		else if(score<scores[position]){
			AddScore(name,score, position+1);
		}
	}
	public static int GetLowestScore(){
		return scores[9];
	}
	public static int GetHighestScore(){
		return scores[0];
	}
	
/*
	public void AddScore(String name, int score, int coin){
		int pos = 10;
		for(Object o : scores){
			int i = (int)o;
			if(score>i){
				pos--;
			}
		}
		if(pos>=10) return;
		while(pos<10);
			String tempname = names.get(pos);
			int tempscore = (int)scores.get(pos);
			int tempcoins = (int)coins.get(pos);
			
			
	}
*/
	public static boolean ReadFile(){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscores.dat"));
			users = (User[]) ois.readObject();
			int i = 0;
			for(User u: users){
				scores[i] = u.score;
				names[i] = u.name;
				i++;
			}
			ois.close();
			return true;
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO NOTE!! we wanna keep the highscores even after closing the game? What we 
		// should do here is try reading a file called "highscores.dat" (for example) and
		// and read in the values of names, scores, and coins. If the file doesn't exist,
		//then make a default file. 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void SaveFile(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highscores.dat"));
			oos.writeObject(users);
			oos.close();
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

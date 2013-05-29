import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.Scanner;

import fun.GamePanel;


public class HighScores extends GamePanel {

	final Rectangle button1;

	Color color1;
//	ArrayList<String> names;
//	ArrayList scores;
//	ArrayList coins;
	String[] names = new String[10];
	int[] scores = new int[10];
	int[] coins = new int[10];
	int fh;
	int size1 = 25; 
	boolean up1 = false; 
	
	public HighScores(JFrame f) {
		super(f);
		fh = f.getHeight();
		button1 = new Rectangle(15, fh-90, 100, 40) ;
		color1 = Color.PINK;

		Random r = new Random();
		AddScore("Harry",r.nextInt(500000),r.nextInt(500));
		AddScore("Todd",r.nextInt(500000),r.nextInt(500));
		AddScore("Riley",r.nextInt(500000),r.nextInt(500));
		AddScore("Kristina",r.nextInt(500000),r.nextInt(500));
		AddScore("Jon",r.nextInt(500000),r.nextInt(500));
		AddScore("Cory",r.nextInt(500000),r.nextInt(500));
		AddScore("Haily",r.nextInt(500000),r.nextInt(500));
		AddScore("Bryanna",r.nextInt(500000),r.nextInt(500));
		AddScore("Carl",r.nextInt(500000),r.nextInt(500));
		AddScore("Lars",r.nextInt(500000),r.nextInt(500));
	}

	public void init() {
		
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
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
		g2d.setFont(new Font("italic", Font.BOLD, 30));
		g2d.setColor(Color.black);
		g2d.drawString("Name", 50, 60);
		g2d.drawString("Score", 200, 60);
		g2d.drawString("Coins", 350, 60);

		g2d.setFont(new Font("italic", Font.PLAIN, 18));
		int curx = 50;
		int cury = 100;
		for(int i=0;i<names.length;i++){
			g2d.drawString(""+(i+1), curx-30, cury);
			g2d.drawString(names[i], curx, cury);
			g2d.drawString(""+scores[i], curx+150, cury);
			g2d.drawString(""+coins[i], curx+300, cury);
			cury+=30;
		}
		
	}


	public void action() {
		
		if(input.mouseIsClicked())
			if(button1.contains(input.point))
				setStage(0);
		
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
	
	public boolean CheckScore(int score){
		boolean result = false;
		for(int i : scores){
			if(score>i){
				result = true;
				break;
			}
			
		}
		return result;
	}
	public void AddScore(String name, int score, int coin){
		AddScore(name, score, coin, 0);
	}
	public void AddScore(String name, int score, int coin, int position){
		if(position>=names.length) return;
		else if(score>scores[position]){
			String tempname = names[position];
			int tempscore = scores[position];
			int tempcoins = coins[position];
			names[position] = name;
			scores[position] = score;
			coins[position] = coin;
			AddScore(tempname,tempscore,tempcoins,position+1);
			}
		else if(score<scores[position]){
			AddScore(name,score,coin,position+1);
		}
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
}
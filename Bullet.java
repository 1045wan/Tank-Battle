package cmo.lxr.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
private static final int SPEED = 10;

public static int WIDTH = 20,HEIGHT=20;


	
	private int x, y;

	private Dir dir;
	private boolean living = true;
	tankFrame tf=null;
	
	public Bullet (int x,int y,Dir dir,tankFrame tf) 
	{
		
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf=tf;
		}
	public void paint(Graphics g) {
		if(!living) {
			tf.bullets.remove(this);
		}
		 Color  c =g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		
		move();
		
		
	}
	private void move() {
		switch (dir)

		{
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
		if(x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) living = false;
	}

}

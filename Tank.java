package cmo.lxr.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Tank {

	private int x, y;

	private Dir dir = Dir.DOWN;
	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	private static final int SPEED = 5;
	
	private boolean moving=false;
	
	private tankFrame tf = null;
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private boolean living = true;

	public Tank(int x, int y, Dir dir,tankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf=tf;
	}

	public void paint(Graphics g) {
		g.drawImage(null, x, y, tf);
		
		
		
		
		
		move();
		
		
	}

	private void move() {
		if(!moving) return ;
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
		
	}

	public void fire() {
		tf.bullets.add( new Bullet(this.x ,this.y, this.dir,this.tf));
		
	}
}

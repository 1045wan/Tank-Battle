package com.liuyonghong.tank;


import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.util.Random;

import com.liuyonghong.tank.observer.TankFireEvent;
import com.liuyonghong.tank.observer.TankFireObserver;

public abstract class Tank extends GameObject{
	private static final int SPEED =2;
	public static int WIDTH = ResourceMgr.goodtankU.getWidth();

	public static int HEIGHT = ResourceMgr.goodtankU.getHeight();
	
	public Rectangle rect = new Rectangle();
	private int x,y;

	private Random random = new Random();

	 int oldx, oldy;

	 private Dir dir =Dir.DOWN;


	private boolean moving = true;
	TankFrame tf =null;
	private boolean living = true;
	public Group group = Group.BAD;
	
    
	public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group=group;
		this.tf = tf;
		
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width= WIDTH;
		rect.height = HEIGHT;
	
	}	
		public void fire() {
			int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
			int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
			
			tf.bullets. add(new Bullet(bX, bY, this.dir, this.group, this.tf));
			if(this.group == Group.GOOD) new Thread(()- >new Audio(" audio/tank_ fire . wav").play()).start();
			}

	
	
	
	
	
	public Dir getDir() {
		return dir;
	}
	

	
	private void move() {
		if(!moving) return;
		
		 switch(dir) {
		 case LEFT:
			 x -=SPEED;
			 break;
		 case UP:
			 y -=SPEED;
			 break;
		 case RIGHT:
			 x +=SPEED;
			 break;
		 case DOWN:
			 y +=SPEED;
			 break;
		 }	
		 
		
		 if(this.group == Group.BAD && random.nextInt(100)>95)
			 this.fire();
		 
		 if(this.group == Group.BAD && random.nextInt(100)>95)
			 randomDir();
		 
		 boundsCheck();
		 //update react
		 rect.x = this.x;
		 rect.y = this.y;
		 
	}
	
	
	private void boundsCheck() {
		if (this.x <2)
			x=2;
		if (this.y <28)
			y=28;
		if (this.x >TankFrame.GAME_WIDTH -Tank.WIDTH -2)
			x=TankFrame.GAME_WIDTH -Tank.WIDTH -2;
		if (this.y >TankFrame.GAME_HEIGHT -Tank.HEIGHT -2)
			y=TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
		
	}

	private void randomDir() {
		
		this.dir = Dir.values()[random.nextInt(4)];
		// TODO 自动生成的方法存根
		
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
	if(!living) GameModel.getInstanks.remove(this);
		
	    switch (dir) {
	    case LEFT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodtankL:ResourceMgr.badtankL, x, y,null);
			break;
	    case UP:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodtankU:ResourceMgr.badtankU, x, y,null);
			break;
	    case RIGHT:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodtankR:ResourceMgr.badtankR, x, y,null);
			break;
	    case DOWN:
			g.drawImage(this.group == Group.GOOD? ResourceMgr.goodtankD:ResourceMgr.badtankD, x, y,null);
			break;
	    
	    }
	    
		 move();
		 
	}

	private void eandomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	
	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void stop() {
		moving = false;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}

	protected abstract Group getGroup();
	protected abstract void die();
	public int getX() {
		// TODO 自动生成的方法存根
		return 0;
	}
	public int getY() {
		// TODO 自动生成的方法存根
		return 0;
	}}
	
	//private transient List<TankFireObserver> fireObservers = Arrays.asList(new);
		//	private void handleFireKey() {
		//TankFireEvent event = new TankFireEvent();
		
		//for(TankFireObserver o : fireObservers) {
		//	TankFireEvent event;
		//	o.actionOnFire(event);
		//}
	



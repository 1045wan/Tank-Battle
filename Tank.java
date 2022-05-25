package com.liuyonghong.tank;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Random;

public class Tank extends GameObject{
	private static final int SPEED =2;
	public static int WIDTH = ResourceMgr.goodtankU.getWidth();

	public static int HEIGHT = ResourceMgr.goodtankU.getHeight();
	
	public Rectangle rect = new Rectangle();
	
	private Random random = new Random();

	 int oldx, oldy;

	 public Dir dir =Dir.DOWN;


	private boolean moving = true;
   
	private boolean living = true;
	public Group group = Group.BAD;
	
    FireStrategy fs ;
    
	public Tank(int x, int y, Dir dir,Group group) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group=group;
		
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width= WIDTH;
		rect.height = HEIGHT;
		
		if(group == Group.GOOD) {
			String goodFSName =(String)PropertyMgr.get("goodFS");
			try {
		   fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
	    }catch  (Exception e) {
	    	e.printStackTrace();
	    }
	}else {
			fs = new DefaultFireStrategy();
	}
		GameModel.getInstance().add(this);
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
	
	public void die() {
		this.living = false;
	}
	
	public void fire() {
		// TODO Auto-generated method stub
		fs.fire(this);
			 } 
	
	
	public Dir getDir() {
		return dir;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}
	
	public void back() {
		x = oldX;
		Y = oldY;
	}
	
	public void move() {//记录移动之前的位置
		oldX =x;
		 oldY =y;
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
		 
		 boundscheck();
		 //update rect
		 rect.x = this.x;
		 rect.y = this.y;
		 
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
	
	public void setX(int x) {
		this.x = x;
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
	
	private transient List<TankFireObserver> fireObservers = Arrays.asList(new  )
	public void handleFireKey() {
		TankFireEvent event = new TankFireEvent(this);
		for(TankFireObserver o : fireObservers) {
			o.actionOnFire(event);
		}
	}


}

package com.sxt;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject {
	//尺寸
	public int width = 40;
	public int height = 50;
	//速度
	private int speed = 3;
	//方向
	Direction direction = Direction.up;
	//血量
	public boolean alive = false;
	//四个方向图片
	private String upImg;
	private String leftImg;
	private String rightImg;
	private String downImg;
	/*//攻击冷却状态
	private boolean attackCoolDown = true;
	//攻击冷却时间毫秒间隔1000ms
	private int attackCoolDownTime = 1000;*/
	
	public Tank(String img, int X, int y , GamePanel gamePanel,String upImg,String leftImg, String rightImg, String downImg) {
		super(img, X, y, gamePanel);
		this.upImg=upImg;
		this.leftImg = leftImg;
		this.rightImg = rightImg;
		this.downImg = downImg;
	}
	public void leftward() {
		setImg (leftImg);
		direction = Direction.left;
		if(!hitWall(x -speed,y) && !moveToBorder(x -speed,y)) {
			this.x -= speed;
		}
	}
	public void upward() {
		setImg(upImg);
		direction = Direction.up;
		if(!hitWall(x,y-speed) && !moveToBorder(x,y-speed)) {
			this.y -= speed; 
		}
	}
	public void rightward() {
		setImg(rightImg);
		direction = Direction.right;
		if(!hitWall(x +speed,y) && !moveToBorder(x +speed,y)) {
			this.x += speed;
		}
	}
	public void downward() {
		setImg(downImg);
		direction = Direction.down;
		if(!hitWall(x,y+speed) && !moveToBorder(x,y+speed)) {
			this.y += speed;
		}
	}
	public void setImg(String img) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
	}
	public void attack() {
			
			if(/*attackCoolDown*/  alive) {
				Point p = this.getHeadPoint(); 
				Bullet bullet = new Bullet("images/bullet/bulletGreen.gif",p.x, p.y, this.gamePanel,direction);
				this.gamePanel.bulletList.add(bullet);
			//线程开始
			//new AttackCD().start();
			
		}
		//新线程
		/*class AttackCD extends Thread {
			public void run() {
				//将攻击功能设置为冷却状态
				attackCoolDown = false;
				//休眠1秒
				try {
					Thread.sleep(attackCoolDownTime); 
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				//将攻击功能解除冷却状态
				attackCoolDown = true;
				//线程终止
				this.stop();
				}
			}*/
		}
		public Point getHeadPoint() {
			switch(direction) {
				case left:
				return new Point(x, y + height/2);
				case right:
				return new Point (x+width, y+height/2);
				case up:
				return new Point (x+width/2, y);
				case down:
				return new Point (x+width/2, y+height);
				default:
				return null;
				}
		}
		//与围墙碰撞检测
		public boolean hitWall(int x,int y) {
			//围墙列表
			ArrayList<Wall> walls = this. gamePanel.walllist;
			//下一步矩形
			Rectangle next = new Rectangle(x, y ,width, height);
			//遍历列表
			for(Wall wall: walls) {
				//与每一-个围墙进行碰撞检测
				if(next . intersects(wall.getRec())) {
					//发生碰撞，返回true 
					return true;
				}
			}
			return false;
		}
		//坦克与边界
		public boolean moveToBorder(int x, int y) {
			if(x< 0) {
			return true ;
			}
			else if(x + width > this.gamePanel.getWidth()) {
				return true;
			}
			else if(y < 0) {
				return true;
			}
			else if(y + height > this . gamePanel. getHeight()) {
				return true ;
			}
				return false;
		}

		@Override
		public abstract void paintselft(Graphics g);
		@Override
		public abstract Rectangle getRec();
}
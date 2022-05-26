package com.sxt;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject {
	//�ߴ�
	public int width = 40;
	public int height = 50;
	//�ٶ�
	private int speed = 3;
	//����
	Direction direction = Direction.up;
	//Ѫ��
	public boolean alive = false;
	//�ĸ�����ͼƬ
	private String upImg;
	private String leftImg;
	private String rightImg;
	private String downImg;
	/*//������ȴ״̬
	private boolean attackCoolDown = true;
	//������ȴʱ�������1000ms
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
			//�߳̿�ʼ
			//new AttackCD().start();
			
		}
		//���߳�
		/*class AttackCD extends Thread {
			public void run() {
				//��������������Ϊ��ȴ״̬
				attackCoolDown = false;
				//����1��
				try {
					Thread.sleep(attackCoolDownTime); 
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				//���������ܽ����ȴ״̬
				attackCoolDown = true;
				//�߳���ֹ
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
		//��Χǽ��ײ���
		public boolean hitWall(int x,int y) {
			//Χǽ�б�
			ArrayList<Wall> walls = this. gamePanel.walllist;
			//��һ������
			Rectangle next = new Rectangle(x, y ,width, height);
			//�����б�
			for(Wall wall: walls) {
				//��ÿһ-��Χǽ������ײ���
				if(next . intersects(wall.getRec())) {
					//������ײ������true 
					return true;
				}
			}
			return false;
		}
		//̹����߽�
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
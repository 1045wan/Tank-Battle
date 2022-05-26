package com.sxt;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Bot extends Tank{
	int moveTime = 0;
	//private Direction direction;
	public Bot(String img, int X, int y, GamePanel gamePanel, String upImg, String leftImg, String rightImg,
			String downImg) {
		super(img, X, y, gamePanel, upImg, leftImg, rightImg, downImg);
		// TODO 自动生成的构造函数存根
	}
	public Direction getRandomDirection( ) {
		Random random = new Random();
		int rnum = random. nextInt(4);
		switch( rnum) {
			case 0:
			return Direction. left;
			case 1:
			return Direction. right;
			case 2:
			return Direction. up;
			case 3:
			return Direction. down;
			default:
			return null;
		}
	}
	public void go(){
		attack();
		if(moveTime>=20) {
			direction = getRandomDirection();
			moveTime=0;
		}
		else{
			moveTime+=1;
			switch(direction) {
				case up:
				upward();
				break;
				case down:
				downward();
				break;
				case right:
				rightward();
				break;
				case left:
				leftward();
				break; 
			}
		}
	}
	public void attack() {
		Point p = getHeadPoint();
		Random random = new Random();
		int runm = random.nextInt(100);
		if(runm<4) {
			this.gamePanel.bulletList.add(new EnemyBullet("images/bullet/bulletyellow.gif",p.x, p.y, this.gamePanel,direction));
		}
	}


	@Override
	public void paintselft(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img, x, y,null); 
		go();
	}

	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return new Rectangle(x,y,width,height);
	}

}

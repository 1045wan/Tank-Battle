package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends GameObject{
	//尺寸
		int width = 10;
		int height = 10;
		//速度
		int speed = 7;
		//方向
		Direction direction;
	public Bullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
		super(img, x, y, gamePanel);
		// TODO 自动生成的构造函数存根
		this.direction = direction;
	}
	public void Leftward() {
		x -= speed;
	}
	public void rightward() {
		x += speed;
	}
	public void upward() {
		y -=speed;
	}
	public void downward() {
		y += speed;
	}
	public void go() {
		switch(direction) {
			case left:
			Leftward();
			break;
			case right:
			rightward();
			break;
			case up:
				upward();
				break;
				case down:
				downward();
				break;		
		}
	this.hitWall();
	this.hitbase();
	}
	//我方子弹与敌方坦克碰撞检测
	public void hitBot() {
		ArrayList<Bot> bots = this. gamePanel.BotList;
		for(Bot bot: bots) {
			if(this. getRec(). intersects (bot.getRec())) {
				this.gamePanel.blastlist.add(new Blast("",bot.x-34,bot.y-14,this.gamePanel));
				this. gamePanel. BotList. remove(bot);
				this. gamePanel. removeList . add(this);
				break;
			}
		}
	}  
	public void hitbase() {
		ArrayList<Base> Baselist = this. gamePanel.baselist;
		for(Base base: Baselist) {
			if(this. getRec(). intersects (base.getRec())) {
				
				this. gamePanel. baselist. remove(base);
				this. gamePanel. removeList . add(this);
				break;
			}
		}
	}
	//子弹与墙壁碰撞检测
	public void hitWall() {
		//围墙列表
		ArrayList<Wall> walls = this . gamePanel. walllist;
		//遍历列表
		for(Wall wall: walls) {
			//与每一个围墙进行碰撞检测
			if(this. getRec(). intersects(wall. getRec())) {
				//删去围墙和子弹
				this . gamePanel. walllist . remove (wall);
				this. gamePanel. removeList. add(this) ;
				//停止循环
				break;
			}
		}
	}
	//子弹出边界移除
	public void	mooveeToBorder() {
		if(x < 0 | x + width > this. gamePanel. getWidth()) {
			this. gamePanel. removeList . add(this);
		}
		if(y < 0 | y + height > this. gamePanel. getHeight()) {
			this. gamePanel. removeList . add(this);
		}
	}

	
	@Override
	public void paintselft(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img, x,y, null);
		this.go();
		this.hitBot();
	}
	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return new Rectangle(x,y,width,height);
	}
	
}

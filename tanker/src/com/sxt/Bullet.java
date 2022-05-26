package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends GameObject{
	//�ߴ�
		int width = 10;
		int height = 10;
		//�ٶ�
		int speed = 7;
		//����
		Direction direction;
	public Bullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
		super(img, x, y, gamePanel);
		// TODO �Զ����ɵĹ��캯�����
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
	//�ҷ��ӵ���з�̹����ײ���
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
	//�ӵ���ǽ����ײ���
	public void hitWall() {
		//Χǽ�б�
		ArrayList<Wall> walls = this . gamePanel. walllist;
		//�����б�
		for(Wall wall: walls) {
			//��ÿһ��Χǽ������ײ���
			if(this. getRec(). intersects(wall. getRec())) {
				//ɾȥΧǽ���ӵ�
				this . gamePanel. walllist . remove (wall);
				this. gamePanel. removeList. add(this) ;
				//ֹͣѭ��
				break;
			}
		}
	}
	//�ӵ����߽��Ƴ�
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
		// TODO �Զ����ɵķ������
		g.drawImage(img, x,y, null);
		this.go();
		this.hitBot();
	}
	@Override
	public Rectangle getRec() {
		// TODO �Զ����ɵķ������
		return new Rectangle(x,y,width,height);
	}
	
}

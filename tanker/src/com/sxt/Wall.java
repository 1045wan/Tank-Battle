package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{
	//尺寸
	int length=50;
	
	public Wall(String img, int x, int y, GamePanel gamePanel) {
		super(img, x, y, gamePanel);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void paintselft(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img , x ,y, gamePanel);
	}

	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return new Rectangle(x,y,length,length);
	}

}

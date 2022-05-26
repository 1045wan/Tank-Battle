package com.liuyonghong.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{
	
	
	public Wall(String img, int x, int y, GameModel gameModel) {
		super(img, x, y, gameModel);
		// TODO 自动生成的构造函数存根
	}

	//尺寸
	int length =50;

	@Override
	public void paintselft(Graphics g) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return null;
	}



}
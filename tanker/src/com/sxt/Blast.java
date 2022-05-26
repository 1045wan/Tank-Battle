package com.sxt;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Blast extends GameObject{
	//爆炸图片
	static Image[] imgs = new Image[8];
	int explodeCount = 0;
	static {
		for(int i = 0; i<8; i++) {
			imgs[i] = Toolkit.getDefaultToolkit().getImage("images/blast/blast"+(i+1)+".gif");
		}
	}
	
	public Blast(String img, int x, int y, GamePanel gamePanel) {
		super(img, x, y, gamePanel);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void paintselft(Graphics g) {
		// TODO 自动生成的方法存根
		if(explodeCount < 8) {
			g.drawImage(imgs[explodeCount],x,y,null);
			explodeCount++;
		}
	}

	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return null;
	}

}

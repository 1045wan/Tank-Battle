package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Base extends GameObject{
	int length = 50;
	public Base(String img, int x, int y, GamePanel gamePanel) {
		super(img, x, y, gamePanel);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void paintselft(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img, x, y, gamePanel);
	}

	@Override
	public Rectangle getRec() {
		// TODO �Զ����ɵķ������
		return new Rectangle( x,y,length,length );
	}

}

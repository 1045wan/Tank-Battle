package com.liuyonghong.tank;

import java.awt.Graphics;

import com.liuyonghong.tank.abstractfactory.BaseExplode;

public class Explode extends BaseExplode{
	public static int WIDTH = ResourceMgr.explodes[0].getWidth(); 
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight(); 

	
	private int x, y;

	
	private boolean living =true;
	TankFrame tf=null;
	
	private int step = 0;
	
	public Explode(int x,int y,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	 
 new Thread(()->new Audio("audio/explode.wav").play()).start();
	}
	
	
    @Override
	public void paint(Graphics g) {
		
	g.drawImage(ResourceMgr.explodes[step++],x,y,null);
	
	if(step>=ResourceMgr.explodes.length)
		tf.exp1odes.remove(this);
	
    }
	
}



package com.liuyonghong.tank;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tf= new TankFrame();
		
		int initTamkCount = Integer.parseInt((String)PropertyMgr.get("initTamkCount"));
		
		//初始化敌方坦克
		//for(int i=0;i<initTamkCount;i++) {
		//	 tf.tanks.add(new Tank(50+i*80,200,Dir.DOWN,Group.BAD,tf));
		//}
		
		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		 
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
    }
}
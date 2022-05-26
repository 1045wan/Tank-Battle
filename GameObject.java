package com.liuyonghong.tank;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class GameObject {
	
	//图片
	public Image img;
	//坐标
	public int x;
	public int y;
	//界面
	public static GameModel gameModel;
	
	public GameObject(String img,int x,int y,GameModel gameModel) {
		this.img=Toolkit.getDefaultToolkit().getImage(img);
		this.x=x;
		this.y=y;
		this.gameModel=gameModel;
	}
	
	public GameObject() {
		
		// TODO 自动生成的构造函数存根
	}

	public abstract void paintselft(Graphics g);
	public abstract Rectangle getRec();

}
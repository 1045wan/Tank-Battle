package com.liuyonghong.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameModel  {
	
	private static final GameModel INSTANCE = new GameModel();
	
	static {
		INSTANCE.init();
	}
	
	Tank myTank;
	//List<Bullet> bullets = new ArrayList<>();
	//List<Tank> tanks = new ArrayList<>();
	//List<Explode> explodes = new ArrayList<>();
	ColliderChain chain = new ColliderChain();
	
	private List<GameObject> objects = new ArrayList<>();
	
	private satic GameModel getInstance() {
		return INSTANCE;
	}
	private GameModel() {}
	
	private void init() {
		//初始化主坦克
		myTank =new Tank (200,400,Dir.DOWN,Group.GOOD);
		int initTankCount = Integer.parseInt((String) PropertyMgr.get(key)
				
				//初始化墙
		add(new Wall(150,150,200,50));
		add(new Wall(550,150,200,80));
		add(new Wall(350,350,60,140));
		add(new Wall(250,250,70,180));
		
	}
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	
	public void paint(Graphics g) {
    	Color c = g.getColor();
    	g.setColor(Color.WHITE);
		//g.drawString("子弹的数量:"+ bullets.size(),10,60);
    	//g.drawString("敌人的数量:"+ tanks.size(),10,80);
    	//g.drawString("爆炸的数量:"+ explodes.size(),10,100);
    	//g.setColor(c);
		
		
    //消除子弹列表的内存泄露，处理迭代器中的删除问题	
    //方法一	
    	myTank.paint(g);
    	for(int i=0; i<objects.size();i++) {
    		objects.get(i).paint(g);
  	}
    	//相互碰撞
    	for (int i =0; i < objects.size(); i++) {
    		for (int j = i+1;j< objects.size(); j++) {
    			GameObject o1 =objects.get(i);
    			GameObject o2 =objects.get(j);
    			//for
    			chain.collide(o1,o2);
    			
    		}
    	}
    	
    	//for (int i =0;i<bullets.size(); i++){
    	//for (int i =0;i<tanks.size(); j++)
    	//bullets.get(i).collideWith(tanks.get(j));
    	//}
	}
	
	 public Tank getMainTank() {
		 return myTank;
	 }
	 public void save() {
		 File f =new File("c:/liuyonghong/tank.date");
		 ObjectOutputStream oos =null;
		 try {
		 oos = new ObjectOutputStream(new FileOutputStream(f));
		 oos.writeObject(myTank);
		 oos.writeObject(objects);
	 }catch (FileNotFoundException e) {
		 e.printStackTrace();
	 }catch (IOException e) {
		 e.printStackTrace();	 
	 } finally {
		 if(oos != null) {
			 try {
				 oos.close();
			 }catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	 }
 }
	
	public void load() {
		 File f =new File("c:/liuyonghong/tank.date");
		 ObjectOutputStream oos =null;
		try {
			oos= new ObjectOutputStream(new FileOutputStream(f));
			oos =(Tank)ois.readObject();
			oos = (List)ois.readObject();
		 
	 }catch (FileNotFoundException e) {
		 e.printStackTrace();
	 }catch (IOException e) {
		 e.printStackTrace();
      catch (ClassNotFoundException e) {
    	  e.printStackTrace();
      }finally {
		 if(oos != null) {
			 try {
				 oos.close();
			 }catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	 }
		 
	 }




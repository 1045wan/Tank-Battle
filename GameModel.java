package com.liuyonghong.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GameModel  {
	
	private static final GameModel INSTANCE = new GameModel();
	public static Object getInstanks;
	
	static {
		INSTANCE.init(null);
	}
	
	Tank myTank;
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	List<Explode> explodes = new ArrayList<>();
	//ColliderChain chain = new ColliderChain();
	
	private List<GameObject> objects = new ArrayList<>();
	
	static GameModel getInstance() {
		return INSTANCE;
	}
	private GameModel() {}
	
	private void init(String key) {
		//初始化主坦克
	//	myTank =new Tank (200,400,Dir.DOWN,Group.GOOD);
		//int initTankCount = Integer.parseInt((String) PropertyMgr.get (key);
				
				//初始化墙
		add(new Wall(null, 146, 150, null));
		add(new Wall(null, 140, 130, null));
		add(new Wall(null, 60, 150, null));
		add(new Wall(null, 80, 150, null));
		
		
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
    		objects.get(i).paintselft(g);
  	}
    	//相互碰撞
    	for (int i =0; i < objects.size(); i++) {
    		for (int j = i+1;j< objects.size(); j++) {
    			GameObject o1 =objects.get(i);
    			GameObject o2 =objects.get(j);
    			//for
    			//chain.collide(o1,o2);
    			
    		}
    	}
    	
    	for (int i =0;i<bullets.size(); i++){
    	int j = 98;
		for (int i1 =0;i1<tanks.size(); j++)
    	bullets.get(i1).collideWith(tanks.get(j));
    	}
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
			//oos =(Tank)ois.readObject();
			//oos = (List)ois.readObject();
		 
	 }catch (FileNotFoundException e) {
		 e.printStackTrace();
	 }catch (IOException e) {
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
}



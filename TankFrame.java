package com.liuyonghong.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {
	
	

	Tank myTank = new Tank(200,200,Dir.DOWN,this);
	List<Bullet> bullets =new ArrayList<>();
	static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
	
    public TankFrame() {
    setSize(800,600);
    setResizable(false);
    setTitle("tank war");
    setVisible(true);
    
    this.addKeyListener(new MykeyListener());

    addWindowListener(new WindowAdapter() {
    	
    	@Override
    	public void windowClosing(WindowEvent e) {
    		System.exit(0);
    	}
    });
  }
   //消除闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
    	if(offScreenImage == null) {
    		offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
    	}
    	Graphics gOffScreen =offScreenImage.getGraphics();
    	Color c = gOffScreen.getColor();
    	gOffScreen.setColor(Color.PINK);
    	gOffScreen.fillRect(0, 0,GAME_WIDTH ,GAME_HEIGHT);
    	gOffScreen.setColor(c);
    	paint(gOffScreen);
    	g.drawImage(offScreenImage, 0, 0, null);
    }
    
    
    @Override
   public void paint(Graphics g) {
    	Color c = g.getColor();
    	g.setColor(Color.WHITE);
    	g.drawString("子弹的数量:"+ bullets.size(),10,60);
    	g.setColor(c);
    //消除子弹列表的内存泄露，处理迭代器中的删除问题	
    //方法一	
    	myTank.paint(g);
    	for(int i=0; i<bullets.size();i++) {
    		bullets.get(i).paint(g);
  	}
	//方法二
    //for(Iterator<Bullet>it = bullets.iterator();it.hasNext();) {
	//	Bullet b = it.next();
	//	if(!b.live)it.remove();	}
    	
    
    	
   }
    
    //记录按键状态来控制坦克方向
    class  MykeyListener extends KeyAdapter{
    	
    	boolean bL = false;
    	boolean bU = false;
    	boolean bR = false;
    	boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
            case KeyEvent.VK_UP:
            	bU = true;
				break;	
            case KeyEvent.VK_RIGHT:
            	bR = true;
				break;	
            case KeyEvent.VK_DOWN:
            	bD = true;
	            break;
			
            case KeyEvent.VK_CONTROL:
            myTank.fire();    
            break;
            
            
            
				default:
					break;
			}
		setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
            case KeyEvent.VK_UP:
            	bU = false;
				break;	
            case KeyEvent.VK_RIGHT:
            	bR = false;
				break;	
            case KeyEvent.VK_DOWN:
            	bD = false;
	            break;
	            
            case KeyEvent.VK_CONTROL:
            	myTank.fire();
            	break;
				
				default:
					break;
			}
			setMainTankDir();
		}
    	
		private void setMainTankDir() {
			
			if(!bL && !bU && !bR && !bD) myTank.setMoving(false); //添加坦克禁止的处理，只要四个键都没有按下，坦克就是静止的
			else {
			myTank.setMoving(true);
			
			if(bL) myTank.setDir(Dir.LEFT);
			if(bU) myTank.setDir(Dir.UP);
			if(bR) myTank.setDir(Dir.RIGHT);
			if(bD) myTank.setDir(Dir.DOWN);
			
	       }	
		
			
		}
   
    }
  
}

   

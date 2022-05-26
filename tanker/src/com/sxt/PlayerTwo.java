package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class PlayerTwo extends Tank{
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
	public PlayerTwo(String img, int X, int y, GamePanel gamePanel, String upIMg, String leftImg, String rightImg,
			String downImg) {
		super(img, X, y, gamePanel, upIMg, leftImg, rightImg, downImg);
		// TODO 自动生成的构造函数存根
	}
	//坦克移动方法
	 public void keyPressed(KeyEvent e){
	        int key = e.getKeyCode();
	        switch (key){
	            case KeyEvent.VK_LEFT:
	                left = true;
	                break;
	            case KeyEvent.VK_DOWN:
	                down = true;
	                break;
	            case KeyEvent.VK_RIGHT:
	                right = true;
	                break;
	            case KeyEvent.VK_UP:
	                up = true;
	                break;
	            case KeyEvent.VK_K:
	            	attack();
	            default:
	                break;
	        }
	    }

	    public void keyReleased(KeyEvent e){
	        int key = e.getKeyCode();
	        switch (key){
	            case KeyEvent.VK_LEFT:
	                left = false;
	                break;
	            case KeyEvent.VK_DOWN:
	                down = false;
	                break;
	            case KeyEvent.VK_RIGHT:
	                right = false;
	                break;
	            case KeyEvent.VK_UP:
	                up = false;
	                break;
	            default:
	                break;
	        }
	    }

	    public void move(){
	        if(left){
	            leftward();
	        }
	        else if(right){
	            rightward();
	        }
	        else if(up){
	            upward();
	        }else if(down){
	            downward();
	        }
	    }
	@Override
	public void paintselft(Graphics g) {
		// TODO 自动生成的方法存根
		g.drawImage(img,x,y, null);
		move();
	}

	@Override
	public Rectangle getRec() {
		// TODO 自动生成的方法存根
		return new Rectangle(x,y,width,height);
	}

}

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
		// TODO �Զ����ɵĹ��캯�����
	}
	//̹���ƶ�����
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
		// TODO �Զ����ɵķ������
		g.drawImage(img,x,y, null);
		move();
	}

	@Override
	public Rectangle getRec() {
		// TODO �Զ����ɵķ������
		return new Rectangle(x,y,width,height);
	}

}

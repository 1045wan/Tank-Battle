package cmo.lxr.tank;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {
private static final int SPEED = 10;

public static int WIDTH = 10;

public static int HEIGHT=10;


	
	private int x, y;

	private Dir dir;
	private boolean living = true;
	tankFrame tf=null;
	private Group group = Group.BAD;
	
	public Bullet (int x,int y,Dir dir,Group group,tankFrame tf) 
	{
		
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf=tf;
		}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public void paint(Graphics g) {
		if(!living) {
			tf.bullets.remove(this);
		}
		switch (dir) {
		case LEFT:		
		try {
			BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\bulletL.gif"));
			assertNotNull(image);
			g.drawImage(image, x, y, tf);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		break;
		case UP:		
			try {
				BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\bulletU.gif"));
				assertNotNull(image);
				g.drawImage(image, x, y, tf);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			break;
		case RIGHT:		
			try {
				BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\bulletR.gif"));
				assertNotNull(image);
				g.drawImage(image, x, y, tf);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			break;
		case DOWN:		
			try {
				BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\bulletD.gif"));
				assertNotNull(image);
				g.drawImage(image, x, y, tf);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			break;
		}	
		
		
		move();
		
		
	}
	private void move() {
		switch (dir)

		{
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
		if(x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) living = false;
	}
	public void collideWith(Tank tank) {
		if(this.group==tank.getGroup()) return;
		Rectangle rect1=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
		Rectangle rect2=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
		if(rect1.intersects(rect2)) {
			tank.die();
			this.die();
			
		}
		
	}
	private void die() {
		this.living=false;
		
	}
			
	
		
	}



package cmo.lxr.tank;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;









public class Tank {

	private static final int SPEED = 1;


	public static int WIDTH =20;

	public static int HEIGHT=20;
	
	private Random random = new Random();

	private int x,y;

	private Dir dir = Dir.DOWN;

	private boolean moving=true;
	private tankFrame tf = null;

	private boolean living = true;
	private Group group = Group.BAD;
	public Tank(int x, int y, Dir dir, Group group,tankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf=tf;
		
	}
	public void fire() {
		int bx=this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int by=this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		tf.bullets.add( new Bullet(this.x ,this.y, this.dir,this.group,this.tf));
		
	}
			
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Dir getDir() {
		return dir;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isMoving() {
		return moving;
	}

	private void move() {
		if(!moving) return ;
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
		
		if(random.nextInt(10)>8) this.fire();
	}

	public void paint(Graphics g) {
		if(!living) tf.tanks.remove(this);
		switch (dir) {
		case LEFT:		
		try {
			BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\tankL.gif"));
			assertNotNull(image);
			g.drawImage(image, x, y, tf);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		break;
		case UP:		
			try {
				BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\tankU.gif"));
				assertNotNull(image);
				g.drawImage(image, x, y, tf);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			break;
		case RIGHT:		
			try {
				BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\tankR.gif"));
				assertNotNull(image);
				g.drawImage(image, x, y, tf);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			break;
		case DOWN:		
			try {
				BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\tankD.gif"));
				assertNotNull(image);
				g.drawImage(image, x, y, tf);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			break;
		}	
					
			
					
				
				
				
		
		
		
		move();
		
		
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void die() {
		this.living=false;
		
	}
}

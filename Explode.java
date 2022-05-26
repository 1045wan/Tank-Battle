package cmo.lxr.tank;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Explode {
private static final int SPEED = 10;

public static int WIDTH = 10;

public static int HEIGHT=10;


	
	private int x, y;


	private boolean living = true;
	tankFrame tf=null;
	private int step = 0;
	
	public Explode (int x,int y,tankFrame tf) 
	{
		
		this.x = x;
		this.y = y;
		
		this.tf=tf;
		}
	
	public void paint(Graphics g) {
		try {
			BufferedImage image =ImageIO.read(new File("D:\\新建文件夹 (2)\\tank\\src\\images\\e10.gif"));
			assertNotNull(image);
			g.drawImage(image, x, y, tf);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
			
		
		
	}
	
	
		
	}



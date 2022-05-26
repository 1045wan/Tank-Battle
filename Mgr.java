package cmo.lxr.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mgr {
public  static BufferedImage tankL,tankU,tankR,tankD;
public  static BufferedImage BulletL,BulletU,BulletR,BulletD;
public static BufferedImage[] explodes = new BufferedImage[16];

static {
	try {
		tankL=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
		tankU=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
		tankR=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
		tankD=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
		
		BulletL=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/BulletL.gif"));
		BulletU=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/BulletU.gif"));
		BulletR=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/BulletR.gif"));
		BulletD=ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/BulletD.gif"));
		
		for(int i=0; i<16; i++) 
			explodes[i] = ImageIO.read(Mgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}
}

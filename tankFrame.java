package cmo.lxr.tank;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class tankFrame extends Frame {

	Tank myTank=new Tank(200,200,Dir.DOWN,this);
	List <Bullet>bullets=new ArrayList<>();
	
	
	
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
	
	public tankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
	
	this.addKeyListener(new MyKeyListener());
		
	addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
 }
 
 Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.PINK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

 
 @Override
 public void paint(Graphics g) {
	 
	 Color c = g.getColor();
		g.setColor(Color.white);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.setColor(c);
	 
	 myTank.paint(g);  
	 for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
	// for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
			// Bullet b = it.next();
			// if(!b.live) it.remove();
			// }
	 
	 
 }

 class MyKeyListener extends KeyAdapter {

	 boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			bL = true;
			setMainTankDir();
			break;
		case KeyEvent.VK_UP:
			bU = true;
			setMainTankDir();
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			setMainTankDir();
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			setMainTankDir();
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
			setMainTankDir();
			break;
		case KeyEvent.VK_UP:
			bU = false;
			setMainTankDir();
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			setMainTankDir();
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			setMainTankDir();
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
		if (!bL && !bU && !bR && !bD)myTank.setMoving(false);
		else {
		myTank.setMoving(true);
		if (bL) myTank.setDir(Dir.LEFT);
		if (bU) myTank.setDir(Dir.UP);
		if (bR) myTank.setDir(Dir.RIGHT);
		if (bD) myTank.setDir(Dir.DOWN);
		}
		
	}
}
}

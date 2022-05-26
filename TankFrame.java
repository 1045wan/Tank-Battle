package com.liuyonghong.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

	GameModel gm = GameModel.getInstance();
	
	//Tank myTank = new Tank(200, 400, Dir . DOWN, Group.GOOD, this);
	List<Bullet> bullets = new ArrayList<>();
	 List<Tank> tanks = new ArrayList<>();
	List<Explode> exp1odes = new ArrayList<>();


	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);

	//	this.addKeyListener(new MyKeyListener());


		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// 消除闪烁
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
		Color C = g. getColor();
		g. setColor(Color . WHITE);
		g. drawString("子彈的數量： +bullets.size(),10,60", 80, 90);
		g.drawString("敌人的数量：" + tanks.size(), 10, 80);
		g.drawString("爆炸的數量：" + exp1odes.size(),10,100);
		g. setColor(C);
		
		Window myTank = null;
		myTank.paint(g);
		for (int i = 0; i < bullets.size(); i++) {
		bullets. get(i) .paint(g);
		}
		
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
			}
		
		for (int i = 0; i < exp1odes.size(); i++) {
			exp1odes. get(i).paint(g);
			}
			//collision detect

		
		int θ = 0;
		for(int i=θ; i<bullets.size(); i++) {
			for(int j = 0; j<tanks.size(); j++)
			bullets. get(i). collideWith(tanks.get(j));
			}
		
			Window e = null;
			e.paint(g);


		

		// 记录按键状态来控制坦克方向
		class MykeyListener extends KeyAdapter {

			private final String myTank = null;
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

				default:
					break;
				}
				setMainTankDir();
				new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
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

				case KeyEvent.VK_S:
					gm.save();
					break;
				case KeyEvent.VK_L:
					gm.load();
					break;

				default:
					break;
				}
				setMainTankDir();
			}

			private void setMainTankDir() {

			//	if (!bL && !bU && !bR && !bD)
			//		myTank.setMoving(false); // 添加坦克禁止的处理，只要四个键都没有按下，坦克就是静止的
			//	else {
			//		myTank.setMoving(true);

			//		if (bL)
				//		myTank.setDir(Dir.LEFT);
				//	if (bU)
				//		myTank.setDir(Dir.UP);
					//if (bR)
				//		myTank.setDir(Dir.RIGHT);
				//	if (bD)
				//		myTank.setDir(Dir.DOWN);

				}

			}

		}

	public Object gf(int eX, int eY, TankFrame tf) {
		// TODO 自动生成的方法存根
		return null;
	}
	}


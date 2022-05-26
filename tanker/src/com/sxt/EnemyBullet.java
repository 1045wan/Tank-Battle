package com.sxt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class EnemyBullet extends Bullet{

	public EnemyBullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
		super(img, x, y, gamePanel, direction);
		// TODO �Զ����ɵĹ��캯�����
	}
	public void hitplayer() {
		ArrayList<Tank> players = this. gamePanel.playerList;
		for(Tank player: players) {
			if(this. getRec().intersects (player.getRec())) {
				this.gamePanel.blastlist.add(new Blast("",player.x-34,player.y-14,this.gamePanel));
				this.gamePanel.playerList.remove(player);
				this.gamePanel.removeList.add(this);
				player.alive = false;
				break;
			}
		}
	}
	@Override
	public void paintselft(Graphics g) {
		// TODO �Զ����ɵķ������
		g.drawImage(img, x,y, null);
		this.go();
		this.hitplayer();
	}
	@Override
	public Rectangle getRec() {
		// TODO �Զ����ɵķ������
		return new Rectangle(x,y,width,height);
	}
}

package com.liuyonghong.tank;

public class FourDirFireStrategy  {
	@SuppressWarnings("unused")
	private Dir dir;

	public void fire(Tank t) {
	int bX = t.oldx + Tank.WIDTH/2 - Bullet.WIDTH/2;
	int bY = t.oldy + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
	
	Dir[] dirs = Dir. values();
	for (Dir dir:dirs) {
	   
	new Bullet(bX, bY, dir,t .group, t.tf);
	
	if(t.group == Group.GOOD) new Audio("audio/tank");
	}


	}
}

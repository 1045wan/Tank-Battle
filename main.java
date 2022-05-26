package cmo.lxr.tank;


import java.awt.event.WindowEvent;

public class main {

	public static void main(String[] args) throws InterruptedException {
	
		tankFrame tf=new tankFrame();
		
		for(int i=0; i<5; i++) {
			tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD,tf));
		}
		
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}	

	}

}

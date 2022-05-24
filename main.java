package cmo.lxr.tank;


import java.awt.event.WindowEvent;

public class main {

	public static void main(String[] args) throws InterruptedException {
	
		tankFrame tl=new tankFrame();
		
		while(true) {
			Thread.sleep(50);
			tl.repaint();
		}	

	}

}

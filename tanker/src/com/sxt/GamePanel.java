package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JFrame {
	

    /** ����˫����ͼƬ */
    Image offScreenImage = null;
    //��Ϸ״̬ 0 ��Ϸδ��ʼ�� 1 ����ģʽ�� 2 ˫��ģʽ�� 3 ��Ϸ��ͣ�� 4 ��Ϸʧ�ܣ� 5 ��Ϸʤ��
    int state= 0;
    //��Ϸ�Ƿ�ʼ
    private boolean start = false;
    //��ʱ����
    int a = 1;
    //�ػ����
    int count = 0;
    //�����ɵ�������
    int emenyCount = 0;
  //��ϷԪ���б�
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    ArrayList<Bot> BotList = new ArrayList<Bot>();
    ArrayList<Bullet> removeList = new ArrayList<Bullet>();
    ArrayList<Tank> playerList = new ArrayList<Tank>();
    ArrayList<Wall> walllist = new ArrayList<Wall>();
    ArrayList<Base> baselist = new ArrayList<Base>();
    ArrayList<Blast> blastlist = new ArrayList<Blast>();
    //ָ��ͼƬ
    private Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
    //���ڳ���
    int width = 800;
    int height = 610;
    //���弯��
    public List<Tank> tankList = new ArrayList<>();
    
    //ָ���ʼ�߶�
    int y = 150;
    //PlayerOne
    PlayerOne playerOne = new PlayerOne("images/player1/p1tankU.gif", 125, 510,this,
    "images/player1/p1tankU.gif" , "images/player1/p1tankL.gif",
    "images/player1/p1tankR.gif", "images/player1/p1tankD.gif");
    //PlayerTwo
    PlayerTwo playerTwo = new PlayerTwo("images/player2/p2tankU.gif", 625, 510,this,
    "images/player2/p2tankU.gif" , "images/player2/p2tankL.gif",
    "images/player2/p2tankR.gif", "images/player2/p2tankD.gif");
    //����
    Base base = new Base("images/star.gif",375,570,this);
    //���ڵ���������
    public void launch(){   			
        //����
        setTitle("̹�˴�ս");
        //���ڳ�ʼ��С
        setSize(width, height);
        //ʹ��Ļ����
        setLocationRelativeTo(null);
        //��ӹر��¼�
        setDefaultCloseOperation(3);
        //�û����ܵ�����С
        setResizable(false);
        //ʹ���ڿɼ�
        setVisible(true);
        //��Ӽ����¼�
        this.addKeyListener(new GamePanel.KeyMonitor());
      //���Χǽ
        for(int i=0;i<14;i++){
        walllist.add(new Wall("images/walls.gif",i*60, 170, this));
        }
        walllist.add(new Wall("images/walls.gif", 305, 560, this));
        walllist.add(new Wall("images/walls.gif", 305, 500, this));
        walllist.add(new Wall("images/walls.gif", 365, 500, this));
        walllist.add(new Wall("images/walls.gif", 425, 500,this));
        walllist.add(new Wall("images/walls.gif", 425,560,this));
        baselist.add(base);
        while (true){
        	//��Ϸʤ���ж�
        	if(BotList.size() == 0 && emenyCount == 10) {
        		state = 5;
        	}
        	//��Ϸʧ���ж�
        	if((playerList.size() == 0 && state == 1 || playerList.size() == 0 && state ==2) || baselist.size() == 0) {
        		state = 4;
        	}        	
        	if(count % 200 == 1 && emenyCount < 30  && state == 1 || count % 200 == 1 && emenyCount < 10 && state == 2) {
        		//��ӵ���̹��
        		Random random = new Random();
        		int runm = random.nextInt(800);
            	BotList.add(new Bot("images/enemy/enemy1U.gif",runm,110,this,
                		"images/enemy/enemy1U.gif","images/enemy/enemy1L.gif",
                		"images/enemy/enemy1R.gif","images/enemy/enemy1D.gif"
                		));
            	emenyCount++;
        	}
            repaint();
            try {
                //�߳�����  1�� = 1000����
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    
    @Override
    public void paint(Graphics g) {
    	//System.out.println(bulletList.size());
        //����������һ����С��ImageͼƬ
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        //��ø�ͼƬ�Ļ���
        Graphics gImage= offScreenImage.getGraphics();
        //���ñ�����ɫ
        gImage.setColor(Color.gray);
        //�����������
        gImage.fillRect(0, 0, width, height);
        //�ұ仭����ɫ
        gImage.setColor(Color.GREEN);
        //�ı����ִ�С����ʽ
        gImage.setFont(new Font("����",Font.PLAIN,50));
        if(state == 0){
            //�������
            gImage.drawString("ѡ����Ϸģʽ",220,100);
            gImage.drawString("������Ϸ",220,200);
            gImage.drawString("˫����Ϸ",220,300);
            gImage.drawString("��1��2ѡ��ģʽ�����س���ʼ��Ϸ",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
           gImage.setFont(new Font("����",Font.BOLD,30));
           gImage.setColor(Color.red);
           gImage.drawString("WASD�����ƶ�",0,510);
           gImage.drawString("�ո����",0,550);
           gImage.drawString("ʣ����ˣ�"+ BotList.size(),10,60);
            //paint�ػ���ϷԪ��
            for(Tank player: playerList) {
            	player. paintselft(gImage);
            	}
            for(Bullet bullet: bulletList) {
            	bullet.paintselft(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot: BotList) {
            	bot.paintselft(gImage);
            }
            for (Wall wall: walllist) {
            	wall. paintselft(gImage) ;
            	}
            for (Base base: baselist) {
            	base. paintselft(gImage) ;
            	}
            for (Blast blast: blastlist) {
            	blast. paintselft(gImage) ;
            	}
            //�ػ����+1
            count++;
            
        }
        if(state == 2){
            gImage.drawString("����������ƶ�",575,510);
            gImage.drawString("K���",575,550);
        }
        else if(state == 3) {
        	gImage.drawString("��Ϸ��ͣ",220,200);
        }	
        else if(state == 4) {
        	gImage.drawString("��Ϸʧ��",220,200);
        }	
        else if(state == 5) {
        	gImage.drawString("��Ϸʤ��",220,200);
        }
       
        /* �����������ƺ�Ŷ��ͼ���������Ƶ������Ļ����� */
        g.drawImage(offScreenImage, 0, 0, null);
    }
    //���̼�����
    private class KeyMonitor extends KeyAdapter {
    	//���¼���
        @Override
        public void keyPressed(KeyEvent e) {
            //���ؼ�ֵ
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    if(!start){
                        y = 150;
                        a = 1;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_2:
                    if(!start){
                        y = 250;
                        a = 2;
                    }
                    //System.out.println(state);
                    break;
                case KeyEvent.VK_ENTER:
                    state = a;
                    //������
                    if(state == 1 && !start){
                        playerList.add(playerOne);
                    }
                    else{
                        playerList.add(playerOne);
                        playerList.add(playerTwo);
                        playerTwo.alive = true;
                    }
                    playerOne.alive = true;
                    
                    start = true;
                    break;
                case KeyEvent.VK_P:
                	if(state !=3) {
                		a = state;
                		state = 3;               		
                	}
                	else {
                		state = a;
                		if(a == 0) {
                			a=1;
                		}
                	}
                	break;
                default:
                	
                    playerOne.keyPressed(e);
                    playerTwo.keyPressed(e);
                    break;
                    
            }
        }
        //�ɿ�����
        @Override
        public void keyReleased(KeyEvent e){
        	
            playerOne.keyReleased(e);
            playerTwo.keyReleased(e);
            
        }
    }
    //main
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }
}
package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JFrame {
	

    /** 定义双缓存图片 */
    Image offScreenImage = null;
    //游戏状态 0 游戏未开始， 1 单人模式， 2 双人模式， 3 游戏暂停， 4 游戏失败， 5 游戏胜利
    int state= 0;
    //游戏是否开始
    private boolean start = false;
    //临时变量
    int a = 1;
    //重绘次数
    int count = 0;
    //已生成敌人数量
    int emenyCount = 0;
  //游戏元素列表
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    ArrayList<Bot> BotList = new ArrayList<Bot>();
    ArrayList<Bullet> removeList = new ArrayList<Bullet>();
    ArrayList<Tank> playerList = new ArrayList<Tank>();
    ArrayList<Wall> walllist = new ArrayList<Wall>();
    ArrayList<Base> baselist = new ArrayList<Base>();
    ArrayList<Blast> blastlist = new ArrayList<Blast>();
    //指针图片
    private Image select = Toolkit.getDefaultToolkit().getImage("images/selecttank.gif");
    //窗口长宽
    int width = 800;
    int height = 610;
    //物体集合
    public List<Tank> tankList = new ArrayList<>();
    
    //指针初始高度
    int y = 150;
    //PlayerOne
    PlayerOne playerOne = new PlayerOne("images/player1/p1tankU.gif", 125, 510,this,
    "images/player1/p1tankU.gif" , "images/player1/p1tankL.gif",
    "images/player1/p1tankR.gif", "images/player1/p1tankD.gif");
    //PlayerTwo
    PlayerTwo playerTwo = new PlayerTwo("images/player2/p2tankU.gif", 625, 510,this,
    "images/player2/p2tankU.gif" , "images/player2/p2tankL.gif",
    "images/player2/p2tankR.gif", "images/player2/p2tankD.gif");
    //基地
    Base base = new Base("images/star.gif",375,570,this);
    //窗口的启动方法
    public void launch(){   			
        //标题
        setTitle("坦克大战");
        //窗口初始大小
        setSize(width, height);
        //使屏幕居中
        setLocationRelativeTo(null);
        //添加关闭事件
        setDefaultCloseOperation(3);
        //用户不能调整大小
        setResizable(false);
        //使窗口可见
        setVisible(true);
        //添加键盘事件
        this.addKeyListener(new GamePanel.KeyMonitor());
      //添加围墙
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
        	//游戏胜利判定
        	if(BotList.size() == 0 && emenyCount == 10) {
        		state = 5;
        	}
        	//游戏失败判定
        	if((playerList.size() == 0 && state == 1 || playerList.size() == 0 && state ==2) || baselist.size() == 0) {
        		state = 4;
        	}        	
        	if(count % 200 == 1 && emenyCount < 30  && state == 1 || count % 200 == 1 && emenyCount < 10 && state == 2) {
        		//添加电脑坦克
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
                //线程休眠  1秒 = 1000毫秒
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    
    @Override
    public void paint(Graphics g) {
    	//System.out.println(bulletList.size());
        //创建和容器一样大小的Image图片
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        //获得该图片的画布
        Graphics gImage= offScreenImage.getGraphics();
        //设置背景颜色
        gImage.setColor(Color.gray);
        //填充整个画布
        gImage.fillRect(0, 0, width, height);
        //挂变画笔颜色
        gImage.setColor(Color.GREEN);
        //改变文字大小和样式
        gImage.setFont(new Font("宋体",Font.PLAIN,50));
        if(state == 0){
            //添加文字
            gImage.drawString("选择游戏模式",220,100);
            gImage.drawString("单人游戏",220,200);
            gImage.drawString("双人游戏",220,300);
            gImage.drawString("按1，2选择模式，按回车开始游戏",0,400);
            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
           gImage.setFont(new Font("仿宋",Font.BOLD,30));
           gImage.setColor(Color.red);
           gImage.drawString("WASD控制移动",0,510);
           gImage.drawString("空格射击",0,550);
           gImage.drawString("剩余敌人："+ BotList.size(),10,60);
            //paint重绘游戏元素
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
            //重绘次数+1
            count++;
            
        }
        if(state == 2){
            gImage.drawString("方向键控制移动",575,510);
            gImage.drawString("K射击",575,550);
        }
        else if(state == 3) {
        	gImage.drawString("游戏暂停",220,200);
        }	
        else if(state == 4) {
        	gImage.drawString("游戏失败",220,200);
        }	
        else if(state == 5) {
        	gImage.drawString("游戏胜利",220,200);
        }
       
        /* 将缓冲区绘制好哦的图形整个绘制到容器的画布中 */
        g.drawImage(offScreenImage, 0, 0, null);
    }
    //键盘监视器
    private class KeyMonitor extends KeyAdapter {
    	//按下键盘
        @Override
        public void keyPressed(KeyEvent e) {
            //返回键值
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
                    //添加玩家
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
        //松开键盘
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
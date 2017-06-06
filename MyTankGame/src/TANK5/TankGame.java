
package TANK5;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.util.*;;

public class TankGame extends JFrame

{ 
	Mypanel mp=null;
	JMenuBar bar=null;
	JMenuItem item1,item2,item3=null;
	JMenu menu1,menu2=null;
	public static void main (String args[ ]) 
    {
		TankGame tankgame=new TankGame();	
		AePlayWave apw=new AePlayWave("./111.wav");
		apw.start();
	} 
	public TankGame()
	{
		mp=new Mypanel();
		bar=new JMenuBar();
		menu1=new JMenu("游戏");
		menu2=new JMenu("关于");
		item1=new JMenuItem("游戏参数");
		item2=new JMenuItem("保存");
		item3=new JMenuItem("继续");
		Thread th1=new Thread(mp);
		th1.start();
		this.setLayout(new BorderLayout());
		this.add(mp);
		bar.add(menu1);
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SetWindow sw=new SetWindow(mp);
				sw.bt1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						for(int i=0;i<sw.vc.size();i++)
						{
							sw.vc.get(i).setSpeed(Integer.valueOf(sw.tx2.getText()).intValue());
						}
						mp.setSp_em=Integer.valueOf(sw.tx2.getText()).intValue();
						mp.emNum=Integer.valueOf(sw.tx1.getText()).intValue();
						mp.vc.removeAllElements();
						for(int i=0;i<mp.emNum;i++)
						{
							Enemy enemy=new Enemy((int)(Math.random()*570), (int)(Math.random()*220));
							mp.vc.add(enemy);
							enemy.setSpeed(mp.setSp_em);
							Thread th=new Thread(enemy);
							th.start();
						}
						mp.hero.setSpeed(Integer.valueOf(sw.tx3.getText()));
						mp.hero.bulletNum=Integer.valueOf(sw.tx4.getText());
						sw.setVisible(false);
					}
				});
				sw.bt2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						sw.setVisible(false);
					}
				});
			}
		});
		item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File f=new File("F:\\JAVA资料\\TankData.txt");
				if(!f.exists())//如果不存在数据记事本则创建一个
				{
					try {
						f.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				FileWriter fr=null;
				BufferedWriter bw=null;
				try {
					fr=new FileWriter("F:\\JAVA资料\\TankData.txt");
					bw=new BufferedWriter(fr);
					bw.write(String.valueOf(mp.emNum)+"\r\n");
					bw.write(String.valueOf(mp.setSp_em)+"\r\n");
					bw.write(String.valueOf(mp.hero.getSpeed())+"\r\n");
					bw.write(String.valueOf(mp.hero.bulletNum)+"\r\n");
					bw.write(String.valueOf(mp.hero.getX())+"\r\n");
					bw.write(String.valueOf(mp.hero.getY())+"\r\n");
					bw.write(String.valueOf(mp.hero.getDir())+"\r\n");
					bw.write(String.valueOf(mp.vc.size())+"\r\n");
					for(int i=0;i<mp.vc.size();i++)
					{
						bw.write(String.valueOf(mp.vc.get(i).getX())+"\r\n");
						bw.write(String.valueOf(mp.vc.get(i).getY())+"\r\n");
						bw.write(String.valueOf(mp.vc.get(i).getDir())+"\r\n");
					}				
				} catch (Exception e2) {
					e2.printStackTrace();// TODO: handle exception
				}finally{
					try {
						bw.close();
					} catch (Exception e3) {
						e3.printStackTrace();// TODO: handle exception
					}
				}
			}
		});
		item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileReader fr=null;// TODO Auto-generated method stub
				BufferedReader br=null;
				try {
					fr=new FileReader("F:\\JAVA资料\\TankData.txt");
					br=new BufferedReader(fr);
					mp.emNum=Integer.valueOf(br.readLine());
					mp.setSp_em=Integer.valueOf(br.readLine());
					for(int j=0;j<mp.vc.size();j++)
					{
						mp.vc.get(j).setSpeed(mp.setSp_em);
					}	
					mp.hero.setSpeed(Integer.valueOf(br.readLine()));
					mp.hero.bulletNum=Integer.valueOf(br.readLine());
					mp.hero.setX(Integer.valueOf(br.readLine()));
					mp.hero.setY(Integer.valueOf(br.readLine()));
					mp.hero.setDir(Integer.valueOf(br.readLine()));
					int num=Integer.valueOf(br.readLine());//保存时画面上存在的敌人数
					mp.vc.removeAllElements();
					for(int i=0;i<mp.emNum;i++)
					{
						Enemy enemy=new Enemy((int)(Math.random()*540), (int)(Math.random()*320));
						enemy.setSpeed(mp.setSp_em);
						mp.vc.add(enemy);
						Thread th=new Thread(enemy);
						th.start();
					}
					for(int i=0;i<num;i++)
					{
						mp.vc.get(i).setX(Integer.valueOf(br.readLine()));
						mp.vc.get(i).setY(Integer.valueOf(br.readLine()));
						mp.vc.get(i).setDir(Integer.valueOf(br.readLine()));
					}
				} catch (Exception e2) {
					e2.printStackTrace();// TODO: handle exception
				}finally{
					try {
						br.close();
					} catch (Exception e3) {
						e3.printStackTrace();// TODO: handle exception
					}
				}
			}
		});
		bar.add(menu2);
		this.add(bar,BorderLayout.NORTH);
		this.setSize(800, 600);
		this.setTitle("坦克大战1.00000");
		this.setLocation(200, 70);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(mp);
	}
	
} 
class SetWindow extends JFrame
{
	JLabel lb1,lb2,lb3,lb4=null;
	JTextField tx1,tx2,tx3,tx4=null;
	JPanel jp1,jp2,jp3,jp4,jp5=null;
	JButton bt1,bt2=null;
	Hero hero=null;
	Vector<Enemy> vc=null;
	public SetWindow(Mypanel mp)
	{   
		this.hero=mp.hero;
		this.vc=mp.vc;
		lb1=new JLabel("敌人数量");
		lb2=new JLabel("敌人速度");
		lb3=new JLabel("玩家速度");
		lb4=new JLabel("玩家弹药数");
		bt1=new JButton("确定");
		bt2=new JButton("取消");
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		tx1=new JTextField(String.valueOf(mp.emNum),3);
		tx2=new JTextField(String.valueOf(vc.get(0).speed),3);
		tx3=new JTextField(String.valueOf(hero.speed),3);
		tx4=new JTextField(String.valueOf(hero.bulletNum),3);
		this.setBounds(300,120,200, 220);
		this.setLayout(new GridLayout(5,1));
		jp1.add(lb1);
		jp1.add(tx1);
		jp2.add(lb2);
		jp2.add(tx2);
		jp3.add(lb3);
		jp3.add(tx3);
		jp4.add(lb4);
		jp4.add(tx4);
		jp5.add(bt1);
		jp5.add(bt2);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.setVisible(true);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
class Mypanel extends JPanel implements KeyListener,Runnable
{
	Hero hero =null;
	Boss boss=null;
	//敌人坦克集合
	Vector<Enemy> vc=new Vector<Enemy>();
	//爆炸效果集合
	Vector<Bomb> bombs=new Vector<Bomb>();	
	//定义爆炸图片
	Image img1=null;
	Image img2=null;
	Image img3=null;
	int emNum=5; //敌人数量
	int setSp_em=2;
	Boolean pause=false;
	int myPoint=0;
	int deadCount=0;
	int protectTime=0;
	Boolean bossFlag=false;
	Boolean protectFlag=true;
	public void paint(Graphics g)
	{		
		super.paint(g);
		g.fillRect(0, 0, 600, 480);
		if(hero.live)
		{
			if(this.protectFlag)
			{
				this.drawProtect(hero.getX(), hero.getY(), g);
			}
			this.drawTank(hero.getX(), hero.getY(), g,hero.getDir(),hero.getColor());
		}
		else
		{
			g.setColor(Color.red);
			g.setFont(new Font("黑体",Font.TRUETYPE_FONT,40));
			g.drawString("You Die!", 210, 240);
			g.setFont(new Font("宋体",Font.BOLD,20));
			g.setColor(Color.CYAN);
			g.drawString("按H键复活     按Esc退出", 170, 340);
		}
			for(int i=0;i<hero.vector.size();i++) //画玩家子弹
			{
					Bullet bl=hero.vector.get(i);
					if(bl!=null&&bl.live)
					this.drawBullet(bl.getX(),bl.getY(), g,Color.white);	
			}
			for(int i=0;i<this.vc.size();i++) //画电脑子弹
			{
				for(int j=0;j<vc.get(i).embu_vc.size();j++)
				{
						Bullet bl=vc.get(i).embu_vc.get(j);
						if(bl!=null&&bl.live)
						this.drawBullet(bl.getX(),bl.getY(), g,Color.yellow);	
				}
			}
		for(int i=0;i<this.vc.size();i++) //画敌人坦克
		{
			Enemy enemy=(Enemy)vc.get(i);
			this.drawTank(enemy.getX(),enemy.getY(), g,enemy.getDir(),enemy.getColor());
		}
		//画统计信息
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString("得分："+String.valueOf(myPoint), 600, 20);
		g.drawString("阵亡："+String.valueOf(deadCount) ,600, 60);
		if(boss!=null)
		{
			g.setColor(Color.red);
			g.drawString("BOSS生命："+String.valueOf(boss.life) ,600, 100);
		}
		else
		{
			g.setColor(Color.red);
			g.drawString("BOSS潜伏中...",600, 100);
		}
		//满足触发条件，画Boss
		if(boss!=null)
		{
			this.drawBoss(this.boss.getX(), this.boss.getY(),this.boss.getDir(),g);
			for(int i=0;i<boss.boss_bu.size();i++) //画Boss子弹
			{
					Bullet bl=boss.boss_bu.get(i);
					if(bl!=null&&bl.live)
					this.drawBullet(bl.getX(),bl.getY(), g,Color.red);	
			}
		}
		//画爆炸效果
		for(int i=0;i<bombs.size();i++)
		{
			Bomb bomb =bombs.get(i);
			if(bomb.existTime>26)
			{
				g.drawImage(img1, bomb.x, bomb.y, 30, 30,this);
			}
			else if(bomb.existTime>13)
			{
				g.drawImage(img2, bomb.x, bomb.y, 30, 30,this);
			}
			else if(bomb.existTime>0)
			{
				g.drawImage(img3, bomb.x, bomb.y, 30, 30,this);
			}
			else
			{
				bombs.remove(i);
			}
				bomb.existTime--;
		}
	}
	public Mypanel()
	{
		hero=new Hero(300,400);
		for(int i=0;i<this.emNum;i++)
		{
			Enemy enemy=new Enemy((int)(Math.random()*570), (int)(Math.random()*220));
			vc.add(enemy);
			Thread th=new Thread(enemy);
			th.start();
		}
		img1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		img2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		img3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	public void drawBullet(int x,int y,Graphics g,Color color)
	{
		g.setColor(color);
		g.fillOval(x, y, 5, 5);
	}
	public void drawProtect(int x,int y,Graphics g)
	{
		g.setColor(Color.white);
		g.drawRect(x-4, y-3, 42, 42);
	}
	public void drawBoss(int x,int y,int dir,Graphics g)
	{
		switch(dir)
		{
		case 0:
		{
			g.setColor(Color.RED);
			g.fillRect(x, y, 15,55);
			g.setColor(new Color(151,0,0));
			g.fill3DRect(x+15,y-30,40,55,true);
			g.setColor(Color.RED);
			g.fillRect(x+55, y, 15,55);
			g.setColor(Color.RED);
			g.fillOval(x+19, y-25, 30, 40);
			g.fillRect(x+32, y-55, 5,50);
			g.fillRect(x+25, y+10, 4,30);
			g.fillRect(x+40, y+10, 4,30);
			break;
		}
		case 1:
		{
			g.setColor(Color.RED);
			g.fillRect(x, y, 15,55);
			g.setColor(new Color(151,0,0));
			g.fill3DRect(x+15,y+30,40,55,true);
			g.setColor(Color.RED);
			g.fillRect(x+55, y, 15,55);
			g.setColor(Color.RED);
			g.fillOval(x+19, y+35, 30, 40);
			g.fillRect(x+32, y+55, 5,50);
			g.fillRect(x+25, y+15, 4,30);
			g.fillRect(x+40, y+15, 4,30);
			break;
		}
		case 2:
			{
				g.setColor(Color.RED);
				g.fillRect(x, y, 55,15);
				g.setColor(new Color(151,0,0));
				g.fill3DRect(x-30,y+15,55,40,true);
				g.setColor(Color.RED);
				g.fillRect(x, y+55, 55,15);
				g.setColor(Color.RED);
				g.fillOval(x-23, y+19, 40, 30);
				g.fillRect(x-55, y+32, 50,5);
				g.fillRect(x+12, y+25, 30,4);
				g.fillRect(x+12, y+40, 30,4);
				break;
			}
		case 3:
			{
				g.setColor(Color.RED);
				g.fillRect(x, y, 55,15);
				g.setColor(new Color(151,0,0));
				g.fill3DRect(x+30,y+15,55,40,true);
				g.setColor(Color.RED);
				g.fillRect(x, y+55, 55,15);
				g.setColor(Color.RED);
				g.fillOval(x+35, y+19, 40, 30);
				g.fillRect(x+55, y+32, 50,5);
				g.fillRect(x+15, y+25, 30,4);
				g.fillRect(x+15, y+40, 30,4);
				break;
			}
		}
	}
	public void drawTank(int x,int y,Graphics g,int dir,Color c) 
	{
		
		switch(dir)
		{
			case 0:
			{
				g.setColor(c);
				g.fillRect(x, y,8,40);
				g.setColor(new Color(34, 177, 76));
				if(c==Color.yellow)
				{
					g.setColor(new Color(238, 167, 47));
				}
				g.fillRect(x+8, y+10, 20, 25);
				g.setColor(c);
				g.fillRect(x+28,y,8,40);
				g.setColor(c);
				g.fillOval(x+13, y+15, 10, 15);
				g.fillRect(x+18, y-10, 2, 28);
				break;
			}
			case 1:
			{
				g.setColor(c);
				g.fillRect(x, y,8,40);
				g.setColor(new Color(34, 177, 76));
				if(c==Color.yellow)
				{
					g.setColor(new Color(238, 167, 47));
				}
				g.fillRect(x+8, y+10, 20, 25);
				g.setColor(c);
				g.fillRect(x+28,y,8,40);
				g.setColor(c);
				g.fillOval(x+13, y+15, 10, 15);
				g.fillRect(x+18, y+16, 2, 37);
				break;
			}
			case 2:
			{
				g.setColor(c);
				g.fillRect(x, y,40,8);
				g.setColor(new Color(34, 177, 76));
				if(c==Color.yellow)
				{
					g.setColor(new Color(238, 167, 47));
				}
				g.fillRect(x+10, y+8, 25, 20);
				g.setColor(c);
				g.fillRect(x,y+28,40,8);
				g.setColor(c);
				g.fillOval(x+15, y+13, 15, 10);
				g.fillRect(x-13, y+18, 30, 2);
				break;
			}
			case 3:
			{
				g.setColor(c);
				g.fillRect(x, y,40,8);
				g.setColor(new Color(34, 177, 76));
				if(c==Color.yellow)
				{
					g.setColor(new Color(238, 167, 47));
				}
				g.fillRect(x+10, y+8, 25, 20);
				g.setColor(c);
				g.fillRect(x,y+28,40,8);
				g.setColor(c);
				g.fillOval(x+15, y+13, 15, 10);
				g.fillRect(x+16, y+18, 37, 2);
				break;
			}
		}
	}
	public void clearDead()//清除失效对象，判断子弹击中坦克等
	{
		for(int i=0;i<hero.vector.size();i++)//判断子弹是否击中敌人
		{
			for(int j=0;j<this.vc.size();j++)
			{
				if((hero.vector.get(i).getX()<=(this.vc.get(j).getX()+36))&&(hero.vector.get(i).getX()>=this.vc.get(j).getX()-5))
				{
					if((hero.vector.get(i).getY()<=(this.vc.get(j).getY()+40))&&(hero.vector.get(i).getY()>=this.vc.get(j).getY()))
					{	
						hero.vector.get(i).live=false;
						Bomb bomb=new Bomb(vc.get(j).getX(),vc.get(j).getY());
						bombs.add(bomb);
						vc.remove(j);
						this.myPoint++;
						if(this.myPoint%5==0&&boss==null)
						{
							this.boss=new Boss((int)(Math.random()*540), (int)(Math.random()*350));
							Thread th=new Thread(boss);
							th.start();
							this.bossFlag=true;
						}
					}
				}
			}			
		}
		for(int i=0;i<vc.size();i++) //判断玩家是否被击中
		{
			for(int j=0;j<vc.get(i).embu_vc.size();j++)
			{
				if((vc.get(i).embu_vc.get(j).getX()<=(hero.getX()+36))&&(vc.get(i).embu_vc.get(j).getX()>=hero.getX()-5))
				{
					if((vc.get(i).embu_vc.get(j).getY()<=(hero.getY()+40))&&(vc.get(i).embu_vc.get(j).getY()>=hero.getY()))
					{	
						vc.get(i).embu_vc.get(j).live=false;
						if(this.protectFlag==false)
						{
							if(hero.live)
							{
								this.deadCount++;
							}
							Bomb bomb= new Bomb(hero.x, hero.y);
							bombs.add(bomb);
							hero.live=false;
						}
					}
				}
			}
		}
		for(int i=0;i<hero.vector.size();i++) //清除玩家的失效子弹
		{
			if(hero.vector.get(i).live==false)
				hero.vector.remove(i);
		}
		if(this.bossFlag&&boss!=null)
		{
			for(int i=0;i<boss.boss_bu.size();i++) //清除Boss的失效子弹,判断boss是否击中玩家
			{									//boss被击中
				if(boss.boss_bu.get(i).live==false)
					boss.boss_bu.remove(i);
			}
			for(int j=0;j<boss.boss_bu.size();j++)
			{
				if((boss.boss_bu.get(j).getX()<=(hero.getX()+36))&&(boss.boss_bu.get(j).getX()>=hero.getX()-5))
				{
					if((boss.boss_bu.get(j).getY()<=(hero.getY()+40))&&(boss.boss_bu.get(j).getY()>=hero.getY()))
					{	
						boss.boss_bu.get(j).live=false;
						if(this.protectFlag==false)
						{
							if(hero.live)//玩家已经阵亡就不再记死亡次数和产生爆炸
							{
								this.deadCount++;
								Bomb bomb= new Bomb(hero.x, hero.y);
								bombs.add(bomb);
							}
							hero.live=false;
						}
					}
				}
			}
			for(int i=0;i<hero.vector.size();i++)
			{
				if(this.bossFlag&&boss!=null)
				{
					if((hero.vector.get(i).getX()<=(boss.getX()+70))&&(hero.vector.get(i).getX()>=boss.getX()-5))
					{
						if((hero.vector.get(i).getY()<=(boss.getY()+60))&&(hero.vector.get(i).getY()>=boss.getY()))
						{
							hero.vector.get(i).live=false;
							hero.vector.remove(i);
							boss.life--;
							this.myPoint++;
							if(boss.life==0)
							{
								for(int n=0;n<10;n++)
								{
									Bomb bomb= new Bomb(boss.x+((int)(Math.random()*50)), boss.y+((int)(Math.random()*50)));
									bombs.add(bomb);
								}
								this.bossFlag=false;
								this.boss=null;
							}
						}
					}
				}
			}
		}
		for(int i=0;i<this.vc.size();i++)//清除电脑的失效子弹
		{
			for(int j=0;j<vc.get(i).embu_vc.size();j++) 
			{
				if(vc.get(i).embu_vc.get(j).live==false)
					vc.get(i).embu_vc.remove(j);
			}
		}
	}
	public void pause() //暂停，继续游戏
	{
		if(this.pause==false)
		{
			for(int i=0;i<this.vc.size();i++)
			{
				vc.get(i).pause=true;
				for(int j=0;j<vc.get(i).embu_vc.size();j++)
				{
					vc.get(i).embu_vc.get(j).pause=true;
				}
			}
			for(int k=0;k<hero.vector.size();k++)
			{
				hero.vector.get(k).pause=true;
			}
			if(this.bossFlag)
			{
				for(int k=0;k<boss.boss_bu.size();k++)
				{
					boss.boss_bu.get(k).pause=true;
				}
				boss.pause=true;
			}
			hero.pause=true;
			this.pause=true;
		}
		else
		{
			for(int i=0;i<this.vc.size();i++)
			{
				vc.get(i).pause=false;
				for(int j=0;j<vc.get(i).embu_vc.size();j++)
				{
					vc.get(i).embu_vc.get(j).pause=false;
				}
			}
			for(int k=0;k<hero.vector.size();k++)
			{
				hero.vector.get(k).pause=false;
			}
			if(this.bossFlag)
			{
				for(int k=0;k<boss.boss_bu.size();k++)
				{
					boss.boss_bu.get(k).pause=false;
				}
				boss.pause=false;
			}
			hero.pause=false;
			this.pause=false;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(hero.live&&hero.pause==false)
		{	
			if(e.getKeyCode()==e.VK_UP)
				{
					hero.y-=hero.speed;
					if(hero.y<10)
						hero.y=10;
					hero.setDir(0);
					
				}
			if(e.getKeyCode()==e.VK_DOWN)
				{
					hero.y+=hero.speed;
					if(hero.y>426)
						hero.y=426;
					hero.setDir(1);
				}
			if(e.getKeyCode()==e.VK_LEFT)
				{
					hero.x-=hero.speed;
					if(hero.x<10)
						hero.x=10;
					hero.setDir(2);
				}
			if(e.getKeyCode()==e.VK_RIGHT)
				{
					hero.x+=hero.speed;
					if(hero.x>546)
						hero.x=546;
					hero.setDir(3);
				}
			if(e.getKeyCode()==e.VK_SPACE)
			{	
				if(hero.vector.size()<hero.bulletNum)
					hero.fire();
			}
			if(e.getKeyCode()==e.VK_F5)
			{	
				pause();
			}
			if(e.getKeyCode()==e.VK_P)
			{	
				this.protectFlag=true;
			}
		}
		else
		{
			if(e.getKeyCode()==e.VK_H)
			{	
				hero.live=true;
				this.protectFlag=true;
			}
			if(e.getKeyCode()==e.VK_ESCAPE)
			{	
				System.exit(0);
			}
			if(e.getKeyCode()==e.VK_F5)
			{	
				pause();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//小于目标敌人数时 ，随机复活敌人坦克
	public void emReborn()
	{
		if((int)(Math.random()*100)==1&&this.pause==false) 
		{
			if(this.vc.size()<this.emNum)
			{
				Enemy enemy=new Enemy((int)(Math.random()*540), (int)(Math.random()*320));
				enemy.setSpeed(setSp_em);
				vc.add(enemy);
				Thread th=new Thread(enemy);
				th.start();
			}
		}
	}
	//判断保护罩失效否
	public void protectJudge()
	{
		if(this.protectFlag)
		{
			protectTime++;
		}
		if(protectTime==300)
		{
			this.protectFlag=false;
			protectTime=0;
		}
	}
	@Override
	public void run() 
	{	
		while(true)
		{	
			this.emReborn();
			this.clearDead();
			this.protectJudge();
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		}
	}
}
class Tank
{
	int x;
	int y;
	int dir;//0上 1下 2左 3右
	int speed=2;
	int life;
	int bulletNum=6; 
	Color color;
	Bullet bullet;
	Boolean live=true;
	Boolean pause=false;
	public Boolean getPause() {
		return pause;
	}
	public void setPause(Boolean pause) {
		this.pause = pause;
	}
	public Tank(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
class Hero extends Tank
{
	Vector<Bullet> vector=new Vector<Bullet>();
	Bullet bl=null;
	public Hero(int x,int y)
	{
		super(x,y);
		this.setColor(Color.green);
		this.setDir(0);
		this.speed=5;
	}
	public void fire()
	{
			switch(this.dir)
			{
				case 0:
				{
					bl=new Bullet(x+15,y+5,0);
					vector.add(bl);
					break;
				}
				case 1:
				{
					bl=new Bullet(x+15,y+30,1);
					vector.add(bl);
					break;
				}
				case 2:
				{
					bl=new Bullet(x+10,y+13,2);
					vector.add(bl);
					break;
				}
				case 3:
				{
					bl=new Bullet(x+28,y+16,3);
					vector.add(bl);
					break;
				}		
			}
			Thread th=new Thread(bl);
			th.start();
	 }
}
class Enemy extends Tank implements Runnable
{
	Vector<Bullet> embu_vc=new Vector<Bullet>();
	public Enemy(int x,int y)
	{
		super(x,y);
		this.setColor(Color.yellow);
		this.setDir((int)(Math.random()*4));
	}
	public void fire()  //开火
	{
		switch(this.dir)
		{
			case 0:
			{
				bullet=new Bullet(x+15,y+5,0);
				embu_vc.add(bullet);
				break;
			}
			case 1:
			{
				bullet=new Bullet(x+15,y+30,1);
				embu_vc.add(bullet);
				break;
			}
			case 2:
			{
				bullet=new Bullet(x+10,y+13,2);
				embu_vc.add(bullet);
				break;
			}
			case 3:
			{
				bullet=new Bullet(x+28,y+16,3);
				embu_vc.add(bullet);
				break;
			}		
		}
		Thread th=new Thread(bullet);
		th.start(); 
	}
	@Override
	public void run() 
	{	
		while(true)
		{
			synchronized(this)
			{
				if(((int)(Math.random()*30))==1&&this.pause==false) //随机改变方向
					this.setDir((int)(Math.random()*3));
				if(((int)(Math.random()*30))==1&&this.pause==false) //随机开火
					this.fire();
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();// TODO: handle exception
				}
				// 让敌方坦克乱跑
				if(this.pause==false)
				{
					if(this.getDir()==0)
						{
							if(y>10)
								this.y-=this.speed;
							else
								this.setDir(1);		
						}
					if(this.getDir()==1)
						{
							if(y<426)
								this.y+=this.speed;
							else
								this.setDir(0);
						}
					if(this.getDir()==2)
						{
							if(x>10)
								this.x-=this.speed;
							else
								this.setDir(3);
						}
					if(this.getDir()==3)
						{	
							if(x<546)
								this.x+=this.speed;
							else
								this.setDir(2);
						}
				}
			}
				
		}
	}
	
}
class Bullet implements Runnable
{
	int speed=8;
	int dir;
	boolean live=true;
	boolean pause=false;
	public Bullet(int x,int y,int dir)
	{
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	int x;
	int y;
	@Override
	public void run() 
	{
		while(true)
	  {	
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		if(this.pause==false)
		{
			if(dir==0)
			{
				if(y>-5)
				y-=this.speed;
				else
					break;
			}
			else if(dir==1)
			{
				if(y<480)
				y+=this.speed;
				else
					break;
			}
			else if(dir==2)
			{
				if(x>-3)
				x-=this.speed;
				else
					break;
			}
			else if(dir==3)
			{
				if(x<600)
				x+=this.speed;
				else
					break;
			}	
		}
	  }	
		this.live=false;
	}
}
class Boss extends Tank implements Runnable
{
	Vector<Bullet> boss_bu=new Vector<Bullet>();
	public Boss(int x,int y)
	{
		super(x,y);
		this.speed=10;
		this.bulletNum=40;
		this.life=10; 
	}
	public void fire()
	{
		switch(this.dir)
		{
			case 0:
			{
				bullet=new Bullet(x+32,y-40,0);
				bullet.speed=14;
				boss_bu.add(bullet);
				break;
			}
			case 1:
			{
				bullet=new Bullet(x+33,y+30,1);
				bullet.speed=14;
				boss_bu.add(bullet);
				break;
			}
			case 2:
			{
				bullet=new Bullet(x-30,y+30,2);
				bullet.speed=14;
				boss_bu.add(bullet);
				break;
			}
			case 3:
			{
				bullet=new Bullet(x+50,y+32,3);
				bullet.speed=14;
				boss_bu.add(bullet);
				break;
			}		
		}
		Thread th=new Thread(bullet);
		th.start(); 
	}
	

	@Override
	public void run() 
	{
		while(true)
		{
			if(((int)(Math.random()*22))==1&&this.pause==false) //随机改变方向
				this.setDir((int)(Math.random()*3));
			if(((int)(Math.random()*5))==1&&this.pause==false) //随机开火
				this.fire();
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
			if(this.pause==false)
			{
				if(this.getDir()==0)
					{
						if(y>30)
							this.y-=this.speed;
						else
							this.setDir(1);		
					}
				if(this.getDir()==1)
					{
						if(y<375)
							this.y+=this.speed;
						else
							this.setDir(0);
					}
				if(this.getDir()==2)
					{
						if(x>50)
							this.x-=this.speed;
						else
							this.setDir(3);
					}
				if(this.getDir()==3)
					{	
						if(x<490)
							this.x+=this.speed;
						else
							this.setDir(2);
					}
			}
		}
	}
}
//播放声音的类
class AePlayWave extends Thread 
{
	private String filename;
	public AePlayWave(String wavfile) 
	{
		filename = wavfile;
	}
	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}
}
//爆炸效果类
class Bomb
{
	int x;
	int y;
	int existTime=50;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
}
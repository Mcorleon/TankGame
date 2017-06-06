
package TANK6;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

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
	} 
	public TankGame()
	{
		mp=new Mypanel();
		bar=new JMenuBar();
		menu1=new JMenu("��Ϸ");
		menu2=new JMenu("����");
		item1=new JMenuItem("��Ϸ����");
		item2=new JMenuItem("����");
		item3=new JMenuItem("����");
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
				File f=new File("F:\\JAVA����\\TankData.txt");
				if(!f.exists())//������������ݼ��±��򴴽�һ��
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
					fr=new FileWriter("F:\\JAVA����\\TankData.txt");
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
					fr=new FileReader("F:\\JAVA����\\TankData.txt");
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
					int num=Integer.valueOf(br.readLine());//����ʱ�����ϴ��ڵĵ�����
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
		this.setTitle("ɽկ̹�˴�ս");
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
		lb1=new JLabel("��������");
		lb2=new JLabel("�����ٶ�");
		lb3=new JLabel("����ٶ�");
		lb4=new JLabel("��ҵ�ҩ��");
		bt1=new JButton("ȷ��");
		bt2=new JButton("ȡ��");
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
	Vector<Enemy> vc=new Vector<Enemy>();
	int emNum=5; //��������
	int setSp_em=2;
	Boolean pause=false;
	public void paint(Graphics g)
	{		
		super.paint(g);
		g.fillRect(0, 0, 600, 480);
		if(hero.live)
		{
			this.drawTank(hero.getX(), hero.getY(), g,hero.getDir(),hero.getColor());
		}
		else
		{
			g.setColor(Color.red);
			g.setFont(new Font("����",Font.HANGING_BASELINE,40));
			g.drawString("You Die!", 210, 240);
			g.setFont(new Font("����",Font.BOLD,20));
			g.setColor(Color.CYAN);
			g.drawString("��H������     ��Esc�˳�", 170, 340);
		}
			for(int i=0;i<hero.vector.size();i++) //������ӵ�
			{
					Bullet bl=hero.vector.get(i);
					if(bl!=null&&bl.live)
					this.drawBullet(bl.getX(),bl.getY(), g);	
			}
			for(int i=0;i<this.vc.size();i++) //�������ӵ�
			{
				for(int j=0;j<vc.get(i).embu_vc.size();j++)
				{
						Bullet bl=vc.get(i).embu_vc.get(j);
						if(bl!=null&&bl.live)
						this.drawBullet(bl.getX(),bl.getY(), g);	
				}
			}
		for(int i=0;i<this.vc.size();i++) //������̹��
		{
			Enemy enemy=(Enemy)vc.get(i);
			this.drawTank(enemy.getX(),enemy.getY(), g,enemy.getDir(),enemy.getColor());
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
		
	}
	public void drawBullet(int x,int y,Graphics g)
	{
		g.setColor(Color.white);
		g.fillOval(x, y, 5, 5);
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
	public void clearDead()//���ʧЧ�����ж��ӵ�����̹�˵�
	{
		for(int i=0;i<hero.vector.size();i++)//�ж��ӵ��Ƿ���е���
		{
			for(int j=0;j<this.vc.size();j++)
			{
				if((hero.vector.get(i).getX()<=(this.vc.get(j).getX()+36))&&(hero.vector.get(i).getX()>=this.vc.get(j).getX()-5))
				{
					if((hero.vector.get(i).getY()<=(this.vc.get(j).getY()+40))&&(hero.vector.get(i).getY()>=this.vc.get(j).getY()))
					{	
						hero.vector.get(i).live=false;
						vc.remove(j);
					}
				}
			}			
		}
		for(int i=0;i<vc.size();i++) //�ж�����Ƿ񱻻���
		{
			for(int j=0;j<vc.get(i).embu_vc.size();j++)
			{
				if((vc.get(i).embu_vc.get(j).getX()<=(hero.getX()+36))&&(vc.get(i).embu_vc.get(j).getX()>=hero.getX()-5))
				{
					if((vc.get(i).embu_vc.get(j).getY()<=(hero.getY()+40))&&(vc.get(i).embu_vc.get(j).getY()>=hero.getY()))
					{	
						vc.get(i).embu_vc.get(j).live=false;
						hero.live=false;
					}
				}
			}
		}
		for(int i=0;i<hero.vector.size();i++) //�����ҵ�ʧЧ�ӵ�
		{
			if(hero.vector.get(i).live==false)
				hero.vector.remove(i);
		}
		for(int i=0;i<this.vc.size();i++)//������Ե�ʧЧ�ӵ�
		{
			for(int j=0;j<vc.get(i).embu_vc.size();j++) 
			{
				if(vc.get(i).embu_vc.get(j).live==false)
					vc.get(i).embu_vc.remove(j);
			}
		}
	}
	public void pause() //��ͣ��������Ϸ
	{
		if(this.pause==false)
		{
			for(int i=0;i<vc.size();i++)
			{
				vc.get(i).setPause(true);
			}	
			this.pause=true;
		}
		else
		{
				for(int i=0;i<vc.size();i++)
				{
					vc.get(i).setPause(false);
					Thread th =new Thread(vc.get(i));
					th.start();
				}	
				this.pause=false;
			
		}

		for(int i=0;i<vc.size();i++)
		{
			//System.out.println(vc.get(i).getPause());
			//System.out.println(this.pause);
		}	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(hero.live)
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
		}
		else
		{
			if(e.getKeyCode()==e.VK_H)
			{	
				hero.live=true;
			}
			if(e.getKeyCode()==e.VK_ESCAPE)
			{	
				System.exit(0);
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
	@Override
	public void run() 
	{	
		while(true)
		{	
			if((int)(Math.random()*100)==1) //С��Ŀ�������ʱ ������������̹��
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
			this.clearDead();
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
			this.repaint();
		}
	}
}
class Tank
{
	int x;
	int y;
	int dir;//0�� 1�� 2�� 3��
	int speed=2;
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
	public void fire()  //����
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
				while(this.pause==true)
				{
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					if(((int)(Math.random()*30))==1) //����ı䷽��
						this.setDir((int)(Math.random()*3));
					if(((int)(Math.random()*30))==1) //�������
						this.fire();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();// TODO: handle exception
					}
					// �õз�̹������
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
class Bullet implements Runnable
{
	int speed=8;
	int dir;
	boolean live=true;
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
		this.live=false;
	}
}


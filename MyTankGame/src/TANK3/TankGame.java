
package TANK3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.util.*;;

public class TankGame extends JFrame

{ 
	Mypanel mp=null;
	public static void main (String args[ ]) 
    {
		TankGame tankgame=new TankGame();		
	} 
	public TankGame()
	{
		mp=new Mypanel();
		Thread th1=new Thread(mp);
		th1.start();
		this.add(mp);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(mp);
	}
	
} 
class Mypanel extends JPanel implements KeyListener,Runnable
{
	Hero hero =null;
	Vector<Enemy> vc=new Vector<Enemy>();
	int emNum=5; //敌人数量
	public void paint(Graphics g)
	{		
		super.paint(g);
		g.fillRect(0, 0, 600, 480);
		this.drawTank(hero.getX(), hero.getY(), g,hero.getDir(),hero.getColor());
			for(int i=0;i<hero.vector.size();i++)
			{
					Bullet bl=hero.vector.get(i);
					if(bl!=null&&bl.live)
					this.drawBullet(bl.getX(),bl.getY(), g);	
			}
		for(int i=0;i<this.vc.size();i++)
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
			Enemy enemy=new Enemy((int)(Math.random()*540), (int)(Math.random()*220));
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
	public void killEnemy()//判断子弹是否击中敌人
	{
		for(int i=0;i<hero.vector.size();i++)
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
	}
	@Override
	public void keyPressed(KeyEvent e) {
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
			if((int)(Math.random()*100)==1)
			{
				if(this.vc.size()<this.emNum)
				{
					Enemy enemy=new Enemy((int)(Math.random()*540), (int)(Math.random()*320));
					vc.add(enemy);
					Thread th=new Thread(enemy);
					th.start();
				}
			}
			this.killEnemy();
			for(int i=0;i<hero.vector.size();i++)
			{
				if(hero.vector.get(i).live==false)
					hero.vector.remove(i);
			}
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
	int dir;//0上 1下 2左 3右
	int speed=3;
	int bulletNum=6; 
	Color color;
	Bullet bullet;
	Boolean live=true;
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
	public Enemy(int x,int y)
	{
		super(x,y);
		this.setColor(Color.yellow);
		this.setDir((int)(Math.random()*4));
	}

	@Override
	public void run() {
		// 让敌方坦克乱跑
		while(true)
		{
			if(((int)(Math.random()*26))==1)
				this.setDir((int)(Math.random()*3));
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
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


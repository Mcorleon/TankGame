
package TANK1;
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
		this.add(mp);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(mp);
	}
	
} 
class Mypanel extends JPanel implements KeyListener
{
	Hero hero =null;
	Vector vc=new Vector();
	int emNum=3; //敌人数量
	public void paint(Graphics g)
	{		
		super.paint(g);
		g.fillRect(0, 0, 600, 480);
		this.drawTank(hero.getX(), hero.getY(), g,hero.getDir(),hero.getColor());
		for(int i=0;i<this.emNum;i++)
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
		this.repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class Tank
{
	int x;
	int y;
	int dir;//0上 1下 2左 3右
	int speed=3;
	Color color;
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
	public Hero(int x,int y)
	{
		super(x,y);
		this.setColor(Color.green);
		this.setDir(0);
	}
}
class Enemy extends Tank
{
	
	public Enemy(int x,int y)
	{
		super(x,y);
		this.setColor(Color.yellow);
		this.setDir((int)(Math.random()*4));
	}
	
}


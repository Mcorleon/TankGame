
package test3;
import java.util.*;
public class FristTest 

{ 
	public static void main (String args[ ]) 

    {
		Tom tom=new Tom();
		Computer com=new Computer();
		Playsystem pl=new Playsystem();
		pl.com=com;
		pl.tom=tom;
		while(true)
		{
			tom.giveNum();
			com.giveNum();
			pl.compare();
			pl.nextGame();
		}
	} 

} 

class Tom
{	
	int chuquan;
	public void giveNum()
	{
		System.out.println("请出拳：0石头  1剪刀  2布");
		Scanner sr=new Scanner(System.in);
		chuquan=sr.nextInt();
		if(chuquan==0)
				System.out.println("你出的是石头");
		else if(chuquan==1)
				System.out.println("你出的是剪刀");
		else if(chuquan==2)
				System.out.println("你出的是布");
		
	}
}

class Computer
{
	int chuquan;
	public void giveNum()
	{
		chuquan=(int)Math.random()*3;
		if(chuquan==0)
			System.out.println("电脑出的是石头");
		if(chuquan==1)
			System.out.println("电脑出的是剪刀");
		if(chuquan==2)
			System.out.println("电脑出的是布");
	}
}

class Playsystem
{
	Tom tom;
	Computer com;
	static int record=1;
	static int win=0;
	public void compare()
	{
		if(tom.chuquan==com.chuquan)
			System.out.println("平局");
		else if((tom.chuquan==0&&com.chuquan==2)||(tom.chuquan==1&&com.chuquan==0))
			System.out.println("你输了");
		else if(tom.chuquan>2)
			System.out.println("你没有出拳");
		else 
			{
				System.out.println("你赢了");
				win++;
		    }
		System.out.println("总共进行"+record+"局，你赢了"+win+"局");
	}
	public void nextGame()
	{
		System.out.println("继续游戏？？  任意键继续  0 退出");
		Scanner sr=new Scanner(System.in);
		if(sr.nextLine().equals("0"))
		{
			System.exit(0);
		}
		else record++;
		
	}
}















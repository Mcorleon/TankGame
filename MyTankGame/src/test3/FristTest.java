
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
		System.out.println("���ȭ��0ʯͷ  1����  2��");
		Scanner sr=new Scanner(System.in);
		chuquan=sr.nextInt();
		if(chuquan==0)
				System.out.println("�������ʯͷ");
		else if(chuquan==1)
				System.out.println("������Ǽ���");
		else if(chuquan==2)
				System.out.println("������ǲ�");
		
	}
}

class Computer
{
	int chuquan;
	public void giveNum()
	{
		chuquan=(int)Math.random()*3;
		if(chuquan==0)
			System.out.println("���Գ�����ʯͷ");
		if(chuquan==1)
			System.out.println("���Գ����Ǽ���");
		if(chuquan==2)
			System.out.println("���Գ����ǲ�");
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
			System.out.println("ƽ��");
		else if((tom.chuquan==0&&com.chuquan==2)||(tom.chuquan==1&&com.chuquan==0))
			System.out.println("������");
		else if(tom.chuquan>2)
			System.out.println("��û�г�ȭ");
		else 
			{
				System.out.println("��Ӯ��");
				win++;
		    }
		System.out.println("�ܹ�����"+record+"�֣���Ӯ��"+win+"��");
	}
	public void nextGame()
	{
		System.out.println("������Ϸ����  ���������  0 �˳�");
		Scanner sr=new Scanner(System.in);
		if(sr.nextLine().equals("0"))
		{
			System.exit(0);
		}
		else record++;
		
	}
}















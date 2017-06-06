
package test4;

import java.util.*;;

public class FristTest 

{ 
	
	public static void main (String args[ ]) 

    {
		Clerk cl1=new Clerk("Tom","6012114041",32,8050);
		Clerk cl2=new Clerk("Jhon","6012114021",51,9050);
		Clerk cl3=new Clerk("Lina","6012114049",42,3250);
		Clerk cl4=new Clerk("Sally","6012114011",22,7250);
		Clerk cl5=new Clerk("Eason","6012114013",52,3250);
		ClerkSystem cs=new ClerkSystem();
		cs.addClerk(cl1);
		cs.addClerk(cl2);
		cs.addClerk(cl3);
		cs.addClerk(cl4);
		cs.addClerk(cl5);
		while(true)
		{
			cs.menu();
			
		}
	} 

} 

class Clerk
{	
	String num;
	float salary;
	int age;
	String name;
	public Clerk(String name,String num,int age,float salary)
	{
		this.name=name;
		this.num=num;
		this.salary=salary;
		this.age=age;
	}
}

class ClerkSystem
{
	ArrayList al=new ArrayList();
	 ArrayList rank_al=new ArrayList();
	public void addClerk(Clerk cl)
	{
		al.add(cl);
	}
	public void removeClk(Clerk clk)
	{
		al.remove(clk);
	}
	public void menu()
	{
		String no;
		System.out.println("**********欢迎进入员工管理系统**********");
		System.out.println("1.查询员工信息");
		System.out.println("2.显示所有员工信息");
		System.out.println("3.增加新员工");
		System.out.println("4.修改员工薪水");
		System.out.println("5.删除离职员工");
		System.out.println("6.员工薪水排序");
		Scanner sr=new Scanner(System.in);
		no=sr.nextLine();
		switch(no)
		{
			case "1":
			{
				searchInfo();
				break;
			}
			case "2":
			{
				for(int i=0;i<al.size();i++)
				{
					Clerk clerk=(Clerk)al.get(i);
					showInfo(clerk);
				}
				break;
			}
			case "3":
			{
				System.out.println("请输入姓名");
				String name=sr.nextLine();
				System.out.println("请输入员工号");
				String num=sr.nextLine();
				System.out.println("请输入年龄");
				int age=sr.nextInt();
				System.out.println("请输入薪水");
				float salary=sr.nextFloat();				
				Clerk clerk=new Clerk(name,num,age,salary);
				addClerk(clerk);
				break;
			}
			case "4":
			{
				System.out.println("请输入员工号");
				no=sr.nextLine();
				for(int i=0;i<al.size();i++)
				{
					Clerk clerk=(Clerk)al.get(i);
					if(no.equals(clerk.num))
					{
						System.out.println("请输入修改薪水");
						float sal=sr.nextFloat();
						clerk.salary=sal;
						System.out.println("修改完成！");
						break;
					}					
				}
				System.out.println("不存在该员工");
				break;
			}
			case "5":
			{
				System.out.println("请输入员工号");
				no=sr.nextLine();
				for(int i=0;i<al.size();i++)
				{
					Clerk clerk=(Clerk)al.get(i);
					if(no.equals(clerk.num))
					al.remove(clerk);
				}
				System.out.println("不存在该员工");
				break;
			}
			case "6":
			{
				salaryRank();
				break;
			}
		}
	}

	public void searchInfo()
	{
		System.out.println("请输入员工号");
		Scanner sr=new Scanner(System.in);
		String no;
		no=sr.nextLine();
		for(int i=0;i<al.size();i++)
		{
			Clerk clerk=(Clerk)al.get(i);
			if(no.equals(clerk.num))
			{
				showInfo(clerk);
				break;
			}
		}
		System.out.println("不存在该员工");
		
	}
	public void showInfo(Clerk cl)
	{
		System.out.println("姓名："+cl.name+" "+"员工号："+cl.num+" "+"年龄："+cl.age+" "+"薪水："+cl.salary);
//		System.out.println("员工号："+cl.num);
//		System.out.println("年龄："+cl.age);
//		System.out.println("薪水："+cl.salary);
//		System.out.println("-----------------");
	}
	
	public void salaryRank()
	{   
		float temp;
		float sal[]=new float[al.size()];
		//建立工资数组sal[]
		for(int i=0;i<al.size();i++)
		{
			Clerk clk =(Clerk)al.get(i);
			sal[i]=clk.salary;
		}
		for(int i=0;i<al.size()-1;i++)
		{
			for(int j=0;j<al.size()-i-1;j++)
			{
				if(sal[j]>=sal[j+1])
				{
					temp=sal[j];
					sal[j]=sal[j+1];
					sal[j+1]=temp;
				}
			}
		}
		
			
		for(int i=0;i<al.size();i++)
		{
			//System.out.println(sal[i]);
			for(int k=0;k<al.size();k++)
			{
				if(i!=0&&(sal[i]==sal[i-1]))
				break;	
				Clerk clerk=(Clerk)al.get(k);
				if(clerk.salary==sal[i])
					showInfo(clerk);
			}
		}
	}
}
















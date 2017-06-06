
package test2;
public class FristTest 

{ 
	public static void main (String args[ ]) 

    {
		for(int j=1;j<=9;j++)
		{
			Printline ln =new Printline();
			ln.a=j;
			ln.showLine();
			System.out.println();
		}
	} 

} 

class Printline
{	
	int a;
	public void showLine()
	{
		for(int i=1;i<=9;i++)
		{	
			if(i*a<10)
			System.out.print(a+"*"+i+"="+a*i +"   ");
			else
		    System.out.print(a+"*"+i+"="+a*i +"  ");
		}
	}
}

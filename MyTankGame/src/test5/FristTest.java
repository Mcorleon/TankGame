
package test5;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;;

public class FristTest extends JFrame

{ 
	JPanel jp1,jp2,jp3=null;
	JLabel lb1,lb2=null;
	JTextField tx1,tx2=null;
	JButton bt1,bt2=null;
	JPasswordField pw;
	public static void main (String args[ ]) 
    {
		FristTest test=new FristTest();
		
	} 
	public FristTest()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		lb1=new JLabel("用户名");
		lb2=new JLabel("密码");
		tx1=new JTextField(15);
		tx2=new JTextField(15);
		bt1=new JButton("确认");
		bt2=new JButton("取消");
		bt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		pw=new JPasswordField(15);
		jp1.add(lb1);
		jp1.add(tx1);
		jp2.add(lb2);
		jp2.add(pw);
		jp3.add(bt1);
		jp3.add(bt2);
		this.setTitle("用户管理系统");
		this.setBounds(500, 200, 300,200 );
		this.setLayout(new GridLayout(3,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setVisible(true);
	}
} 


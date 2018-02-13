package com.hms.applet;

import javax.swing.JApplet;
import javax.swing.JPanel;

import com.hotelmanagement.MainPage;

public class Test extends JApplet
{
	//empty constructor
	public Test()
	{
		System.out.println("Java");
	}
	//single variable constructor
	public Test(String s)
	{
		System.out.println(s);
	}
	//method
	public void method()
	{
		System.out.println("hie am from method");
	}
	//init
	public void init()
	{
		 		 System.out.println("Applet initializing");
		 		 getContentPane().add(new panel());
		method();
		new MainPage();
		}
		//start
 	public void start()
		{
				System.out.println("Applet starting");
		}
		//stop
		public void stop()
		{
				System.out.println("Applet stopping");
		}
		//destroy
		public void destroy()
		{
				System.out.println("Applet destroyed");
		}
	//Panel
	public class panel extends JPanel
	{
		public panel()
		{
			//this is where the diplay items go
		}
	}
//	public  static void main(String[] args)
//	{
//		JFrame frame = new JFrame("Frame");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Test   t = new Test();
//		t.init();				// simulate browser call(1)	
//		frame.setSize(400,600);   		// Set the size of the frame
//		frame.setVisible(true);   		// Show the frame 
//	}
}

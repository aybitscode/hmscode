package com.hotelmanagement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Settings extends JDialog implements ChangeListener {

	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	public static JTabbedPane jtb;
	MainPage mpg;
	static JFrame df=new JFrame();
	public Settings(MainPage mpg) {
		super(df, "Project SetUp", true);	
		this.mpg=mpg;
		setSize(680,480);
		setLocation(mpg.scrwidth-682,100);
		setResizable(false);
		jtb=new JTabbedPane(JTabbedPane.TOP);
		add(jtb);
		
		jtb.addTab("Font",new SFont(mpg));
		//jtb.addTab("Layout",null);
		jtb.addTab("Colors",null);
		jtb.addTab("Look & Feel",null);
		jtb.addChangeListener(this);
		setVisible(true);	
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jtb)
		{
			if(jtb.getSelectedIndex()==0)
			{
				jtb.setComponentAt(0, new SFont(mpg));
			}
//			else if(jtb.getSelectedIndex()==1)
//			{
//				jtb.setComponentAt(1,new SLayout(mpg));
//			}
			else if(jtb.getSelectedIndex()==1)
			{
				jtb.setComponentAt(1, new SetColor());
			}
			else if(jtb.getSelectedIndex()==2)
			{
				jtb.setComponentAt(2,new SLookFeel());
			}
		}
	}


}

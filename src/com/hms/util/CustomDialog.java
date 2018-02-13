package com.hms.util;

import java.awt.Component;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CustomDialog extends JOptionPane {
	JDialog dlg;
	Point p;
	public static URL ERROR_ICON = CustomDialog.class.getResource("/images/dialog/error_small.png");
	public static URL INFO_ICON = CustomDialog.class.getResource("/images/dialog/info_small.png");
	public CustomDialog(Component c, String msg, String title, Component rel, int xPixels, int yPixels, URL url)
	{
		super(msg);	
		dlg = this.createDialog(c, title);
		dlg.setLocationRelativeTo(rel);
		p = dlg.getLocation();
		dlg.setLocation(p.x + xPixels, p.y + yPixels);
		this.setIcon(new ImageIcon(url));
		//dlg.setResizable(true);
		dlg.pack();
		dlg.setVisible(true);
		
	}

}

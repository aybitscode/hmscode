package com.hotelmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.DateDifferenceCalculator;
import com.hms.viewhandler.ViewHandler;

public class MainFrame {
	static java.util.Date currentDate = new java.util.Date();
	static Connection con = DBConnection.getDBConnection();
	MainPage mpg;
	public MainFrame(MainPage mpg)
	{
		this.mpg = mpg;
		
	}
	public void setVisible()
	{
		
		int days = (int) diffDays();
		if(days > 5)
		{
			 Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
			 Preferences prefs1 = Preferences.userRoot().node(Constants.PRE_RESOURCE_MYSQL);
			if(prefs.get("DefaultValue", "").equals("-1") || prefs1.get("DefaultValue", "").equals("-1"))
			{ 
				RFPanel obj =  new RFPanel("Trial Version Has Been Expired");
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
				RFPanel.setExVisible(true);				
			}
			else
			{
//				Thread t=new Thread(){
//					public void run()
//					{
//						new Login();
//					}
//					};
//					t.start();
				//mpg.triggerLogin();   Modify according to expirty
			}		
			
		}
		else
		{
//			Thread t=new Thread(){
//				public void run()
//				{
//					new Login();
//				}
//				};
//				t.start();			
			//mpg.triggerLogin();      Modify according to expirty
		}
	}
	public static long diffDays()
	{
		java.util.Date date = null;			
			try {
				PreparedStatement pst = con.prepareStatement(DatabaseConstants.CREATE_TABLE);
				ResultSet rs = pst.executeQuery();
				if(rs.next())
					date = new java.util.Date(rs.getDate(1).getTime());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return DateDifferenceCalculator.calculateDifference(date, currentDate);
	}
	
	
}

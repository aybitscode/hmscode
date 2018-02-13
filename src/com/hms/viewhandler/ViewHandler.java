package com.hms.viewhandler;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hms.view.CustomerEntry;

public class ViewHandler {
	public static void mouseEnteredJLabel(JLabel objLabel)
	{
		objLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		objLabel.setBackground(new Color(44, 174, 186));
	}
	public static void mouseExitedJLabel(JLabel objLabel)
	{
		objLabel.setBackground(new Color(50, 197, 210));
	}
	public static void updateDashBoard(Object obj, JPanel dashBoardContainer, GridBagConstraints gbc_bodyPanel, JPanel sliderMenu, GridBagConstraints gbc_sliderMenu)
	{
		dashBoardContainer.removeAll();
		dashBoardContainer.add(sliderMenu, gbc_sliderMenu);
		dashBoardContainer.add((Component) obj, gbc_bodyPanel);
		dashBoardContainer.updateUI();
	}

}

package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Search extends JPanel implements ChangeListener{

	/**
	 * Create the panel.
	 */
	JTabbedPane jtbsrch;
	MainPage mpg;
	public Search(MainPage mpg) {
		this.mpg=mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtbsrch = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(jtbsrch, gbc_tabbedPane);
		jtbsrch.addTab("Transaction Details", new TransactionSrch(mpg));
		jtbsrch.addTab("Student Details", null);
		jtbsrch.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize-2));
		jtbsrch.setMnemonicAt(0, KeyEvent.VK_N);
		jtbsrch.setMnemonicAt(1, KeyEvent.VK_U);
		jtbsrch.addChangeListener(this);
		srchbkColor();
		srchtbColor();

	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(jtbsrch.getSelectedIndex()==0)
		{
			jtbsrch.setComponentAt(0, new TransactionSrch(mpg));
		}
		else if(jtbsrch.getSelectedIndex()==1)
		{
			//Runtime r = Runtime.getRuntime();
			//r.freeMemory();
			//r.gc();
			//r.freeMemory();
			jtbsrch.setComponentAt(1, new StudentSrch(mpg));
		}
	}

	public void srchbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
	}
	public void srchtbColor()
	{
		jtbsrch.setForeground(new Color(SetColor.tbColor));
	}

}

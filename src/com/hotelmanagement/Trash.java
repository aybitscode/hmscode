package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Trash extends JPanel implements ChangeListener{

	/**
	 * Create the panel.
	 */
	JTabbedPane jtbt;
	MainPage mpg;
	public Trash(MainPage mpg) {
		this.mpg=mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtbt = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(jtbt, gbc_tabbedPane);
		jtbt.addTab("Transaction Records", new TransactionTrash(mpg));
		jtbt.addTab("Student Records", null);
		jtbt.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize-2));
		jtbt.setMnemonicAt(0, KeyEvent.VK_N);
		jtbt.setMnemonicAt(1, KeyEvent.VK_U);
		jtbt.addChangeListener(this);
		tshbkColor();
		tshtbColor();
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(jtbt.getSelectedIndex()==0)
		{
			//try {
				//mpg.data.con.close();
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			jtbt.setComponentAt(0, new TransactionTrash(mpg));
		
		}
		else if(jtbt.getSelectedIndex()==1)
		{
			//try {
				//mpg.data.con.close();
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			
			jtbt.setComponentAt(1, new StudentTrash(mpg));
		}
	}

   	public void tshbkColor()
   	{
   		setBackground(new Color(SetColor.bkColor));
   	}
	public void tshtbColor()
	{
		jtbt.setForeground(new Color(SetColor.tbColor));
	}

}

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


public class DataBas extends JPanel implements ChangeListener {

	/**
	 * Create the panel.
	 */
	JTabbedPane stj;
	MainPage mpg;
	public DataBas(MainPage mpg) {
		this.mpg=mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		stj= new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(stj, gbc_tabbedPane);
		stj.addTab("Transaction Database", new TransactionDatabase(mpg));
		stj.addTab("Student Database", null);
		stj.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize-2));
		stj.setMnemonicAt(0, KeyEvent.VK_N);
		stj.setMnemonicAt(1, KeyEvent.VK_U);
		stj.addChangeListener(this);
		dbsbkColor();
		dbstbColor();
		
	}

	public void dbsbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
	}
	public void dbstbColor()
	{
		stj.setForeground(new Color(SetColor.tbColor));
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(stj.getSelectedIndex()==0)
		{
			//try {
				//mpg.data.con.close();
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			stj.setComponentAt(0, new TransactionDatabase(mpg));
		}
		else if(stj.getSelectedIndex()==1)
		{
			//try {
				//mpg.data.con.close();
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			stj.setComponentAt(1, new StudentDatabase(mpg));
		}
	}


}

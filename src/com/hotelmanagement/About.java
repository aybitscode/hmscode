package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
public class About extends JPanel {

	/**
	 * 
	 */
	/**
	 * Create the panel.
	 */
	JLabel label_2;

	public About() {
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("The Software is designed by...");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 4;
		gbc_label.gridy = 8;
		
		JLabel lblPvtLtd = new JLabel(" Pvt Ltd.");
		lblPvtLtd.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
		lblPvtLtd.setForeground(Color.ORANGE);
		GridBagConstraints gbc_lblPvtLtd = new GridBagConstraints();
		gbc_lblPvtLtd.insets = new Insets(0, 0, 5, 0);
		gbc_lblPvtLtd.gridx = 4;
		gbc_lblPvtLtd.gridy = 10;
		//add(lblPvtLtd, gbc_lblPvtLtd);
		
		JLabel label_3 = new JLabel("for Management of Al-Ameen Institute Of Information Sciences");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 11;
		
		label_2 = new JLabel("Empowered by");
		label_2.setForeground(Color.ORANGE);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 1;
		add(label_2, gbc_label_2);
		
		JLabel lblMcaStudents = new JLabel("Ecstasy Business Solutions India");
		lblMcaStudents.setForeground(Color.WHITE);
		lblMcaStudents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblMcaStudents = new GridBagConstraints();
		gbc_lblMcaStudents.insets = new Insets(0, 0, 5, 5);
		gbc_lblMcaStudents.gridx = 1;
		gbc_lblMcaStudents.gridy = 2;
		add(lblMcaStudents, gbc_lblMcaStudents);
		abtbkColor();
		abtmtColor();
		abtSTFont(SFont.stFType, SFont.stfProp, SFont.stSize);

	}
	public void abtbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
	}
	public void abtmtColor()
	{
		label_2.setForeground(new Color(SetColor.mtColor));
	}
	public void abtSTFont(String stFType,int stfProp,int stSize)
	{
	}

}

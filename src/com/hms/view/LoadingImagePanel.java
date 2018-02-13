package com.hms.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hotelmanagement.SetColor;

public class LoadingImagePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoadingImagePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblLoadingicon = new JLabel("");
		GridBagConstraints gbc_lblLoadingicon = new GridBagConstraints();
		gbc_lblLoadingicon.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoadingicon.gridx = 1;
		gbc_lblLoadingicon.gridy = 1;
		add(lblLoadingicon, gbc_lblLoadingicon);
		lblLoadingicon.setIcon(new ImageIcon(SingleLeftPanel.class.getResource("/images/loading_small.gif")));

		setBackground(new Color(SetColor.bkColor));
		
	}

}

package com.hms.learning;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.hms.util.JPlaceholderTextField;

public class DemoLabel extends JFrame {

	private JPanel contentPane;
	private JPlaceholderTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoLabel frame = new DemoLabel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DemoLabel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDemo = new JLabel("Demo");
		GridBagConstraints gbc_lblDemo = new GridBagConstraints();
		gbc_lblDemo.insets = new Insets(0, 0, 5, 0);
		gbc_lblDemo.gridx = 0;
		gbc_lblDemo.gridy = 0;
		contentPane.add(lblDemo, gbc_lblDemo);
		//EmptyBorder border = new EmptyBorder(5,15,5,15);
		lblDemo.setOpaque(true);
		//lblDemo.setBackground(Color.red);
		//lblDemo.setBorder(border);
        Border border = BorderFactory.createLineBorder(Color.blue);
        lblDemo.setBorder(border);
		
		textField = new JPlaceholderTextField("Username");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setFont(new Font("Open Sans", Font.BOLD, 28));
	}

}

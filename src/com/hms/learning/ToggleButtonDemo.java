package com.hms.learning;

import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ToggleButtonDemo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToggleButtonDemo frame = new ToggleButtonDemo();
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
	public ToggleButtonDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
		
		JToggleButton tglbtnDemo = new JToggleButton();
		tglbtnDemo.setMargin(new Insets(0,0,0,0));
		tglbtnDemo.setBorder(new EmptyBorder(0,0,0,0));
		tglbtnDemo.setIcon(new ImageIcon("C:/HotelManagement/boot/images/booked.png"));
		contentPane.add(tglbtnDemo, "cell 0 0");
	}

}

package com.hms.learning;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hms.validators.IntegerValidator;
import javax.swing.JComboBox;

public class Demo extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEtnerOnlyText;
	private JTextField textField_1;
	private JButton btnSubmit;
	private JLabel lblEnterText;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JLabel lblCobo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo frame = new Demo();
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
	public Demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblEnterOnlyNumber = new JLabel("Enter only number");
		GridBagConstraints gbc_lblEnterOnlyNumber = new GridBagConstraints();
		gbc_lblEnterOnlyNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterOnlyNumber.anchor = GridBagConstraints.EAST;
		gbc_lblEnterOnlyNumber.gridx = 0;
		gbc_lblEnterOnlyNumber.gridy = 0;
		contentPane.add(lblEnterOnlyNumber, gbc_lblEnterOnlyNumber);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		//textField.setInputVerifier(new MyInputVerifier());
		//textField.setInputVerifier(new NotEmptyValidator((JDialog) getParent(), textField, "Field cannot be null."));
		//textField.setInputVerifier(new MyNumericVerifier());
		
		lblEtnerOnlyText = new JLabel("Etner only text");
		GridBagConstraints gbc_lblEtnerOnlyText = new GridBagConstraints();
		gbc_lblEtnerOnlyText.anchor = GridBagConstraints.EAST;
		gbc_lblEtnerOnlyText.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtnerOnlyText.gridx = 0;
		gbc_lblEtnerOnlyText.gridy = 1;
		contentPane.add(lblEtnerOnlyText, gbc_lblEtnerOnlyText);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		//textField_1.setInputVerifier(new NotEmptyValidator((JDialog) getParent(), textField_1, "Field cannot be null."));
		textField_1.setInputVerifier(new NumericInputVerifier());
		
		lblEnterText = new JLabel("enter text");
		GridBagConstraints gbc_lblEnterText = new GridBagConstraints();
		gbc_lblEnterText.anchor = GridBagConstraints.EAST;
		gbc_lblEnterText.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterText.gridx = 0;
		gbc_lblEnterText.gridy = 2;
		contentPane.add(lblEnterText, gbc_lblEnterText);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		//textField_2.setInputVerifier(new NotEmptyValidator((JDialog) getParent(), textField_2, "Field cannot be null."));
		textField_2.setInputVerifier(new IntegerValidator((JDialog) getParent(), textField_2, "Enter only numbers."));
		textField_2.setInputVerifier(new IntegerValidator(null, textField_2, "Enter only numbers"));
		lblCobo = new JLabel("cobo");
		GridBagConstraints gbc_lblCobo = new GridBagConstraints();
		gbc_lblCobo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCobo.anchor = GridBagConstraints.EAST;
		gbc_lblCobo.gridx = 0;
		gbc_lblCobo.gridy = 3;
		contentPane.add(lblCobo, gbc_lblCobo);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		contentPane.add(comboBox, gbc_comboBox);
		//comboBox.setInputVerifier(new MyNumericVerifier());
		//comboBox.setInputVerifier(new NotEmptyValidator((JDialog) getParent(), comboBox, "Field cannot be null."));
		
		
		btnSubmit = new JButton("submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 4;
		contentPane.add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("the textfield data is"+textField.getText());
	}

}

package keygen;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class KeyGen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyGen frame = new KeyGen();
					frame.setVisible(true);
					frame.pack();
					frame.setLocation(500,300);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JLabel lblKey;
	public KeyGen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblKeyGenerator = new JLabel("Key Generator");
		lblKeyGenerator.setForeground(Color.WHITE);
		lblKeyGenerator.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblKeyGenerator = new GridBagConstraints();
		gbc_lblKeyGenerator.gridwidth = 2;
		gbc_lblKeyGenerator.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeyGenerator.gridx = 0;
		gbc_lblKeyGenerator.gridy = 0;
		contentPane.add(lblKeyGenerator, gbc_lblKeyGenerator);
		
		JLabel lblProductId = new JLabel("Product Id");
		lblProductId.setForeground(Color.WHITE);
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProductId = new GridBagConstraints();
		gbc_lblProductId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProductId.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductId.gridx = 0;
		gbc_lblProductId.gridy = 2;
		contentPane.add(lblProductId, gbc_lblProductId);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblKey = new JLabel();
		lblKey.setForeground(Color.WHITE);
		lblKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblKey = new GridBagConstraints();
		gbc_lblKey.gridx = 1;
		gbc_lblKey.gridy = 6;
		contentPane.add(lblKey, gbc_lblKey);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		textField_1 = new JPasswordField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_1.getText().equals("Mohammed"))
				{
				int chk=9999,chk2=9919;
				Calendar now=Calendar.getInstance();
				int mm=now.get(Calendar.MONTH);
				mm=mm+1;
				int calmn=777*(mm);
				chk=chk+Integer.parseInt(textField.getText());
				chk2=chk2+Integer.parseInt(textField.getText());
				int key1=chk-calmn;
				int key2=chk2-calmn;
				lblKey.setText(""+key2+"  "+key1);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Enter the password correctly", "!!Security Check",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.anchor = GridBagConstraints.WEST;
		gbc_btnGenerate.insets = new Insets(0, 0, 5, 0);
		gbc_btnGenerate.gridx = 1;
		gbc_btnGenerate.gridy = 4;
		contentPane.add(btnGenerate, gbc_btnGenerate);
		
		JLabel lblProductKey = new JLabel("Product Key");
		lblProductKey.setForeground(Color.WHITE);
		lblProductKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblProductKey = new GridBagConstraints();
		gbc_lblProductKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblProductKey.insets = new Insets(0, 0, 0, 5);
		gbc_lblProductKey.gridx = 0;
		gbc_lblProductKey.gridy = 6;
		contentPane.add(lblProductKey, gbc_lblProductKey);
		

	}

}

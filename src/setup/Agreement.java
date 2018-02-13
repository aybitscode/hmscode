package setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Agreement extends JDialog implements ActionListener {
String fn;
JButton button,button_1,button_2;
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	public Agreement() {
		super(new JFrame(), "Project SetUp", true);	
		fn=SetUp.absolutePath+"/license.txt";
		setSize(500, 385);
		setLocation(d.width/3,d.height/4);	
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblLicenseAgreement = new JLabel(" License Agreement ");
		GridBagConstraints gbc_lblLicenseAgreement = new GridBagConstraints();
		gbc_lblLicenseAgreement.anchor = GridBagConstraints.WEST;
		gbc_lblLicenseAgreement.gridwidth = 3;
		gbc_lblLicenseAgreement.insets = new Insets(0, 0, 5, 0);
		gbc_lblLicenseAgreement.gridx = 0;
		gbc_lblLicenseAgreement.gridy = 0;
		panel.add(lblLicenseAgreement, gbc_lblLicenseAgreement);
		lblLicenseAgreement.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblPleaseReviewThe = new JLabel(" Please review the license terms before installing the software.");
		GridBagConstraints gbc_lblPleaseReviewThe = new GridBagConstraints();
		gbc_lblPleaseReviewThe.anchor = GridBagConstraints.WEST;
		gbc_lblPleaseReviewThe.gridwidth = 3;
		gbc_lblPleaseReviewThe.insets = new Insets(0, 0, 0, 5);
		gbc_lblPleaseReviewThe.gridx = 0;
		gbc_lblPleaseReviewThe.gridy = 1;
		panel.add(lblPleaseReviewThe, gbc_lblPleaseReviewThe);
		lblPleaseReviewThe.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel lblPressPageDown = new JLabel(" Press page down to see the rest of the agreement.");
		lblPressPageDown.setFont(new Font("Calibri", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPressPageDown = new GridBagConstraints();
		gbc_lblPressPageDown.anchor = GridBagConstraints.WEST;
		gbc_lblPressPageDown.gridwidth = 3;
		gbc_lblPressPageDown.insets = new Insets(0, 0, 5, 0);
		gbc_lblPressPageDown.gridx = 0;
		gbc_lblPressPageDown.gridy = 1;
		getContentPane().add(lblPressPageDown, gbc_lblPressPageDown);
		
		TextArea textArea = new TextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblYouAreNow = new JLabel("          You are now aware of your rights. Click next to continue.");
		lblYouAreNow.setFont(new Font("Calibri", Font.PLAIN, 13));
		GridBagConstraints gbc_lblYouAreNow = new GridBagConstraints();
		gbc_lblYouAreNow.anchor = GridBagConstraints.WEST;
		gbc_lblYouAreNow.gridwidth = 3;
		gbc_lblYouAreNow.insets = new Insets(0, 0, 5, 0);
		gbc_lblYouAreNow.gridx = 0;
		gbc_lblYouAreNow.gridy = 3;
		getContentPane().add(lblYouAreNow, gbc_lblYouAreNow);
		
		button = new JButton("   Back   ");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		gbc_button.gridx = 0;
		gbc_button.gridy = 5;
		getContentPane().add(button, gbc_button);
		
		button_1 = new JButton("   Next   ");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 5;
		getContentPane().add(button_1, gbc_button_1);
		
		button_2 = new JButton("Cancel");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 0);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 5;
		getContentPane().add(button_2, gbc_button_2);

		try
		{
			File f=new File(fn);
		FileInputStream fs=new FileInputStream(f);
		byte b[]=new byte[fs.available()];
		fs.read(b);
		textArea.setText(new String(b));
		
		JLabel label = new JLabel("   ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 6;
		getContentPane().add(label, gbc_label);
		fs.close();
		}catch(Exception ea){System.out.println(ea);}
		button.addActionListener(this);
		button_1.addActionListener(this);
		button_2.addActionListener(this);

			setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==button_1)
		{
			dispose();
			new Register();
			//new Install();

		}
		if(arg0.getSource()==button)
		{
		new SetUp();
		dispose();

		}
		if(arg0.getSource()==button_2)
		{
		if(JOptionPane.showConfirmDialog(null,"Do you wish to cancel setup?","choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		System.exit(0);
		}
	}

}

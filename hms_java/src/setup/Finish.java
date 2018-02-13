package setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.hotelmanagement.MainPage;

public class Finish extends JDialog implements ActionListener {
	
	String fn,absolutePath;
	private JPanel contentPane;
	JButton button_1;
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon icon;
	JCheckBox chckbxLaunchApplication,chckbxDesktopShortcut;
	int clp=1,cds=1;
	MainPage mpg;
	DesktopS sc;
	
	public Finish() {
		super(new JFrame(),"Hotel Management Project Setup",true);
  		 absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
 		 absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setSize(500, 385);
		setLocation(d.width/3,d.height/4);	
		setResizable(false);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridheight = 8;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;

		
		JPanel panel_2 = new JPanel();


				
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(128, 128, 128));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridheight = 9;
		gbc_label.insets = new Insets(0, 0, 0, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		label.setIcon(new ImageIcon(SetUp.class.getResource("/images/setup.png")));
		
		JLabel lblNewLabel = new JLabel("Completing the project");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblSetupWizard = new JLabel("Setup Wizard");
		GridBagConstraints gbc_lblSetupWizard = new GridBagConstraints();
		gbc_lblSetupWizard.anchor = GridBagConstraints.WEST;
		gbc_lblSetupWizard.insets = new Insets(0, 0, 5, 0);
		gbc_lblSetupWizard.gridx = 1;
		gbc_lblSetupWizard.gridy = 1;
		panel_1.add(lblSetupWizard, gbc_lblSetupWizard);
		lblSetupWizard.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel("    ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		panel_1.add(label_1, gbc_label_1);
		
		JLabel lblThisWizardWill = new JLabel("The project files have been installed successfully.");
		GridBagConstraints gbc_lblThisWizardWill = new GridBagConstraints();
		gbc_lblThisWizardWill.anchor = GridBagConstraints.WEST;
		gbc_lblThisWizardWill.insets = new Insets(0, 0, 5, 0);
		gbc_lblThisWizardWill.gridx = 1;
		gbc_lblThisWizardWill.gridy = 3;
		panel_1.add(lblThisWizardWill, gbc_lblThisWizardWill);
		lblThisWizardWill.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Click finish to close the setup wizard.");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel label_4 = new JLabel("    ");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 5;
		panel_1.add(label_4, gbc_label_4);
		
		chckbxDesktopShortcut = new JCheckBox("Desktop Shortcut");
		chckbxDesktopShortcut.setSelected(true);
		chckbxDesktopShortcut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxDesktopShortcut.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxDesktopShortcut = new GridBagConstraints();
		gbc_chckbxDesktopShortcut.anchor = GridBagConstraints.WEST;
		gbc_chckbxDesktopShortcut.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDesktopShortcut.gridx = 1;
		gbc_chckbxDesktopShortcut.gridy = 6;
		panel_1.add(chckbxDesktopShortcut, gbc_chckbxDesktopShortcut);
		chckbxDesktopShortcut.setEnabled(false);
		
		chckbxLaunchApplication = new JCheckBox("Launch Application");
		chckbxLaunchApplication.setSelected(true);
		chckbxLaunchApplication.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxLaunchApplication.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxLaunchApplication = new GridBagConstraints();
		gbc_chckbxLaunchApplication.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxLaunchApplication.gridx = 1;
		gbc_chckbxLaunchApplication.gridy = 7;
		panel_1.add(chckbxLaunchApplication, gbc_chckbxLaunchApplication);
		chckbxLaunchApplication.setEnabled(false);
		
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 25, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label_3 = new JLabel("    ");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 1;
		panel.add(label_3, gbc_label_3);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 1;
		panel.add(btnCancel, gbc_btnCancel);
		btnCancel.setEnabled(false);
		
		button_1 = new JButton("Finish");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.anchor = GridBagConstraints.SOUTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 1;
		panel.add(button_1, gbc_button_1);
		
		JLabel label_2 = new JLabel("          ");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 3;
		gbc_label_2.gridy = 1;
		panel.add(label_2, gbc_label_2);
		button_1.addActionListener(this);
		chckbxDesktopShortcut.addActionListener(this);
		chckbxLaunchApplication.addActionListener(this);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==button_1)
		{
			if(cds==1&&clp==1)
			{
		        Thread worker = new Thread() {
		            public void run() {
		              try {
		                Thread.sleep(2000);
		              } catch (InterruptedException ex) {
		              }

		              // Report the result using invokeLater().
		              SwingUtilities.invokeLater(new Runnable() {
		                public void run() {		
				
								sc=new DesktopS();
								sc.createDesktopShortcut();
								new MainPage();
								
		                }
		              });
		            }
		          };
		          worker.start();
		          dispose();
			}
	 
		 
		}
		else if(arg0.getSource()==chckbxLaunchApplication)
		{
			if(chckbxLaunchApplication.isSelected())
				clp=1;
			else
				clp=0;
		}
		else if(arg0.getSource()==chckbxDesktopShortcut)
		{
			if(chckbxDesktopShortcut.isSelected())
				cds=1;
			else 
				cds=0;
		}
	}

}

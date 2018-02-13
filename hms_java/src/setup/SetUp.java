package setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SetUp extends JFrame implements ActionListener {
	
	public static String fn,absolutePath;
	private JPanel contentPane;
	JButton button,button_1;
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	ImageIcon icon;
	UIManager uim;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
					SetUp frame = new SetUp();
					frame.setSize(500, 385);
					frame.setLocation(d.width/3,d.height/4);	
					frame.setVisible(true);
	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 * 
	 */
	public SetUp() {
		uim=new UIManager();
		try {
		uim.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
  		 absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
 		 absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
 		 setSize(d.width/3+50,d.height/2);
 		 setLocation(d.width/3,d.height/4);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridheight = 7;
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
		
	
		JLabel lblNewLabel = new JLabel("Hotel Management ");
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
		
		JLabel lblThisWizardWill = new JLabel("This Wizard will help your through the ");
		GridBagConstraints gbc_lblThisWizardWill = new GridBagConstraints();
		gbc_lblThisWizardWill.anchor = GridBagConstraints.WEST;
		gbc_lblThisWizardWill.insets = new Insets(0, 0, 5, 0);
		gbc_lblThisWizardWill.gridx = 1;
		gbc_lblThisWizardWill.gridy = 2;
		panel_1.add(lblThisWizardWill, gbc_lblThisWizardWill);
		lblThisWizardWill.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1 = new JLabel("installation of the Project Files.");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel label_1 = new JLabel("                                      ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 4;
		panel_1.add(label_1, gbc_label_1);
		
		JLabel lblNewLabel_2 = new JLabel("It is recomended that you close all the other");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 5;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		JLabel lblApplicationsBeforeStarting = new JLabel("applications before starting Setup.");
		GridBagConstraints gbc_lblApplicationsBeforeStarting = new GridBagConstraints();
		gbc_lblApplicationsBeforeStarting.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblApplicationsBeforeStarting.gridx = 1;
		gbc_lblApplicationsBeforeStarting.gridy = 6;
		panel_1.add(lblApplicationsBeforeStarting, gbc_lblApplicationsBeforeStarting);
		lblApplicationsBeforeStarting.setFont(new Font("Calibri", Font.PLAIN, 13));
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{55, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 25, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_3 = new JLabel("        ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		button = new JButton("    Next    ");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.SOUTH;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		panel.add(button, gbc_button);
		
		button_1 = new JButton("Cancel");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.SOUTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 1;
		panel.add(button_1, gbc_button_1);
		button.addActionListener(this);
		button_1.addActionListener(this);
		setVisible(true);
	}
	public  void GetExecutionPath(){
	    absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	    absolutePath = absolutePath.substring(1, absolutePath.lastIndexOf("/"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==button)
		{
		
			GetExecutionPath();
		dispose();
		new Agreement();

		}
		if(arg0.getSource()==button_1)
		{
		if(JOptionPane.showConfirmDialog(null,"Do you wish to cancel setup?","choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		System.exit(0);
		}
	}

}

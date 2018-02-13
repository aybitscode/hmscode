package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.hms.util.DBConnection;


public class FeeReceipt extends JDialog implements FocusListener,Printable,ActionListener,ItemListener {

	private Panel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	JButton button,btnCance;
	JLabel lblStudentId,lblNewLabel_5,lblNewLabel_7,lblNewLabel_1,lblNewLabel_2,label_6;
	JLabel label_9,lblCash,label_14,label_4,label_15,lblFeeReceipt,label_11,label,lblName,lblNewLabel,lbl1,lbl2,lbl3,lbl4,label_2  ;
	static String date;
	ButtonGroup bg;
	JRadioButton rdbtnCash,rdbtnByCheque;
	int state=0;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblamountInWords;
	private JTextField textField_3;
	Statement st;
	ResultSet rs;

	String id,name,course,datee;
	int uid,pamt,bal;
	private JLabel lblReceiptNo;
	private JLabel label_5;
	private JLabel lblNewLabel_3;
	MainPage mpg;
	Connection con = DBConnection.getDBConnection();
	public FeeReceipt(MainPage mpg) {
			this.mpg=mpg;

		try{
			
			PreparedStatement pst= con.prepareStatement("select uid from payments where id=? and pdate=? and amtpd=?");
			pst.setString(1,Payments.fid);
			pst.setDate(2, Payments.fdate);
			pst.setFloat(3, Payments.famt);
			rs=pst.executeQuery();
			int l=0;
			while(rs.next())
			{
				uid=rs.getInt("uid");
			}
		}catch(Exception ee){JOptionPane.showMessageDialog(this, ""+ee,"Error!!",JOptionPane.ERROR_MESSAGE);}
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		lbl1=new JLabel();
		lbl2=new JLabel();
		lbl3=new JLabel();
		lbl4=new JLabel();
		bg=new ButtonGroup();
		getContentPane().setLayout(null);
		setBackground(SystemColor.inactiveCaption);
		setSize(650,550);
		setLocation(100,100);
		contentPane = new Panel();
		contentPane.setBounds(10,10,625,525);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 105, 183, 104, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{27, 28, 24, 19, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\imgs\\coll.png"));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.gridheight = 5;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 0;
		contentPane.add(label_1, gbc_label_1);
		
		JLabel lblAlAmeenInstitute = new JLabel(" Al Ameen Institute Of Information Sciences");
		lblAlAmeenInstitute.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblAlAmeenInstitute = new GridBagConstraints();
		gbc_lblAlAmeenInstitute.anchor = GridBagConstraints.WEST;
		gbc_lblAlAmeenInstitute.gridwidth = 4;
		gbc_lblAlAmeenInstitute.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlAmeenInstitute.gridx = 2;
		gbc_lblAlAmeenInstitute.gridy = 0;
		contentPane.add(lblAlAmeenInstitute, gbc_lblAlAmeenInstitute);
		
		JLabel lblAffilatedToBangalore = new JLabel("                  Affilated to Bangalore University");
		lblAffilatedToBangalore.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAffilatedToBangalore = new GridBagConstraints();
		gbc_lblAffilatedToBangalore.anchor = GridBagConstraints.WEST;
		gbc_lblAffilatedToBangalore.gridwidth = 4;
		gbc_lblAffilatedToBangalore.insets = new Insets(0, 0, 5, 5);
		gbc_lblAffilatedToBangalore.gridx = 2;
		gbc_lblAffilatedToBangalore.gridy = 1;
		contentPane.add(lblAffilatedToBangalore, gbc_lblAffilatedToBangalore);
		
		JLabel lblHosurRoadOpp = new JLabel(" Hosur Road, Opp. Lalbagh Main Gate, Bangalore - 560 027.");
		lblHosurRoadOpp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblHosurRoadOpp = new GridBagConstraints();
		gbc_lblHosurRoadOpp.fill = GridBagConstraints.VERTICAL;
		gbc_lblHosurRoadOpp.anchor = GridBagConstraints.WEST;
		gbc_lblHosurRoadOpp.gridwidth = 4;
		gbc_lblHosurRoadOpp.insets = new Insets(0, 0, 5, 5);
		gbc_lblHosurRoadOpp.gridx = 2;
		gbc_lblHosurRoadOpp.gridy = 2;
		contentPane.add(lblHosurRoadOpp, gbc_lblHosurRoadOpp);
		contentPane.setBackground(Color.WHITE);
		getContentPane().add(contentPane);
		
		lblFeeReceipt = new JLabel("Fee Receipt");
		lblFeeReceipt.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblFeeReceipt = new GridBagConstraints();
		gbc_lblFeeReceipt.gridwidth = 3;
		gbc_lblFeeReceipt.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeeReceipt.gridx = 2;
		gbc_lblFeeReceipt.gridy = 4;
		contentPane.add(lblFeeReceipt, gbc_lblFeeReceipt);
		
		label_11 = new JLabel("        ");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 5;
		gbc_label_11.gridy = 4;
		contentPane.add(label_11, gbc_label_11);
		
		label_5 = new JLabel("            ");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 2;
		gbc_label_5.gridy = 5;
		contentPane.add(label_5, gbc_label_5);
		
		JLabel lblReceipt = new JLabel("  Date");
		GridBagConstraints gbc_lblReceipt = new GridBagConstraints();
		gbc_lblReceipt.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblReceipt.insets = new Insets(0, 0, 5, 5);
		gbc_lblReceipt.gridx = 1;
		gbc_lblReceipt.gridy = 6;
		contentPane.add(lblReceipt, gbc_lblReceipt);
		
	 	SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		String sdate = sdf.format(Payments.fdate);
		label = new JLabel(""+sdate);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 6;
		contentPane.add(label, gbc_label);
		
		lblReceiptNo = new JLabel("Receipt No");
		GridBagConstraints gbc_lblReceiptNo = new GridBagConstraints();
		gbc_lblReceiptNo.anchor = GridBagConstraints.WEST;
		gbc_lblReceiptNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblReceiptNo.gridx = 4;
		gbc_lblReceiptNo.gridy = 6;
		contentPane.add(lblReceiptNo, gbc_lblReceiptNo);
		
		 label_2 = new JLabel("");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 5;
		gbc_label_2.gridy = 6;
		contentPane.add(label_2, gbc_label_2);
		
		lblNewLabel_3 = new JLabel("             ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 6;
		gbc_lblNewLabel_3.gridy = 8;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblName = new JLabel("  Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 9;
		contentPane.add(lblName, gbc_lblName);
		
//		lblNewLabel = new JLabel(mpg.pmt.fname);
//		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
//		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
//		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
//		gbc_lblNewLabel.gridx = 2;
//		gbc_lblNewLabel.gridy = 9;
//		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblStudentId = new GridBagConstraints();
		gbc_lblStudentId.anchor = GridBagConstraints.WEST;
		gbc_lblStudentId.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentId.gridx = 4;
		gbc_lblStudentId.gridy = 9;
		contentPane.add(lblStudentId, gbc_lblStudentId);
		
//		lblNewLabel_5 = new JLabel(mpg.pmt.fid);
//		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
//		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
//		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
//		gbc_lblNewLabel_5.gridx = 5;
//		gbc_lblNewLabel_5.gridy = 9;
//		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblCourse = new JLabel("  Course");
		lblCourse.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCourse = new GridBagConstraints();
		gbc_lblCourse.anchor = GridBagConstraints.WEST;
		gbc_lblCourse.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourse.gridx = 1;
		gbc_lblCourse.gridy = 10;
		contentPane.add(lblCourse, gbc_lblCourse);
		
//		lblNewLabel_1 = new JLabel(mpg.pmt.fcourse);
//		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
//		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
//		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
//		gbc_lblNewLabel_1.gridx = 2;
//		gbc_lblNewLabel_1.gridy = 10;
//		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblSession = new JLabel("  Academic Year");
		lblSession.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblSession = new GridBagConstraints();
		gbc_lblSession.anchor = GridBagConstraints.WEST;
		gbc_lblSession.insets = new Insets(0, 0, 5, 5);
		gbc_lblSession.gridx = 1;
		gbc_lblSession.gridy = 11;
		contentPane.add(lblSession, gbc_lblSession);
		
//		lblNewLabel_2 = new JLabel(mpg.pmt.facademic);
//		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
//		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
//		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
//		gbc_lblNewLabel_2.gridx = 2;
//		gbc_lblNewLabel_2.gridy = 11;
//		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel label_12 = new JLabel("  PaymentMode");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.WEST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 1;
		gbc_label_12.gridy = 12;
		contentPane.add(label_12, gbc_label_12);
		
		rdbtnCash = new JRadioButton("By Cash");
		rdbtnCash.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnCash = new GridBagConstraints();
		gbc_rdbtnCash.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCash.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCash.gridx = 1;
		gbc_rdbtnCash.gridy = 13;
		contentPane.add(rdbtnCash, gbc_rdbtnCash);
		
		lblCash = new JLabel("CASH");
		lblCash.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCash = new GridBagConstraints();
		gbc_lblCash.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCash.insets = new Insets(0, 0, 5, 5);
		gbc_lblCash.gridx = 2;
		gbc_lblCash.gridy = 13;
		contentPane.add(lblCash, gbc_lblCash);
		
		rdbtnByCheque = new JRadioButton("By Cheque");
		rdbtnByCheque.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_rdbtnByCheque = new GridBagConstraints();
		gbc_rdbtnByCheque.anchor = GridBagConstraints.WEST;
		gbc_rdbtnByCheque.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnByCheque.gridx = 1;
		gbc_rdbtnByCheque.gridy = 14;
		contentPane.add(rdbtnByCheque, gbc_rdbtnByCheque);
		bg.add(rdbtnCash);
		bg.add(rdbtnByCheque);
		
		label_14 = new JLabel("Cheque/DDnumber");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.anchor = GridBagConstraints.WEST;
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.gridx = 2;
		gbc_label_14.gridy = 14;
		contentPane.add(label_14, gbc_label_14);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 14;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		
		label_4 = new JLabel("Cheque/DD Date");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 15;
		contentPane.add(label_4, gbc_label_4);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 15;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		
		label_15 = new JLabel("Bank");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.anchor = GridBagConstraints.WEST;
		gbc_label_15.insets = new Insets(0, 0, 5, 5);
		gbc_label_15.gridx = 2;
		gbc_label_15.gridy = 16;
		contentPane.add(label_15, gbc_label_15);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 16;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		Label label_3 = new Label("  Particular                                                       ");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBackground(Color.CYAN);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_3.gridwidth = 3;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 17;
		contentPane.add(label_3, gbc_label_3);
		
		Label label_10 = new Label("Amount");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_10.setBackground(Color.CYAN);
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 4;
		gbc_label_10.gridy = 17;
		contentPane.add(label_10, gbc_label_10);
		
		JLabel lblTotalFee = new JLabel("Tution Fee");
		lblTotalFee.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblTotalFee = new GridBagConstraints();
		gbc_lblTotalFee.anchor = GridBagConstraints.WEST;
		gbc_lblTotalFee.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalFee.gridx = 1;
		gbc_lblTotalFee.gridy = 18;
		contentPane.add(lblTotalFee, gbc_lblTotalFee);
		
//		label_6 = new JLabel(mpg.pmt.famtpd);
//		GridBagConstraints gbc_label_6 = new GridBagConstraints();
//		gbc_label_6.insets = new Insets(0, 0, 5, 5);
//		gbc_label_6.gridx = 4;
//		gbc_label_6.gridy = 18;
//		contentPane.add(label_6, gbc_label_6);
		
		lblamountInWords = new JLabel("(Amount in words)");
		lblamountInWords.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblamountInWords = new GridBagConstraints();
		gbc_lblamountInWords.anchor = GridBagConstraints.WEST;
		gbc_lblamountInWords.insets = new Insets(0, 0, 5, 5);
		gbc_lblamountInWords.gridx = 1;
		gbc_lblamountInWords.gridy = 19;
		contentPane.add(lblamountInWords, gbc_lblamountInWords);
		
		textField_3 = new JTextField();
		textField_3.setText("RUPEES ONLY");
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 19;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
	
		
		btnCance = new JButton("Cancel");
		GridBagConstraints gbc_btnCance = new GridBagConstraints();
		gbc_btnCance.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCance.insets = new Insets(0, 0, 5, 5);
		gbc_btnCance.gridx = 5;
		gbc_btnCance.gridy = 26;
		contentPane.add(btnCance, gbc_btnCance);
		
		label_9 = new JLabel("       ");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 2;
		gbc_label_9.gridy = 25;
		contentPane.add(label_9, gbc_label_9);
		
		JLabel lblSignature = new JLabel("Signature");
		lblSignature.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblSignature = new GridBagConstraints();
		gbc_lblSignature.anchor = GridBagConstraints.WEST;
		gbc_lblSignature.insets = new Insets(0, 0, 0, 5);
		gbc_lblSignature.gridx = 3;
		gbc_lblSignature.gridy = 27;
		contentPane.add(lblSignature, gbc_lblSignature);
		
		button = new JButton("Print");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 4;
		gbc_button.gridy = 26;
		contentPane.add(button, gbc_button);
		contentPane.add(lbl1,gbc_textField);
		contentPane.add(lbl2,gbc_textField_1);
		contentPane.add(lbl3,gbc_textField_2);
		contentPane.add(lbl4, gbc_textField_3);

		lblCash.setVisible(false);
		lbl1.setVisible(false);
		lbl2.setVisible(false);
		lbl3.setVisible(false);
		lbl4.setVisible(false);
		lbl4.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
				lbl4.setVisible(false);
				textField_3.setVisible(true);
			}
		});
		label_2.setText(Integer.toString(uid));
		setVisibility(false);
		button.setMnemonic(KeyEvent.VK_P);
		btnCance.setMnemonic(KeyEvent.VK_C);
		rdbtnCash.addItemListener(this);
		rdbtnByCheque.addItemListener(this);
		button.addActionListener(this);
		btnCance.addActionListener(this);
		textField.addFocusListener(this);
		textField_1.addFocusListener(this);
		textField_2.addFocusListener(this);
		textField_3.addFocusListener(this);
		setVisible(true);
	}
	public void setTextt()
	{
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
	}
	public void setVisibility(boolean b)
	{
		label_4.setVisible(b);
		label_14.setVisible(b);
		label_15.setVisible(b);

		textField.setVisible(b);
		textField_1.setVisible(b);
		textField_2.setVisible(b);
	
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		((JTextField)arg0.getSource()).selectAll();
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==textField)
		{
			String s=textField.getText().trim();
			textField.setVisible(false);
			lbl1.setVisible(true);
			lbl1.setText(s.toUpperCase());
			
		}
		if(arg0.getSource()==textField_1)
		{
			String s=textField_1.getText().trim();
			textField_1.setVisible(false);
			lbl2.setVisible(true);
			lbl2.setText(s.toUpperCase());
			
		}
		if(arg0.getSource()==textField_2)
		{
			String s=textField_2.getText().trim();
			textField_2.setVisible(false);
			lbl3.setVisible(true);
			lbl3.setText(s.toUpperCase());
			
		}
		if(arg0.getSource()==textField_3)
		{
			String s=textField_3.getText().trim();
			textField_3.setVisible(false);
			lbl4.setVisible(true);
			lbl4.setText(s.toUpperCase());
			
		}
	
				
	}
	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		// TODO Auto-generated method stub

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        printAll(g);
        return PAGE_EXISTS;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==button)
		{
		button.setVisible(false);
		btnCance.setVisible(false);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
        }
        setVisible(false);
		}
		if(arg0.getSource()==btnCance)
		{
			setVisible(false);
		}
      }
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()== rdbtnCash)
		{
			lblCash.setVisible(true);
			setVisibility(false);
			lbl1.setVisible(false);
			lbl2.setVisible(false);
			lbl3.setVisible(false);
			setTextt();
		}
		if(arg0.getSource()==rdbtnByCheque)
		{
			setVisibility(true);
			lblCash.setVisible(false);
		}
	}
}

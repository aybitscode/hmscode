package com.hms.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.CustomerController;
import com.hms.model.Customer;
import com.hms.util.Constants;
import com.hms.util.CustomDialog;
import com.hms.util.DatabaseConstants;
import com.hms.util.ScrollUtil;
import com.hms.util.SearchBoxModel;
import com.hms.util.ViewConstants;
import com.hms.validators.EmailValidator;
import com.hms.validators.MobileValidator;
import com.hms.validators.StringValidator;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

public class CustomerEntry extends JPanel implements ActionListener,FocusListener, MouseListener {
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private JTextField text_customerID;
	public static String customer_ID;
	private JTextField text_firstName;
    private JLabel lblName;
    private JLabel lblCity;
	private JTextField text_city;
	private JButton btnSave;
	private JLabel  lblCustomerId;
	private JButton btnCancel_1;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JTextField text_lastName;
	private JTextField text_email;
	private JLabel lblMobileNo;
	private JTextField text_mobile;
	JTextArea text_address;
	private JPanel componentContainer;
	private JLabel lblAdrs;
	private JLabel lblGender;
	private ButtonGroup bg;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;

	JButton btnSearch;

	private CustomerController customer_controller;
	public static SearchBoxModel sbm_consignCom;
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
		

	List<JTextField> text_list;
	private JLabel lblViewAll;
	private JLabel lblCustomerDetails;
	private JCheckBox chckbxNew;
	GridBagConstraints gbc_componentContainer;
	JPanel searchPanel;
	JComboBox comboSearch; 
	JButton btnViewAll;
	JLabel lblCustomerMobile;
	MainPage mpg;
	String customerId;

	public CustomerEntry(MainPage mpg){
		this.mpg = mpg;
		customer_controller = new CustomerController();
		System.out.println("hi this is test");
		System.out.println("hi this new is test");
		bg = new ButtonGroup();
		text_list = new ArrayList<JTextField>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblCustomerDetails = new JLabel("Customer Details");
		GridBagConstraints gbc_lblCustomerDetails = new GridBagConstraints();
		gbc_lblCustomerDetails.gridwidth = 3;
		gbc_lblCustomerDetails.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails.gridx = 0;
		gbc_lblCustomerDetails.gridy = 1;
		add(lblCustomerDetails, gbc_lblCustomerDetails);
		lblCustomerDetails.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblCustomerDetails.setForeground(new Color(50, 197, 210));
		
		chckbxNew = new JCheckBox("New Customer");
		GridBagConstraints gbc_chckbxNew = new GridBagConstraints();
		gbc_chckbxNew.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNew.gridx = 1;
		gbc_chckbxNew.gridy = 2;
		add(chckbxNew, gbc_chckbxNew);
		chckbxNew.addActionListener(this);
		chckbxNew.setSelected(true);
		
		
		
		gbc_componentContainer = new GridBagConstraints();
		gbc_componentContainer.fill = GridBagConstraints.BOTH;
		gbc_componentContainer.insets = new Insets(0, 0, 5, 5);
		gbc_componentContainer.gridx = 1;
		gbc_componentContainer.gridy = 3;
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new MigLayout("", "[79px][150][150][]", "[35px][35px]"));
		searchPanel.setBackground(new Color(SetColor.bkColor));
		
		
		lblCustomerMobile = new JLabel("Customer Mobile");
		searchPanel.add(lblCustomerMobile, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom = new SearchBoxModel(comboSearch, DatabaseConstants.CUSTOMER_MOBILE);
		comboSearch.setModel(sbm_consignCom);
		comboSearch.addItemListener(sbm_consignCom);
		comboSearch.addPopupMenuListener(sbm_consignCom); 
		searchPanel.add(comboSearch,  "cell 1 0 2 1,grow");
		
		comboSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	customer_controller.populateCustomerList();
		            }
		            else
		            {		    
		            	btnSave.setText(Constants.UPDATE);
		            	lblViewAll.setVisible(true);
		            	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
		            	setData(customer);
						remove(searchPanel);	
						sbm_consignCom.cb.setSelectedItem("");
						add(componentContainer, gbc_componentContainer);
						updateUI();
		            }
		            	
		        }
		    }
		});
		
		btnSearch = new JButton("Search");
		searchPanel.add(btnSearch, "cell 1 1,grow"); 
		btnSearch.addActionListener(this);
		
		 btnViewAll = new JButton("View All");
		 searchPanel.add(btnViewAll, "cell 2 1,grow");
		 btnViewAll.addActionListener(this);
		
		lblViewAll = new JLabel("View All");
		GridBagConstraints gbc_lblViewAll = new GridBagConstraints();
		gbc_lblViewAll.anchor = GridBagConstraints.EAST;
		gbc_lblViewAll.insets = new Insets(10, 10, 10, 10);
		gbc_lblViewAll.gridx = 2;
		gbc_lblViewAll.gridy = 2;
		//add(lblViewAll, gbc_lblViewAll);
		lblViewAll.setBorder(new EmptyBorder(5,10,5,10));
		lblViewAll.setOpaque(true);
		lblViewAll.setBackground(new Color(50, 197, 210));
		lblViewAll.setForeground(Color.white);
		lblViewAll.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblViewAll.addMouseListener(this);
		lblViewAll.setVisible(false);
		
 
		
 
		//add(customerSearchPanel, gbc_customerContainer);
		
		componentContainer = new JPanel();
		add(componentContainer, gbc_componentContainer);
		componentContainer.setLayout(new MigLayout("", "[1px][145px][5px][150px]", "[35px][10][35px][10][35px][10][35px][10][35px][10][35px][10][50px][][]"));
		lblCustomerId = new JLabel("Customer ID  ");
		GridBagConstraints gbc_lblCustomerId = new GridBagConstraints();
		gbc_lblCustomerId.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerId.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerId.gridx = 0;
		gbc_lblCustomerId.gridy = 2;
		//panel.add(lblCustomerId, gbc_lblCustomerId);
		
		text_customerID = new JTextField();
		GridBagConstraints gbc_text_customerID = new GridBagConstraints();
		gbc_text_customerID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_customerID.gridwidth = 2;
		gbc_text_customerID.insets = new Insets(0, 0, 5, 0);
		gbc_text_customerID.gridx = 2;
		gbc_text_customerID.gridy = 2;
		//panel.add(text_customerID, gbc_text_customerID);
		text_customerID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_customerID.setColumns(10);
		
//		text_customerID.setText(cs.generateCustomerId());
//		text_customerID.setEditable(false);
		
		lblName = new JLabel("First Name");
		componentContainer.add(lblName, "cell 0 0,alignx left,aligny center");
		
		text_firstName = new JTextField();
		componentContainer.add(text_firstName, "cell 1 0 3 1,grow");
		text_firstName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_firstName.setColumns(10);
		text_firstName.setInputVerifier(new StringValidator(null, text_firstName, "Enter only text values"));
		text_list.add(text_firstName);
		
		lblLastName = new JLabel("Last Name");
		componentContainer.add(lblLastName, "cell 0 2,growx,aligny center");
		
		text_lastName = new JTextField();
		componentContainer.add(text_lastName, "cell 1 2 3 1,grow");
		text_lastName.setColumns(10);
		text_lastName.setInputVerifier(new StringValidator(null, text_lastName, "Enter only text values"));
		text_list.add(text_lastName);
		
		lblGender = new JLabel("Gender");
		componentContainer.add(lblGender, "cell 0 4,alignx left,aligny center");
		
		rdbtnMale = new JRadioButton("Male");
		componentContainer.add(rdbtnMale, "cell 1 4,grow");
		
		rdbtnFemale = new JRadioButton("Female");
		componentContainer.add(rdbtnFemale, "cell 3 4,grow");
		
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);
		
		lblEmail = new JLabel("E-mail");
		componentContainer.add(lblEmail, "cell 0 6,growx,aligny center");
		
		text_email = new JTextField();
		componentContainer.add(text_email, "cell 1 6 3 1,grow");
		text_email.setColumns(10);
		text_email.setInputVerifier(new EmailValidator((JDialog) getParent(), text_email, "Enter correct email address"));
		
		lblMobileNo = new JLabel("Mobile No");
		componentContainer.add(lblMobileNo, "cell 0 8,growx,aligny center");
		
		text_mobile = new JTextField();
		componentContainer.add(text_mobile, "cell 1 8 3 1,grow");
		text_mobile.setColumns(10);
		text_mobile.setInputVerifier(new MobileValidator((JDialog) getParent(), text_mobile, "Enter 10-digit mobile number"));
		text_list.add(text_mobile);
	
		
		lblCity = new JLabel("City");
		componentContainer.add(lblCity, "cell 0 10,alignx left,aligny center");
		
		text_city = new JTextField();
		componentContainer.add(text_city, "cell 1 10 3 1,grow");
		text_city.setInputVerifier(new StringValidator(null, text_city, "Enter only text values"));
		text_list.add(text_city);
	
		lblAdrs = new JLabel("Address");
		componentContainer.add(lblAdrs, "cell 0 12,alignx left,aligny top");		
		
		text_address = new JTextArea(2,1);
		JScrollPane spane = new JScrollPane(text_address);
		componentContainer.add(spane, "cell 1 12 3 1,grow");
		text_address.setLineWrap(true);
		text_address.setWrapStyleWord(true);
		
		

		
 
	 
	 
 
		
		btnSave = new JButton(Constants.SUBMIT);
		componentContainer.add(btnSave, "cell 1 14,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		componentContainer.add(btnCancel_1, "cell 3 14,grow");
		btnCancel_1.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		
		
		btnCancel_1.addActionListener(this);
		text_mobile.addFocusListener(this);
		text_email.addFocusListener(this);
		text_lastName.addFocusListener(this);
		text_firstName.addFocusListener(this);
		//text_customerID.addFocusListener(this);
		text_city.addFocusListener(this);
		text_address.addFocusListener(this);
		

		
	}

	private void setClear1()
	{
		//text_customerID.setText("");
		text_firstName.setText("");
		text_lastName.setText("");
		text_email.setText("");
		text_mobile.setText("");
		text_address.setText("");
		text_city.setText("");
		bg.clearSelection();
	}
	private void setData(Customer objCustomer)
	{
		customerId = objCustomer.getCustomerID();
		text_firstName.setText(objCustomer.getFirst_name());
		text_lastName.setText(objCustomer.getLast_name());
		text_email.setText(objCustomer.getEmail());
		text_mobile.setText(objCustomer.getPhone_number());
		text_address.setText(objCustomer.getAddress());
		text_city.setText(objCustomer.getCity());
		if(objCustomer.getGender().equals("MALE"))
			rdbtnMale.setSelected(true);
		else
			rdbtnFemale.setSelected(true);
	}

	public boolean checkTextFields(List<JTextField> textList)
	{ 
		boolean b = false;
		for(JTextField text: textList)
		{
			text.getText().trim();
			if(text.getText().length() == 0)
				b = true; 
		}
		return b;		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnCancel_1)
		{
			setClear1(); 
			btnSave.setText(Constants.SUBMIT);
			lblViewAll.setVisible(false);
			chckbxNew.setSelected(true);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI();
		}

		else if(e.getActionCommand().equals(Constants.SUBMIT))
		{			
			if(text_firstName.getText().trim().length() == 0)
			{
				text_firstName.requestFocus(true);
				new CustomDialog(this, "Enter the First Name", "Error", text_firstName, 75, 0, CustomDialog.ERROR_ICON);
				
			}
			else if(text_lastName.getText().trim().length() == 0)
			{
				text_lastName.requestFocus(true);
				new CustomDialog(this, "Enter the Last Name", "Error", text_lastName, 75, 0, CustomDialog.ERROR_ICON);
			}
			else if(text_mobile.getText().trim().length() != 10)
			{
				text_mobile.requestFocus(true);
				new CustomDialog(this, "Enter the Mobile Number Properly", "Error", text_mobile, 75, 0, CustomDialog.ERROR_ICON);
			}
			else if(text_city.getText().trim().length() == 0)
			{
				text_city.requestFocus(true);
				new CustomDialog(this, "Enter the city", "Error", text_city, 75, 0, CustomDialog.ERROR_ICON);
			}
			else
			{
			
			String gender;
			if(rdbtnMale.isSelected())
				gender = "MALE";
			else 
				gender = "FEMALE";
			try{
			Customer customer = new Customer();
			customer.setFirst_name(text_firstName.getText().trim().toUpperCase());
			customer.setLast_name(text_lastName.getText().trim().toUpperCase());
			customer.setGender(gender);
			customer.setCity(text_city.getText().trim().toUpperCase());
			customer.setPhone_number(text_mobile.getText().trim());
			customer.setEmail(text_email.getText().trim());
			customer.setAddress(text_address.getText());
			
			Boolean isCustomerAdditionSuccessful = customer_controller.addCustomer(customer);
		

			
			if(isCustomerAdditionSuccessful)
			{
				sbm_consignCom.db.add(customer.getPhone_number());
				JOptionPane.showMessageDialog(this,"Customer created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
				//customer_controller.populateCustomerList();
			
				int answer = JOptionPane.showConfirmDialog(this, "Do you wish to continue with booking", null, JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
				{
					BookingSingleContainer objBooking = new BookingSingleContainer(mpg);
					ViewHandler.updateDashBoard(objBooking, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
					SingleLeftPanel.text_mobile.setSelectedItem(text_mobile.getText().trim());
				}					
		

				updateUI();
				setClear1();
			
			}
			else
			{
			JOptionPane.showMessageDialog(this,"Customer with mobile number already exists","Failure",JOptionPane.ERROR_MESSAGE);
			text_mobile.requestFocus(true);
			text_mobile.selectAll();
			}
			
		
			}catch(NumberFormatException ee){
				//text_customerID.requestFocus(true);
				text_firstName.requestFocus(true);
				JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
			catch (Exception ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
			}
			
			}
		 
			
			
		}
		else if(e.getActionCommand() == "Update")
		{
			if(text_firstName.getText().trim().length() == 0)
			{
				text_firstName.requestFocus(true);
				new CustomDialog(this, "Enter the First Name", "Error", text_firstName, 75, 0, CustomDialog.ERROR_ICON);
				
			}
			else if(text_lastName.getText().trim().length() == 0)
			{
				text_lastName.requestFocus(true);
				new CustomDialog(this, "Enter the Last Name", "Error", text_lastName, 75, 0, CustomDialog.ERROR_ICON);
			}
			else if(text_mobile.getText().trim().length() != 10)
			{
				text_mobile.requestFocus(true);
				new CustomDialog(this, "Enter the Mobile Number Properly", "Error", text_mobile, 75, 0, CustomDialog.ERROR_ICON);
			}
			else if(text_city.getText().trim().length() == 0)
			{
				text_city.requestFocus(true);
				new CustomDialog(this, "Enter the city", "Error", text_city, 75, 0, CustomDialog.ERROR_ICON);
			}
			else
			{
			
			String gender;
			if(rdbtnMale.isSelected())
				gender = "MALE";
			else 
				gender = "FEMALE";
			try{
			Customer customer = new Customer();
			customer.setCustomerID(customerId);
			customer.setFirst_name(text_firstName.getText().trim().toUpperCase());
			customer.setLast_name(text_lastName.getText().trim().toUpperCase());
			customer.setGender(gender);
			customer.setCity(text_city.getText().trim().toUpperCase());
			customer.setPhone_number(text_mobile.getText().trim());
			customer.setEmail(text_email.getText().trim());
			customer.setAddress(text_address.getText());
			
			Boolean isCustomerAdditionSuccessful = customer_controller.updateCustomer(customer);
		

			
			if(isCustomerAdditionSuccessful)
			{ 
				JOptionPane.showMessageDialog(this,"Customer Details Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
				CustomerEntry objCustomer = new CustomerEntry(mpg);
				ViewHandler.updateDashBoard(objCustomer, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else
			{
			JOptionPane.showMessageDialog(this,"Customer with mobile number already exists","Failure",JOptionPane.ERROR_MESSAGE);
			text_mobile.requestFocus(true);
			text_mobile.selectAll();
			}
			
		
			}catch(NumberFormatException ee){
				//text_customerID.requestFocus(true);
				text_firstName.requestFocus(true);
				JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
			catch (Exception ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
			}
			
			}
			
		}
		else if(e.getSource()==chckbxNew)
		{
			if(chckbxNew.isSelected())
			{
				setClear1();
				btnSave.setText(Constants.SUBMIT);
				lblViewAll.setVisible(false);
				this.remove(searchPanel);
				sbm_consignCom.cb.setSelectedItem("");
				this.add(componentContainer, gbc_componentContainer);
				this.updateUI();
			}
			else
			{
				 
				btnSave.setText(Constants.UPDATE);
				lblViewAll.setVisible(true);
				this.remove(componentContainer);					
				this.add(searchPanel, gbc_componentContainer);
				this.updateUI();
			}
			
		}
		else if(e.getSource() == btnSearch)
		{
			btnSave.setText(Constants.UPDATE);
			lblViewAll.setVisible(true);
        	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
        	setData(customer);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI();
		}
		else if(e.getSource() == btnViewAll)
		{
//			WelcomeEntry.active = ViewConstants.REPORTS;
//			WelcomeEntry.lblReports.setForeground(new Color(50, 197, 210));
			ScrollUtil.scroll(WelcomeEntry.scrollPane, SwingConstants.TOP);
			ViewAllCustomers obj = new ViewAllCustomers(mpg);
			ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
		}
					
					
			 
	}
	
	

	
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
//		if(arg0.getSource()==text_customerID)
//			text_customerID.selectAll();
		if(arg0.getSource()==text_firstName)
			text_firstName.selectAll();
		else if(arg0.getSource()==text_email)
			text_email.selectAll();
		else if(arg0.getSource()==text_mobile)
			text_mobile.selectAll();
		else if(arg0.getSource()==text_city)
			text_city.selectAll();
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

//		if(arg0.getSource()==text_customerID)
//			text_customerID.setText(text_customerID.getText().trim().toUpperCase());
		if(arg0.getSource()==text_firstName)
			text_firstName.setText(text_firstName.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_lastName)
			text_lastName.setText(text_lastName.getText().trim().toUpperCase());
	
		else if(arg0.getSource()==text_city)
		{
			text_city.setText(text_city.getText().trim().toUpperCase());
		}
		else if(arg0.getSource()==text_address)
		{
			text_address.setText(text_address.getText().trim().toUpperCase());
		}
		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblCustomerId.setForeground(new Color(SetColor.cColor));
		lblLastName.setForeground(new Color(SetColor.cColor));
		lblEmail.setForeground(new Color(SetColor.cColor));
		lblName.setForeground(new Color(SetColor.cColor));
		lblCity.setForeground(new Color(SetColor.cColor));
		lblMobileNo.setForeground(new Color(SetColor.cColor));
		lblAdrs.setForeground(new Color(SetColor.cColor));
		lblGender.setForeground(new Color(SetColor.cColor));
		rdbtnMale.setForeground(new Color(SetColor.cColor));
		rdbtnFemale.setForeground(new Color(SetColor.cColor)); 
		lblCustomerMobile.setForeground(new Color(SetColor.cColor)); 
		
	}
	public void uplmtColor()
	{
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		componentContainer.setBackground(new Color(SetColor.bkColor));
		
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblMobileNo.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCustomerId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblLastName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblEmail.setFont(new Font(ctFType,ctfProp,ctSize));
		lblName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCity.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdrs.setFont(new Font(ctFType,ctfProp,ctSize));
		lblGender.setFont(new Font(ctFType,ctfProp,ctSize)); 
		lblCustomerMobile.setForeground(new Color(SetColor.cColor)); 
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblViewAll)
		{
			ViewHandler.mouseEnteredJLabel(lblViewAll);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblViewAll)
		{
			ViewHandler.mouseExitedJLabel(lblViewAll);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblViewAll)
		{
//			customerContainer.removeAll();
//			CustomerDetails objCustomerDetails = new CustomerDetails();
//			GridBagConstraints gbc_customer_details = new GridBagConstraints();
//			gbc_customer_details.gridwidth = 2;
//			gbc_customer_details.fill = GridBagConstraints.BOTH;
//			gbc_customer_details.insets = new Insets(0, 0, 5, 5);
//			gbc_customer_details.gridx = 0;
//			gbc_customer_details.gridy = 0;
//			customerContainer.add(objCustomerDetails, gbc_customer_details);
//			customerContainer.updateUI();
			//CustomerHistory objUser = new CustomerHistory();
			//ViewHandler.updateDashBoard(objUser, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
		}
	}
	

}

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
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.ExpenseController;
import com.hms.model.Expenses;
import com.hms.util.Constants;
import com.hms.util.CustomDialog;
import com.hms.util.DatabaseConstants;
import com.hms.util.SearchBoxModel;
import com.hms.validators.StringValidator;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class ExpensesEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	private JTextField text_ExpensesID;
	public static String Expenses_ID;
	private JTextField text_name;
    private JLabel lblPayTo;
	private JButton btnSave;
	private JLabel  lblExpensesId;
	private JButton btnCancel_1;
	private JLabel lblDepartment;
	private JLabel lblDescription;
	private JTextField text_department;
	private JTextArea text_desc;
	private JLabel lblAmount;
	private JTextField text_amount;
	private JPanel componentContainer;
	JPanel searchPanel;
 
	

	private ExpenseController expense_controller;
	public static SearchBoxModel sbm_consignCom;
	private JLabel lblExp;
	private JCheckBox chckbxNew;
	GridBagConstraints gbc_componentContainer;
	JComboBox comboSearch;
	JLabel lblCustomerMobile;
	JButton btnSearch;;
	public ExpensesEntry(){
 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblExp = new JLabel("Service Request Details");
		lblExp.setForeground(new Color(50, 197, 210));
		lblExp.setFont(new Font("Dialog", Font.PLAIN, 28));
		GridBagConstraints gbc_lblExp = new GridBagConstraints();
		gbc_lblExp.gridwidth = 3;
		gbc_lblExp.insets = new Insets(0, 0, 5, 5);
		gbc_lblExp.gridx = 0;
		gbc_lblExp.gridy = 1;
		add(lblExp, gbc_lblExp);
		
		chckbxNew = new JCheckBox("New Request");
		chckbxNew.setSelected(true);
		GridBagConstraints gbc_chckbxNew = new GridBagConstraints();
		gbc_chckbxNew.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNew.gridx = 1;
		gbc_chckbxNew.gridy = 2;
		add(chckbxNew, gbc_chckbxNew);
		chckbxNew.addActionListener(this);
		chckbxNew.setSelected(true);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new MigLayout("", "[79px][300px]", "[35px][35px]"));
		searchPanel.setBackground(new Color(SetColor.bkColor));
		
		
		lblCustomerMobile = new JLabel("Customer Mobile");
		searchPanel.add(lblCustomerMobile, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom = new SearchBoxModel(comboSearch,  DatabaseConstants.CUSTOMER_MOBILE);//please check
		comboSearch.setModel(sbm_consignCom);
		comboSearch.addItemListener(sbm_consignCom);
		comboSearch.addPopupMenuListener(sbm_consignCom); 
		searchPanel.add(comboSearch, "cell 1 0,grow");
		
		comboSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	//checkin_controller.retrieveBookingIDs(query, param)
		            }
		            else
		            {		    
		            	btnSave.setText(Constants.UPDATE);
//		            	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
//		            	setData(customer);
//						remove(customerSearchPanel);					
//						add(componentContainer, gbc_componentContainer);
//						updateUI();
		            }
		            	
		        }
		    }
		});
		
		btnSearch = new JButton("Search");
		searchPanel.add(btnSearch, "cell 1 1,alignx center,growy"); 
		btnSearch.addActionListener(this);
		//add(customerSearchPanel, gbc_customerContainer);
 
		
		componentContainer = new JPanel();
		gbc_componentContainer = new GridBagConstraints();
		gbc_componentContainer.fill = GridBagConstraints.BOTH;
		gbc_componentContainer.insets = new Insets(0, 0, 5, 5);
		gbc_componentContainer.gridx = 1;
		gbc_componentContainer.gridy = 3;
		add(componentContainer, gbc_componentContainer);
		componentContainer.setLayout(new MigLayout("", "[1px][150][150]", "[35][10][35][10][35][10][35][10][9px]"));
		lblExpensesId = new JLabel("Expenses ID  ");
		GridBagConstraints gbc_lblExpensesId = new GridBagConstraints();
		gbc_lblExpensesId.anchor = GridBagConstraints.WEST;
		gbc_lblExpensesId.insets = new Insets(0, 0, 5, 5);
		gbc_lblExpensesId.gridx = 0;
		gbc_lblExpensesId.gridy = 2;
		//panel.add(lblExpensesId, gbc_lblExpensesId);
		
		text_ExpensesID = new JTextField();
		GridBagConstraints gbc_text_ExpensesID = new GridBagConstraints();
		gbc_text_ExpensesID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_ExpensesID.gridwidth = 2;
		gbc_text_ExpensesID.insets = new Insets(0, 0, 5, 0);
		gbc_text_ExpensesID.gridx = 2;
		gbc_text_ExpensesID.gridy = 2;
		//panel.add(text_ExpensesID, gbc_text_ExpensesID);
		text_ExpensesID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_ExpensesID.setColumns(10);
		
//		text_ExpensesID.setText(cs.generateExpensesId());
//		text_ExpensesID.setEditable(false);
		
		lblPayTo = new JLabel("Pay To");
		componentContainer.add(lblPayTo, "cell 0 0,grow");
		
		text_name = new JTextField();
		componentContainer.add(text_name, "cell 1 0 2 1,grow");
		text_name.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_name.setColumns(10);
		text_name.setInputVerifier(new StringValidator(null, text_name, "Enter only text values"));
 
		
		lblDepartment = new JLabel("Department");
		componentContainer.add(lblDepartment, "cell 0 2,grow");
		
		text_department = new JTextField();
		componentContainer.add(text_department, "cell 1 2 2 1,grow");
		text_department.setColumns(10);
		text_department.setInputVerifier(new StringValidator(null, text_department, "Enter only text values"));
 
		lblDescription = new JLabel("Description");
		componentContainer.add(lblDescription, "cell 0 4,grow");
		
		text_desc = new JTextArea();
		componentContainer.add(text_desc, "cell 1 4 2 1,grow");
		text_desc.setColumns(10);
		//text_desc.setInputVerifier(new EmailValidator((JDialog) getParent(), text_desc, "Enter correct email address"));
		
		lblAmount = new JLabel("Amount");
		componentContainer.add(lblAmount, "cell 0 6,grow");
		
		text_amount = new JTextField();
		componentContainer.add(text_amount, "cell 1 6 2 1,grow");
		text_amount.setColumns(10);
		//text_amount.setInputVerifier(new MobileValidator((JDialog) getParent(), text_amount, "Enter correct mobile number"));
		
		btnSave = new JButton("Submit");
		componentContainer.add(btnSave, "cell 1 8,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		componentContainer.add(btnCancel_1, "cell 2 8,grow");
		btnCancel_1.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		
		
		btnCancel_1.addActionListener(this);
		text_amount.addFocusListener(this);
		text_department.addFocusListener(this);
		text_name.addFocusListener(this);
		
		setBackground(new Color(SetColor.bkColor)); 

		
	}

	private void setClear1()
	{
		//text_ExpensesID.setText("");
		text_name.setText("");
		text_department.setText("");
		text_desc.setText("");
		text_amount.setText("");  
		
 
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
		}

				if(e.getSource()==btnSave)
				{			
					if(text_name.getText().trim().length() == 0)
					{
						text_name.requestFocus(true);
						new CustomDialog(this, "Enter the First Name", "Error", text_name, 75, 0, CustomDialog.ERROR_ICON);
						
					}
					else if(text_department.getText().trim().length() == 0)
					{
						text_department.requestFocus(true);
						new CustomDialog(this, "Enter the Last Name", "Error", text_department, 75, 0, CustomDialog.ERROR_ICON);
					}
//					else if(text_amount.getText().trim().length() != 10)
//					{
//						text_amount.requestFocus(true);
//						new CustomDialog(this, "Enter the Mobile Number Properly", "Error", text_amount, 75, 0, CustomDialog.ERROR_ICON);
//					} 
					else
					{
					 
					try{
					Expenses objExpense = new Expenses();
					objExpense.setPaidTo(text_name.getText().trim().toUpperCase());
					objExpense.setDepartment(text_department.getText().trim().toUpperCase()); 
					objExpense.setAmount(text_amount.getText().trim());
					objExpense.setDescription(text_desc.getText().trim());  
					Timestamp createdDate = new Timestamp(System.currentTimeMillis());
					objExpense.setCreatedDate(createdDate);
					objExpense.setCreatedBy(MainPage.userID);
					
					Boolean isExpensesAdditionSuccessful = expense_controller.addExpenses(objExpense);
				

					
					if(isExpensesAdditionSuccessful)
					{
						//sbm_consignCom.db.add(objExpense.getPhone_number());
						JOptionPane.showMessageDialog(this,"Expenses Details Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
						expense_controller.populateExpensesList();
					
//						int answer = JOptionPane.showConfirmDialog(this, "Do you wish to continue with booking", null, JOptionPane.YES_NO_OPTION);
//						if (answer == JOptionPane.YES_OPTION)
//						{
//							MainPage.tabbedPane.setSelectedIndex(2);
//							SingleLeftPanel.text_mobile.setSelectedItem(text_amount.getText().trim());
//						}					
				
	
						updateUI();
						setClear1();
					
					}
					else
					{
					JOptionPane.showMessageDialog(this,"Expenses with mobile number already exists","Failure",JOptionPane.ERROR_MESSAGE);
					text_amount.requestFocus(true);
					text_amount.selectAll();
					}
					
				
					}catch(NumberFormatException ee){
						//text_ExpensesID.requestFocus(true);
						text_name.requestFocus(true);
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
						this.remove(searchPanel);					
						this.add(componentContainer, gbc_componentContainer);
						this.updateUI();
					}
					else
					{
						 
						btnSave.setText(Constants.UPDATE);
						this.remove(componentContainer);					
						this.add(searchPanel, gbc_componentContainer);
						this.updateUI();
					}
					
				}
				else if(e.getSource() == btnSearch)
				{
//					btnSave.setText(Constants.UPDATE);
//	            	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
//	            	setData(customer);
//					this.remove(customerSearchPanel);					
//					this.add(componentContainer, gbc_componentContainer);
//					this.updateUI();
				}
				else
				{
					
				}
	 
			}
	
	

	
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
//		if(arg0.getSource()==text_ExpensesID)
//			text_ExpensesID.selectAll();
		if(arg0.getSource()==text_name)
			text_name.selectAll();
		else if(arg0.getSource()==text_desc)
			text_desc.selectAll();
		else if(arg0.getSource()==text_amount)
			text_amount.selectAll(); 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

//		if(arg0.getSource()==text_ExpensesID)
//			text_ExpensesID.setText(text_ExpensesID.getText().trim().toUpperCase());
		if(arg0.getSource()==text_name)
			text_name.setText(text_name.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_department)
			text_department.setText(text_department.getText().trim().toUpperCase());
 
		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblExpensesId.setForeground(new Color(SetColor.cColor));
		lblDepartment.setForeground(new Color(SetColor.cColor));
		lblDescription.setForeground(new Color(SetColor.cColor));
		lblPayTo.setForeground(new Color(SetColor.cColor));
		lblAmount.setForeground(new Color(SetColor.cColor)); 
		
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
		lblAmount.setFont(new Font(ctFType,ctfProp,ctSize));
		lblExpensesId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblDepartment.setFont(new Font(ctFType,ctfProp,ctSize));
		lblDescription.setFont(new Font(ctFType,ctfProp,ctSize));
		lblPayTo.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}
	

}

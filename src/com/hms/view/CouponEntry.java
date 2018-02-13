package com.hms.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.CouponController;
import com.hms.model.Coupon;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.ScrollUtil;
import com.hms.util.SearchBoxModel;
import com.hms.validators.DoubleValidator;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

public class CouponEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_couponID;
	private JTextField textCouponName;
    private JLabel lblCouponName;
	private JButton btnSave;
	private JLabel  lblCouponID;
	private JButton btnCancel_1;
	private JLabel lblCouponDiscount;
	private JTextField textDiscount;
	private JPanel componentContainer;
	private ButtonGroup bg;
	private CouponController couponController;
	
	
	
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
 
	private JLabel lblCustomerDetails_1;

	
	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	JLabel transExcel;
	String filePath;
	public static JButton btnSubmit;
	public static JComboBox combo_search;
	public static JLabel lblRows;
	public static SearchBoxModel sbm_consignCom;
	private JComboBox comboCouponType;
	private JLabel lblType;
	private JLabel lblCouponDetails;
	private JCheckBox chckbxNew;
	
	JPanel searchPanel;
	JComboBox comboSearch; 
	JButton btnViewAll;
	JLabel lblSearch;
	GridBagConstraints gbc_componentContainer;
	JButton btnSearch;
	String couponId;
	MainPage mpg;
	public CouponEntry(MainPage mpg)
	{
		this.mpg = mpg;
		couponController = new CouponController();
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 2;
		gbc_lblRows.gridy = 0;
		//add(lblRows, gbc_lblRows);
		
		lblCustomerDetails_1 = new JLabel("Coupon Details");
		GridBagConstraints gbc_lblCustomerDetails_1 = new GridBagConstraints();
		gbc_lblCustomerDetails_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerDetails_1.gridx = 3;
		gbc_lblCustomerDetails_1.gridy = 0;
		//add(lblCustomerDetails_1, gbc_lblCustomerDetails_1);
		
		combo_search = new JComboBox();
		GridBagConstraints gbc_combo_search = new GridBagConstraints();
		gbc_combo_search.anchor = GridBagConstraints.SOUTHEAST;
		gbc_combo_search.insets = new Insets(0, 0, 5, 5);
		gbc_combo_search.gridx = 4;
		gbc_combo_search.gridy = 0;
		combo_search.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	//couponController.retrieveAll(DatabaseConstants.TABLE_COUPON_COLS);
		            }
		            else
		            {		            	
		            	//couponController.retrieve(DatabaseConstants.TABLE_COUPON_NAME, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.COUPON_NAME);
		combo_search.setModel(sbm_consignCom);
		combo_search.addItemListener(sbm_consignCom);
		combo_search.addPopupMenuListener(sbm_consignCom);


		//add(combo_search, gbc_combo_search);
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 0;
		//add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(this);
		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		
		lblCouponDetails = new JLabel("Coupon Details");
		GridBagConstraints gbc_lblCouponDetails = new GridBagConstraints();
		gbc_lblCouponDetails.gridwidth = 3;
		gbc_lblCouponDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblCouponDetails.gridx = 0;
		gbc_lblCouponDetails.gridy = 1;
		add(lblCouponDetails, gbc_lblCouponDetails);
		lblCouponDetails.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblCouponDetails.setForeground(new Color(50, 197, 210));
		
		
		
		chckbxNew = new JCheckBox("New Coupon");
		GridBagConstraints gbc_chckbxNew = new GridBagConstraints();
		gbc_chckbxNew.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNew.gridx = 1;
		gbc_chckbxNew.gridy = 2;
		add(chckbxNew, gbc_chckbxNew);
		chckbxNew.addActionListener(this);
		chckbxNew.setSelected(true);
		
		//add(transExcel, gbc_lblCustomerDetails_excel);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new MigLayout("", "[79px][150][150][]", "[35px][35px]"));
		searchPanel.setBackground(new Color(SetColor.bkColor));
		
		
		lblSearch = new JLabel("Coupon Name");
		searchPanel.add(lblSearch, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom = new SearchBoxModel(comboSearch,  DatabaseConstants.COUPON_NAME);
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
		            	//customer_controller.populateCustomerList();
		            }
		            else
		            {		    
		            	btnSave.setText(Constants.UPDATE); 
		            	Coupon objCoupon = couponController. populateForm(""+comboSearch.getSelectedItem());
		            	setData(objCoupon);
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
		
		componentContainer = new JPanel();
		gbc_componentContainer = new GridBagConstraints();
		gbc_componentContainer.fill = GridBagConstraints.BOTH;
		gbc_componentContainer.insets = new Insets(0, 0, 5, 5);
		gbc_componentContainer.gridx = 1;
		gbc_componentContainer.gridy = 3;
		add(componentContainer, gbc_componentContainer);
		componentContainer.setLayout(new MigLayout("", "[1px][34px][48px][5px][33px]", "[35][10][35][10][35][10][20px][9px]"));
		lblCouponID = new JLabel("Coupon ID");
		GridBagConstraints gbc_lblCouponID = new GridBagConstraints();
		gbc_lblCouponID.anchor = GridBagConstraints.WEST;
		gbc_lblCouponID.insets = new Insets(0, 0, 5, 5);
		gbc_lblCouponID.gridx = 0;
		gbc_lblCouponID.gridy = 2;
		//panel.add(lblCouponID, gbc_lblCouponID);
		
		text_couponID = new JTextField();
		GridBagConstraints gbc_text_couponID = new GridBagConstraints();
		gbc_text_couponID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_couponID.gridwidth = 2;
		gbc_text_couponID.insets = new Insets(0, 0, 5, 0);
		gbc_text_couponID.gridx = 2;
		gbc_text_couponID.gridy = 2;
		//panel.add(text_couponID, gbc_text_couponID);
		text_couponID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_couponID.setColumns(10);
		
		lblCouponName = new JLabel("Coupon Name");
		componentContainer.add(lblCouponName, "cell 0 0,alignx left,aligny center");
		
		textCouponName = new JTextField();
		componentContainer.add(textCouponName, "cell 1 0 3 1,grow");
		textCouponName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textCouponName.setColumns(10);
		//text_couponName.setInputVerifier(new StringValidator(null, text_couponName, "Enter only text values"));
		
		lblCouponDiscount = new JLabel("Discount");
		componentContainer.add(lblCouponDiscount, "cell 0 2,grow");
		
		textDiscount = new JTextField();
		componentContainer.add(textDiscount, "cell 1 2 3 1,grow");
		textDiscount.setInputVerifier(new DoubleValidator(null, textDiscount, "Enter only numeric values > 0"));		
		
		textCouponName.addFocusListener(this);
		text_couponID.addFocusListener(this);



		tableModel = new DefaultTableModel(Constants.couponColumnNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
				if(table.getRowCount()>1)
				{
					return false;

				}
				else
				{
					   return true;  
				}
 
				  }  
		};
		
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.couponTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		TableColumn  col2 = table.getColumnModel().getColumn(2);
		String[] coupon_type = new String[] { Constants.COUPON_TYPE_GENERAL, Constants.COUPON_TYPE_SEASONAL};
		col2.setCellEditor(new MyComboBoxEditor((coupon_type)));
		scrollPane = new JScrollPane(table);
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		//add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		//couponController = new CouponController(tableModel, table);
		//couponController.retrieveAll(DatabaseConstants.TABLE_COUPON_COLS);
		
		lblType = new JLabel("Coupon Type");
		componentContainer.add(lblType, "cell 0 4,grow");
		
		comboCouponType = new JComboBox();
		componentContainer.add(comboCouponType, "cell 1 4 3 1,grow");
		comboCouponType.addItem(Constants.COUPON_TYPE_GENERAL);
		comboCouponType.addItem(Constants.COUPON_TYPE_SEASONAL);
 
		
		btnSave = new JButton(Constants.SUBMIT);
		componentContainer.add(btnSave, "cell 1 6,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		componentContainer.add(btnCancel_1, "cell 2 6,grow");
		btnCancel_1.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		btnCancel_1.addActionListener(this);
		
		transExcel.setIcon(new ImageIcon(MainPage.class.getResource("/images/excel.png")));
		transExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
	
			
                try {
                	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            		jfc.setDialogTitle("Choose a directory to save your file: ");
            		//jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            		jfc.setAcceptAllFileFilterUsed(false);
            		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xls");
            		jfc.addChoosableFileFilter(filter);
            		
            		int returnValue = jfc.showSaveDialog(null);
            		if (returnValue == JFileChooser.APPROVE_OPTION) {
            			 File file = jfc.getSelectedFile();
            		      if (file == null) {
            		        return;
            		      }
            		      if (!file.getName().toLowerCase().endsWith(".xls")) {
            		        file = new File(file.getParentFile(), file.getName() + ".xls");
            		      }
            		      filePath=file.getAbsolutePath();
                          ExcelExporter.fillData(table, filePath,"Coupon Details");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}    } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
		//text_couponID.setText(coupon_controller.generateCouponId());
		text_couponID.setEditable(false);
		
	}

	private void setClear1()
	{
		text_couponID.setText("");
		textCouponName.setText("");
		textDiscount.setText("");
	
	}
	private void setData(Coupon objCoupon)
	{
		couponId = objCoupon.getCouponId();
		textCouponName.setText(objCoupon.getCouponName());
		textDiscount.setText(objCoupon.getCouponDiscount());
		comboCouponType.setSelectedItem(objCoupon.getCouponType());
	}

 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnCancel_1)
		{ 
			setClear1();
			btnSave.setText(Constants.SUBMIT); 
			chckbxNew.setSelected(true);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI();
		}

		else if(e.getActionCommand().equals(Constants.SUBMIT))
		{			

			if(textCouponName.getText().trim().length() == 0)
			{
				textCouponName.requestFocus(true);
				textCouponName.selectAll();
				JOptionPane.showMessageDialog(this, "Enter the coupon name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(textDiscount.getText().trim().length() == 0)
			{
				textDiscount.requestFocus(true);
				textDiscount.selectAll();
				JOptionPane.showMessageDialog(this, "Enter the discount > 0", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			try{
			Coupon obj_coupon = new Coupon();
			double dicountPrice = Double.parseDouble(textDiscount.getText().trim());
			obj_coupon.setCouponId(couponController.generateID());
			obj_coupon.setCouponName(textCouponName.getText().trim().toUpperCase());
			obj_coupon.setCouponDiscount(""+BigDecimalType.roundDown(dicountPrice));
			obj_coupon.setCouponType(""+comboCouponType.getSelectedItem());
			CouponController obj_controller = new CouponController(obj_coupon);
			int s = obj_controller.submitCoupon(DatabaseConstants.INSERT_COUPON);
			
			if(s>0)
			{
			JOptionPane.showMessageDialog(this,"Coupon created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
			//text_couponID.requestFocus(true);
			textCouponName.requestFocus(true);  
			setClear1();
			}
			else
			{
			JOptionPane.showMessageDialog(this,"Duplicate value for coupon name","Failure",JOptionPane.ERROR_MESSAGE);
			}
			
		
			}catch(NumberFormatException ee){
				//text_couponID.requestFocus(true);
				textCouponName.requestFocus(true);
				
				JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
			catch (Exception ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
			}
			}
				
		}
		else if(e.getActionCommand() == "Update")
		{
			if(textCouponName.getText().trim().length() == 0)
			{
				textCouponName.requestFocus(true);
				textCouponName.selectAll();
				JOptionPane.showMessageDialog(this, "Enter the coupon name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(textDiscount.getText().trim().length() == 0)
			{
				textDiscount.requestFocus(true);
				textDiscount.selectAll();
				JOptionPane.showMessageDialog(this, "Enter the discount > 0", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			try{
			Coupon objCoupon = new Coupon();
			double dicountPrice = Double.parseDouble(textDiscount.getText().trim());
			objCoupon.setCouponId(couponId);
			objCoupon.setCouponName(textCouponName.getText().trim().toUpperCase());
			objCoupon.setCouponDiscount(""+BigDecimalType.roundDown(dicountPrice));
			objCoupon.setCouponType(""+comboCouponType.getSelectedItem());
			int s = couponController.updateForm(objCoupon);
			
			if(s>0)
			{
			JOptionPane.showMessageDialog(this,"Coupon updated successfully","Success",JOptionPane.INFORMATION_MESSAGE);
			setClear1();
			btnSave.setText(Constants.SUBMIT); 
			chckbxNew.setSelected(true);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI();
			}
			else
			{
			JOptionPane.showMessageDialog(this,"Contact admin","Failure",JOptionPane.ERROR_MESSAGE);
			}
			
		
			}catch(NumberFormatException ee){
				//text_couponID.requestFocus(true);
				textCouponName.requestFocus(true);
				
				JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
			catch (Exception ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
			}
			}
		}
		else if(e.getSource()==btnSubmit)
		{
			couponController.updateService(DatabaseConstants.UPDATE_COUPONS, DatabaseConstants.TABLE_COUPON_COLS, ""+combo_search.getSelectedItem());
		}
		else if(e.getSource()==chckbxNew)
		{
			if(chckbxNew.isSelected())
			{
				setClear1();
				btnSave.setText(Constants.SUBMIT); 
				this.remove(searchPanel);
				sbm_consignCom.cb.setSelectedItem("");
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
			btnSave.setText(Constants.UPDATE); 
        	Coupon objCoupon = couponController.populateForm(""+comboSearch.getSelectedItem());
        	setData(objCoupon);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI();
		}
		else if(e.getSource() == btnViewAll)
		{
//					WelcomeEntry.active = ViewConstants.REPORTS;
//					WelcomeEntry.lblReports.setForeground(new Color(50, 197, 210));
			ScrollUtil.scroll(WelcomeEntry.scrollPane, SwingConstants.TOP);
			ViewAllCoupons obj = new ViewAllCoupons(mpg);
			ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
		}
	}
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_couponID)
			text_couponID.selectAll();
		else if(arg0.getSource()==textCouponName)
			textCouponName.selectAll();
		else if(arg0.getSource()==textDiscount)
			textCouponName.selectAll();		
 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_couponID)
			text_couponID.setText(text_couponID.getText().trim().toUpperCase());
		else if(arg0.getSource()==textCouponName)
			textCouponName.setText(textCouponName.getText().trim().toUpperCase());
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblCouponID.setForeground(new Color(SetColor.cColor));
		lblCouponDiscount.setForeground(new Color(SetColor.cColor));
		lblCouponName.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));
		lblType.setForeground(new Color(SetColor.cColor));
		
	}
	public void uplmtColor()
	{
		lblCustomerDetails_1.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		componentContainer.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblCouponID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCouponDiscount.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCouponName.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
		lblType.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
	}
	
	
	
}

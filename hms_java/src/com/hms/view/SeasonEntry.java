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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import com.hms.controller.SeasonController;
import com.hms.model.Season;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.DateDifferenceCalculator;
import com.hms.util.ExcelExporter;
import com.hms.util.ScrollUtil;
import com.hms.util.SearchBoxModel;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

public class SeasonEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_seasonID;
	private JTextField textSeasonName;
    private JLabel lblSeasonName;
	private JButton btnSave;
	private JLabel  lblSeasonID;
	private JButton btnCancel_1;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JComboBox <String> comboCouponId;
	private JLabel lblCouponID;
	private JPanel componentContainer;
	private ButtonGroup bg;
	private SeasonController season_controller;
	
	
	
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;

	private JLabel lblCustomerDetails_1;
		
 
	
	java.sql.Date startDate;
	java.sql.Date endDate;
	
	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	JLabel transExcel;
	String filePath;
	Connection con = DBConnection.getDBConnection();
	public static JButton btnSubmit;
	public static JComboBox combo_search;
	public static JLabel lblRows;
	private SearchBoxModel sbm_consign_coupon;
	public static SearchBoxModel sbm_consignCom;
	JDatePicker season_start;
	JDatePicker season_end;
	UtilDateModel start_model = new UtilDateModel();
	UtilDateModel end_model = new UtilDateModel();
	private JCheckBox chckbxNew;
	private JLabel lblSeasonDetails;
	JPanel searchPanel;
	JComboBox comboSearch; 
	JButton btnViewAll;
	JLabel lblSearch;
	GridBagConstraints gbc_componentContainer;
	JButton btnSearch;
	String seasonId;
	String couponName;
	SeasonController seasonController;
	MainPage mpg;
	int flag = 0;
	public SeasonEntry(MainPage mpg){
		this.mpg = mpg;
		seasonController = new SeasonController();
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 2;
		gbc_lblRows.gridy = 0;
		//add(lblRows, gbc_lblRows);
		
		lblCustomerDetails_1 = new JLabel("Season Details");
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
		            	season_controller.retrieveAll(DatabaseConstants.TABLE_SEASON_COLS);
		            }
		            else
		            {		            	
		            	season_controller.retrieve(DatabaseConstants.TABLE_SEASON_NAME, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.SEASON_NAME);
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
		
		lblSeasonDetails = new JLabel("Season Details");
		GridBagConstraints gbc_lblSeasonDetails = new GridBagConstraints();
		gbc_lblSeasonDetails.gridwidth = 3;
		gbc_lblSeasonDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeasonDetails.gridx = 0;
		gbc_lblSeasonDetails.gridy = 1;
		add(lblSeasonDetails, gbc_lblSeasonDetails);
		lblSeasonDetails.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblSeasonDetails.setForeground(new Color(50, 197, 210));
		
		chckbxNew = new JCheckBox("New");
		GridBagConstraints gbc_chckbxNew = new GridBagConstraints();
		gbc_chckbxNew.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNew.gridx = 1;
		gbc_chckbxNew.gridy = 2;
		add(chckbxNew, gbc_chckbxNew);
		chckbxNew.addActionListener(this);
		chckbxNew.setSelected(true);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new MigLayout("", "[79px][150][150][]", "[35px][35px]"));
		searchPanel.setBackground(new Color(SetColor.bkColor));
		
		
		lblSearch = new JLabel("Season Name");
		searchPanel.add(lblSearch, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom = new SearchBoxModel(comboSearch, DatabaseConstants.SEASON_NAME);
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
		            	Season objSeason = seasonController. populateForm(""+comboSearch.getSelectedItem());
		            	setData(objSeason);
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
		componentContainer.setLayout(new MigLayout("", "[1px][150][150][68px]", "[35][10][35][10][21px][10][35][10][9px]"));
		lblSeasonID = new JLabel("Season ID");
		GridBagConstraints gbc_lblSeasonID = new GridBagConstraints();
		gbc_lblSeasonID.anchor = GridBagConstraints.WEST;
		gbc_lblSeasonID.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeasonID.gridx = 0;
		gbc_lblSeasonID.gridy = 2;
		//panel.add(lblSeasonID, gbc_lblSeasonID);
		
		text_seasonID = new JTextField();
		GridBagConstraints gbc_text_seasonID = new GridBagConstraints();
		gbc_text_seasonID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_seasonID.gridwidth = 3;
		gbc_text_seasonID.insets = new Insets(0, 0, 5, 5);
		gbc_text_seasonID.gridx = 2;
		gbc_text_seasonID.gridy = 2;
		//panel.add(text_seasonID, gbc_text_seasonID);
		text_seasonID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_seasonID.setColumns(10);
		
		lblSeasonName = new JLabel("Season Name");
		componentContainer.add(lblSeasonName, "cell 0 0,alignx left,aligny center");
		
		textSeasonName = new JTextField();
		componentContainer.add(textSeasonName, "cell 1 0 2 1,grow");
		textSeasonName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textSeasonName.setColumns(10);
		
		lblStartDate = new JLabel("Start Date");
		componentContainer.add(lblStartDate, "cell 0 2,grow");
	
		final Date current_date = new Date();
		start_model.setValue(current_date);
		end_model.setValue(current_date);
		season_start = new JDatePicker(start_model, "dd-MM-yyyy");
		season_end = new JDatePicker(end_model, "dd-MM-yyyy");
		componentContainer.add(season_start, "cell 1 2 2 1,grow");
		

		
		lblEndDate = new JLabel("End Date");
		componentContainer.add(lblEndDate, "cell 0 4,grow");
		
		season_end = new JDatePicker(end_model, "dd-MM-yyyy");
		componentContainer.add(season_end, "cell 1 4 2 1,grow");
	
		lblCouponID = new JLabel("Coupon");
		componentContainer.add(lblCouponID, "cell 0 6,grow");
		textSeasonName.addFocusListener(this);
		text_seasonID.addFocusListener(this);
		
		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		//add(transExcel, gbc_lblCustomerDetails_excel);	

		tableModel = new DefaultTableModel(Constants.seasonEntryNames, 0);
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
	    header.setToolTipStrings(Constants.seasonEntryTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		
		

		scrollPane = new JScrollPane(table);
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 5;
		 gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		//add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		season_controller = new SeasonController(tableModel, table);
		season_controller.retrieveAll(DatabaseConstants.TABLE_SEASON_COLS);
		
		
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
                          ExcelExporter.fillData(table, filePath,"Season Details");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}        } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
		comboCouponId = new JComboBox<String>();
		comboCouponId.setEditable(true);
		
		sbm_consign_coupon = new SearchBoxModel(comboCouponId, DatabaseConstants.SEASONAL_COUPONS_LEFT, Constants.COUPON_TYPE_SEASONAL);
		comboCouponId.setModel(sbm_consign_coupon);
		comboCouponId.addPopupMenuListener(sbm_consign_coupon);
		comboCouponId.addItemListener(sbm_consign_coupon);		
		componentContainer.add(comboCouponId, "cell 1 6 2 1,grow");
		
 
		
		
		List<String> categoryList = (ArrayList<String>)sbm_consign_coupon.db.clone();
		TableColumn  col3 = table.getColumnModel().getColumn(3);
		categoryList.remove("");
		String[] categories = categoryList.toArray(new String[categoryList.size()]);
		col3.setCellEditor(new MyComboBoxEditor(categories));
		
		btnSave = new JButton(Constants.SUBMIT);
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
		paybkColor();
		btnCancel_1.addActionListener(this);
//		try
//		{
//
//			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//			rs=stmt.executeQuery(DatabaseConstants.COUPON_NAME);
//			while(rs.next())
//			{
//				comboBox_couponID.addItem(rs.getString(1));	
//			}
//			rs.first();
//
//		}catch(Exception e){
//			Thread t=new Thread(){
//				public void run()
//				{
//					comboBox_couponID.removeAllItems();
//					JOptionPane.showMessageDialog(this, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
//				}
//				};
//				t.start();
//		}
//		
		//text_seasonID.setText(season_controller.generateSeasonID());
		text_seasonID.setEditable(false);
	}

	private void setClear1()
	{
		text_seasonID.setText("");
		textSeasonName.setText("");
		comboCouponId.setSelectedItem("");
 
	}
	private void setData(Season obj)
	{
		seasonId = obj.getSeasonId();
		textSeasonName.setText(obj.getSeasonName());
		comboCouponId.setSelectedItem(obj.getCouponName());
		couponName = obj.getCouponName();
		
	}
	public boolean checkCouponInDB(String param)
	{
		boolean b = false;
		if(param.length()>0)
		{
			for(String item : sbm_consign_coupon.db)
			{
				if(item.equals(param))
				{
					b =  true;
					break;
				}
			}
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
			chckbxNew.setSelected(true);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI(); 
		}

		else if(e.getActionCommand().equals(Constants.SUBMIT))
		{
			Date utilStartDate = (Date) season_start.getModel().getValue();
			Date utilEndDate = (Date) season_end.getModel().getValue();
			startDate = new java.sql.Date(utilStartDate.getTime());
			endDate = new java.sql.Date(utilEndDate.getTime());
			if(textSeasonName.getText().trim().length() == 0)
			{
				textSeasonName.requestFocus(true);
				textSeasonName.selectAll();
				JOptionPane.showMessageDialog(this, "Enter season name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(DateDifferenceCalculator.calculateDifference(startDate, endDate)<0)
			{	
				season_start.requestFocus(true);
				JOptionPane.showMessageDialog(this, "Ending date should be greater that start date", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
					if(checkCouponInDB(""+comboCouponId.getSelectedItem()))						
					submitSeason();
					else 
					{
						comboCouponId.requestFocus(true);
						JOptionPane.showMessageDialog(this, "Select the coupon from the list", "Failure", JOptionPane.ERROR_MESSAGE);
					}
			}
		}
		else if(e.getActionCommand() == "Update")
		{
			Date utilStartDate = (Date) season_start.getModel().getValue();
			Date utilEndDate = (Date) season_end.getModel().getValue();
			startDate = new java.sql.Date(utilStartDate.getTime());
			endDate = new java.sql.Date(utilEndDate.getTime());
			if(textSeasonName.getText().trim().length() == 0)
			{
				textSeasonName.requestFocus(true);
				textSeasonName.selectAll();
				JOptionPane.showMessageDialog(this, "Enter season name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(DateDifferenceCalculator.calculateDifference(startDate, endDate)<0)
			{	
				season_start.requestFocus(true);
				JOptionPane.showMessageDialog(this, "Ending date should be greater that start date", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
					if(checkCouponInDB(""+comboCouponId.getSelectedItem()))						
					updateSeason();
					else 
					{
						flag = 1;
						updateSeason();
					}
			}
		}
		else if(e.getSource()==btnSubmit)
		{
			season_controller.updateService(DatabaseConstants.UPDATE_SEASON, DatabaseConstants.TABLE_SEASON_COLS, ""+combo_search.getSelectedItem());
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
	    	Season obj = seasonController.populateForm(""+comboSearch.getSelectedItem());
	    	setData(obj);
			this.remove(searchPanel);
			sbm_consignCom.cb.setSelectedItem("");
			this.add(componentContainer, gbc_componentContainer);
			this.updateUI();
		}
		else if(e.getSource() == btnViewAll)
		{
	//							WelcomeEntry.active = ViewConstants.REPORTS;
	//							WelcomeEntry.lblReports.setForeground(new Color(50, 197, 210));
			ScrollUtil.scroll(WelcomeEntry.scrollPane, SwingConstants.TOP);
			ViewAllSeasons obj = new ViewAllSeasons(mpg);
			ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
		}
	}

	private void submitSeason() {


		try{

		Season obj_season = new Season();
		obj_season.setSeasonId(season_controller.generateID());
		obj_season.setSeasonName(textSeasonName.getText().trim().toUpperCase());
		obj_season.setDateStart(startDate);
		obj_season.setDateEnd(endDate);
		obj_season.setCouponName((String)comboCouponId.getSelectedItem());

		SeasonController obj_controller = new SeasonController(obj_season);
		int s = obj_controller.submitService(DatabaseConstants.INSERT_SEASON);
		
		if(s>0)
		{
		JOptionPane.showMessageDialog(this,"Season created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
		textSeasonName.requestFocus(true);
		season_controller.retrieveAll(DatabaseConstants.TABLE_SEASON_COLS);
		sbm_consign_coupon.db.remove(comboCouponId.getSelectedItem());
		sbm_consignCom.db.add(textSeasonName.getText().trim().toUpperCase());
		comboCouponId.setSelectedItem("");
		setClear1();
		}
		else
		{
		JOptionPane.showMessageDialog(this,"Duplicate value for season name","Failure",JOptionPane.ERROR_MESSAGE);
		}
		

		}
		catch (Exception ee) {
			// TODO Auto-generated catch blockock
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this,"Check for solution","Failure",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateSeason() {


		try{

		Season obj_season = new Season();
		obj_season.setSeasonId(seasonId);
		obj_season.setSeasonName(textSeasonName.getText().trim().toUpperCase());
		obj_season.setDateStart(startDate);
		obj_season.setDateEnd(endDate);
		if(flag == 0)
		obj_season.setCouponName((String)comboCouponId.getSelectedItem());
		else
			obj_season.setCouponName(couponName);

		SeasonController obj_controller = new SeasonController(obj_season);
		int s = obj_controller.updateForm(obj_season);
		
		if(s>0)
		{
		JOptionPane.showMessageDialog(this,"Season updated successfully","Success",JOptionPane.INFORMATION_MESSAGE);
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
		JOptionPane.showMessageDialog(this,"Duplicate value for season name","Failure",JOptionPane.ERROR_MESSAGE);
		}
		

		}
		catch (Exception ee) {
			// TODO Auto-generated catch blockock
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this,"Check for solution","Failure",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void throwError(String msg) throws Exception 
	{
		    throw new Exception(msg);
	} 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_seasonID)
			text_seasonID.selectAll();
		else if(arg0.getSource()==textSeasonName)
			textSeasonName.selectAll();
 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_seasonID)
			text_seasonID.setText(text_seasonID.getText().trim().toUpperCase());
		else if(arg0.getSource()==textSeasonName)
			textSeasonName.setText(textSeasonName.getText().trim().toUpperCase());
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblSeasonID.setForeground(new Color(SetColor.cColor));
		lblStartDate.setForeground(new Color(SetColor.cColor));
		lblEndDate.setForeground(new Color(SetColor.cColor));
		lblSeasonName.setForeground(new Color(SetColor.cColor));
		lblCouponID.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));

		
	}
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		season_start.setBackground(new Color(SetColor.bkColor));
		season_end.setBackground(new Color(SetColor.bkColor));		
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
		lblSeasonID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblStartDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblEndDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSeasonName.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
	}
	
	
	
}

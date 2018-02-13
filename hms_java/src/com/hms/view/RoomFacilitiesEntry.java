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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.RoomFacilitiesController;
import com.hms.model.RoomFacilities;
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

public class RoomFacilitiesEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_roomFacilitiesID;
	private JTextField text_roomFacilitiesName;
    private JLabel lblRoomFacilitiesName;
	private JButton btnSave;
	private JLabel  lblRoomFacilitiesId;
	private JButton btnCancel_1;
	private JLabel lblRoomFacilitiesDesc;
	private JTextArea text_roomFacilitiesDesc;
	private JPanel componentContainer;
	private ButtonGroup bg;
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
		


	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	
	JLabel transExcel;
	String filePath;
	private JLabel lblPrice;
	private JTextField text_price;
	public static JButton btnSubmit;
	public static JComboBox combo_search;
	public static  JLabel lblRows;
	public static SearchBoxModel sbm_consignCom;
	RoomFacilitiesController room_facilities_controller;
	private JLabel lblRoomFacilities;
	private JCheckBox chckbxNew;
	JPanel searchPanel;
	JComboBox comboSearch; 
	JButton btnViewAll;
	JLabel lblSearch;
	GridBagConstraints gbc_componentContainer;
	JButton btnSearch;
	String facilitiesId;
	RoomFacilitiesController objCon;
	MainPage mpg;
	public RoomFacilitiesEntry(MainPage mpg){
		
		this.mpg = mpg;
		objCon = new RoomFacilitiesController();
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 2;
		gbc_lblRows.gridy = 0;
		
		combo_search = new JComboBox();
		GridBagConstraints gbc_combo_search = new GridBagConstraints();
		gbc_combo_search.anchor = GridBagConstraints.SOUTH;
		gbc_combo_search.insets = new Insets(0, 0, 5, 5);
		gbc_combo_search.fill = GridBagConstraints.HORIZONTAL;
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
		            	room_facilities_controller.retrieveAll(DatabaseConstants.TABLE_ROOM_FACILITES_COLS);
		            }
		            else
		            {		            	
		            	room_facilities_controller.retrieve(DatabaseConstants.TABLE_ROOM_FACILITES_NAME, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.ROOM_FACILITIES_NAME );
		combo_search.setModel(sbm_consignCom);
		combo_search.addItemListener(sbm_consignCom);
		combo_search.addPopupMenuListener(sbm_consignCom);

		//add(combo_search, gbc_combo_search);
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 0;
		//add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(this);
		
		lblRoomFacilities = new JLabel("Room Facilities");
		GridBagConstraints gbc_lblRoomFacilities = new GridBagConstraints();
		gbc_lblRoomFacilities.gridwidth = 3;
		gbc_lblRoomFacilities.insets = new Insets(0, 0, 5, 0);
		gbc_lblRoomFacilities.gridx = 0;
		gbc_lblRoomFacilities.gridy = 1;
		add(lblRoomFacilities, gbc_lblRoomFacilities);
		lblRoomFacilities.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblRoomFacilities.setForeground(new Color(50, 197, 210));
		
		
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
		
		
		lblSearch = new JLabel("Room Facility");
		searchPanel.add(lblSearch, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom = new SearchBoxModel(comboSearch, DatabaseConstants.ROOM_FACILITIES_NAME );
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
		            	RoomFacilities obj = objCon. populateForm(""+comboSearch.getSelectedItem());
		            	setData(obj);
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
		componentContainer.setLayout(new MigLayout("", "[1px][150][150]", "[35][10][42px][10][35][10][25]"));
		lblRoomFacilitiesId = new JLabel("Room Facility ID  ");
		GridBagConstraints gbc_lblRoomFacilitiesId = new GridBagConstraints();
		gbc_lblRoomFacilitiesId.anchor = GridBagConstraints.WEST;
		gbc_lblRoomFacilitiesId.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomFacilitiesId.gridx = 0;
		gbc_lblRoomFacilitiesId.gridy = 2;
		//panel.add(lblRoomFacilitiesId, gbc_lblRoomFacilitiesId);
		
		text_roomFacilitiesID = new JTextField();
		GridBagConstraints gbc_text_roomFacilitiesID = new GridBagConstraints();
		gbc_text_roomFacilitiesID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_roomFacilitiesID.gridwidth = 2;
		gbc_text_roomFacilitiesID.insets = new Insets(0, 0, 5, 0);
		gbc_text_roomFacilitiesID.gridx = 2;
		gbc_text_roomFacilitiesID.gridy = 2;
		//panel.add(text_roomFacilitiesID, gbc_text_roomFacilitiesID);
		text_roomFacilitiesID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_roomFacilitiesID.setColumns(10);
		
		lblRoomFacilitiesName = new JLabel("Facility Name");
		componentContainer.add(lblRoomFacilitiesName, "cell 0 0,alignx left,aligny center");
		
		text_roomFacilitiesName = new JTextField();
		componentContainer.add(text_roomFacilitiesName, "cell 1 0 2 1,grow");
		text_roomFacilitiesName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_roomFacilitiesName.setColumns(10);
		//text_roomFacilitiesName.setInputVerifier(new StringValidator(null, text_roomFacilitiesName, "Enter only text values"));
		
		lblRoomFacilitiesDesc = new JLabel("Description");
		componentContainer.add(lblRoomFacilitiesDesc, "cell 0 2,grow");
		
		text_roomFacilitiesDesc = new JTextArea(2,1);
		text_roomFacilitiesDesc.setWrapStyleWord(true);
		text_roomFacilitiesDesc.setLineWrap(true);
		JScrollPane spane = new JScrollPane(text_roomFacilitiesDesc);
		componentContainer.add(spane, "cell 1 2 2 1,grow");
		
		//text_roomFacilitiesDesc.setInputVerifier(new StringValidator(null, text_roomFacilitiesDesc, "Enter only text values"));
		
		
		
		text_roomFacilitiesName.addFocusListener(this);
		text_roomFacilitiesID.addFocusListener(this);


		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		//add(transExcel, gbc_lblCustomerDetails_excel);	
		
		tableModel = new DefaultTableModel(Constants.roomFaclitiesColNames, 0);
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
	    header.setToolTipStrings(Constants.roomFacilitiesTipStr);
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
		
		room_facilities_controller = new RoomFacilitiesController(tableModel, table);
		room_facilities_controller.retrieveAll(DatabaseConstants.TABLE_ROOM_FACILITES_COLS);
		
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
                          ExcelExporter.fillData(table, filePath,"Facilities Details");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
		lblPrice = new JLabel("Price");
		componentContainer.add(lblPrice, "cell 0 4,alignx left,aligny center");
		
		text_price = new JTextField();
		componentContainer.add(text_price, "cell 1 4 2 1,grow");
		text_price.setColumns(10);
		text_price.setInputVerifier(new DoubleValidator(null, text_price, "Enter only numeric values > 0"));
		
		btnSave = new JButton("Submit");
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
		
		
		//text_roomFacilitiesID.setText(room_facilities_controller.generateFacilitiesId());
		text_roomFacilitiesID.setEditable(false);
	}

	private void setClear1()
	{
		text_roomFacilitiesID.setText("");
		text_roomFacilitiesName.setText("");
		text_roomFacilitiesDesc.setText("");
		text_price.setText("");
	
	}
	private void setData(RoomFacilities obj) {
		// TODO Auto-generated method stub
		facilitiesId = obj.getRoom_facilities_ID();
		text_roomFacilitiesName.setText(obj.getRoom_facilities_name());
		text_roomFacilitiesDesc.setText(obj.getRoom_facilities_desc());
		text_price.setText(obj.getRoom_facilities_price());
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
			if(text_roomFacilitiesName.getText().trim().length() == 0)
			{
				text_roomFacilitiesName.requestFocus(true);
				text_roomFacilitiesName.selectAll();
				JOptionPane.showMessageDialog(this, "Enter facility name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(text_roomFacilitiesDesc.getText().trim().length() == 0)
			{
				text_roomFacilitiesDesc.requestFocus(true);
				text_roomFacilitiesDesc.selectAll();
				JOptionPane.showMessageDialog(this, "Enter facility description", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(text_price.getText().trim().length() == 0)
			{
				text_price.requestFocus(true);
				text_price.selectAll();
				JOptionPane.showMessageDialog(this, "Enter facility price > 0", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			try{
			double facility_price = Double.parseDouble(text_price.getText().trim());
			RoomFacilities obj_roomFacilities = new RoomFacilities();
			obj_roomFacilities.setRoom_facilities_ID(room_facilities_controller.generateID());
			obj_roomFacilities.setRoom_facilities_name(text_roomFacilitiesName.getText().trim().toUpperCase());
			obj_roomFacilities.setRoom_facilities_desc(text_roomFacilitiesDesc.getText().trim().toUpperCase());
			obj_roomFacilities.setRoom_facilities_price(""+BigDecimalType.roundDown(facility_price));
			RoomFacilitiesController obj_controller = new RoomFacilitiesController(obj_roomFacilities);
			int s = obj_controller.submitFacility();
			
			if(s>0)
			{
			JOptionPane.showMessageDialog(this,"Facilities Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
			//text_roomFacilitiesID.requestFocus(true);
			text_roomFacilitiesName.requestFocus(true);
			room_facilities_controller.retrieveAll(DatabaseConstants.TABLE_ROOM_FACILITES_COLS);
			sbm_consignCom.db.add(text_roomFacilitiesName.getText().trim().toUpperCase());
			updateUI();
			setClear1();
			}
			else
			{
			JOptionPane.showMessageDialog(this,"Duplicate value for room facility name","Failure",JOptionPane.ERROR_MESSAGE);
			}
			
		
			}catch(NumberFormatException ee){
				//text_roomFacilitiesID.requestFocus(true);
				text_roomFacilitiesName.requestFocus(true);
				JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
			catch (Exception ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
			}
			
	

			}
			
		}
		else if(e.getActionCommand() == Constants.UPDATE)
		{			
			if(text_roomFacilitiesName.getText().trim().length() == 0)
			{
				text_roomFacilitiesName.requestFocus(true);
				text_roomFacilitiesName.selectAll();
				JOptionPane.showMessageDialog(this, "Enter facility name", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(text_roomFacilitiesDesc.getText().trim().length() == 0)
			{
				text_roomFacilitiesDesc.requestFocus(true);
				text_roomFacilitiesDesc.selectAll();
				JOptionPane.showMessageDialog(this, "Enter facility description", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(text_price.getText().trim().length() == 0)
			{
				text_price.requestFocus(true);
				text_price.selectAll();
				JOptionPane.showMessageDialog(this, "Enter facility price > 0", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			try{
			double facility_price = Double.parseDouble(text_price.getText().trim());
			RoomFacilities objRF = new RoomFacilities();
			objRF.setRoom_facilities_ID(facilitiesId);
			objRF.setRoom_facilities_name(text_roomFacilitiesName.getText().trim().toUpperCase());
			objRF.setRoom_facilities_desc(text_roomFacilitiesDesc.getText().trim().toUpperCase());
			objRF.setRoom_facilities_price(""+BigDecimalType.roundDown(facility_price)); 
			int s = objCon.updateForm(objRF);
			
			if(s>0)
			{
			JOptionPane.showMessageDialog(this,"Facilities Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(this,"Duplicate value for room facility name","Failure",JOptionPane.ERROR_MESSAGE);
			}
			
		
			}catch(NumberFormatException ee){
				//text_roomFacilitiesID.requestFocus(true);
				text_roomFacilitiesName.requestFocus(true);
				JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
			catch (Exception ee) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
			}
			
	

			}
			
		}
		else if(e.getSource()==btnSubmit)
		{
			room_facilities_controller.updateService(DatabaseConstants.UPDATE_FACILITIES, DatabaseConstants.TABLE_ROOM_FACILITES_COLS, ""+combo_search.getSelectedItem());
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
	    	RoomFacilities obj = objCon.populateForm(""+comboSearch.getSelectedItem());
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
			ViewAllFacilities obj = new ViewAllFacilities(mpg);
			ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
		}
	
	}
	

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_roomFacilitiesID)
			text_roomFacilitiesID.selectAll();
		else if(arg0.getSource()==text_roomFacilitiesName)
			text_roomFacilitiesName.selectAll();
		else if(arg0.getSource()==text_roomFacilitiesDesc)
			text_roomFacilitiesDesc.selectAll();	
		else if(arg0.getSource()==text_price)
			text_price.selectAll();	
 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_roomFacilitiesID)
			text_roomFacilitiesID.setText(text_roomFacilitiesID.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_roomFacilitiesName)
			text_roomFacilitiesName.setText(text_roomFacilitiesName.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_roomFacilitiesDesc)
			text_roomFacilitiesDesc.setText(text_roomFacilitiesDesc.getText().trim().toUpperCase());		
		
		
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblRoomFacilitiesId.setForeground(new Color(SetColor.cColor));
		lblRoomFacilitiesDesc.setForeground(new Color(SetColor.cColor));
		lblRoomFacilitiesName.setForeground(new Color(SetColor.cColor));
		lblPrice.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));
		
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
		lblRoomFacilitiesId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomFacilitiesDesc.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomFacilitiesName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblPrice.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}
	
	
	}

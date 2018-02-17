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
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.hms.controller.RoomCapacityController;
import com.hms.controller.RoomCategoryController;
import com.hms.controller.RoomCategoryController;
import com.hms.model.RoomCategory;
import com.hms.model.RoomCategory;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.ScrollUtil;
import com.hms.util.SearchBoxModel;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;

public class RoomCategoryEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_roomCategory;
	private JComboBox<String> text_roomCategoryType;
    private JLabel lblRoomCategoryType;
	private JButton btnSave;
	private JLabel  lblRoomCategory;
	private JLabel lblRoomCategoryDesc;
	private JLabel lblCapacity;
	
	private JComboBox<String> combo_capacity;
	private JPanel componentContainer;
	private ButtonGroup bg;
	public static SearchBoxModel sbm_consignCom;
	
	private RoomCategoryController room_category_controller;
	
	
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
		
	private JTextArea text_roomCategoryDesc;
	private JLabel lblRoomCategoryID;	
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
	List<String> capacityList;
	private JLabel lblRoomCategories;
	private JCheckBox chckbxNew;
	JPanel searchPanel;
	JComboBox comboSearch; 
	JButton btnViewAll;
	JLabel lblSearch;
	GridBagConstraints gbc_componentContainer;
	JButton btnSearch;
	String facilitiesId;
	RoomCategoryController objCon;
	MainPage mpg;

	public RoomCategoryEntry(){		
		this.mpg = mpg;
		objCon = new RoomCategoryController();
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
		            	room_category_controller.retrieveAllRoomCategories(DatabaseConstants.SELECT_ALL_ROOM_CATEGORY);
		            }
		            else
		            {		            	
		            	room_category_controller.retrieveRoomCategory(DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.SELECT_ROOM_CATEGORY_ID);
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
		
		lblRoomCategories = new JLabel("Room Category");
		GridBagConstraints gbc_lblRoomCategories = new GridBagConstraints();
		gbc_lblRoomCategories.gridwidth = 3;
		gbc_lblRoomCategories.insets = new Insets(0, 0, 5, 0);
		gbc_lblRoomCategories.gridx = 0;
		gbc_lblRoomCategories.gridy = 1;
		add(lblRoomCategories, gbc_lblRoomCategories);
		//add(transExcel, gbc_lblCustomerDetails_excel);
		lblRoomCategories.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblRoomCategories.setForeground(new Color(50, 197, 210));
		
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
		sbm_consignCom = new SearchBoxModel(comboSearch, DatabaseConstants.SELECT_ROOM_CATEGORY_ID);
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
		            	RoomCategory obj = objCon. populateForm(""+comboSearch.getSelectedItem());
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
		componentContainer.setLayout(new MigLayout("", "[1px][300]", "[9px][35][10][35][10][42][10][35][][9px]"));
		lblRoomCategory = new JLabel("Room Category");
		componentContainer.add(lblRoomCategory, "cell 0 1,grow");
		
		text_roomCategory = new JTextField();
		componentContainer.add(text_roomCategory, "cell 1 1,grow");
		text_roomCategory.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_roomCategory.setColumns(10);
		
		lblRoomCategoryType = new JLabel("Category Type");
		componentContainer.add(lblRoomCategoryType, "cell 0 3,alignx left,aligny center");
		
		text_roomCategoryType = new JComboBox<String>();
		componentContainer.add(text_roomCategoryType, "cell 1 3,grow");
		text_roomCategoryType.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		text_roomCategoryType.setInputVerifier(new StringValidator(null, text_roomCategoryType, "Enter only text values"));
		text_roomCategoryType.addItem("");
		text_roomCategoryType.addItem("AC");
		text_roomCategoryType.addItem("NON AC");
		
		lblRoomCategoryDesc = new JLabel("Room Category Desc");
		componentContainer.add(lblRoomCategoryDesc, "cell 0 5,grow");
		

		
		text_roomCategoryDesc = new JTextArea(2,1);
		text_roomCategoryDesc.setLineWrap(true);
		text_roomCategoryDesc.setWrapStyleWord(true);
		JScrollPane spane = new JScrollPane(text_roomCategoryDesc);
		componentContainer.add(spane, "cell 1 5,grow");
		//text_roomCategoryDesc.setInputVerifier(new StringValidator(null, text_roomCategoryDesc, "Enter only text values"));
		
		lblCapacity = new JLabel("Capacity");
		componentContainer.add(lblCapacity, "cell 0 7,grow");
		
		
		combo_capacity = new JComboBox<String>();
		componentContainer.add(combo_capacity, "cell 1 7,grow");
		RoomCapacityController rcc = new RoomCapacityController();
		capacityList = rcc.retrieveCapacityName(DatabaseConstants.ROOM_CAPACITY_NAME);
		for(String item: capacityList)
		combo_capacity.addItem(item);
		
		
		text_roomCategory.addFocusListener(this);


		tableModel = new DefaultTableModel(Constants.roomCategoyColNames, 0);
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
	    header.setToolTipStrings(Constants.toolTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		TableColumn  col1 = table.getColumnModel().getColumn(1);
		TableColumn  col3 = table.getColumnModel().getColumn(3);
		
		String[] category_types = new String[] { "AC", "NON AC"};
		capacityList.remove("");
		String[] capacities = capacityList.toArray(new String[capacityList.size()]);
		
		col1.setCellEditor(new MyComboBoxEditor(category_types));
	    col3.setCellEditor(new MyComboBoxEditor((capacities)));
	    //col.setCellRenderer(new MyComboBoxRenderer(values));
		
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
		
		room_category_controller = new RoomCategoryController(tableModel, table);
		room_category_controller.retrieveAllRoomCategories(DatabaseConstants.SELECT_ALL_ROOM_CATEGORY);
		

		
		
		btnSave = new JButton("Submit");
		componentContainer.add(btnSave, "cell 1 9,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
			
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
                          ExcelExporter.fillData(table, filePath,"Room Categories");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
	}

	public void setClear1()
	{
		sbm_consignCom.cb.setSelectedItem("");
		text_roomCategory.setText("");
		text_roomCategoryDesc.setText("");
		combo_capacity.setSelectedItem("");
		text_roomCategoryType.setSelectedItem("");
	}
	public void setData(RoomCategory obj)
	{
		text_roomCategory.setText(obj.getCategory_ID());
		text_roomCategoryDesc.setText(obj.getCategory_Desc());
		combo_capacity.setSelectedItem(obj.getRoom_capacity_ID());
		text_roomCategoryType.setSelectedItem(obj.getCategory_Name());
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSave)
				{			
					if(text_roomCategory.getText().trim().length() == 0)
					{
						text_roomCategory.requestFocus(true);
						text_roomCategory.selectAll();
						JOptionPane.showMessageDialog(this, "Enter room category", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(text_roomCategoryType.getSelectedItem().toString().trim().length() == 0)
					{
						text_roomCategoryType.requestFocus(true);
						JOptionPane.showMessageDialog(this, "Select the category type", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(text_roomCategoryDesc.getText().trim().length() == 0)
					{
						text_roomCategoryDesc.requestFocus(true);
						text_roomCategoryDesc.selectAll();
						JOptionPane.showMessageDialog(this, "Enter room category desc", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(combo_capacity.getSelectedItem().toString().trim().length() == 0)
					{
						combo_capacity.requestFocus(true);
						JOptionPane.showMessageDialog(this, "Select the capacity", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					try{ 
//						if(comboBoxCapacity.getSelectedItem().equals("------Select------")||comboBoxFacilities.getSelectedItem().equals("------Select------"))
//							throwError("Select the beds");
					RoomCategory obj_roomCategory = new RoomCategory();
					obj_roomCategory.setCategory_ID(text_roomCategory.getText().trim().toUpperCase());
					obj_roomCategory.setCategory_Name(""+text_roomCategoryType.getSelectedItem());
					obj_roomCategory.setCategory_Desc(text_roomCategoryDesc.getText().trim().toUpperCase());
					obj_roomCategory.setCategory_Beds(""+combo_capacity.getSelectedItem());
					//obj_roomCategory.setRoom_capacity_ID((String)comboBoxCapacity.getSelectedItem());
					//obj_roomCategory.setRoom_facilities_ID((String)comboBoxFacilities.getSelectedItem());
					RoomCategoryController obj_controller = new RoomCategoryController(obj_roomCategory);
					int s = obj_controller.submitRoom();
					if(s>0)
					{
						JOptionPane.showMessageDialog(this,"Category created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
						setClear1();
					}
					
				
					}catch(NumberFormatException ee){
						text_roomCategory.requestFocus(true);
						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
					catch (Exception ee) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
					}
 
					}
					
				}
				else if(e.getSource()==btnSubmit)
				{
					room_category_controller.updateRoomCategory(DatabaseConstants.UPDATE_ROOM_CATEGORY);
					text_roomCategory.requestFocus(true);
					setClear1();
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
			    	RoomCategory obj = objCon.populateForm(""+comboSearch.getSelectedItem());
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
		if(arg0.getSource()==text_roomCategory)
			text_roomCategory.selectAll();
//		else if(arg0.getSource()==text_roomCategoryType)
//			text_roomCategoryType.selectAll();
//		else if(arg0.getSource()==text_beds)
//			text_beds.selectAll();
		else if(arg0.getSource()==text_roomCategoryDesc)
			text_roomCategoryDesc.selectAll();
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_roomCategory)
			text_roomCategory.setText(text_roomCategory.getText().trim().toUpperCase());
//		else if(arg0.getSource()==text_roomCategoryType)
//			text_roomCategoryType.setText(text_roomCategoryType.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_roomCategoryDesc)
			text_roomCategoryDesc.setText(text_roomCategoryDesc.getText().trim().toUpperCase());		
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblRoomCategory.setForeground(new Color(SetColor.cColor));
		lblRoomCategoryDesc.setForeground(new Color(SetColor.cColor));
		lblCapacity.setForeground(new Color(SetColor.cColor));
		lblRoomCategoryType.setForeground(new Color(SetColor.cColor));
		
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
		

		lblRoomCategory.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomCategoryDesc.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCapacity.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomCategoryType.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}
	
	
}

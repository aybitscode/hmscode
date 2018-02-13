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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import com.hms.controller.RoomPriceController;
import com.hms.model.RoomPrice;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.validators.DoubleValidator;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import net.miginfocom.swing.MigLayout;

public class RoomPriceEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_RoomPriceID_1;
	//private JTextField text_RoomPriceName;
    //private JLabel lblRoomPriceName;
	private JButton btnSave;
	private JLabel  lblRoomPriceId;
	private JButton btnCancel_1;
	private JLabel lblRoomPrice;
	private JLabel lblCategoryID;
	//private JLabel lblSeasonID;
	private JComboBox <String>comboBox_categoryID;
	private JPanel panel;
	private ButtonGroup bg;
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
	private JLabel lblEnterRoomPriceDetails;
	//private JComboBox <String>comboBox_seasonID;
	private JTextField text_RoomPrice;
	//private JLabel lblRoomCapacityID;
	//private JComboBox <String>comboBox_capacity;
	private final JList<String> comboBox_facilitiesID;
	DefaultListModel<String> listItems = new DefaultListModel<>();
	private JLabel lblFacilitiesID;
	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	RoomPriceController room_price_controller;
	JLabel transExcel;
	String filePath;
	Connection con = DBConnection.getDBConnection();
	public static JButton btnSubmit;
	private JComboBox combo_search;
	public static JLabel lblRows;
	public static SearchBoxModel sbm_consignCom;
	public static SearchBoxModel sbm_consignCom2;
	SearchBoxModel sbm_consignCom3;
	SearchBoxModel sbm_consignCom4;
	public RoomPriceEntry(){
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.fill = GridBagConstraints.HORIZONTAL;
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
		            	room_price_controller.retrieveRoomPrices();
		            }
		            else
		            {		            	
		            	room_price_controller.retrieveRoomPrice(DatabaseConstants.ALL_ROOM_PRICE_CATEGORY, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.ROOM_PRICE_CATEGORY);
		combo_search.setModel(sbm_consignCom);
		combo_search.addItemListener(sbm_consignCom);
		combo_search.addPopupMenuListener(sbm_consignCom);

		//add(combo_search, gbc_combo_search);
		
		btnSubmit = new JButton("Save");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 0;
		//add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(this);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[1px][150][150]", "[9px][35][10][35][10][45px][10][25]"));
		
		lblEnterRoomPriceDetails = new JLabel("Room Price Details");
		panel.add(lblEnterRoomPriceDetails, "cell 0 0 3 1,grow");
		lblRoomPriceId = new JLabel("Room Price  ID  ");
		GridBagConstraints gbc_lblRoomPriceId = new GridBagConstraints();
		gbc_lblRoomPriceId.anchor = GridBagConstraints.WEST;
		gbc_lblRoomPriceId.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomPriceId.gridx = 1;
		gbc_lblRoomPriceId.gridy = 2;
		//panel.add(lblRoomPriceId, gbc_lblRoomPriceId);
		
		text_RoomPriceID_1 = new JTextField();
		GridBagConstraints gbc_text_RoomPriceID_1 = new GridBagConstraints();
		gbc_text_RoomPriceID_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_RoomPriceID_1.gridwidth = 2;
		gbc_text_RoomPriceID_1.insets = new Insets(0, 0, 5, 0);
		gbc_text_RoomPriceID_1.gridx = 3;
		gbc_text_RoomPriceID_1.gridy = 2;
		//panel.add(text_RoomPriceID_1, gbc_text_RoomPriceID_1);
		text_RoomPriceID_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_RoomPriceID_1.setColumns(10);
		
//		lblRoomPriceName = new JLabel("Price Name");
//		GridBagConstraints gbc_lblRoomPriceName = new GridBagConstraints();
//		gbc_lblRoomPriceName.anchor = GridBagConstraints.WEST;
//		gbc_lblRoomPriceName.insets = new Insets(0, 0, 5, 5);
//		gbc_lblRoomPriceName.gridx = 1;
//		gbc_lblRoomPriceName.gridy = 3;
//		panel.add(lblRoomPriceName, gbc_lblRoomPriceName);
//		
//		text_RoomPriceName = new JTextField();
//		GridBagConstraints gbc_text_RoomPriceName = new GridBagConstraints();
//		gbc_text_RoomPriceName.fill = GridBagConstraints.HORIZONTAL;
//		gbc_text_RoomPriceName.gridwidth = 2;
//		gbc_text_RoomPriceName.insets = new Insets(0, 0, 5, 0);
//		gbc_text_RoomPriceName.gridx = 3;
//		gbc_text_RoomPriceName.gridy = 3;
//		panel.add(text_RoomPriceName, gbc_text_RoomPriceName);
//		text_RoomPriceName.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		text_RoomPriceName.setColumns(10);
//		text_RoomPriceName.setInputVerifier(new StringValidator(null, text_RoomPriceName, "Enter only text values"));
//		
		
		lblCategoryID = new JLabel("Room Category");
		panel.add(lblCategoryID, "cell 0 1,grow");	
		
		
		comboBox_categoryID = new JComboBox();
		comboBox_categoryID.setEditable(true);
		sbm_consignCom2 = new SearchBoxModel(comboBox_categoryID, DatabaseConstants.COMBO_ROOM_PRICE_CATEGORY);
		comboBox_categoryID.setModel(sbm_consignCom2);
		comboBox_categoryID.addItemListener(sbm_consignCom2);
		comboBox_categoryID.addPopupMenuListener(sbm_consignCom2);
		panel.add(comboBox_categoryID, "cell 1 1 2 1,grow");
		
		
		lblRoomPrice = new JLabel("Room Price");
		panel.add(lblRoomPrice, "cell 0 3,grow");
		

		
		text_RoomPrice = new JTextField();
		text_RoomPrice.setColumns(10);
		panel.add(text_RoomPrice, "cell 1 3 2 1,grow");
		text_RoomPrice.setInputVerifier(new DoubleValidator(null, text_RoomPrice, "Enter only numeric values > 0"));
		

		
//		lblSeasonID = new JLabel("Season");
//		GridBagConstraints gbc_lblSeasonID = new GridBagConstraints();
//		gbc_lblSeasonID.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblSeasonID.insets = new Insets(0, 0, 5, 5);
//		gbc_lblSeasonID.gridx = 1;
//		gbc_lblSeasonID.gridy = 6;
//		panel.add(lblSeasonID, gbc_lblSeasonID);
		

	//	text_RoomPriceName.addFocusListener(this);
		text_RoomPriceID_1.addFocusListener(this);
		text_RoomPrice.addFocusListener(this);

				
		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		//add(transExcel, gbc_lblCustomerDetails_excel);			

		tableModel = new DefaultTableModel(Constants.roomPriceEntryColNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
				if(table.getRowCount()>1)
				{
					return false;

				}
				else
				{
						if(column==5||column==6)
								return false;
							else
								return true;  
				}
				  }  
		};
		
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.roomPriceTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		
		
		List<String> categoryList = (ArrayList<String>)sbm_consignCom2.db.clone();
		TableColumn  col = table.getColumnModel().getColumn(0);
		categoryList.remove("");
		String[] categories = categoryList.toArray(new String[categoryList.size()]);
		col.setCellEditor(new MyComboBoxEditor(categories));
		
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
		
		room_price_controller = new RoomPriceController(tableModel, table);
		room_price_controller.retrieveRoomPrices();
		
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
                          ExcelExporter.fillData(table, filePath,"Room Price Details");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}        } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
		
		
//		comboBox_seasonID = new JComboBox();
//		GridBagConstraints gbc_comboBox_seasonID = new GridBagConstraints();
//		gbc_comboBox_seasonID.gridwidth = 2;
//		gbc_comboBox_seasonID.insets = new Insets(0, 0, 5, 0);
//		gbc_comboBox_seasonID.fill = GridBagConstraints.HORIZONTAL;
//		gbc_comboBox_seasonID.gridx = 3;
//		gbc_comboBox_seasonID.gridy = 6;
//		comboBox_seasonID.setEditable(true);
//		sbm_consignCom3 = new SearchBoxModel(comboBox_seasonID, DatabaseConstants.SEASON_NAME);
//		comboBox_seasonID.setModel(sbm_consignCom3);
//		comboBox_seasonID.addItemListener(sbm_consignCom3);		
//		panel.add(comboBox_seasonID, gbc_comboBox_seasonID);
		
//		lblRoomCapacityID = new JLabel("Capacity");
//		GridBagConstraints gbc_lblRoomCapacityID = new GridBagConstraints();
//		gbc_lblRoomCapacityID.anchor = GridBagConstraints.WEST;
//		gbc_lblRoomCapacityID.insets = new Insets(0, 0, 5, 5);
//		gbc_lblRoomCapacityID.gridx = 1;
//		gbc_lblRoomCapacityID.gridy = 7;
//		panel.add(lblRoomCapacityID, gbc_lblRoomCapacityID);
		
//		comboBox_capacity = new JComboBox();
//		GridBagConstraints gbc_comboBox_capacity = new GridBagConstraints();
//		gbc_comboBox_capacity.fill = GridBagConstraints.HORIZONTAL;
//		gbc_comboBox_capacity.gridwidth = 2;
//		gbc_comboBox_capacity.insets = new Insets(0, 0, 5, 0);
//		gbc_comboBox_capacity.gridx = 3;
//		gbc_comboBox_capacity.gridy = 7;
//		comboBox_capacity.setEditable(true);
//		sbm_consignCom4 = new SearchBoxModel(comboBox_capacity, DatabaseConstants.TOTAL_CAPACITY);
//		comboBox_capacity.setModel(sbm_consignCom4);
//		comboBox_capacity.addItemListener(sbm_consignCom4);
//		panel.add(comboBox_capacity, gbc_comboBox_capacity);
		
		lblFacilitiesID = new JLabel("Facilities");
		lblFacilitiesID.setForeground(Color.BLACK);
		lblFacilitiesID.setFont(new Font("Dialog", Font.PLAIN, 0));
		panel.add(lblFacilitiesID, "cell 0 5,alignx left,aligny center");
		
		try
		{
			 
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(DatabaseConstants.ROOMCATEGORY_ID);
 
			rs=stmt.executeQuery(DatabaseConstants.ROOM_FACILITIES_NAME);	
			while(rs.next())
			{
				if(!rs.getString(1).equals(Constants.NO_FACILITIES))
				listItems.addElement(rs.getString(1));	
			}
			rs.first();

		}catch(Exception e){
			Thread t=new Thread(){
				public void run()
				{
					comboBox_facilitiesID.removeAll();
					JOptionPane.showMessageDialog(null, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				};
				t.start();
		}
				
		
		comboBox_facilitiesID = new JList<>(listItems);
		comboBox_facilitiesID.setBackground(Color.LIGHT_GRAY);
		panel.add(comboBox_facilitiesID, "cell 1 5 2 1,grow");
 
		
		btnSave = new JButton("Submit");
		panel.add(btnSave, "cell 1 7,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		panel.add(btnCancel_1, "cell 2 7,grow");
		btnCancel_1.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		btnCancel_1.addActionListener(this);
//		
//		try
//		{
//			comboBox_categoryID.addItem(Constants.ROOMPRICEENTRYSELECTITEM);
//			comboBox_seasonID.addItem(Constants.ROOMPRICEENTRYSELECTITEM);
//			comboBox_capacity.addItem(Constants.ROOMPRICEENTRYSELECTITEM);
//			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//			rs=stmt.executeQuery(DatabaseConstants.ROOMCATEGORY_ID);
//			while(rs.next())
//			{
//				comboBox_categoryID.addItem(rs.getString(1));	
//			}
//			rs.first();
//			rs=stmt.executeQuery(DatabaseConstants.SEASON_NAME);
//			while(rs.next())
//			{
//				comboBox_seasonID.addItem(rs.getString(1));	
//			}
//			rs.first();			
//			rs=stmt.executeQuery(DatabaseConstants.TOTAL_CAPACITY);
//			while(rs.next())
//			{
//				comboBox_capacity.addItem(rs.getString(1));	
//			}
//			rs.first();
//			rs=stmt.executeQuery(DatabaseConstants.ROOM_FACILITIES_NAME);	
//			while(rs.next())
//			{
//				listItems.addElement(rs.getString(1));	
//			}
//			rs.first();
//
//		}catch(Exception e){
//			Thread t=new Thread(){
//				public void run()
//				{
//					comboBox_categoryID.removeAllItems();
//					comboBox_seasonID.removeAllItems();
//					comboBox_capacity.removeAllItems();
//					comboBox_facilitiesID.removeAll();
//					JOptionPane.showMessageDialog(this, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
//				}
//				};
//				t.start();
//		}
//		

		
		//text_RoomPriceID_1.setText(room_price_controller.generateRoomPriceID());
		text_RoomPriceID_1.setEditable(false);
		
		
	}

	private void setClear1()
	{
		text_RoomPriceID_1.setText("");
		//text_RoomPriceName.setText("");
		text_RoomPrice.setText("");
		//comboBox_seasonID.setSelectedItem("");
		comboBox_categoryID.setSelectedItem("");
		//comboBox_capacity.setSelectedItem("");
		comboBox_facilitiesID.clearSelection();
 
	}
	public boolean checkCategoryInDB(String param)
	{
		boolean b = false;
		if(param.length()>0)
		{
			for(String item : sbm_consignCom2.db)
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
	
	public boolean checkSeasonInDB(String param)
	{
		boolean b = false;
		if(param.length()>0)
		{
			for(String item : sbm_consignCom3.db)
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
	
	public boolean checkCapacityInDB(String param)
	{
		boolean b = false;
		if(param.length()>0)
		{
			for(String item : sbm_consignCom4.db)
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
				}
			 
				if(e.getSource()==btnSave)
				{			
//					if(text_RoomPriceName.getText().trim().length() == 0)
//					{
//						text_RoomPriceName.requestFocus(true);
//						text_RoomPriceName.selectAll();
//						JOptionPane.showMessageDialog(this, "Enter room price name", "Error", JOptionPane.ERROR_MESSAGE);
//					}
					if(text_RoomPrice.getText().trim().length() == 0)
					{
						text_RoomPrice.requestFocus(true);
						text_RoomPrice.selectAll();
						JOptionPane.showMessageDialog(this, "Enter the price > 0", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{					
						if(checkCategoryInDB(""+comboBox_categoryID.getSelectedItem()))
						{
//							if(checkSeasonInDB(""+comboBox_seasonID.getSelectedItem()))
//							{
//								if(checkCapacityInDB(""+comboBox_capacity.getSelectedItem()))
//								{
								submitRoomPrice();
//								}
//								else
//								{
//									comboBox_capacity.requestFocus(true);
//									JOptionPane.showMessageDialog(this, "Select the Capacity from the list", "Failure", JOptionPane.ERROR_MESSAGE);
//								}
//							}
//							else
//							{
//								comboBox_seasonID.requestFocus(true);
//								JOptionPane.showMessageDialog(this, "Select the Season from the list", "Failure", JOptionPane.ERROR_MESSAGE);
//							}
						}
						else
						{
							comboBox_categoryID.requestFocus(true);
							JOptionPane.showMessageDialog(this, "Select the Room Category from the list", "Failure", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				}
				
				else if(e.getSource()==btnSubmit)
				{
					int rowCount = tableModel.getRowCount();
					int colCount = tableModel.getColumnCount();
					int cl = 1;
						if(compareRows())
							JOptionPane.showMessageDialog(this, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
						else
						{
							
							Connection con = DBConnection.getDBConnection();
							try {
								PreparedStatement pst=con.prepareStatement(DatabaseConstants.UPDATE_ROOM_PRICE);
								for(int i=0;i<rowCount;i++)
									for(int j=0;j<colCount-2;j++)
									{
										pst.setString(cl, ""+tableModel.getValueAt(i, j));
										cl++;
										
									}
								pst.setString(cl, ""+combo_search.getSelectedItem());
								int s = pst.executeUpdate();
								if(s>0)
								{
									sbm_consignCom.db.remove(combo_search.getSelectedItem());
									sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
									JOptionPane.showMessageDialog(this, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
									room_price_controller.retrieveRoomPrices();
									combo_search.setSelectedItem("");
									comboBox_facilitiesID.clearSelection();
								}
								else
									JOptionPane.showMessageDialog(this, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
										
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(this, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
								
							}
							
						}

				}

				
			}

	private void submitRoomPrice() {

			double roomPrice = Double.parseDouble(text_RoomPrice.getText().trim());
			Object[] listFacilites = null;
			if(comboBox_facilitiesID.getSelectedValues().length>0)
			{
				System.out.println("from length > 0");
				double totalFacilitiesCost = 0;
			
				
				String allFacilities = null;
				try {
					PreparedStatement pstFacilityPrice =con.prepareStatement("select  roomFacilitiesPrice from roomFacilities where roomFacilitiesID = ?");

					listFacilites = comboBox_facilitiesID.getSelectedValues();
		              if(comboBox_facilitiesID.getSelectedIndex() != -1){  
		                    for(Object obj :listFacilites){  
		                    	allFacilities += allFacilities + obj;
		                    	pstFacilityPrice.setString(1,""+obj);
		                    	ResultSet rs=pstFacilityPrice.executeQuery();
		                    	if(rs.next())
		                    	totalFacilitiesCost += Double.parseDouble((rs.getString(1)));  
		                    }  
		                 }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try{ 
					RoomPrice obj_roomPrice = new RoomPrice();	
					obj_roomPrice.setRoom_price_ID(room_price_controller.generateID());
					obj_roomPrice.setRoom_price(""+BigDecimalType.roundDown(roomPrice));
					obj_roomPrice.setRoom_category_ID((String)comboBox_categoryID.getSelectedItem());
					//obj_roomPrice.setSeason_ID((String)comboBox_seasonID.getSelectedItem());
					//obj_roomPrice.setCapacity_ID((String)comboBox_capacity.getSelectedItem());
					obj_roomPrice.setFacilities_ID(allFacilities);
					obj_roomPrice.setRoomFacilitiesPrice(""+BigDecimalType.roundDown(totalFacilitiesCost));
					
					RoomPriceController obj_room_price_controller = new RoomPriceController(obj_roomPrice);
					int s = obj_room_price_controller.submitRoom();
					
					if(s>0)
					{

					int s1 = obj_room_price_controller.submitFacilities(listFacilites,	obj_roomPrice);
					if(s1>0)
					{									
						JOptionPane.showMessageDialog(this,"Room Price created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
						//	text_RoomPriceID_1.requestFocus(true);
						comboBox_categoryID.requestFocus(true);
						room_price_controller.retrieveRoomPrices();
						updateUI();

						sbm_consignCom2.db.remove(comboBox_categoryID.getSelectedItem());
						sbm_consignCom.db.add(""+comboBox_categoryID.getSelectedItem());
						comboBox_facilitiesID.clearSelection();
						
						setClear1();
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.INFORMATION_MESSAGE);
					}
					}
					else
					{
					JOptionPane.showMessageDialog(this,"Duplicate value for room price name","Failure",JOptionPane.ERROR_MESSAGE);
					}
					
				
					}
					catch (Exception ee) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
					}
			}
			else
			{
				
				
				try{ 
//					if(comboBox_seasonID.getSelectedItem().equals(Constants.ROOMPRICEENTRYSELECTITEM)||comboBox_capacity.getSelectedItem().equals(Constants.ROOMPRICEENTRYSELECTITEM)||
//					comboBox_categoryID.getSelectedItem().equals(Constants.ROOMPRICEENTRYSELECTITEM))
//						throwError("Select the items");
				RoomPrice obj_roomPrice = new RoomPrice();	
				obj_roomPrice.setRoom_price_ID(room_price_controller.generateID());
				obj_roomPrice.setRoom_price(""+BigDecimalType.roundDown(roomPrice));
				obj_roomPrice.setRoom_category_ID((String)comboBox_categoryID.getSelectedItem());
				//obj_roomPrice.setSeason_ID((String)comboBox_seasonID.getSelectedItem());
				//obj_roomPrice.setCapacity_ID((String)comboBox_capacity.getSelectedItem());
				//obj_roomPrice.setFacilities_ID(allFacilities);
				obj_roomPrice.setRoomFacilitiesPrice(""+BigDecimalType.roundDown(0));
				
				RoomPriceController obj_room_price_controller = new RoomPriceController(obj_roomPrice);
				int s = obj_room_price_controller.submitRoom();
				
				if(s>0)
				{
					
					int s1 = obj_room_price_controller.submitNoFacilities(Constants.NO_FACILITIES,	obj_roomPrice);
					if(s1>0)
					{
					
					JOptionPane.showMessageDialog(this,"Room Price created successfully no facilities","Success",JOptionPane.INFORMATION_MESSAGE);
					//	text_RoomPriceID_1.requestFocus(true);
					comboBox_categoryID.requestFocus(true);
					room_price_controller.retrieveRoomPrices();
					updateUI();
					sbm_consignCom2.db.remove(comboBox_categoryID.getSelectedItem());
					sbm_consignCom.db.add(""+comboBox_categoryID);								
					setClear1();
					}
				
				}
				else
				{
				JOptionPane.showMessageDialog(this,"Duplicate value for room price name","Failure",JOptionPane.ERROR_MESSAGE);
				}
										
				}
				catch (Exception ee) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		
	}
	
	public boolean compareRows(){
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		boolean rowequals = false;
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.ALL_CUSTOMER_MOBILE);
			pst.setString(1, ""+combo_search.getSelectedItem());
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
					int cl = 1;
				for(int i=0;i<rowCount;i++)
					for(int j=0;j<colCount;j++)
					{
						if(rs.getString(cl).equals(tableModel.getValueAt(i, j)))
						{
							rowequals = true;
						cl++;
						}
						else
						{
							rowequals = false;
							break;
						}
					}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rowequals;
	}

	

	private static void throwError(String msg) throws Exception 
	{
		    throw new Exception(msg);
	} 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_RoomPriceID_1)
			text_RoomPriceID_1.selectAll();

	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_RoomPriceID_1)
			text_RoomPriceID_1.setText(text_RoomPriceID_1.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_RoomPrice)
			text_RoomPrice.setText(text_RoomPrice.getText().trim().toUpperCase());		
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblRoomPriceId.setForeground(new Color(SetColor.cColor));
		lblRoomPrice.setForeground(new Color(SetColor.cColor));
		lblCategoryID.setForeground(new Color(SetColor.cColor));
//		lblRoomPriceName.setForeground(new Color(SetColor.cColor));
//		lblSeasonID.setForeground(new Color(SetColor.cColor));
//		lblRoomCapacityID.setForeground(new Color(SetColor.cColor));
		lblFacilitiesID.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));
		
	}
	public void uplmtColor()
	{
		lblEnterRoomPriceDetails.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		//lblSeasonID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblFacilitiesID.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblRoomCapacityID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomPriceId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomPrice.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCategoryID.setFont(new Font(ctFType,ctfProp,ctSize));
	//	lblRoomPriceName.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblEnterRoomPriceDetails.setFont(new Font(stFType,stfProp,stSize));
	}
}

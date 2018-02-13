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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.hms.controller.RoomCapacityController;
import com.hms.model.RoomCapacity;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.validators.IntegerValidator;
import com.hms.validators.StringValidator;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import net.miginfocom.swing.MigLayout;

public class RoomCapacityEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_roomCapacityID;
	private JTextField text_roomCapacityName;
    private JLabel lblRoomCapacityName;
	private JButton btnSave;
	private JLabel  lblRoomCapacityId;
	private JButton btnCancel_1;
	private JLabel lblRoomCapacityAdults;
	private JLabel lblRoomChildCapacity;
	private JTextField text_roomAdultCapacity;
	private JTextField text_roomChildCapacity;
	private JPanel panel;
	private ButtonGroup bg;
	
	private RoomCapacity obj_roomCapacity;
	private RoomCapacityController room_capacity_controller;
	
	
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
 
	private JLabel lblCustomerDetails_1;
	private JLabel lblEnterRoomDetails;
	public static SearchBoxModel sbm_consignCom;
	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	JLabel transExcel;
	String filePath;
	public static JButton btnSubmit;
	public static JComboBox combo_search;
	public static JLabel lblRows;
	public RoomCapacityEntry(){
		
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 2;
		gbc_lblRows.gridy = 0;
		//add(lblRows, gbc_lblRows);
		
		
		lblCustomerDetails_1 = new JLabel("Room Capacity Details");
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
		            	room_capacity_controller.retrieveAll(DatabaseConstants.TABLE_ROOM_CAPACITY_COLS);
		            }
		            else
		            {		            	
		            	room_capacity_controller.retrieve(DatabaseConstants.TABLE_ROOM_CAPACITY_NAME, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.ROOM_CAPACITY_NAME);
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
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[1px][150][150]", "[9px][35][10][35][10][35][10][25px]"));
		
		lblEnterRoomDetails = new JLabel("Capacity Details");
		panel.add(lblEnterRoomDetails, "cell 0 0 3 1,grow");
		lblRoomCapacityId = new JLabel("Room Capacity ID  ");
		GridBagConstraints gbc_lblRoomCapacityId = new GridBagConstraints();
		gbc_lblRoomCapacityId.anchor = GridBagConstraints.WEST;
		gbc_lblRoomCapacityId.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomCapacityId.gridx = 0;
		gbc_lblRoomCapacityId.gridy = 2;
		//panel.add(lblRoomCapacityId, gbc_lblRoomCapacityId);
		
		text_roomCapacityID = new JTextField();
		GridBagConstraints gbc_text_roomCapacityID = new GridBagConstraints();
		gbc_text_roomCapacityID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_roomCapacityID.gridwidth = 2;
		gbc_text_roomCapacityID.insets = new Insets(0, 0, 5, 0);
		gbc_text_roomCapacityID.gridx = 2;
		gbc_text_roomCapacityID.gridy = 2;
		//panel.add(text_roomCapacityID, gbc_text_roomCapacityID);
		text_roomCapacityID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_roomCapacityID.setColumns(10);
		
		lblRoomCapacityName = new JLabel("Room Capacity Name");
		panel.add(lblRoomCapacityName, "cell 0 1,alignx left,aligny center");
		
		text_roomCapacityName = new JTextField();
		panel.add(text_roomCapacityName, "cell 1 1 2 1,grow");
		text_roomCapacityName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_roomCapacityName.setColumns(10);
		text_roomCapacityName.setInputVerifier(new StringValidator(null, text_roomCapacityName, "Enter only text values"));
		
		lblRoomCapacityAdults = new JLabel("Adult Capacity");
		panel.add(lblRoomCapacityAdults, "cell 0 3,grow");
		
		text_roomAdultCapacity = new JTextField();
		panel.add(text_roomAdultCapacity, "cell 1 3 2 1,grow");
		text_roomAdultCapacity.setInputVerifier(new IntegerValidator(null, text_roomAdultCapacity, "Enter only digits > 0"));
		
 
		
		lblRoomChildCapacity = new JLabel("Child Capacity");
		panel.add(lblRoomChildCapacity, "cell 0 5,grow");
		
		text_roomChildCapacity = new JTextField();
		panel.add(text_roomChildCapacity, "cell 1 5 2 1,grow");
		text_roomChildCapacity.setColumns(10);
		text_roomChildCapacity.addFocusListener(this);
		text_roomCapacityName.addFocusListener(this);
		text_roomCapacityID.addFocusListener(this);
		text_roomChildCapacity.setInputVerifier(new IntegerValidator(null, text_roomChildCapacity, "Enter only digits > 0"));

		
		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		//add(transExcel, gbc_lblCustomerDetails_excel);	

		tableModel = new DefaultTableModel(Constants.roomCapacityColNames, 0);
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
	    header.setToolTipStrings(Constants.roomCapacityTipStr);
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
		
		room_capacity_controller = new RoomCapacityController(tableModel, table);
		room_capacity_controller.retrieveAll(DatabaseConstants.TABLE_ROOM_CAPACITY_COLS);
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
                          ExcelExporter.fillData(table, filePath,"Capacity Details");
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
 
		
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
		
		
		//text_roomCapacityID.setText(room_capacity_controller.generateRoomCapacityID());
		text_roomCapacityID.setEditable(false);
	}

	private void setClear1()
	{
		text_roomCapacityID.setText("");
		text_roomCapacityName.setText("");
		text_roomChildCapacity.setText("");
		text_roomAdultCapacity.setText("");
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
					if(text_roomCapacityName.getText().trim().length() == 0)
					{
						text_roomCapacityName.requestFocus(true);
						text_roomCapacityName.selectAll();
						JOptionPane.showMessageDialog(this, "Enter the capcity name", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(text_roomAdultCapacity.getText().trim().length() == 0)
					{
						text_roomAdultCapacity.requestFocus(true);
						text_roomAdultCapacity.selectAll();
						JOptionPane.showMessageDialog(this, "Enter the adult capacity > 0", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(text_roomChildCapacity.getText().trim().length() == 0)
					{
						text_roomChildCapacity.requestFocus(true);
						text_roomChildCapacity.selectAll();
						JOptionPane.showMessageDialog(this, "Enter child capacity > 0", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					try{
					RoomCapacity obj_roomCapacity = new RoomCapacity();
					obj_roomCapacity.setRoom_capacity_ID(room_capacity_controller.generateID());
					obj_roomCapacity.setRoom_capacity_name(text_roomCapacityName.getText().trim().toUpperCase());
					obj_roomCapacity.setRoom_capacity_adults(text_roomAdultCapacity.getText().trim());
					obj_roomCapacity.setRoom_capacity_childs(text_roomChildCapacity.getText().trim());
					
					RoomCapacityController capacity_controller = new RoomCapacityController(obj_roomCapacity);
					int s = capacity_controller.submitRoom();
					
					
					if(s>0)
					{
					JOptionPane.showMessageDialog(this,"Room capacity created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					//text_roomCapacityID.requestFocus(true);
					text_roomCapacityName.requestFocus(true);
					room_capacity_controller.retrieveAll(DatabaseConstants.TABLE_ROOM_CAPACITY_COLS);
					sbm_consignCom.db.add(text_roomCapacityName.getText().trim().toUpperCase());
					updateUI();
					setClear1();
					}
					else
					{
					JOptionPane.showMessageDialog(this,"Duplicate value for room capacity name","Failure",JOptionPane.ERROR_MESSAGE);
					}
					
				
					}catch(NumberFormatException ee){
						//text_roomCapacityID.requestFocus(true);
						text_roomCapacityName.requestFocus(true);
						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
					catch (Exception ee) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
					}
					
					}
					
					
				}
				else if(e.getSource()==btnSubmit)
				{
					room_capacity_controller.updateService(DatabaseConstants.UPDATE_ROOM_CAPACITY, DatabaseConstants.TABLE_ROOM_CAPACITY_COLS, ""+combo_search.getSelectedItem());
				}
			}
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_roomCapacityID)
			text_roomCapacityID.selectAll();
		else if(arg0.getSource()==text_roomCapacityName)
			text_roomCapacityName.selectAll();
		else if(arg0.getSource()==text_roomChildCapacity)
			text_roomChildCapacity.selectAll();
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_roomCapacityID)
			text_roomCapacityID.setText(text_roomCapacityID.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_roomCapacityName)
			text_roomCapacityName.setText(text_roomCapacityName.getText().trim().toUpperCase());
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblRoomCapacityId.setForeground(new Color(SetColor.cColor));
		lblRoomCapacityAdults.setForeground(new Color(SetColor.cColor));
		lblRoomChildCapacity.setForeground(new Color(SetColor.cColor));
		lblRoomCapacityName.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));
		
	}
	public void uplmtColor()
	{
		lblCustomerDetails_1.setForeground(new Color(SetColor.mtColor));
		lblEnterRoomDetails.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblRoomCapacityId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomCapacityAdults.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomChildCapacity.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomCapacityName.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
		lblEnterRoomDetails.setFont(new Font(stFType,stfProp,stSize));
	}
	
	}

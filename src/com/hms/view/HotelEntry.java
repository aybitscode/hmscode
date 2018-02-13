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
import java.sql.SQLException;
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

import com.hms.controller.HotelController;
import com.hms.model.Hotel;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.validators.IntegerValidator;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

public class HotelEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_HotelID;
	private JTextField text_hotelName;
    private JLabel lblHotelName;
	private JButton btnSave;
	private JLabel  lblHotelId;
	private JButton btnCancel_1;
	private JLabel lblHotelDesc;
	private JLabel lblTotalRooms;
	private JTextArea text_hotelDesc;
	private JTextField text_totalRooms;
	private JLabel lblTotalFloors;
	private JTextField text_totalFloors;
	private JPanel panel;
	private ButtonGroup bg;
	
	private HotelController hotel_controller;
	
	
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
 
	private JLabel lblCustomerDetails_1;
	private JLabel lblEnterHotelDetails;

	
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
	MainPage mpg;
	public HotelEntry(MainPage mpg){
		this.mpg = mpg;
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 2;
		gbc_lblRows.gridy = 0;
		//add(lblRows, gbc_lblRows);
		
		lblCustomerDetails_1 = new JLabel("Hotel Details");
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
		            	hotel_controller.retrieveAll(DatabaseConstants.TABLE_HOTEL_COLS);
		            }
		            else
		            {		            	
		            	hotel_controller.retrieve(DatabaseConstants.TABLE_HOTEL_NAME, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.HOTEL_NAME );
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
		panel.setLayout(new MigLayout("", "[grow][39.00][150][150][grow]", "[][35][10][50px][10][35][10][35][][20px][0,grow]"));
		
		lblEnterHotelDetails = new JLabel("Hotel Details");
		panel.add(lblEnterHotelDetails, "cell 1 0 3 1,grow");
		lblHotelId = new JLabel("Hotel ID  ");
		GridBagConstraints gbc_lblHotelId = new GridBagConstraints();
		gbc_lblHotelId.anchor = GridBagConstraints.WEST;
		gbc_lblHotelId.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotelId.gridx = 0;
		gbc_lblHotelId.gridy = 2;
		//panel.add(lblHotelId, gbc_lblHotelId);
		
		text_HotelID = new JTextField();
		GridBagConstraints gbc_text_HotelID = new GridBagConstraints();
		gbc_text_HotelID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_HotelID.gridwidth = 2;
		gbc_text_HotelID.insets = new Insets(0, 0, 5, 0);
		gbc_text_HotelID.gridx = 2;
		gbc_text_HotelID.gridy = 2;
		//panel.add(text_HotelID, gbc_text_HotelID);
		text_HotelID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_HotelID.setColumns(10);
		
		lblHotelName = new JLabel("Hotel Name");
		panel.add(lblHotelName, "cell 1 1,alignx left,aligny center");
		
		text_hotelName = new JTextField();
		panel.add(text_hotelName, "cell 2 1 2 1,grow");
		text_hotelName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_hotelName.setColumns(10);
		//text_hotelName.setInputVerifier(new StringValidator(null, text_hotelName, "Enter only text values"));
		
		lblHotelDesc = new JLabel("Description");
		panel.add(lblHotelDesc, "cell 1 3,grow");
		
		text_hotelDesc = new JTextArea(2,1);
		text_hotelDesc.setWrapStyleWord(true);
		text_hotelDesc.setLineWrap(true);
		JScrollPane spane = new JScrollPane(text_hotelDesc);
		panel.add(spane, "cell 2 3 2 1,grow");
		//text_hotelDesc.setInputVerifier(new StringValidator(null, text_hotelDesc, "Enter only text values"));

		
		lblTotalRooms = new JLabel("Total Rooms");
		panel.add(lblTotalRooms, "cell 1 5,grow");
		
		text_totalRooms = new JTextField();
		panel.add(text_totalRooms, "cell 2 5 2 1,grow");
		text_totalRooms.setColumns(10);
		text_totalRooms.setInputVerifier(new IntegerValidator(null, text_totalRooms, "Enter only digits > 0"));
		
		lblTotalFloors = new JLabel("Total Floors");
		panel.add(lblTotalFloors, "cell 1 7,grow");
		
		text_totalFloors = new JTextField();
		panel.add(text_totalFloors, "cell 2 7 2 1,grow");
		text_totalFloors.setColumns(10);
		text_totalFloors.setInputVerifier(new IntegerValidator(null, text_totalFloors, "Enter only digits > 0"));
		
		
		text_totalFloors.addFocusListener(this);
		text_totalRooms.addFocusListener(this);
		text_hotelName.addFocusListener(this);
		text_HotelID.addFocusListener(this);

		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		//add(transExcel, gbc_lblCustomerDetails_excel);	

	tableModel = new DefaultTableModel(Constants.hotelColumnNames, 0);
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
    header.setToolTipStrings(Constants.hotelTipStr);
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
	
	hotel_controller = new HotelController(tableModel, table);
	hotel_controller.retrieveAll(DatabaseConstants.TABLE_HOTEL_COLS);
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
                      ExcelExporter.fillData(table, filePath,"Hotel Details");
                      JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                      Desktop.getDesktop().open(new File(filePath));
        		}           } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
	});
		
		btnSave = new JButton("Submit");
		panel.add(btnSave, "cell 2 9,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		panel.add(btnCancel_1, "cell 3 9,grow");
		btnCancel_1.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		btnCancel_1.addActionListener(this);

		//text_HotelID.setText(hotel_controller.generateHotelId());
		text_HotelID.setEditable(false);
		try {
			Connection con = DBConnection.getDBConnection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int count = 0;
			ResultSet rk=stmt.executeQuery("select count(*) from hotel");
			rk.next();
			count = rk.getInt(1);
			if(count>0)
			{
				btnSave.setEnabled(false);
				btnCancel_1.setEnabled(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setClear1()
	{
		text_HotelID.setText("");
		text_hotelName.setText("");
		text_totalRooms.setText("");
		text_totalFloors.setText("");
		text_hotelDesc.setText("");

 
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
					if(text_hotelName.getText().trim().length() == 0)
					{
						text_hotelName.requestFocus(true);
						text_hotelName.selectAll();
						JOptionPane.showMessageDialog(this, "Enter hotel name", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(text_totalRooms.getText().trim().length() == 0)
					{
						text_totalRooms.requestFocus(true);
						text_totalRooms.selectAll();
						JOptionPane.showMessageDialog(this, "Enter total rooms > 0", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(text_totalFloors.getText().trim().length() == 0)
					{
						text_totalFloors.requestFocus(true);
						text_totalFloors.selectAll();
						JOptionPane.showMessageDialog(this, "Enter total floors > 0", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					try{
					Hotel obj_hotel = new Hotel();
					obj_hotel.setHotel_ID(hotel_controller.generateID());
					obj_hotel.setHotel_name(text_hotelName.getText().trim().toUpperCase());
					obj_hotel.setHotel_desc(text_hotelDesc.getText().trim().toUpperCase());
					obj_hotel.setHotel_total_rooms(text_totalRooms.getText().trim());
					obj_hotel.setHotel_total_floors(text_totalFloors.getText().trim().toUpperCase());
					HotelController obj_controller = new HotelController(obj_hotel);
					int s = obj_controller.submitRoom(DatabaseConstants.INSERT_HOTEL);
					
					if(s>0)
					{
					JOptionPane.showMessageDialog(this,"Hotel details created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					//text_HotelID.requestFocus(true);
					MainPage.lblhotelName.setText("                      "+text_hotelName.getText());
					text_hotelName.requestFocus(true);
					
					hotel_controller.retrieveAll(DatabaseConstants.TABLE_HOTEL_COLS);
					setClear1();
					}
					else
					{
					JOptionPane.showMessageDialog(this,"Check For Solution","Failure",JOptionPane.ERROR_MESSAGE);
					}
					
				
					}catch(NumberFormatException ee){
						//text_HotelID.requestFocus(true);
						text_hotelName.requestFocus(true);
						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
					catch (Exception ee) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
					}
					
 
					}
					
				}
				else if(e.getSource()==btnSubmit)
				{
					hotel_controller.updateService(DatabaseConstants.UPDATE_HOTEL, DatabaseConstants.TABLE_HOTEL_COLS, ""+combo_search.getSelectedItem());
				}
				

			}
	
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_HotelID)
			text_HotelID.selectAll();
		else if(arg0.getSource()==text_hotelName)
			text_hotelName.selectAll();
		else if(arg0.getSource()==text_totalRooms)
			text_totalRooms.selectAll();
		else if(arg0.getSource()==text_totalFloors)
			text_totalFloors.selectAll();
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_HotelID)
			text_HotelID.setText(text_HotelID.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_hotelName)
			text_hotelName.setText(text_hotelName.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_hotelDesc)
			text_hotelDesc.setText(text_hotelDesc.getText().trim().toUpperCase());		
 

		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblHotelId.setForeground(new Color(SetColor.cColor));
		lblHotelDesc.setForeground(new Color(SetColor.cColor));
		lblTotalRooms.setForeground(new Color(SetColor.cColor));
		lblHotelName.setForeground(new Color(SetColor.cColor));
		lblTotalFloors.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));
		
	}
	public void uplmtColor()
	{
		lblCustomerDetails_1.setForeground(new Color(SetColor.mtColor));
		lblEnterHotelDetails.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblTotalFloors.setFont(new Font(ctFType,ctfProp,ctSize));
		lblHotelId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblHotelDesc.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTotalRooms.setFont(new Font(ctFType,ctfProp,ctSize));
		lblHotelName.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
		lblEnterHotelDetails.setFont(new Font(stFType,stfProp,stSize));
	}
}

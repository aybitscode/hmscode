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
import java.text.DecimalFormat;

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

import com.hms.controller.GSTController;
import com.hms.model.GST;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.validators.DoubleValidator;
import com.hms.validators.IntegerValidator;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import net.miginfocom.swing.MigLayout;

public class GSTEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_HotelID;
	private JComboBox text_gstType;
    private JLabel lblGSTName;
	private JButton btnSave;
	private JLabel  lblHotelId;
	private JButton btnCancel_1;
	private JLabel lblLowerBound;
	private JLabel lblUpperbound;
	private JTextField text_lowerbound;
	private JTextField text_upperbound;
	private JLabel lblPercentage;
	private JTextField text_percentage;
	private JPanel panel;
	private ButtonGroup bg;
	
	private GSTController gst_controller;
	
	
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
 
	private JLabel lblCustomerDetails_1;
	private JLabel lblEnterGSTDetails;

	
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
	private JLabel lblCategory;
	private JComboBox combo_category;
	private JLabel lblCGST;
	private JLabel lblSGST;
	private JTextField text_cgst;
	private JTextField text_sgst;
	DecimalFormat df = new DecimalFormat("###.#");
	public GSTEntry(){
	
		bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
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
		
		lblCustomerDetails_1 = new JLabel("TAX Details");
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
		            	gst_controller.retrieveAll(DatabaseConstants.TABLE_GST_COLS);
		            }
		            else
		            {		            	
		            	gst_controller.retrieve(DatabaseConstants.TABLE_TAX_TYPE, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.TAX_TYPE );
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
		panel.setLayout(new MigLayout("", "[1px][150][150px][33px]", "[20][35][10][35][10][35][10][35][10][35][10][35][10][25]"));
		
		lblEnterGSTDetails = new JLabel("TAX Details");
		panel.add(lblEnterGSTDetails, "cell 0 0 4 1,grow");
		
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
		
		lblCategory = new JLabel("Category");
		panel.add(lblCategory, "cell 0 1,grow");
		
		combo_category = new JComboBox();
		panel.add(combo_category, "cell 1 1 2 1,grow");
		combo_category.addItem("");
		combo_category.addItem(Constants.GST_BOOKING);
		combo_category.addItem(Constants.GST_ROOM_SERVICE);
		//combo_category.setInputVerifier(new StringValidator(null, combo_category, "Enter only digits > 0"));
		
		lblPercentage = new JLabel("Percentage ");
		panel.add(lblPercentage, "cell 0 3,grow");
		
		text_percentage = new JTextField();
		panel.add(text_percentage, "cell 1 3 2 1,grow");
		text_percentage.setColumns(10);
		text_percentage.setInputVerifier(new DoubleValidator(null, text_percentage, "Enter only digits > 0"));
		text_percentage.addFocusListener(this);
		
		lblGSTName = new JLabel("TAX Type");
		GridBagConstraints gbc_lblGSTName = new GridBagConstraints();
		gbc_lblGSTName.anchor = GridBagConstraints.WEST;
		gbc_lblGSTName.insets = new Insets(0, 0, 5, 5);
		gbc_lblGSTName.gridx = 0;
		gbc_lblGSTName.gridy = 3;
		//panel.add(lblGSTName, gbc_lblGSTName);
		
		text_gstType = new JComboBox();
		GridBagConstraints gbc_text_gstType = new GridBagConstraints();
		gbc_text_gstType.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_gstType.gridwidth = 2;
		gbc_text_gstType.insets = new Insets(0, 0, 5, 0);
		gbc_text_gstType.gridx = 2;
		gbc_text_gstType.gridy = 3;
		//panel.add(text_gstType, gbc_text_gstType);
		text_gstType.addItem("");
		text_gstType.addItem("CGST");
		text_gstType.addItem("SGST");
		
		lblCGST = new JLabel("CGST");
		panel.add(lblCGST, "cell 0 5,alignx left,aligny center");
		
		text_cgst = new JTextField();
		panel.add(text_cgst, "cell 1 5 2 1,grow");
		text_cgst.setColumns(10);
		text_cgst.setInputVerifier(new DoubleValidator(null, text_cgst, "Enter only digits > 0"));
		
		lblSGST = new JLabel("SGST");
		panel.add(lblSGST, "cell 0 7,alignx left,aligny center");
		
		text_sgst = new JTextField();
		panel.add(text_sgst, "cell 1 7 2 1,grow");
		text_sgst.setColumns(10);
		//text_gstType.addItem("SERVICE TAX");
		text_sgst.setInputVerifier(new DoubleValidator(null, text_sgst, "Enter only digits > 0"));
		
		
		//text_hotelName.setInputVerifier(new StringValidator(null, text_hotelName, "Enter only text values"));
		
		lblLowerBound = new JLabel("Lower Bound");
		panel.add(lblLowerBound, "cell 0 9,grow");
		
		text_lowerbound = new JTextField();
		text_lowerbound.setColumns(10);
		panel.add(text_lowerbound, "cell 1 9 2 1,grow");
		text_lowerbound.setInputVerifier(new IntegerValidator(null, text_lowerbound, "Enter only digits > 0"));

		
		lblUpperbound = new JLabel("Upper Bound");
		panel.add(lblUpperbound, "cell 0 11,grow");
		
		text_upperbound = new JTextField();
		panel.add(text_upperbound, "cell 1 11 2 1,grow");
		text_upperbound.setColumns(10);
		text_upperbound.setInputVerifier(new IntegerValidator(null, text_upperbound, "Enter only digits > 0"));
		

		
		
		text_percentage.addFocusListener(this);
		text_upperbound.addFocusListener(this);
		text_HotelID.addFocusListener(this);
		text_lowerbound.addFocusListener(this);

		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		//add(transExcel, gbc_lblCustomerDetails_excel);	

	tableModel = new DefaultTableModel(Constants.gstColumnNames, 0);
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
    header.setToolTipStrings(Constants.gstTipStr);
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
	gbc_scrollPane.fill = GridBagConstraints.BOTH;
	gbc_scrollPane.gridx = 2;
	gbc_scrollPane.gridy = 1;
	//add(scrollPane, gbc_scrollPane);
	
	table.setForeground(new Color(SetColor.cColor));
	setBackground(new Color(SetColor.bkColor));
	table.setBackground(new Color(SetColor.bkColor));
	table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
	
	gst_controller = new GSTController(tableModel, table);
	gst_controller.retrieveAll(DatabaseConstants.TABLE_GST_COLS);
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
                      ExcelExporter.fillData(table, filePath,"Tax Details");
                      JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                      Desktop.getDesktop().open(new File(filePath));
        		}
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		}
	});
		
		btnSave = new JButton("Submit");
		panel.add(btnSave, "cell 1 13,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		panel.add(btnCancel_1, "cell 2 13,grow");
		btnCancel_1.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		btnCancel_1.addActionListener(this);

		//text_HotelID.setText(gst_controller.generateHotelId());
		text_HotelID.setEditable(false);
//		try {
//			Connection con = DBConnection.getDBConnection();
//			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//			int count = 0;
//			ResultSet rk=stmt.executeQuery("select count(*) from hotel");
//			rk.next();
//			count = rk.getInt(1);
//			if(count>0)
//			{
//				btnSave.setEnabled(false);
//				btnCancel_1.setEnabled(false);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void setClear1()
	{
		text_HotelID.setText("");
		text_gstType.setSelectedItem("");
		combo_category.setSelectedItem("");
		text_upperbound.setText("");
		text_percentage.setText("");
		text_lowerbound.setText("");
		text_cgst.setText("");
		text_sgst.setText("");

 
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
					
					String upperbound=null, lowerbound = null, percentage = null;
//					if(text_gstType.getSelectedItem().toString().length() == 0)
//					{
//						text_gstType.requestFocus(true);
//						JOptionPane.showMessageDialog(this, "Enter TAX type", "Error", JOptionPane.ERROR_MESSAGE);
//					}
					if(combo_category.getSelectedItem().toString().length() == 0)
					{
						combo_category.requestFocus(true);
						JOptionPane.showMessageDialog(this, "Select the category", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
//					else if(text_upperbound.getText().trim().length() == 0)
//					{
//						text_upperbound.requestFocus(true);
//						text_upperbound.selectAll();
//						JOptionPane.showMessageDialog(this, "Enter total rooms > 0", "Error", JOptionPane.ERROR_MESSAGE);
//					}
//					else if(text_percentage.getText().trim().length() == 0)
//					{
//						text_percentage.requestFocus(true);
//						text_percentage.selectAll();
//						JOptionPane.showMessageDialog(this, "Enter total floors > 0", "Error", JOptionPane.ERROR_MESSAGE);
//					}
					else
					{
						System.out.println("from insed the combo");
						if(text_upperbound.getText().trim().length() == 0)
							upperbound = Constants.NOT_DEFINED;
						else 
							upperbound = text_upperbound.getText().trim();
						
						if(text_lowerbound.getText().trim().length() == 0)
							lowerbound = "0";
						else 
							lowerbound = text_lowerbound.getText().trim();
						
						if(text_percentage.getText().trim().length() == 0 || Double.parseDouble(text_percentage.getText())==0)
							percentage = "0";
						else
							percentage = text_percentage.getText().trim();
							if(!upperbound.equals(Constants.NOT_DEFINED))
							{
								if(Double.parseDouble(lowerbound) < Double.parseDouble(upperbound))
								{
										try{
										GST obj_gst = new GST();
										obj_gst.setGstID(gst_controller.generateID());
										obj_gst.setCgstPercentage(text_cgst.getText());
										obj_gst.setSgstPercentage(text_sgst.getText());
										obj_gst.setLowerBound(""+BigDecimalType.roundDown(Double.parseDouble(lowerbound)));
										obj_gst.setCategory(""+combo_category.getSelectedItem());						
										if(upperbound.equals(Constants.NOT_DEFINED))
											obj_gst.setUpperBound(upperbound);
										else						
											obj_gst.setUpperBound(""+BigDecimalType.roundDown(Double.parseDouble(upperbound)));
										obj_gst.setGstPercentge(percentage);
										obj_gst.setStatus(Constants.MODE_ON);
										GSTController obj_controller = new GSTController(obj_gst);
										int s = obj_controller.submitService(DatabaseConstants.INSERT_GST);
										
										if(s>0)
										{
										JOptionPane.showMessageDialog(this,"Tax details created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
										//text_HotelID.requestFocus(true);
										text_gstType.requestFocus(true);
										sbm_consignCom.db.add(""+text_gstType.getSelectedItem());
										gst_controller.retrieveAll(DatabaseConstants.TABLE_GST_COLS);
										setClear1();
										}
										else
										{
										JOptionPane.showMessageDialog(this,"Duplcate tax rule entered","Failure",JOptionPane.ERROR_MESSAGE);
										}
										
									
										}catch(NumberFormatException ee){
											//text_HotelID.requestFocus(true);
											text_gstType.requestFocus(true);
											JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
										catch (Exception ee) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
										}
								}
								else
								{
									JOptionPane.showMessageDialog(this,"Please enter valid lower / upper bound","Failure",JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								try{
									GST obj_gst = new GST();
									obj_gst.setGstID(gst_controller.generateID());
									obj_gst.setLowerBound(""+BigDecimalType.roundDown(Double.parseDouble(lowerbound)));
									obj_gst.setCategory(""+combo_category.getSelectedItem());
									if(upperbound.equals(Constants.NOT_DEFINED))
										obj_gst.setUpperBound(upperbound);
									else						
										obj_gst.setUpperBound(""+BigDecimalType.roundDown(Double.parseDouble(upperbound)));
									obj_gst.setCgstPercentage(text_cgst.getText());
									obj_gst.setSgstPercentage(text_sgst.getText());
									obj_gst.setGstPercentge(percentage);
									obj_gst.setStatus(Constants.MODE_ON);
									GSTController obj_controller = new GSTController(obj_gst);
									
									int s = obj_controller.submitService(DatabaseConstants.INSERT_GST);
									
									if(s>0)
									{
									JOptionPane.showMessageDialog(this,"Tax details created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
									//text_HotelID.requestFocus(true);
									text_gstType.requestFocus(true);
									sbm_consignCom.db.add(""+text_gstType.getSelectedItem());
									gst_controller.retrieveAll(DatabaseConstants.TABLE_GST_COLS);
									setClear1();
									}
									else
									{
									JOptionPane.showMessageDialog(this,"Duplcate tax rule entered","Failure",JOptionPane.ERROR_MESSAGE);
									}
									
								
									}catch(NumberFormatException ee){
										//text_HotelID.requestFocus(true);
										text_gstType.requestFocus(true);
										JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
									catch (Exception ee) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
									}
							}
						
					
					}
					
				}
				else if(e.getSource()==btnSubmit)
				{
					gst_controller.updateService(DatabaseConstants.UPDATE_GST, DatabaseConstants.TABLE_GST_COLS, ""+combo_search.getSelectedItem());
				}
				

			}
	
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_HotelID)
			text_HotelID.selectAll();
//		else if(arg0.getSource()==text_gstType)
//			text_gstType.selectAll();
		else if(arg0.getSource()==text_upperbound)
			text_upperbound.selectAll();
		else if(arg0.getSource()==text_percentage)
			text_percentage.selectAll();
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_HotelID)
			text_HotelID.setText(text_HotelID.getText().trim().toUpperCase());
//		else if(arg0.getSource()==text_gstType)
//			text_gstType.setText(text_gstType.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_lowerbound)
			text_lowerbound.setText(text_lowerbound.getText().trim().toUpperCase());		
		else if(arg0.getSource()==text_percentage)
		{
			if(text_percentage.getText().trim().length()>0)
			{
			double gst = Double.parseDouble(text_percentage.getText());			
			text_cgst.setText(""+df.format(gst/2));
			text_sgst.setText(""+df.format(gst/2));
			text_lowerbound.requestFocus(true);
			}
		}
		
		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblHotelId.setForeground(new Color(SetColor.cColor));
		lblLowerBound.setForeground(new Color(SetColor.cColor));
		lblUpperbound.setForeground(new Color(SetColor.cColor));
		lblGSTName.setForeground(new Color(SetColor.cColor));
		lblPercentage.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));
		lblCategory.setForeground(new Color(SetColor.cColor));
		lblCGST.setForeground(new Color(SetColor.cColor));
		lblSGST.setForeground(new Color(SetColor.cColor));
		
	}
	public void uplmtColor()
	{
		lblCustomerDetails_1.setForeground(new Color(SetColor.mtColor));
		lblEnterGSTDetails.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblPercentage.setFont(new Font(ctFType,ctfProp,ctSize));
		lblHotelId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblLowerBound.setFont(new Font(ctFType,ctfProp,ctSize));
		lblUpperbound.setFont(new Font(ctFType,ctfProp,ctSize));
		lblGSTName.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCategory.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCGST.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSGST.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
		lblEnterGSTDetails.setFont(new Font(stFType,stfProp,stSize));
	}
}

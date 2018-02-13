package com.hms.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.hms.controller.RoomFacilitiesController;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.SearchBoxModel;
import com.hms.validators.DoubleValidator;
import com.hms.validators.NotEmptyValidator;
import com.hms.validators.StringValidator;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class MiscellaneousServices extends JDialog implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_roomFacilitiesID;
	public JTextField text_roomFacilitiesName;
    private JLabel lblRoomFacilitiesName;
	private JButton btnSave;
	private JLabel  lblRoomFacilitiesId;
	private JButton btnSubmit;
	private JLabel lblRoomFacilitiesDesc;
	private JTextField text_roomFacilitiesDesc;
	private JPanel panel;
	private ButtonGroup bg;
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
	private JLabel lblEnterRoomDetails;
	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	public static JTable table;
	DefaultTableModel tableModel;
	String filePath;
	private JLabel lblPrice;
	private JTextField text_price;
	public static SearchBoxModel sbm_consignCom;
	RoomFacilitiesController room_facilities_controller;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private double miscellaneous_total = 0;
	public MiscellaneousServices(){
		//super(new JFrame(),"Miscellaneous Service Details",true);	
		bg = new ButtonGroup();
		
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setSize(3*d.width/4, 3*d.height/8);
		setLocation(d.width/8, d.height/4);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		GridBagLayout gbl_contentPanel = new GridBagLayout();
//		gbl_contentPanel.columnWidths = new int[]{0, 0, 100, 75, 0, 50, 50, 0, 0, 0};
//		gbl_contentPanel.rowHeights = new int[]{50, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0};
//		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
//		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		contentPanel.setLayout(gbl_contentPanel);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 50, 0, 0, 10, 0};
		gbl_panel.rowHeights = new int[]{0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblEnterRoomDetails = new JLabel("Miscellaneous Service Details");
		GridBagConstraints gbc_lblEnterRoomDetails = new GridBagConstraints();
		gbc_lblEnterRoomDetails.fill = GridBagConstraints.BOTH;
		gbc_lblEnterRoomDetails.gridwidth = 3;
		gbc_lblEnterRoomDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterRoomDetails.gridx = 1;
		gbc_lblEnterRoomDetails.gridy = 1;
		panel.add(lblEnterRoomDetails, gbc_lblEnterRoomDetails);
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
		
		lblRoomFacilitiesName = new JLabel("Service Name");
		GridBagConstraints gbc_lblRoomFacilitiesName = new GridBagConstraints();
		gbc_lblRoomFacilitiesName.anchor = GridBagConstraints.WEST;
		gbc_lblRoomFacilitiesName.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomFacilitiesName.gridx = 1;
		gbc_lblRoomFacilitiesName.gridy = 2;
		panel.add(lblRoomFacilitiesName, gbc_lblRoomFacilitiesName);
		
		text_roomFacilitiesName = new JTextField();
		GridBagConstraints gbc_text_roomFacilitiesName = new GridBagConstraints();
		gbc_text_roomFacilitiesName.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_roomFacilitiesName.gridwidth = 2;
		gbc_text_roomFacilitiesName.insets = new Insets(0, 0, 5, 5);
		gbc_text_roomFacilitiesName.gridx = 2;
		gbc_text_roomFacilitiesName.gridy = 2;
		panel.add(text_roomFacilitiesName, gbc_text_roomFacilitiesName);
		text_roomFacilitiesName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_roomFacilitiesName.setColumns(10);
		text_roomFacilitiesName.setInputVerifier(new StringValidator(null, text_roomFacilitiesName, "Enter only text values"));
		
		lblRoomFacilitiesDesc = new JLabel("Description");
		GridBagConstraints gbc_lblRoomFacilitiesDesc = new GridBagConstraints();
		gbc_lblRoomFacilitiesDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRoomFacilitiesDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomFacilitiesDesc.gridx = 1;
		gbc_lblRoomFacilitiesDesc.gridy = 3;
		panel.add(lblRoomFacilitiesDesc, gbc_lblRoomFacilitiesDesc);
		
		text_roomFacilitiesDesc = new JTextField();
		text_roomFacilitiesDesc.setColumns(10);
		GridBagConstraints gbc_text_roomFacilitiesDesc = new GridBagConstraints();
		gbc_text_roomFacilitiesDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_roomFacilitiesDesc.gridwidth = 2;
		gbc_text_roomFacilitiesDesc.insets = new Insets(0, 0, 5, 5);
		gbc_text_roomFacilitiesDesc.gridx = 2;
		gbc_text_roomFacilitiesDesc.gridy = 3;
		panel.add(text_roomFacilitiesDesc, gbc_text_roomFacilitiesDesc);
		//text_roomFacilitiesDesc.setInputVerifier(new StringValidator(null, text_roomFacilitiesDesc, "Enter only text values"));
		
		
		
		text_roomFacilitiesName.addFocusListener(this);
		text_roomFacilitiesID.addFocusListener(this);
		text_roomFacilitiesDesc.addFocusListener(this);
		
		tableModel = new DefaultTableModel(Constants.miscellaneousColNames, 0);
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
	    header.setToolTipStrings(Constants.miscellaneousTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
	       final ActionListener menuListener = new ActionListener() {  
	            public void actionPerformed(ActionEvent event) {  
	                DefaultTableModel dtm = (DefaultTableModel) table.getModel();  
	                int selRow = table.getSelectedRow();  
	 
	                if(selRow > -1){  
	                    dtm.removeRow(selRow);  
	                }  
	            }  
	        };  
	 
	        table.addMouseListener(new MouseAdapter(){  
	            public void mouseReleased(MouseEvent e) {  
	                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ){  
	 
	                    JPopupMenu popup = new JPopupMenu();  
	 
	                    JMenuItem menuItem = new JMenuItem("Delete");  
	                    menuItem.addActionListener(menuListener);  
	 
	                    popup.add(menuItem);  
	                    popup.show(e.getComponent(), e.getX(), e.getY());  
	                }  
	            }  
	        });  
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 5;
		 gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		
		lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 4;
		panel.add(lblPrice, gbc_lblPrice);
		
		text_price = new JTextField();
		GridBagConstraints gbc_text_price = new GridBagConstraints();
		gbc_text_price.gridwidth = 2;
		gbc_text_price.insets = new Insets(0, 0, 5, 5);
		gbc_text_price.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_price.gridx = 2;
		gbc_text_price.gridy = 4;
		panel.add(text_price, gbc_text_price);
		text_price.setColumns(10);
		text_price.setInputVerifier(new DoubleValidator(null, text_price, "Enter only numeric values > 0"));
		text_price.addFocusListener(this);
		
		btnSave = new JButton("Add");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 5;
		panel.add(btnSave, gbc_btnSave);
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.fill = GridBagConstraints.BOTH;
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 5;
		panel.add(btnSubmit, gbc_btnSubmit);
		btnSubmit.setMnemonic(KeyEvent.VK_C);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		btnSubmit.addActionListener(this);
		
		
		//text_roomFacilitiesID.setText(room_facilities_controller.generateFacilitiesId());
		text_roomFacilitiesID.setEditable(false);
	}

	private void setClear1()
	{
		//text_roomFacilitiesID.setText("");
		text_roomFacilitiesName.setText("");
		text_roomFacilitiesDesc.setText("");
		text_price.setText("");
	
	}

 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSubmit)
		{
			if(table.getRowCount()>0)
			{
			BookingCheckout.text_miscellaneous.setText(""+BigDecimalType.roundDown(miscellaneous_total));
			setVisible(false);
			}
			else
				JOptionPane.showMessageDialog(this, "Enter atleast one service", "Error", JOptionPane.ERROR_MESSAGE);
				
		}
		else if(e.getSource()==btnSave)
		{	
		   if(text_roomFacilitiesName.getText().trim().length()!=0)
		   {
			if(text_roomFacilitiesName.getInputVerifier().verify(text_roomFacilitiesName))
				if(text_price.getInputVerifier().verify(text_price))
				{
					tableModel.addRow(new Object[]{
							text_roomFacilitiesName.getText(),
							text_roomFacilitiesDesc.getText(),
							BigDecimalType.roundDown(Double.parseDouble(text_price.getText()))							
						});
					miscellaneous_total += Double.parseDouble(text_price.getText());
					setClear1();
					text_roomFacilitiesName.requestFocus(true);
				}
		   }
		   else
		   {
			   JOptionPane.showMessageDialog(this, "Enter the service name", "Error", JOptionPane.ERROR_MESSAGE);
			   text_roomFacilitiesName.requestFocus(true);
		   }
		   
			
		}

			}
 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getSource()==text_roomFacilitiesName)
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
		
	}
	public void uplmtColor()
	{
		lblEnterRoomDetails.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblRoomFacilitiesId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomFacilitiesDesc.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomFacilitiesName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblPrice.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSubmit.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblEnterRoomDetails.setFont(new Font(stFType,stfProp,stSize));
	}
	
	
	}

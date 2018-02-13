package com.hms.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.hms.controller.BookingCashTransactionController;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.util.TableColumnAdjuster;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;


public class BookingCashTransactions extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	BookingCashTransactionController obj_bct;
	private JScrollPane scrollPane;
	JLabel lblBookingTransactions;
	JLabel transExcel;
	String filePath;
	private JComboBox combo_search;
	SearchBoxModel sbm_consignCom;
	public static JButton btnSave;
	
	
	/**
	 * Create the panel.//you have constructed the table without table model.
	 */
	public BookingCashTransactions() {
	
		setBackground(Color.BLACK);
		setForeground(Color.ORANGE);
 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		
		
		btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 0;
		add(btnSave, gbc_btnSave);
		

		tableModel = new DefaultTableModel(Constants.bookingTransactionColumnNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
			if(table.getRowCount()>1)
			{
				
				return false;

			}
			else
			{
				if(column == 0)
					return false;
				else
				   return true;  
			}
			}
		};
		
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.bTransactionsTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );	
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.adjustColumns();
		
		obj_bct = new BookingCashTransactionController(tableModel, table);
		lblBookingTransactions = new JLabel("Booking Transactions");
		GridBagConstraints gbc_lblBookingTransactions = new GridBagConstraints();
		gbc_lblBookingTransactions.anchor = GridBagConstraints.EAST;
		gbc_lblBookingTransactions.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingTransactions.gridx = 0;
		gbc_lblBookingTransactions.gridy = 0;
		add(lblBookingTransactions, gbc_lblBookingTransactions);
		
		combo_search = new JComboBox();
		GridBagConstraints gbc_combo_search = new GridBagConstraints();
		gbc_combo_search.anchor = GridBagConstraints.EAST;
		gbc_combo_search.insets = new Insets(0, 0, 5, 5);
		gbc_combo_search.gridx = 1;
		gbc_combo_search.gridy = 0;
		combo_search.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	obj_bct.retrieveAllTransactions(DatabaseConstants.ALL_BOOKING_PAYMENTMODE, Constants.CASH);
		            }
		            else
		            {		            	
		            	obj_bct.retrieveTransaction(DatabaseConstants.ALL_BOOKING_ID, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.ALL_BOOKING_PAYMENTMODE, Constants.CASH);
		combo_search.setModel(sbm_consignCom);
		combo_search.addItemListener(sbm_consignCom);
		add(combo_search, gbc_combo_search);
		
		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblBookingTransactions1 = new GridBagConstraints();
		gbc_lblBookingTransactions1.insets = new Insets(0, 0, 5, 0);
		gbc_lblBookingTransactions1.gridx = 3;
		gbc_lblBookingTransactions1.gridy = 0;
		add(transExcel, gbc_lblBookingTransactions1);		
		
		
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 4;
		 gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		
		obj_bct.retrieveAllTransactions(DatabaseConstants.ALL_BOOKING_PAYMENTMODE, Constants.CASH);
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		lblBookingTransactions.setForeground(new Color(SetColor.mtColor));
		lblBookingTransactions.setFont(new Font(SFont.stFType, SFont.stfProp, SFont.stSize));
		
//		transExcel = new JLabel("");
//		MainPage.panel_1.add(transExcel,MainPage.gbc_lblNewLabel);
		
		transExcel.setIcon(new ImageIcon(MainPage.class.getResource("/images/excel.png")));
		transExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
	
			
                try {
       			FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			filePath=fd.getDirectory()+fd.getFile();
     			if(!filePath.equals("nullnull"))
     			{
                    ExcelExporter.fillData(table, filePath,"Booking Transactions");
                    JOptionPane.showMessageDialog(null, "Data saved at " +filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(filePath));  
     			}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
		btnSave.setVisible(false);
		btnSave.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== btnSave){
			int rowCount = tableModel.getRowCount();
			int colCount = tableModel.getColumnCount();
			int cl = 1;
				if(compareRows())
					JOptionPane.showMessageDialog(this, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
				else
				{
					
					Connection con = DBConnection.getDBConnection();
					try {
						PreparedStatement pst=con.prepareStatement(DatabaseConstants.UPDATE_BOOKING);
						for(int i=0;i<rowCount;i++)
							for(int j=0;j<colCount;j++)
							{
								pst.setString(cl, ""+tableModel.getValueAt(i, j));
								if(cl==2||cl==3||cl==4)
								{
								SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
								java.util.Date date = sdf1.parse(""+tableModel.getValueAt(i, j));
								java.sql.Date sqlDate = new java.sql.Date(date.getTime());
								pst.setDate(cl, sqlDate);
								}
								cl++;
							}
						pst.setString(cl, ""+tableModel.getValueAt(0, 0));
						
						int s = pst.executeUpdate();
						if(s>0)
						{
							JOptionPane.showMessageDialog(this, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
							obj_bct.retrieveAllTransactions(DatabaseConstants.ALL_BOOKING_PAYMENTMODE, Constants.CASH);
							combo_search.setSelectedItem("");
						}
						else
							JOptionPane.showMessageDialog(this, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
								
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ParseException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
					
				}
				
		}
	}
	
	
	public boolean compareRows(){
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		boolean rowequals = false;
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.ALL_BOOKING_ID);
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

}

package com.hms.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
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
import java.text.ParseException;

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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import com.hms.controller.BookingTransactionController;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hms.util.TableDialog;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;


public class BookingTransactions extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	public static BookingTransactionController btc;
	private JScrollPane scrollPane;
	JLabel transExcel;
	String filePath;
	public static JComboBox combo_search;
	public static SearchBoxModel sbm_consignCom;
	public static JButton btnSubmit;
	public static JLabel lblRows;
	private JCheckBox chckbxCash;
	private JCheckBox chckbxCard;
	private JCheckBox chckbxTax;
	public static ButtonGroup bg;
	private JCheckBox chckbxTaxless;
	public static JCheckBox chckbxAll;
	private JCheckBox chckbxBooked;
	private JCheckBox chckbxCheckin;
	private JCheckBox chckbxCheckout;
	private JCheckBox chckbxCanceled;
	private static String sheetName = "ALL";
	private JLabel lblBack;
	
	/**
	 * Create the panel.//you have constructed the table without table model.
	 */
	public BookingTransactions(final MainPage mpg) {
	
		setBackground(Color.BLACK);
		setForeground(Color.ORANGE);
 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\back.png"));
		GridBagConstraints gbc_lblBack = new GridBagConstraints();
		gbc_lblBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBack.insets = new Insets(0, 0, 5, 5);
		gbc_lblBack.gridx = 0;
		gbc_lblBack.gridy = 1;
		add(lblBack, gbc_lblBack);
		lblBack.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseReleased(MouseEvent e)
			{
				
				ReportContainer obj = new ReportContainer(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
		});
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 1;
		gbc_lblRows.gridy = 1;
		add(lblRows, gbc_lblRows);
		
		bg = new ButtonGroup();
		
		chckbxAll = new JCheckBox("ALL");
		GridBagConstraints gbc_chckbxAll = new GridBagConstraints();
		gbc_chckbxAll.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAll.gridx = 3;
		gbc_chckbxAll.gridy = 1;
		add(chckbxAll, gbc_chckbxAll);
		chckbxAll.addActionListener(this);
		
		chckbxCash = new JCheckBox("CASH");
		GridBagConstraints gbc_chckbxCash = new GridBagConstraints();
		gbc_chckbxCash.anchor = GridBagConstraints.EAST;
		gbc_chckbxCash.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCash.gridx = 4;
		gbc_chckbxCash.gridy = 1;
		add(chckbxCash, gbc_chckbxCash);
		chckbxCash.setVisible(false);
		
		
		chckbxCard = new JCheckBox("CARD");
		GridBagConstraints gbc_chckbxCard = new GridBagConstraints();
		gbc_chckbxCard.anchor = GridBagConstraints.EAST;
		gbc_chckbxCard.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCard.gridx = 5;
		gbc_chckbxCard.gridy = 1;
		add(chckbxCard, gbc_chckbxCard);
		
		chckbxTax = new JCheckBox("CASH WITH TAX");
		GridBagConstraints gbc_chckbxTax = new GridBagConstraints();
		gbc_chckbxTax.anchor = GridBagConstraints.EAST;
		gbc_chckbxTax.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTax.gridx = 6;
		gbc_chckbxTax.gridy = 1;
		add(chckbxTax, gbc_chckbxTax);
		
		
		chckbxTaxless = new JCheckBox("CASH WITH OUT TAX");
		GridBagConstraints gbc_chckbxTaxless = new GridBagConstraints();
		gbc_chckbxTaxless.anchor = GridBagConstraints.EAST;
		gbc_chckbxTaxless.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTaxless.gridx = 7;
		gbc_chckbxTaxless.gridy = 1;
		add(chckbxTaxless, gbc_chckbxTaxless);
		chckbxTaxless.setVisible(true);
		chckbxTaxless.setVisible(false);
		
		chckbxBooked = new JCheckBox("BOOKED");
		GridBagConstraints gbc_chckbxBooked = new GridBagConstraints();
		gbc_chckbxBooked.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxBooked.gridx = 8;
		gbc_chckbxBooked.gridy = 1;
		add(chckbxBooked, gbc_chckbxBooked);
		
		chckbxCheckin = new JCheckBox("CHECK-IN");
		GridBagConstraints gbc_chckbxCheckin = new GridBagConstraints();
		gbc_chckbxCheckin.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCheckin.gridx = 9;
		gbc_chckbxCheckin.gridy = 1;
		add(chckbxCheckin, gbc_chckbxCheckin);
		
		chckbxCheckout = new JCheckBox("CHECK-OUT");
		GridBagConstraints gbc_chckbxCheckout = new GridBagConstraints();
		gbc_chckbxCheckout.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCheckout.gridx = 10;
		gbc_chckbxCheckout.gridy = 1;
		add(chckbxCheckout, gbc_chckbxCheckout);
		
		chckbxCanceled = new JCheckBox("CANCELED");
		GridBagConstraints gbc_chckbxCanceled = new GridBagConstraints();
		gbc_chckbxCanceled.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCanceled.gridx = 11;
		gbc_chckbxCanceled.gridy = 1;
		add(chckbxCanceled, gbc_chckbxCanceled);

		chckbxCash.addActionListener(this);
		chckbxCard.addActionListener(this);
		chckbxTax.addActionListener(this);
		chckbxTaxless.addActionListener(this);
		chckbxBooked.addActionListener(this);
		chckbxCheckin.addActionListener(this);
		chckbxCheckout.addActionListener(this);
		chckbxCanceled.addActionListener(this);
		bg.add(chckbxCash);
		bg.add(chckbxCard);
		bg.add(chckbxTax);
		bg.add(chckbxTaxless);
		bg.add(chckbxAll);
		bg.add(chckbxBooked);
		bg.add(chckbxCheckin);
		bg.add(chckbxCheckout);
		bg.add(chckbxCanceled);
		chckbxAll.setSelected(true);
		
		btnSubmit = new JButton("Save");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 13;
		gbc_btnSubmit.gridy = 1;
		add(btnSubmit, gbc_btnSubmit);
		btnSubmit.setVisible(false);
		

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
		JTableHeader table_header = table.getTableHeader();
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.bTransactionsTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );	
//		TableColumnAdjuster tca = new TableColumnAdjuster(table);
//		tca.adjustColumns();
//		tca.setColumnHeaderIncluded(false);
		TableDialog.tableRecordDetails(mpg, table, DatabaseConstants.BOOKING_ID);

		 
		btc = new BookingTransactionController(tableModel, table);
		
		btc.retrieveAll();
			
		
		scrollPane = new JScrollPane(table);	
		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 15;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		

		combo_search = new JComboBox();
		GridBagConstraints gbc_combo_search = new GridBagConstraints();
		gbc_combo_search.anchor = GridBagConstraints.EAST;
		gbc_combo_search.insets = new Insets(0, 0, 5, 5);
		gbc_combo_search.gridx = 12;
		gbc_combo_search.gridy = 1;
		combo_search.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	btc.retrieveAll();
		            	chckbxAll.setSelected(true);
		            }
		            else
		            {	
		            	
		                	   		
		            	btc.retrieve(DatabaseConstants.TABLE_BOOKING_ID, ""+combo_search.getSelectedItem());
		            	bg.clearSelection();
	             
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		if(MainPage.user_role.equals(Constants.ADMIN)&&MainPage.user_mode.equals(Constants.MODE_OFF))
			sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.BOOKING_ID);
		else
			sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.BOOKING_ID_USER);	
		combo_search.setModel(sbm_consignCom);
		combo_search.addItemListener(sbm_consignCom);
		combo_search.addPopupMenuListener(sbm_consignCom);
		
		add(combo_search, gbc_combo_search);

		
				
		transExcel = new JLabel();
		GridBagConstraints gbc_lblBookingTransactions1 = new GridBagConstraints();
		gbc_lblBookingTransactions1.insets = new Insets(0, 0, 5, 0);
		gbc_lblBookingTransactions1.gridx = 14;
		gbc_lblBookingTransactions1.gridy = 1;
		add(transExcel, gbc_lblBookingTransactions1);		
		
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
                          ExcelExporter.fillData(table, filePath, sheetName);
                          JOptionPane.showMessageDialog(null, "Data saved at "+filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                          Desktop.getDesktop().open(new File(filePath));
            		}                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
			public void mouseEntered(MouseEvent e)
			{
				transExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		setCompBackground(new Color(SetColor.bkColor));
		setcColor(new Color(SetColor.cColor));
		setcFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
	

		if(MainPage.user_role.equals(Constants.ADMIN)&&MainPage.user_mode.equals(Constants.MODE_OFF))
		{
			chckbxCash.setVisible(true);
			chckbxTaxless.setVisible(true);
		}
		else
		{
			chckbxCash.setVisible(false);
			chckbxTaxless.setVisible(false);			
		}

		
		
		btnSubmit.setVisible(false);
		btnSubmit.addActionListener(this);
	}




	public void setCompBackground(Color clr)
	{
		setBackground(clr);
		table.setBackground(clr);
		scrollPane.setBackground(clr);
		scrollPane.getViewport().setBackground(clr);
		
	}
	public void setcFont(Font fnt)
	{
		lblRows.setFont(fnt);
		table.setFont(fnt);
	}
	public void setcColor(Color clr)
	{
		table.setForeground(clr);
		lblRows.setForeground(clr);
		chckbxCash.setForeground(clr);
		chckbxCard.setForeground(clr);
		chckbxTax.setForeground(clr);
		chckbxTaxless.setForeground(clr);
		chckbxAll.setForeground(clr);
		chckbxBooked.setForeground(clr);
		chckbxCheckin.setForeground(clr);
		chckbxCheckout.setForeground(clr);
		chckbxCanceled.setForeground(clr);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSubmit)
		{
			try {
				btc.updateService(DatabaseConstants.UPDATE_TABLE_BOOKING, DatabaseConstants.TABLE_BOOKING_COLS, ""+combo_search.getSelectedItem());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == chckbxCash)
		{
			sheetName = "CASH";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_MODE, Constants.CASH);
		}
		else if(e.getSource() == chckbxCard)
		{
			sheetName = "CARD";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_MODE, Constants.CARD);
		}
		else if(e.getSource() == chckbxTax)
		{
			sheetName = "CASH WITH TAX";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_MODE_CASH_TAX, Constants.CASH);
		}
		else if(e.getSource() == chckbxTaxless)
		{
			sheetName = "CASH WITH OUT TAX";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_MODE_TAX_LESS, Constants.CASH);
		}
		else if(e.getSource() == chckbxAll)
		{
			sheetName = "ALL";
				btc.retrieveAll();				
		}
		else if(e.getSource() == chckbxBooked)
		{
			sheetName = "BOOKED";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_STATUS, Constants.BOOKED);
		}
		else if(e.getSource() == chckbxCheckin)
		{
			sheetName = "CHECK IN";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_STATUS, Constants.CHECKIN);
		}
		else if(e.getSource() == chckbxCheckout)
		{
			sheetName = "CHECK 0UT";
			if(MainPage.user_role.equals(Constants.ADMIN)&&MainPage.user_mode.equals(Constants.MODE_OFF))
				btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_STATUS, Constants.CHECKOUT);
			else
				btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_STATUS_USER, Constants.CHECKOUT);

		}
		else if(e.getSource() == chckbxCanceled)
		{
			sheetName = "CANCELLED";
			btc.retrieveAll(DatabaseConstants.TABLE_BOOKING_STATUS, Constants.CANCEL);
		}
	}
	
	

}

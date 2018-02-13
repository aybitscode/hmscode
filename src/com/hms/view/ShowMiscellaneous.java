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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.controller.ShowMiscellaneousController;
import com.hms.util.Constants;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class ShowMiscellaneous extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.sql.Date tdate;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
	private JScrollPane scrollPane;
	GridBagConstraints gbc_scrollPane;
	public static JTable table;
	DefaultTableModel tableModel;
	String filePath;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	public ShowMiscellaneous(String query, String param){
		//super(new JFrame(),"Miscellaneous Service Details",true);	
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setSize(3*d.width/6, 3*d.height/8);
		setLocation(d.width/4, d.height/4);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
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
		
		ShowMiscellaneousController obj_mis = new ShowMiscellaneousController(tableModel, table);
		obj_mis.retriveAll(query, param);
		
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
		 gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);

	}


 

 
	public void uplcColor()
	{

		
	}
	public void uplmtColor()
	{
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{

	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}
	
	
	}

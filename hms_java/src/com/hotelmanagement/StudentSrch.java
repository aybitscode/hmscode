package com.hotelmanagement;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.hms.util.DBConnection;

public class StudentSrch extends JPanel implements ItemListener,FocusListener,ActionListener{
	/**
	 * Create the panel.
	 */
	JRadioButton rdbtnByBalance;
	JRadioButton rdbtnAll;
	ButtonGroup bg;
	Statement stmt;
	ResultSet rs,rk;
	String s,cors="----Select----"; 
	String syear="----Select----";
	JComboBox <String>choice,choice_1;
	JButton btnNewButton;
	String slno,fn="",content="";
	private JScrollPane ascroll,idscroll;
	DefaultTableModel model;
	JTable srchtable,srchidtable;
	int ch=0,yrs,kkk=0,cryr,upd=0;
	static int promStatus;
	float curryear=0.00f;
	float arrears=0.00f,amtpd=0.00f;
	public int rbal;
	private JLabel label_5;
	JButton btnNewButton_1;
	private JRadioButton rdbtnById;
	private JLabel lblEnterTheId;
	private JLabel lblSelect,lblYear;
	
	private JTextField textField_1;
	private JTextField textField_9;
	ImageIcon icon;
	FileInputStream fis;
	File img;
	GridBagLayout gridBagLayout;
	private JPanel panel;
	private JLabel lblSearchStudentDetails;
	private JButton btnBack;
	private JLabel lblSelectSearchCategory;
	TableCellRenderer lblRend;
	Object[] values1;
    private boolean DEBUG = false;
    GridBagConstraints gbc_scrollPane;
    JLabel srchExcel,srchWord;
    MyTableModel mb;
	MainPage mpg;
      JComboBox<String> comboBox;
      private JPanel panel_1;
      private JButton btnPromote;
      Connection con = DBConnection.getDBConnection();
      public StudentSrch(MainPage mpg) {
		this.mpg=mpg;
		lblRend=new JTableLabelRend();
		try{
			//mpg.data=new SqlDataCon();
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}catch(Exception e){JOptionPane.showMessageDialog(this, ""+e,"Check",JOptionPane.ERROR_MESSAGE);}
		String columnNames[]={"SL NO","ID","STUDENT NAME","COURSE","YEAR OF JOIN","TOTAL FEE","CURRENT YEAR","ARREARS","BALANCE","AMOUNT PAID"};
	    String[] toolTipStr = { "Serial Number", "Student Id", "Student Name","Course","Year of join", "Total fee","Current amount","Previous years balance","Balance of previous years and current year","Amount paid till current year"};

	
		bg=new ButtonGroup();
		
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblSearchStudentDetails = new JLabel("Search Student Details");
		GridBagConstraints gbc_lblSearchStudentDetails = new GridBagConstraints();
		gbc_lblSearchStudentDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchStudentDetails.gridwidth = 2;
		gbc_lblSearchStudentDetails.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchStudentDetails.gridx = 0;
		gbc_lblSearchStudentDetails.gridy = 0;
		panel.add(lblSearchStudentDetails, gbc_lblSearchStudentDetails);
		
		lblSelectSearchCategory = new JLabel("Search Category");
		GridBagConstraints gbc_lblSelectSearchCategory = new GridBagConstraints();
		gbc_lblSelectSearchCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelectSearchCategory.gridwidth = 2;
		gbc_lblSelectSearchCategory.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectSearchCategory.gridx = 0;
		gbc_lblSelectSearchCategory.gridy = 1;
		panel.add(lblSelectSearchCategory, gbc_lblSelectSearchCategory);
		
		rdbtnById = new JRadioButton("Student Id");
		GridBagConstraints gbc_rdbtnById = new GridBagConstraints();
		gbc_rdbtnById.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnById.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnById.gridx = 0;
		gbc_rdbtnById.gridy = 2;
		panel.add(rdbtnById, gbc_rdbtnById);
		bg.add(rdbtnById);
		
		rdbtnById.addItemListener(this);
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.insets = new Insets(0, 0, 5, 0);
		gbc_textField_9.gridx = 1;
		gbc_textField_9.gridy = 3;
		panel.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		textField_9.setVisible(false);
		
		
		lblEnterTheId = new JLabel("Enter the ID");
		GridBagConstraints gbc_lblEnterTheId = new GridBagConstraints();
		gbc_lblEnterTheId.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnterTheId.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterTheId.gridx = 0;
		gbc_lblEnterTheId.gridy = 3;
		panel.add(lblEnterTheId, gbc_lblEnterTheId);
		
		rdbtnAll = new JRadioButton("Course");
		GridBagConstraints gbc_rdbtnAll = new GridBagConstraints();
		gbc_rdbtnAll.anchor = GridBagConstraints.NORTH;
		gbc_rdbtnAll.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnAll.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAll.gridx = 0;
		gbc_rdbtnAll.gridy = 4;
		panel.add(rdbtnAll, gbc_rdbtnAll);
		bg.add(rdbtnAll);
		rdbtnAll.addItemListener(this);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 6;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		lblSelect = new JLabel("Course");
		GridBagConstraints gbc_lblSelect = new GridBagConstraints();
		gbc_lblSelect.anchor = GridBagConstraints.NORTH;
		gbc_lblSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelect.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelect.gridx = 0;
		gbc_lblSelect.gridy = 0;
		panel_1.add(lblSelect, gbc_lblSelect);
		lblSelect.setVisible(false);
		
		choice = new JComboBox();
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 0);
		gbc_choice.gridx = 1;
		gbc_choice.gridy = 0;
		panel_1.add(choice, gbc_choice);
		
		lblYear = new JLabel("Year of join");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.NORTH;
		gbc_lblYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblYear.insets = new Insets(0, 0, 0, 5);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 1;
		panel_1.add(lblYear, gbc_lblYear);
		lblYear.setVisible(false);
		
		choice_1 = new JComboBox();
		GridBagConstraints gbc_choice_1 = new GridBagConstraints();
		gbc_choice_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_1.gridx = 1;
		gbc_choice_1.gridy = 1;
		panel_1.add(choice_1, gbc_choice_1);
		choice_1.addItem("----Select----");
		choice_1.setVisible(false);	
		choice_1.addItemListener(this);
		choice.addItem("----Select----");
		choice.setVisible(false);
		choice.addFocusListener(this);
		choice.addItemListener(this);
		
		rdbtnByBalance = new JRadioButton("Balance");
		GridBagConstraints gbc_rdbtnByBalance = new GridBagConstraints();
		gbc_rdbtnByBalance.anchor = GridBagConstraints.NORTH;
		gbc_rdbtnByBalance.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnByBalance.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnByBalance.gridx = 0;
		gbc_rdbtnByBalance.gridy = 5;
		panel.add(rdbtnByBalance, gbc_rdbtnByBalance);
		bg.add(rdbtnByBalance);
		rdbtnByBalance.addItemListener(this);
		
		label_5 = new JLabel("Limit");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 7;
		panel.add(label_5, gbc_label_5);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 7;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);

		
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 8;
		panel.add(btnBack, gbc_btnBack);
		
		btnPromote = new JButton("Promote");
		GridBagConstraints gbc_btnPromote = new GridBagConstraints();
		gbc_btnPromote.insets = new Insets(0, 0, 0, 5);
		gbc_btnPromote.gridx = 0;
		gbc_btnPromote.gridy = 9;
		panel.add(btnPromote, gbc_btnPromote);
		btnPromote.setVisible(false);
		
		btnNewButton_1 = new JButton("  Print  ");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 9;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addActionListener(this);
		
		btnNewButton = new JButton("Search");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);

		label_5.setVisible(false);
		lblEnterTheId.setVisible(false);
		try
		{
			//data=new SqlDataCon();
			rs=stmt.executeQuery("select coursename from courses");
			while(rs.next())
			{
				choice.addItem(rs.getString(1).toUpperCase());	
			}
			
		}catch(Exception e){JOptionPane.showMessageDialog(this, ""+e, "Error Message", JOptionPane.ERROR_MESSAGE);}
		model = new DefaultTableModel(columnNames,0);
		srchtable = new JTable(model)
		{
			public boolean isCellEditable(int row, int column){  
				   return false;  
				  }  
		};
	
	    ToolTipHeader header = new ToolTipHeader(srchtable.getColumnModel());
	    header.setToolTipStrings(toolTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    srchtable.setTableHeader(header);
	    srchtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    srchtable.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
	    srchtable.getColumn("SL NO").setMaxWidth(50);
	    srchtable.setFillsViewportHeight(true);
		
		ascroll = new JScrollPane(srchtable);
		ascroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ascroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		
		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		add(ascroll,gbc_scrollPane);

		srchExcel = new JLabel("");
		srchExcel.setIcon(new ImageIcon(MainPage.class.getResource("/images/excel.png")));
		srchWord = new JLabel("");
		srchWord.setIcon(new ImageIcon(MainPage.class.getResource("/images/word.png")));
		srchExcel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				System.out.println("from student search srchExcel");
                try {
				String sheet=""+choice.getSelectedItem()+" "+choice_1.getSelectedItem();
       			FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			fn=fd.getDirectory()+fd.getFile();
     			if(!fn.equals("nullnull"))
     			{
     				System.out.println("Helooooooooooooooooooooofrom nulll");
                    fillData(srchtable, fn,sheet);
                    JOptionPane.showMessageDialog(null, "Data saved at " +fn+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(fn));  
     			}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		srchWord.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				System.out.println("from student search srchword");
				try {
				FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			fn=fd.getDirectory()+fd.getFile();
     			if(!fn.equals("nullnull"))
     			{
				getData();
				writeToFile(content, fn);
					JOptionPane.showMessageDialog(null, "Data saved at " +fn+" successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
					Desktop.getDesktop().open(new File(fn));
     			}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					
				}  
			}
		});
		for(int i=2010;i<2050;i++)
			choice_1.addItem(Integer.toString(i));
		
		ascroll.setVisible(false);
		btnNewButton.setEnabled(false);
		btnBack.setEnabled(false);
		
		srchmtColor();
		srchCColor();
		srchbkColor();
		srchCTFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		srchSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		
		btnNewButton.setMnemonic(KeyEvent.VK_H);
		btnBack.setMnemonic(KeyEvent.VK_B);
		btnNewButton_1.setMnemonic(KeyEvent.VK_P);
		
		textField_1.addFocusListener(this);
		textField_9.addFocusListener(this);
		btnNewButton.addActionListener(this);
		btnBack.addActionListener(this);
		btnPromote.addActionListener(this);
		


	}
      void getData()
      {
    	  try{
    		  TableModel model = srchtable.getModel();
    		  for (int i = 0; i < model.getColumnCount(); i++) {
    		   content=content+model.getColumnName(i)+"   ";
    		  }
    		  content=content+"\n";

    		  int j = 0;
    		  for (int i = 0; i < model.getRowCount(); i++) {
    		      for (j = 0; j < model.getColumnCount(); j++) {
    		         content = content + model.getValueAt(i, j).toString()+" ";

    		      }
    		      content = content+"\n";
    		  }
   

    		  }
    		  catch(Exception e){}
    		  
    		

      }
      private  void writeToFile(String content, String path) {
    	  try{
    	  POIFSFileSystem fs = new POIFSFileSystem();
    	  DirectoryEntry directory = fs.getRoot();
    	  directory.createDocument("WordDocument", new ByteArrayInputStream(content.getBytes()));
    	  FileOutputStream out = new FileOutputStream(path);

    	  fs.writeFilesystem(out);
    	  out.close();
    	  }
    	  catch(Exception ex){
    	  System.out.println(ex.getMessage());
    	  }
    	  }
      void fillData(JTable table, String path, String sheet) {
    		System.out.println("Hello from if part");
          try {
          	File file = new File(path);
          	if(!file.exists())
          	{
          		System.out.println("Hello from if part");
              WritableWorkbook workbook1 = Workbook.createWorkbook(file);
              WritableSheet sheet1 = workbook1.createSheet(sheet, 0); 
              TableModel tmodel = table.getModel();

              for (int i = 0; i < tmodel.getColumnCount(); i++) {
                  Label column = new Label(i, 0, tmodel.getColumnName(i));
                  sheet1.addCell(column);
              }
              int j = 0;
              for (int i = 0; i < tmodel.getRowCount()-2; i++) {
                  for (j = 0; j < tmodel.getColumnCount(); j++) {
                      Label row = new Label(j, i + 1, 
                              tmodel.getValueAt(i, j).toString());
                      sheet1.addCell(row);
                  }
              }
              workbook1.write();
              workbook1.close();
          	}
          	else
          	{
          		System.out.println("Hello from else part");
          		 Workbook workbook = Workbook.getWorkbook(file);
          		 WritableWorkbook copy = Workbook.createWorkbook(file, workbook);
                   WritableSheet sheet1 = copy.createSheet(sheet,0); 
                   TableModel tmodel = table.getModel();
                   for (int i = 0; i < tmodel.getColumnCount(); i++) {
                       Label column = new Label(i, 0, tmodel.getColumnName(i));
                       sheet1.addCell(column);
                   }
                   int j = 0;
                   for (int i = 0; i < tmodel.getRowCount()-2; i++) {
                       for (j = 0; j < tmodel.getColumnCount(); j++) {
                           Label row = new Label(j, i + 1, 
                                   tmodel.getValueAt(i, j).toString());
                           sheet1.addCell(row);
                       }
                   }
                   copy.write();
                   copy.close();
          	}
          } catch (Exception ex) {
              ex.printStackTrace();
          }
      }
    public void setUpChoiceColumn(JTable table,
            TableColumn sportColumn) {
    	comboBox=new JComboBox();
		try
		{
			//data=new SqlDataCon();
			rs=stmt.executeQuery("select coursename from courses");
			while(rs.next())
			{
				String crs=rs.getString(1).toUpperCase();
				comboBox.addItem(crs);	
			}
			
		}catch(Exception e){JOptionPane.showMessageDialog(this, ""+e, "Error Message", JOptionPane.ERROR_MESSAGE);}
    	sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
    	//Set up tool tips for the sport cells.
    	DefaultTableCellRenderer renderer =
    			new DefaultTableCellRenderer();
    	renderer.setToolTipText("Click for Course");
    	sportColumn.setCellRenderer(renderer);
    	System.out.println("from setupchoice colu");
}
	public void setAppear(boolean b)
	{
		rdbtnByBalance.setVisible(b);
		rdbtnAll.setVisible(b);
		choice.setVisible(b);
		choice_1.setVisible(b);
		lblSelect.setVisible(b);
		lblYear.setVisible(b);
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==choice)
		{
			cors=(String) choice.getSelectedItem();
		}
		if(arg0.getSource()==rdbtnById)
		{
			btnBack.setEnabled(true);
			btnNewButton.setEnabled(true);
			setAppear(false);
			ascroll.setVisible(false);
			ch=3;
			s="select * from student where id=?";
			lblEnterTheId.setVisible(true);
			textField_9.setVisible(true);
			textField_9.setEditable(true);
			label_5.setVisible(false);
			textField_1.setVisible(false);
			//rdbtnById.setEnabled(false);
	
			
		}
		if(arg0.getSource()==rdbtnByBalance)
		{
		
			btnBack.setEnabled(true);
			btnNewButton.setEnabled(true);
			label_5.setVisible(true);
			s="select id,name,course,year,fee,balance from student where course=? and balance>=? and year=?";
			ch=2;
			textField_9.setVisible(false);
			lblEnterTheId.setVisible(false);
			textField_1.setVisible(true);
			setAppear(true);
			rdbtnById.setVisible(false);
			rdbtnAll.setVisible(false);
			//rdbtnByBalance.setEnabled(false);
		}
		if(arg0.getSource()==rdbtnAll)
		{
			btnBack.setEnabled(true);
			btnNewButton.setEnabled(true);
			textField_9.setVisible(false);
			label_5.setVisible(false);
			textField_1.setVisible(false);
			s="select id,name,course,year,fee,balance from student where course=? and year=?";
			ch=1;
			textField_9.setVisible(false);
			lblEnterTheId.setVisible(false);
			setAppear(true);
			rdbtnByBalance.setVisible(false);
			rdbtnById.setVisible(false);
			//rdbtnAll.setEnabled(false);

	
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnPromote)
		{

			ascroll.setVisible(false);
			btnNewButton_1.setVisible(false);
			srchExcel.setVisible(false);
			srchWord.setVisible(false);
			Promote.cors=(String) choice.getSelectedItem();
			Promote.year=(Integer.parseInt((String) choice_1.getSelectedItem()));
			new Promote(mpg);
		}
		else if(arg0.getSource()==btnBack)
		{
			bg.clearSelection();
			srchExcel.setVisible(false);
			srchWord.setVisible(false);
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0};
			gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0};
			btnNewButton.setEnabled(false);
			 setAppear(false);
			 btnBack.setEnabled(false);
			 
			 if(ascroll instanceof JScrollPane)
			 ascroll.setVisible(false);
			 
			 if(idscroll instanceof JScrollPane)
			 idscroll.setVisible(false);
			 
			 rdbtnById.setVisible(true);
			 rdbtnAll.setVisible(true);
			 rdbtnByBalance.setVisible(true);
			 rdbtnById.setEnabled(true);
			 rdbtnAll.setEnabled(true);
			 rdbtnByBalance.setEnabled(true);
			 lblEnterTheId.setVisible(false);
			 textField_9.setVisible(false);
			 label_5.setVisible(false);
			 textField_1.setVisible(false);
			 textField_9.setText("");
			 btnNewButton_1.setVisible(false);
			 btnPromote.setVisible(false);
		}

		if(arg0.getSource()==btnNewButton)
		{ 
		//mpg.panel_1.add(srchExcel,mpg.gbc_lblNewLabel);
		//mpg.panel_1.add(srchWord,mpg.gbc_label_4);
		btnBack.setEnabled(true);
		Float balance=(float) 0.00,actbal=(float) 0.00;
		int dlg1=0,yoj=0;
		float fee=0.00f;
		float totarrears=0.00f,totcurryear=0.00f,totamtpd=0.00f;
		String name="",id="",course="";
			if(ch==1)
			{

				
			try
			{	
				float totfee=0.0f,totbal=0.0f;
				yrs=Integer.parseInt((String) choice_1.getSelectedItem());
				if(cors.equals("----Select----"))
					throwError();
				
				if(kkk==1)
				{
				int numRows = model.getRowCount();
				for (int i=numRows;i>0;i--) {
					model.removeRow(i-1);
					srchtable.revalidate();
					
					}
				}
					PreparedStatement psmt=con.prepareStatement(s);
					psmt.setString(1,cors);
					psmt.setInt(2,yrs);
					rs=psmt.executeQuery();
					String sql = "select * from student where id =(?)";
					PreparedStatement ps =con.prepareStatement(sql);
					
					int l =1;
					while(rs.next())
					{

						id=rs.getString(1);
						name=rs.getString(2);
						course=rs.getString(3);
						yoj=rs.getInt(4);
						fee=rs.getFloat(5);
						actbal=rs.getFloat(6);
						ps.setString(1,id);
						rk = ps.executeQuery();
						balance=(float) display();
						totfee=totfee+fee;
						totbal=totbal+balance;
					
						totarrears=totarrears+arrears;
						totcurryear=totcurryear+curryear;
						totamtpd=totamtpd+amtpd;
						 if(arrears==0)
						 {
							 if(balance>curryear)
							 {
								 arrears=balance-curryear;
								 totarrears=totarrears+arrears;
							 }
						 }
						if(l<=9)
						{
							slno="0"+l;
							l++;
						}
						else
						{
							slno=""+l++;
						}

						model.addRow(new Object[]{slno,id.toUpperCase(), name.toUpperCase(),course,yoj, fee+"0",curryear+"0",arrears+"0", balance+"0",amtpd+"0"});
						dlg1=1;
						kkk=1;
						choice.requestFocus(true);
					}


					if(dlg1==1)
					{
						model.addRow(new Object[]{"", "", "","","","","",""});
						model.addRow(new Object[]{"Total","","","","", ""+totfee+"0",""+totcurryear+"0" , ""+totarrears+"0",""+totbal+"0",""+totamtpd+"0" });	
						gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
						gridBagLayout.rowWeights = new double[]{1.0, 0.0, 10};
					ascroll.setVisible(true);
					srchExcel.setVisible(true);
					srchWord.setVisible(true);
					btnNewButton_1.setVisible(true);
					btnPromote.setVisible(true);
					btnNewButton_1.setEnabled(true);
					}
					else
					{
					if(ascroll instanceof JScrollPane)
						ascroll.setVisible(false);
					btnNewButton_1.setVisible(false);
					btnPromote.setVisible(false);
					choice.requestFocus(true);
					srchExcel.setVisible(false);
					srchWord.setVisible(false);
					mpg.lblLbltime.setBounds(10,-2,80,20);
					JOptionPane.showMessageDialog(this, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
					updateUI();
					}catch(Exception e){
						System.out.println(""+e);
						JOptionPane.showMessageDialog(this, "Select the details properly", "Error Message", JOptionPane.ERROR_MESSAGE);
						btnNewButton.setEnabled(true);
					}

				}

	 if(ch==2)
		{
			
		try
			{
			btnPromote.setVisible(false);
			yrs=Integer.parseInt((String) choice_1.getSelectedItem());
			if(cors.equals("----Select----"))
				throwError();		
			
			 if(kkk==1)
				{
				int numRows = model.getRowCount();
				for (int i=numRows;i>0;i--) {
					model.removeRow(i-1);
					srchtable.revalidate();
					System.out.println("Validating");
					}
				}
			 	
				PreparedStatement psmt=con.prepareStatement(s);
				psmt.setString(1,cors);
				psmt.setInt(2,Integer.parseInt(textField_1.getText().trim()));
				psmt.setInt(3, Integer.parseInt((String) choice_1.getSelectedItem()));
				rs=psmt.executeQuery();
				String sql = "select * from student where id =(?)";
				PreparedStatement ps = con.prepareStatement(sql);
				int l=1;
				while(rs.next())
				{
					id=rs.getString(1);
					name=rs.getString(2);
					course=rs.getString(3);
					yoj=rs.getInt(4);
					fee=rs.getFloat(5);
					actbal=rs.getFloat(6);
					ps.setString(1,id);
					rk = ps.executeQuery();
					balance=(float) display();
					if(balance>Float.parseFloat(textField_1.getText().trim()))
					{
					if(l<=9)
					{
						slno="0"+l;
						l++;
					}
					else
					{
						slno=""+l++;
					}
					 if(arrears==0)
					 {
						 if(balance>curryear)
						 {
							 arrears=balance-curryear;
							 totarrears=totarrears+arrears;
						 }
					 }
					model.addRow(new Object[]{slno,id.toUpperCase(), name.toUpperCase(),course,yoj, fee+"0",curryear+"0",arrears+"0", balance+"0",amtpd+"0",actbal+"0"});
					kkk=1;
					}
					btnNewButton_1.setVisible(true);
					btnNewButton_1.setEnabled(true);
					choice.requestFocus(true);
				}
				if(l==1)
				{
					btnNewButton_1.setVisible(false);
					choice.requestFocus(true);
					srchExcel.setVisible(false);
					srchWord.setVisible(false);
					JOptionPane.showMessageDialog(this, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
					gridBagLayout.rowWeights = new double[]{1.0, 0.0, 10};
				ascroll.setVisible(true);
				srchExcel.setVisible(true);
				srchWord.setVisible(true);
				}
				updateUI();
			}catch(Exception e){JOptionPane.showMessageDialog(this, "Enter the details properly", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		   		
		}
	 if(ch==3)
	 {
		 textField_9.setEditable(false);
		 btnNewButton.setEnabled(false);
			mb=new MyTableModel();
			srchidtable = new JTable(mb);
			if(kkk==1)
			{
				srchidtable.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
				srchidtable.setFont(new Font(SFont.ctFType,SFont.ctfProp,SFont.ctSize));
				srchidtable.setBackground(new Color(SetColor.bkColor));
				srchidtable.setForeground(new Color(SetColor.cColor));
				srchidtable.getColumn("Photo").setCellRenderer(lblRend);
				srchidtable.getColumn("Update").setCellRenderer(lblRend);
				srchidtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				srchidtable.setFillsViewportHeight(true);
				srchidtable.setAutoCreateRowSorter(false);
				srchidtable.addMouseListener(new JTableButtonMouseListener(srchidtable));
				srchidtable.getTableHeader().setReorderingAllowed(false);
				srchidtable.getColumn("Photo").setMaxWidth(80);
				srchidtable.setRowHeight(80);
			idscroll = new JScrollPane(srchidtable);
		    setUpChoiceColumn(srchidtable, srchidtable.getColumn("Course"));
			idscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			idscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			add(idscroll,gbc_scrollPane);
		 	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
			gridBagLayout.rowWeights = new double[]{1.0, 0.0, 10};
			idscroll.setVisible(true);
			updateUI();
			idscroll.updateUI();
			System.out.println("hi"+srchidtable.getValueAt(0, 2));
			}
	 }

		
		}
		if(arg0.getSource()==btnNewButton_1)
		{
	        MessageFormat header = new MessageFormat("Page {0,number,integer}");
	        try {
		         TableColumn tcol = srchtable.getColumnModel().getColumn(5);
		         TableColumn tcol1 = srchtable.getColumnModel().getColumn(6);
		         TableColumn tcol2 = srchtable.getColumnModel().getColumn(7);
		         srchtable.getColumnModel().removeColumn(tcol);
		         srchtable.getColumnModel().removeColumn(tcol1);
		         srchtable.getColumnModel().removeColumn(tcol2);
		         
		        // table.removeColumn(table.getColumnModel().getColumn(3));
		       //  table.setAutoCreateColumnsFromModel(false);
		       //  removeColumnAndData(table, 0);
		         srchtable.print(JTable.PrintMode.FIT_WIDTH, header, null);
	        } catch (java.awt.print.PrinterException e) {
	        }
		}
		
	}
	class MyDefaultTableModel extends DefaultTableModel {
	    public Vector getColumnIdentifiers() {
	        return columnIdentifiers;
	    }
	}
	public void removeColumnAndData(JTable table, int vColIndex) {
	    MyDefaultTableModel model = (MyDefaultTableModel)table.getModel();
	    TableColumn col = table.getColumnModel().getColumn(vColIndex);
	    int columnModelIndex = col.getModelIndex();
	    Vector data = model.getDataVector();
	    Vector colIds = model.getColumnIdentifiers();

	    // Remove the column from the table
	    table.removeColumn(col);

	    // Remove the column header from the table model
	    colIds.removeElementAt(columnModelIndex);

	    // Remove the column data
	    for (int r=0; r<data.size(); r++) {
	        Vector row = (Vector)data.get(r);
	        row.removeElementAt(columnModelIndex);
	    }
	    model.setDataVector(data, colIds);

	    // Correct the model indices in the TableColumn objects
	    // by decrementing those indices that follow the deleted column
	    Enumeration<TableColumn> enm = table.getColumnModel().getColumns();
	    for (; enm.hasMoreElements(); ) {
	        TableColumn c = (TableColumn)enm.nextElement();
	        if (c.getModelIndex() >= columnModelIndex) {
	            c.setModelIndex(c.getModelIndex()-1);
	        }
	    }
	    model.fireTableStructureChanged();
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
	if(arg0.getSource()==textField_9)
			textField_9.selectAll();
		if(arg0.getSource()==textField_1)
			textField_1.selectAll();
			
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		
		if(arg0.getSource()==textField_9)
		{
			textField_9.setText(textField_9.getText().trim().toUpperCase());
		}		
	}

	public float display()
	{
	
		int jnyear=0,wyr,nyrs=0;
		float fee=0.00f;
		float peryr=0.00f;
		Float bal=(float) 0.0,pamt=(float) 0.0,arrs=(float) 0.0;
		arrears=0.00f;
		amtpd=0.00f;
		try{ 
			if(rk.next())
			{
				fee=rk.getInt("fee");
				jnyear = rk.getInt("year");
				bal=rk.getFloat("balance");
			}
			PreparedStatement pst=con.prepareStatement("select years from courses where coursename=?");
			pst.setString(1,cors);
			ResultSet rkk=pst.executeQuery();
			if(rkk.next())
				nyrs=rkk.getInt(1);
			Calendar now=Calendar.getInstance();
			cryr=now.get(Calendar.YEAR);
			wyr=cryr-jnyear;
				 int n=wyr;
				 peryr=fee/nyrs;
				 pamt=fee-bal;
				 amtpd=(float) pamt;
				 if(amtpd<0)
					 amtpd=0;
				 if(pamt>0)
				 {
				 if(n<1)
					 n=1;
				 if(n>nyrs)
					 n=nyrs;
					 if(n==1)
					 {
						 if(pamt<peryr)
						 bal=peryr-pamt;
						 else 
							 bal=(float) 0;
						 arrs=(float) 0;
					 }
					 else
					 {
						 arrs=(n-1)*peryr-pamt;
						 if(arrs<0)
							 arrs=(float) 0;
						 if(arrs==0)
						 {
							 bal=n*peryr-pamt;
							 if(bal<0)
								 bal=(float) 0;
						 }
						 else
						     bal=peryr+arrs;
					 }
			
				 }
				 else
				 {
					  
				 }
		
	
		}catch(Exception ex){JOptionPane.showMessageDialog(this, "Enter the details correctly"+ex,"Error",JOptionPane.ERROR_MESSAGE);}
		curryear=peryr;
		arrears=arrs;	
		return bal;
	}

	  private class ToolTipHeader extends JTableHeader {
		    String[] toolTips;

		    public ToolTipHeader(TableColumnModel model) {
		      super(model);
		    }

		    public String getToolTipText(MouseEvent e) {
		      int col = columnAtPoint(e.getPoint());
		      int modelCol = getTable().convertColumnIndexToModel(col);
		      String retStr;
		      try {
		        retStr = toolTips[modelCol];
		      } catch (NullPointerException ex) {
		        retStr = "";
		      } catch (ArrayIndexOutOfBoundsException ex) {
		        retStr = "";
		      }
		      if (retStr.length() < 1) {
		        retStr = super.getToolTipText(e);
		      }
		      return retStr;
		    }

		    public void setToolTipStrings(String[] toolTips) {
		      this.toolTips = toolTips;
		    }
		  }
	
		public void srchmtColor()
		{
			lblSearchStudentDetails.setForeground(new Color(SetColor.mtColor));
			
		}
		public void srchCColor()
		{
			lblSelectSearchCategory.setForeground(new Color(SetColor.cColor));
			rdbtnByBalance.setForeground(new Color(SetColor.cColor));
			rdbtnAll.setForeground(new Color(SetColor.cColor));
			srchtable.setForeground(new Color(SetColor.cColor));
			label_5.setForeground(new Color(SetColor.cColor));
			rdbtnById.setForeground(new Color(SetColor.cColor));
			lblEnterTheId.setForeground(new Color(SetColor.cColor));
			lblSelect.setForeground(new Color(SetColor.cColor));
			lblYear.setForeground(new Color(SetColor.cColor));
		
		}
		public void srchbkColor()
		{
			setBackground(new Color(SetColor.bkColor));
			panel.setBackground(new Color(SetColor.bkColor));
			panel_1.setBackground(new Color(SetColor.bkColor));
			srchtable.setBackground(new Color(SetColor.bkColor));
			rdbtnById.setBackground(new Color(SetColor.bkColor));
			rdbtnAll.setBackground(new Color(SetColor.bkColor));
			rdbtnByBalance.setBackground(new Color(SetColor.bkColor));
			
		}
		public void srchCTFont(String ctFType,int ctfProp,int ctSize)
		{
			lblSelectSearchCategory.setFont(new Font(ctFType,ctfProp,ctSize));
			rdbtnByBalance.setFont(new Font(ctFType,ctfProp,ctSize));
			rdbtnAll.setFont(new Font(ctFType,ctfProp,ctSize));
			srchtable.setFont(new Font(ctFType,ctfProp,ctSize));
			label_5.setFont(new Font(ctFType,ctfProp,ctSize));
			rdbtnById.setFont(new Font(ctFType,ctfProp,ctSize));
			lblEnterTheId.setFont(new Font(ctFType,ctfProp,ctSize));
			lblSelect.setFont(new Font(ctFType,ctfProp,ctSize));
			lblYear.setFont(new Font(ctFType,ctfProp,ctSize));
			btnNewButton.setFont(new Font(ctFType,ctfProp,ctSize));
			btnBack.setFont(new Font(ctFType,ctfProp,ctSize));
			btnNewButton_1.setFont(new Font(ctFType,ctfProp,ctSize));
		}
		public void srchSTFont(String stFType,int stfProp,int stSize)
		{
			lblSearchStudentDetails.setFont(new Font(stFType,stfProp,stSize));
		}
		  public static void throwError() throws InterruptedException {
			  throw new InterruptedException();
			  } 
		   private	class JTableLabelRend implements TableCellRenderer {	
	   			JLabel butt;
	   			JButton btn;
		   		@Override 
		   		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){

		   			if(column==0)
		   			butt = (JLabel)value;
		   			else if(column==10)
		   			{
		   			btn=(JButton)value;
		   			}
		   			if (isSelected) {
		 				btn.setForeground(table.getSelectionForeground());
		   				btn.setBackground(table.getSelectionBackground());
		 	
		   		    } else {
		   			
		   		    	//btn.setForeground(table.getForeground());
		   		    	//btn.setBackground(UIManager.getColor("Button.background"));
		   		    }
		   			if(column==0)
		   			return butt;	
		   			else 
		   			return btn;
		   		}
		   	}
		   

		   
		   
			class JTableButtonMouseListener extends MouseAdapter {
		   		private final JTable table;
		  
		   		public JTableButtonMouseListener(JTable table) {
		   			this.table = table;
		   		}

		   		public void mouseReleased(MouseEvent e) {
		   			if(MainPage.labelUser.getText().trim().equalsIgnoreCase("ADMIN"))
		   			{
		   	   			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
		   	   			int row    = e.getY()/table.getRowHeight(); 
		   	   			String id,name,course,fname,email,mobile;
		   	   			int year ;
		   	   			float balance,fee;
					if(column==0)
		   			  {
		   				JLabel lb=new JLabel("");	
		   				try{
		   				 FileDialog fd=new FileDialog(new JFrame(),"Open",FileDialog.LOAD);
		   				fd.setVisible(true);
		   				fn=fd.getDirectory()+fd.getFile();
		   				lb.setIcon(new ImageIcon(fn));

						if(fn.equals("nullnull"))
						{
						upd=0;
						}
						else
						{
			   				table.setValueAt(lb, row, 0);
						}
		   				}catch(Exception ee){System.out.println(ee);}
		   			  }
					if(column==10)
					{
						name=(String) table.getValueAt(row, 2);
						if(JOptionPane.showConfirmDialog(null,"Do you wish to update "+"'"+name+"'", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
						{
							id=((String) table.getValueAt(row, 1)).trim();
							fname=((String) table.getValueAt(row, 3)).trim();
							email=((String) table.getValueAt(row, 4)).trim();
							mobile=((String) table.getValueAt(row, 5)).trim();
							course=((String) table.getValueAt(row, 6)).trim();
							System.out.println(course);
							year=(int) Integer.parseInt((String)table.getValueAt(row, 7));
							fee= (float) Float.parseFloat(((String)  table.getValueAt(row, 8)).trim());
							balance= (float) Float.parseFloat(((String) table.getValueAt(row, 9)).trim());

							try{
								if(!fn.equals(""))
								{
								if(!fn.equals("nullnull"))
								{
								img=new File(fn);
								FileInputStream fs=new FileInputStream(fn);
								byte b[]=new byte[fs.available()];
								upd=1;
								fs.read(b);
								}
								}
								if(upd==1)
								{	
								PreparedStatement pst=con.prepareStatement("update student set img=?,id=?,name=?,fname=?,email=?,mobile=?,course=?,year=?,fee=?,balance=? where id=?");
								fis=new FileInputStream(img);
								pst.setBinaryStream(1, (InputStream)fis, (int)(img.length()));
								pst.setString(2,id.toUpperCase().trim());
								pst.setString(3,name.toUpperCase().trim());
								pst.setString(4,fname.toUpperCase().trim());
								pst.setString(5,email.trim());
								pst.setString(6,mobile.trim());
								pst.setString(7,course.toString().toUpperCase().trim());
								pst.setInt(8,year);
								pst.setFloat(9,fee);
								pst.setFloat(10,balance);
								pst.setString(11, textField_9.getText().trim().toUpperCase());
								pst.execute();
								JOptionPane.showMessageDialog(null,"Data Updated Successfully.","Success",JOptionPane.INFORMATION_MESSAGE);	
								}
								else
								{
									PreparedStatement pst=con.prepareStatement("update student set id=?,name=?,fname=?,email=?,mobile=?,course=?,year=?,fee=?,balance=? where id=?");
									pst.setString(1,id.toUpperCase().trim());
									pst.setString(2,name.toUpperCase().trim());
									pst.setString(3,fname.toUpperCase().trim());
									pst.setString(4,email.trim());
									pst.setString(5,mobile.trim());
									pst.setString(6,course.toString().toUpperCase().trim());
									pst.setInt(7,year);
									pst.setFloat(8,fee);
									pst.setFloat(9,balance);
									pst.setString(10,textField_9.getText().trim().toUpperCase());
									pst.execute();
									JOptionPane.showMessageDialog(null,"Data Updated Successfully.","Success",JOptionPane.INFORMATION_MESSAGE);	
								}
								}catch(Exception ee){
									JOptionPane.showMessageDialog(null,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);}
						}
		
					}
		   			
		   			}
		   			else
		   			{
		   				JOptionPane.showMessageDialog(null,"No Privileges, login as admin","Message",JOptionPane.INFORMATION_MESSAGE);
		   			}
		   		}
		   		
		   	}
		    class MyTableModel extends AbstractTableModel {
		    	 /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				List<Row> rows = new ArrayList<Row>();
		    	private String columnNames[]={"Photo","ID","Name","Father's Name","Email-Id","Mobile No","Course","Year","Fee","Balance","Update"};
		    	Object [][] data;
		    	public MyTableModel()
		    	{
		    		try {
			    		data = new Object[1][11];
		  				PreparedStatement pmt=con.prepareStatement("select * from student where id=?");
		  				pmt.setString(1,textField_9.getText().trim());
		  				rk=pmt.executeQuery();                   
		    		for(int i=0;i<1;i++)
		    		{
							rk.next();
							for(int j=0;j<10;j++)
								{
								if(j!=0)
								{
									String temp=rk.getString(j+1);
									if(temp.equals(""))
									{
										temp="NULL";
									}
									data[i][j]=temp;
									System.out.println("the data is"+data[i][j]);
								}
								else if(j==0)
								{
								    byte[] imagedata = rk.getBytes(j+1);
								    Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
								    icon =new ImageIcon(img);
								    data[i][j]=new JLabel(icon);
								}

								}
							data[i][10]=new JButton("Update");
							Row row = new Row(data);
							addRows(row);
							kkk=1;
		    		}
		    		} catch (SQLException e) {
						// TODO Auto-generated catch block
		    			kkk=0;
		    			textField_9.setEditable(true);
		    			btnNewButton.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Student doesn't exist","Message", JOptionPane.INFORMATION_MESSAGE);
					
					}
	
		    	}

		        public int getColumnCount() {
		            return columnNames.length;
		        }

		        public int getRowCount() {
		        	return data.length;
		        }

		        public String getColumnName(int col) {
		        	return columnNames[col];
		        }

		        public Object getValueAt(int row, int col) {
		            return data[row][col];
		        }

		        /*
		         * JTable uses this method to determine the default renderer/
		         * editor for each cell.  If we didn't implement this method,
		         * then the last column would contain text ("true"/"false"),
		         * rather than a check box.
		         */
		        public Class getColumnClass(int c) {
		            return getValueAt(0, c).getClass();
		        }

		        /*
		         * Don't need to implement this method unless your table's
		         * editable.
		         */
		        public boolean isCellEditable(int row, int col) {
		            //Note that the data/cell address is constant,
		            //no matter where the cell appears onscreen.
		            if (col==8) {
		                return false;
		            } 
		            else 
		            {
		                return true;
		            }
		        }
		      	public void removeRow(int n)
		      	{
		      		rows.remove(n);
		      		fireTableRowsDeleted(n,n);
		      	}
		      	public void addRows(Row row)
		      	{
		      		rows.add(row);
		      		//fireTableRowsInserted(0,row);
		      	}

		        /*
		         * Don't need to implement this method unless your table's
		         * data can change.
		         */
		        public void setValueAt(Object value, int row, int col) {
		            if (DEBUG) {
		                System.out.println("Setting value at " + row + "," + col
		                                   + " to " + value
		                                   + " (an instance of "
		                                   + value.getClass() + ")");
		            }

		            data[row][col] = value;
		            fireTableCellUpdated(row, col);

		            if (DEBUG) {
		                System.out.println("New value of data:");
		                printDebugData();
		            }
		        }
		        

		        private void printDebugData() {
		            int numRows = getRowCount();
		            int numCols = getColumnCount();

		            for (int i=0; i < numRows; i++) {
		                System.out.print("    row " + i + ":");
		                for (int j=0; j < numCols; j++) {
		                    System.out.print("  " + data[i][j]);
		                }
		            }
		        }
			    }

		    class Row {
		        private final Object[][]  values;

		        public Row(Object[][] values) {
		            this.values = values;
		        }

		        public int getSize() {
		            return values.length;
		        }

		        public Object getValue(int i) {
		        
		            return values[i];
		           
		        }
		  

		    }
}



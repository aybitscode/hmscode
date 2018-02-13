package com.hotelmanagement;

import java.awt.Color;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
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
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.hms.util.DBConnection;
public class TransactionSrch extends JPanel implements ItemListener,ActionListener,FocusListener{
	private JTextField textField;
	ButtonGroup abg;
	GridBagConstraints gbc_scrollPane;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnAll;
	private JScrollPane allscroll;
	private JLabel lblCourse;
	private JComboBox <String>choice,choice_1;
	private JLabel lblYear;
 	DefaultTableModel tmodel;
	ResultSet rs,rk;
	Statement st,stmt;
	String id,name,course,date,cors="",img="",sdm,sdate,s,slno,fn="",content="";
	int uid,rows=0,ch=0,yrs,rdb=1,year,dlgcntrl=0,tglQuery=0,kkk=0;
	float pamt,bal,fee;
	int tiid[];
	JTable tab;
	int kl=5,dl=2,pl=0;
	java.sql.Date tdate;
	private JButton btnSearch;
	MainPage mpg;
	private JButton btnBack;
	private JPanel panel;
	private JRadioButton rdbtnTransactionId;
	private JRadioButton rdbtnDate;
	private JLabel lblSearchTransactions;
	private JLabel lblEnterTransactionId;
	private JLabel lblSearchCategory;
	GridBagLayout gridBagLayout;
	private JToggleButton tglbtnSingleDate;
	private JToggleButton tglbtnMultiple;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblFrom;
	private JDatePickerImpl datePicker_1;
	private JLabel lblTo,tranExcel,tranWord;
	private JDatePickerImpl datePicker_2;
	JDatePanelImpl datePanel,datePanel_1;
   	String columnNames[]={"SL NO","TRANSACTION ID","STUDENT ID","NAME","COURSE","YEAR OF JOIN","DATE","AMOUNT PAID","BALANCE"};
	SimpleDateFormat sdfSrc;
	Connection con = DBConnection.getDBConnection();
	public TransactionSrch(MainPage mpg) {
		this.mpg=mpg;
		 String[] toolTipStr = { "Serial Number", "Transaction Id", "Student Id", "Student Name", "Course","Year of join","Date on which transaction was performed","The amount that was paid","Remaining balance of the current year"};
		tiid=new int[1000];
		try{
			//mpg.data=new SqlDataCon();
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("select * from payments");
			rk=st.executeQuery("select * from student");
		}catch(Exception e){JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
		sdfSrc = new SimpleDateFormat("dd/MMM/yyyy");
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		abg=new ButtonGroup();


		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		
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
		
		lblSearchTransactions = new JLabel("Search Transactions");
		GridBagConstraints gbc_lblSearchTransactions = new GridBagConstraints();
		gbc_lblSearchTransactions.gridwidth = 2;
		gbc_lblSearchTransactions.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchTransactions.gridx = 0;
		gbc_lblSearchTransactions.gridy = 0;
		panel.add(lblSearchTransactions, gbc_lblSearchTransactions);
		
		lblSearchCategory = new JLabel("Search Category");
		GridBagConstraints gbc_lblSearchCategory = new GridBagConstraints();
		gbc_lblSearchCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchCategory.gridwidth = 2;
		gbc_lblSearchCategory.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchCategory.gridx = 0;
		gbc_lblSearchCategory.gridy = 1;
		panel.add(lblSearchCategory, gbc_lblSearchCategory);
		
		rdbtnTransactionId = new JRadioButton("Transaction Id");
		GridBagConstraints gbc_rdbtnTransactionId = new GridBagConstraints();
		gbc_rdbtnTransactionId.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnTransactionId.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTransactionId.gridx = 0;
		gbc_rdbtnTransactionId.gridy = 2;
		panel.add(rdbtnTransactionId, gbc_rdbtnTransactionId);
		abg.add(rdbtnTransactionId);
		rdbtnId = new JRadioButton("Student ID");
		GridBagConstraints gbc_rdbtnId = new GridBagConstraints();
		gbc_rdbtnId.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnId.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnId.gridx = 0;
		gbc_rdbtnId.gridy = 3;
		panel.add(rdbtnId, gbc_rdbtnId);
		abg.add(rdbtnId);
				
				lblEnterTransactionId = new JLabel("");
				GridBagConstraints gbc_lblEnterTransactionId = new GridBagConstraints();
				gbc_lblEnterTransactionId.insets = new Insets(0, 0, 5, 5);
				gbc_lblEnterTransactionId.gridx = 0;
				gbc_lblEnterTransactionId.gridy = 4;
				panel.add(lblEnterTransactionId, gbc_lblEnterTransactionId);
		
				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.insets = new Insets(0, 0, 5, 0);
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 4;
				panel.add(textField, gbc_textField);
				textField.setColumns(10);
				textField.setForeground(Color.BLACK);
				
				rdbtnAll = new JRadioButton("Course");
				GridBagConstraints gbc_rdbtnAll = new GridBagConstraints();
				gbc_rdbtnAll.fill = GridBagConstraints.HORIZONTAL;
				gbc_rdbtnAll.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnAll.gridx = 0;
				gbc_rdbtnAll.gridy = 5;
				panel.add(rdbtnAll, gbc_rdbtnAll);
				abg.add(rdbtnAll);
				
				panel_2 = new JPanel();
				GridBagConstraints gbc_panel_2 = new GridBagConstraints();
				gbc_panel_2.fill = GridBagConstraints.BOTH;
				gbc_panel_2.gridwidth = 2;
				gbc_panel_2.insets = new Insets(0, 0, 5, 0);
				gbc_panel_2.gridx = 0;
				gbc_panel_2.gridy = 6;
				panel.add(panel_2, gbc_panel_2);
				GridBagLayout gbl_panel_2 = new GridBagLayout();
				gbl_panel_2.columnWidths = new int[]{0, 0, 0};
				gbl_panel_2.rowHeights = new int[]{0, 0, 0};
				gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				panel_2.setLayout(gbl_panel_2);
				
				lblCourse = new JLabel(" Course");
				GridBagConstraints gbc_lblCourse = new GridBagConstraints();
				gbc_lblCourse.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblCourse.insets = new Insets(0, 0, 5, 5);
				gbc_lblCourse.gridx = 0;
				gbc_lblCourse.gridy = 0;
				panel_2.add(lblCourse, gbc_lblCourse);
				
				choice = new JComboBox();
				GridBagConstraints gbc_choice = new GridBagConstraints();
				gbc_choice.fill = GridBagConstraints.HORIZONTAL;
				gbc_choice.insets = new Insets(0, 0, 5, 0);
				gbc_choice.gridx = 1;
				gbc_choice.gridy = 0;
				panel_2.add(choice, gbc_choice);
				choice.setForeground(Color.BLACK);
				
				lblYear = new JLabel(" Year Of Join");
				GridBagConstraints gbc_lblYear = new GridBagConstraints();
				gbc_lblYear.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblYear.insets = new Insets(0, 0, 0, 5);
				gbc_lblYear.gridx = 0;
				gbc_lblYear.gridy = 1;
				panel_2.add(lblYear, gbc_lblYear);
				
				choice_1 = new JComboBox();
				GridBagConstraints gbc_choice_1 = new GridBagConstraints();
				gbc_choice_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_choice_1.gridx = 1;
				gbc_choice_1.gridy = 1;
				panel_2.add(choice_1, gbc_choice_1);
				choice_1.setForeground(Color.BLACK);
				choice_1.addItem("----Select----");
				choice_1.addFocusListener(this);
				choice.addItem("----Select----");
				choice.addFocusListener(this);
				
				rdbtnDate = new JRadioButton("Transaction Date");
				GridBagConstraints gbc_rdbtnDate = new GridBagConstraints();
				gbc_rdbtnDate.fill = GridBagConstraints.HORIZONTAL;
				gbc_rdbtnDate.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnDate.gridx = 0;
				gbc_rdbtnDate.gridy = 7;
				panel.add(rdbtnDate, gbc_rdbtnDate);
				abg.add(rdbtnDate);
				
				panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridwidth = 2;
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 8;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				
				tglbtnSingleDate = new JToggleButton(" Single ");
				GridBagConstraints gbc_tglbtnSingleDate = new GridBagConstraints();
				gbc_tglbtnSingleDate.fill = GridBagConstraints.HORIZONTAL;
				gbc_tglbtnSingleDate.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnSingleDate.gridx = 0;
				gbc_tglbtnSingleDate.gridy = 0;
				panel_1.add(tglbtnSingleDate, gbc_tglbtnSingleDate);
				
				tglbtnMultiple = new JToggleButton("Multiple");
				GridBagConstraints gbc_tglbtnMultiple = new GridBagConstraints();
				gbc_tglbtnMultiple.fill = GridBagConstraints.HORIZONTAL;
				gbc_tglbtnMultiple.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnMultiple.gridx = 1;
				gbc_tglbtnMultiple.gridy = 0;
				panel_1.add(tglbtnMultiple, gbc_tglbtnMultiple);
				
				lblFrom = new JLabel();
				lblFrom.setText(" ");
				GridBagConstraints gbc_lblFrom = new GridBagConstraints();
				gbc_lblFrom.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
				gbc_lblFrom.gridx = 0;
				gbc_lblFrom.gridy = 1;
				panel_1.add(lblFrom, gbc_lblFrom);
				
				
				Calendar cal = new GregorianCalendar();
				int tyr = cal.get(Calendar.YEAR);
				int tmon = cal.get(Calendar.MONTH);
				int tday = cal.get(Calendar.DAY_OF_MONTH);
				SqlDateModel model = new SqlDateModel();
				SqlDateModel model_1 = new SqlDateModel();
				model.setDate(tyr, tmon, tday);
				model_1.setDate(tyr, tmon, tday);
				model.setSelected(true);
				model_1.setSelected(true);
				
				datePanel = new JDatePanelImpl(model);
				datePanel_1 = new JDatePanelImpl(model_1);
				
				datePicker_1 = new JDatePickerImpl(datePanel,new DateLabelFormatter());
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.insets = new Insets(0, 0, 5, 5);
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.gridx = 1;
				gbc_textField_1.gridy = 1;
				panel_1.add(datePicker_1, gbc_textField_1);
				
				
				lblTo = new JLabel("To");
				GridBagConstraints gbc_lblTo = new GridBagConstraints();
				gbc_lblTo.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblTo.insets = new Insets(0, 0, 0, 5);
				gbc_lblTo.gridx = 0;
				gbc_lblTo.gridy = 2;
				panel_1.add(lblTo, gbc_lblTo);
				
				datePicker_2 =  new JDatePickerImpl(datePanel_1,new DateLabelFormatter());
				GridBagConstraints gbc_textField_2 = new GridBagConstraints();
				gbc_textField_2.insets = new Insets(0, 0, 5, 5);
				gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_2.gridx = 1;
				gbc_textField_2.gridy = 2;
				panel_1.add(datePicker_2, gbc_textField_2);

				btnSearch = new JButton("Search");
				GridBagConstraints gbc_btnSearch = new GridBagConstraints();
				gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
				gbc_btnSearch.gridx = 0;
				gbc_btnSearch.gridy = 9;
				panel.add(btnSearch, gbc_btnSearch);
				btnBack = new JButton("Back");
				GridBagConstraints gbc_btnBack = new GridBagConstraints();
				gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBack.gridx = 1;
				gbc_btnBack.gridy = 9;
				panel.add(btnBack, gbc_btnBack);
				btnSearch.setEnabled(false);
				btnBack.setEnabled(false);
				textField.setVisible(false);
				lblEnterTransactionId.setVisible(false);

		try
		{
			//data=new SqlDataCon();  // Sibgathulla 
			rs=stmt.executeQuery("select coursename from courses");
			while(rs.next())
			{
				choice.addItem(rs.getString(1).toUpperCase());	
			}
		}catch(Exception e){JOptionPane.showMessageDialog(this, ""+e, "Error Message", JOptionPane.ERROR_MESSAGE);}
		for(int i=2010;i<2050;i++)
			choice_1.addItem(Integer.toString(i));
		tmodel = new DefaultTableModel(columnNames,0);
		tab = new JTable(tmodel)
		{
			public boolean isCellEditable(int row, int column){  
				   return false;  
				  }  
		};
	    ToolTipHeader header = new ToolTipHeader(tab.getColumnModel());
	    header.setToolTipStrings(toolTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    tab.setTableHeader(header);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tab.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		tab.getColumn("SL NO").setMaxWidth(50);
		tab.setFillsViewportHeight(true);
		
		allscroll = new JScrollPane(tab);
		add(allscroll,gbc_scrollPane);
		
		panel_2.setVisible(false);
		panel_1.setVisible(false);
		tranExcel = new JLabel("");
		tranExcel.setIcon(new ImageIcon(MainPage.class.getResource("/images/excel.png")));
		tranWord = new JLabel("");
		tranWord.setIcon(new ImageIcon(MainPage.class.getResource("/images/word.png")));
		tranExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
				String sheet = "";
				if(ch==4)
					sheet=""+choice.getSelectedItem()+" "+choice_1.getSelectedItem();
				else
				{
					SimpleDateFormat sdfSrc = new SimpleDateFormat("dd_MMM_yyyy");
					if(tglQuery==1)
					{
					sheet = sdfSrc.format(datePicker_1.getModel().getValue());
					}
					else if(tglQuery==2)
						sheet = ""+sdfSrc.format(datePicker_1.getModel().getValue())+""+sdfSrc.format(datePicker_2.getModel().getValue());	
				}
                try {
       			FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			fn=fd.getDirectory()+fd.getFile();
     			if(!fn.equals("nullnull"))
     			{
                    fillData(tab, fn,sheet);
                    JOptionPane.showMessageDialog(null, "Data saved at " +fn+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(fn));  
     			}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		tranWord.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
				content="";
				System.out.println("from transaction search lblWored");
				try {
				FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			fn=fd.getDirectory()+fd.getFile();
     			System.out.println("from mouse listener"+fn);
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
		dbsstColor();
		dbscColor();
		dbsbkColor();
		dbscFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		dbsSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		allscroll.setVisible(false);
		btnSearch.setMnemonic(KeyEvent.VK_H);
		btnBack.setMnemonic(KeyEvent.VK_B);
		tglbtnSingleDate.setMnemonic(KeyEvent.VK_G);
		tglbtnMultiple.setMnemonic(KeyEvent.VK_M);
		//rdbtnTransactionId.setMnemonic(KeyEvent.VK_I);
		//rdbtnAll.setMnemonic(KeyEvent.VK_C);
		//rdbtnId.setMnemonic(KeyEvent.VK_E);
		//rdbtnDate.setMnemonic(KeyEvent.VK_D);
		tglbtnSingleDate.addActionListener(this);
		tglbtnMultiple.addActionListener(this);
		btnSearch.addActionListener(this);
		btnBack.addActionListener(this);
		rdbtnDate.addItemListener(this);
		rdbtnAll.addItemListener(this);
		textField.addFocusListener(this);
		datePicker_1.addFocusListener(this);
		rdbtnId.addItemListener(this);
		rdbtnTransactionId.addItemListener(this);

	}
	public void setDisabled(boolean b)
	{
		rdbtnAll.setEnabled(b);
		rdbtnId.setEnabled(b);
		textField.setEditable(b);
		choice.setEnabled(b);
		choice_1.setEnabled(b);
		
	}
    void getData()
    {
  	  try{
  		  TableModel model = tab.getModel();
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
        try {
        	System.out.println("From fill data inside Transaction Search"+tmodel.getRowCount());
        	File file = new File(path);
        	if(!file.exists())
        	{
                System.out.println("From fill data inside Transaction Search dubby"+table.getRowCount()+"sib dear"+tab.getRowCount());
            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet(sheet, 0); 
            TableModel tmodel = table.getModel();
            for (int i = 0; i < tmodel.getColumnCount(); i++) {
                Label column = new Label(i, 0, tmodel.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < tmodel.getRowCount(); i++) {
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
        		 Workbook workbook = Workbook.getWorkbook(file);
        		 WritableWorkbook copy = Workbook.createWorkbook(file, workbook);
                 WritableSheet sheet1 = copy.createSheet(sheet,0); 
                 TableModel tmodel = table.getModel();
                 for (int i = 0; i < tmodel.getColumnCount(); i++) {
                     Label column = new Label(i, 0, tmodel.getColumnName(i));
                     sheet1.addCell(column);
                 }
                 int j = 0;
                 for (int i = 0; i < tmodel.getRowCount(); i++) {
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
	@Override
	public void itemStateChanged(ItemEvent arg0) {

			if(arg0.getSource()==rdbtnId)
			{
				if(rdbtnId.isSelected())
				{
				ch=3;
				btnSearch.setEnabled(true);
				btnBack.setEnabled(true);
				panel_2.setVisible(false);
				rdbtnAll.setVisible(false);
				rdbtnTransactionId.setVisible(false);
				rdbtnDate.setVisible(false);
				textField.setVisible(true);
				lblEnterTransactionId.setText("Enter Student Id");
				lblEnterTransactionId.setVisible(true);
				updateUI();
				}
			}
			else if(arg0.getSource()==rdbtnAll)
			{
				if(rdbtnAll.isSelected())
				{
					ch=4;
					s="select * from payments where course=? and year=?";
					btnSearch.setEnabled(true);
					btnBack.setEnabled(true);
					rdbtnId.setVisible(false);
					rdbtnTransactionId.setVisible(false);
					rdbtnDate.setVisible(false);
					panel_2.setVisible(true);
					textField.setVisible(false);
					updateUI();
				}
			}
			else if(arg0.getSource()==rdbtnTransactionId)
			{
				if(rdbtnTransactionId.isSelected())
				{
					ch=1;
					btnSearch.setEnabled(true);
					btnBack.setEnabled(true);
					panel_2.setVisible(false);
					rdbtnAll.setVisible(false);
					rdbtnId.setVisible(false);
					rdbtnDate.setVisible(false);
					textField.setVisible(true);
					lblEnterTransactionId.setVisible(true);
					lblEnterTransactionId.setText("Enter Transaction Id");
					updateUI();
				}
			}
			else if(arg0.getSource()==rdbtnDate)
			{
				if(rdbtnDate.isSelected())
				{
					ch=2;
					panel_1.setVisible(true);
					lblFrom.setVisible(false);
					datePicker_1.setVisible(false);
					lblTo.setVisible(false);
					datePicker_2.setVisible(false);
					rdbtnAll.setVisible(false);
					rdbtnId.setVisible(false);
					rdbtnTransactionId.setVisible(false);
					btnBack.setEnabled(true);

				}
			}

		
	
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		if(arg0.getSource()==tglbtnSingleDate)
		{
			tglQuery=1;
			s="select * from payments where pdate>?";
			btnSearch.setEnabled(true);
			tglbtnMultiple.setSelected(false);
			lblFrom.setText(" Date");
			lblFrom.setVisible(true);
			datePicker_1.setVisible(true);
			lblTo.setVisible(false);
			datePicker_2.setVisible(false);
			
		}
		else if(arg0.getSource()==tglbtnMultiple)
		{
			tglQuery=2;
			s="select * from payments where pdate>? and pdate <?";
			btnSearch.setEnabled(true);
			tglbtnSingleDate.setSelected(false);
			lblFrom.setText("From");
			lblFrom.setVisible(true);
			datePicker_1.setVisible(true);
			lblTo.setVisible(true);
			datePicker_2.setVisible(true);
		}
		if(arg0.getSource()==btnBack)
		{
			tranExcel.setVisible(false);
			tranWord.setVisible(false);
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
			//mpg.lblLbltime.setBounds(10,-2,80,20);
			btnBack.setEnabled(false);
			btnSearch.setEnabled(false);
			if(ch==1)//rdbtnTransactionID
			{		
					rdbtnAll.setVisible(true);
					rdbtnId.setVisible(true);
					rdbtnDate.setVisible(true);
					textField.setVisible(false);
					textField.setText("");
					abg.clearSelection();
					setDisabled(true);
					lblEnterTransactionId.setVisible(false);
					updateUI();
			}
			else if(ch==2)//tdbtnDate
			{	
				panel_1.setVisible(false);
				if(tglbtnMultiple.isSelected())
				tglbtnMultiple.setSelected(false);
				else if(tglbtnSingleDate.isSelected())
				tglbtnSingleDate.setSelected(false);
				else{}
				abg.clearSelection();
				rdbtnId.setVisible(true);
				rdbtnTransactionId.setVisible(true);
				rdbtnAll.setVisible(true);	
				updateUI();
			}
			else if(ch==3)//For rdbtnId 
			{
			rdbtnAll.setVisible(true);
			rdbtnTransactionId.setVisible(true);
			rdbtnDate.setVisible(true);
			textField.setVisible(false);
			textField.setText("");
			abg.clearSelection();
			setDisabled(true);
			lblEnterTransactionId.setVisible(false);
			btnSearch.requestFocus();
			updateUI();
			}
			else if(ch==4)//For course
			{
				panel_2.setVisible(false);
				rdbtnId.setVisible(true);
				rdbtnTransactionId.setVisible(true);
				rdbtnDate.setVisible(true);
				abg.clearSelection();
				setDisabled(true);
				updateUI();
			}
			else
			{
			}
			if(allscroll instanceof JScrollPane)
			allscroll.setVisible(false);
			ch=0;
		}
		if(arg0.getSource()==btnSearch)
		{
			int dlg1=0;
//			mpg.panel_1.add(tranExcel,mpg.gbc_lblNewLabel);
//			mpg.panel_1.add(tranWord,mpg.gbc_label_4);
			if(ch==1)
			{
       			int uid;
				try {
					uid=Integer.parseInt(textField.getText().trim());
					PreparedStatement pmt = con.prepareStatement("select * from payments where uid=?");
	 				pmt.setInt(1,uid);
	 				rs=pmt.executeQuery();
	 				
	    			retriveRows(dlg1);
	    			textField.requestFocus(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "Database error constult admin","Error", JOptionPane.ERROR_MESSAGE);
					textField.requestFocus(true);
				}
				catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "Enter the details correctyl","Error", JOptionPane.ERROR_MESSAGE);
					textField.requestFocus(true);
				}

			}
			else if(ch==2)
			{

	
       		  if(tglQuery==1)
       		  {
       			java.sql.Date sdate_1 = (java.sql.Date) datePicker_1.getModel().getValue();
           		PreparedStatement pmt;
				try {
					pmt = con.prepareStatement("select * from payments where pdate=?");
	   				pmt.setDate(1,sdate_1);
	   				rs=pmt.executeQuery();
	   				retriveRows(dlg1);
	   				datePicker_1.requestFocus(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					datePicker_1.requestFocus(true);
				}

       		  }
       		  else if(tglQuery==2)
       		  {
         			java.sql.Date sdate_1 = (java.sql.Date) datePicker_1.getModel().getValue();
         			java.sql.Date sdate_2 = (java.sql.Date) datePicker_2.getModel().getValue();
         			PreparedStatement pmt;
				try {
					pmt = con.prepareStatement("select * from payments where pdate>? and pdate <?");
	   				pmt.setDate(1,sdate_1);
	   				pmt.setDate(2,sdate_2);
	   				rs=pmt.executeQuery();
	   				retriveRows(dlg1);
	   				datePicker_1.requestFocus(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					datePicker_1.requestFocus(true);
				}

       		  }
			}
			else if(ch==3)
			{
      			String id="";
    

				try {
					id=textField.getText().trim();
					PreparedStatement pmt = con.prepareStatement("select * from payments where id=?");
					pmt.setString(1,id);
					rs=pmt.executeQuery();
					retriveRows(dlg1);
					textField.requestFocus(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					textField.requestFocus(true);
				}
				catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "Enter the details correctyl","Error", JOptionPane.ERROR_MESSAGE);
					textField.requestFocus(true);
				}
	
			}

			else if(ch==4)
			{
				try
				{	
					yrs=Integer.parseInt((String) choice_1.getSelectedItem());
					cors=(String)choice.getSelectedItem();
					if(cors.equals("----Select----"))
						throwError();
					
					if(kkk==1)
					{
					int numRows = tmodel.getRowCount();
					for (int i=numRows;i>0;i--) {
						tmodel.removeRow(i-1);
						tab.revalidate();
						
						}
					}
						PreparedStatement psmt=con.prepareStatement(s);
						psmt.setString(1,cors);
						psmt.setInt(2,yrs);
						rs=psmt.executeQuery();
						int l =1;
						while(rs.next())
						{
							uid=rs.getInt("uid");
							id=rs.getString("id");
							name=rs.getString("name");
							course=rs.getString("course");
							tdate=rs.getDate("pdate");
							sdate=sdfSrc.format(tdate);
							System.out.println("string date is"+sdate);
							pamt = rs.getFloat("amtpd");
							bal=rs.getFloat("balance");		
							year=rs.getInt("year");
							if(l<=9)
							{
								slno="0"+l;
								l++;
							}
							else
							{
								slno=""+l++;
							}

							tmodel.addRow(new Object[]{slno,uid,id.toUpperCase(), name.toUpperCase(), course,year,sdate,pamt,bal});
							dlg1=1;
							kkk=1;
							choice.requestFocus(true);
						}
						if(dlg1==1)
						{
							
							gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
							gridBagLayout.rowWeights = new double[]{1.0, 0.0, 10};
							allscroll.setVisible(true);
							tranExcel.setVisible(true);
							tranWord.setVisible(true);
						}
						else
						{
						if(allscroll instanceof JScrollPane)
							allscroll.setVisible(false);
						choice.requestFocus(true);

						tranExcel.setVisible(false);
						tranWord.setVisible(false);
						JOptionPane.showMessageDialog(this, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
						}
						updateUI();
						}catch(Exception e){
							JOptionPane.showMessageDialog(this, "Select the details properly", "Error Message", JOptionPane.ERROR_MESSAGE);
							btnSearch.setEnabled(true);
						}
				
			}
			
			}
		}
	private void retriveRows(int dlg1) throws SQLException {
		int uid;
		if(kkk==1)
		{
		int numRows = tmodel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tmodel.removeRow(i-1);
			tab.revalidate();
			
			}
		}
		
		int l =1;
		while(rs.next())
		{
			uid=rs.getInt("uid");
			id=rs.getString("id");
			name=rs.getString("name");
			course=rs.getString("course");
			tdate=rs.getDate("pdate");
			sdate=sdfSrc.format(tdate);
			System.out.println("string date is"+sdate);
			pamt = rs.getFloat("amtpd");
			bal=rs.getFloat("balance");		
			year=rs.getInt("year");
			if(l<=9)
			{
				slno="0"+l;
				l++;
			}
			else
			{
				slno=""+l++;
			}

			tmodel.addRow(new Object[]{slno,uid,id.toUpperCase(), name.toUpperCase(), course,year,sdate,pamt,bal});
			dlg1=1;
			kkk=1;
		}
		if(dlg1==1)
		{
			
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
			gridBagLayout.rowWeights = new double[]{1.0, 0.0, 10};
			allscroll.setVisible(true);
			tranExcel.setVisible(true);
			tranWord.setVisible(true);
		}
		else
		{
		if(allscroll instanceof JScrollPane)
			allscroll.setVisible(false);
		//btnNewButton_1.setVisible(false);
		tranExcel.setVisible(false);
		tranWord.setVisible(false);
		JOptionPane.showMessageDialog(this, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		updateUI();
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getSource()==textField)
		{
			textField.selectAll();
		}

		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
	}
   
	private static void throwError() throws InterruptedException 
	{
		    throw new InterruptedException();
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
	public void dbsstColor()
	{
		lblSearchTransactions.setForeground(new Color(SetColor.mtColor));
	}
	public void dbscColor()
	{
		rdbtnDate.setForeground(new Color(SetColor.cColor));
		rdbtnTransactionId.setForeground(new Color(SetColor.cColor));
		rdbtnId.setForeground(new Color(SetColor.cColor));
		rdbtnAll.setForeground(new Color(SetColor.cColor));
		lblCourse.setForeground(new Color(SetColor.cColor));
		lblYear.setForeground(new Color(SetColor.cColor));
		lblEnterTransactionId.setForeground(new Color(SetColor.cColor));
		lblSearchCategory.setForeground(new Color(SetColor.cColor));
		lblFrom.setForeground(new Color(SetColor.cColor));
		lblTo.setForeground(new Color(SetColor.cColor));
		tab.setForeground(new Color(SetColor.cColor));
		
	}
	public void dbsbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
		panel_1.setBackground(new Color(SetColor.bkColor));
		panel_2.setBackground(new Color(SetColor.bkColor));
		rdbtnDate.setBackground(new Color(SetColor.bkColor));
		rdbtnTransactionId.setBackground(new Color(SetColor.bkColor));
		rdbtnId.setBackground(new Color(SetColor.bkColor));
		rdbtnAll.setBackground(new Color(SetColor.bkColor));
		tab.setBackground(new Color(SetColor.bkColor));
		datePicker_1.setBackground(new Color(SetColor.bkColor));
		datePicker_2.setBackground(new Color(SetColor.bkColor));
		
	}
	public void dbscFont(String ctFType,int ctfProp,int ctSize)
	{
		rdbtnDate.setFont(new Font(ctFType,ctfProp,ctSize));
		rdbtnTransactionId.setFont(new Font(ctFType,ctfProp,ctSize));
		rdbtnId.setFont(new Font(ctFType,ctfProp,ctSize));
		rdbtnAll.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCourse.setFont(new Font(ctFType,ctfProp,ctSize));
		lblYear.setFont(new Font(ctFType,ctfProp,ctSize));
		lblEnterTransactionId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSearchCategory.setFont(new Font(ctFType,ctfProp,ctSize));
		lblFrom.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTo.setFont(new Font(ctFType,ctfProp,ctSize));
		tab.setFont(new Font(ctFType,ctfProp,ctSize));

		
	}
	public void dbsSTFont(String stFType,int stfProp,int stSize)
	{
		lblSearchTransactions.setFont(new Font(stFType,stfProp,stSize));
	}
	
}



	




package com.hotelmanagement;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.hms.util.DBConnection;

public class TransactionDatabase extends JPanel implements ItemListener,ActionListener,FocusListener,MouseListener,TextListener{
	private TextField textField;
	ButtonGroup bg,abg,bgr;
	GridBagConstraints gbc_scrollPane;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnAll;

	private JScrollPane scrollPane,idscroll,allscroll;
	private JLabel lblCourse;
	private JComboBox <String>choice,choice_1;
	private JLabel lblYear;
   	 private boolean DEBUG = false,b;
	ResultSet rs,rk;
	Statement st,stmt;
	String id="",name="",course="",cors="",img="";
	java.sql.Date date;
	int uid,rows=0,ch=0,yrs,rdb=1,year;
	float pamt,bal,fee;
	static Object[][] dat;
	Object[][] lat;
	JTable table,tab;
	Object[] values,values1;
	int tiid[];
	List<String> list;
	 ArrayList<Integer> ilist = new ArrayList<Integer>(100);
	 ArrayList <String> aiid=new ArrayList<String>(100);
	int kl=5,dl=2,pl=0,dlgcntrl=0;
	public Checkbox lblSelectAll;//you cannot take JCheckBox due to the problme in itemstatechanged 
	Row rol;
	 MyTableModel m,mytb;
	private JButton btnSearch;
	TableCellRenderer buttonRenderer; 
	TableCellRenderer buttonRend;
	private JButton btnDelete;
	/**
	 * Create the panel.
	 */
	MainPage mpg;
	private JButton btnBack;
	private JLabel lblTransactionDatabase;
	private JLabel lblSearchCategory;
	Connection con = DBConnection.getDBConnection();
	public TransactionDatabase(MainPage mpg) {
		this.mpg=mpg;
		tiid=new int[1000];
		buttonRenderer = new JTableButtonRenderer();
		try{
			//mpg.data=new SqlDataCon();
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("select * from payments");
			rk=st.executeQuery("select * from student");
		}catch(Exception e){JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		bg=new ButtonGroup();
		abg=new ButtonGroup();
		bgr=new ButtonGroup();
		
		lblTransactionDatabase = new JLabel("Transaction Database");
		GridBagConstraints gbc_lblTransactionDatabase = new GridBagConstraints();
		gbc_lblTransactionDatabase.gridwidth = 2;
		gbc_lblTransactionDatabase.insets = new Insets(0, 0, 5, 5);
		gbc_lblTransactionDatabase.gridx = 0;
		gbc_lblTransactionDatabase.gridy = 0;
		add(lblTransactionDatabase, gbc_lblTransactionDatabase);
		
		lblSelectAll = new Checkbox("Select All");
		GridBagConstraints gbc_lblSelectAll = new GridBagConstraints();
		gbc_lblSelectAll.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSelectAll.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectAll.gridx = 2;
		gbc_lblSelectAll.gridy = 0;
		add(lblSelectAll, gbc_lblSelectAll);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 3;
		gbc_btnBack.gridy = 0;
		add(btnDelete, gbc_btnBack);
		 m = new MyTableModel();
		table = new JTable(m);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(false);
		table.getColumn("DELETE").setCellRenderer(buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.getColumn(" ").setMaxWidth(20);
		table.getColumn("DELETE").setMaxWidth(80);
		table.getColumn("SL NO").setMaxWidth(50);
		table.getTableHeader().setReorderingAllowed(false);
		
		lblSearchCategory = new JLabel("Search Category");
		GridBagConstraints gbc_lblSearchCategory = new GridBagConstraints();
		gbc_lblSearchCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearchCategory.gridwidth = 2;
		gbc_lblSearchCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearchCategory.gridx = 0;
		gbc_lblSearchCategory.gridy = 1;
		add(lblSearchCategory, gbc_lblSearchCategory);
		scrollPane = new JScrollPane(table);
		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		if(m.getRowCount()==0)
		{
			lblSelectAll.setEnabled(false);
			Thread t=new Thread(){
				public void run()
				{
			JOptionPane.showMessageDialog(null, "Transaction database is empty","Message",JOptionPane.INFORMATION_MESSAGE);
				}
			};
			t.start();
		}
		
		rdbtnId = new JRadioButton("Student ID");
		GridBagConstraints gbc_rdbtnId = new GridBagConstraints();
		gbc_rdbtnId.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnId.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnId.gridx = 0;
		gbc_rdbtnId.gridy = 2;
		add(rdbtnId, gbc_rdbtnId);

		textField = new TextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		rdbtnAll = new JRadioButton("Course");
		GridBagConstraints gbc_rdbtnAll = new GridBagConstraints();
		gbc_rdbtnAll.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnAll.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAll.gridx = 0;
		gbc_rdbtnAll.gridy = 3;
		add(rdbtnAll, gbc_rdbtnAll);
		
		lblCourse = new JLabel(" Course");
		GridBagConstraints gbc_lblCourse = new GridBagConstraints();
		gbc_lblCourse.anchor = GridBagConstraints.WEST;
		gbc_lblCourse.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourse.gridx = 0;
		gbc_lblCourse.gridy = 4;
		add(lblCourse, gbc_lblCourse);
		
		choice = new JComboBox();
		choice.setForeground(Color.BLACK);
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 5);
		gbc_choice.gridx = 1;
		gbc_choice.gridy = 4;
		add(choice, gbc_choice);
		choice.addItem("----Select----");
		
		lblYear = new JLabel(" Year Of Join");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.WEST;
		gbc_lblYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 5;
		add(lblYear, gbc_lblYear);
		
		choice_1 = new JComboBox();
		choice_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_choice_1 = new GridBagConstraints();
		gbc_choice_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_1.insets = new Insets(0, 0, 5, 5);
		gbc_choice_1.gridx = 1;
		gbc_choice_1.gridy = 5;
		add(choice_1, gbc_choice_1);
		choice_1.addItem("----Select----");
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
		
		btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 6;
		add(btnSearch, gbc_btnSearch);
		
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack1 = new GridBagConstraints();
		gbc_btnBack1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack1.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack1.gridx = 1;
		gbc_btnBack1.gridy = 6;
		add(btnBack, gbc_btnBack1);
		abg.add(rdbtnId);
		abg.add(rdbtnAll);
		btnSearch.setEnabled(false);
		textField.setForeground(Color.BLACK);
		mytb=new MyTableModel();
		tab=new JTable(mytb);
		tab.setVisible(false);
		textField.setVisible(false);
		btnBack.setEnabled(false);
		dbsstColor();
		dbscColor();
		dbsbkColor();
		dbscFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		dbsSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		
		rdbtnId.setMnemonic(KeyEvent.VK_E);
		rdbtnAll.setMnemonic(KeyEvent.VK_C);
		btnSearch.setMnemonic(KeyEvent.VK_H);
		btnBack.setMnemonic(KeyEvent.VK_B);
		btnDelete.setMnemonic(KeyEvent.VK_L);
		
		choice.addFocusListener(this);
		choice_1.addFocusListener(this);
		btnSearch.addActionListener(this);
		rdbtnId.addItemListener(this);
		rdbtnAll.addItemListener(this);
		lblSelectAll.addItemListener(this);
		btnDelete.addActionListener(this);
		textField.addTextListener(this);
		textField.addFocusListener(this);
		btnBack.addActionListener(this);
		setVisibility(false);

	}
	public void setDisabled(boolean b)
	{
		rdbtnAll.setEnabled(b);
		rdbtnId.setEnabled(b);
		textField.setEditable(b);
		choice.setEnabled(b);
		choice_1.setEnabled(b);
		btnSearch.setEnabled(b);
		
	}
	private void setVisibility(boolean b)
	{
		lblCourse.setVisible(b);
		lblYear.setVisible(b);
		choice.setVisible(b);
		choice_1.setVisible(b);
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
	if(arg0.getSource()==lblSelectAll)
	{

		int row=0,col=0;
		  Checkbox cbox = (Checkbox) arg0.getItemSelectable();
		    if (cbox.getState()) 
		    {
		   
		    	if(ch==3||ch==4)//For making checkbox to get selected
		    	{
			    	btnDelete.setEnabled(true);
			    	kl=1;
			    	for(int i=0;i<mytb.getRowCount();i++)
					{
						boolean hkb=(boolean) mytb.getValueAt(i, 0);
						if(!hkb)
							mytb.setValueAt(values, i, 0);
					}
			    	kl=5;
		    	}
		    	else
		    	{
			    	btnDelete.setEnabled(true);
			    	kl=1;
			    	for(int i=0;i<m.getRowCount();i++)
					{
						boolean hkb=(boolean) m.getValueAt(i, 0);
			
						if(!hkb)
							m.setValueAt(values, i, 0);
					}
			    	kl=5;
		    	}

		    } 
		    else // For making check box to get unselected
		    {

		    	if(ch==3||ch==4)
		    	{

			    	kl=0;
			    	btnDelete.setEnabled(false);
			    	for(int i=0;i<mytb.getRowCount();i++)
					{
							mytb.setValueAt(values, i, 0);
					}
			    	kl=5;
		    	}
		    	else
		    	{
			    	kl=0;
			    	btnDelete.setEnabled(false);
			    	for(int i=0;i<m.getRowCount();i++)
					{
							m.setValueAt(values, i, 0);
					}
			    	kl=5;
		    	}
		    	
		    }
	

	}

			if(arg0.getSource()==rdbtnId)
			{
				if(rdbtnId.isSelected())
				{
				if(scrollPane instanceof JScrollPane)
					scrollPane.setVisible(false);
				
				if(allscroll instanceof JScrollPane)
					allscroll.setVisible(false);
				ch=3;
				btnSearch.setEnabled(true);
				btnBack.setEnabled(true);
				setVisibility(false);
				rdbtnAll.setVisible(false);
				textField.setVisible(true);
				lblSelectAll.setVisible(false);
				btnDelete.setVisible(false);
				if(lblSelectAll.getState())
				{
		    	kl=0;
		    	btnDelete.setEnabled(false);
		    	for(int i=0;i<m.getRowCount();i++)
				{
						m.setValueAt(values, i, 0);
				}
		    	kl=5;
		    	lblSelectAll.setState(false);
				}
				updateUI();
				}
			}
			else if(arg0.getSource()==rdbtnAll)
			{
				if(rdbtnAll.isSelected())
				{
					if(scrollPane instanceof JScrollPane)
						scrollPane.setVisible(false);
					
					if(idscroll instanceof JScrollPane)
						idscroll.setVisible(false);
					ch=4;
					btnSearch.setEnabled(true);
					btnBack.setEnabled(true);
					rdbtnId.setVisible(false);
					setVisibility(true);
					textField.setVisible(false);
					lblSelectAll.setVisible(false);
					btnDelete.setVisible(false);
					if(lblSelectAll.getState())
					{
			    	kl=0;
			    	btnDelete.setEnabled(false);
			    	for(int i=0;i<m.getRowCount();i++)
					{
							m.setValueAt(values, i, 0);
					}
			    	kl=5;
			    	lblSelectAll.setState(false);
					}
					updateUI();
				}
			}

		
	
	}
	public void delAllTransactions()
	{
		int ciid[],count=0;
		try{
			PreparedStatement psmt=con.prepareStatement("select * from payments where uid=?");
			PreparedStatement ps=con.prepareStatement("insert into trash (uid,id,name,course,pdate,amtpd,balance,year) "+ "values(?,?,?,?,?,?,?,?)");
			PreparedStatement pst=con.prepareStatement("delete from payments where uid=?");
			   for (String number : aiid) {
				psmt.setInt(1,Integer.parseInt(number));
				rs=psmt.executeQuery();
				while(rs.next())
				{
					uid=rs.getInt("uid");
					id = rs.getString("id");
					name = rs.getString("name");
					course = rs.getString("course");
					date=rs.getDate("pdate");
					pamt = rs.getFloat("amtpd");
					bal=rs.getFloat("balance");
					year=rs.getInt("year");
					rows++;
				}
				ps.setInt(1,uid);
				ps.setString(2,id);
				ps.setString(3,name);
				ps.setString(4,course);
				ps.setDate(5,date);
				ps.setFloat(6,pamt);
				ps.setFloat(7,bal);
				ps.setInt(8, year);
				int s=ps.executeUpdate();

			
			pst.setInt(1,Integer.parseInt(number));
			pst.execute();
			count++;	
		}	
			}catch(Exception ee){JOptionPane.showMessageDialog(this,""+ee,"Error!!",JOptionPane.ERROR_MESSAGE);}
	
		JOptionPane.showMessageDialog(this,"Records Deleted Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
		   for (Integer number : ilist) {
			     tiid[ilist.indexOf(number)]=number;//row numbers of the mytb or m
			    } 
		   ilist.clear();
		ciid=new int[count];
		for(int i=0;i<m.getRowCount();i++)
		{
			   for (String number : aiid) {
				   if(m.getValueAt(i, 3).equals(number))
				   {
					   ilist.add(i);
				   }
				  
			   }
		}
		 for (Integer number : ilist) {
		     ciid[ilist.indexOf(number)]=number;//row numbers of the m 
		    } 
		 ilist.clear();
	
		if(ch==3||ch==4)
		{
			m.removeRows(ciid);//we have to delete rows in both the panes so we used this.
			mytb.removeRows(tiid);
		}
		else
		{
			m.removeRows(ciid);
		}

	

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnBack)
		{
			btnSearch.requestFocus(true);
			btnBack.setEnabled(false);
			if(lblSelectAll.getState())
				lblSelectAll.setState(false);
			lblSelectAll.setEnabled(true);
			if(ch==3)//For rdbtnId for tran
			{
			if(idscroll instanceof JScrollPane)
			idscroll.setVisible(false);
			rdbtnAll.setVisible(true);
			textField.setVisible(false);
			textField.setText("");
			abg.clearSelection();
			scrollPane.setVisible(true);
			setDisabled(true);
			lblSelectAll.setVisible(true);
			btnDelete.setVisible(true);
			btnSearch.requestFocus();
			lblCourse.setVisible(false);
			choice_1.setVisible(false);
			}
			else if(ch==4)//For rdbtnAll for tran
			{
				if(allscroll instanceof JScrollPane)
				allscroll.setVisible(false);
				lblYear.setVisible(false);
				choice_1.setVisible(false);
				rdbtnId.setVisible(true);
				abg.clearSelection();
				choice.setVisible(false);
				lblCourse.setVisible(false);
				scrollPane.setVisible(true);
				setDisabled(true);
				lblSelectAll.setVisible(true);
				btnDelete.setVisible(true);
				btnSearch.requestFocus();
			}
			else
			{
			}
			ch=0;
			btnSearch.setEnabled(false);
			updateUI();
		}
		else if(arg0.getSource()==btnDelete)
		{
			
			if(MainPage.labelUser.getText().trim().equalsIgnoreCase("ADMIN"))
			{
		  	int i=0,j=0,tid=0;
			if(dl==0&&rdb==1)
			{
				if(JOptionPane.showConfirmDialog(null,"Do you wish to delete :\" All  records from transactions", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					delAllTransactions();
				aiid.clear();
				lblSelectAll.setState(false);
				lblSelectAll.setEnabled(false);
				btnDelete.setEnabled(false);
				}
		
			}
			else if(dl==1&&rdb==1)
			{
				if(JOptionPane.showConfirmDialog(null,"Do you wish to delete the selected records from transactions", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					delAllTransactions();
					aiid.clear();
					btnDelete.setEnabled(false);
					pl=0;
					if(lblSelectAll.getState())
					{
						lblSelectAll.setState(false);
						lblSelectAll.setEnabled(false);
					}
				}
			}
	
		}
		else
		{
				JOptionPane.showMessageDialog(this, "No privileges, login as admin","Message",JOptionPane.INFORMATION_MESSAGE);
		}
		}
		else if(arg0.getSource()==btnSearch)
		{
			btnSearch.setEnabled(false);
		if(ch==3)
		{
				mytb=new MyTableModel();
				tab=new JTable(mytb);
				tab.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
				tab.setFont(new Font("Tahoma",Font.PLAIN,14));
				tab.setBackground(new Color(SetColor.bkColor));
				tab.setForeground(new Color(SetColor.cColor));
					tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tab.setFillsViewportHeight(true);
					tab.setAutoCreateRowSorter(false);
					tab.getColumn("DELETE").setCellRenderer(buttonRenderer);
					tab.addMouseListener(new JTableButtonMouseListener(tab));
					tab.getColumn(" ").setMaxWidth(20);
					tab.getColumn("DELETE").setMaxWidth(80);
					tab.getColumn("SL NO").setMaxWidth(50);
					tab.getTableHeader().setReorderingAllowed(false);
					idscroll=new JScrollPane(tab);
					idscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					idscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					add(idscroll,gbc_scrollPane);
					if(tab.getRowCount()==0)
					{
					textField.setEditable(true);
					idscroll.setVisible(false);
					lblSelectAll.setVisible(false);
					btnDelete.setVisible(false);
					btnSearch.setEnabled(true);
					JOptionPane.showMessageDialog(this,"No Data Found", "Error!!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					textField.setEditable(true);
					idscroll.setVisible(true);
					lblSelectAll.setVisible(true);
					btnDelete.setVisible(true);
					}
					updateUI();
		}

		else if(ch==4)
		{

					mytb=new MyTableModel();
					tab=new JTable(mytb);
					if(dlgcntrl==0)
					{
					tab.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
					tab.setFont(new Font(SFont.ctFType,SFont.ctfProp,SFont.ctSize));
					tab.setBackground(new Color(SetColor.bkColor));
					tab.setForeground(new Color(SetColor.cColor));
					tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tab.setFillsViewportHeight(true);
					tab.setAutoCreateRowSorter(false);
					tab.getColumn("DELETE").setCellRenderer(buttonRenderer);
					tab.addMouseListener(new JTableButtonMouseListener(tab));
					tab.getColumn(" ").setMaxWidth(20);
					tab.getColumn("DELETE").setMaxWidth(80);
					tab.getColumn("SL NO").setMaxWidth(50);
					tab.getTableHeader().setReorderingAllowed(false);
					allscroll=new JScrollPane(tab);
					allscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					allscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					add(allscroll,gbc_scrollPane);
					if(tab.getRowCount()==0)
					{
					choice.setEnabled(true);
					choice_1.setEnabled(true);
					allscroll.setVisible(false);
					lblSelectAll.setVisible(false);
					btnDelete.setVisible(false);
					btnSearch.setEnabled(true);
					JOptionPane.showMessageDialog(this,"No Data Found", "Error!!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
					choice.setEnabled(true);
					choice_1.setEnabled(true);
					allscroll.setVisible(true);
					lblSelectAll.setVisible(true);
					btnDelete.setVisible(true);
					}
					updateUI();
					}
				
				}

			
			}
		}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getSource()==choice)
		{	
		btnSearch.setEnabled(true);
		if(allscroll instanceof JScrollPane)
			allscroll.setVisible(false);
		else if(idscroll instanceof JScrollPane)
			idscroll.setVisible(false);
		lblSelectAll.setVisible(false);
		btnDelete.setVisible(false);
		
		}
		else if(arg0.getSource()==choice_1)
		{
		btnSearch.setEnabled(true);
		if(allscroll instanceof JScrollPane)
			allscroll.setVisible(false);
		else if(idscroll instanceof JScrollPane)
			idscroll.setVisible(false);
		lblSelectAll.setVisible(false);
		btnDelete.setVisible(false);
		}
		if(arg0.getSource()==textField)
		{
			textField.selectAll();
		}
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

		if(arg0.getSource()==choice)
		{
		btnSearch.setEnabled(true);
		}


	}
	
    class MyTableModel extends AbstractTableModel {
   	 List<Row> rows = new ArrayList<Row>();
		private  final long serialVersionUID = 1L;
   	String columnNames[]={" ","DELETE","SL NO","TRANSACTION ID","STUDENT ID","NAME","COURSE","YEAR OF JOIN","DATE","AMOUNT PAID","BALANCE"};
   	String sdate="";
   	SimpleDateFormat sdfSrc = new SimpleDateFormat("dd/MMM/yyyy");
       Row ro;
       public int k=0,lk=0,rl=0;
       public MyTableModel() {
           int l=1;
           try{
        	  if(ch==3)
        	  {
      			String id="";
    			id=textField.getText().trim();
        		PreparedStatement pmt=con.prepareStatement("select * from payments where id=?");
				pmt.setString(1,id);
				rs=pmt.executeQuery();
        	  }
        	  if(ch==4)
        	  {
        		 
        			String course;
        			int year=0;
        			
          		  if(choice.getSelectedItem().equals("----Select----"))
        			  throwError();
        			yrs=Integer.parseInt((String) choice_1.getSelectedItem());
					PreparedStatement pmt=con.prepareStatement("select * from payments where course=? and year=?");
					pmt.setString(1,(String) choice.getSelectedItem());
					pmt.setInt(2, yrs);
					rs=pmt.executeQuery();
        	  }
        	  dlgcntrl=0;
           while (rs.next()) {
               values = new Object[11];
               values[0] = Boolean.FALSE;
               values[1] = new JButton("Delete");
               values[2] = ""+l;
               values[3] = rs.getString("uid");
               values[4] = rs.getString("id");
               values[5] = rs.getString("name");
               values[6] = rs.getString("course");
               values[7] = rs.getString("year");
               sdate = sdfSrc.format(rs.getDate("pdate"));
               values[8] = sdate;
               values[9] = rs.getString("amtpd");
               values[10] = rs.getString("balance");
               Row row = new Row(values);
               rows.add(row);
               rl++;
               l++;
           		}
           
           	}catch(Exception e){
        	   dlgcntrl=1;
        	   JOptionPane.showMessageDialog(null, "Select the details properly","Errorr!!",JOptionPane.ERROR_MESSAGE);}
       }

       public int getColumnCount() {
           return columnNames.length;
       }


       public int getRowCount() {
           return rows.size();
       }

       public String getColumnName(int col) {
           return columnNames[col];
       }

       @Override
       public Object getValueAt(int rowIndex, int columnIndex) {
           if(rowIndex > rows.size()){
               return null;
           }
           return rows.get(rowIndex).getValue(columnIndex);
           
       }
     
		/*
		* JTable uses this method to determine the default renderer/
		* editor for each cell.  If we didn't implement this method,
		* then the last column would contain text ("true"/"false"),
		* rather than a check box.
		*/
		@Override public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
		}

       /*
        * Don't need to implement this method unless your table's
        * editable.
        */
       public boolean isCellEditable(int row, int col) {
           //Note that the data/cell address is constant,
           //no matter where the cell appears onscreen.
           if (col > 0) {
              return false;
           } else {
               return true;
           }
       }
       //to remove rows
   	public void removeRows(int[] ilist)
   	{
   	    quickSort(ilist, 0, pl-1);
   		for (int i = pl - 1; i >= 0; i--)
   		{
   			int row = ilist[i];
   			rows.remove(row);
   			fireTableRowsDeleted(row, row);
   			
   			
   		}
   	}
   	public void removeRow(int n)
   	{
   		rows.remove(n);
   		fireTableRowsDeleted(n,n);
   	}
    public void removeDuplicates(ArrayList list) {
        HashSet set = new HashSet(list);
        list.clear();
        list.addAll(set);
       
}
   
       /*
        * Don't need to implement this method unless your table's
        * data can change.
        */
       public void setValueAt(Object value, int row, int col) {
       	Row ro=rows.get(row);
       	
      
           if (DEBUG) {
               System.out.println("Setting value at " + row + "," + col
                                  + " to " + value
                                  + " (an instance of "
                                  + value.getClass() + ")");
           }
           switch (col) {
           case 0:
        	   if(kl==5)
        	   {
        		   b=false;
            	  if(ro.values[0]==Boolean.FALSE)
            	  {
            	  ro.values[0]=true;
            	  }
            	  else
            	   ro.values[0]=false;//used to select the checkbox
            	  if((Boolean)ro.values[0])
            	  {
            		  ilist.add(row);
            		  aiid.add((String) ro.values[3]);
            		  pl++;
            		  dl=1;
            		  if(pl>1)
            		  btnDelete.setEnabled(true);
            	  }
            	  else
            	  {
             	  pl--;
             	  ilist.remove(ilist.indexOf(row));
             	  aiid.remove(aiid.indexOf(ro.values[3]));
             	  if(pl<=1)
             	 btnDelete.setEnabled(false);
             	  lk=0;
            	  }
        	   }
       else if(kl==1)
       {
       	ro.values[0]=Boolean.TRUE;
       	aiid.add((String) ro.values[3]);
       	
       	ilist.add(row);
       	dl=0;//if we go for select all
       	pl++;
       }
       else if(kl==0)
       {
    	   pl=0;                  
    	   ro.values[0]=Boolean.FALSE;
    	   ilist.clear();
    	   aiid.clear();
    	   lk=0;
       }else{}
           	   if(rows.size()==pl)
           	   {
           		   lblSelectAll.setState(true);
           	   }
           	   else
           	   {
           		   lblSelectAll.setState(false);
           	   }
           }
           fireTableCellUpdated(row, col);
          

           if (DEBUG) {
               printDebugData();
           }
           removeDuplicates(aiid);
	       removeDuplicates(ilist);
	

       }
  
       private void printDebugData() {
           int numRows = getRowCount();
           int numCols = getColumnCount();

           for (int i=0; i < numRows; i++) {
               for (int j=0; j < numCols; j++) {
               }
            
           }
          // System.out.println("--------------------------");
       }
       public void quickSort(int[] a, int p, int r)
       {
           if(p<r)
           {
               int q=partition(a,p,r);
               quickSort(a,p,q);
               quickSort(a,q+1,r);
           }
       }

       private  int partition(int[] a, int p, int r) {

           int x = a[p];
           int i = p-1 ;
           int j = r+1 ;

           while (true) {
               i++;
               while ( i< r && a[i] < x)
                   i++;
               j--;
               while (j>p && a[j] > x)
                   j--;

               if (i < j)
                   swap(a, i, j);
               else
                   return j;
           }
       }

       private void swap(int[] a, int i, int j) {
           // TODO Auto-generated method stub
           int temp = a[i];
           a[i] = a[j];
           a[j] = temp;
       }
   }
  
   class Row {
       private final Object[]  values;

       public Row(Object[] values) {
           this.values = values;
       }

       public int getSize() {
           return values.length;
       }

       public Object getValue(int i) {
       
           return values[i];
          
       }
 

   }
   	private class JTableButtonRenderer implements TableCellRenderer {		
		@Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JButton button = (JButton)value;
			if (isSelected) {
		    	
				button.setForeground(Color.black);
		    	button.setBackground(UIManager.getColor("Button.background"));
				
		    } else {
		    	button.setForeground(Color.black);
		    	button.setBackground(UIManager.getColor("Button.background"));
				
		    }
			return button;	
		
			}
		}
   	class JTableButtonRend implements TableCellRenderer {		
   		@Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
   			JCheckBox butt = (JCheckBox)value;
   			if (isSelected) {
   				butt.setForeground(table.getSelectionForeground());
   				butt.setBackground(table.getSelectionBackground());
   		    } else {
   		    	butt.setForeground(table.getForeground());
   		    	butt.setBackground(UIManager.getColor("Button.background"));
   		    }
   			return butt;	
   		}
   	}
   	class JTableButtonMouseListener extends MouseAdapter {
   		private final JTable table;
   		List<Row> rows;
   		String lid,lname,lcourse,ldate;
		java.sql.Date sdate;
   		int luid,lyear;
   		float lbal,lamt,lfee;
   		public JTableButtonMouseListener(JTable table) {
   			this.table = table;
   			this.rows=rows;
   		}

   		public void mouseClicked(MouseEvent e) {
   			if(MainPage.labelUser.getText().trim().equalsIgnoreCase("ADMIN"))
   			{
   			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
   			int row    = e.getY()/table.getRowHeight(); 
   	
   			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
   			    Object value = table.getValueAt(row, column);
   			    if (value instanceof JButton) {
   			    	((JButton)value).doClick();
						int rw=table.getSelectedRow();
						if(rdb==1)
						{
								luid=Integer.parseInt((String) table.getValueAt(rw, 3));
								lid=(String) table.getValueAt(rw, 4);
								lname=(String) table.getValueAt(rw, 5);
								lcourse=(String) table.getValueAt(rw, 6);
								lyear=Integer.parseInt((String) table.getValueAt(rw, 7));
								ldate=(String) table.getValueAt(rw, 8);
								SimpleDateFormat sdf= new SimpleDateFormat("dd/MMM/yyyy");
								try {
								Date udate = sdf.parse(ldate);
								sdate = new java.sql.Date(udate.getTime());
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								lamt=Float.parseFloat((String) table.getValueAt(rw, 9));
								lbal=Float.parseFloat((String) table.getValueAt(rw, 10));

								if(ch==3||ch==4)
								{
								for(int i=0;i<table.getRowCount();i++)
								{
									if(i!=row)
									{
									boolean hkb=(boolean) table.getValueAt(i, 0);
									if(hkb)
										mytb.setValueAt(values, i, 0);
									}
								}
								if(!(boolean) table.getValueAt(row, 0))
									mytb.setValueAt(values, row, 0);
								}
								else
								{
									for(int i=0;i<table.getRowCount();i++)
									{
										if(i!=row)
										{
										boolean hkb=(boolean) table.getValueAt(i, 0);
										if(hkb)
											m.setValueAt(values, i, 0);
										}
									}
									if(!(boolean) table.getValueAt(row, 0))
										m.setValueAt(values, row, 0);	
								}
		   			    		if(JOptionPane.showConfirmDialog(null,"Do you wish to delete transaction ' "+luid+" '", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		   			    		{
		   			    			try{
		   			 		
		   			    				PreparedStatement ps=con.prepareStatement("insert into trash (uid,id,name,course,pdate,amtpd,balance,year) "+ "values(?,?,?,?,?,?,?,?)");
		   			    				ps.setInt(1,luid);
		   			    				ps.setString(2,lid);
		   			    				ps.setString(3,lname);
		   			    				ps.setString(4,lcourse);
		   			    				ps.setDate(5,sdate);
		   			    				ps.setFloat(6,lamt);
		   			    				ps.setFloat(7,lbal);
		   			    				ps.setInt(8, lyear);
		   			    				int s=ps.executeUpdate();
		   			    				PreparedStatement pst=con.prepareStatement("delete from payments where uid=?");//Sibgathulla from previous
		   			    				pst.setInt(1,luid);
		   			    				pst.execute();
		   			    				if(ch==3||ch==4)
		   			    				{
						    				for(int i=0;i<m.getRowCount();i++)
						    				{
						    					if(mytb.getValueAt(row, 3).equals(m.getValueAt(i,3)))
						    					m.removeRow(i);
						    				}
						    				mytb.removeRow(row);
		   			    				}
		   			    				else
		   			    				m.removeRow(row);	
		   			    				
		   			    				}catch(Exception ee){JOptionPane.showMessageDialog(null, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
	   			    				if(ch==3||ch==4)
	   			    				{
									if(mytb.getRowCount()==0)
										lblSelectAll.setEnabled(false);
	   			    				}
	   			    				else
	   			    				{
									if(m.getRowCount()==0)
										lblSelectAll.setEnabled(false);	   			    					
	   			    				}
									pl--;
		   			
		   			    		}
		   			    		else
		   			    		{
		   			    			if(ch==3||ch==4)
		   	   			        	mytb.setValueAt(values, row, 0);
		   			    			else
			   	   			        m.setValueAt(values, row, 0);		   			    				
		   			    		}
		   			   		
		   			    	aiid.clear();
						}
					
					
   			    }
   				
   			}
   		}
   			else
   			{
   				JOptionPane.showMessageDialog(null,"No Privileges, login as admin","Message",JOptionPane.INFORMATION_MESSAGE);
   			}
   		}
   	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	@Override
	public void textValueChanged(TextEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==textField)
		{
			if(idscroll instanceof JScrollPane)
			idscroll.setVisible(false);
			btnSearch.setEnabled(true);
		}
	}
	private static void throwError() throws InterruptedException 
	{
		    throw new InterruptedException();
	} 
	public void dbsstColor()
	{
		lblTransactionDatabase.setForeground(new Color(SetColor.mtColor));
	}
	public void dbscColor()
	{
		rdbtnId.setForeground(new Color(SetColor.cColor));
		rdbtnAll.setForeground(new Color(SetColor.cColor));
		lblCourse.setForeground(new Color(SetColor.cColor));
		lblYear.setForeground(new Color(SetColor.cColor));
		table.setForeground(new Color(SetColor.cColor));
		lblSelectAll.setForeground(new Color(SetColor.cColor));
		lblSearchCategory.setForeground(new Color(SetColor.mtColor));
	}
	public void dbsbkColor()
	{
		table.setBackground(new Color(SetColor.bkColor));
		setBackground(new Color(SetColor.bkColor));
		lblSelectAll.setBackground(new Color(SetColor.bkColor));
		rdbtnId.setBackground(new Color(SetColor.bkColor));
		rdbtnAll.setBackground(new Color(SetColor.bkColor));
	}
	public void dbscFont(String ctFType,int ctfProp,int ctSize)
	{
		rdbtnId.setFont(new Font(ctFType,ctfProp,ctSize));
		rdbtnAll.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCourse.setFont(new Font(ctFType,ctfProp,ctSize));
		lblYear.setFont(new Font(ctFType,ctfProp,ctSize));
		table.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSelectAll.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSearchCategory.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void dbsSTFont(String stFType,int stfProp,int stSize)
	{
		lblTransactionDatabase.setFont(new Font(stFType,stfProp,stSize));
	}
}



	




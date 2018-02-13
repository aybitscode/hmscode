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
import java.util.ArrayList;
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

public class StudentDatabase extends JPanel implements ItemListener,ActionListener,FocusListener,MouseListener,TextListener{
	/**
	 * 
	 */
	private TextField textField;
	ButtonGroup bg,abg,bgr;
	GridBagConstraints gbc_scrollPane;
	private JRadioButton rdId;
	private JRadioButton rdAll;
	public static Checkbox lblSelectAll;
	private JScrollPane scroll,idscroll,allscroll;
	private JLabel lblCourse;
	private JComboBox <String>choice,choice_1;
	private JLabel lblYear;
    private boolean DEBUG = false,b;
	ResultSet rs,rk;
	Statement st,stmt;
	String id,name,course,date,cors,img,fname,email,mobile;
	int uid,rows=0,ch=0,yrs,rdb=1,year,dlgcntrl=0;
	float pamt,bal,fee;
	static Object[][] dat;
	Object[][] lat;
	JTable tab;
	Object[] values,values1;
	int tiid[];
	 List<String> list;
	 ArrayList<Integer> ilist = new ArrayList<Integer>(100);
	 ArrayList <String> aiid=new ArrayList<String>(100);
	int kl=5,dl=2,pl=0;
	Row rol;
	MTableModel mb,mtb; 
	private JButton btnSearch;
	TableCellRenderer buttonRenderer; 
	TableCellRenderer buttonRend;
	private JLabel lblStudentDatabase;
	private JButton btnDelete;
	/**
	 * Create the panel.
	 */
	MainPage mpg;
	private JButton btnBack;
	private JLabel lblSearchCategory;
	Connection con = DBConnection.getDBConnection();
	public StudentDatabase(MainPage mpg) {
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
		
		lblStudentDatabase = new JLabel("Student Database");
		GridBagConstraints gbc_lblStudentDatabase = new GridBagConstraints();
		gbc_lblStudentDatabase.gridwidth = 2;
		gbc_lblStudentDatabase.anchor = GridBagConstraints.EAST;
		gbc_lblStudentDatabase.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentDatabase.gridx = 0;
		gbc_lblStudentDatabase.gridy = 0;
		add(lblStudentDatabase, gbc_lblStudentDatabase);
		
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
		
		 mb=new MTableModel();
		 	tab=new JTable(mb);
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
			
			lblSearchCategory = new JLabel("Search Category");
			GridBagConstraints gbc_lblSearchCategory = new GridBagConstraints();
			gbc_lblSearchCategory.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSearchCategory.gridwidth = 2;
			gbc_lblSearchCategory.insets = new Insets(0, 0, 5, 5);
			gbc_lblSearchCategory.gridx = 0;
			gbc_lblSearchCategory.gridy = 1;
			add(lblSearchCategory, gbc_lblSearchCategory);
			scroll=new JScrollPane(tab);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.gridheight = 7;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 2;
			gbc_scrollPane.gridy = 1;
			add(scroll,gbc_scrollPane);
			
		if(mb.getRowCount()==0)
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
		

		rdId=new JRadioButton("Student ID");
		GridBagConstraints gbc_rdbtnId = new GridBagConstraints();
		gbc_rdbtnId.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnId.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnId.gridx = 0;
		gbc_rdbtnId.gridy = 2;
		add(rdId,gbc_rdbtnId);
		textField = new TextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		rdAll=new JRadioButton("Course");
		GridBagConstraints gbc_rdbtnAll = new GridBagConstraints();
		gbc_rdbtnAll.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnAll.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAll.gridx = 0;
		gbc_rdbtnAll.gridy = 3;
		add(rdAll, gbc_rdbtnAll);
		
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

		bgr.add(rdId);
		bgr.add(rdAll);
		btnSearch.setEnabled(false);
		textField.setForeground(Color.BLACK);
		textField.setVisible(false);
		btnBack.setEnabled(false);
		dbsstColor();
		dbscColor();
		dbsbkColor();
		dbscFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		dbsSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		
		rdId.setMnemonic(KeyEvent.VK_E);
		rdAll.setMnemonic(KeyEvent.VK_C);
		btnSearch.setMnemonic(KeyEvent.VK_H);
		btnBack.setMnemonic(KeyEvent.VK_B);
		btnDelete.setMnemonic(KeyEvent.VK_L);
		
		choice.addFocusListener(this);
		choice_1.addFocusListener(this);
		btnSearch.addActionListener(this);
		rdAll.addItemListener(this);
		rdId.addItemListener(this);
		rdId.addFocusListener(this);
		rdAll.addFocusListener(this);
		lblSelectAll.addItemListener(this);
		btnDelete.addActionListener(this);
		textField.addTextListener(this);
		textField.addFocusListener(this);
		btnBack.addActionListener(this);
		setVisibility(false);

	}
	public void setDisabled(boolean b)
	{
		rdAll.setEnabled(b);
		rdId.setEnabled(b);
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
			    {//if lblselect is selected

			    	if(ch==1||ch==2)//For making checkbox to get selected
			    	{
			    		
				    	btnDelete.setEnabled(true);
				    	kl=1;
				    	for(int i=0;i<mtb.getRowCount();i++)
						{
							boolean hkb=(boolean) mtb.getValueAt(i, 0);
							if(!hkb)
								mtb.setValueAt(values, i, 0);
						}
				    	kl=5;
			    	}
			    	else
			    	{		
				    	btnDelete.setEnabled(true);
				    	kl=1;
				    	for(int i=0;i<mb.getRowCount();i++)
						{
							boolean hkb=(boolean) mb.getValueAt(i, 0);
							if(!hkb)
								mb.setValueAt(values, i, 0);
						}
				    	kl=5;
			    	}

			    } 
			    else // For making check box to get unselected
			    {

			    	if(ch==1||ch==2)
			    	{

				    	kl=0;
				    	btnDelete.setEnabled(false);
				    	for(int i=0;i<mtb.getRowCount();i++)
						{
								mtb.setValueAt(values, i, 0);
						}
				    	kl=5;
			    	}
			    	else
			    	{

				    	kl=0;
				    	btnDelete.setEnabled(false);
				    	for(int i=0;i<mb.getRowCount();i++)
						{
								mb.setValueAt(values, i, 0);
						}
				    	kl=5;	
			    	}
			    	
			    }
		
	}


		if(arg0.getSource()==rdId)
			{
				if(rdId.isSelected())
				{
					if(scroll instanceof JScrollPane)
						scroll.setVisible(false);
					ch=1;
					rdAll.setVisible(false);
					setVisibility(false);
					btnSearch.setEnabled(true);
					btnBack.setEnabled(true);
					textField.setVisible(true);
					lblSelectAll.setVisible(false);
					btnDelete.setVisible(false);
					if(lblSelectAll.getState())
					{
			    	kl=0;
			    	btnDelete.setEnabled(false);
			    	for(int i=0;i<mb.getRowCount();i++)
					{
							mb.setValueAt(values, i, 0);
					}
			    	kl=5;
			    	lblSelectAll.setState(false);
					}
					updateUI();
				}
				
			}
			else if(arg0.getSource()==rdAll)
			{
				if(rdAll.isSelected())
				{
					if(scroll instanceof JScrollPane)
						scroll.setVisible(false);
					
					if(idscroll instanceof JScrollPane)
						idscroll.setVisible(false);
					rdId.setVisible(false);
					btnSearch.setEnabled(true);
					btnBack.setEnabled(true);
					ch=2;
					textField.setVisible(false);
					setVisibility(true);
					choice_1.setVisible(true);
					lblYear.setVisible(true);
					lblSelectAll.setVisible(false);
					btnDelete.setVisible(false);
					if(lblSelectAll.getState())
					{
			    	kl=0;
			    	btnDelete.setEnabled(false);
			    	for(int i=0;i<mb.getRowCount();i++)
					{
							mb.setValueAt(values, i, 0);
					}
			    	kl=5;
			    	lblSelectAll.setState(false);
					}
					updateUI();
				}
			}
		
		
	
	}

	public void delAllStudent()
	{
		int ciid[],count=0;
		try{
			PreparedStatement psmt=con.prepareStatement("select * from student where id=?");
			PreparedStatement ps=con.prepareStatement("insert into strash (img,id,name,fname,email,mobile,course,year,fee,balance) "+ "values(?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement pst=con.prepareStatement("delete from student where id=?");
			   for (String number : aiid) {
				psmt.setString(1,number);
				rs=psmt.executeQuery();
				byte[] bytes=null;
				while(rs.next())
				{
					bytes=rs.getBytes("img");
					id = rs.getString("id");
					name = rs.getString("name");
					fname=rs.getString("fname");
					email=rs.getString("email");
					mobile=rs.getString("mobile");
					course = rs.getString("course");
					year = rs.getInt("year");
					fee=rs.getFloat("fee");
					bal=rs.getFloat("balance");
					
					rows++;
				}
				ps.setBytes(1, bytes);
				ps.setString(2,id);
				ps.setString(3,name);
				ps.setString(4, fname);
				ps.setString(5, email);
				ps.setString(6, mobile);
				ps.setString(7,course);
				ps.setFloat(8,fee);
				ps.setInt(9,year);
				ps.setFloat(10,bal);
				int s=ps.executeUpdate();

			
			pst.setString(1,number);
			pst.execute();
			count++;
		}	
			}catch(Exception ee){JOptionPane.showMessageDialog(this, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
	
		JOptionPane.showMessageDialog(this,"Records Deleted Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
		   for (Integer number : ilist) {
			     tiid[ilist.indexOf(number)]=number;
			    } 
		   ilist.clear();
		ciid=new int[count];
		for(int i=0;i<mb.getRowCount();i++)
		{
			   for (String number : aiid) {
				   if(mb.getValueAt(i, 3).equals(number))
				   {
					   ilist.add(i);
				   }
				  
			   }
		}
		 for (Integer number : ilist) {
		     ciid[ilist.indexOf(number)]=number;//row numbers of the m 
		    } 
		 ilist.clear();
		if(ch==1||ch==2)
		{
			mb.removeRows(ciid);
			mtb.removeRows(tiid);
		}
		else
			mb.removeRows(ciid);

	

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

			if(ch==1)//For rdid
			{
				
				 
				if(idscroll instanceof JScrollPane)
				idscroll.setVisible(false);
				rdAll.setVisible(true);
				textField.setVisible(false);
				textField.setText("");
				textField.setEditable(true);
				bgr.clearSelection();
				scroll.setVisible(true);
				setDisabled(true);
				btnSearch.requestFocus();
				lblSelectAll.setVisible(true);
				btnDelete.setVisible(true);
				updateUI();

			}
			else if(ch==2)
			{
				if(allscroll instanceof JScrollPane)//For rdAll
				allscroll.setVisible(false);
				rdId.setVisible(true);
				bgr.clearSelection();
				choice.setVisible(false);
				choice_1.setVisible(false);
				lblYear.setVisible(false);
				lblCourse.setVisible(false);
				lblSelectAll.setVisible(true);
				btnDelete.setVisible(true);
				scroll.setVisible(true);
				setDisabled(true);
				btnSearch.requestFocus();
			}
			else
			{
				
			}
			ch=0;
			btnSearch.setEnabled(false);
		}
		if(arg0.getSource()==btnDelete)
		{
			
			if(MainPage.labelUser.getText().trim().equalsIgnoreCase("ADMIN"))
			{
		  	int i=0,j=0,tid=0;

			if(dl==0&&rdb==2)
			{
				if(JOptionPane.showConfirmDialog(null,"Do you wish to delete :\" All records from student", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
				delAllStudent();
				aiid.clear();
				lblSelectAll.setState(false);
				lblSelectAll.setEnabled(false);
				btnDelete.setEnabled(false);
				}
			}
			else
			{
				if(JOptionPane.showConfirmDialog(null,"Do you wish to delete :\" the selected records from student", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					delAllStudent();
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
		if(arg0.getSource()==btnSearch)
		{
			btnSearch.setEnabled(false);
		if(ch==1)	
		{
				mtb=new MTableModel();
				tab=new JTable(mtb);
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
				idscroll=new JScrollPane(tab);
				idscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				idscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				add(idscroll,gbc_scrollPane);
				if(tab.getRowCount()==0)
				{
				textField.setEditable(true);
				idscroll.setVisible(false);
				btnSearch.setEnabled(true);
				JOptionPane.showMessageDialog(this,"No Data Found", "Error!!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				textField.setEditable(true);
				idscroll.setVisible(true);
				lblSelectAll.setVisible(true);
				}
				updateUI();
			
				
		}


		else if(ch==2)
		{
					mtb=new MTableModel();
					tab=new JTable(mtb);
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
		if(allscroll instanceof JScrollPane)
			allscroll.setVisible(false);
		else if(idscroll instanceof JScrollPane)
			idscroll.setVisible(false);
		lblSelectAll.setVisible(false);
		btnDelete.setVisible(false);
		btnSearch.setEnabled(true);
		
		}
		if(arg0.getSource()==choice_1)
		{
		if(allscroll instanceof JScrollPane)
			allscroll.setVisible(false);
		else if(idscroll instanceof JScrollPane)
			idscroll.setVisible(false);
		lblSelectAll.setVisible(false);
		btnDelete.setVisible(false);
		btnSearch.setEnabled(true);
		}
		if(arg0.getSource()==textField)
		{
			textField.selectAll();
		}
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

		
	}

    class MTableModel extends AbstractTableModel {
      	 List<Row> rows = new ArrayList<Row>();
   		private  final long serialVersionUID = 1L;
    	String colNames[]={" ","DELETE","SL NO","STUDENT ID","NAME","COURSE","FEE","YEAR OF JOIN","BALANCE"};
       
          Row ro;
          public int k=0,lk=0;
          public MTableModel() {
              int l=1;
              
              try{	
            	  if(ch==1)
            	  {
      				PreparedStatement pmt=con.prepareStatement("select * from student where id=?");
      				pmt.setString(1,textField.getText().trim());
      				rk=pmt.executeQuery();
            	  }
            	  if(ch==2)
            	  {
            		  if(choice.getSelectedItem().equals("----Select----"))
            			  throwError();
      				PreparedStatement ptt=con.prepareStatement("select * from student where course=? and year=?");
					ptt.setString(1,(String) choice.getSelectedItem());
					ptt.setInt(2,Integer.parseInt((String) choice_1.getSelectedItem()));
					rk=ptt.executeQuery();
	
            	  }
            	  dlgcntrl=0;
              while (rk.next()) {
            	  
                  values1 = new Object[9];
                  values1[0] = Boolean.FALSE;
                  values1[1] = new JButton("Delete");
                  values1[2] = ""+l;
                  values1[3] = rk.getString("id");
                  values1[4] = rk.getString("name");
                  values1[5] = rk.getString("course");
                  values1[6] = rk.getString("fee");
                  values1[7] = rk.getString("year");
                  values1[8] = rk.getString("balance");
                  Row row = new Row(values1);
                  rows.add(row);
                  l++;
                 
              }
          
         
              }catch(Exception e){
            	  dlgcntrl=1;
            	  JOptionPane.showMessageDialog(null, "Select the details correctly","Errorr!!",JOptionPane.ERROR_MESSAGE);}
          }

          // ADDED


          public int getColumnCount() {
              return colNames.length;
          }


          public int getRowCount() {
              return rows.size();
          }

          public String getColumnName(int col) {
              return colNames[col];
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
          	dl=0;
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
           		   lblSelectAll.setState(true);
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
   		String lid,lname,lcourse,ldate,fname,email,mobile;
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

							if(ch==1||ch==2)
							{
							for(int i=0;i<table.getRowCount();i++)
							{
								if(i!=row)
								{
								boolean hkb=(boolean) table.getValueAt(i, 0);
								if(hkb)
									mtb.setValueAt(values, i, 0);
								}
							}
							if(!(boolean) table.getValueAt(row, 0))
								mtb.setValueAt(values, row, 0);
							}
							else
							{
								for(int i=0;i<table.getRowCount();i++)
								{
									if(i!=row)
									{
									boolean hkb=(boolean) table.getValueAt(i, 0);
									if(hkb)
										mb.setValueAt(values, i, 0);
									}
								}
								if(!(boolean) table.getValueAt(row, 0))
									mb.setValueAt(values, row, 0);
							}
							lid=(String) table.getValueAt(rw, 3);
							lname=(String) table.getValueAt(rw, 4);
							lcourse=(String) table.getValueAt(rw, 5);
							lfee=Float.parseFloat((String) table.getValueAt(rw, 6));
							lyear=Integer.parseInt((String)  table.getValueAt(rw, 7));
							lbal=Float.parseFloat((String) table.getValueAt(rw, 8));	

					    		if(JOptionPane.showConfirmDialog(null,"Do you wish to delete student smd ' "+lid+" '", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					    		{
					    			try{
					    				PreparedStatement pstt=con.prepareStatement("select img,fname,email,mobile from student where id=?");
					    				pstt.setString(1,lid);
					    				rk=pstt.executeQuery();
					    				byte[] bytes=null;
					    				if(rk.next()){
					    					bytes=rk.getBytes("img");
					    					fname=rk.getString("fname");
					    					email=rk.getString("email");
					    					mobile=rk.getString("mobile");
					    				}

					    				PreparedStatement ps=con.prepareStatement("insert into strash (img,id,name,fname,email,mobile,course,year,fee,balance) "+ "values(?,?,?,?,?,?,?,?,?,?)");//Sibgathulla from prevous
					    				ps.setBytes(1, bytes);
					    				ps.setString(2,lid);
					    				ps.setString(3,lname);
					    				ps.setString(4,fname);
					    				ps.setString(5,email);
					    				ps.setString(6,mobile);
					    				ps.setString(7,lcourse);
					    				ps.setInt(8,lyear);
					    				ps.setFloat(9,lfee);
					    				ps.setFloat(10,lbal);
	
					    				int s=ps.executeUpdate();
					    				PreparedStatement pst=con.prepareStatement("delete from student where id=?");
					    				pst.setString(1,lid);
					    				pst.execute();
					    				if(ch==1||ch==2)
					    				{
						    				for(int i=0;i<mb.getRowCount();i++)
						    				{
						    					if(mtb.getValueAt(row, 3).equals(mb.getValueAt(i,3)))
						    					mb.removeRow(i);
						    				}
					    				mtb.removeRow(row);
					    				}
					    				else
						    			mb.removeRow(row);					    					
					    				
					    				}catch(Exception ee){JOptionPane.showMessageDialog(null, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
					    			if(ch==1||ch==2)
					    			{
									if(mtb.getRowCount()==0)
										lblSelectAll.setEnabled(false);
					    			}
					    			else
					    			{
									if(mb.getRowCount()==0)
										lblSelectAll.setEnabled(false);					    				
					    			}
									pl--;
					
					    		}
					    		else
					    		{
					    			if(ch==1||ch==2)
				   			        mtb.setValueAt(values, row, 0);
					    			else
				   			        mb.setValueAt(values, row, 0);					    				
					    		}
					    		aiid.clear();

					    	
						
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
		lblStudentDatabase.setForeground(new Color(SetColor.mtColor));
	}
	public void dbscColor()
	{
		rdId.setForeground(new Color(SetColor.cColor));
		rdAll.setForeground(new Color(SetColor.cColor));
		lblCourse.setForeground(new Color(SetColor.cColor));
		lblYear.setForeground(new Color(SetColor.cColor));
		lblSelectAll.setForeground(new Color(SetColor.cColor));
		lblSearchCategory.setForeground(new Color(SetColor.mtColor));
		
	}
	public void dbsbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		lblSelectAll.setBackground(new Color(SetColor.bkColor));
		rdId.setBackground(new Color(SetColor.bkColor));
		rdAll.setBackground(new Color(SetColor.bkColor));
	}
	public void dbscFont(String ctFType,int ctfProp,int ctSize)
	{
		rdId.setFont(new Font(ctFType,ctfProp,ctSize));
		rdAll.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCourse.setFont(new Font(ctFType,ctfProp,ctSize));
		lblYear.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSelectAll.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSearchCategory.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void dbsSTFont(String stFType,int stfProp,int stSize)
	{
		lblStudentDatabase.setFont(new Font(stFType,stfProp,stSize));
	}
}
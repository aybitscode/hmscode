package com.hotelmanagement;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.hms.util.DBConnection;

public class TransactionTrash extends JPanel implements ItemListener,ActionListener,FocusListener {
	ButtonGroup bg;
	GridBagConstraints gbc_scrollPane;
	public static Checkbox lblSelectAll;
	private JScrollPane scrollPane;
    private boolean DEBUG = false,b;
	ResultSet rs,rk;
	Statement st,stmt;
	String id="",name="",course="",date="",cors="";
	int uid,rows=0,ch=0,rdb=1,year;
	float pamt=0.0f,bal=0.0f;
	JTable table;
	Object[] values,values1;
	int tiid[]=new int[300];
	 ArrayList<Integer> ilist = new ArrayList<Integer>(100);
	 ArrayList <String> aiid=new ArrayList<String>(100);
	 ArrayList <String> aid=new ArrayList<String>(100);
	int kl=5,dl=2,pl=0,rl=0;
	 MyTableModel m,mytb;
	TableCellRenderer buttonRender; 
	TableCellRenderer buttonRend;
	private JButton btnDelete;
	/**
	 * Create the panel.
	 */
	MainPage mpg;
	Connection con = DBConnection.getDBConnection();
	public TransactionTrash(MainPage mpg) {
		this.mpg=mpg;
		buttonRender = new JTableButtonRendererB();
		try{
			//mpg.data=new SqlDataCon();
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("select uid,id,name,course,year,pdate,amtpd,balance from trash where uid>0");
			  rk=st.executeQuery("select * from strash");
		}catch(Exception e){JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		bg=new ButtonGroup();
		
		lblSelectAll = new Checkbox("Select All");
		GridBagConstraints gbc_lblSelectAll = new GridBagConstraints();
		gbc_lblSelectAll.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSelectAll.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectAll.gridx = 0;
		gbc_lblSelectAll.gridy = 0;
		add(lblSelectAll, gbc_lblSelectAll);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		add(btnDelete, gbc_button_1);
		 m = new MyTableModel();
		table = new JTable(m);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(false);
		table.getColumn("DELETE").setCellRenderer(buttonRender);
		table.getColumn("RESTORE").setCellRenderer(buttonRender);
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.getColumn(" ").setMaxWidth(20);
		table.getColumn("DELETE").setMaxWidth(80);
		table.getColumn("RESTORE").setMaxWidth(80);
		table.getColumn("SL NO").setMaxWidth(50);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane = new JScrollPane(table);
		if(m.getRowCount()==0)
		{
			lblSelectAll.setEnabled(false);
			Thread t=new Thread(){
				public void run()
				{
			JOptionPane.showMessageDialog(null, "Transactions are empty","Message",JOptionPane.INFORMATION_MESSAGE);
				}
			};
			t.start();
		}
		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		tshmtColor();
		tshcColor();
		tshbkColor();
		tshcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		tshSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		
		btnDelete.setMnemonic(KeyEvent.VK_L);
		lblSelectAll.addItemListener(this);
		btnDelete.addActionListener(this);

	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
	if(arg0.getSource()==lblSelectAll)
	{
		if(rdb==1)
		{
		int row=0,col=0;
		  Checkbox cbox = (Checkbox) arg0.getItemSelectable();
		    if (cbox.getState()) {

		    	if(ch==3||ch==4)
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
		    else
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
	}
	}
	public void restoreTransaction()
	{

		try{
			PreparedStatement psmt=con.prepareStatement("select uid,id,name,course,pdate,amtpd,balance from trash where uid=?");
			PreparedStatement ps=con.prepareStatement("insert into trash (uid,id,name,course,pdate,amtpd,balance) "+ "values(?,?,?,?,?,?,?)");
			PreparedStatement pst=con.prepareStatement("delete from payments where uid=?");
				rs=psmt.executeQuery();
				while(rs.next())
				{
					uid=rs.getInt("uid");
					id = rs.getString("id");
					name = rs.getString("name");
					course = rs.getString("course");
					date=rs.getString("pdate");
					pamt = rs.getInt("amtpd");
					bal=rs.getInt("balance");
					year=rs.getInt("year");
					rows++;
				}
				ps.setInt(1,uid);
				ps.setString(2,id);
				ps.setString(3,name);
				ps.setString(4,course);
				ps.setInt(5, year);
				ps.setString(6,date);
				ps.setFloat(7,pamt);
				ps.setFloat(8,bal);
				int s=ps.executeUpdate();
			pst.execute();
			}catch(Exception ee){System.out.println(ee);}
	
		JOptionPane.showMessageDialog(this,"Records Deleted Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
		   for (Integer number : ilist) {
			     tiid[ilist.indexOf(number)]=number;
			    }  
		m.removeRows(tiid);
		if(ch==3||ch==4)
			mytb.removeRows(tiid);

	
	}
	public void delAllTransactions()
	{
		try{

			PreparedStatement pst=con.prepareStatement("delete from trash where uid=?");
			   for (String number : aid) {	
			pst.setInt(1,Integer.parseInt(number));
			pst.execute();
		}	
			}catch(Exception ee){JOptionPane.showMessageDialog(this, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
		JOptionPane.showMessageDialog(this,"Records Deleted Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
		   for (Integer number : ilist) {
			     tiid[ilist.indexOf(number)]=number;
			    }  
		m.removeRows(tiid);
		if(ch==3||ch==4)
			mytb.removeRows(tiid);

	

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnDelete)
		{
   			if(MainPage.labelUser.getText().trim().equalsIgnoreCase("ADMIN"))
   			{
		  	int i=0,j=0,tid=0;
			if(dl==0&&rdb==1)
			{
				if(JOptionPane.showConfirmDialog(null,"Do you wish to delete :\" All  records from transactions", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					delAllTransactions();
					aid.clear();
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
					aid.clear();
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
   				JOptionPane.showMessageDialog(this,"No Privileges, login as admin","Message",JOptionPane.INFORMATION_MESSAGE);
   			}
	}
}
	
	@Override
	public void focusGained(FocusEvent arg0) {
	}
	@Override
	public void focusLost(FocusEvent arg0) {
	}
	
   private class MyTableModel extends AbstractTableModel {
   	 List<Row> rows = new ArrayList<Row>();
   	String columnNames[]={" ","DELETE","RESTORE","SL NO","TRANSACTION ID","STUDENT ID","NAME","COURSE","YEAR OF JOIN", "DATE","AMOUNT PAID","BALANCE"};
 	String sdate="";
   	SimpleDateFormat sdfSrc = new SimpleDateFormat("dd/MMM/yyyy");
       Row ro;
       public int k=0,lk=0;
       public MyTableModel() {
           int l=1;
           //values = new Object[12];
           try{
           while (rs.next()) {
               values = new Object[12];
               values[0] = Boolean.FALSE;
               values[1] = new JButton("Delete");
               values[2] = new JButton("Restore");
               values[3] = ""+l;
               values[4] = rs.getString("uid");
               values[5] = rs.getString("id");
               values[6] = rs.getString("name");
               values[7] = rs.getString("course");
               values[8] = rs.getString("year");
               sdate = sdfSrc.format(rs.getDate("pdate"));
               values[9] = sdate;
               values[10] = rs.getString("amtpd");
               values[11] = rs.getString("balance");
               Row row = new Row(values);
               rows.add(row);
               rl++;
               l++;
           }}catch(Exception e){JOptionPane.showMessageDialog(null, ""+e,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
       }

       // ADDED


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
       	//System.out.println(""+ro.values[3]);
      
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
            		  aid.add((String) ro.values[4]);
            		  pl++;
            		  dl=1;
            		  if(pl>1)
            		  btnDelete.setEnabled(true);
            	  }
            	  else
            	  {
             	  pl--;
             	  ilist.remove(ilist.indexOf(row));
             	  aid.remove(aid.indexOf(ro.values[4]));
             	  if(pl<=1)
             	 btnDelete.setEnabled(false);
             	  lk=0;
            	  }
        	   }
       else if(kl==1)
       {
    	ro.values[0]=Boolean.TRUE;
       	aid.add((String) ro.values[4]);
       	ilist.add(row);
       	dl=0;//if we go for select all
       	pl++;
       	
       }
       else if(kl==0)
       {
    	   pl=0;                  
    	   ro.values[0]=Boolean.FALSE;
    	   ilist.clear();
    	   aid.clear();
    	   lk=0;
       }else{}
           	   if(rows.size()==pl)
           	   {
           		   lblSelectAll.setState(true);
           	   }
           	   else
           		   lblSelectAll.setState(false);
           }
           fireTableCellUpdated(row, col);
          

           if (DEBUG) {
               printDebugData();
           }
           removeDuplicates(aid);
	       removeDuplicates(ilist);
	

       }
  
       private void printDebugData() {
           int numRows = getRowCount();
           int numCols = getColumnCount();

           for (int i=0; i < numRows; i++) {
               for (int j=0; j < numCols; j++) {
               }
               //System.out.println();
           }
           //System.out.println("--------------------------");
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
   	class JTableButtonRendererB implements TableCellRenderer {		
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
   		float lamt=0.0f,lbal=0.0f,lfee=0.0f;
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
						luid=Integer.parseInt((String)table.getValueAt(rw, 4));
						lid=(String) table.getValueAt(rw, 5);
						lname=(String) table.getValueAt(rw, 6);
						lcourse=(String) table.getValueAt(rw, 7);
						lyear=Integer.parseInt((String)table.getValueAt(rw, 8));
						ldate=(String) table.getValueAt(rw, 9);
						SimpleDateFormat sdf= new SimpleDateFormat("dd/MMM/yyyy");
						try {
						Date udate = sdf.parse(ldate);
						sdate = new java.sql.Date(udate.getTime());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						lamt=Float.parseFloat((String) table.getValueAt(rw, 10));
						lbal=Float.parseFloat((String)table.getValueAt(rw, 11));		
					if(column==1)
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
						if(JOptionPane.showConfirmDialog(null,"Do you wish to delete transaction ' "+luid+" '", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
						{
							try{

									PreparedStatement pst= con.prepareStatement("delete from trash where uid=?");
									pst.setInt(1,luid);
									pst.execute();
									m.removeRow(rw);
								}catch(Exception ee){JOptionPane.showMessageDialog(null, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
							if(m.getRowCount()==0)
							{
								lblSelectAll.setEnabled(false);
							}
							pl--;
						}
						else
					 	m.setValueAt(values, row, 0);
   			    	}else
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
   			    		if(JOptionPane.showConfirmDialog(null,"Do you wish to restore transaction ' "+luid+" '", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
   			    		{
   			    		
   			    			try{
   			    				
   			    				PreparedStatement ps= con.prepareStatement("insert into payments (uid,id,name,course,pdate,amtpd,balance,year) "+ "values(?,?,?,?,?,?,?,?)");//sibgathulla form previous
   			    				ps.setInt(1,luid);
   			    				ps.setString(2,lid);
   			    				ps.setString(3,lname);
   			    				ps.setString(4,lcourse);
   			    				ps.setDate(5,sdate);
   			    				ps.setFloat(6,lamt);
   			    				ps.setFloat(7,lbal);
   			    				ps.setInt(8, lyear);
   			    				int s=ps.executeUpdate();
   			    				
   			    				PreparedStatement pst=con.prepareStatement("delete from trash where uid=?");
   			    				pst.setInt(1,luid);
   			    				pst.execute();
   			    				m.removeRow(row);
   			    				
   			    				}catch(Exception ee){JOptionPane.showMessageDialog(null, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
							if(m.getRowCount()==0)
							{
								lblSelectAll.setEnabled(false);
							}
							pl--;
   			
   			    		}
   			    		else
   			        	m.setValueAt(values, row, 0);
   			    	}
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
   	public void tshmtColor()
   	{
   	}
   	public void tshcColor()
   	{
   		table.setForeground(new Color(SetColor.cColor));
   		lblSelectAll.setForeground(new Color(SetColor.cColor));
   	}
   	public void tshbkColor()
   	{
   		setBackground(new Color(SetColor.bkColor));
		lblSelectAll.setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		lblSelectAll.setBackground(new Color(SetColor.bkColor));
   	}
   	public void tshcFont(String ctFType,int ctfProp,int ctSize)
   	{
   		table.setFont(new Font(ctFType,ctfProp,ctSize));
   		lblSelectAll.setFont(new Font(ctFType,ctfProp,ctSize));
   	}
   	public void tshSTFont(String stFType,int stfProp,int stSize)
   	{
   	}
}



	




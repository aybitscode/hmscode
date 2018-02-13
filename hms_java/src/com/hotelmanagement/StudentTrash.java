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
import java.util.ArrayList;
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

public class StudentTrash extends JPanel implements ItemListener,ActionListener,FocusListener {
	ButtonGroup bg;
	GridBagConstraints gbc_scrollPane;
	public static Checkbox lblSelectAll;
	private JScrollPane scroll;
    private boolean DEBUG = false,b;
	ResultSet rk;
	Statement st,stmt;
	int ch=0,rdb;
	JTable tab;
	Object[] values,values1;
	int tiid[]=new int[300];
	 ArrayList<Integer> ilist = new ArrayList<Integer>(100);
	 ArrayList <String> aiid=new ArrayList<String>(100);
	int kl=5,dl=2,pl=0;
	MTableModel mb; 
	TableCellRenderer buttonRender; 
	TableCellRenderer buttonRend;
	private JButton btnDelete;
	/**
	 * Create the panel.
	 */
	MainPage mpg;
	Connection con = DBConnection.getDBConnection();
	public StudentTrash(MainPage mpg) {
		this.mpg=mpg;
		buttonRender = new JTableButtonRendererB();
		try{
			//mpg.data=new SqlDataCon();
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		gbc_button_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		add(btnDelete, gbc_button_1);
		
		gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		
		 mb=new MTableModel();
			tab=new JTable(mb);
			tab.setFont(new Font(SFont.ctFType,SFont.ctfProp,SFont.ctSize));
			tab.setBackground(new Color(SetColor.bkColor));
			tab.setForeground(new Color(SetColor.cColor));
			tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tab.setFillsViewportHeight(true);
			tab.setAutoCreateRowSorter(false);
			tab.getColumn("DELETE").setCellRenderer(buttonRender);
			tab.getColumn("RESTORE").setCellRenderer(buttonRender);
			tab.addMouseListener(new JTableButtonMouseListener(tab));
			tab.getColumn(" ").setMaxWidth(20);
			tab.getColumn("DELETE").setMaxWidth(80);
			tab.getColumn("RESTORE").setMaxWidth(80);
			tab.getColumn("SL NO").setMaxWidth(50);
			tab.getTableHeader().setReorderingAllowed(false);
			scroll=new JScrollPane(tab);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			add(scroll,gbc_scrollPane);
			scroll.setVisible(true);
		if(mb.getRowCount()==0)
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

			int row=0,col=0;
			  Checkbox cbox = (Checkbox) arg0.getItemSelectable();
			    if (cbox.getState()) 
			    {
			    	btnDelete.setEnabled(true);
			    	kl=1;
			    	for(int i=0;i<mb.getRowCount();i++)
					{
						boolean hkb=(boolean) mb.getValueAt(i, 0);
						if(!hkb)
							mb.setValueAt(values1, i, 0);
					}
			    	kl=5;		    	
			    } 
			    else
			    {
			    	kl=0;
			    	btnDelete.setEnabled(false);
			    	for(int i=0;i<mb.getRowCount();i++)
					{
							mb.setValueAt(values1, i, 0);
					}
			    	kl=5;
			    }
		
	}
	}


	public void delAllStudent()
	{
		try{
			PreparedStatement pst=con.prepareStatement("delete from strash where id=?");
			   for (String number : aiid) {
			pst.setString(1,number);
			pst.execute();
		}	
			}catch(Exception ee){JOptionPane.showMessageDialog(this, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
	
		JOptionPane.showMessageDialog(this,"Records Deleted Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
		   for (Integer number : ilist) {
			     tiid[ilist.indexOf(number)]=number;
			    }  
		mb.removeRows(tiid);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
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
	
    class MTableModel extends AbstractTableModel {
      	 List<Row> rows = new ArrayList<Row>();
   		private  final long serialVersionUID = 1L;

    	String colNames[]={" ","DELETE","RESTORE","SL NO","STUDENT ID","NAME","COURSE","YEAR OF JOIN","FEE","BALANCE"};
       
          Row ro;
          public int k=0,lk=0;
          public MTableModel() {
              int l=1;
              values1 = new Object[10];
              
              try{	
              while (rk.next()) {
            	  
                  values1 = new Object[10];
                  values1[0] = Boolean.FALSE;
                  values1[1] = new JButton("Delete");
                  values1[2] = new JButton("Restore");
                  values1[3] = ""+l;
                  values1[4] = rk.getString("id");
                  values1[5] = rk.getString("name");
                  values1[6] = rk.getString("course");
                  values1[7] = rk.getString("year");
                  values1[8] = rk.getString("fee");
                  values1[9] = rk.getString("balance");
                  Row row = new Row(values1);
                  rows.add(row);
                  l++;
              }}catch(Exception e){JOptionPane.showMessageDialog(null, ""+e,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
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
              //if(rowIndex > rows.size()){
                 // return null;
              //}
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
               		  aiid.add((String) ro.values[4]);
               		  pl++;
               		  dl=1;
               		  if(pl>1)
               		  btnDelete.setEnabled(true);
               	  }
               	  else
               	  {
                	  pl--;
                	  ilist.remove(ilist.indexOf(row));
                	  aiid.remove(aiid.indexOf(ro.values[4]));
                	  if(pl<=1)
                	 btnDelete.setEnabled(false);
                	  lk=0;
               	  }
           	   }
          else if(kl==1)
          {
          	ro.values[0]=Boolean.TRUE;
          	aiid.add((String) ro.values[4]);
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
           		   lblSelectAll.setState(false);
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
                 // System.out.println();
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
   		String lid,lname,lcourse,ldate,fname,email,mobile;
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

					lid=(String) table.getValueAt(rw, 4);
					lname=(String) table.getValueAt(rw, 5);
					lcourse=(String) table.getValueAt(rw, 6);
					lyear=Integer.parseInt((String)table.getValueAt(rw, 7));
					lfee=Float.parseFloat((String) table.getValueAt(rw, 8));
					lbal=Float.parseFloat((String)table.getValueAt(rw, 9));	
				if(column==1)
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
					if(JOptionPane.showConfirmDialog(null,"Do you wish to delete student ' "+lname+" '", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					{
						try{

								PreparedStatement pst=con.prepareStatement("delete from strash where id=?");
								pst.setString(1,lid);
								pst.execute();
								mb.removeRow(rw);
							}catch(Exception ee){JOptionPane.showMessageDialog(null, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
						if(mb.getRowCount()==0)
						{
							lblSelectAll.setEnabled(false);
						}
						pl--;
					}
			    	else
	   			        mb.setValueAt(values, row, 0);
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
			    		if(JOptionPane.showConfirmDialog(null,"Do you wish to restore student ' "+lid+" '", "choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			    		{
			    			try{
			    				PreparedStatement pstt=con.prepareStatement("select img,fname,email,mobile from strash where id=?");
			    				pstt.setString(1,lid);
			    				rk=pstt.executeQuery();
			    				byte[] bytes=null;
			    				if(rk.next()){
			    					bytes=rk.getBytes("img");
			    					fname=rk.getString("fname");
			    					email=rk.getString("email");
			    					mobile=rk.getString("mobile");
			    				}
			    			
			    				PreparedStatement ps=con.prepareStatement("insert into student (img,id,name,fname,email,mobile,course,year,fee,balance) "+ "values(?,?,?,?,?,?,?,?,?,?)");
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
			    				PreparedStatement pst=con.prepareStatement("delete from strash where id=?");
			    				pst.setString(1,lid);
			    				pst.execute();
			    				mb.removeRow(row);
			    				
			    				}catch(Exception ee){JOptionPane.showMessageDialog(null, ""+ee,"Errorr!!",JOptionPane.ERROR_MESSAGE);}
							if(mb.getRowCount()==0)
							{
								lblSelectAll.setEnabled(false);
							}
							pl--;
			
			    			}
   			    			else
   	   			        	mb.setValueAt(values, row, 0);
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
   		lblSelectAll.setForeground(new Color(SetColor.cColor));
   	}
   	public void tshbkColor()
   	{
   		setBackground(new Color(SetColor.bkColor));
		lblSelectAll.setBackground(new Color(SetColor.bkColor));
		lblSelectAll.setBackground(new Color(SetColor.bkColor));
   	}
   	public void tshcFont(String ctFType,int ctfProp,int ctSize)
   	{
   		lblSelectAll.setFont(new Font(ctFType,ctfProp,ctSize));
   	}
   	public void tshSTFont(String stFType,int stfProp,int stSize)
   	{
   	}
}



	




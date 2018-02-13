package com.hms.jtable;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

 
public class EditableTableExample extends JFrame implements ActionListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btn;
	EmployeeTableModel model;
	public EditableTableExample()
    {
        Employee row1 = new Employee(1, "John", 40.0, false, new JButton("Update"));
        Employee row2 = new Employee(2, "Rambo", 70.0, false, new JButton("Update"));
        Employee row3 = new Employee(3, "Zorro", 60.0, true, new JButton("Update"));
         
        //build the list
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(row1);
        employeeList.add(row2);
        employeeList.add(row3);
         
        //create the model
        model = new EmployeeTableModel(employeeList);
        
        //create the table
        JTable table = new JTable(model);
        setLayout(new FlowLayout());
        setSize(500,500); 
        //add the table to the frame
        add(new JScrollPane(table));
         
        setTitle("Editable Table Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        
        btn = new JButton("Update");
        this.add(btn);
        btn.addActionListener(this);
       // this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditableTableExample();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn)
		{
			System.out.println("updated row is"+model.getValueAt(EmployeeTableModel.storeIndex, 1));
			System.out.println("update row number is"+ EmployeeTableModel.storeIndex);
		}
	}   
}
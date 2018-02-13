package com.hms.learning;
import javax.swing.*;    
import java.awt.event.*;    
import javax.swing.table.DefaultTableModel;  
 
class Program extends JFrame{    
    public static void main(String args[]){    
        Program main = new Program();    
        main.setVisible(true);    
    }    
 
    public Program(){    
        super("JTable Demo");    
 
        DefaultTableModel dtm = new DefaultTableModel(  
            new Object[][]  
            {    
                { "1", "John", "Smith", "j.smith@mailxyz.com" },    
                { "2", "Suzan", "Carter", "s.carter@mailxyz.com" },    
                { "3", "Abdul", "Latif", "a.latif@mailxyz.com" },    
                { "4", "Jia", "Lou", "j.lou@mailxyz.com" }    
            },    
            new Object[]  
            {   
                "Employee Id", "First Name", "Last Name", "Email"   
            }  
        );  
 
        final JTable table = new JTable(dtm);   
 
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
 
        this.getContentPane().add(table);    
        this.setSize(400,400);    
    }    
}

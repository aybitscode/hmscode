package com.hms.util;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.hms.controller.SeasonController;
import com.hms.view.BookingCheckout;
import com.hms.view.SingleLeftPanel;
 
public class SearchCouponModel extends AbstractListModel implements ComboBoxModel,
        KeyListener, ItemListener, PopupMenuListener {
   public ArrayList<String> db = new ArrayList<String>();
    ArrayList<String> data = new ArrayList<String>();
    String selection;
    public JComboBox cb;
    ComboBoxEditor cbe;
    int currPos = 0;
    Statement stmt;
    Connection con = DBConnection.getDBConnection();
 
    public SearchCouponModel(){
    	
    }
    public SearchCouponModel(JComboBox jcb, String query) {
 
        cb = jcb;
        cbe = jcb.getEditor();
        // here we add the key listener to the text field that the combobox is
        // wrapped around
        cbe.getEditorComponent().addKeyListener(this);
        //cb.addPopupMenuListener(this);
        
        // set up our "database" of items - in practice you will usuallu have a
        // proper db.
		try {
			stmt = con.createStatement();
			ResultSet rk = stmt.executeQuery(query);
			
			 int i = 0;
			 db.add("");
			while(rk.next())
			{		
			db.add(rk.getString(1));
			
			i++;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	       cb.getEditor().getEditorComponent().addFocusListener( new FocusAdapter() {
//	            @Override
//	            public void focusGained( FocusEvent e ) {
//	                if(e.getSource() instanceof JTextComponent) {
//	                    ((JTextComponent)e.getSource()).setCaretPosition( 0 );
//	                }
//	            }
//	        } );
  
    }
    
    public SearchCouponModel(JComboBox jcb, String query, String param) {
    	 
        cb = jcb;
        cbe = jcb.getEditor();
        // here we add the key listener to the text field that the combobox is
        // wrapped around
        cbe.getEditorComponent().addKeyListener(this);
        //cb.addPopupMenuListener(this);
 
        // set up our "database" of items - in practice you will usuallu have a
        // proper db.
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, param);
			ResultSet rk = stmt.executeQuery();
			db.add("");
			while(rk.next())
			{		
			db.add(rk.getString(1));
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
    }    
 
    public void clearData()
    {
    	data.clear();
    }
    public void updateModel(String in) {
        data.clear();
        // lets find any items which start with the string the user typed, and
        // add it to the popup list
        // here you would usually get your items from a database, or some other
        // storage...
        for (String s : db)
        {
            if (s.contains(in))
                data.add(s);
            
        }
 
        super.fireContentsChanged(this, 0, data.size());
 
        // this is a hack to get around redraw problems when changing the list
        // length of the displayed popups
        cb.hidePopup();
        cb.showPopup();
        if (data.size() != 0)
            cb.setSelectedIndex(0);
    }
    public void updateModelDrop() {
        data.clear();
        // lets find any items which start with the string the user typed, and
        // add it to the popup list
        // here you would usually get your items from a database, or some other
        // storage...
        for (String s : db)
        {

//            if (s.contains(in))
//            {
                data.add(s);
//                System.out.println(s);
//            }
            
        }
 
        super.fireContentsChanged(this, 0, data.size());
 
        // this is a hack to get around redraw problems when changing the list
        // length of the displayed popups
        //cb.hidePopup();
        //cb.showPopup();
        if (data.size() != 0)
            cb.setSelectedIndex(0);
    }
    public int getSize() {
        return data.size();
    }
 
    public Object getElementAt(int index) {
        return data.get(index);
    }
 
    public void setSelectedItem(Object anItem) {
        selection = (String) anItem;
    }
 
    public Object getSelectedItem() {
        return selection;
    }
 
    public void keyTyped(KeyEvent e) {
    }
 
    public void keyPressed(KeyEvent e) {
    }
 
    public void keyReleased(KeyEvent e) {
        String str = cbe.getItem().toString();
        JTextField jtf = (JTextField) cbe.getEditorComponent();
        currPos = jtf.getCaretPosition();
 
        if (e.getKeyChar() == KeyEvent.CHAR_UNDEFINED) {
            if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                cbe.setItem(str);
                jtf.setCaretPosition(currPos);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            cb.setSelectedIndex(cb.getSelectedIndex());
        } else {
            updateModel(cb.getEditor().getItem().toString());
            cbe.setItem(str);
            jtf.setCaretPosition(currPos);
        }
        cb.removePopupMenuListener(this);
    }
 
    public void itemStateChanged(ItemEvent e) {
        cbe.setItem(e.getItem().toString());
        cb.setSelectedItem(e.getItem());
        System.out.println("the item is"+e.getItem());
		String couponName = ""+e.getItem();
		if(couponName.trim().length()!=0){
		SeasonController obj_season = new SeasonController();
		BookingCheckout.text_discount.setText(obj_season.seasonCouponDiscount(couponName));

		}
    }
	@Override
	public void popupMenuCanceled(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		updateModelDrop();
	
	}
	
 
}
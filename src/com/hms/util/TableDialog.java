package com.hms.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JTable;

import com.hms.view.BookingDetails;
import com.hotelmanagement.MainPage;

public class TableDialog {
	public static void tableRecordDetails(final MainPage mpg, final JTable table, final String query) {
		table.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            int row = table.rowAtPoint(new Point(e.getX(), e.getY()));
		            int col = table.columnAtPoint(new Point(e.getX(), e.getY()));
		            System.out.println(row + " " + col);

		            String bookingID = (String) table.getModel().getValueAt(row, col);
		            System.out.println(bookingID + " was clicked");
		            if(col ==0)
		            {
		    		try {
		    			BookingDetails dialog = new BookingDetails(mpg, bookingID, query);
		    			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    			dialog.setVisible(true);
		    		} catch (Exception e1) {
		    			e1.printStackTrace();
		    		}
		            }
		            
		        }

		        @Override
		        public void mouseEntered(MouseEvent e) {
		            int col = table.columnAtPoint(new Point(0, 0));
		            if (col == 0) {
		            	table.setCursor(new Cursor(Cursor.HAND_CURSOR));

		            }
		        }

		        @Override
		        public void mouseExited(MouseEvent e) {
		            int col = table.columnAtPoint(new Point(0,0));
		            if (col != 0) {
		            	table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		            }
		        }
		    });
	}

}

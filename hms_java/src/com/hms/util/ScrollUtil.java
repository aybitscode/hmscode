package com.hms.util;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ScrollUtil {
	public static void scroll(JScrollPane c, int vertical) {        
	    switch (vertical) {
	        case SwingConstants.TOP:
	            c.getVerticalScrollBar().setValue(0);
	            break;
	        case SwingConstants.CENTER:
	            c.getVerticalScrollBar().setValue(c.getVerticalScrollBar().getMaximum());
	            c.getVerticalScrollBar().setValue(c.getVerticalScrollBar().getValue() / 2);
	            break;
	        case SwingConstants.BOTTOM:  
	            c.getVerticalScrollBar().setValue(c.getVerticalScrollBar().getMaximum());
	            break;
	    }
	}
}

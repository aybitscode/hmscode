package com.hms.learning;

import java.math.BigDecimal;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NumericInputVerifier extends InputVerifier {
//    @Override
//    public boolean verify(JComponent input) {
//        String text = ((JTextField) input).getText();
//        try {
//            BigDecimal value = new BigDecimal(text);
//            return (value.scale() <= Math.abs(4)); 
//        } catch (NumberFormatException e) {
//        	JOptionPane.showMessageDialog(this, "Please enter only numbers", "Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//    }

	
	   @Override
	    public boolean verify(JComponent input) {
	       String text = ((JTextField) input).getText();
	       try {
	          Integer.parseInt(text);
	       } catch (NumberFormatException e) {
	    	   JOptionPane.showMessageDialog(null, "Please enter only text", "Error", JOptionPane.ERROR_MESSAGE);
	          return false;
	       }

	       return true;
	    }
}
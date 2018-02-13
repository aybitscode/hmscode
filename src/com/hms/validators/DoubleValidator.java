package com.hms.validators;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class DoubleValidator extends AbstractValidator{

	public DoubleValidator(JDialog parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validationCriteria(JComponent c) {
		// TODO Auto-generated method stub
		boolean b = true;
        String text = null;
          text = ((JTextField) c).getText();

        try {
          double value = Double.parseDouble(text);
          if(value < 0)
         	 b = false;
          
        } catch (NumberFormatException e) {
           b = false;
        }
        if(text.trim().length()==0)
     	   b = true;
        
        return b;
	
	}

}

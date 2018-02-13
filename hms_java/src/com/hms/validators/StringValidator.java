package com.hms.validators;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class StringValidator extends AbstractValidator{

	public StringValidator(JDialog parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validationCriteria(JComponent c) {
		// TODO Auto-generated method stub
			boolean b = false;
		        String text = ((JTextField) c).getText();
		        if (text.matches("[a-zA-Z ]+")) // Reads: "Any of a-z or A-Z or space one or more times (together, not each)" ---> blank field or field containing anything other than those will return false.
		        {
		            b =  true;
		        }
		        else if(text.trim().length() == 0)
		        	b = true;
		return b;
	}

}

package com.hms.validators;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;

public class ComboValidator extends AbstractValidator{

	public ComboValidator(JDialog parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validationCriteria(JComponent c) {
		// TODO Auto-generated method stub
			boolean b = false;
		        String text = (String) ((JComboBox) c).getSelectedItem();
		        if (text.matches("[a-zA-Z ]+")) // Reads: "Any of a-z or A-Z or space one or more times (together, not each)" ---> blank field or field containing anything other than those will return false.
		        {
		            b =  true;
		        }
		        else if(text.trim().length() == 0)
		        	b = false;
		return b;
	}

}

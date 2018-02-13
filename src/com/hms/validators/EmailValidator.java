package com.hms.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EmailValidator extends AbstractValidator{

	public EmailValidator(JDialog parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validationCriteria(JComponent c) {
		// TODO Auto-generated method stub
		boolean b = false;
		String text = ((JTextField) c).getText();
		final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	
		Matcher matcher = pattern.matcher(text);
		if(matcher.matches())
		{
			b = true;
		}
		else if(text.trim().length() == 0)
			b = true;
		return b;
	}

}

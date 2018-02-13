package com.hms.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class MobileValidator extends AbstractValidator{

	public MobileValidator(JDialog parent, JComponent c, String message) {
		super(parent, c, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validationCriteria(JComponent c) {
		// TODO Auto-generated method stub
		boolean b = false;
		String text = ((JTextField) c).getText();
	      Pattern pattern = Pattern.compile("\\d{10}");	 
	      Matcher matcher = pattern.matcher(text);
	      if(matcher.matches())
	      {			    	  
	    	  b = true;
	      }
	      else if(text.trim().length()==0)
	    	  b = true;
		return b;
	}

}

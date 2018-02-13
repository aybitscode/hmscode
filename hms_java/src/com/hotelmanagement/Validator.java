package com.hotelmanagement;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.prefs.Preferences;


public class Validator {
    public static String getValidator(String input)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
        {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.reset();
            byte[] buffer = input.toLowerCase().getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();

            String hexStr = "";
            for (int i = 0; i < digest.length; i++) {
                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
            return hexStr;
        }
    public static boolean getStatus() throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
    	boolean b = false;
    	Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE);
    	String s1 = prefs.get("pdColor", "").toLowerCase();
    	String s2 = getValidator(s1);
    	String s3 = RFPanel.text_key.getText();
    	if(s2.equals(s3))
    	b = true;
    	
		return b;    	
    }
}

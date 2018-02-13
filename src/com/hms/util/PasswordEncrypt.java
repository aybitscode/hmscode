package com.hms.util;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class PasswordEncrypt 
{
    public static String encrypt(String password) 
    {
    	String encpass = null;
        try 
        {
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            encpass = new String(encrypted);


        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		return encpass;
    }
    public static String decrypt(String enctext)
    {
    	String decrypted = null;
    	try
    	{
    	byte[] encpass = enctext.getBytes();
        String key = "Bar12345Bar12345"; // 128 bit key
        // Create key and cipher
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        // decrypt the text
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        decrypted = new String(cipher.doFinal(encpass));
    	}
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		return decrypted;
    }
    public static boolean checkPassword(String password, String enctext)
    {
    	boolean b = false;
    	String decpass = decrypt(enctext);
    	if(password.equals(decpass))
    		b = true;
		return b;
    	
    }
//    public static void main(String arp[])
//    {
//    	PasswordEncrypt ps = new PasswordEncrypt();
//    	System.out.println(ps.encrypt("admin"));
//    	//¦ù9§Èç‚x“ùqÔäH-‰
//    	//¦ù9§Èç‚x“ùqÔäH-‰
//    }

}
package com.hms.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class GetFileCreationDateExample
{
	
    public static String getFileCD()
    {
    	String date = null;
    	String time = null;

    	try{

    		Process proc =
    		   Runtime.getRuntime().exec(Constants.FILE);

    		BufferedReader br =
    		   new BufferedReader(
    		      new InputStreamReader(proc.getInputStream()));

    		String data ="";

    		//it's quite stupid but work
    		for(int i=0; i<6; i++){
    			data = br.readLine();
    		}

    		//split by space
    		StringTokenizer st = new StringTokenizer(data);
    		date = st.nextToken();//Get date
    		time = st.nextToken();//Get time

    	}catch(IOException e){

    		e.printStackTrace();

    	}
		return date;

    }

}
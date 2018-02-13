package com.hotelmanagement;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class BackUp {
	static int BUFFER = 10485760;      

	public static String getData(String host, String port,    String user, String password, String db) throws Exception {

	Process run = Runtime.getRuntime().exec(
			"C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump --host="  + host + " --port=" + port + 
	           " --user=" + user + " --password=" + password +
	           " --compact --databases --add-drop-table --complete-insert --extended-insert " +
	           "--skip-comments --skip-triggers "+ db);

	InputStream in = run.getInputStream(); 
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	StringBuffer temp = new StringBuffer();
	
	int count;
	 char[] cbuf = new char[BUFFER];

	while ((count = br.read(cbuf, 0, BUFFER)) != -1)
	       	 temp.append(cbuf, 0, count);

	 br.close();
	 in.close();

	 return temp.toString();
	}
}
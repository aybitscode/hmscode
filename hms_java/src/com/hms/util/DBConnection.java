package com.hms.util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DBConnection {

    private static Connection connection = null;
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static Integer connectionCount = 0;
    private static int JDBC_QUERY_TIMEOUT = 300;
    
    
    public static final Connection getDBConnection(){
		try{
	    		if(null == connection){	
	    			String fileName = "hms_config.properties";
	    			Properties props = new Properties();
			        props.load(DBConnection.class.getClassLoader().getResourceAsStream(fileName));
		
			    	//connectSSH(props);
			    	connectToDataBase(props);
			    	
				
	    		}
		
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (Exception e){
				e.printStackTrace();
		}
    	
    	return connection;
    }


    public static Integer getJDBCQueryTimeOut()
    {
		return JDBC_QUERY_TIMEOUT;
    	
    }

   

    private static void connectToDataBase(Properties props) throws SQLException {
       
	
        try {

        	String databaseName 	= props.getProperty("db.sid");
        	String dbUserName 	= props.getProperty("db.user");
        	String dbPassword 	= props.getProperty("db.password");
        	int dbPort 		= Integer.parseInt(props.getProperty("db.port"));
            String dbServerName  	= props.getProperty("db.servername");
          
            if(connection == null){
            Class.forName(driverName).newInstance();

            //mysql database connectivity
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName(dbServerName);
            dataSource.setPortNumber(dbPort);
            dataSource.setUser(dbUserName);
            dataSource.setAllowMultiQueries(true);

            dataSource.setPassword(dbPassword);
            dataSource.setDatabaseName(databaseName);

            connection = dataSource.getConnection();
            }
            System.out.print("Connection to server successful!:" + connection + "\n\n");
            System.out.print("Connection["+connectionCount+"] established successfully");
            connectionCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeDBConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Closing Database Connection");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    


    
    

	
	



}

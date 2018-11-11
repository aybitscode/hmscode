package com.aybits.hms.arch.dbman;

import com.aybits.hms.arch.util.HmsConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class DBCPConnection
{
    static Logger log = Logger.getLogger(DBCPConnection.class);

    private static int JDBC_QUERY_TIMEOUT = 300;
    private static HikariConfig cfgObj = null;
    private static HikariDataSource ds = null;
    HmsConfig hmsConfig = new HmsConfig();

    public DBCPConnection() {
    	Properties hmsConfigProperties  = hmsConfig.getHmsConfigProps();
        log.info(" Initializing connection pool..");
        cfgObj = new HikariConfig(hmsConfigProperties);
        ds = new HikariDataSource(cfgObj);
    }

    public static final Connection getDBConnection() {
        Connection conn = null;
        try {
            if (ds != null) {
                conn = ds.getConnection();
            }
        } catch (Exception e) {
            log.info(" Exception occurred while getting connection ", e);
        }
        return conn;
    }

    public static void closeDBConnection(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            log.info(" Exception occurred while closing connection ", e);
        }
    }

    public static Integer getJDBCQueryTimeOut()
    {
        return JDBC_QUERY_TIMEOUT;

    }
}

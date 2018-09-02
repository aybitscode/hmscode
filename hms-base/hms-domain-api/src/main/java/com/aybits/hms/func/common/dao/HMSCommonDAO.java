package com.aybits.hms.func.common.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.util.Objects.requireNonNull;

public class HMSCommonDAO {

    static Logger Log = Logger.getLogger(HMSCommonDAO.class);


    public String getNextPrimaryKey(String primaryColumn,String table){

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String primaryKey = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            String query = CommonDBQueries.getNextPrimaryKeyQuery(primaryColumn,table);
            stmt = connection.prepareStatement(query);

            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();
            rs = requireNonNull(rs);

            if (rs.next() == false) {
                System.out.println("ResultSet is empty in Java");
                return null;
            } else {
                primaryKey = rs.getString("NEXT_ID");
            }


            stmt = requireNonNull(stmt);
            rs.close();
            stmt.close();


        } catch (Exception npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return primaryKey;
        }

    }


}

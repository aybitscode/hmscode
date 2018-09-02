package com.aybits.hms.func.common.dao;

import com.aybits.hms.arch.dbman.DBConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSException;
import com.aybits.hms.func.hotel.dao.HotelDBQueries;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Objects.requireNonNull;

public class HMSCommonDAO {

    static Logger Log = Logger.getLogger(HMSCommonDAO.class);

    private Connection connection = DBConnection.getDBConnection();

    public String getNextPrimaryKey(String primaryColumn,String table){

        PreparedStatement stmt;
        ResultSet rs;
        String primaryKey = null;

        try {
            connection = requireNonNull(connection);
            connection.setAutoCommit(false);
            String query = CommonDBQueries.getNextPrimaryKeyQuery(primaryColumn,table);
            stmt = connection.prepareStatement(query);

            stmt.setQueryTimeout(DBConnection.getJDBCQueryTimeOut());
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


        } catch (SQLException sqle) {
            // TODO Auto-generated catch block
            throw new HMSException(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured");
        } catch (NullPointerException npe) {
            throw new HMSException(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage());
        } finally {
            return primaryKey;
        }

    }


}

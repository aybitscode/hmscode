package com.aybits.hms.func.common.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.util.Objects.requireNonNull;

public class HMSCommonDAO {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public String getNextPrimaryKey(String primaryColumn,String table) {

        String primaryKey = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            String query = CommonDBQueries.getNextPrimaryKeyQuerySQL(primaryColumn, table);
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
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return primaryKey;
        }
    }
    
    public String getNextPrimaryKey(String[] columns,String table) {

        String primaryKey = null;

        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            String query = CommonDBQueries.getNextPrimaryKeyQuerySQL(columns, table);
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
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(rs, stmt, connection);
            return primaryKey;
        }
    }

    public String getNextPrimaryKey(String primaryKeyColumn1,String primaryKeyColumn2,String table){

            String primaryKey = null;
            try {
                connection = DBCPConnection.getDBConnection();
                connection.setAutoCommit(false);
                String query = CommonDBQueries.getNextPrimaryKeyQuerySQL(primaryKeyColumn1,primaryKeyColumn2,table);
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
                throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
            } finally {
                DBCPConnection.closeDBConnection(rs, stmt, connection);
                return primaryKey;
            }

    }


}

package com.aybits.hms.func.service.dao;

import com.aybits.hms.arch.dbman.DBCPConnection;
import com.aybits.hms.arch.exception.HMSErrorCodes;
import com.aybits.hms.arch.exception.HMSErrorInfo;
import com.aybits.hms.arch.exception.HMSRuntimeException;
import com.aybits.hms.arch.util.HMSUtilAPI;
import com.aybits.hms.func.service.beans.Service;
import com.aybits.hms.func.service.beans.ServiceType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ServiceSelectDAO {

    static Logger Log = Logger.getLogger(ServiceSelectDAO.class);
    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;


    protected List<Service> getServicesByAvailability(String hotelId, Boolean isServiceAvailable) throws HMSRuntimeException{

        List<Service> servicesList = new ArrayList<Service>();
        Service service = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.FETCH_SERVICE_BY_AVAILABILITY);

            Integer availability = HMSUtilAPI.getIntegerValueFromBoolean(isServiceAvailable);

            stmt.setString(1, hotelId);
            stmt.setInt(2,availability);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            service = populateService(rs);

            if (null != service) {
                Log.info("\nPopulating Service[" + service.getHotelId() + "," + service.getServiceId() + "] in Service Object");
                servicesList.add(service);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return servicesList;
        }
    }

    protected List<Service> getServicesByChargeability(String hotelId, Boolean isChargeable) throws HMSRuntimeException{

        List<Service> servicesList = new ArrayList<Service>();
        Service service = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.FETCH_SERVICE_BY_CHARGEABILITY);

            Integer chargeability = HMSUtilAPI.getIntegerValueFromBoolean(isChargeable);

            stmt.setString(1, hotelId);
            stmt.setInt(2,chargeability);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            service = populateService(rs);

            if (null != service) {
                Log.info("\nPopulating Service[" + service.getHotelId() + "," + service.getServiceId() + "] in Service Object");
                servicesList.add(service);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return servicesList;
        }
    }


    private List<Service> getAllServices(String hotelId) throws HMSRuntimeException{

        List<Service> servicesList = new ArrayList<Service>();
        Service service = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.FETCH_ALL_SERVICES);
            stmt.setString(1, hotelId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            service = populateService(rs);

            if (null != service) {
                Log.info("\nPopulating Service[" + service.getHotelId() + "," + service.getServiceId() + "] in Service Object");
                servicesList.add(service);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return servicesList;
        }

    }


    protected Service getService(String hotelId, String serviceId) throws HMSRuntimeException {
        Service service = null;
        try {
            connection = DBCPConnection.getDBConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(ServiceDBQueries.FETCH_SERVICE_BY_SERVICE_ID);
            stmt.setString(1, hotelId);
            stmt.setString(2, serviceId);
            stmt.setQueryTimeout(DBCPConnection.getJDBCQueryTimeOut());
            rs = stmt.executeQuery();

            service = populateService(rs);

            if (null != service)
                Log.info("\nPopulating Service[" + service.getHotelId()+","+service.getServiceId() + "] in Service Object");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.DB_SQL_EXCEPTION_OCCURED, "DB SQL Exception Occured"));
        } catch (NullPointerException npe) {
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION, "Object instantiated is null::" + npe.getMessage()));
        } finally {
            DBCPConnection.closeDBConnection(null, stmt, connection);
            return service;
        }

    }


    private Service populateService(ResultSet rs) throws SQLException {

        if (rs.next() == false) {
            System.out.println("ResultSet is empty in Java");
            return null;
        } else {
            String serviceId = rs.getString("SERVICE_ID");
            String hotelId = rs.getString("HOTEL_ID");
            String serviceName = rs.getString("SERVICE_NAME");
            String serviceDescription = rs.getString("SERVICE_DESCRIPTION");
            String isServiceAvailable = rs.getString("IS_AVAILABLE");
            String isServiceChargeable = rs.getString("IS_CHARGEABLE");

            Boolean isAvailable = HMSUtilAPI.getBooleanValueFromString(isServiceAvailable);
            Boolean isChargeable = HMSUtilAPI.getBooleanValueFromString(isServiceChargeable);

            Double serviceCharges = rs.getDouble("SERVICE_CHARGES");


            Date dateCreated = HMSUtilAPI.convertTimestampToDate(rs.getTimestamp("DATE_CREATED"));
            Date dateUpdated = HMSUtilAPI.convertTimestampToDate(rs.getTimestamp("DATE_UPDATED"));


            ServiceType serviceType = ServiceType.convertIntToServiceType(rs.getInt("SERVICE_TYPE"));

            return new Service(hotelId, serviceId, serviceName, serviceDescription,
                    isAvailable,isChargeable, serviceType, serviceCharges);
        }
    }


    protected HashMap<String,Service> getServicesMapByHotelId(String hotelId) throws HMSRuntimeException{

        HashMap<String,Service> servicesHashMap = new HashMap<>();
        try{
            List<Service> servicesList = getAllServices(hotelId);
            for(Service service:servicesList){
                String serviceId = service.getServiceId();
                servicesHashMap.put(serviceId,service);
            }

        }catch(HMSRuntimeException he){
            Log.error("Exception occurred while loading Services Map from DB");
            throw new HMSRuntimeException(HMSErrorInfo.getNewErrorInfo(HMSErrorCodes.HMS_EXCEPTION,"Exception occurred while loading Services Map from DB"));
        }
        return servicesHashMap;
    }
}

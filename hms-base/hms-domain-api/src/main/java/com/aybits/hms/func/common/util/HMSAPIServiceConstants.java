package com.aybits.hms.func.common.util;

public class HMSAPIServiceConstants {

    /******************* HMS MODULE TYPE ******************************
     * These constants provide info on all the modules present in the
     * HMS Solution
     *****************************************************************/
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String BOOKING = "BOOKING";
    public static final String CHECKIN= "CHECKIN";
    public static final String CHECKOUT = "CHECKOUT";
    public static final String GENERATE_INVOICE = "GENERATE_INVOICE";
    public static final String PRINT_INVOICE = "PRINT_INVOICE";

    /****************** HMS_SERVICE_ACTION *****************************
     * These constants provide info on all of the actions that are applicable on
     * above modules
     */
    public static final String INSERT = "INSERT";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";
    public static final String FETCH  = "FETCH";
    public static final String FETCH_ALL = "FETCH_ALL";
    public static final String DELETE_ALL = "DELETE_ALL";
    public static final String UPSERT = "UPSERT";

    /**************** HMS_CONSTANTS ************************************
     * These constants are commonly used in all of the functional modules
     * of HMS
     */
    public static final String HOTEL_FACILITY = "HOTEL_FACILITY";
    public static final String ROOM_FACILITY  = "ROOM_FACILITY";
    public static final String CUSTOMER_CACHE = "CUSTOMER_CACHE";
    public static final String HOTEL_CACHE = "HOTEL_CACHE";
    public static final String ROOM_CACHE = "ROOM_CACHE";
    public static final String ROOM_CATEGORY_CACHE = "ROOM_CATEGORY_CACHE";
    public static final String VOUCHER_CACHE = "VOUCHER_CACHE";
    public static final String EMPLOYEE_CACHE = "EMPLOYEE_CACHE";
    public static final String ID_PARAM_CACHE = "ID_PARAM_CACHE";
    public static final String FACILITY_CACHE = "FACILITY_CACHE";


    /*************** HMS_REST_CONSTANTS ************************************
     * These are the definitions of SUCCESS, FAILURE status codes
     */
    public static final String HMS_RESPONSE_SUCCESS = "success";
    public static final String HMS_RESPONSE_FAILURE = "failure";
    public static final String HMS_FAILURE_RESPONSE_DATA = "NOVAL";


}



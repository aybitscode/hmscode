package com.hms.cache;

import com.hms.dbutil.ExpenseDbOperations;
import com.hms.dbutil.HMSDbOperations;
import com.hms.exception.HMSException;

public class HMSCache {
	
	public static CustomerCache custCache = new CustomerCache();
	public static ExpensesCache expenseCache = new ExpensesCache();
	
	//public static BookingCache bookingCache = new BookingCache();
	//public static CouponCache couponCache = new CouponCache();
	
	
	public static void loadApplicationCache(){
		HMSDbOperations dbOps = new HMSDbOperations();
		Boolean isCustomerCacheLoaded = false;
		
		try {
			isCustomerCacheLoaded = dbOps.getAllCustomers(custCache);
			
			if(!isCustomerCacheLoaded){
				System.out.println("Customer Cache Loading Failed");
			}else{
				System.out.println("Customer Cache Loaded Successfully");
			}
			
			
		//	reloadExpensesCache();
			
			
		} catch (HMSException e) {
			// TODO print appropriate error message whenever any cache loading fails
			//log(e.getErrorCode()+"-"+e.getMessage());
			//LOG_FORMAT = <Server_ID>,<Timestamp>::<LOG_MESSAGE>
		}
		//dbOps.getAllBookings(bookingCache);
		
	}
	
	public static void reloadCustomerCache(){
		
	}
	
	public static void reloadBookingCache(){
		
	}
	
	public static void reloadExpensesCache()
	{

		ExpenseDbOperations dbOps = new ExpenseDbOperations();
		Boolean isExpensesCacheLoaded = false;
		
		try {
			isExpensesCacheLoaded = dbOps.getAllExpenses(expenseCache);
			
			if(!isExpensesCacheLoaded){
				System.out.println("Expenses Cache Loading Failed");
			}else{
				System.out.println("Expenses Cache Loaded Successfully");
			}
			
			
			
			
			
		} catch (HMSException e) {
			// TODO print appropriate error message whenever any cache loading fails
			//log(e.getErrorCode()+"-"+e.getMessage());
			//LOG_FORMAT = <Server_ID>,<Timestamp>::<LOG_MESSAGE>
		}
		//dbOps.getAllBookings(bookingCache);
	}
	
}

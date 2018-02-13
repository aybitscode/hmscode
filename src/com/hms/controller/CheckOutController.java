package com.hms.controller;

import java.util.List;

import com.hms.model.CheckOut;
import com.hms.model.ReportDetails;
import com.hms.services.CheckOutService;
import com.hotelmanagement.MainPage;
import com.reports.Report;

public class CheckOutController {
	public CheckOut obj_CheckOut;
	public ReportDetails obj_rpt;
	CheckOutService checkOut_service;
	
	public CheckOutController(CheckOut obj_CheckOut)
	{
		this.obj_CheckOut = obj_CheckOut;
		checkOut_service = new CheckOutService(obj_CheckOut);
	}
	public CheckOutController(ReportDetails obj_rpt)
	{
		this.obj_rpt = obj_rpt;
		checkOut_service = new CheckOutService(obj_rpt);
	}
	public double getCouponDiscount(String couponName)
	{
		return checkOut_service.getCouponDiscount(couponName);
	}
	public int submitService()
	{
		return checkOut_service.submitService();		
	}

	public List<String> retrieveCustomerDetails()
	{
		return checkOut_service.retrieveCustomer();
	}
	public void retrieveReportDetailss()
	{
		  checkOut_service.retrieveCheckOutDetails();
		  
	}
	public void generateReport(MainPage mpg)
	{
		Report rpt = new Report(mpg);
		rpt.callReport(obj_rpt.getBookingID(), retrieveCustomerDetails());
	}
}

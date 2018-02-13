package com.hms.controller;

import com.hms.model.ReportDetails;
import com.hms.services.ReportDetailService;
import com.hms.view.BookingDetails;
import com.reports.Report;

public class ReportDetailsController {
	public ReportDetails obj_rpt;
	ReportDetailService obj_service;
	

	public ReportDetailsController(ReportDetails obj_rpt)
	{
		this.obj_rpt = obj_rpt;
		obj_service = new ReportDetailService(obj_rpt);
	}

	public void submitService()
	{
		obj_service.submitService();
		//generateReport();
		
	}
	
	public void generateReport(BookingDetails bds)
	{
		Report rpt = new Report(bds);
		rpt.callReport(obj_rpt.getBookingID(), obj_rpt);
	}

}

package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Coupon;
import com.hms.model.Season;
import com.hms.services.SeasonService;

public class SeasonController {
	public Season objSeason;
	SeasonService seasonService;

	public SeasonController()
	{
		seasonService = new SeasonService();
	}
	public SeasonController(Season obj_season)
	{
		this.objSeason = obj_season;
		seasonService = new SeasonService(obj_season);
	}
	public SeasonController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
		seasonService = new SeasonService(tableModel, table);
	}
	public int submitService(String query)
	{
		return seasonService.submitService(query);
	}
	public String generateID() {
		// TODO Auto-generated method stub
		return seasonService.generateID();
	}
	public void retrieveAll(String query)
	{
		seasonService.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		seasonService.retrieve(query, param);
	}
	public void updateService(String query1, String query2, String param)
	{
		seasonService.updateService(query1, query2, param);
	}
	public String seasonCouponDiscount(String couponName)
	{
		return seasonService.seasonCouponDiscount(couponName);
	}
	
	public Season populateForm(String param)
	{
		return seasonService.populateForm(param);
	}
	public int updateForm(Season objSeason )
	{
		return seasonService.updateForm(objSeason);
	}
}
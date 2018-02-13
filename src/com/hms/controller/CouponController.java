package com.hms.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Coupon;
import com.hms.services.CouponService;

public class CouponController {
	public Coupon obj_coupon;
	CouponService couponService;
	
	public CouponController()
	{
		couponService = new CouponService();
	}
	public CouponController(Coupon obj_coupon)
	{
		this.obj_coupon = obj_coupon;
		couponService = new CouponService(obj_coupon);
	}
	public CouponController(DefaultTableModel tableModel, JTable table) {
		// TODO Auto-generated constructor stub
			couponService = new CouponService(tableModel, table);
	}
	public int submitCoupon(String query)
	{
		return couponService.submitService(query);
	}
	public String generateID() {
		// TODO Auto-generated method stub
		return couponService.generateCouponId();
	}
	public void retrieveAll(String query)
	{
		couponService.retrieveAll(query);
	}
	public void retrieve(String query, String param)
	{
		couponService.retrieve(query, param);
	}
	public Coupon populateForm(String param)
	{
		return couponService.poulateForm(param);
	}
	public int updateForm(Coupon objCoupon )
	{
		return couponService.updateForm(objCoupon);
	}
	public void updateService(String query1, String query2, String param)
	{
		couponService.updateService(query1, query2, param);
	}
}
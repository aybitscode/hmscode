package com.hms.controller;

import java.util.List;

import com.hms.model.Welcome;
import com.hms.services.WelcomeService;

public class WelcomeController {
	WelcomeService obj_service;
		
	public WelcomeController()
	{
		obj_service = new WelcomeService();
	}
	public List<Welcome> retriveCheckinCheckout(String query, String query1)
	{
		return obj_service.currentCheckins(query, query1);
	}

}

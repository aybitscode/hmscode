//	@Override
//	public void focusGained(FocusEvent arg0) {
//		// TODO Auto-generated method stub
//		if(arg0.getSource()==lblHotelDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new HotelEntry(mpg);
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI();
//		}
//		else if(arg0.getSource()==lblCouponDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new CouponEntry(mpg);
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			lblHotelDetails.setForeground(Color.black);
//			lblCouponDetails.setForeground(new Color(50, 197, 210));
//			
//			updateUI();
//			
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new SeasonEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new SeasonEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new SeasonEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new SeasonEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblEmployeeDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new EmployeeEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblTaxDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new GSTEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomCapacityDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomCapacityEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomCategoryDetails)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomCategoryEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomNumbers)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomFacilities)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomFacilitiesEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomPrice)
//		{
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomPriceEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			updateUI(); 
//		}
//		
//	}
//
//	@Override
//	public void focusLost(FocusEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		if(arg0.getSource()==lblHotelDetails)
//		{
//			lblHotelDetails.setForeground(new Color(50, 197, 210));
//			lblHotelDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblCouponDetails)
//		{	
//			lblCouponDetails.setForeground(new Color(50, 197, 210));	
//			lblCouponDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{	
//			lblSeasonDetails.setForeground(new Color(50, 197, 210));
//			lblSeasonDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblEmployeeDetails)
//		{
//			lblEmployeeDetails.setForeground(new Color(50, 197, 210));
//			lblEmployeeDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblTaxDetails)
//		{
//			lblTaxDetails.setForeground(new Color(50, 197, 210));
//			lblTaxDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblRoomCapacityDetails)
//		{
//			lblRoomCapacityDetails.setForeground(new Color(50, 197, 210));
//			lblRoomCapacityDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblRoomCategoryDetails)
//		{
//			lblRoomCategoryDetails.setForeground(new Color(50, 197, 210));
//			lblRoomCategoryDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblRoomNumbers)
//		{
//			lblRoomNumbers.setForeground(new Color(50, 197, 210));
//			lblRoomNumbers.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblRoomFacilities)
//		{
//			lblRoomFacilities.setForeground(new Color(50, 197, 210));
//			lblRoomFacilities.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else if(arg0.getSource()==lblRoomPrice)
//		{
//			lblRoomPrice.setForeground(new Color(50, 197, 210));
//			lblRoomPrice.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		}
//		else
//		{
//			
//		}
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		if(arg0.getSource()==lblHotelDetails)
//		{
//			if(!active.equals(ViewConstants.HOTEL_DETAILS))
//			lblHotelDetails.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblCouponDetails)
//		{	
//			if(!active.equals(ViewConstants.COUPON_DETAILS))
//			lblCouponDetails.setForeground(Color.black);			
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{	
//			if(!active.equals(ViewConstants.SEASON_DETAILS))
//			lblSeasonDetails.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblEmployeeDetails)
//		{
//			if(!active.equals(ViewConstants.EMPLOYEE_DETAILS))
//			lblEmployeeDetails.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblTaxDetails)
//		{
//			if(!active.equals(ViewConstants.TAX_DETAILS))
//			lblTaxDetails.setForeground(Color.black); 
//		}
//		else if(arg0.getSource()==lblRoomCapacityDetails)
//		{
//			if(!active.equals(ViewConstants.ROOM_CAPACITY))
//			lblRoomCapacityDetails.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblRoomCategoryDetails)
//		{
//			if(!active.equals(ViewConstants.ROOM_CATEGORY))
//			lblRoomCategoryDetails.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblRoomNumbers)
//		{
//			if(!active.equals(ViewConstants.ROOM_NUMBERS))
//			lblRoomNumbers.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblRoomFacilities)
//		{
//			if(!active.equals(ViewConstants.ROOM_FACILITIES))
//			lblRoomFacilities.setForeground(Color.black);
//		}
//		else if(arg0.getSource()==lblRoomPrice)
//		{
//			if(!active.equals(ViewConstants.ROOM_PRICE))
//			lblRoomPrice.setForeground(Color.black);
//		}
//		else
//		{
//			
//		}
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//	 
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//		if(arg0.getSource()==lblHotelDetails)
//		{
//			active = ViewConstants.HOTEL_DETAILS;
//			masterContainer.remove(containerPanel);
//			containerPanel = new HotelEntry(mpg);
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblHotelDetails.setForeground(new Color(50, 197, 210));
//			
//			updateUI();
//		}
//		else if(arg0.getSource()==lblCouponDetails)
//		{
//			active = ViewConstants.SEASON_DETAILS;
//			masterContainer.remove(containerPanel);
//			containerPanel = new CouponEntry(mpg);
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblCouponDetails.setForeground(new Color(50, 197, 210));
//
//			updateUI();
//			
//		}
//		else if(arg0.getSource()==lblSeasonDetails)
//		{
//			active = ViewConstants.SEASON_DETAILS;
//			masterContainer.remove(containerPanel);
//			containerPanel = new SeasonEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblSeasonDetails.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblEmployeeDetails)
//		{
//			active = ViewConstants.EMPLOYEE_DETAILS;
//			masterContainer.remove(containerPanel);
//			containerPanel = new EmployeeEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblEmployeeDetails.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblTaxDetails)
//		{
//			active = ViewConstants.TAX_DETAILS;
//			masterContainer.remove(containerPanel);
//			containerPanel = new GSTEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblTaxDetails.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomCapacityDetails)
//		{
//			active = ViewConstants.ROOM_CAPACITY;
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomCapacityEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblRoomCapacityDetails.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomCategoryDetails)
//		{
//			active = ViewConstants.ROOM_CATEGORY;
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomCategoryEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblRoomCategoryDetails.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomNumbers)
//		{
//			active = ViewConstants.ROOM_NUMBERS;
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblRoomNumbers.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomFacilities)
//		{
//			active = ViewConstants.ROOM_FACILITIES;
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomFacilitiesEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblRoomFacilities.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else if(arg0.getSource()==lblRoomPrice)
//		{
//			active = ViewConstants.ROOM_PRICE;
//			masterContainer.remove(containerPanel);
//			containerPanel = new RoomPriceEntry();
//			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
//			resetForeground(Color.black);
//			lblRoomPrice.setForeground(new Color(50, 197, 210));
//
//			updateUI(); 
//		}
//		else
//		{
//			
//		}
//	}
//
//}

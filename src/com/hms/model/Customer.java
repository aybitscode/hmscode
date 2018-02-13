package com.hms.model;

public class Customer implements Comparable<Customer>{
	private String customerID;
	private String first_name;
	private String last_name;
	private String gender;
	private String city;
	private String phone_number;
	private String email;
	private String points;
	private String address;
	
	
	public Customer()
	{
		
	}
	
	public Customer(String customerId,String firstName,String lastName,String gender,String city,
			String email,String phoneNumber,String address){
		this.customerID = customerId;
		this.first_name = firstName;
		this.last_name = lastName;
		this.gender = gender;
		this.city = city;
		this.phone_number = phoneNumber;
		this.email = email;
		this.address = address;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int compareTo(Customer other) {
		// TODO Auto-generated method stub
		 int i = this.first_name.compareTo(other.first_name);
		    if (i != 0) return i;

		    i = this.last_name.compareTo(other.last_name);
		    if (i != 0) return i;

		    i = this.address.compareTo(other.address);
		    if (i != 0) return i;
		    
		    i = this.city.compareTo(other.city);
		    if (i != 0) return i;
		    
		    i = this.email.compareTo(other.email);
		    if (i != 0) return i;
		    
		    i = this.phone_number.compareTo(other.phone_number);
		    if (i != 0) return i;
		    
		return 0;
	}
	
	@Override
	 public String toString() {
	   return "Customer[ID:"+customerID+",Name:" + first_name +" "+last_name+ "]";

	 }

	

}

package com.hms.model;

public class GST {
	private String gstID;
	private String gstPercentge;
	private String cgstPercentage;
	private String sgstPercentage;
	private String gstValue;

	private String lowerBound;
	private String upperBound;
	private String category;
	private String status;
	
	
	public String getGstPercentge() {
		return gstPercentge;
	}
	public void setGstPercentge(String gstPercentge) {
		this.gstPercentge = gstPercentge;
	}
	public String getGstValue() {
		return gstValue;
	}
	public void setGstValue(String gstValue) {
		this.gstValue = gstValue;
	}

	public String getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}
	public String getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}
	public String getGstID() {
		return gstID;
	}
	public void setGstID(String gstID) {
		this.gstID = gstID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCgstPercentage() {
		return cgstPercentage;
	}
	public void setCgstPercentage(String cgstPercentage) {
		this.cgstPercentage = cgstPercentage;
	}
	public String getSgstPercentage() {
		return sgstPercentage;
	}
	public void setSgstPercentage(String sgstPercentage) {
		this.sgstPercentage = sgstPercentage;
	}

}

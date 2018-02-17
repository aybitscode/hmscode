package com.hms.model;

public class Season {
	private String slno;
	private String seasonId;
	private String seasonName;
	private java.sql.Date dateStart;
	private java.sql.Date dateEnd;
	private String couponId;
	private String couponName;
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}
	public String getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public java.sql.Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(java.sql.Date dateStart) {
		this.dateStart = dateStart;
	}
	public java.sql.Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(java.sql.Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
 
 

}

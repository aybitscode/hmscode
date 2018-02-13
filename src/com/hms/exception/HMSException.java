package com.hms.exception;

public class HMSException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode = null;
	private String[] args = null;
	

	public HMSException(String errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	public HMSException(String message, Throwable cause)
	{
		super(message, cause);
		if(cause instanceof HMSException)
		{
			this.errorCode = ((HMSException) cause).getErrorCode();
		}
	}
	
	public HMSException(String errorCode, String args[], String message)
	{
		super(message);
		this.setErrorCode(errorCode);
		this.args = args;
	}
	public HMSException(String errorCode)
	{
		this.setErrorCode(errorCode);
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage()
	{
		return this.getMessage();
	}
	
			

}

package com.hms.jtable;

import javax.swing.JButton;

public class Employee
{
    private int id;
    private String name;
    private double hourlyRate;
    private boolean partTime;
    private JButton button;
    
 
    public Employee(int id, String name, double hourlyRate, boolean partTime, JButton button)
    {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.partTime = partTime;
        this.button = button;
    }
 
    public int getId()
    {
        return id;
    }
 
    public void setId(int id)
    {
        this.id = id;
    }
 
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }
 
    public double getHourlyRate()
    {
        return hourlyRate;
    }
 
    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }
 
    public boolean isPartTime()
    {
        return partTime;
    }
 
    public void setPartTime(boolean partTime)
    {
        this.partTime = partTime;
    }

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
 
}
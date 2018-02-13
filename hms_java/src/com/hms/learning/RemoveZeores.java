package com.hms.learning;

import java.text.DecimalFormat;

public class RemoveZeores {
	public static void main(String ar[])
	{
		double answer = 5.00000;
		DecimalFormat df = new DecimalFormat("###");
		System.out.println(df.format(answer));
	}
}

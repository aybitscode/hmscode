package com.hms.learning;

public class GetRGB {
	public int getIntFromColor(float Red, float Green, float Blue){
	    int R = Math.round(255 * Red);
	    int G = Math.round(255 * Green);
	    int B = Math.round(255 * Blue);

	    R = (R << 16) & 0x00FF0000;
	    G = (G << 8) & 0x0000FF00;
	    B = B & 0x000000FF;

	    return 0xFF000000 | R | G | B;
	}
	
	public static void main(String ar[])
	{
		GetRGB s = new GetRGB();
		System.out.println(s.getIntFromColor(23, 196, 187));
	}
}

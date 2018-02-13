package com.hms.util;

import java.math.BigDecimal;

public class BigDecimalType {
	public static BigDecimal roundDown(double value)
	{
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN);
	}
	public static BigDecimal round(double value)
	{
		return new BigDecimal(value).setScale(1, BigDecimal.ROUND_DOWN);
	}
}

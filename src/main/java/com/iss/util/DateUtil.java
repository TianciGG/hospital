package com.iss.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String dateToStringHHmm(Date myDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(myDate);
	}
}

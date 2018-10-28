package com.anyun100.legal.lawcase_robot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	public static LocalDateTime parseLocalDateTime(String dateTime) {
		try {
			return LocalDateTime.parse(dateTime, formatter);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

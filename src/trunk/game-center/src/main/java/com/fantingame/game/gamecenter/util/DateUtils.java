package com.fantingame.game.gamecenter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具集合
 * 
 * @author CJ
 * 
 */
public class DateUtils {

	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return format.format(date);
	}
	
	public static String getDateForLog() {
		return getDateForLog(new Date());
	}
	
	public static String getDateForLog(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	public static String getDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getDateStr(final long millis, String fmt) {
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		Date date = new Date();
		if (millis > 0) {
			date.setTime(millis);
		}

		return format.format(date);
	}

	public static Date getDateAtMidnight() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getDateAtMidnight(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取两天相差天数
	 * 
	 * @param dt1
	 * @param dt2
	 * @return
	 */
	public static int getDayDiff(Date dt1, Date dt2) {
		long t1 = dt1.getTime();
		long t2 = dt2.getTime();
		double diff = t2 - t1;
		diff = diff / (24 * 60 * 60 * 1000);
		return (int) diff;
	}

	public static int getYear() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.YEAR);
	}

	public static int getHour() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	public static int getDay() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MONTH);
	}

	public static int getMinute() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MINUTE);
	}

	public static int getDayOfWeek() {
		Calendar cld = Calendar.getInstance();
		cld.add(Calendar.DATE, -1);
		return cld.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfWeek(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.add(Calendar.DATE, -1);
		return cld.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekOfYear() {
		Calendar cld = Calendar.getInstance();
		cld.setFirstDayOfWeek(Calendar.MONDAY);
		return cld.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getTime() {
		long time = System.currentTimeMillis();
		return getTime(time);
	}

	public static String getTime(final long millis) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		if (millis > 0) {
			date.setTime(millis);
		}
		return format.format(date);
	}

	public static String getTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date str2Date(String s) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getYesterday(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.DATE, -1);
		return cl.getTime();
	}
	
	public static void main(String[] args) {

		Calendar cld = Calendar.getInstance();
		cld.setFirstDayOfWeek(Calendar.MONDAY);
		cld.setTime(DateUtils.str2Date("2013-10-13 12:00:00"));
		// cld.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY);
		int week = cld.get(Calendar.DAY_OF_WEEK);
		System.out.println(week);
	}

	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 获取多少秒之后的时间
	 * 
	 * @param createdTime
	 * @param second
	 */
	public static Date getAfterTime(Date date, int second) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.SECOND, second);
		return cl.getTime();
	}
}

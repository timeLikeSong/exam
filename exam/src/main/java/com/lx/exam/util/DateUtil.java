package com.lx.exam.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 日期相加
	 * @param date 日期
	 * @param day 天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 日期加分钟
	 * @param date 日期
	 * @param minutes 分钟数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addMinute(java.util.Date date, int minutes) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) minutes) * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 日期相减
	 * @param date 日期
	 * @param date1 日期
	 * @return 返回相减后的天数
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 日期相减
	 * @param date 日期
	 * @param date1 日期
	 * @return 返回相减后的分钟数
	 */
	public static int diffMinutes(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (60 * 1000));
	}

	public static String format(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化输出日期
	 * @param date 日期
	 * @param format 格式
	 * @return 返回字符型日期
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static int getCurDate() {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
		String date = format.format(new java.util.Date());
		return Integer.parseInt(date);
	}

	public static int getCurTime() {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("HHmmss");
		String date = format.format(new java.util.Date());
		return Integer.parseInt(date);
	}

	public static int getCurYear() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(new java.util.Date());
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 返回字符型日期
	 * @param date 日期
	 * @return 返回字符型日期
	 */
	public static String getDate(java.util.Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 返回字符型日期时间
	 * @param date 日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTime(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 返回日份
	 * @param date 日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回本周的第一天
	 * @param date 日期
	 * @return 返回星期
	 */
	public static Date getFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return cal.getTime();
	}

	/**
	 * 返回小时
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回毫秒
	 * @param date 日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回分钟
	 * @param date 日期
	 * @return 返回分钟
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 返回月份
	 * @param date 日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 返回秒钟
	 * @param date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 返回字符型时间
	 * @param date 日期
	 * @return 返回字符型时间
	 */
	public static String getTime(java.util.Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 返回星期
	 * @param date 日期
	 * @return 返回星期
	 */
	public static int getWeek(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_WEEK);
	}

	/**
	 * 返回年份
	 * @param date 日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	public static java.util.Date parseDate(java.sql.Date date) {
		return date;
	}

	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr.replaceAll("/", "-"), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期
	 * @param dateStr 字符型日期
	 * @param format 格式
	 * @return 返回日期
	 */
	public static java.util.Date parseDate(String dateStr, String format) {
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			date = (java.util.Date) df.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}

	public static java.sql.Date parseSqlDate(java.util.Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	public static java.sql.Date parseSqlDate(String dateStr) {
		return parseSqlDate(dateStr, "yyyy-MM-dd");
	}

	public static java.sql.Date parseSqlDate(String dateStr, String format) {
		java.util.Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}

	public static java.sql.Timestamp parseTimestamp(String dateStr) {
		return parseTimestamp(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static java.sql.Timestamp parseTimestamp(String dateStr, String format) {
		java.util.Date date = parseDate(dateStr, format);
		if (date != null) {
			long t = date.getTime();
			return new java.sql.Timestamp(t);
		} else
			return null;
	}

}

package com.miao.gp.mytool.utils;

import org.apache.http.client.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	

	public static String getRemainderTime(Date currentTime, Date firstTime) {
		long diff = currentTime.getTime() - firstTime.getTime();// 这样得到的差值是微秒级别

		Calendar currentTimes = dataToCalendar(currentTime);// 当前系统时间转Calendar类型
		Calendar firstTimes = dataToCalendar(firstTime);// 查询的数据时间转Calendar类型
		int year = currentTimes.get(Calendar.YEAR) - firstTimes.get(Calendar.YEAR);// 获取年
		int month = currentTimes.get(Calendar.MONTH) - firstTimes.get(Calendar.MONTH);
		int day = currentTimes.get(Calendar.DAY_OF_MONTH) - firstTimes.get(Calendar.DAY_OF_MONTH);
		if (day < 0) {
			month -= 1;
			currentTimes.add(Calendar.MONTH, -1);
			day = day + currentTimes.getActualMaximum(Calendar.DAY_OF_MONTH);// 获取日
		}
		if (month < 0) {
			month = (month + 12) % 12;// 获取月
			year--;
		}
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); // 获取时
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);// 获取分钟
		long s = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);// 获取秒
		if (days > 0) {
			return days + "天";
		} else {
			return hours + "小时" + minutes + "分" + s + "秒";
		}

	}

	public static String getTime(Date currentTime, Date firstTime) {

		long diff = currentTime.getTime() - firstTime.getTime();// 这样得到的差值是微秒级别

		Calendar currentTimes = dataToCalendar(currentTime);// 当前系统时间转Calendar类型
		Calendar firstTimes = dataToCalendar(firstTime);// 查询的数据时间转Calendar类型
		int year = currentTimes.get(Calendar.YEAR) - firstTimes.get(Calendar.YEAR);// 获取年
		int month = currentTimes.get(Calendar.MONTH) - firstTimes.get(Calendar.MONTH);
		int day = currentTimes.get(Calendar.DAY_OF_MONTH) - firstTimes.get(Calendar.DAY_OF_MONTH);
		if (day < 0) {
			month -= 1;
			currentTimes.add(Calendar.MONTH, -1);
			day = day + currentTimes.getActualMaximum(Calendar.DAY_OF_MONTH);// 获取日
		}
		if (month < 0) {
			month = (month + 12) % 12;// 获取月
			year--;
		}
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); // 获取时
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);// 获取分钟
		long s = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);// 获取秒
		String CountTime = year + "年" + month + "月" + day + "天" + hours + "小时" + minutes + "分" + s + "秒";
		return CountTime;
	} // Date类型转Calendar类型

	public static Calendar dataToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static int getYear(Date currentTime, Date firstTime) {
		Calendar currentTimes = dataToCalendar(currentTime);// 当前系统时间转Calendar类型
		Calendar firstTimes = dataToCalendar(firstTime);// 查询的数据时间转Calendar类型

		int year1 = currentTimes.get(Calendar.YEAR);
		int year2 = firstTimes.get(Calendar.YEAR);
		int month1 = currentTimes.get(Calendar.MONTH);
		int month2 = firstTimes.get(Calendar.MONTH);
		int day1 = currentTimes.get(Calendar.DAY_OF_MONTH);
		int day2 = firstTimes.get(Calendar.DAY_OF_MONTH);

		int year = year1 - year2;// 获取年\

		if (month1 < month2 || (month1 == month2 && day1 < day2)) {
			year--;
		}

		return year;
	}

	public static int getYearDate(Date currentTime) {
		Calendar currentTimes = dataToCalendar(currentTime);// 当前系统时间转Calendar类型
		int year = currentTimes.get(Calendar.YEAR);
		return year;
	}

	public static int getMonthDate(Date currentTime) {
		Calendar currentTimes = dataToCalendar(currentTime);// 当前系统时间转Calendar类型
		int month = currentTimes.get(Calendar.MONTH) + 1;
		return month;
	}

	public static int getDayDate(Date currentTime) {
		Calendar currentTimes = dataToCalendar(currentTime);// 当前系统时间转Calendar类型
		int day = currentTimes.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 比较两个日期的年月日是否相同
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean sameDate(Date d1, Date d2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		// fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
		return fmt.format(d1).equals(fmt.format(d2));

	}
	/**
	 * 加上指定的年份
	 * 
	 * @param payDate
	 * @param n
	 * @return
	 */
	public static String addYear(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.YEAR, n);// 增加n年
		Date time = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(time);
		return dateString;
	}

	/**
	 * 加上指定的年份
	 * 
	 * @param payDate
	 * @param n
	 * @return
	 */
	public static Long addDateYear(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.YEAR, n);// 增加n年
		Date time = cal.getTime();
		return time.getTime();
	}

	public static String addYearString(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.YEAR, n);// 增加n年
		Date time = cal.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return year + "年" + month + "月" + day + "日";
	}

	public static String switchString(Date payDate) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		Date time = cal.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 加上指定的年份
	 * 
	 * @param payDate
	 * @param n
	 * @return
	 */
	public static Date addYearDate(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.YEAR, n);// 增加n年
		Date time = cal.getTime();
		return time;
	}

	/**
	 * 加上指定的月份
	 * 
	 * @param payDate
	 * @param n
	 * @return
	 */
	public static String addMonth(Date payDate, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(payDate);

		calendar.add(Calendar.MONTH, n);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(calendar.getTime().getTime());
		String dateString = formatter.format(calendar.getTime());
		return dateString;
	}


	/**
	 * 获取两个日期相差的月数
	 */
	public static int getMonthDiff(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值
		int yearInterval = year1 - year2;
		int monthInterval = 0;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if (month1 < month2 || month1 == month2 && day1 < day2) {
			yearInterval--;
			monthInterval = (month1 + 12) - month2;
		} else {
			monthInterval = month1 - month2;
		}
		// 获取月数差值
		// 日数差一天认为满一个月
		if (day1 < day2 + 1)
			monthInterval--;
		int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
		return monthsDiff;
	}

	// num为需要增加的天数
	public static long addDate(int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num);
		Date date = calendar.getTime();
		long time = date.getTime();
		return time;
	}

	// num为需要增加的天数
	public static String addDayDateString(int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return year + "年" + month + "月" + day + "日";
	}




	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(long date1, long date2) {
		int days = (int) ((date2 - date1) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 通过date判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByDate(Date dateN, Date dateE) {
		long date2 = dateE.getTime();
		long date1 = dateN.getTime();
		int days = (int) ((date2 - date1) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * @Description: long类型转换成日期
	 *
	 * @param lo
	 *            毫秒数
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String longToDate(long lo) {
		Date date = new Date(lo);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(date);
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDays(Date date1, Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		int days = (int) ((time1 - time2) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 获取两个日期相差的秒
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getSecond(Date date1, Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		int second = (int) ((time1 - time2) / (1000));
		return second;
	}

	/**
	 * 获取指定日期的一天小时集合yyyyMMddHH
	 **/
	public static List<String> getOneDayHourList(Date date) {
		List<String> hourList = new ArrayList<String>();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String dateString = fmt.format(date);
		for (int i = 0; i < 24; i++) {
			String hour = String.valueOf(i);
			if (i < 10) {
				hour = "0" + hour;
			}
			hourList.add(dateString + hour);
		}
		// hourList.add("2019120815");
		return hourList;
	}

	/**
	 * 获取指定日期的前N天日期
	 **/
	public static Date getBeforeDayDate(Date date, int beforeDay) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.add(Calendar.DATE, -beforeDay);
		return a.getTime();
	}

	/**
	 * 添加指定的小时
	 * 
	 * @param payDate
	 * @param n
	 * @return
	 */
	public static Date addHour(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.HOUR, n);// 增加n个小时
		Date time = cal.getTime();
		return time;
	}

	public static Date addDays(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.DATE, n);// 增加n天
		Date time = cal.getTime();
		return time;
	}

	public static Date addSecond(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.SECOND, n);// 增加多少秒
		Date time = cal.getTime();
		return time;
	}

	public static Date addMinute(Date payDate, int n) {
		Calendar cal = dataToCalendar(payDate);// 当前系统时间转Calendar类型
		cal.add(Calendar.MINUTE, n);// 增加多少秒
		Date time = cal.getTime();
		return time;
	}

}

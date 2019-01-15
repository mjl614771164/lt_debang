package com.leting.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTool {

	public static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat datetimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String [] dateArray = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16",
		"17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	
	/** 
	 * 将时间转换成 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String dateformat(Date date){
		return dateformat.format(date);
	}
	
	/**
	 * 将时间转换成 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String datetimeformat(Date date){
		return datetimeformat.format(date);
	}
	

	
	/** 
	 * 将时间转换成 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String dateformat(Timestamp date){
		return dateformat.format(date);
	}
	
	/**
	 * 将时间转换成 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String datetimeformat(Timestamp date){
		return datetimeformat.format(date);
	}
	
	 /**
	     * 获取当月的 天数
	    */
	   public static int getCurrentMonthDay() {
	
		   Calendar a = Calendar.getInstance();
		   a.set(Calendar.DATE, 1);
		   a.roll(Calendar.DATE, -1);
		   int maxDate = a.get(Calendar.DATE);
		   return maxDate;
    }
	 
	   /**
      * 根据 年、月 获取对应的月份 的 天数
	     */
	   public static int getDaysByYearMonth(int year, int month) {
		   Calendar a = Calendar.getInstance();
	       a.set(Calendar.YEAR, year);
	       a.set(Calendar.MONTH, month - 1);
	       a.set(Calendar.DATE, 1);
	       a.roll(Calendar.DATE, -1);
	       int maxDate = a.get(Calendar.DATE);
	       return maxDate;
	   }
	
	/**
	* 根据日期 找到对应日期的 星期几
	*/
	public static String getDayOfWeekByDate(String date) {
		String dayOfweek = "-1";
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = myFormatter.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("E");
			String str = formatter.format(myDate);
        	dayOfweek = str;
		
		} catch (Exception e) {
	        System.out.println("错误!");
        }
		return dayOfweek;
	}
	
	
	public static String[] getDateArray(int datenum){
		
		String [] dates = null ;
		dates = Arrays.copyOfRange(dateArray, 0, datenum);
		return dates;
	}
	
	/**
	 * 获取日期区间中的所有日期
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	public static List<String> getDayStrList(Date startdate,Date enddate){
		
		List<String> dayStrList = new ArrayList<String>();
		//获取期间的所有日期列表
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(startdate.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		while(cal.getTimeInMillis()<=(enddate.getTime())){//此时日期可用
			dayStrList.add(dateformat.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		return dayStrList;
	}
	
	/**
	 * 获取日期区间中的所有日期
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	public static List<String> getDayStrList(String start,String end){
		
		Date startdate;
		Date enddate;
		try {
			startdate = dateformat.parse(start);
			enddate = dateformat.parse(end);
		} catch (ParseException e) {
			startdate = new Date();
			enddate = new Date();
		}
		
		List<String> dayStrList = new ArrayList<String>();
		//获取期间的所有日期列表
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(startdate.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		while(cal.getTimeInMillis()<=(enddate.getTime())){//此时日期可用
			dayStrList.add(dateformat.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		return dayStrList;
	}
	
}

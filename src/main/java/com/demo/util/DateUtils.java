package com.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;


public class DateUtils {
	/**
	 * 页面的日期控件日期字符串转为Date
	 * 
	 * @param str
	 *            控件中的值
	 * @return
	 */
	public static Date stringToDate(String str) throws ParseException {
		Date return_date = null;
			str = str.trim();
			if (str != null && !str.equals("")) {
				String[] dateItems = str.split(" ");
				if (dateItems.length > 1) {
					String[] times = dateItems[1].split(":");
					if (times.length > 2) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						return_date = formatter.parse(str);
					} else if (times.length == 2) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						return_date = formatter.parse(str);
					}
				} else {
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					return_date = formatter.parse(str);
				}
			}
		return return_date;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param ch1
	 *            日期与时间的分隔符
	 * @param ch2
	 *            年月日的分隔符
	 * @param ch3
	 *            时分秒的分隔符
	 * @return Date
	 */
	public static Date stringToDate(String dateStr, String ch1, String ch2,
			String ch3) {
		Date resultDate = null;
		String[] strs = dateStr.split(ch1);
		if (strs != null && strs.length > 0) {
			int strsLength = strs.length;
			if (strsLength == 1) {
				String[] dates = strs[0].split(ch2);
				if (dates != null && dates.length > 0) {
					String year = dates[0].trim();
					if (year.length() == 2) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yy-MM-dd");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (year.length() > 2) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else if (strsLength > 1) {
				String[] dates1 = strs[0].split(ch2);
				String[] dates2 = strs[1].split(ch3);
				if (dates1[0].length() == 2) {
					int dateLength = dates2.length;
					if (dateLength == 1) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yy-MM-dd HH");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else if (dateLength == 2) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yy-MM-dd HH:mm");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else if (dateLength == 3) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yy-MM-dd HH:mm:ss");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else if (dates1[0].length() > 2) {
					int dateLength = dates2.length;
					if (dateLength == 1) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (dateLength == 2) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (dateLength == 3) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						try {
							resultDate = formatter.parse(dateStr);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}

		return resultDate;
	}

	/**
	 * 日期转为字符串
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	/**
	 * 日期字符串格式转换
	 * @param dateStr 日期字符串
	 * @param originalFormat 原始格式
	 * @param targetFormat 目标格式
	 * @return
	 */
	public static String stringToString(String dateStr,String originalFormat,String targetFormat){
		return dateToString(stringToDate(dateStr, originalFormat), targetFormat);
	}
	/**
	 * 字符串转日期
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return
	 */
	public static Date stringToDate(String dateStr,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date =  sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算之前几天
	 * 
	 * @param date
	 * @param days
	 * @param format_day
	 * @return String
	 */
	public static String getBeforeDate(Date date, long days, String format_day) {
		SimpleDateFormat df = new SimpleDateFormat(format_day);
		Date beforeDate = new Date(date.getTime() - days * 24 * 60 * 60 * 1000);
		return df.format(beforeDate);
	}

	/**
	 * 计算之后几天
	 * 
	 * @param date
	 * @param days
	 * @param format_day
	 * @return String
	 */
	public static String getAfterDate(Date date, long days, String format_day) {
		SimpleDateFormat df = new SimpleDateFormat(format_day);
		Date beforeDate = new Date(date.getTime() + days * 24 * 60 * 60 * 1000);
		return df.format(beforeDate);
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayOfWeek(Date date) {
		if (date == null)
			return null;
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(c.getTime());
	}

	/**
	 * 取得当前日期后num年
	 * 
	 * @param num
	 * @return Date
	 */
	public static Date getLast(int num) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 365 * num);
		return c.getTime();
	}

	/**
	 * 查询这个月的第一天
	 * 
	 * @param str
	 * @param par
	 * @return
	 * @throws ParseException
	 */
	public static String monthFirstDay() throws ParseException {
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(rightNow.getTime());
	}

	/**
	 * 查询这个月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLast() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(cal.getTime());
	}
	
	public static String getUpMonthFirstDay(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DATE, 1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(c.getTime());
	}
	
	public static String getUpMonthLastDay(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.roll(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(c.getTime());
	}

	/**
	 * 查询出本季的起始日期和结束日期
	 * 
	 * @param date
	 * @return
	 */
	public static String[] getQuarterDate(Date date) {
		String[] array = new String[2];

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if ((cal.get(Calendar.MONTH) + 1) % 3 == 0)// 季度结束月
			{
				array[1] = cal.get(Calendar.YEAR) + "-"
						+ changeStr((cal.get(Calendar.MONTH) + 1)) + "-"
						+ changeStr(cal.getActualMaximum(Calendar.DATE));// 结束日期
				cal.add(Calendar.MONTH, -2);
				array[0] = cal.get(Calendar.YEAR) + "-"
						+ changeStr((cal.get(Calendar.MONTH) + 1)) + "-"
						+ changeStr(cal.getActualMinimum(Calendar.DATE));// 开始日期
			} else if ((cal.get(Calendar.MONTH) + 2) % 3 == 0)// 季度中间月
			{
				cal.add(Calendar.MONTH, -1);
				array[0] = cal.get(Calendar.YEAR) + "-"
						+ changeStr((cal.get(Calendar.MONTH) + 1)) + "-"
						+ changeStr(cal.getActualMinimum(Calendar.DATE));// 开始日期
				cal.add(Calendar.MONTH, +2);
				array[1] = cal.get(Calendar.YEAR) + "-"
						+ changeStr((cal.get(Calendar.MONTH) + 1)) + "-"
						+ changeStr(cal.getActualMaximum(Calendar.DATE));// 结束日期
			} else if ((cal.get(Calendar.MONTH) + 3) % 3 == 0)// 季度起始月
			{
				array[0] = cal.get(Calendar.YEAR) + "-"
						+ changeStr((cal.get(Calendar.MONTH) + 1)) + "-"
						+ changeStr(cal.getActualMinimum(Calendar.DATE));// 开始日期
				cal.add(Calendar.MONTH, +2);
				array[1] = cal.get(Calendar.YEAR) + "-"
						+ changeStr((cal.get(Calendar.MONTH) + 1)) + "-"
						+ changeStr(cal.getActualMaximum(Calendar.DATE));// 结束日期
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return array;
	}

	/**
	 * 本年的第一天
	 * 
	 * @return
	 */
	public static String getYearFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 0);
		int minMonthDate = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, minMonthDate);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(cal.getTime());
	}

	/**
	 * 本年的最后一天
	 * 
	 * @return
	 */
	public static String getYearLastDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 11);
		int minMonthDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DATE, minMonthDate);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(cal.getTime());
	}

	public static String changeStr(int num) {
		String str = "";
		if (num < 10) {
			str = "0" + num;
		} else {
			str = num + "";
		}
		return str;
	}

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date)+getNumRandomString(5);
	}
	
    /**
     * 
     * @Title: getRandomString
     * @Description: 生成随机数
     * @param length 位数默认6位  最大值32位
     * @return String 随机数
     */
    public static String getNumRandomString(int length){
    	if(length < 1 || length > 32)
    		length = 6;
    	Random random = new Random();
		StringBuffer sb = new StringBuffer();
		int index, len = ch_num.length;
		for (int i = 0; i < length; i++) {
			index = random.nextInt(len);
			sb.append(ch_num[index]);
		}
		return sb.toString();
    }
    
    /**
     * @Description:得到date前minute的日期时间
     * @param date
     * @param minute 大于0
     * @return Date 
     */
    public static Date getBeforeDate(Date date, long minute){
    	if(minute < 1){
    		return date;
    	}
    	long now = date.getTime();
    	long beforeDateTime = now - (minute * 60 * 1000);
    	return new Date(beforeDateTime);
    }
    //给时间加？分钟
    public static Date getAfterDate(Date date, int minute){
    	if(minute < 1){
    		return date;
    	}
    	long now = date.getTime();
    	long beforeDateTime = now + (minute * 60 * 1000);
    	return new Date(beforeDateTime);
    }
    public static Date deleteAfterDate(Date date, int minute){
    	if(minute < 1){
    		return date;
    	}
    	long now = date.getTime();
    	long beforeDateTime = now - (minute * 60 * 1000);
    	return new Date(beforeDateTime);
    }
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";
    /** 年月日时分秒(无下划线) yyyy-MM-dd HH:mm:ss */
    public static final String dt1Long = "yyyy-MM-dd HH:mm:ss";
    
    private static char[] ch_num = "0123456789".toCharArray();
	
	public static void main(String[] args) throws ParseException {
//		 System.out.println(getBeforeDate(new Date(),1,"yyyy-MM-dd"));
//		System.out.println(getAfterDate(stringToDate("2011-1-1"), 280,
//				"yyyy-MM-dd"));
		// System.out.println(getFirstDayOfWeek(new Date()));
		// System.out.println(getLastDayOfWeek(new Date()));
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(df.format(new Date()));
		// System.out.println(monthFirstDay());
		// System.out.println(getMonthLast());
		// String[] arr=getQuarterDate(new Date());
		// for(String s:arr)
		// {
		// System.out.println(s);
		// }
		// System.out.println(dateToString(new Date(),"yyyy-MM-dd"));
		// for(int index =0; index < 10; index ++){
		// System.out.println(getAfterDate(new Date(),index,"yyyy-MM-dd"));
		// }
		
		String s1 = "2012-11-09 17:50:01";
		Date date1 = DateUtils.stringToDate(s1);
		int minute = 10;
		for(minute = 10; minute <= 60; minute ++){
			Date date2 = DateUtils.getBeforeDate(date1, minute);
		
			System.out.println(s1+" "+minute+"分钟前 "+DateUtils.dateToString(date2, "yyyy-MM-dd HH:mm:ss")+"\r\n"+date2.getMinutes());
		}
		
	}
	/**
	 * 根据时间判断周几
	 * @param date
	 * @return
	 */
	public static String dateToWeekString(Date date){
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE",Locale.CHINA);
		return dateFm.format(date).replaceAll("星期", "周");
    }
	/**
	 * 获取当前月的前N个月份
	 * @param i
	 * @return
	 */
	public static String getLast12Months(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -i);
		Date m = c.getTime();
		return sdf.format(m);
	}

}

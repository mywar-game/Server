/**
 * 
 */
package com.framework.util; 

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.framework.servicemanager.CustomerContextHolder;
import com.system.manager.DataSourceManager;

/**
 * @author huanglong
 * 
 *         2011-9-30
 */
public class DateUtil {
	protected static Log logger = LogFactory.getLog(DateUtil.class);
	 
    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
 
    // 格式：年－月－日 小时：分钟
    public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";
 
    // 格式：年月日 小时分钟秒
    public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";
    
    public static final String FORMAT_FOUR = "yyyyMMdd";
 
    // 格式：年－月－日
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";
 
    // 格式：月－日
    public static final String SHORT_DATE_FORMAT = "MM-dd";
 
    // 格式：小时：分钟：秒
    public static final String LONG_TIME_FORMAT = "HH:mm:ss";
 
    //格式：年-月
    public static final String MONTG_DATE_FORMAT = "yyyy-MM";
 
    // 年的加减
    public static final int SUB_YEAR = Calendar.YEAR;
 
    // 月加减
    public static final int SUB_MONTH = Calendar.MONTH;
 
    // 天的加减
    public static final int SUB_DAY = Calendar.DATE;
 
    // 小时的加减
    public static final int SUB_HOUR = Calendar.HOUR;
 
    // 分钟的加减
    public static final int SUB_MINUTE = Calendar.MINUTE;
 
    // 秒的加减
    public static final int SUB_SECOND = Calendar.SECOND;
 
    static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四",
            "星期五", "星期六" };
 
    @SuppressWarnings("unused")
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
 
    public DateUtil() {
    }
	/**
	 * 以当前日期推断，并返回一个yyyy-MM-dd hh-mm-ss的格式的字符串形式的日期.
	 * 
	 * @return
	 */
	public static String getAllDate(Integer count) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DATE, count); 
		int date = calendar.get(Calendar.DATE); // 得到当前日期的前一个日期
		int month = calendar.get(Calendar.MONTH) + 1; // 得到当前月
		int year = calendar.get(Calendar.YEAR); // 得的当前年
		int hh = calendar.get(Calendar.HOUR); // 得到小时
		int mm = calendar.get(Calendar.MINUTE); // 得到分钟
		int ss = calendar.get(Calendar.SECOND); // 得到秒钟
		return year + "-" + month + "-" + date + " " + hh + ":" + mm + ":" + ss; 
	}
	
	

	/**
	 * 以当前日期推断，并返回一个以毫秒为单位的时间
	 */
	public static long getTimeInMillis(Integer count) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DATE, count); 
		return calendar.getTimeInMillis(); 
	}
	
	/**
	 * 得到一个跟现在相差几天的日期
	 * @param count 几天前为负，几天后为正
	 * @return
	 */
	public static Date getSomeDaysDiffDate(int count) {
	    Date now = getNowDateBySystemNum();
	    now.setTime(now.getTime() + (24L*3600*1000)*count);
	    return now;
	}
	
	/**
	 * 得到一个跟某天相差几天的日期
	 * @param count 几天前为负，几天后为正
	 * @return
	 */
	public static Date getDiffDate(Date date,int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, count); 
	    return calendar.getTime();
	}
	
	/**
	 * 得到一个跟现在相差几天的日期字符串
	 * @param count 几天前为负，几天后为正
	 * @return
	 */
	public static String getSomeDaysDiffDateStr(int count){
		Date d = getSomeDaysDiffDate(count);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int year = calendar.get(Calendar.YEAR); // 得的当前年
		int month = calendar.get(Calendar.MONTH); // 得到当前月
		int date = calendar.get(Calendar.DATE); // 得到当前日期的前一个日期
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
//		LogSystem.info(new Date(calendar.getTimeInMillis()).toString());
//		LogSystem.info(year + "-" + (month + 1) + "-" + date + " " + hour + ":" + minute + ":" + second);
		String dateStr = year + "-" + (month + 1) + "-" + date + " " + hour + ":" + minute + ":" + second;
		return dateStr;
	}
	
	/**
	 * 返回一个几天前的当天日期数组，yyyy-MM-dd hh:mm:ss 的格式的字符串形式的日期数组.
	 * @return 相差现在几天
	 */
	public static String[] getOneDayStrArr(Integer count) {
		String[] dates = new String[2]; 
		String timeZoneId = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getTimeZoneId();
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));  
		calendar.add(Calendar.DATE, count); 
		int year = calendar.get(Calendar.YEAR); // 得的当前年
		int month = calendar.get(Calendar.MONTH); // 得到当前月
		int date = calendar.get(Calendar.DATE); // 得到当前日期的前一个日期
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);
//		int minute = calendar.get(Calendar.MINUTE);
//		int second = calendar.get(Calendar.SECOND);
//		LogSystem.info(new Date(calendar.getTimeInMillis()).toString());
//		LogSystem.info(year + "-" + (month + 1) + "-" + date + " " + hour + ":" + minute + ":" + second);
		dates[0] = year + "-" + (month + 1) + "-" + date +" 00:00:00";
		dates[1] = year + "-" + (month + 1) + "-" + date +" 23:59:59";
		
		return dates; 
	}
	
	/**
	 * 获得当天零点和当前时间的字符串数组
	 * @return
	 */
	public static String[] getTodayStrArr(Date time){
		String[] dates = new String[2];
		Calendar calendar = null;
		if(time==null){
			String timeZoneId = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getTimeZoneId();
			calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));  
		}else{
			calendar = Calendar.getInstance();
			calendar.setTime(time);
		}
		int year = calendar.get(Calendar.YEAR); // 得的当前年
		int month = calendar.get(Calendar.MONTH); // 得到当前月
		int date = calendar.get(Calendar.DATE); // 得到当前日期的前一个日期
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
//		LogSystem.info(new Date(calendar.getTimeInMillis()).toString());
//		LogSystem.info(year + "-" + (month + 1) + "-" + date + " " + hour + ":" + minute + ":" + second);
		dates[0] = year + "-" + (month + 1) + "-" + date +" 00:00:00";
		dates[1] = year + "-" + (month + 1) + "-" + date + " " + hour + ":" + minute + ":" + second;
		
		return dates; 
	}
	
	/**
	 * 获得两个时间段对应的字符串数组
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String[] getDateArrStr(Date startDate,Date endDate){
		Calendar calendar1 = Calendar.getInstance(); 
		Calendar calendar2 = Calendar.getInstance(); 
		calendar1.setTime(startDate);
		calendar2.setTime(endDate);
		String[] dates = new String[2]; 
		int year1 = calendar1.get(Calendar.YEAR); // 得的当前年
		int month1 = calendar1.get(Calendar.MONTH); // 得到当前月
		int date1 = calendar1.get(Calendar.DATE); // 得到当前日期的前一个日期
		int year2 = calendar2.get(Calendar.YEAR); // 得的当前年
		int month2 = calendar2.get(Calendar.MONTH); // 得到当前月
		int date2 = calendar2.get(Calendar.DATE); // 得到当前日期的前一个日期
		dates[0] = year1 + "-" + (month1 + 1) + "-" + date1 +" 00:00:00";
		dates[1] = year2 + "-" + (month2 + 1) + "-" + date2 + " 23:59:59";
		return dates; 
	}
	
	/**
	 * 获得两个时间段对应的字符串数组
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String[] getMonthArrStr(Date startDate,Date endDate){
		Calendar calendar1 = Calendar.getInstance(); 
		Calendar calendar2 = Calendar.getInstance(); 
		calendar1.setTime(startDate);
		calendar2.setTime(endDate);
		String[] dates = new String[2]; 
		int year1 = calendar1.get(Calendar.YEAR); // 得的当前年
		int month1 = calendar1.get(Calendar.MONTH); // 得到当前月
		int date1 = calendar1.get(Calendar.DATE); // 得到当前日期的前一个日期
		int year2 = calendar2.get(Calendar.YEAR); // 得的当前年
		int month2 = calendar2.get(Calendar.MONTH); // 得到当前月
		int date2 = calendar2.get(Calendar.DATE); // 得到当前日期的前一个日期
		dates[0] = year1 + "-" + (month1 + 1) + "-" + date1 +" 00:00:00";
		dates[1] = year2 + "-" + (month2 + 1) + "-" + date2 + " 23:59:59";
		return dates; 
	}
	
	
	/**
	 * 某个时间点30分钟之内的的时间字符数组
	 */
	public static String[] getInSomeMinuteStrArr(Date date,int count){
		String[] dates = new String[2]; 
		Calendar calendar = Calendar.getInstance();  
		Calendar calendar1 = Calendar.getInstance(); 
		calendar1.setTime(date);
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, count);
		dates[0] = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
		dates[1] = calendar1.get(Calendar.YEAR) + "-" + (calendar1.get(Calendar.MONTH) + 1) + "-" + calendar1.get(Calendar.DATE) + " " + calendar1.get(Calendar.HOUR_OF_DAY) + ":" + calendar1.get(Calendar.MINUTE) + ":" + calendar1.get(Calendar.SECOND);
		return dates;
	}
	
	/**
	 * 获取时间维度
	 * @param rate
	 * @return
	 */
	public static int getIndex(Date date,int rate){
		Calendar calendar = Calendar.getInstance(); 
		if(date!=null){
			calendar.setTime(date);
		}
		int longTime = calendar.get(Calendar.HOUR_OF_DAY)*60*60*1000+calendar.get(Calendar.MINUTE)*60*1000+calendar.get(Calendar.SECOND)*1000;
		return longTime/rate;
	}
	
	public static void main(String[] args) {
		String[] dates = new String[2]; 
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));  
		Calendar calendar1 = Calendar.getInstance();  
		calendar.add(Calendar.MINUTE, 30);
		calendar.add(Calendar.MONTH, -5);
		calendar1.add(Calendar.DATE, 1);
		dates[0] = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
		dates[1] = calendar1.get(Calendar.YEAR) + "-" + (calendar1.get(Calendar.MONTH) + 1) + "-" + calendar1.get(Calendar.DATE) + " " + calendar1.get(Calendar.HOUR_OF_DAY) + ":" + calendar1.get(Calendar.MINUTE) + ":" + calendar1.get(Calendar.SECOND);
		System.out.print("\n"+getIndex(new Date(),30*60*1000));
		String date = "2013-7-7 01:29:59";
		Date d = stringtoDate(date, FORMAT_ONE);
		System.out.print("\n"+dates[0].substring(0, dates[0].length()-9));
		System.out.print("\n"+dates[1].substring(0, dates[1].length()-9));
		System.out.print("\n"+getIndex(d,30*60*1000));
		System.out.print("\n"+dateToString(d, LONG_DATE_FORMAT));
	}

    /**
     * 根据当前服务器的编号获得服务器端的当前Date
     * @return 当前Date
     */
    public static Date getNowDateBySystemNum(){  
    	String timeZoneId = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getTimeZoneId();
        int timeOffset = TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone(timeZoneId).getRawOffset();  
        Date timeZoneDate = new Date(System.currentTimeMillis() - timeOffset);  
	    return timeZoneDate;  
	}
    
    /**
     * 获得当天的零点时间
     * @param date
     * @return
     */
    public static Date getZeroTime(Date date){
    	Calendar calendar = Calendar.getInstance();  
    	calendar.setTime(date);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return calendar.getTime();
    }
    
    /**
     * 查看已开服几天
     * @return
     */
    public static int getOpenDays(){
    	int openDays = 0;
    	Calendar openC = Calendar.getInstance();
		Timestamp serverOpenTime = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getServerOpernTime();
		openC.setTimeInMillis(serverOpenTime.getTime());
		openC.set(Calendar.HOUR_OF_DAY, 0);
		openC.set(Calendar.MINUTE, 0);
		openC.set(Calendar.SECOND, 0);
		openC.set(Calendar.MILLISECOND, 0);
		
		Calendar now = Calendar.getInstance();
		now.setTime(getNowDateBySystemNum());
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		while (openC.before(now)) {
			openDays++;
			openC.add(Calendar.DAY_OF_YEAR, 1);
		}
		return openDays;
    }
    
    /**
     * 查看某个服务器到某天为止是否已经开服30天了
     * @return
     */
    public static boolean isOpenThirtyDays(Date date,Integer sysNum){
    	int openDays = 0;
    	Calendar openC = Calendar.getInstance();
    	if (!DataSourceManager.getInstatnce().getGameServerMap().containsKey(sysNum))
    		return false;
		Timestamp serverOpenTime = DataSourceManager.getInstatnce().getGameServerMap().get(sysNum).getServerOpernTime();
		openC.setTimeInMillis(serverOpenTime.getTime());
		openC.set(Calendar.HOUR_OF_DAY, 0);
		openC.set(Calendar.MINUTE, 0);
		openC.set(Calendar.SECOND, 0);
		openC.set(Calendar.MILLISECOND, 0);
		
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		while (openC.before(now)) {
			openDays++;
			openC.add(Calendar.DAY_OF_YEAR, 1);
		}
		if(openDays>=30){
			return true;
		}else{
			return false;
		}
    }
    
    public static String formatTime(long time) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String timeZoneId = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getTimeZoneId();
    	sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
    	return sdf.format(new Date(time));
    }
    
    public static String getTime(Integer count) {
    	Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DATE, count); 
		return dateToString(calendar.getTime(), LONG_DATE_FORMAT);
    }
    
    public static String getTime(Integer count, Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, count);
    	return dateToString(calendar.getTime(), LONG_DATE_FORMAT);
    }
    
    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static java.util.Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }
    
    
    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static java.util.Date stringtoDate(String dateStr, String format,
            ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }
 
    /**
     * 把日期转换为字符串
     */
    public static String dateToString(java.util.Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }
 
    /**
     * 获取当前时间的指定格式
     */
    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }
 
    public static String dateSub(int dateKind, String dateStr, int amount) {
        Date date = stringtoDate(dateStr, FORMAT_ONE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return dateToString(calendar.getTime(), FORMAT_ONE);
    }
 
    /**
     * 两个日期相减
     * @return 相减得到的秒数
     */
    public static long timeSub(String firstTime, String secTime) {
        long first = stringtoDate(firstTime, FORMAT_ONE).getTime();
        long second = stringtoDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }
 
    /**
     * 获得某月的天数
     */
    public static int getDaysOfMonth(String year, String month) {
        int days = 0;
        if (month.equals("1") || month.equals("3") || month.equals("5")
                || month.equals("7") || month.equals("8") || month.equals("10")
                || month.equals("12")) {
            days = 31;
        } else if (month.equals("4") || month.equals("6") || month.equals("9")
                || month.equals("11")) {
            days = 30;
        } else {
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
                    || Integer.parseInt(year) % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }
 
        return days;
    }
 
    /**
     * 获取某年某月的天数
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
 
    /**
     * 获得当前日期
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }
 
    /**
     * 获得当前月份
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
 
    /**
     * 获得当前年份
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
 
    /**
     * 返回日期的天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
 
    /**
     * 返回日期的年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
 
    /**
     * 返回日期的月份，1-12
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
 
    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }
    
    /**
     * 比较两个日期的年差
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = stringtoDate(before, LONG_DATE_FORMAT);
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(afterDay) - getYear(beforeDay);
    }
 
    /**
     * 比较指定日期与当前日期的差
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringtoDate(after, LONG_DATE_FORMAT);
        return getYear(beforeDay) - getYear(afterDay);
    }
     
    /**
     * 比较指定日期与当前日期的差
     */
    public static long dayDiffCurr(String before) {
        Date beforeDate = stringtoDate(before, LONG_DATE_FORMAT);
        return (System.currentTimeMillis() - beforeDate.getTime()) / 86400000;
 
    }
 
    /**
     * 获取每月的第一周
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取每月的最后一周
     */
    public static int getLastWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }
 
    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     * 
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return dateToString(today.getTime(), FORMAT_ONE);
    }
 
    
 
    /**
     * 判断日期是否有效,包括闰年的情况
     * 
     * @param date
     *          YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }
    
    /**
     * 获得两个日期见的日期字符串
     * @param date1
     * @param date2
     */
    public static Date[] dayStrDiff(Date date1, Date date2){
    	Date z1 = getZeroTime(date1);
    	Date z2 = getZeroTime(date2);
    	int diffDay = (int)dayDiff(z1, z2);
    	Date[] dateArr = new Date[diffDay+1];
    	dateArr[0] = z1;
    	if(diffDay>0){
    		for(int i=1;i<=diffDay;i++){
    			Date date = getDiffDate(z1, i);
    			dateArr[i] = date;
    		}
    	}
    	return dateArr;
    }
    
    /**
    * 根据某个日期得到前一天日期
    * @param d
    * @return
    */
    public static Date getBeforeDate(Date d){
    	Date date = d;
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, -1);
    	date = calendar.getTime();
    	return date;
    }
    
    /**
     * 通过某日期获取格式化日期(后来添加, 用于重新采集数据)
     * @param d
     * @return
     */
    public static String[] getDateStr(Date d) {
    	String[] dates = new String[2]; 
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(d);
		int year = calendar.get(Calendar.YEAR); // 得的当前年
		int month = calendar.get(Calendar.MONTH); // 得到当前月
		int date = calendar.get(Calendar.DATE); // 得到当前日期的前一个日期
		
		dates[0] = year + "-" + (month + 1) + "-" + date +" 00:00:00";
		dates[1] = year + "-" + (month + 1) + "-" + date +" 23:59:59";
		
		return dates; 
    }
    
    /**
     * 根据日期获取几天前，后的日期(后来添加, 用于重新采集)
     * @param d
     * @param count
     * @return
     */
    public static String[] getBeforeDateByDate(Date d, int count) {
    	String[] dates = new String[2]; 
		String timeZoneId = DataSourceManager.getInstatnce().getGameServerMap().get(CustomerContextHolder.getSystemNum()).getTimeZoneId();
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
		calendar.setTime(d);
		calendar.add(Calendar.DATE, count); 
		int year = calendar.get(Calendar.YEAR); // 得的当前年
		int month = calendar.get(Calendar.MONTH); // 得到当前月
		int date = calendar.get(Calendar.DATE); // 得到当前日期的前一个日期
		
		dates[0] = year + "-" + (month + 1) + "-" + date +" 00:00:00";
		dates[1] = year + "-" + (month + 1) + "-" + date +" 23:59:59";
		
		return dates; 
    }
    
    /**
     * 根据日期获得与这个日期相差几天的日期(后来添加, 用于重新采集)
     * @param count
     * @return
     */
    public static Date getSomeDaysDiffDateByDate(Date now, int count) {
	    now.setTime(now.getTime() + (24L*3600*1000)*count);
	    return now;
	}
    
    /** 
     * 根据日期获得与该日期相差几天的字符串(后来添加, 用于重新采集)
     * @param count
     * @return
     */
    public static String getTimeByDate(Date d, Integer count) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(d);
		calendar.add(Calendar.DATE, count); 
		return dateToString(calendar.getTime(), LONG_DATE_FORMAT);
    }
}

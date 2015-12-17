/**
 * 
 */
package com.framework.common;

/**
 * @author hzy
 * 2012-7-18
 */
public final class SystemStatsDate {
	
	/**  **/
	private SystemStatsDate() {
	}
	
	/**  **/
	public static final int TODAY = 0;
	
	/**  **/
	public static final int YESTERDAY = -1;
	/**两天前**/
	public static final int TWO_DAYS_AGO = -2;
	/**  **/
	public static final int THREE_DAYS_AGO = -3;
	
	/**  **/
	public static final int LASTWEEK_YESTERDAY = -7;
	
	/**  **/
	public static final int LAST_MONTH = -30; 
	/**30分钟钱**/
	public static final int THIRTY_MINUTE_AGO = -30;
	
	public static final int FIVE_MINUTE_INDEX = 5*60*1000;//5分钟的维度
	public static final int HALF_HOUR_INDEX = 30*60*1000;//半小时的维度
}

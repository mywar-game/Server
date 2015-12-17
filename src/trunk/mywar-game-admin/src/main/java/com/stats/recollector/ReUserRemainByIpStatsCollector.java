package com.stats.recollector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.admin.util.Tools;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.log.service.UserRegLogService;
import com.stats.bo.UserRemainByIpStats;
import com.stats.service.UserRemainByIpStatsService;


public class ReUserRemainByIpStatsCollector {
	
	/** 次日留存：第2天 */
	private static final int ONE_DAY_REMAIN_DAYS = 2;
	/** 2日留存：第3天 */
	private static final int TWO_DAY_REMAIN_DAYS = 3;
	/** 3日留存：第4天 */
	private static final int THREE_DAY_REMAIN_DAYS = 4;
	/** 4日留存：第5天 */
	private static final int FOUR_DAY_REMAIN_DAYS = 5;
	/** 5日留存：第6天 */
	private static final int FIVE_DAY_REMAIN_DAYS = 6;
	/** 6日留存：第7天 */
	private static final int SIX_DAY_REMAIN_DAYS = 7;
	/** 7日留存：第八天 */
	private static final int SEVEN_DAY_REMAIN_DAYS = 8;
	/** 8日留存：第九天*/
	private static final int EIGHT_DAY_REMAIN_DAYS = 9;
	/** 9日留存：第十天*/
	private static final int NIGHT_DAY_REMAIN_DAYS = 10;
	/** 10日留存：第十一天*/
	private static final int TEN_DAY_REMAIN_DAYS = 11;
	/** 11日留存：第十二天*/
	private static final int ELEVEN_DAY_REMAIN_DAYS = 12;
	/** 12日留存：第十三天*/
	private static final int TWELVE_DAY_REMAIN_DAYS = 13;
	/** 13日留存：第十四天*/
	private static final int THIRTEEN_DAY_REMAIN_DAYS = 14;
	/** 14日留存：第十五天*/
	private static final int FOURTEEN_DAY_REMAIN_DAYS = 15;
	/** 15日留存：第16天 */
	private static final int FIFTEEN_DAY_REMAIN_DAYS = 16;
	/** 16日留存：第17天*/
	private static final int SIXTEEN_DAY_REMAIN_DAYS = 17;
	/** 17日留存：第18天*/
	private static final int SEVENTEEN_DAY_REMAIN_DAYS = 18;
	/** 18日留存：第19天*/
	private static final int EIGHTTEEN_DAY_REMAIN_DAYS = 19;
	/** 19日留存：第20天*/
	private static final int NIGHTTEEN_DAY_REMAIN_DAYS = 20;
	/** 20日留存：第21天*/
	private static final int TWENTIE_DAY_REMAIN_DAYS = 21;
	/** 21日留存：第22天*/
	private static final int TWENTIEONE_DAY_REMAIN_DAYS = 22;
	/** 22日留存：第23天*/
	private static final int TWENTIETWO_DAY_REMAIN_DAYS = 23;
	/** 23日留存：第24天*/
	private static final int TWENTIETHREE_DAY_REMAIN_DAYS = 24;
	/** 24日留存：第25天*/
	private static final int TWENTIEFOUR_DAY_REMAIN_DAYS = 25;
	/** 25日留存：第26天*/
	private static final int TWENTIEFIVE_DAY_REMAIN_DAYS = 26;
	/** 26日留存：第27天*/
	private static final int TWENTIESIX_DAY_REMAIN_DAYS = 27;
	/** 27日留存：第28天*/
	private static final int TWENTIESEVEN_DAY_REMAIN_DAYS = 28;
	/** 28日留存：第29天*/
	private static final int TWENTIEEIGHT_DAY_REMAIN_DAYS = 29;
	/** 29日留存：第30天*/
	private static final int TWENTIENEIGHT_DAY_REMAIN_DAYS = 30;
	/** 30日留存：第三十一天 */
	private static final int THIRTY_DAY_REMAIN_DAYS = 31;
	
	public void execute(String dateStr) {
		LogSystem.info("手动--用户留存统计Collector开始(根据ip)");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateTemp = null;
		try {
			dateTemp = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] dates = DateUtil.getBeforeDateByDate(dateTemp, 0);
		
		/** 计算差几天 **/
		String dateStr2 = dateStr + " 00:00:00";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dateTemp2 = null;
		try {
			dateTemp2 = sdf2.parse(dateStr2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long intervalMilli = new Date().getTime() - dateTemp2.getTime();
		int leftDays = (int) (intervalMilli / (24 * 60 * 60 * 1000));
		if (leftDays < 2) {
			// 不能采集今天和昨天的数据
			return;
		}
		/** 计算差几天 **/

		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		
		// 获得当前服务器的留存数据
		UserRemainByIpStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainByIpStatsService.class);
		Map<String, UserRemainByIpStats> statsMap = statsService.findNowServerData();
		
		// 只重新采集特定日期的数据
		if (statsMap.containsKey(dateStr)) {
			UserRemainByIpStats userRemainStats = statsMap.get(dateStr);
			for (int i = 2; i <= leftDays; i++) {
				if (i == 2) {
					// 2天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 1);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setOneDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setOneDayRemainNum(remainUsers);
					userRemainStats.setOneDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);
					
				} else if (i == 3) {
					// 3天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 2);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 3天前的那天注册的人数
					
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwoDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setTwoDayRemainNum(remainUsers);
					userRemainStats.setTwoDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);
				} else if (i == 4) {
					// 4天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 3);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 4天前的那天注册的人数
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setThreeDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setThreeDayRemainNum(remainUsers);
					userRemainStats.setThreeDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);	
				} else if (i == 5) {
					// 5天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 4);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 5天前的那天注册的人数
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setFourDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setFourDayRemainNum(remainUsers);
					userRemainStats.setFourDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);	
				} else if (i == 6) {
					// 6天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 5);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 6天前的那天注册的人数
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setFiveDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setFiveDayRemainNum(remainUsers);
					userRemainStats.setFiveDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);
					
				} else if (i == 7) {
					// 7天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 6);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 7天前的那天注册的人数
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setSixDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setSixDayRemainNum(remainUsers);
					userRemainStats.setSixDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);	
				} else if (i == 8) {
					// 8天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 7);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 8天前的那天注册的人数
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setSevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setSevenDayRemainNum(remainUsers);
					userRemainStats.setSevenDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);
				} else if (i == 9) {
					// 9天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 8);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 9天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setEightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
							
					userRemainStats.setEightDayRemainNum(remainUsers);
					userRemainStats.setEightDayRegNum(regUserNum);
							
					statsService.save(userRemainStats);
				} else if (i == 10) {
					// 10天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 9);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 10天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setNeightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
									
					userRemainStats.setNeightDayRemainNum(remainUsers);
					userRemainStats.setNeightDayRegNum(regUserNum);
									
					statsService.save(userRemainStats);
				} else if (i == 11) {
					// 11天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 10);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 11天前的那天注册的人数
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
											
					userRemainStats.setTenDayRemainNum(remainUsers);
					userRemainStats.setTenDayRegNum(regUserNum);
											
					statsService.save(userRemainStats);	
				} else if (i == 12) {
					// 12天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 11);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 12天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setElevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
													
					userRemainStats.setElevenDayRemainNum(remainUsers);
					userRemainStats.setElevenDayRegNum(regUserNum);
													
					statsService.save(userRemainStats);
				} else if (i == 13) {
					// 13天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 12);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 13天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwelfDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
															
					userRemainStats.setTwelfDayRemainNum(remainUsers);
					userRemainStats.setTwelfDayRegNum(regUserNum);
															
					statsService.save(userRemainStats);
						
				} else if (i == 14) {
					// 14天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 13);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 14天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setThirteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																	
					userRemainStats.setThirteenDayRemainNum(remainUsers);
					userRemainStats.setThirteenDayRegNum(regUserNum);
																	
					statsService.save(userRemainStats);	
				} else if (i == 15) {
					// 15天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 14);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates,dates1);
					// 15天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setFourteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																			
					userRemainStats.setFourteenDayRemainNum(remainUsers);
					userRemainStats.setFourteenDayRegNum(regUserNum);
																			
					statsService.save(userRemainStats);	
				} else if (i == 16) {
					// 16天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 15);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 16天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setFifteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setFifteenDayRemainNum(remainUsers);
					userRemainStats.setFifteenDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);
				} else if (i == 17) {
					// 17天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 16);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 17天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setSixteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
							
					userRemainStats.setSixteenDayRemainNum(remainUsers);
					userRemainStats.setSixteenDayRegNum(regUserNum);
							
					statsService.save(userRemainStats);
				} else if (i == 18) {
					// 18天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 17);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 18天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setSeventeenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
									
					userRemainStats.setSeventeenDayRemainNum(remainUsers);
					userRemainStats.setSeventeenDayRegNum(regUserNum);
									
					statsService.save(userRemainStats);	
				} else if (i == 19) {
					// 19天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 18);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 19天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setEightteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
											
					userRemainStats.setEightteenDayRemainNum(remainUsers);
					userRemainStats.setEightteenDayRegNum(regUserNum);
											
					statsService.save(userRemainStats);
				} else if (i == 20) {
					// 20天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 19);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 20天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setNeightteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
													
					userRemainStats.setNeightteenDayRemainNum(remainUsers);
					userRemainStats.setNeightteenDayRegNum(regUserNum);
													
					statsService.save(userRemainStats);	
				} else if (i == 21) {
					// 21天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 20);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 21天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentieDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
															
					userRemainStats.setTwentieDayRemainNum(remainUsers);
					userRemainStats.setTwentieDayRegNum(regUserNum);
															
					statsService.save(userRemainStats);	
				} else if (i == 22) {
					// 22天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 21);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 22天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentieoneDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																	
					userRemainStats.setTwentieoneDayRemainNum(remainUsers);
					userRemainStats.setTwentieoneDayRegNum(regUserNum);
															
					statsService.save(userRemainStats);
						
				} else if (i == 23) {
					// 23天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 22);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 23天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentietwoDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																			
					userRemainStats.setTwentietwoDayRemainNum(remainUsers);
					userRemainStats.setTwentietwoDayRegNum(regUserNum);
																	
					statsService.save(userRemainStats);
				} else if (i == 24) {
					// 24天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 23);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 24天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentiethreeDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																					
					userRemainStats.setTwentiethreeDayRemainNum(remainUsers);
					userRemainStats.setTwentiethreeDayRegNum(regUserNum);
																			
					statsService.save(userRemainStats);
				} else if (i == 25) {
					// 25天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 24);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 25天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentiefourDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																							
					userRemainStats.setTwentiefourDayRemainNum(remainUsers);
					userRemainStats.setTwentiefourDayRegNum(regUserNum);
																					
					statsService.save(userRemainStats);
				} else if (i == 26) {
					// 26天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 25);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 26天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentiefiveDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																									
					userRemainStats.setTwentiefiveDayRemainNum(remainUsers);
					userRemainStats.setTwentiefiveDayRegNum(regUserNum);
																							
					statsService.save(userRemainStats);
				} else if (i == 27) {
					// 27天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 26);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 27天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentiesixDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																											
					userRemainStats.setTwentiesixDayRemainNum(remainUsers);
					userRemainStats.setTwentiesixDayRegNum(regUserNum);
																									
					statsService.save(userRemainStats);
				} else if (i == 28) {
					// 28天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 27);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 28天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentiesevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																													
					userRemainStats.setTwentiesevenDayRemainNum(remainUsers);
					userRemainStats.setTwentiesevenDayRegNum(regUserNum);
																											
					statsService.save(userRemainStats);
				} else if (i == 29) {
					// 29天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 28);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 29天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentieeightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																															
					userRemainStats.setTwentieeightDayRemainNum(remainUsers);
					userRemainStats.setTwentieeightDayRegNum(regUserNum);
																													
					statsService.save(userRemainStats);
				} else if (i == 30) {
					// 30天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 29);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 30天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setTwentienightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
																															
					userRemainStats.setTwentieneightDayRemainNum(remainUsers);
					userRemainStats.setTwentieneightDayRegNum(regUserNum);
																															
					statsService.save(userRemainStats);
				} else if (i == 31) {
					// 31天前的那天注册并且第昨天登录的人数
					Date regDate = DateUtil.getDiffDate(dateTemp, -leftDays + 30);
					String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
					String[] dates1 = new String[2];
					dates1[0] = regDateStr + " 00:00:00";
					dates1[1] = regDateStr + " 23:59:59";
					int remainUsers = userLoginLogService.findUserRemainInfoByIp(dates, dates1);
					// 31天前的那天注册的人数
					// int regUserNum = userService.countSomeDayRegedUser(THIRTY_DAY_REMAIN_DAYS);
					int regUserNum = userRegLogService.reCountSomeDayCreatedUserByIp(-leftDays);
					userRemainStats.setThirtyDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers, (double)regUserNum, "#.0000")));
					
					userRemainStats.setThirtyDayRemainNum(remainUsers);
					userRemainStats.setThirtyDayRegNum(regUserNum);
					
					statsService.save(userRemainStats);
				}
			}
		}

		LogSystem.info("手动--用户留存统计Collector完毕(根据ip)");
	}
}

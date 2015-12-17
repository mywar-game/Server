package com.stats.collector;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.admin.util.Tools;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.log.service.UserRegLogService;
import com.stats.bo.UserRemainByChannelStats;
import com.stats.service.UserRemainByChannelStatsService;

public class UserRemainStatsByChannelCollector implements Collector {
	
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
	
	@Override
	public void execute(Date time) throws Exception {
		LogSystem.info("分渠道--用户留存统计Collector开始");
		
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		UserRemainByChannelStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainByChannelStatsService.class);		
		Map<String, List<UserRemainByChannelStats>> statsMap = statsService.findNowServerDataByChannel();
//		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();
		// 改成从实际数据获取
		UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
		List<String> channelList = userRegLogService.findAllChannel();
		
		// 昨天的留存信息统计
		Date yesterday = DateUtil.getDiffDate(time, SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		String[] dates = new String[2];
		dates[0] = yesterdayStr + " 00:00:00";
		dates[1] = yesterdayStr + " 23:59:59";
		Map<String, Integer> totalMap = userService.countAllUserByChannel();
		Map<String, Integer> newRegMap = userService.findRegUserNumInSomeTimeByChannel(dates);
		Map<String, Integer> loginMap = userLoginLogService.findActiveUserAmountByChannel(dates);
		Map<String, Integer> newCreateMap = userService.findCreatedUserNumInSomeTimeByChannel(dates);
		
		
		for(String channel : channelList){
			//String channel = partner.getPNum(); // 渠道
			List<UserRemainByChannelStats> userRemainByChannelStatsList = statsMap.get(channel);
		    
		    UserRemainByChannelStats yesterdayStats = new UserRemainByChannelStats();
		    yesterdayStats.setUserTotal(totalMap.get(channel)==null?0:totalMap.get(channel)); // 玩家总数
		    yesterdayStats.setNewReg(newRegMap.get(channel)==null?0:newRegMap.get(channel)); // 新注册
		    yesterdayStats.setDayActive(loginMap.get(channel)==null?0:loginMap.get(channel)); // 日活跃
		    yesterdayStats.setNewCreated(newCreateMap.get(channel)==null?0:newCreateMap.get(channel)); // 新创角	    
		    yesterdayStats.setSysNum(CustomerContextHolder.getSystemNum()); // 服务器
			yesterdayStats.setTime(yesterday); // 日期
			yesterdayStats.setChannel(channel); // 渠道
		    
		    statsService.save(yesterdayStats); // 昨天的数据
		    
		    if (userRemainByChannelStatsList != null) {
		    	 UserRemainByChannelStats userRemainByChannelStats;
				    for (UserRemainByChannelStats st : userRemainByChannelStatsList) {
				    	
				    	userRemainByChannelStats = st;
				    	String dateFormat = DateUtil.dateToString(st.getTime(), DateUtil.LONG_DATE_FORMAT);
				    	// 统计2天前那天的次日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-ONE_DAY_REMAIN_DAYS))) {
				    		if (st.getOneDayRemain() == null) {
				    			// 2天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -ONE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(ONE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setOneDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setOneDayRemainNum(remainUsers);
									userRemainByChannelStats.setOneDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计3天前那天的的2日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWO_DAY_REMAIN_DAYS))) {
				    		if (st.getTwoDayRemain() == null) {
				    			// 3天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWO_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWO_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwoDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwoDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwoDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计4天前那天的的3日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-THREE_DAY_REMAIN_DAYS))) {
				    		if (st.getThreeDayRemain() == null) {
				    			// 4天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -THREE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(THREE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setThreeDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setThreeDayRemainNum(remainUsers);
									userRemainByChannelStats.setThreeDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计5天前那天的的4日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-FOUR_DAY_REMAIN_DAYS))) {
				    		if (st.getFourDayRemain() == null) {
				    			// 5天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -FOUR_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(FOUR_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setFourDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setFourDayRemainNum(remainUsers);
									userRemainByChannelStats.setFourDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计6天前那天的的5日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-FIVE_DAY_REMAIN_DAYS))) {
				    		if (st.getFiveDayRemain() == null) {
				    			// 6天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -FIVE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(FIVE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setFiveDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setFiveDayRemainNum(remainUsers);
									userRemainByChannelStats.setFiveDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计7天前那天的的6日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-SIX_DAY_REMAIN_DAYS))) {
				    		if (st.getSixDayRemain() == null) {
				    			// 7天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -SIX_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(SIX_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setSixDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setSixDayRemainNum(remainUsers);
									userRemainByChannelStats.setSixDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计8天前那天的的7日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-SEVEN_DAY_REMAIN_DAYS))) {
				    		if (st.getSevenDayRemain() == null) {
				    			// 8天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -SEVEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(SEVEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setSevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setSevenDayRemainNum(remainUsers);
									userRemainByChannelStats.setSevenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计9天前那天的的8日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-EIGHT_DAY_REMAIN_DAYS))) {
				    		if (st.getEightDayRemain() == null) {
				    			// 9天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -EIGHT_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(EIGHT_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setEightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setEightDayRemainNum(remainUsers);
									userRemainByChannelStats.setEightDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计10天前那天的的9日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-NIGHT_DAY_REMAIN_DAYS))) {
				    		if (st.getNeightDayRemain() == null) {
				    			// 10天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -NIGHT_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(NIGHT_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setNeightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setNeightDayRemainNum(remainUsers);
									userRemainByChannelStats.setNeightDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计11天前那天的的10日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TEN_DAY_REMAIN_DAYS))) {
				    		if (st.getTenDayRemain() == null) {
				    			// 11天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTenDayRemainNum(remainUsers);
									userRemainByChannelStats.setTenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计12天前那天的的11日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-ELEVEN_DAY_REMAIN_DAYS))) {
				    		if (st.getElevenDayRemain() == null) {
				    			// 12天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -ELEVEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(ELEVEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setElevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setElevenDayRemainNum(remainUsers);
									userRemainByChannelStats.setElevenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计13天前那天的的12日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWELVE_DAY_REMAIN_DAYS))) {
				    		if (st.getTwelfDayRemain() == null) {
				    			// 13天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWELVE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWELVE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwelfDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwelfDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwelfDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计14天前那天的的13日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-THIRTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getThirteenDayRemain() == null) {
				    			// 14天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -THIRTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(THIRTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setThirteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setThirteenDayRemainNum(remainUsers);
									userRemainByChannelStats.setThirteenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计15天前那天的的14日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-FOURTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getFourteenDayRemain() == null) {
				    			// 15天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -FOURTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(FOURTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setFourteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setFourteenDayRemainNum(remainUsers);
									userRemainByChannelStats.setFourteenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计16天前那天的的15日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-FIFTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getFifteenDayRemain() == null) {
				    			// 16天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -FIFTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(FIFTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setFifteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setFifteenDayRemainNum(remainUsers);
									userRemainByChannelStats.setFifteenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计17天前那天的的16日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-SIXTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getSixteenDayRemain() == null) {
				    			// 17天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -SIXTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(SIXTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setSixteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setSixteenDayRemainNum(remainUsers);
									userRemainByChannelStats.setSixteenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计18天前那天的的17日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-SEVENTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getSeventeenDayRemain() == null) {
				    			// 18天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -SEVENTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(SEVENTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setSeventeenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setSeventeenDayRemainNum(remainUsers);
									userRemainByChannelStats.setSeventeenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计19天前那天的的18日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-EIGHTTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getEightteenDayRemain() == null) {
				    			// 19天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -EIGHTTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(EIGHTTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setEightteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setEightteenDayRemainNum(remainUsers);
									userRemainByChannelStats.setEightteenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计20天前那天的的19日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-NIGHTTEEN_DAY_REMAIN_DAYS))) {
				    		if (st.getNeightteenDayRemain() == null) {
				    			// 20天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -NIGHTTEEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(NIGHTTEEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setNeightteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setNeightteenDayRemainNum(remainUsers);
									userRemainByChannelStats.setNeightteenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计21天前那天的的20日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIE_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentieDayRemain() == null) {
				    			// 21天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentieDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentieDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentieDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计22天前那天的的21日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIEONE_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentieoneDayRemain() == null) {
				    			// 22天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIEONE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIEONE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentieoneDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentieoneDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentieoneDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计23天前那天的的22日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIETWO_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentietwoDayRemain() == null) {
				    			// 23天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIETWO_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIETWO_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentietwoDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentietwoDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentietwoDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计24天前那天的的23日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIETHREE_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentiethreeDayRemain() == null) {
				    			// 24天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIETHREE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIETHREE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentiethreeDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentiethreeDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentiethreeDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计25天前那天的的24日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIEFOUR_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentiefourDayRemain() == null) {
				    			// 25天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIEFOUR_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIEFOUR_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentiefourDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentiefourDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentiefourDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计26天前那天的的25日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIEFIVE_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentiefiveDayRemain() == null) {
				    			// 26天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIEFIVE_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIEFIVE_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentiefiveDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentiefiveDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentiefiveDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计27天前那天的的26日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIESIX_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentiesixDayRemain() == null) {
				    			// 27天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIESIX_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIESIX_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentiesixDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentiesixDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentiesixDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计28天前那天的的27日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIESEVEN_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentiesevenDayRemain() == null) {
				    			// 28天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIESEVEN_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIESEVEN_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentiesevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentiesevenDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentiesevenDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计29天前那天的的28日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIEEIGHT_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentieeightDayRemain() == null) {
				    			// 29天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIEEIGHT_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIEEIGHT_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentieeightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentieeightDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentieeightDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计30天前那天的的29日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-TWENTIENEIGHT_DAY_REMAIN_DAYS))) {
				    		if (st.getTwentienightDayRemain() == null) {
				    			// 30天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -TWENTIENEIGHT_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(TWENTIENEIGHT_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setTwentienightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setTwentieneightDayRemainNum(remainUsers);
									userRemainByChannelStats.setTwentieneightDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    	
				    	// 统计31天前那天的的30日留存
				    	if (dateFormat.equalsIgnoreCase(DateUtil.getTime(-THIRTY_DAY_REMAIN_DAYS))) {
				    		if (st.getThirtyDayRemain() == null) {
				    			// 31天前的那天注册并且第昨天登录的人数
				    			Date regDate = DateUtil.getDiffDate(time, -THIRTY_DAY_REMAIN_DAYS);
				    			String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				    			String[] dates1 = new String[2];
								dates1[0] = regDateStr + " 00:00:00";
								dates1[1] = regDateStr + " 23:59:59";
								try {
									int remainUsers = userLoginLogService.findUserRemainInfoByChannel(dates, dates1, channel);
									int regUserNum = userService.countSomeDayRegedUserByChannel(THIRTY_DAY_REMAIN_DAYS, channel);
									userRemainByChannelStats.setThirtyDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
									
									userRemainByChannelStats.setThirtyDayRemainNum(remainUsers);
									userRemainByChannelStats.setThirtyDayRegNum(regUserNum);
									
									statsService.save(userRemainByChannelStats);
								} catch (Exception e) {
									e.printStackTrace();
								}
				    		}
				    	}
				    }
		    }
		}
		LogSystem.info("分渠道--用户留存统计Collector完毕");
	}
}


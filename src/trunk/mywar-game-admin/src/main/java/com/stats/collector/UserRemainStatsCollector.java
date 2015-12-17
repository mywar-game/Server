package com.stats.collector;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.admin.util.Tools;
import com.adminTool.service.UserService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.stats.bo.UserRemainStats;
import com.stats.service.UserRemainStatsService;

public class UserRemainStatsCollector implements Collector {
	
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
		LogSystem.info("用户留存统计Collector开始");
		//获得当前服务器的留存数据
		UserRemainStatsService statsService = ServiceCacheFactory.getServiceCache().getService(UserRemainStatsService.class);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		Map<String,UserRemainStats> statsMap = statsService.findNowServerData();
		//昨天的留存信息统计
		Date yesterday = DateUtil.getDiffDate(time, SystemStatsDate.YESTERDAY);
		String yesterdayStr = DateUtil.dateToString(yesterday, DateUtil.LONG_DATE_FORMAT);
		String[] dates = new String[2];
		dates[0] = yesterdayStr+" 00:00:00";
		dates[1] = yesterdayStr+" 23:59:59";
		UserRemainStats yesterdayStats = new UserRemainStats();
		int userCount = userService.countAllUser();//用户总量
		List<Object> newRegList = userService.findCreateUserNumInSomeTime(dates,false);//新注册数
		List<Object> loginList = userLoginLogService.findActiveUserAmount(dates,false);
		int dayActive = 0;//日活跃
		if(loginList!=null && loginList.size()>0){
			dayActive = Integer.valueOf(((Object[])loginList.get(0))[0].toString());
		}
		yesterdayStats.setDayActive(dayActive);
		yesterdayStats.setUserTotal(userCount);
		if(newRegList==null || newRegList.size()==0){
			yesterdayStats.setNewReg(0);
		}else{
			yesterdayStats.setNewReg(Integer.valueOf(((BigInteger)newRegList.get(0)).toString()));
		}
		
		yesterdayStats.setSysNum(CustomerContextHolder.getSystemNum());
		yesterdayStats.setTime(yesterday);
		statsService.save(yesterdayStats);
		
		UserRemainStats userRemainStats;
		//统计2天前那天的的次日留存
		if (statsMap.containsKey(DateUtil.getTime(-ONE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-ONE_DAY_REMAIN_DAYS));
			if(userRemainStats.getOneDayRemain()==null){
				//2天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -ONE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//2天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(ONE_DAY_REMAIN_DAYS);
					userRemainStats.setOneDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计3天前那天的的2日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWO_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWO_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwoDayRemain()==null){
				//3天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWO_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//3天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWO_DAY_REMAIN_DAYS);
					userRemainStats.setTwoDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计4天前那天的的3日留存
		if (statsMap.containsKey(DateUtil.getTime(-THREE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-THREE_DAY_REMAIN_DAYS));
			if(userRemainStats.getThreeDayRemain()==null){
				//4天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -THREE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//4天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(THREE_DAY_REMAIN_DAYS);
					userRemainStats.setThreeDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计5天前那天的的4日留存
		if (statsMap.containsKey(DateUtil.getTime(-FOUR_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-FOUR_DAY_REMAIN_DAYS));
			if(userRemainStats.getFourDayRemain()==null){
				//5天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -FOUR_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//5天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(FOUR_DAY_REMAIN_DAYS);
					userRemainStats.setFourDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计6天前那天的的5日留存
		if (statsMap.containsKey(DateUtil.getTime(-FIVE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-FIVE_DAY_REMAIN_DAYS));
			if(userRemainStats.getFiveDayRemain()==null){
				//6天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -FIVE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//6天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(FIVE_DAY_REMAIN_DAYS);
					userRemainStats.setFiveDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计7天前那天的的6日留存
		if (statsMap.containsKey(DateUtil.getTime(-SIX_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-SIX_DAY_REMAIN_DAYS));
			if(userRemainStats.getSixDayRemain()==null){
				//7天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -SIX_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//7天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(SIX_DAY_REMAIN_DAYS);
					userRemainStats.setSixDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		//统计8天前那天的的7日留存
		if (statsMap.containsKey(DateUtil.getTime(-SEVEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-SEVEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getSevenDayRemain()==null){
				//8天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -SEVEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//8天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(SEVEN_DAY_REMAIN_DAYS);
					userRemainStats.setSevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		/** add **/
		//统计9天前那天的的8日留存
		if (statsMap.containsKey(DateUtil.getTime(-EIGHT_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-EIGHT_DAY_REMAIN_DAYS));
			if(userRemainStats.getEightDayRemain()==null){
				//9天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -EIGHT_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//9天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(EIGHT_DAY_REMAIN_DAYS);
					userRemainStats.setEightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
						
			}
		}
				
		//统计10天前那天的的9日留存
		if (statsMap.containsKey(DateUtil.getTime(-NIGHT_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-NIGHT_DAY_REMAIN_DAYS));
			if(userRemainStats.getNeightDayRemain()==null){
				//10天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -NIGHT_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//10天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(NIGHT_DAY_REMAIN_DAYS);
					userRemainStats.setNeightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
				
		//统计11天前那天的的10日留存
		if (statsMap.containsKey(DateUtil.getTime(-TEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getTenDayRemain()==null){
				//11天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//11天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TEN_DAY_REMAIN_DAYS);
					userRemainStats.setTenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}		
			}
		}
				
		//统计12天前那天的的11日留存
		if (statsMap.containsKey(DateUtil.getTime(-ELEVEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-ELEVEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getElevenDayRemain()==null){
				//12天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -ELEVEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//12天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(ELEVEN_DAY_REMAIN_DAYS);
					userRemainStats.setElevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
						
			}
		}
				
		//统计13天前那天的的12日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWELVE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWELVE_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwelfDayRemain()==null){
				//13天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWELVE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//13天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWELVE_DAY_REMAIN_DAYS);
					userRemainStats.setTwelfDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}		
			}
		}
				
		//统计14天前那天的的13日留存
		if (statsMap.containsKey(DateUtil.getTime(-THIRTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-THIRTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getThirteenDayRemain()==null){
				//14天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -THIRTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//14天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(THIRTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setThirteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}		
			}
		}
				
		//统计15天前那天的的14日留存
		if (statsMap.containsKey(DateUtil.getTime(-FOURTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-FOURTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getFourteenDayRemain()==null){
				//15天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -FOURTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//15天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(FOURTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setFourteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		/** end add **/
				
		//统计16天前那天的的15日留存
		if (statsMap.containsKey(DateUtil.getTime(-FIFTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-FIFTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getFifteenDayRemain()==null){
				//16天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -FIFTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//16天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(FIFTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setFifteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		/** add **/
		//统计17天前那天的的16日留存
		if (statsMap.containsKey(DateUtil.getTime(-SIXTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-SIXTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getSixteenDayRemain()==null){
				//17天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -SIXTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//17天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(SIXTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setSixteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计18天前那天的的17日留存
		if (statsMap.containsKey(DateUtil.getTime(-SEVENTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-SEVENTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getSeventeenDayRemain()==null){
				//18天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -SEVENTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//18天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(SEVENTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setSeventeenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计19天前那天的的18日留存
		if (statsMap.containsKey(DateUtil.getTime(-EIGHTTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-EIGHTTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getEightteenDayRemain()==null){
				//19天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -EIGHTTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//19天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(EIGHTTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setEightteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计20天前那天的的19日留存
		if (statsMap.containsKey(DateUtil.getTime(-NIGHTTEEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-NIGHTTEEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getNeightteenDayRemain()==null){
				//20天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -NIGHTTEEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//20天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(NIGHTTEEN_DAY_REMAIN_DAYS);
					userRemainStats.setNeightteenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计21天前那天的的20日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIE_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentieDayRemain()==null){
				//21天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//21天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIE_DAY_REMAIN_DAYS);
					userRemainStats.setTwentieDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计22天前那天的的21日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIEONE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIEONE_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentieoneDayRemain()==null){
				//22天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIEONE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//22天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIEONE_DAY_REMAIN_DAYS);
					userRemainStats.setTwentieoneDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		//统计23天前那天的的22日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIETWO_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIETWO_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentietwoDayRemain()==null){
				//23天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIETWO_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//23天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIETWO_DAY_REMAIN_DAYS);
					userRemainStats.setTwentietwoDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计24天前那天的的23日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIETHREE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIETHREE_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentiethreeDayRemain()==null){
				//24天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIETHREE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//24天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIETHREE_DAY_REMAIN_DAYS);
					userRemainStats.setTwentiethreeDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计25天前那天的的24日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIEFOUR_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIEFOUR_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentiefourDayRemain()==null){
				//25天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIEFOUR_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//25天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIEFOUR_DAY_REMAIN_DAYS);
					userRemainStats.setTwentiefourDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计26天前那天的的25日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIEFIVE_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIEFIVE_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentiefiveDayRemain()==null){
				//25天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIEFIVE_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//25天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIEFIVE_DAY_REMAIN_DAYS);
					userRemainStats.setTwentiefiveDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计27天前那天的的26日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIESIX_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIESIX_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentiesixDayRemain()==null){
				//27天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIESIX_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//27天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIESIX_DAY_REMAIN_DAYS);
					userRemainStats.setTwentiesixDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计28天前那天的的27日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIESEVEN_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIESEVEN_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentiesevenDayRemain()==null){
				//28天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIESEVEN_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//28天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIESEVEN_DAY_REMAIN_DAYS);
					userRemainStats.setTwentiesevenDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计29天前那天的的28日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIEEIGHT_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIEEIGHT_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentieeightDayRemain()==null){
				//29天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIEEIGHT_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//29天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIEEIGHT_DAY_REMAIN_DAYS);
					userRemainStats.setTwentieeightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		//统计30天前那天的的29日留存
		if (statsMap.containsKey(DateUtil.getTime(-TWENTIENEIGHT_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-TWENTIENEIGHT_DAY_REMAIN_DAYS));
			if(userRemainStats.getTwentienightDayRemain()==null){
				//30天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -TWENTIENEIGHT_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//30天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(TWENTIENEIGHT_DAY_REMAIN_DAYS);
					userRemainStats.setTwentienightDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		/** end add **/
		
		//统计31天前那天的的30日留存
		if (statsMap.containsKey(DateUtil.getTime(-THIRTY_DAY_REMAIN_DAYS))) {
			userRemainStats = statsMap.get(DateUtil.getTime(-THIRTY_DAY_REMAIN_DAYS));
			if(userRemainStats.getThirtyDayRemain()==null){
				//31天前的那天注册并且第昨天登录的人数
				Date regDate = DateUtil.getDiffDate(time, -THIRTY_DAY_REMAIN_DAYS);
				String regDateStr = DateUtil.dateToString(regDate, DateUtil.LONG_DATE_FORMAT);
				String[] dates1 = new String[2];
				dates1[0] = regDateStr+" 00:00:00";
				dates1[1] = regDateStr+" 23:59:59";
				try {
					int remainUsers = userLoginLogService.findUserRemainInfo(dates,dates1);
					//31天前的那天注册的人数
					int regUserNum = userService.countSomeDayRegedUser(THIRTY_DAY_REMAIN_DAYS);
					userRemainStats.setThirtyDayRemain(Float.valueOf(Tools.decimalFormat((double)remainUsers,(double)regUserNum, "#.0000")));
					statsService.save(userRemainStats);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		LogSystem.info("用户留存统计Collector完毕");
	}

}

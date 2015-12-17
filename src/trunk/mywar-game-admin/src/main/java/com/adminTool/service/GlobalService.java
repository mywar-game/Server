package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.GlobalInfo;
import com.adminTool.bo.StatisticsInfo;
import com.adminTool.dao.GlobalInfoDao;
import com.adminTool.dao.StatisticsInfoDao;
import com.dataconfig.service.UserPayService;
import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLogoutLogService;
import com.log.service.UserRegLogService;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class GlobalService {

	private GlobalInfoDao globalInfoDao;
	
	private StatisticsInfoDao statisticsInfoDao;
	
	/**
	 * 选择服务器后的首页数据
	 */
	public void dealGlobalInfo() {
		for (TGameServer gameServer : DataSourceManager.getInstatnce().getGameServerMap().values()) {
			Integer systemNum = gameServer.getServerId();
			
			//设置系统编号 线程变量
			if (systemNum != null) {
				CustomerContextHolder.setSystemNum(systemNum);
			} else {
				continue;
			}
			//各等级区间数据
			String[] dates = new String[2];
			dates[0] = DateUtil.getSomeDaysDiffDateStr(-3);
			dates[1] = DateUtil.getSomeDaysDiffDateStr(0);
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("(CASE WHEN ISNULL(userLevel.nowLevel) THEN 1 ELSE userLevel.nowLevel END) AS level,");
			sql.append("(CASE WHEN ISNULL(login.activeUserId) THEN 0 else 1 end) as isActive,");
			sql.append("(CASE WHEN ISNULL(pay.payUserId) then 0 else 1 end) as ispay ");
			sql.append("FROM `user` u ");
			sql.append("LEFT JOIN (SELECT USER_ID,MAX(`LEVEL`) AS nowLevel FROM user_levelup_log GROUP BY USER_ID) userLevel ON u.USER_ID = userLevel.USER_ID ");
			sql.append("LEFT JOIN (SELECT DISTINCT(USER_ID) AS activeUserId FROM (select USER_ID,LOGIN_TIME from user_login_log union all select USER_ID,LOGIN_TIME from user_login_log_bak) log WHERE log.LOGIN_TIME BETWEEN '").append(dates[0]).append("' AND '").append(dates[1]).append("') login ON u.USER_ID = login.activeUserId ");
			sql.append("LEFT JOIN (SELECT DISTINCT(USER_ID) AS payUserId FROM payment_log) pay ON u.USER_ID = pay.payUserId");
			
			globalInfoDao.closeSession(DBSource.LOG);
			List<Object> list = globalInfoDao.findSQL_(sql.toString());
			//等级区间人数数组，索引为区间
			int[] levelIntervalUserNumArr = new int[12];
			//等级区间活跃人数数组，索引为区间
			int[] levelIntervalActiveArr = new int[12];
			//等级区间付费人数数组，索引为区间
			int[] levelIntervalPayeArr = new int[12];
			//填充上面两个数组
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					//玩家等级
					int level = Integer.valueOf(((Object[]) list.get(i))[0].toString());
					//是否活跃
					boolean isActive = Integer.valueOf(((Object[]) list.get(i))[1].toString()) == 1 ? true:false;
					//是否充值
					boolean isPay = Integer.valueOf(((Object[]) list.get(i))[2].toString()) == 1 ? true:false;
					//等级区间索引（1到10级是0,11-20是1）
					int levelIntervalIndex = (level-1)/10;
					levelIntervalUserNumArr[levelIntervalIndex] += 1;
					if (isActive) {
						levelIntervalActiveArr[levelIntervalIndex] += 1;
					}
					if (isPay) {
						levelIntervalPayeArr[levelIntervalIndex] += 1;
					}
				}
			}
			List<GlobalInfo> globalInfoList = new ArrayList<GlobalInfo>();
			for (int i = 0; i < 10; i++) {
				GlobalInfo globalInfo = new GlobalInfo();
				globalInfo.setSysNum(systemNum);
				globalInfo.setLevel(i);
				globalInfo.setPopulation(levelIntervalUserNumArr[i]);
				globalInfo.setActivePopulation(levelIntervalActiveArr[i]);
				globalInfo.setPayPopulation(levelIntervalPayeArr[i]);
				globalInfoList.add(globalInfo);
			}
			this.deleteAllGlobalInfo(systemNum);
			// 各等级区间数据
			this.saveAllGlobalInfo(globalInfoList);
			
			//世界总览信息
			UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
			UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
			UserLogoutLogService userLogoutLogService = ServiceCacheFactory.getServiceCache().getService(UserLogoutLogService.class);
			int count = userPayService.getPayAmount(null);
//			
			StatisticsInfo statisticsInfo = new StatisticsInfo();
			// 总充值数
			statisticsInfo.setTotalPayNum(count);
			// 总金币数
//			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_DIAMOND_AMOUNT, new CommomMsgBody(), ResGetDiamondAmount.class);
//			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
//				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
//				LogSystem.warn(commomMsgBody.getErrorDescription()+" 获得游戏现有金币数失败！");
//				statisticsInfo.setTotalGoldenNum(0L);
//			} else {
//				ResGetDiamondAmount resGetDiamondAmount = (ResGetDiamondAmount) msg.getMsgBody();
//				statisticsInfo.setTotalGoldenNum(Long.valueOf(resGetDiamondAmount.getServerDiamondAmount()));
//			}
			statisticsInfo.setTotalGoldenNum(0l);//游戏现有金币数量---需要从游戏服务器查询
			statisticsInfo.setTotalRegisterNum(userRegLogService.findTotalRegUserNum());
			List<Object> onlineList = userLogoutLogService.getOnlineData();//在线数据
			if(onlineList!=null && onlineList.size()>0){
				Object[] arr = (Object[])onlineList.get(0);
				int onlineLiveMinutes = Integer.valueOf(arr[0].toString());//总在线时长
				int onlineUserNum = Integer.valueOf(arr[1].toString());//总在线人数
				statisticsInfo.setAverageOnlineTime(onlineUserNum==0?0:onlineLiveMinutes/onlineUserNum);
				statisticsInfo.setTotalOnlineTime(onlineLiveMinutes);
			}else{
				statisticsInfo.setAverageOnlineTime(0);
				statisticsInfo.setTotalOnlineTime(0);
			}
			statisticsInfo.setSysNum(systemNum);
			this.deleteAllStatisticsInfo(systemNum);
			this.saveAllStatisticsInfo(statisticsInfo);
		}
	}
	
	public List<GlobalInfo> findAllGlobalInfoBySysNum(int sysNum) {
		globalInfoDao.closeSession(DBSource.ADMIN);
		String str = "from GlobalInfo where sysNum = " + sysNum;
		return globalInfoDao.find(str, DBSource.ADMIN);
	}

	public List<StatisticsInfo> findAllStatisticsInfoBySysNum(int sysNum) {
		statisticsInfoDao.closeSession(DBSource.ADMIN);
		String str = "from StatisticsInfo where sysNum = " + sysNum;
		return statisticsInfoDao.find(str, DBSource.ADMIN);
	}

	public void saveAllGlobalInfo(List<GlobalInfo> globalInfoList) {
		globalInfoDao.saveBatch(globalInfoList, DBSource.ADMIN);
	}

	public void saveAllStatisticsInfo(StatisticsInfo statisticsInfo) {
		statisticsInfoDao.save(statisticsInfo, DBSource.ADMIN);
	}

	public void deleteAllGlobalInfo(Integer sysNum) {
		globalInfoDao.closeSession(DBSource.ADMIN);
		String str = "delete from GlobalInfo where sysNum="+sysNum;
		globalInfoDao.execute(str);
	}

	public void deleteAllStatisticsInfo(Integer sysNum) {
		statisticsInfoDao.closeSession(DBSource.ADMIN);
		String str = "delete from StatisticsInfo where sysNum="+sysNum;
		statisticsInfoDao.execute(str);
	}

	public void setGlobalInfoDao(GlobalInfoDao globalInfoDao) {
		this.globalInfoDao = globalInfoDao;
	}

	public GlobalInfoDao getGlobalInfoDao() {
		return globalInfoDao;
	}

	public void setStatisticsInfoDao(StatisticsInfoDao statisticsInfoDao) {
		this.statisticsInfoDao = statisticsInfoDao;
	}

	public StatisticsInfoDao getStatisticsInfoDao() {
		return statisticsInfoDao;
	}

}

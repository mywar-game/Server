package com.stats.action;

import java.util.Date;
import java.util.Map;

import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.util.DateUtil;
import com.stats.recollector.ReHomePageStatsCollector;
import com.stats.recollector.ReLevelStatsCollector;
import com.stats.recollector.ReLoginStatsCollector;
import com.stats.recollector.ReNormalStatsDataCollector;
import com.stats.recollector.RePayIntervalStatsCollector;
import com.stats.recollector.RePayUserInfoStatsCollector;
import com.stats.recollector.RePlunderStatsCollector;
import com.stats.recollector.ReUserCopperStatsCollector;
import com.stats.recollector.ReUserDiamondStatsCollector;
import com.stats.recollector.ReUserEquipStatsCollector;
import com.stats.recollector.ReUserEverydayLoginStatsCollector;
import com.stats.recollector.ReUserLevelStatsCollector;
import com.stats.recollector.ReUserLoginDrawStatsCollector;
import com.stats.recollector.ReUserMallRankStatsCollector;
import com.stats.recollector.ReUserNodeLossStatsCollector;
import com.stats.recollector.ReUserOnlineStatsCollector;
import com.stats.recollector.ReUserRemainByIpStatsCollector;
import com.stats.recollector.ReUserRemainStatsCollector;
import com.stats.recollector.ReUserTimeLossStatsCollector;
import com.stats.recollector.ReUserVipStatsCollector;
import com.stats.recollector.ReZhengbaCollector;
import com.stats.recollector.RechargeStatsCollector;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class ReCollector extends ALDAdminActionSupport {

	private static final long serialVersionUID = 4017016903908868763L;
	private String isCommit = "F";
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	private String className;
	private String reCollectorTime;

	@Override
	public String execute() {
		
		if (isCommit.equalsIgnoreCase("T") || isCommit == "T") {
			Map<Integer, TGameServer> map = DataSourceManager.getInstatnce().getGameServerMap();
			Date date = new Date();
			int index = 0;
//			for (TGameServer server : map.values()) {
//				CustomerContextHolder.setSystemNum(server.getServerId());
//				LogSystem.info("serverId:"+server.getServerId());
//				LogSystem.info("管理后台的时间：" + date.toString());
//				Date nowServerTime = DateUtil.getNowDateBySystemNum();
//				LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
//				
//			}
			
			// 不好的做法
			if (className.equals("plunder") || className == "plunder") {
				RePlunderStatsCollector rePlunderStatsCollector = new RePlunderStatsCollector();
				rePlunderStatsCollector.execute(reCollectorTime, index);
			} else if (className.equals("zhengba") || className == "zhengba") {
				ReZhengbaCollector reZhengbaStatsCollector = new ReZhengbaCollector();
				reZhengbaStatsCollector.execute(reCollectorTime, index);
			} else if (className.equals("userDiamond") || className == "userDiamond") {
				ReUserDiamondStatsCollector reUserDiamondStatsCollector = new ReUserDiamondStatsCollector();
				reUserDiamondStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userCopper") || className == "userCopper") {
				ReUserCopperStatsCollector reUserCopperStatsCollector = new ReUserCopperStatsCollector();
				reUserCopperStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userEquip") || className == "userEquip") {
				ReUserEquipStatsCollector reUserEquipStatsCollector = new ReUserEquipStatsCollector();
				reUserEquipStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userVip") || className == "userVip") {
				ReUserVipStatsCollector reUserVipStatsCollector = new ReUserVipStatsCollector();
				reUserVipStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userTimeLoss") || className == "userTimeLoss") {
				ReUserTimeLossStatsCollector reUserTimeLossStatsCollector = new ReUserTimeLossStatsCollector();
				reUserTimeLossStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userNodeLoss") || className == "userNodeLoss") {
				ReUserNodeLossStatsCollector reUserNodeLossStatsCollector = new ReUserNodeLossStatsCollector();
				reUserNodeLossStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userOnline") || className == "userOnline") {
				ReUserOnlineStatsCollector reUserOnlineStatsCollector = new ReUserOnlineStatsCollector();
				reUserOnlineStatsCollector.execute(reCollectorTime);
			} else if (className.equals("userLoginDraw") || className == "userLoginDraw") {
				ReUserLoginDrawStatsCollector reUserLoginDrawStatsCollector = new ReUserLoginDrawStatsCollector();
				reUserLoginDrawStatsCollector.execute(reCollectorTime);
			} else if (className.equals("payUserInfoStatsCollector") || className == "payUserInfoStatsCollector") {
				RePayUserInfoStatsCollector rePayUserInfoStatsCollector = new RePayUserInfoStatsCollector();
				rePayUserInfoStatsCollector.execute(reCollectorTime);
			} else if (className.equals("reUserRemainByIpStatsCollector") || className == "reUserRemainByIpStatsCollector") {
				ReUserRemainByIpStatsCollector reUserRemainByIpStatsCollector = new ReUserRemainByIpStatsCollector();
				reUserRemainByIpStatsCollector.execute(reCollectorTime);
			} else if (className.equals("reNormalStatsDataCollector") || className == "reNormalStatsDataCollector") {
				ReNormalStatsDataCollector reNormalStatsDataCollector = new ReNormalStatsDataCollector();
				reNormalStatsDataCollector.execute(reCollectorTime);
			} else if (className.equals("rechargeStatsCollector") || className == "rechargeStatsCollector") {
				RechargeStatsCollector rechargeStatsCollector = new RechargeStatsCollector();
				rechargeStatsCollector.execute(reCollectorTime);
			} else if (className.equals("reUserMallRankStatsCollector") || className == "reUserMallRankStatsCollector") {
				ReUserMallRankStatsCollector reUserMallRankStatsCollector = new ReUserMallRankStatsCollector();
				reUserMallRankStatsCollector.execute(reCollectorTime);
			} else if (className.equals("rePayIntervalStatsCollector") || className == "rePayIntervalStatsCollector") {
				RePayIntervalStatsCollector rePayIntervalStatsCollector = new RePayIntervalStatsCollector();
				rePayIntervalStatsCollector.execute();
			} else if (className.equals("reUserRemainCollector") || className == "reUserRemainCollector") {
				ReUserRemainStatsCollector reUserRemainStatsCollector = new ReUserRemainStatsCollector();
				reUserRemainStatsCollector.execute(reCollectorTime);
			} else if (className.equals("reUserEverydayLoginStatsCollector") || className == "reUserEverydayLoginStatsCollector") {
				ReUserEverydayLoginStatsCollector c = new ReUserEverydayLoginStatsCollector();
				c.execute(reCollectorTime);
			} else if (className.equals("reUserLevelStatsCollector") || className == "reUserLevelStatsCollector") {
				ReUserLevelStatsCollector c = new ReUserLevelStatsCollector();
				c.execute(reCollectorTime);
			} else if (className.equals("reHomePageStatsCollector") || className == "reHomePageStatsCollector") {
				ReHomePageStatsCollector c = new ReHomePageStatsCollector();
				c.execute(reCollectorTime);
			}
			
			else {
				isCommit = "NOTFOUND";
			}
			index ++;
		}
		
		return SUCCESS;
	}
	

	public String getReCollectorTime() {
		return reCollectorTime;
	}

	public void setReCollectorTime(String reCollectorTime) {
		this.reCollectorTime = reCollectorTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}

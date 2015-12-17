package com.adminTool.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adminTool.bo.Activity;
import com.adminTool.bo.ActivityDrawConfig;
import com.adminTool.bo.ActivityDrawGoods;
import com.adminTool.bo.ActivityDrawLevelGoods;
import com.adminTool.bo.ActivityDrawPos;
import com.adminTool.bo.ActivityTaskReward;
import com.adminTool.bo.GiftbagDropTool;
import com.adminTool.bo.OncePayReward;
import com.adminTool.bo.System30LoginReward;
import com.adminTool.bo.System7LoginReward;
import com.adminTool.bo.SystemAccumLoginReward;
import com.adminTool.bo.SystemActivityCopyConditionDrop;
import com.adminTool.bo.SystemActivityCopyConfig;
import com.adminTool.bo.SystemActivityScratchReward;
import com.adminTool.bo.SystemDayTotalPayReward;
import com.adminTool.bo.SystemDiamondcat;
import com.adminTool.bo.SystemGrowthPlanReward;
import com.adminTool.bo.SystemLoginDraw;
import com.adminTool.bo.SystemReceivePower;
import com.adminTool.bo.SystemTotalConsumeReward;
import com.adminTool.bo.SystemXianshiMall;
import com.adminTool.bo.ToolExchange;
import com.adminTool.bo.TotalDayPayReward;
import com.adminTool.bo.TotalPayReward;
import com.adminTool.service.ActivityDrawConfigService;
import com.adminTool.service.ActivityDrawGoodsService;
import com.adminTool.service.ActivityDrawLevelGoodsService;
import com.adminTool.service.ActivityDrawPosService;
import com.adminTool.service.ActivityService;
import com.adminTool.service.ActivityTaskRewardService;
import com.adminTool.service.OncePayRewardService;
import com.adminTool.service.System30LoginRewardService;
import com.adminTool.service.System7LoginRewardService;
import com.adminTool.service.SystemAccumLoginRewardService;
import com.adminTool.service.SystemActivityCopyConditionDropService;
import com.adminTool.service.SystemActivityCopyConfigService;
import com.adminTool.service.SystemActivityScratchRewardService;
import com.adminTool.service.SystemDayTotalPayRewardService;
import com.adminTool.service.SystemGrowthPlanRewardService;
import com.adminTool.service.SystemLoginDrawService;
import com.adminTool.service.SystemReceivePowerService;
import com.adminTool.service.SystemTotalConsumeRewardService;
import com.adminTool.service.SystemXianshiMallService;
import com.adminTool.service.ToolExchangeService;
import com.adminTool.service.TotalDayPayRewardService;
import com.adminTool.service.TotalPayRewardService;
import com.adminTool.service.UpdateDropToolService;
import com.adminTool.service.UpdateSystemDiamondcatService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class SynActivityList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 5165132544807486990L;
	private int activityId = -1;
	private String responseMsg = "";
	private List<Activity> activityList;
	private String serverIds;
	private List<Integer> serverIdsList = new ArrayList<Integer>();
	private Integer sysNum;
	private Set<Integer> serverIdsSet = new HashSet<Integer>();
	private Set<Integer> successServerIdsSet = new HashSet<Integer>();
	private String failIds = "";
	private String successIds = "";


	public String execute() {
		
		responseMsg = "success";
		if (activityId == -1) {
			responseMsg = "no";
		} else {
			if (!serverIds.equals("") && serverIds != null) {
				String[] serverArr = serverIds.split(",");
				for (int i = 0; i < serverArr.length; i++) {
					serverIdsList.add(Integer.valueOf(serverArr[i]));
				}
			}
			sysNum = CustomerContextHolder.getSystemNum(); // 保存当前
			System.out.println("当前服务器ID：" + sysNum);
			handleSynActivity(activityId);
			for (Integer s : serverIdsSet) {
				failIds += s + ",";
			}
			for (Integer s : successServerIdsSet) {
				successIds += s + ",";
			}
		}

		ActivityService activityService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityService.class);
		IPage<Activity> iPage = activityService.findActivityPageList(
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if (iPage == null) {
			activityList = new ArrayList<Activity>();
			return SUCCESS;
		}

		activityList = (List<Activity>) iPage.getData();
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}
	
	public void handleSynActivity(int activityId) {
		
		updateActivity();

		// 恢复当前服务器
		CustomerContextHolder.setSystemNum(sysNum);
		ActivityService activityService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityService.class);
		Activity act = activityService.findOneActivity(activityId);
		int activityType = act.getActivityType();
		
		switch (activityType) {
		case 1:
			// 30日累积登陆
			system30LoginReward();
			break;
		case 2:
			// 7日连续登陆
			system7LoginReward();
			break;
		case 3:
			// 累积充值模块
			totalPayReward();
			break;
		case 4:
			// 单笔充值模块
			oncePayReward();
			break;
		case 5:
			// 道具兑换
			toolExchange();
			break;
		case 6:
			// 充值累积天数模块 
			totalDayPay();
			break;
		case 7:
			// 体力恢复
			receivePower();
			break;
		case 8:
			// 活跃度
			activityTask();
			break;
		case 9:
			// 在线奖励 
			updateDropTool();
			break;
		case 10:
			// 礼包码 
			break;
		case 11:
			// 首冲 
			break;
		case 18:
			// 此地无银，金角银角，战争乱入等活动
			systemCopy();
			break;
		case 19:
			// 抽奖
			drawGoods();
			break;			
		case 21:
			// 登陆抽奖活动
			loginDraw();
			break;
		case 25:
			// 神秘商店 
			break;	
		case 30:
			// 坐享其成
			growthPlan();
			break;
		case 31:
			// 限时招募 
			break;
		case 32:
			// 刮刮乐
			scratchReward();
			break;
		case 33:
			// 累积消费
			totalConsume();
			break;	
		case 34:
			// vip每日领取奖励
			break;
		case 38:
			// 招财猫
			systemDiamondcat();
			break;
		case 39:
			// 当日累积充值
			systemDayTotalPayReward();
			break;
		case 40:
			// 限时购买
			systemXianshiMall();
			break;
		case 41:
			// 累计登陆
			systemMidAutumn();
			break;
		case 100:
			// 普通副本N倍开启，精英副本N倍开启，活动副本N倍开启
			break;
		default:
			// responseMsg = "fail";
			break;
		}
	}
	
	public Map<Integer, TGameServer> getGameServerMap() {
		Map<Integer, TGameServer> map = DataSourceManager.getInstatnce().getGameServerMap();
		return map;
	}
	
	// 更新该活动
	public void updateActivity() {
		
		// 读取活动配置
		ActivityService activityService = ServiceCacheFactory.getServiceCache().getService(ActivityService.class);
		Activity activity;
		try {
			activity = activityService.findOneActivity(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		Map<Integer, TGameServer> map = this.getGameServerMap();
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			Activity tempActivity = activityService.findOneActivity(activityId);
			
			if (activity == null) {
				System.out.println("");
			} else {
				if (tempActivity != null) {
					if (!tempActivity.getActivityType().equals(activity.getActivityType())) {
						// 不同类型，则不进行同步
						serverIdsSet.add(server.getServerId());
						continue;
					}
				}
				
				try {
					activityService.delActivity(activityId);
					activityService.saveActivity(activity); // 更新该条记录
					successServerIdsSet.add(server.getServerId());
				} catch (Exception e) {
					e.printStackTrace();
					serverIdsSet.add(server.getServerId());
				} 
			}
		}
	}
	
	public void systemDiamondcat() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		UpdateSystemDiamondcatService service = ServiceCacheFactory.getServiceCache().getService(UpdateSystemDiamondcatService.class);
		List<SystemDiamondcat> list = new ArrayList<SystemDiamondcat>();
		try {
			list = service.getList(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				service.synDeleteAll(activityId);
				// 保存
				service.synSaveAll(list);
				// 成功
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void systemXianshiMall() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemXianshiMallService service = ServiceCacheFactory.getServiceCache().getService(SystemXianshiMallService.class);
		List<SystemXianshiMall> list = new ArrayList<SystemXianshiMall>();
		try {
			list = service.getList(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				service.synDeleteAll(activityId);
				// 保存
				service.synSaveAll(list);
				// 成功
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void system30LoginReward() {
		
		Map<Integer, TGameServer> map = this.getGameServerMap();
		System30LoginRewardService system30LoginRewardService = ServiceCacheFactory.getServiceCache().getService(System30LoginRewardService.class);
		List<System30LoginReward> system30LoginRewardList = new ArrayList<System30LoginReward>();
		try {
			system30LoginRewardList = system30LoginRewardService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				system30LoginRewardService.synDeleteAll();
				// 保存
				system30LoginRewardService.synSaveAll(system30LoginRewardList);
				// 成功
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void system7LoginReward() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		System7LoginRewardService system7LoginRewardService = ServiceCacheFactory.getServiceCache().getService(System7LoginRewardService.class);
		List<System7LoginReward> system7LoginRewardList = new ArrayList<System7LoginReward>();
		try {
			system7LoginRewardList = system7LoginRewardService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				system7LoginRewardService.synDeleteAll();
				// 保存
				system7LoginRewardService.synSaveAll(system7LoginRewardList);
				
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void totalPayReward() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		TotalPayRewardService totalPayRewardService = ServiceCacheFactory.getServiceCache().getService(TotalPayRewardService.class);
		List<TotalPayReward> totalPayRewardList = new ArrayList<TotalPayReward>();
		try {
			totalPayRewardList = totalPayRewardService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				totalPayRewardService.synDeleteAll(activityId);
				// 保存
				totalPayRewardService.synSaveAll(totalPayRewardList);
				
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void oncePayReward() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		OncePayRewardService oncePayRewardService = ServiceCacheFactory.getServiceCache().getService(OncePayRewardService.class);
		List<OncePayReward> oncePayRewardList = new ArrayList<OncePayReward>();
		try {
			oncePayRewardList = oncePayRewardService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				oncePayRewardService.synDeleteAll(activityId);
				// 保存
				oncePayRewardService.synSaveAll(oncePayRewardList);
				
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void systemDayTotalPayReward() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemDayTotalPayRewardService systemDayTotalPayRewardService = ServiceCacheFactory.getServiceCache().getService(SystemDayTotalPayRewardService.class);
		List<SystemDayTotalPayReward> list = new ArrayList<SystemDayTotalPayReward>();
		try {
			list = systemDayTotalPayRewardService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemDayTotalPayRewardService.synDeleteAll(activityId);
				// 保存
				systemDayTotalPayRewardService.synSaveAll(list);
			
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void toolExchange() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		ToolExchangeService toolExchangeService = ServiceCacheFactory.getServiceCache().getService(ToolExchangeService.class);
		List<ToolExchange> toolExchangeList = new ArrayList<ToolExchange>();
		try {
			toolExchangeList = toolExchangeService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				toolExchangeService.synDeleteAll(activityId);
				// 保存
				toolExchangeService.synSaveAll(toolExchangeList);
			
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void totalDayPay() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		TotalDayPayRewardService totalDayPayRewardService = ServiceCacheFactory.getServiceCache().getService(TotalDayPayRewardService.class);
		List<TotalDayPayReward> totalDayPayRewardList = new ArrayList<TotalDayPayReward>();
		try {
			totalDayPayRewardList = totalDayPayRewardService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				
				// 先删除
				totalDayPayRewardService.synDeleteAll(activityId);
				// 保存
				totalDayPayRewardService.synSaveAll(totalDayPayRewardList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	// 体力恢复
	public void receivePower() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemReceivePowerService systemReceivePowerService = ServiceCacheFactory.getServiceCache().getService(SystemReceivePowerService.class);
		List<SystemReceivePower> systemReceivePowerList = new ArrayList<SystemReceivePower>();
		try {
			systemReceivePowerList = systemReceivePowerService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemReceivePowerService.synDeleteAll();
				// 保存
				systemReceivePowerService.synSaveAll(systemReceivePowerList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	// 活跃度
	public void activityTask() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		ActivityTaskRewardService activityTaskRewardService = ServiceCacheFactory.getServiceCache().getService(ActivityTaskRewardService.class);
		List<ActivityTaskReward> activityTaskRewardList = new ArrayList<ActivityTaskReward>();
		
		try {
			activityTaskRewardList = activityTaskRewardService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				activityTaskRewardService.synDeleteAll();
				// 保存
				activityTaskRewardService.synSaveAll(activityTaskRewardList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
		
	}
	
	// 在线奖励
	public void updateDropTool() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		UpdateDropToolService updateDropToolService = ServiceCacheFactory.getServiceCache().getService(UpdateDropToolService.class);
		List<GiftbagDropTool> giftbagDropToolList = new ArrayList<GiftbagDropTool>();
		
		try {
			giftbagDropToolList = updateDropToolService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				updateDropToolService.synDeleteAll();
				// 保存
				updateDropToolService.synSaveAll(giftbagDropToolList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
		
	}
	
	// 登陆抽奖活动
	public void loginDraw() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemLoginDrawService systemLoginDrawService = ServiceCacheFactory.getServiceCache().getService(SystemLoginDrawService.class);
		List<SystemLoginDraw> systemLoginDrawList = new ArrayList<SystemLoginDraw>();
		
		try {
			systemLoginDrawList = systemLoginDrawService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemLoginDrawService.synDeleteAll();
				// 保存
				systemLoginDrawService.synSaveAll(systemLoginDrawList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	// 坐享其成
	public void growthPlan() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemGrowthPlanRewardService systemGrowthPlanRewardService = ServiceCacheFactory.getServiceCache().getService(SystemGrowthPlanRewardService.class);
		List<SystemGrowthPlanReward> systemGrowthPlanRewardList = new ArrayList<SystemGrowthPlanReward>();
		try {
			systemGrowthPlanRewardList = systemGrowthPlanRewardService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemGrowthPlanRewardService.synDeleteAll();
				// 保存
				systemGrowthPlanRewardService.synSaveAll(systemGrowthPlanRewardList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	// 刮刮乐
	public void scratchReward() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemActivityScratchRewardService systemActivityScratchRewardService = ServiceCacheFactory.getServiceCache().getService(SystemActivityScratchRewardService.class);
		List<SystemActivityScratchReward> systemActivityScratchRewardList = new ArrayList<SystemActivityScratchReward>();
		try {
			systemActivityScratchRewardList = systemActivityScratchRewardService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemActivityScratchRewardService.synDeleteAll(activityId);
				// 保存
				systemActivityScratchRewardService.synSaveAll(systemActivityScratchRewardList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	// 累积消费
	public void totalConsume() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemTotalConsumeRewardService systemTotalConsumeRewardService = ServiceCacheFactory.getServiceCache().getService(SystemTotalConsumeRewardService.class);
		List<SystemTotalConsumeReward> systemTotalConsumeRewardList = new ArrayList<SystemTotalConsumeReward>();
		try {
			systemTotalConsumeRewardList = systemTotalConsumeRewardService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemTotalConsumeRewardService.synDeleteAll(activityId);
				// 保存
				systemTotalConsumeRewardService.synSaveAll(systemTotalConsumeRewardList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void systemCopy() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemActivityCopyConfigService systemActivityCopyConfigService = ServiceCacheFactory.getServiceCache().getService(SystemActivityCopyConfigService.class);
		SystemActivityCopyConditionDropService systemActivityCopyConditionDropService = ServiceCacheFactory.getServiceCache().getService(SystemActivityCopyConditionDropService.class);
		List<SystemActivityCopyConfig> systemActivityCopyConfigList = new ArrayList<SystemActivityCopyConfig>();
		List<SystemActivityCopyConditionDrop> systemActivityCopyConditionDropList = new ArrayList<SystemActivityCopyConditionDrop>();
		
		try {
			systemActivityCopyConfigList = systemActivityCopyConfigService.findAll(activityId);
			systemActivityCopyConditionDropList = systemActivityCopyConditionDropService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				systemActivityCopyConfigService.synDeleteAll(activityId);
				systemActivityCopyConditionDropService.synDeleteAll(activityId);
				
				// 保存
				systemActivityCopyConfigService.synSaveAll(systemActivityCopyConfigList);
				systemActivityCopyConditionDropService.synSaveAll(systemActivityCopyConditionDropList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	// 抽奖
	public void drawGoods() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		ActivityDrawPosService activityDrawPosService = ServiceCacheFactory.getServiceCache().getService(ActivityDrawPosService.class);
		ActivityDrawConfigService activityDrawConfigService = ServiceCacheFactory.getServiceCache().getService(ActivityDrawConfigService.class);
		ActivityDrawGoodsService activityDrawGoodsService = ServiceCacheFactory.getServiceCache().getService(ActivityDrawGoodsService.class);
		ActivityDrawLevelGoodsService activityDrawLevelGoodsService = ServiceCacheFactory.getServiceCache().getService(ActivityDrawLevelGoodsService.class);
		
		List<ActivityDrawPos> activityDrawPosList = new ArrayList<ActivityDrawPos>();
		List<ActivityDrawConfig> activityDrawConfigList = new ArrayList<ActivityDrawConfig>();
		List<ActivityDrawGoods> activityDrawGoodsList = new ArrayList<ActivityDrawGoods>();
		List<ActivityDrawLevelGoods> activityDrawLevelGoodsList = new ArrayList<ActivityDrawLevelGoods>();
		
		try {
			activityDrawPosList = activityDrawPosService.findAll(activityId);
			activityDrawConfigList = activityDrawConfigService.findAll(activityId);
			activityDrawGoodsList = activityDrawGoodsService.findAll(activityId);
			activityDrawLevelGoodsList = activityDrawLevelGoodsService.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				activityDrawPosService.synDeleteAll(activityId);
				activityDrawConfigService.synDeleteAll(activityId);
				activityDrawGoodsService.synDeleteAll(activityId);
				activityDrawLevelGoodsService.synDeleteAll(activityId);
				
				// 保存
				activityDrawPosService.synSaveAll(activityDrawPosList);
				activityDrawConfigService.synSaveAll(activityDrawConfigList);
				activityDrawGoodsService.synSaveAll(activityDrawGoodsList);
				activityDrawLevelGoodsService.synSaveAll(activityDrawLevelGoodsList);

				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	public void systemMidAutumn() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		SystemAccumLoginRewardService service = ServiceCacheFactory.getServiceCache().getService(SystemAccumLoginRewardService.class);
		List<SystemAccumLoginReward> list = new ArrayList<SystemAccumLoginReward>();
		try {
			list = service.findAll(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			serverIdsSet.addAll(serverIdsList);
			return;
		}
		Date date = new Date();
		for (TGameServer server : map.values()) {
			if (!serverIdsList.contains(server.getServerId())) {
				continue;
			}
			if (serverIdsSet.contains(server.getServerId())) {
				// 抛出异常的服不进行同步
				continue;
			}
			CustomerContextHolder.setSystemNum(server.getServerId());
			LogSystem.info("serverId: " + server.getServerId());
			LogSystem.info("管理后台的时间：" + date.toString());
			Date nowServerTime = DateUtil.getNowDateBySystemNum();
			LogSystem.info("游戏服务器时间：" + nowServerTime.toString());
			try {
				// 先删除
				service.synDeleteAll(activityId);
				// 保存
				service.synSaveAll(list);
				// 成功
				successServerIdsSet.add(server.getServerId());
			} catch (Exception e) {
				e.printStackTrace();
				serverIdsSet.add(server.getServerId());
			}
		}
	}
	
	
	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public Set<Integer> getServerIdsSet() {
		return serverIdsSet;
	}

	public void setServerIdsSet(Set<Integer> serverIdsSet) {
		this.serverIdsSet = serverIdsSet;
	}

	public String getServerIds() {
		return serverIds;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public Set<Integer> getSuccessServerIdsSet() {
		return successServerIdsSet;
	}

	public void setSuccessServerIdsSet(Set<Integer> successServerIdsSet) {
		this.successServerIdsSet = successServerIdsSet;
	}

	
	public String getFailIds() {
		return failIds;
	}

	public void setFailIds(String failIds) {
		this.failIds = failIds;
	}

	public String getSuccessIds() {
		return successIds;
	}

	public void setSuccessIds(String successIds) {
		this.successIds = successIds;
	}
}

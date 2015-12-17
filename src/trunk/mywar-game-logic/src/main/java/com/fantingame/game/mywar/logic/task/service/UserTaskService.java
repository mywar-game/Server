package com.fantingame.game.mywar.logic.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.NumberUtils;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.RandomUtils;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.task.TaskAction_getUserDailyTaskInfoRes;
import com.fantingame.game.msgbody.client.task.TaskAction_receiveTaskRes;
import com.fantingame.game.msgbody.client.task.UserDailyTaskInfoBO;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.msgbody.notify.task.Task_updateNotify;
import com.fantingame.game.msgbody.notify.task.UserTaskBO;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.service.ActivityService;
import com.fantingame.game.mywar.logic.charge.dao.PaymentLogDao;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.fantingame.game.mywar.logic.equip.service.EquipService;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;
import com.fantingame.game.mywar.logic.gemstone.service.GemstoneService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.dao.UserDailyTaskInfoDao;
import com.fantingame.game.mywar.logic.task.dao.UserDailyTaskListDao;
import com.fantingame.game.mywar.logic.task.dao.UserTaskDao;
import com.fantingame.game.mywar.logic.task.dao.cache.SystemDailyTaskDaoCache;
import com.fantingame.game.mywar.logic.task.dao.cache.SystemTaskDaoCache;
import com.fantingame.game.mywar.logic.task.helper.TaskHelper;
import com.fantingame.game.mywar.logic.task.model.SystemDailyTask;
import com.fantingame.game.mywar.logic.task.model.SystemTask;
import com.fantingame.game.mywar.logic.task.model.UserDailyTaskInfo;
import com.fantingame.game.mywar.logic.task.model.UserDailyTaskList;
import com.fantingame.game.mywar.logic.task.model.UserTask;
import com.fantingame.game.mywar.logic.tool.constant.ToolType;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserTaskService implements ModuleEventHandler{
	/**
	 * 领取任务 用户等级不足
	 */
	public final static int ADD_TASK_LEVEL_LIMIT = 2001;
	/**
	 * 领取任务 任务已失效
	 */
	public final static int ADD_TASK_TASK_ISAVALIAD = 2002;
	/**
	 * 已领取了的完成
	 */
	public final static int ADD_TASK_HAVE_GET = 2003;
	/**
	 * 没有日常任务次数
	 */
	public final static int NO_DAILY_TIMES = 2004;
	
	/**
	 * 只能领取一次日常任务
	 */
	public final static int ONLY_REVEIVE_ONCE = 2005;
	
	/**
	 * 领任务奖励--未完成
	 */
	public final static int TASK_RECEIVE_NOT_FINISH = 2001;

	/**
	 * 领任务奖励--已经领取
	 */
	public final static int TASK_RECEIVE_HAS_RECEIVE = 2002;
	
	/**
	 * 任务道具不足
	 */
	public final static int TASK_TOOL_NOT_ENOUGH = 2003;
	/**
	 * 可领取
	 */
	 public final static int TASK_STATUS_NEW = 0;

	/**
	 * 已领取
	 */
	public final static int TASK_STATUS_HAVE_GET = 1;
	/**
	 * 已经完成
	 */
	public final static int TASK_STATUS_FINISH = 2;

	/**
	 * 已经领取奖励
	 */
	public final static int TASK_STATUS_DRAWED = 3;

	/**
	 * 已经完成(逻辑删除)
	 */
//	public final static int TASK_STATUS_DEL = 4;

	/**
	 * 表示所有
	 */
	public final static int TASK_STATUS_ALL = 100;
	/**
	 * 主线任务
	 */
	public final static int MAIN_TASK = 1;

	/**
	 * 支线任务
	 */
	public final static int BRANCH_TASK = 2;
	
	/**
	 * 每日任务
	 */
	public final static int DAILY_TASK = 3;
	
	/**
	 * 推送-增加
	 */
	public final static int PUSH_TYPE_ADD = 1;
	/**
	 * 推送-更新
	 */
	public final static int PUSH_TYPE_UPDATE = 2;
	/**
	 * 推送-删除
	 */
	public final static int PUSH_TYPE_DELETE = 3;

	@Autowired
	private SystemTaskDaoCache systemTaskDao;
	@Autowired
	private UserTaskDao userTaskDao;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentLogDao paymentLogDao;
	@Autowired
	private UserDailyTaskInfoDao userDailyTaskInfoDao;
	@Autowired
	private SystemDailyTaskDaoCache systemDailyTaskDaoCache;
	@Autowired
	private ConfigDataDaoCacheImpl configDateDao;
	@Autowired
	private UserDailyTaskListDao userDailyTaskListDao;
	@Autowired
	private ToolService toolService;
	@Autowired
	private EquipService equipService;
	@Autowired
	private GemstoneService gemstoneService;
	@Autowired
	private ActivityService activityService;
	
    /**
     * 开启任务
     * 
     * @param userId        
     * @param finishedSystemTaskId  完成的任务id
     * @param isNotify  是否广播
     */
	public void openAfterTask(String userId, int finishedSystemTaskId, boolean isNotify) {
		User role = userService.getByUserId(userId);
		List<SystemTask> listTask = systemTaskDao.getPreSystemTask(MAIN_TASK, finishedSystemTaskId, role.getCamp());
		if(listTask != null && listTask.size() > 0) {
			List<UserTask> userTaskList = new ArrayList<UserTask>();
			List<UserTaskBO> userTaskBOList = Lists.newArrayList();
			for(SystemTask systemTask : listTask) {
				int status = TASK_STATUS_NEW;
				UserTask userTask = createUserTask(userId, status, systemTask);
				userTaskList.add(userTask);
				userTaskBOList.add(creatUserTaskBO(userTask, 0));
			}
			
			if (userTaskList.size() > 0)
				userTaskDao.add(userTaskList);
			
			//广播任务更新
			if (isNotify) {
				this.notifyTaskUpdate(userId, userTaskBOList);
			}
		}
	}
	/**
	 * 接任务
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @param handle
	 */
	public void addTask(String userId, int systemTaskId, int star) {
		Date now = new Date();
		// 触发新的任务
		SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(systemTaskId);
		if (systemTask == null) {
			throw new ServiceException(SystemConstant.FAIL_CODE, "任务不存在" + systemTaskId);
		}
		
		User role = userService.getByUserId(userId);
        //是否达到任务等级
		if(!(systemTask.getLimitMinLevel() <= role.getLevel() 
				&& systemTask.getLimitMaxLevel() >= role.getLevel())) {
			throw new ServiceException(ADD_TASK_LEVEL_LIMIT, "等级不足" + systemTaskId);
		}
		
		 // 不在任务有效期
		if (now.before(systemTask.getEffectBeginTime()) || now.after(systemTask.getEffectEndTime())) {
			throw new ServiceException(ADD_TASK_TASK_ISAVALIAD, "不在有效期" + systemTaskId);
		}
		
		//阵营判断
		if(systemTask.getCamp() != 0 && role.getCamp() != systemTask.getCamp()) {
			throw new ServiceException(SystemConstant.FAIL_CODE, "任务阵营不对，不能接不是本阵营的任务" + systemTaskId);
		}
		
		int receiveTimes = this.configDateDao.getInt(ConfigKey.daily_task_receive_times, 5);
		UserTask userTask = userTaskDao.get(userId, systemTaskId);
		UserDailyTaskInfo info = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
		if (userTask != null) {			
			// 领取日常任务
			if (systemTask.getTaskType() == DAILY_TASK) {				
				if (info.getTimes() >= receiveTimes)
					throw new ServiceException(NO_DAILY_TIMES, "日常任务次数已满");
				
				if (info.getSystemTaskId() > 0) 
					throw new ServiceException(ONLY_REVEIVE_ONCE, "只能接一次日常任务");			
				
				int status = TASK_STATUS_HAVE_GET;
				int finishTimes = 0;
				this.userTaskDao.update(userId, systemTaskId, finishTimes, status);				
				this.userDailyTaskInfoDao.updateUserDailyTaskInfo(userId, info.getTimes(), systemTaskId);					
			} else { 
				if (userTask.getStatus() != TASK_STATUS_NEW)
					throw new ServiceException(SystemConstant.FAIL_CODE, "无法接受任务,任务不是新任务，当前状态为:" + userTask.getStatus()
							+ ",systemTaskId=" + systemTaskId);				
			}
		} else {			
			if (systemTask.getTaskType() == DAILY_TASK) {
				if (info.getTimes() >= receiveTimes)
					throw new ServiceException(NO_DAILY_TIMES, "日常任务次数已满");
				
				if (info.getSystemTaskId() > 0) 
					throw new ServiceException(ONLY_REVEIVE_ONCE, "只能接一次日常任务");	
				
				// 更新日常的任务
				this.userDailyTaskInfoDao.updateUserDailyTaskInfo(userId, info.getTimes(), systemTaskId);
			}
			
			// 接收支线、日常任务
			int status = TASK_STATUS_HAVE_GET;
			userTask = createUserTask(userId, status, systemTask);			
			this.userTaskDao.addUserTask(userTask);
		}
		
		// 查看任务是否以前就已经完成了
		int status = TASK_STATUS_HAVE_GET;
		int finishTimes = taskFinishTimes(userId, systemTask);
		if (finishTimes >= systemTask.getNeedFinishTimes()) {
			finishTimes = systemTask.getNeedFinishTimes();
			status = TASK_STATUS_FINISH;
		}
		userTaskDao.update(userId, systemTaskId, finishTimes, status);		
		
		userTask = userTaskDao.get(userId, systemTaskId);
		UserTaskBO userTaskBO =  creatUserTaskBO(userTask, star);
		//更新任务广播
		List<UserTaskBO> list = Lists.newArrayList();
		list.add(userTaskBO);
		notifyTaskUpdate(userId, list);
	}

	private UserTask createUserTask(String userId, int status, SystemTask systemTask) {
		UserTask userTask = new UserTask();
		userTask.setUserId(userId);
		userTask.setCreatedTime(new Date());
		userTask.setUpdatedTime(new Date());
		userTask.setSystemTaskId(systemTask.getSystemTaskId());
		userTask.setStatus(status);
		userTask.setFinishTimes(0);
		
		return userTask;
	}
	
	/**
	 * 获取单个用户任务信息
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @return
	 */
	public UserTaskBO get(String userId, int systemTaskId) {
		UserTask userTask = this.userTaskDao.get(userId, systemTaskId);
		if (userTask == null) {
			LogSystem.warn("用户任务不存在.userId[" + userId + "], systemTaskId[" + systemTaskId + "]");
			return null;
		}
		return creatUserTaskBO(userTask, 0);
	}

	/**
	 * 创建客户端用户任务信息
	 * 
	 * @param userTask
	 * @return
	 */
	private UserTaskBO creatUserTaskBO(UserTask userTask, int star) {
		UserTaskBO userTaskBO = new UserTaskBO();
		userTaskBO.setFinishTimes(userTask.getFinishTimes());
		userTaskBO.setStatus(userTask.getStatus());
		userTaskBO.setSystemTaskId(userTask.getSystemTaskId());
		userTaskBO.setStar(star);
		return userTaskBO;
	}

	/**
	 * 获取用户任务列表信息
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<UserTaskBO> getUserTaskList(String userId, int status) {
		List<UserTaskBO> userTaskBOList = new ArrayList<UserTaskBO>();
		List<UserTask> userTaskList = this.userTaskDao.getList(userId, status);
		
//		User user = this.userService.getByUserId(userId);
		for (UserTask userTask : userTaskList) {			
			SystemTask systemTask = this.systemTaskDao .getBySystemTaskId(userTask.getSystemTaskId());
			if (systemTask == null) { // 系统任务中没有这个任务就直接跳过
				continue;
			}

			// 已经领取的不返回
			if (systemTask.getTaskType() == MAIN_TASK && userTask.getStatus() == TASK_STATUS_DRAWED) {
				continue;
			}
			
			// 支线任务未接的不需要返回
			if (systemTask.getTaskType() == BRANCH_TASK && userTask.getStatus() == TASK_STATUS_NEW)
				continue;
			
			// 日常任务只显示已领取并且未领奖的
			if (systemTask.getTaskType() == DAILY_TASK && (userTask.getStatus() == TASK_STATUS_NEW || userTask.getStatus() == TASK_STATUS_DRAWED))
				continue;
			
//			if ((systemTask.getTaskType() != MAIN_TASK) && (user.getLevel() < systemTask.getLimitMinLevel() || user.getLevel() > systemTask.getLimitMaxLevel()))
//				continue;
			
			Date now = new Date();
			if (now.before(systemTask.getEffectBeginTime())
					|| now.after(systemTask.getEffectEndTime())) { // 不在任务有效期的不返回
				continue;
			}
			
			int star = 0;
			if (systemTask.getTaskType() == DAILY_TASK) {
				List<UserDailyTaskList> userDailyTaskList = this.userDailyTaskListDao.getUserDailyTaskList(userId);
				if (userDailyTaskList != null && userDailyTaskList.size() > 0) {
					for (UserDailyTaskList dailyTask : userDailyTaskList) {
						if (dailyTask.getSystemTaskId() == systemTask.getSystemTaskId()) {
							star = dailyTask.getStar();
							break;
						}
					}
				}				
			}
			
			UserTaskBO userTaskBO = creatUserTaskBO(userTask, star);
			userTaskBOList.add(userTaskBO);
		}
		return userTaskBOList;
	}


	/**
	 * 领取任务奖励
	 * 
	 * @param userId
	 * @param systemTaskId
	 * @param handle
	 * @return
	 */
	public TaskAction_receiveTaskRes receive(String userId, int systemTaskId) {
		// 可领取状态验证
		UserTask userTask = this.userTaskDao.get(userId, systemTaskId);
		if (userTask == null) {
			String message = "领取任务失败,任务不存在.userId[" + userId + "], systemTaskId[" + systemTaskId + "]";
			LogSystem.warn(message);
			throw new ServiceException(SystemConstant.FAIL_CODE, message);
		}
		
		if (userTask.getStatus() == TASK_STATUS_DRAWED) {
			String message = "领取任务失败,任务已经领取.userId[" + userId + "], systemTaskId[" + systemTaskId + "]";
			LogSystem.warn(message);
			throw new ServiceException(TASK_RECEIVE_HAS_RECEIVE, message);
		}
		
		if (userTask.getStatus() != TASK_STATUS_FINISH) {
			String message = "领取任务失败,任务未完成.userId[" + userId + "], systemTaskId[" + systemTaskId + "]";
			LogSystem.warn(message);
			throw new ServiceException(TASK_RECEIVE_NOT_FINISH, message);
		}
		
		// 获取系统任务
		SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(systemTaskId);
		if (systemTask == null) {
			String message = "领取任务失败,系统任务不存在.userId[" + userId + "], systemTaskId[" + systemTaskId + "]";
			LogSystem.warn(message);
			throw new ServiceException(SystemConstant.FAIL_CODE, message);
		}
		
		TaskAction_receiveTaskRes res = new TaskAction_receiveTaskRes();
		boolean success = false;
		success = this.userTaskDao.update(userId, systemTaskId, TASK_STATUS_DRAWED);
		res.setGoods(new GoodsBeanBO());
		res.setUserEquipIdList(new ArrayList<String>());
		res.setUserGemstoneIdList(new ArrayList<String>());
		
		int taskType = systemTask.getTaskType();
		int taskLibraryType = systemTask.getTaskLibrary();
		if (taskLibraryType == TaskLibraryType.COLLECT) {
			// 需要扣除收集的数据
			Map<String, String> params = TaskHelper.parse(systemTask.getTaskPara());
			int toolId = Integer.parseInt(params.get("toolId"));
			int toolType = Integer.parseInt(params.get("toolType"));
			reduceTaskTool(userId, toolType, toolId, systemTask, res);
		}	
		
		String rewards = systemTask.getRewards();
		// 日常任务提交后，需要更新用户日常任务信息
		if (taskType == DAILY_TASK) {
			UserDailyTaskInfo info = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
			List<UserDailyTaskList> rewardList = this.userDailyTaskListDao.getUserDailyTaskList(userId);
			for (UserDailyTaskList userDaily : rewardList) {
				if (userDaily.getSystemTaskId() == info.getSystemTaskId()) {
					SystemDailyTask dailyTask = this.systemDailyTaskDaoCache.getSystemDailyTask(systemTaskId, userDaily.getStar());
					rewards = dailyTask.getRewards();
					LogSystem.info("日常任务奖励 ：" + rewards + " systemTaskId:" + userDaily.getSystemTaskId() + " star :" + userDaily.getStar());
					break;
				}					
			}			
			
			this.userDailyTaskInfoDao.updateUserDailyTaskInfo(userId, info.getTimes() + 1, -2);
			this.activityService.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_DAILY_TASK, 1);
		} 
		
		if (!success) {
			String message = "领取任务失败,任务不存在.userId[" + userId + "], systemTaskId[" + systemTaskId + "]";
			LogSystem.warn(message);
			throw new ServiceException(SystemConstant.FAIL_CODE, message);
		}
		
		//开启下面的任务
		this.openAfterTask(userId, systemTaskId, true);
		//更新客户端任务状态
		List<UserTaskBO> lists = Lists.newArrayList();
		lists.add(creatUserTaskBO(userTask, 0));
		notifyTaskUpdate(userId, lists);
		// 获取奖励
		CommonGoodsBeanBO commonDropBO = goodsDealService.sendGoods(userId, rewards, GoodsUseType.ADD_TASK);
		res.setDrop(commonDropBO);
		
		// TODO 日常任务提交后，需要更新用户日常任务列表，放这里是因为等用户升级后执行，任务与等级相关
		if (taskType == DAILY_TASK) {
			UserDailyTaskInfo info = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
			returnUserDailyTaskInfoList(info);
		}
		return res;
	}
	
	/**
	 * 检查并扣除任务物品是否足够
	 * 
	 * @param userId
	 */
	private void reduceTaskTool(String userId, int toolType, int toolId, SystemTask systemTask, TaskAction_receiveTaskRes res) {
		int systemTaskId = systemTask.getSystemTaskId();
		
		// 需要扣除收集的数据
		if (GoodsType.tool.intValue == toolType) {
			this.toolService.reduceTool(userId, toolId, systemTask.getNeedFinishTimes(), GoodsUseType.REDUCE_TASK_COLLECT);
			
			res.setGoods(GoodsHelper.parseDropGood(toolType, toolId, systemTask.getNeedFinishTimes()));
		} else if (GoodsType.equip.intValue == toolType) {
			List<UserEquip> equipList = this.equipService.getUnWearEquipList(userId, toolId);
			if (equipList.size() < systemTask.getNeedFinishTimes()) {
				this.userTaskDao.update(userId, systemTaskId, equipList.size(), TASK_STATUS_HAVE_GET);
				throw new ServiceException(TASK_TOOL_NOT_ENOUGH, "任务道具不足？？userId = " + userId + ",systemTaskId=" + systemTaskId 
						+ ",toolType=" + toolType + ",toolId=" + toolId);
			}
			
			List<String> userEquipIdList = Lists.newArrayList();
			for (int index = 0; index < systemTask.getNeedFinishTimes(); index++) {
				UserEquip userEquip = equipList.get(index);
				this.equipService.reduceEquip(userId, userEquip.getUserEquipId(), GoodsUseType.REDUCE_TASK_COLLECT, new CommonGoodsBeanBO());
				userEquipIdList.add(userEquip.getUserEquipId());
			}
			
			res.setUserEquipIdList(userEquipIdList);
		} else if (GoodsType.Gemstone.intValue == toolType) {
			List<UserGemstone> stoneList = this.gemstoneService.getUnFillGemstoneList(userId, toolId);
			if (stoneList.size() < systemTask.getNeedFinishTimes()) {
				this.userTaskDao.update(userId, systemTaskId, stoneList.size(), TASK_STATUS_HAVE_GET);
				throw new ServiceException(TASK_TOOL_NOT_ENOUGH, "任务道具不足？？userId = " + userId + ",systemTaskId=" + systemTaskId 
						+ ",toolType=" + toolType + ",toolId=" + toolId);
			}
			
			List<String> userGemstoneIdList = Lists.newArrayList();
			for (int index = 0; index < systemTask.getNeedFinishTimes(); index++) {
				UserGemstone userGemstone = stoneList.get(index);
				this.gemstoneService.reduceUserGemstone(userId, userGemstone.getUserGemstoneId(), GoodsUseType.REDUCE_TASK_COLLECT, new CommonGoodsBeanBO());
				userGemstoneIdList.add(userGemstone.getUserGemstoneId());
			}
			res.setUserGemstoneIdList(userGemstoneIdList);
		}
	}

	/**
	 * 更新客户端任务状态
	 * 
	 * @param userId
	 * @param status
	 */
	private void notifyTaskUpdate(String userId, List<UserTaskBO> list) {
		Task_updateNotify notify = new Task_updateNotify();
		notify.setUpdateUserTaskList(list);
		MsgDispatchCenter.disPatchUserMsg("Task_update", userId, notify);
	}
	
	/**
	 * 更新客户端任务状态
	 * 
	 * @param userId
	 * @param userTaskBO
	 */
	private void notifyTaskUpdate(String userId, UserTaskBO userTaskBO) {
		List<UserTaskBO> list = Lists.newArrayList();
		list.add(userTaskBO);
		notifyTaskUpdate(userId,list);
	}

	/**
	 * 判断用户是否完成过这个任务
	 * 
	 * @param systemTask
	 */
	private int taskFinishTimes(String userId, SystemTask systemTask) {
		int taskLibraryType = systemTask.getTaskLibrary();
		Map<String, String> params = TaskHelper.parse(systemTask.getTaskPara());
		
		if (taskLibraryType == TaskLibraryType.ALL_CHARGE_NUM) {
			
			return paymentLogDao.getPaymentTotal(userId);
		} else if (taskLibraryType == TaskLibraryType.DAILY_CHARGE_NUM) {			
			Date todayMiddle = DateUtils.getDateAtMidnight();
			return paymentLogDao.getPaymentTotalByTime(userId, todayMiddle, DateUtils.addDays(todayMiddle, 1));
		} else if (taskLibraryType == TaskLibraryType.USER_LEVEL_UP) {			
			int needUserLevel = NumberUtils.parseNumber(params.get("lv"), Integer.class);
			User role = userService.getByUserId(userId);
			if (role.getLevel() >= needUserLevel) {
				return systemTask.getNeedFinishTimes();
			}
		} else if (taskLibraryType == TaskLibraryType.VIP_LEVEL) {			
			int needUserVipLevel = NumberUtils.parseNumber(params.get("vipLv"), Integer.class);
			User role = userService.getByUserId(userId);
			if (role.getVipLevel() >= needUserVipLevel) {
				return systemTask.getNeedFinishTimes();
			}
		} else if (taskLibraryType == TaskLibraryType.GET_HERO) {
			// TODO 还没有实现
			
		} else if (taskLibraryType == TaskLibraryType.COLLECT) {
			int toolType = NumberUtils.parseNumber(params.get("toolType"), Integer.class);
			int toolId = NumberUtils.parseNumber(params.get("toolId"), Integer.class);
			Preconditions.checkNotNull(toolType, "toolType不能为空");
			Preconditions.checkNotNull(toolId, "toolId不能为空");
			
			if (toolType == GoodsType.equip.intValue) {
				List<UserEquip> userEquipList = equipService.getUnWearEquipList(userId, toolId);
				if (userEquipList == null)
					return 0;
				
				return userEquipList.size();
			} else if (toolType == GoodsType.Gemstone.intValue) {
				List<UserGemstone> gemstoneList = gemstoneService.getUnFillGemstoneList(userId, toolId);
				if (gemstoneList == null)
					return 0;
				
				return gemstoneList.size();
			}
				
			UserToolBO userToolBO = this.toolService.getUserToolBO(userId, toolId);
			if (userToolBO == null)
				return 0;
			
			return userToolBO.getToolNum();
		} else {
			UserTask userTask = this.userTaskDao.get(userId, systemTask.getSystemTaskId());
			if (userTask == null)
				return 0;
			
			return userTask.getFinishTimes();
		}  		
		
		return 0;
	}
	
	/**
	 * 更新已完成
	 * 
	 * @param userId
	 * @param times
	 * @param taskChecker
	 */
	public void updateTaskFinish(String userId, int times, ITaskHitChecker taskChecker) {
		List<UserTask> userTaskList = this.userTaskDao.getList(userId, TASK_STATUS_HAVE_GET);
		userTaskList.addAll(this.userTaskDao.getList(userId, TASK_STATUS_FINISH));
		List<UserTaskBO> notifyList = Lists.newArrayList();
		
		Map<Integer, UserTask> map = getParaMap(userId);
		for (UserTask userTask : userTaskList) {
			int systemTaskId = userTask.getSystemTaskId();
			int status = userTask.getStatus();// 当前状态
			if (status == TASK_STATUS_NEW || status == TASK_STATUS_DRAWED)
				continue;
			
			SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(systemTaskId);
			if (systemTask == null) {
				continue;
			}
			
			Map<String, String> params = TaskHelper.parse(systemTask
					.getTaskPara());	
			Map<String, Object> returnMap = taskChecker.isHit(systemTask.getSystemTaskId(),
					systemTask.getTaskLibrary(), params);
			
			boolean isTrue = (boolean) returnMap.get("rt");
			if (!isTrue) {
				continue;
			}
			
			// TODO  在领取奖励之前，有可能将道具使用掉了
			times = (int) returnMap.get("tm");
			int needFinishTimes = systemTask.getNeedFinishTimes();
			int finishTimes = userTask.getFinishTimes();
			
			if (times > 0) {
				finishTimes = finishTimes + times;
				if (finishTimes >= needFinishTimes) {
					finishTimes = needFinishTimes;
					status = TASK_STATUS_FINISH;
				} 
			} else {
				// 若用户将提交的道具、装备、使用掉
				status = TASK_STATUS_HAVE_GET;
				finishTimes = taskFinishTimes(userId, systemTask);
				if (finishTimes >= systemTask.getNeedFinishTimes()) {
					finishTimes = systemTask.getNeedFinishTimes();
					status = TASK_STATUS_FINISH;
				}
			}			
			
			// 更新任务
			if (this.userTaskDao.update(userId, systemTaskId, finishTimes, status)) {
				UserTaskBO userTaskBO = new UserTaskBO();
				userTaskBO.setFinishTimes(finishTimes);
				userTaskBO.setStatus(status);
				userTaskBO.setSystemTaskId(systemTaskId);
				userTaskBO.setStar(0);
				if (systemTask.getTaskType() == DAILY_TASK)
					userTaskBO.setStar(getDailyTaskStar(userId, systemTaskId));
				
				notifyList.add(userTaskBO);
			}			
			
			if (status == TASK_STATUS_FINISH) {				
				UserTaskBO userTaskBO = null;
				
				// 完成某个任务导致另一个任务的完成
				if (map.containsKey(systemTask.getTaskType()) || map.containsKey(systemTaskId)) {
					userTaskBO = updateAnotherTask(map.get(systemTask.getTaskType()));
				} else if (map.containsKey(systemTaskId)) {
					userTaskBO = updateAnotherTask(map.get(systemTaskId));
				}
				
				if (userTaskBO != null)
					notifyList.add(userTaskBO);					
			}
		}
		
		if (notifyList.size()>0) {
			notifyTaskUpdate(userId, notifyList);
		}
	}
	
	private int getDailyTaskStar(String userId, int systemTaskId) {
		List<UserDailyTaskList> dailyTaskList = this.userDailyTaskListDao.getUserDailyTaskList(userId);
		for (UserDailyTaskList task : dailyTaskList) {
			if (task.getSystemTaskId() == systemTaskId)
				return task.getStar();
		}
		
		return 0;
	}
	
	public UserTask getUserTask(String userId, int systemTaskId) {
		return this.userTaskDao.get(userId, systemTaskId);
	}
	
	private UserTaskBO updateAnotherTask(UserTask userTask) {
		UserTaskBO userTaskBO = null;
		SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(userTask.getSystemTaskId());
		
		int status = userTask.getStatus();
		int needFinishTimes = systemTask.getNeedFinishTimes();
		int finishTimes = userTask.getFinishTimes();
		finishTimes = finishTimes + 1;
		if (finishTimes >= needFinishTimes) {
			finishTimes = needFinishTimes;
			status = TASK_STATUS_FINISH;
		}
		
		// 更新任务
		if (this.userTaskDao.update(userTask.getUserId(), userTask.getSystemTaskId(), finishTimes, status)) {
			userTaskBO = new UserTaskBO();
			userTaskBO.setFinishTimes(finishTimes);
			userTaskBO.setStatus(status);
			userTaskBO.setSystemTaskId(userTask.getSystemTaskId());
		
			userTaskBO.setStar(0);
			if (systemTask.getTaskType() == DAILY_TASK)
				userTaskBO.setStar(getDailyTaskStar(userTask.getUserId(), userTask.getSystemTaskId()));
		}
		
		return userTaskBO;
	}
	
	/**
	 * 只获取任务套任务的参数
	 * 
	 * @param userId
	 * @return
	 */
	private Map<Integer, UserTask> getParaMap(String userId) {
		List<UserTask> userTaskList = this.userTaskDao.getList(userId, TASK_STATUS_HAVE_GET);
		Map<Integer, UserTask> paraMap = Maps.newConcurrentMap();
		for (UserTask userTask : userTaskList) {
			int status = userTask.getStatus();// 当前状态
			if (status == TASK_STATUS_NEW || status == TASK_STATUS_FINISH || status == TASK_STATUS_DRAWED)
				continue;
			
			SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(userTask.getSystemTaskId());
			if (systemTask == null)
				continue;
			
			if (systemTask.getTaskLibrary() != TaskLibraryType.FINISH_ANOTHER_TASK)
				continue;
			
			Map<String, String> params = TaskHelper.parse(systemTask
					.getTaskPara());
			if (params.get("taskType") != null) {
				Integer taskType = NumberUtils.parseNumber(params.get("taskType"), Integer.class);
				paraMap.put(taskType, userTask);
			}
			
			if (params.get("taskId") != null) {
				 Integer taskId = NumberUtils.parseNumber(params.get("taskId"), Integer.class);
				 paraMap.put(taskId, userTask);
			}
		}
		
		return paraMap;
	}
	
	/**
	 * 放弃任务
	 * @param userId
	 * @param systemTaskId
	 */
	public void dropTask(String userId,int systemTaskId) {
		UserTask userTask = this.userTaskDao.get(userId, systemTaskId);
		 
		if (userTask==null) {
			throw new ServiceException(SystemConstant.FAIL_CODE, "dropTask任务不存在"+systemTaskId);
		}
		
		if(userTask.getStatus() != TASK_STATUS_HAVE_GET){
			throw new ServiceException(SystemConstant.FAIL_CODE, "dropTask任务状态不科学" + systemTaskId+",status=" + userTask.getStatus());
		}
		
		userTaskDao.update(userId, systemTaskId, 0, TASK_STATUS_NEW);		
		// 日常任务放弃后，需要更新用户日常任务信息
		SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(systemTaskId);
		if (systemTask.getTaskType() == DAILY_TASK) {
			UserDailyTaskInfo info = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
			this.userDailyTaskInfoDao.updateUserDailyTaskInfo(userId, info.getTimes(), -1);
		} 
		
		//更新客户端任务状态
		notifyTaskUpdate(userId,creatUserTaskBO(userTask, 0));
	}

	/**
	 * 刷新日常任务
	 * 
	 * @param userId
	 */
	public void refreshDailyTask(String userId) {
		Date now = new Date();
		UserDailyTaskInfo userDailyTaskInfo = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
		if (userDailyTaskInfo == null) {
			userDailyTaskInfo = new UserDailyTaskInfo();
			userDailyTaskInfo.setUserId(userId);
			userDailyTaskInfo.setTimes(0);
			userDailyTaskInfo.setSystemTaskId(0);
			userDailyTaskInfo.setCreatedTime(now);
			userDailyTaskInfo.setUpdatedTime(now);
			userDailyTaskInfoDao.addUserDailyTaskInfo(userDailyTaskInfo);
			return;
		}
		
		if (!DateUtils.isSameDay(now, userDailyTaskInfo.getUpdatedTime())) {// 同一天更新过的，不处理
			int times = 0;
			this.userDailyTaskInfoDao.updateUserDailyTaskInfo(userId, times, userDailyTaskInfo.getSystemTaskId());
			
			List<UserTask> taskList = this.userTaskDao.getList(userId, 100);
			for (UserTask userTask : taskList) {
				int systemTaskId = userTask.getSystemTaskId();
				SystemTask systemTask = systemTaskDao.getBySystemTaskId(systemTaskId);
				
				// 只更新已经领取的日常任务 
				if (systemTask.getTaskType() == DAILY_TASK && userTask.getStatus() == TASK_STATUS_DRAWED) {					
					int status = TASK_STATUS_NEW;
					this.userTaskDao.update(userId, systemTaskId, 0, status);
				}
			}
		}	
	}

	private List<UserDailyTaskInfoBO> returnUserDailyTaskInfoList(UserDailyTaskInfo userDailyTaskInfo) {
		List<UserDailyTaskInfoBO> returnList = Lists.newArrayList();
		String userId = userDailyTaskInfo.getUserId();
		User user = this.userService.getByUserId(userId);
		
		Map<Integer, List<SystemDailyTask>> dailyTaskMap = this.systemDailyTaskDaoCache.getSystemDailyTaskList(user.getLevel(), user.getCamp());
		if (dailyTaskMap.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "系统日常任务星数表，没有数据？？");
				
		List<UserDailyTaskList> userDailyTaskList = getUserDailyTaskList(dailyTaskMap, userId);
		
		// 未领取日常任务
		if (userDailyTaskInfo.getSystemTaskId() <= 0) {
			userDailyTaskListDao.deleteUserDailyTaskList(userId);
			
			this.userDailyTaskListDao.addList(userId, userDailyTaskList);
			returnList = createdUserDailyInfoBOList(userDailyTaskList);
		} else {
			int systemTaskId = userDailyTaskInfo.getSystemTaskId();
			UserDailyTaskList hasGetDailyTask = userDailyTaskListDao.getUserDailyTaskList(userId, systemTaskId);
			UserTask userTask = this.userTaskDao.get(userId, userDailyTaskInfo.getSystemTaskId());
			returnList.add(createdUserDailyInfoBO(hasGetDailyTask, userTask.getStatus()));
			
			userDailyTaskListDao.deleteUserDailyTaskList(userId, systemTaskId);
			List<UserDailyTaskList> list = Lists.newArrayList();
			for (UserDailyTaskList userDailyTask : userDailyTaskList) {
				if (userDailyTask.getSystemTaskId() == systemTaskId) 
					continue;
				
				if (returnList.size() >= 3)
					break;
				returnList.add(createdUserDailyInfoBO(userDailyTask, TASK_STATUS_NEW));
				list.add(userDailyTask);
			}
			userDailyTaskListDao.addList(userId, list);
		}		
		
		return returnList;
	}
	
	/**
	 * 获取用户日常任务信息
	 * 
	 * @param userId
	 * @return
	 */
	public TaskAction_getUserDailyTaskInfoRes getUserDailyTaskInfo(String userId) {
		UserDailyTaskInfo userDailyTaskInfo = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
		User user = this.userService.getByUserId(userId);
		int times = userDailyTaskInfo.getTimes();	
		
		int openLevel = this.configDateDao.getInt(ConfigKey.daily_task_open_level, 8);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "等级不足暂未开放");
		
		Date now = new Date();
		List<UserDailyTaskList> userDailyTaskList = this.userDailyTaskListDao.getUserDailyTaskList(userId);
		TaskAction_getUserDailyTaskInfoRes res = new TaskAction_getUserDailyTaskInfoRes();
		res.setRemainderTimes(times);
		
		List<UserDailyTaskInfoBO> boList = Lists.newArrayList();
		if (userDailyTaskList == null || userDailyTaskList.size() == 0) {	
			boList = returnUserDailyTaskInfoList(userDailyTaskInfo);
			res.setUserDailyTaskInfoList(boList);
			return res;
		}
		
		// 不是同一天的就更新
		boolean isNeedUpdate = false;
		for (UserDailyTaskList dailyTaskList : userDailyTaskList) {
			if (userDailyTaskInfo.getSystemTaskId() != dailyTaskList.getSystemTaskId() && !DateUtils.isSameDay(now, dailyTaskList.getUpdatedTime())) {
				isNeedUpdate = true;
				break;
			}			
		}
		
		if (isNeedUpdate) {
			boList = returnUserDailyTaskInfoList(userDailyTaskInfo);
			res.setUserDailyTaskInfoList(boList);
			return res;
		}		
		
		// TMD玩家跨等级的时候就得TMD重新刷新日常任务（统一刷新）
		boolean isLevelUp = false;
		for (UserDailyTaskList dailyTaskList : userDailyTaskList) {
			if (userDailyTaskInfo.getSystemTaskId() != dailyTaskList.getSystemTaskId()) {
				SystemTask systemTask = this.systemTaskDao.getBySystemTaskId(dailyTaskList.getSystemTaskId());
				if (user.getLevel() < systemTask.getLimitMinLevel() || user.getLevel() > systemTask.getLimitMaxLevel())
					isLevelUp = true;
			}			
		}
		
		// 重新刷新日常任务
		if (isLevelUp) {			
			boList = returnUserDailyTaskInfoList(userDailyTaskInfo);
			res.setUserDailyTaskInfoList(boList);
			return res;
		}
		
		for (UserDailyTaskList dailyTask : userDailyTaskList) {
			int systemTaskId = dailyTask.getSystemTaskId();
			
			if (userDailyTaskInfo.getSystemTaskId() == systemTaskId) {
				UserTask userTask = this.userTaskDao.get(userId, systemTaskId);
				boList.add(createdUserDailyInfoBO(dailyTask, userTask.getStatus()));									
			} else {
				boList.add(createdUserDailyInfoBO(dailyTask, TASK_STATUS_NEW));
			}
		}
		
		res.setUserDailyTaskInfoList(boList);
		return res;
	}
	
	/**
	 * 一键刷新
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserDailyTaskInfoBO> oneClickRefresh(String userId) {
		List<UserDailyTaskInfoBO> boList = Lists.newArrayList();		
		User user = this.userService.getByUserId(userId);
		
		Map<Integer, List<SystemDailyTask>> dailyTaskMap = this.systemDailyTaskDaoCache.getSystemDailyTaskList(user.getLevel(), user.getCamp());
		if (dailyTaskMap.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "日常任务星数数据表，没有数据？？");
		
		int refreshCost = this.configDateDao.getInt(ConfigKey.one_click_refresh_cost, 20);
		if (user.getMoney() < refreshCost)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (!this.userService.reduceMoney(userId, refreshCost, GoodsUseType.REDUCE_ONECLICK_REFRESH_TASK))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		UserDailyTaskInfo userDailyTaskInfo = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
		// 没有接收任务，直接全部刷新
		if (userDailyTaskInfo.getSystemTaskId() <= 0) {
			List<UserDailyTaskList> userDailyTaskList = getUserDailyTaskList(dailyTaskMap, userId);
			this.userDailyTaskListDao.deleteUserDailyTaskList(userId);
			
			this.userDailyTaskListDao.addList(userId, userDailyTaskList);
			return createdUserDailyInfoBOList(userDailyTaskList);
		}		
		
		// 删掉未领取的其他任务
		this.userDailyTaskListDao.deleteUserDailyTaskList(userId, userDailyTaskInfo.getSystemTaskId());
		List<UserDailyTaskList> userDailyTaskList = getUserDailyTaskList(dailyTaskMap, userId);
		List<UserDailyTaskList> resultList = Lists.newArrayList();
		
		for (UserDailyTaskList dailyTask : userDailyTaskList) {
			if (dailyTask.getSystemTaskId() == userDailyTaskInfo.getSystemTaskId()) {
				UserTask userTask = this.userTaskDao.get(userId, userDailyTaskInfo.getSystemTaskId());
				UserDailyTaskList receiveTask = this.userDailyTaskListDao.getUserDailyTaskList(userId, userDailyTaskInfo.getSystemTaskId());
				boList.add(createdUserDailyInfoBO(receiveTask, userTask.getStatus()));
			} else {				
				resultList.add(dailyTask);
				boList.add(createdUserDailyInfoBO(dailyTask, TASK_STATUS_NEW));
			}		
		}
		
		// 将刷新出来的添加到数据库
		this.userDailyTaskListDao.addList(userId, resultList);
		return boList;
	}
	
	private static final int NO_STAR_REFRESH = 2001;
	/**
	 * 刷五星
	 * 
	 * @param userId
	 * @return
	 */
	public UserDailyTaskInfoBO refreshFiveStar(String userId) {
		UserDailyTaskInfoBO infoBO = new UserDailyTaskInfoBO();
		List<UserDailyTaskList> userDailyTaskList = this.userDailyTaskListDao.getUserDailyTaskList(userId);
		UserDailyTaskInfo info = this.userDailyTaskInfoDao.getUserDailyTaskInfo(userId);
		
		int systemTaskId = 0;
		int fiveStar = 5;
		for (UserDailyTaskList userDailyTask : userDailyTaskList) {
			if (info.getSystemTaskId() == userDailyTask.getSystemTaskId())
				continue;
			
			if (userDailyTask.getStar() == fiveStar)
				continue;
			
			systemTaskId = userDailyTask.getSystemTaskId();
		}		
		
		if (systemTaskId == 0)
			throw new ServiceException(NO_STAR_REFRESH, "没有可刷新的任务了");
		
		User user = this.userService.getByUserId(userId);
		int refreshFiveStar = this.configDateDao.getInt(ConfigKey.refresh_daily_five_star_cost, 50);
		if (user.getMoney() < refreshFiveStar)
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		if (!this.userService.reduceMoney(userId, refreshFiveStar, GoodsUseType.REDUCE_REFRESH_FIVE_TASK))
			throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		
		this.userDailyTaskListDao.updateUserDailyTaskList(userId, systemTaskId, fiveStar);
		infoBO.setStar(fiveStar);
		infoBO.setStatus(TASK_STATUS_NEW);
		infoBO.setSystemTaskId(systemTaskId);
		return infoBO;
	}
	
	private UserDailyTaskInfoBO createdUserDailyInfoBO(UserDailyTaskList userDailyTask, int status) {
		UserDailyTaskInfoBO bo = new UserDailyTaskInfoBO();
		bo.setStatus(status);
		bo.setSystemTaskId(userDailyTask.getSystemTaskId());
		bo.setStar(userDailyTask.getStar());
		
		return bo;
	}
	
	private List<UserDailyTaskInfoBO> createdUserDailyInfoBOList(List<UserDailyTaskList> userList) {
		List<UserDailyTaskInfoBO> boList = Lists.newArrayList();
		for (UserDailyTaskList userDailyTask : userList) {
			UserDailyTaskInfoBO bo = createdUserDailyInfoBO(userDailyTask, TASK_STATUS_NEW);
			boList.add(bo);
		}
		
		return boList;
	}
	
	private List<UserDailyTaskList> getUserDailyTaskList(Map<Integer, List<SystemDailyTask>> dailyTaskMap, String userId) {
		List<UserDailyTaskList> userList = Lists.newArrayList();
		
		for (List<SystemDailyTask> list : dailyTaskMap.values()) {
			int random = RandomUtils.getRandomNum(1, 10000);
			for (SystemDailyTask dailyTask: list) {
				if (dailyTask.getLowerNum() <= random && dailyTask.getUpperNum() >= random) {
					int systemTaskId = dailyTask.getSystemTaskId();
					UserDailyTaskList userDailyTask = new UserDailyTaskList();
					userDailyTask.setSystemTaskId(systemTaskId);
					userDailyTask.setUserId(userId);
					userDailyTask.setStar(dailyTask.getStar());
					userDailyTask.setCreatedTime(new Date());
					userDailyTask.setUpdatedTime(new Date());
				
					userList.add(userDailyTask);
					break;
				}			
			}
		}
		
		if (userList.size() <= 3)
			return userList;
		
		// 每次只取3条
		List<UserDailyTaskList> returnList = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			int random = RandomUtils.getRandomNum(1, userList.size());
			returnList.add(userList.get(random - 1));
			
			userList.remove(random - 1);
		}
		
		return returnList;
	}
	
	/**
	 * 道具开启任务
	 * 
	 * @param userId
	 * @param toolId
	 * @return
	 */
	public int toolOpenTask(String userId, int toolId) {
		SystemTool systemTool = this.toolService.getSystemTool(toolId);
		if (systemTool == null || systemTool.getType() != ToolType.TOOL_OPEN_TASK)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "道具不存在？不是开启任务的类型？？toolId=" + toolId);
		
		addTask(userId, systemTool.getNum(), 1);
		return systemTool.getNum();	
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(handlerType.equals(ModuleEventConstant.USER_LOGIN_EVENT)) {
			//刷新日志任务
			refreshDailyTask(baseModuleEvent.getStringValue("userId", ""));
		}else if (handlerType.equals(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT)) {
			//开启任务
			this.openAfterTask(baseModuleEvent.getStringValue("userId", ""), 0, false);
		}
	}
	
	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.USER_LOGIN_EVENT);
		list.add(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT);
		return list;
	}
	
	@Override
	public int order() {
		return 0;
	}
}

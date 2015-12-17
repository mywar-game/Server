package com.fantingame.game.mywar.logic.activity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.cuser.model.UserMapper;
import com.fantingame.game.cuser.service.UserMapperService;
import com.fantingame.game.msgbody.client.activity.ActivityAction_getActivityTaskInfoRes;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveActivityTaskRewardRes;
import com.fantingame.game.msgbody.client.activity.ActivityAction_receiveLoginReward30Res;
import com.fantingame.game.msgbody.client.activity.SystemActivityBO;
import com.fantingame.game.msgbody.client.activity.UserActivityTaskBO;
import com.fantingame.game.msgbody.client.activity.UserActivityTaskRewardBO;
import com.fantingame.game.msgbody.client.activity.UserLoginRewardBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.notify.activity.Activity_updateActivityTaskNotify;
import com.fantingame.game.mywar.logic.activity.constant.ActivityConstant;
import com.fantingame.game.mywar.logic.activity.constant.ActivityTaskConstant;
import com.fantingame.game.mywar.logic.activity.dao.UserActivityRewardLogDao;
import com.fantingame.game.mywar.logic.activity.dao.UserActivityTaskDao;
import com.fantingame.game.mywar.logic.activity.dao.UserLoginReward30Dao;
import com.fantingame.game.mywar.logic.activity.dao.cache.SystemActivityDaoCache;
import com.fantingame.game.mywar.logic.activity.dao.cache.SystemActivityTaskDaoCache;
import com.fantingame.game.mywar.logic.activity.dao.cache.SystemActivityTaskRewardDaoCache;
import com.fantingame.game.mywar.logic.activity.dao.cache.SystemLoginReward30DaoCache;
import com.fantingame.game.mywar.logic.activity.model.SystemActivity;
import com.fantingame.game.mywar.logic.activity.model.SystemActivityTask;
import com.fantingame.game.mywar.logic.activity.model.SystemActivityTaskReward;
import com.fantingame.game.mywar.logic.activity.model.SystemLoginReward30;
import com.fantingame.game.mywar.logic.activity.model.UserActivityRewardLog;
import com.fantingame.game.mywar.logic.activity.model.UserActivityTask;
import com.fantingame.game.mywar.logic.activity.model.UserLoginReward30;
import com.fantingame.game.mywar.logic.client.bridge.HttpClientBridge;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.user.dao.mysql.UserGiftbagDaoMysqlImpl;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserGiftcode;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 活动模块业务层
 * 
 * @author yezp
 */
public class ActivityService implements ModuleEventHandler {

	@Autowired
	private UserService userService;
	@Autowired
    private UserMapperService userMapperService;
	@Autowired
	private UserGiftbagDaoMysqlImpl userGiftbagDaoMysqlImpl;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private SystemLoginReward30DaoCache systemLoginReward30Dao;
	@Autowired
	private UserLoginReward30Dao userLoginReward30Dao;
	@Autowired
	private SystemActivityDaoCache systemActivityDao;
	@Autowired
	private UserActivityTaskDao userActivityTaskDao;
	@Autowired
	private UserActivityRewardLogDao userActivityRewardLogDao;
	@Autowired
	private SystemActivityTaskDaoCache systemActivityTaskDao;
	@Autowired
	private SystemActivityTaskRewardDaoCache systemActivityTaskRewardDao;
	
	/**
	 * 验证礼包码的URL
	 */
	private final String CHECK_GIFT_CODE_URL = "checkGiftCode.do";
	
	/**
	 * 记录领取日志的URL
	 */
	private final String RECORD_LOG_URL = "recordGiftCodeLog.do";
	
	/**
	 * 已经领取过了
	 */
	private final static int GIFTBAG_HAS_RECEIVE = 2007;
	/**
	 * 领取礼包码奖励
	 * 
	 * @param userId
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CommonGoodsBeanBO receiveGiftBag(String userId, String code) {
		// TODO 判断礼包活动是否开启 ----未做
		
		if (code == null || code.equals(""))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，为传入礼包码");
		
		User user = this.userService.getByUserId(userId);
		UserMapper userMapper = this.userMapperService.getUserMapperByUserId(userId);
		
		// 发送到礼包码服务器，检查领取次数，返回奖励信息
		String response = toCheckGiftCode(userId, user.getLevel(), user.getVipLevel(), userMapper.getServerId(), code);
		JSONObject jsonObject = JSONObject.fromObject(response);
		if (jsonObject.containsKey(HttpClientBridge.RC) 
				&& jsonObject.getInt(HttpClientBridge.RC) != SystemConstant.SUCCESS_CODE) {
			throw new ServiceException(jsonObject.getInt(HttpClientBridge.RC), "领取礼包奖励失败");
		}
		
		int timesLimit = jsonObject.getInt("timesLimit");
		int giftbagId = jsonObject.getInt("giftbagId");
		int type = jsonObject.getInt("type");
				
		List<JSONObject> jsonList = (List<JSONObject>) jsonObject.get("rewardList");
		List<GoodsBeanBO> rewardList = new ArrayList<GoodsBeanBO>();
		for (JSONObject json : jsonList) {			
			GoodsBeanBO good = (GoodsBeanBO) JSONObject.toBean(json, GoodsBeanBO.class);
			rewardList.add(good);
		}
		
		// 检查背包
		userService.checkBag(userId, rewardList);
		
		// 检查领取次数
		List<UserGiftcode> userGiftbagList = userGiftbagDaoMysqlImpl.getGiftcodeListByType(userId, type);
		if (userGiftbagList != null && userGiftbagList.size() > 0) {
			int totalNum = 0;
			
			for (UserGiftcode userGiftcode : userGiftbagList) {
				totalNum = totalNum + userGiftcode.getTotalNum();
			}
			
			if (totalNum >= timesLimit) {
				String message = "领取激活码礼包失败，已经领取过.userId[" + userId + "]";
				LogSystem.info(message);
				throw new ServiceException(GIFTBAG_HAS_RECEIVE, message);
			}
		}
		
		// 发送至礼包码服务器写日志		
		response = toRecordGiftCodeLog(userId, code, userMapper.getServerId(), userMapper.getPartnerId(), userMapper.getQn(), giftbagId);
		if (response == null || response.equals(""))
			throw new ServiceException(SystemConstant.FAIL_CODE, "请求礼包码服务器，返回出错");
		
		jsonObject = JSONObject.fromObject(response);
		if (jsonObject.containsKey(HttpClientBridge.RC) 
				&& jsonObject.getInt(HttpClientBridge.RC) != SystemConstant.SUCCESS_CODE) {
			throw new ServiceException(jsonObject.getInt(HttpClientBridge.RC), "领取礼包奖励失败");
		}
		
		// 给奖励 记录日志
		this.userGiftbagDaoMysqlImpl.addOrUpdateUserGiftcode(userId, type, giftbagId);		
		return goodsDealService.sendGoods(userId, rewardList, GoodsUseType.ADD_BY_RECEIVE_GIFTCODE);
	}
	
	/**
	 * 检查礼包码
	 * 
	 * @param userId
	 * @param level
	 * @param vipLevel
	 * @param serverId
	 * @param code
	 * @return
	 */
	private String toCheckGiftCode(String userId, int level, int vipLevel, String serverId, String code) {
		StringBuilder params = new StringBuilder();
		StringBuilder md5str = new StringBuilder();
		
		params.append("&userId=").append(userId);
		params.append("&level=").append(level);
		params.append("&vipLevel=").append(vipLevel);
		params.append("&serverId=").append(serverId);
		params.append("&code=").append(code);
		
		md5str.append(userId).append(level).append(vipLevel)
			.append(serverId).append(code);;
		String response = HttpClientBridge.sendToGiftbagServer(CHECK_GIFT_CODE_URL, params.toString(),
				md5str.toString());
		
		if (response == null || response.equals(""))
			throw new ServiceException(SystemConstant.FAIL_CODE, "请求礼包码服务器，返回出错");
		
		return response;
	}

	/**
	 * 记录礼包码日志
	 * 
	 * @param userId
	 * @param code
	 * @param serverId
	 * @param partnerId
	 * @param qn
	 * @param giftbagId
	 * @return
	 */
	private String toRecordGiftCodeLog(String userId, String code, String serverId, String partnerId, String qn, int giftbagId) {
		StringBuilder params = new StringBuilder();
		params.append("&code=").append(code);
		params.append("&userId=").append(userId);
		params.append("&serverId=").append(serverId);
		params.append("&partnerId=").append(partnerId);
		params.append("&qn=").append(qn);
		params.append("&giftbagId=").append(giftbagId);
		
		StringBuilder md5str = new StringBuilder();
		md5str.append(code).append(userId).append(serverId).append(partnerId)
				.append(qn).append(giftbagId);
		
		String response = HttpClientBridge.sendToGiftbagServer(RECORD_LOG_URL, params.toString(),
				md5str.toString());
		return response;
	}
	
	/**
	 * 获取活动列表
	 * 
	 * @param status
	 * @return
	 */
	public List<SystemActivityBO> getAllList(int status) {
		List<SystemActivityBO> list = Lists.newArrayList();
		List<SystemActivity> activityList = this.systemActivityDao.getAllList(status);
		for (SystemActivity systemActivity : activityList) {
			list.add(createSystemActivityBO(systemActivity));
		}
		
		return list;
	}
	
	private SystemActivityBO createSystemActivityBO(SystemActivity activity) {
		SystemActivityBO bo = new SystemActivityBO();
		bo.setActivityId(activity.getActivityId());
		bo.setActivityShowType(activity.getActivityShowType());
		bo.setActivityType(activity.getActivityType());
		bo.setActivityName(activity.getActivityName());
		bo.setActivityTitle(activity.getActivityTitle());
		bo.setActivityDesc(activity.getActivityDesc());
		bo.setStartTime(activity.getStartTime().getTime());
		bo.setEndTime(activity.getEndTime().getTime());
		bo.setStatus(activity.getStatus());
		bo.setDisplay(activity.getDisplay());
		bo.setImgId(activity.getImgId());
		bo.setOpenTime(activity.getOpenTime());
		bo.setOpenWeeks(activity.getOpenWeeks());
		bo.setSort(activity.getSort());
		bo.setParam(activity.getParam());
		
		return bo;
	}
	
	// 0 未领取  1 已领取
	public final static int NOT_RECEIVE = 0;
	public final static int HAS_RECEIVE = 1;
	/**
	 * 获取用户每月签到信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserLoginRewardBO> getLoginReward30Info(String userId) {
		SystemActivity systemActivity = this.systemActivityDao.getSystemActivity(ActivityConstant.ACTIVITY_ID_LOGIN_30_DAY);
		checkActivityIsOpen(systemActivity);				
		List<UserLoginRewardBO> list = Lists.newArrayList();
		
		// TODO 自然月刷新
		UserLoginReward30 lastLogin = this.userLoginReward30Dao.getLastLoginReward30(userId);
		if (lastLogin == null) {
			lastLogin = createUserLoginReward30(userId, 1);
			this.userLoginReward30Dao.addUserLoginReward30(lastLogin);
		} else {
			// 已领取并且不是同一天，需要刷新或加一天
			if (lastLogin.getStatus() == HAS_RECEIVE && !DateUtils.isSameDay(new Date(), lastLogin.getUpdatedTime())) {
				int currentMonth = DateUtils.getMonth();
				int userMonth =DateUtils.getMonth(lastLogin.getUpdatedTime());
				if (currentMonth != userMonth) {
					this.userLoginReward30Dao.deleteAll(userId);
					
					UserLoginReward30 newLogin = createUserLoginReward30(userId, 1);
					this.userLoginReward30Dao.addUserLoginReward30(newLogin);
				} else {
					UserLoginReward30 newLogin = createUserLoginReward30(userId, lastLogin.getDay() + 1);
					this.userLoginReward30Dao.addUserLoginReward30(newLogin);
				}
			}
		}
		
		List<UserLoginReward30> userList = this.userLoginReward30Dao.getUserLoginReward30List(userId);
		for (UserLoginReward30 userLoginReward : userList) {
			list.add(createUserLoginRewardBO(userLoginReward));
		}
		
		return list;
	}

	public int checkLoginRewardStatus30(String userId) {
		UserLoginReward30 lastLogin = this.userLoginReward30Dao.getLastLoginReward30(userId);
		if (lastLogin == null) {
			return NOT_RECEIVE;
		} else {
			if (lastLogin.getStatus() == NOT_RECEIVE) {
				return NOT_RECEIVE;
			} else {
				if (!DateUtils.isSameDay(new Date(), lastLogin.getUpdatedTime()))
					return NOT_RECEIVE;
			}
		}
		
		return HAS_RECEIVE;
	}
	
	// 活动状态 0 已开启 1 关闭
	public final static int STATUS_OPEN = 0;
	
	/**
	 * 检查活动是否开启
	 * 
	 * @param systemActivity
	 * @return
	 */
	public boolean checkActivityIsOpen(SystemActivity systemActivity) {
		if(systemActivity == null || systemActivity.getStatus() != STATUS_OPEN)
			throw new ServiceException(SystemErrorCode.ACTIVITY_IS_CLOSED, "该活动已经结束, 活动常量为null或者已关闭");
		
		Map<String, Date> dateMap = computeActivityTime(systemActivity);
		Date startTime = dateMap.get("startTime");
		Date endTime = dateMap.get("endTime");
		Date now = new Date();
		
		if(now.getTime() >= startTime.getTime() && now.getTime() <= endTime.getTime()) {
			//检测是否在配置的时间段内
			if(systemActivity.getOpenTime() != null && !systemActivity.getOpenTime().equals("")){
				int nowHour = DateUtils.getHour();
				int nowMinite = DateUtils.getMinute();
				
				//12:00-14:00|20:00-22:00		
				String[] strArray = systemActivity.getOpenTime().split("\\|");
				System.out.println("strArray=" + strArray);
				for(String oneStep : strArray) {
					//12:00-14:00
					String[] timesDistance = oneStep.split("-");
					String startStr = timesDistance[0];
					String endStr = timesDistance[1];
					
					//开始时间段解析
					String[] startArray = startStr.split(":");
					int startHour = Integer.valueOf(startArray[0]);
					int startMinute =  Integer.valueOf(startArray[1]);
					
					//结束时间段解析
					String[] endArray = endStr.split(":");
					int endHour = Integer.valueOf(endArray[0]);
					int endMinute =  Integer.valueOf(endArray[1]);
					if (nowHour >= startHour && nowHour <= endHour) {
						if(((nowHour == startHour && nowMinite >= startMinute) || nowHour > startHour) &&((nowHour == endHour && nowMinite <= endMinute) || nowHour < endHour))
							return true;
					}
				}
			} else {
				return true;					
			}
		}
			
		throw new ServiceException(SystemErrorCode.ACTIVITY_IS_CLOSED, "该活动已经结束,时间检测失败"+systemActivity.getActivityId()+"startTime="+startTime+",endTime="+endTime+",now Hour");
	}
	
	/**
	 * 根据活动在哪些天开放和现在的时间计算出活动的开始时间和结束时间
	 * 
	 * @param 活动的开放时间
	 *            ，例如：1,3,5 表示在星期一、二、五开放
	 * @return 活动的开始时间和结束时间
	 * @param opemWeeks
	 */
	private Map<String, Date> computeActivityTime(SystemActivity systemActivity) {
		Map<String, Date> startEndTime = Maps.newConcurrentMap();
		String openWeeks = systemActivity.getOpenWeeks();

		// 如果每天都开放，则将结束时间设置为无限远的时间
		if (StringUtils.isBlank(openWeeks) || openWeeks.equals("1,2,3,4,5,6,7")) {
			startEndTime.put("startTime", systemActivity.getStartTime());
			startEndTime.put("endTime", systemActivity.getEndTime());
			return startEndTime;
		}

		Date now = new Date();
		Date startTime = null;
		Date endTime = null;

		int dayOfWeek = DateUtils.getDayOfWeek(); // 当天这星期中的第几天
		if (openWeeks.indexOf(String.valueOf(dayOfWeek)) != -1) {// 当天有开放
			startTime = now;

			for (int i = 1; i <= 7; i++) {// 找结束时间
				Date date = DateUtils.addDays(now, i);
				int dayWeek = DateUtils.getDayOfWeek(date);
				if (openWeeks.indexOf(String.valueOf(dayWeek)) == -1) {// 不开放了
					endTime = DateUtils.getDateAtMidnight(date); // 那么那天的凌晨就是结束时间
					break;
				}
			}
		} else {// 当天没有开放
			for (int i = 1; i <= 7; i++) {// 找开始时间
				Date date = DateUtils.addDays(now, i);
				int dayWeek = DateUtils.getDayOfWeek(date);

				if (openWeeks.indexOf(String.valueOf(dayWeek)) != -1) {// 开放
					if (startTime == null) {
						startTime = DateUtils.getDateAtMidnight(date);// 那么那天的凌晨就是开始时间
					}
				} else if (startTime != null) {
					endTime = DateUtils.getDateAtMidnight(date);
				}
			}
		}

		startEndTime.put("startTime", startTime);
		if (systemActivity.getEndTime().before(endTime)) {
			startEndTime.put("endTime", systemActivity.getEndTime());
		} else {
			startEndTime.put("endTime", endTime);
		}

		return startEndTime;

	}
	
	private UserLoginReward30 createUserLoginReward30(String userId, int day) {
		UserLoginReward30 userLogin = new UserLoginReward30();
		userLogin.setUserId(userId);
		userLogin.setDay(day);
		userLogin.setCreatedTime(new Date());
		userLogin.setUpdatedTime(new Date());
		userLogin.setStatus(NOT_RECEIVE);
		
		return userLogin;
	}
	
	private UserLoginRewardBO createUserLoginRewardBO(UserLoginReward30 userLoginReward30) {
		UserLoginRewardBO bo = new UserLoginRewardBO();
		bo.setDay(userLoginReward30.getDay());
		bo.setStatus(userLoginReward30.getStatus());
		
		return bo;
	}
	
	public final static int RECIVE_NO_LOGIN_INFO = 2001;
	public final static int ERROR_HAS_RECEIVE = 2002;
	/**
	 * 领取签到奖励
	 * 
	 * @param userId
	 * @param day
	 * @return
	 */
	public ActivityAction_receiveLoginReward30Res receiveLoginReward30(String userId, int day) {		
		int maxDay = DateUtils.getMonthOfMaxDay(new Date());
		if (day < 1 || day > maxDay)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，day=" + day);
		
		//检验是否开启
		SystemActivity systemActivity = systemActivityDao.getSystemActivity(ActivityConstant.ACTIVITY_ID_LOGIN_30_DAY);
		checkActivityIsOpen(systemActivity);
		
		UserLoginReward30 loginReward = this.userLoginReward30Dao.getUserLoginReward30(userId, day);
		if (loginReward == null) {
			String message = "用户领取签到奖励失败,没有达到领取条件.userId[" + userId + "], 第[" + day + "] 天";
			throw new ServiceException(RECIVE_NO_LOGIN_INFO, message);
		}
		
		if (loginReward.getStatus() == HAS_RECEIVE)
			throw new ServiceException(ERROR_HAS_RECEIVE, "用户已领取");
		
		User user = this.userService.getByUserId(userId);
		SystemLoginReward30 reward = this.systemLoginReward30Dao.getSystemLoginReward30(day);
		GoodsBeanBO good = GoodsHelper.parseDropGood(reward.getToolType(), reward.getToolId(), reward.getToolNum());
		
		// vip双倍
		if (reward.getVipLevel() != 0 && user.getVipLevel() >= reward.getVipLevel()) {
			good = GoodsHelper.parseDropGood(reward.getToolType(), reward.getToolId(), reward.getToolNum() * 2);
		}		
		
		List<GoodsBeanBO> goodsList = Lists.newArrayList();
		goodsList.add(good);
		
		// 背包检查
		this.userService.checkBag(userId, goodsList);		
		this.userLoginReward30Dao.updateUserLoginReward30(userId, day, HAS_RECEIVE);
		CommonGoodsBeanBO drop = this.goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_LOGIN_REWARD_30);
		
		this.updateActvityTask(userId, ActivityTaskConstant.ACTIVITY_TASK_MONTH_LOGIN, 1);
		ActivityAction_receiveLoginReward30Res res = new ActivityAction_receiveLoginReward30Res();
		res.setDrop(drop);
		return res;
	}
	
	private final static int NOT_FINISH = 0;
	private final static int HAS_FINISH = 1;
	/**
	 * 获取用户活跃度信息
	 * 
	 * @param userId
	 * @return
	 */
	public ActivityAction_getActivityTaskInfoRes getActivityTaskInfo(String userId) {				
		List<UserActivityTask> userTaskList = refreshActivityTask(userId);
		List<UserActivityTaskBO> userActivityTaskList = Lists.newArrayList();
		
		if (userTaskList != null && userTaskList.size() > 0) {
			for (UserActivityTask task : userTaskList) {
				userActivityTaskList.add(createUserActivityTaskBO(task));
			}
		}
		
		List<UserActivityRewardLog> logList = this.userActivityRewardLogDao.getUserActivityRewardLogList(userId);
		List<UserActivityTaskRewardBO> rewardList = Lists.newArrayList();
		
		for (UserActivityRewardLog log : logList) {
			UserActivityTaskRewardBO bo = new UserActivityTaskRewardBO();
			bo.setActivityTaskRewardId(log.getActivityTaskRewardId());
			bo.setStatus(bo.getStatus());
			
			rewardList.add(bo);
		}
		
		ActivityAction_getActivityTaskInfoRes res = new ActivityAction_getActivityTaskInfoRes();
		res.setUserActivityTaskList(userActivityTaskList);
		res.setPoint(getUserActivityPoint(userId));
		res.setRewardLogList(rewardList);
		return res;
	}
	
	private UserActivityTaskBO createUserActivityTaskBO(UserActivityTask task) {
		UserActivityTaskBO taskBO = new UserActivityTaskBO();
		taskBO.setActivityTaskId(task.getActivityTaskId());
		taskBO.setFinishTimes(task.getFinishTimes());
		taskBO.setStatus(task.getStatus());
		
		return taskBO;
	}
	
	private List<UserActivityTask> refreshActivityTask(String userId) {
		List<UserActivityTask> userTaskList = this.userActivityTaskDao.getUserActivityTaskList(userId);
		List<SystemActivityTask> taskList = this.systemActivityTaskDao.getSystemActivityTaskList();
		if (taskList == null || taskList.size() == 0) {
			LogSystem.warn("系统活跃度的数据木有哦...........");
			return userTaskList;
		}
		
		if (userTaskList == null || userTaskList.size() != taskList.size()) {
			userTaskList = updateUserActivityTaskList(userId, taskList);
		} else {
			Date now = new Date();
			UserActivityTask userTask = userTaskList.get(0);
			if (!DateUtils.isSameDay(now, userTask.getCreatedTime()))
				userTaskList = updateUserActivityTaskList(userId, taskList);
		}
		return userTaskList;
	}
	
	/**
	 * 根据类型去获取用户活跃度信息
	 * 
	 * @param userId
	 * @param targetType
	 * @param times
	 * @return
	 */
	public boolean updateActvityTask(String userId, int targetType, int times) {
		Map<Integer, SystemActivityTask> map = this.systemActivityTaskDao.getActivityTaskByTarget();
		SystemActivityTask activityTask = map.get(targetType);
		if (activityTask == null) {// 没这种活跃度任务
			return true;
		}

		int activityTaskId = activityTask.getActivityTaskId();
		UserActivityTask userActivityTask = this.userActivityTaskDao.getUserActivityTask(userId, activityTaskId);

		if (userActivityTask == null) {// 没有这个任务，不应该出现
			//修补
			List<UserActivityTask> list = new ArrayList<UserActivityTask>();
			list.add(createUserActivityTask(userId, activityTask));
			userActivityTaskDao.addUserActivityTaskList(userId, list);
		}

		boolean result = false;
		if (userActivityTask.getFinishTimes() < activityTask.getNeedFinishTimes()) {// 次数没有超
			int status = NOT_FINISH;
			int finishTimes = userActivityTask.getFinishTimes() + times;
			if (finishTimes >= activityTask.getNeedFinishTimes()) {
				finishTimes = activityTask.getNeedFinishTimes();
				status = HAS_FINISH;
			}
			
			result = this.userActivityTaskDao.updateUserActivityTask(userId, activityTaskId, finishTimes, status);
		}
		
		if (result)
			getUserActivityTaskRewardBOList(userId);
		return result;
	}	
	
	private final static int TASK_CAN_NOT_RECEIVE = 0;
	private final static int TASK_NOT_RECEIVE = 1;
	private final static int TASK_HAS_RECEIVE = 2;
	
	public void getUserActivityTaskRewardBOList(String userId) {
		int userPoint = this.getUserActivityPoint(userId);

		List<SystemActivityTaskReward> activityTaskRewardList = this.systemActivityTaskRewardDao.getSystemActivityTaskRewardList();
		List<UserActivityRewardLog> userActivityRewardLogList = this.userActivityRewardLogDao.getUserActivityRewardLogList(userId);
		Set<Integer> recivedRewardIds = new HashSet<Integer>();
		for (UserActivityRewardLog userActivityRewardLog : userActivityRewardLogList) {
			recivedRewardIds.add(userActivityRewardLog.getActivityTaskRewardId());
		}

		List<UserActivityTaskRewardBO> userActivityTaskRewardBOList = new ArrayList<UserActivityTaskRewardBO>();

		for (SystemActivityTaskReward activityTaskReward : activityTaskRewardList) {
			UserActivityTaskRewardBO userActivityTaskRewardBO = new UserActivityTaskRewardBO();
			userActivityTaskRewardBO.setActivityTaskRewardId(activityTaskReward.getActivityTaskRewardId());
			int status = TASK_CAN_NOT_RECEIVE;
			if (userPoint >= activityTaskReward.getPoint()) {
				if (recivedRewardIds.contains(activityTaskReward.getActivityTaskRewardId())) {// 已经领取
					status = TASK_HAS_RECEIVE;
				} else {
					status = TASK_NOT_RECEIVE;
				}
			}
			
			userActivityTaskRewardBO.setStatus(status);
			userActivityTaskRewardBOList.add(userActivityTaskRewardBO);
		}

		if (userActivityTaskRewardBOList.size() > 0)
			notifyUserActivityTaskUpdate(userId, userActivityTaskRewardBOList, userPoint);
	}
	
	/**
	 * 更新客户端活跃度状态
	 * 
	 * @param userId
	 * @param list
	 */
	private void notifyUserActivityTaskUpdate(String userId, List<UserActivityTaskRewardBO> list, int userPoint) {
		Activity_updateActivityTaskNotify notify = new Activity_updateActivityTaskNotify();
		notify.setUpdateUserActivityTaskList(list);
		notify.setPoint(userPoint);
		MsgDispatchCenter.disPatchUserMsg("Activity_updateActivityTask", userId, notify);		
	}
	
	/**
	 * 获取用户活跃度
	 * 
	 * @param userId
	 * @return
	 */
	public int getUserActivityPoint(String userId) {
		List<UserActivityTask> userActivityTaskList = this.userActivityTaskDao.getUserActivityTaskList(userId);
		int point = 0;
		
		for (UserActivityTask userActivityTask : userActivityTaskList) {			
			if (userActivityTask.getStatus() != 1) {
				continue;
			}

			SystemActivityTask activityTask = this.systemActivityTaskDao.getSystemActivityTask(userActivityTask.getActivityTaskId());
			point += activityTask.getPoint();
		}

		return point;
	}
	
	/**
	 * 更新用户活跃度列表
	 * 
	 * @param userId
	 * @return
	 */
	private List<UserActivityTask> updateUserActivityTaskList(String userId, List<SystemActivityTask> taskList) {
		this.userActivityRewardLogDao.deleteRewardLog(userId);
		this.userActivityTaskDao.deleteUserActivityTaskList(userId);		

		List<UserActivityTask> userTaskList = Lists.newArrayList();
		for (SystemActivityTask task : taskList) {
			userTaskList.add(createUserActivityTask(userId, task));
		}
		
		userActivityTaskDao.addUserActivityTaskList(userId, userTaskList);
		return userTaskList;
	}
	
	private UserActivityTask createUserActivityTask(String userId, SystemActivityTask task) {
		UserActivityTask userTask = new UserActivityTask();
		userTask.setActivityTaskId(task.getActivityTaskId());
		userTask.setFinishTimes(0);
		userTask.setStatus(NOT_FINISH);
		userTask.setDate(DateUtils.str2Date(DateUtils.getDate() + " 00:00:00"));
		userTask.setUserId(userId);
		userTask.setCreatedTime(new Date());
		userTask.setUpdatedTime(new Date());
		
		return userTask;
	}
	
	private final static int NO_REWARD_CAN_RECEIVE = 2001;
	/**
	 * 领取活跃度奖励
	 * 
	 * @param userId
	 * @param activityTaskRewardId
	 * @return
	 */
	public ActivityAction_receiveActivityTaskRewardRes receiveActivityTaskReward(String userId) {
		List<SystemActivityTaskReward> rewardList = this.systemActivityTaskRewardDao.getSystemActivityTaskRewardList();
		if (rewardList == null || rewardList.size() == 0)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "没有活跃度奖励的数据......");
		
		int userPoint = this.getUserActivityPoint(userId);
		List<GoodsBeanBO> goodsList = Lists.newArrayList();
		List<Integer> getRewardIdList = Lists.newArrayList();
		
		Map<String, UserActivityRewardLog> userLogMap = this.userActivityRewardLogDao.getUserActivityRewardLogMap(userId);
		for (SystemActivityTaskReward activityTaskReward : rewardList) {
			if (userLogMap.containsKey(activityTaskReward.getActivityTaskRewardId()))
				continue;
			
			int needPoint = activityTaskReward.getPoint();
			if (userPoint < needPoint)
				continue;
			
			goodsList.addAll(GoodsHelper.parseDropGoods(activityTaskReward.getRewards()));
			getRewardIdList.add(activityTaskReward.getActivityTaskRewardId());
		}
		
		if (getRewardIdList.size() == 0)
			throw new ServiceException(NO_REWARD_CAN_RECEIVE, "没有奖励可以领取.......");
		
        //背包判断
		userService.checkBag(userId, goodsList);

		// 做领取日志
		List<UserActivityRewardLog> logList = Lists.newArrayList();
		for (Integer activityTaskRewardId : getRewardIdList) {
			UserActivityRewardLog userActivityRewardLog = new UserActivityRewardLog();
			userActivityRewardLog.setUserId(userId);
			userActivityRewardLog.setActivityTaskRewardId(activityTaskRewardId);
			userActivityRewardLog.setCreatedTime(new Date());
			
			logList.add(userActivityRewardLog);
		}
		this.userActivityRewardLogDao.addUserActivityRewardLogList(logList);

		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_ACTIVITY_TASK);
		ActivityAction_receiveActivityTaskRewardRes res = new ActivityAction_receiveActivityTaskRewardRes();
		res.setDrop(drop);
		return res;
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if(handlerType.equals(ModuleEventConstant.USER_LOGIN_EVENT)) {
			String userId = baseModuleEvent.getStringValue("userId", "");
			if (userId.equals(""))
				return;
			
			refreshActivityTask(userId);
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

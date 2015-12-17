package com.fantingame.game.mywar.logic.achievement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.constant.SystemConstant;
import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.msgbody.client.achievement.UserAchievementBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.notify.achievement.Achievement_updateNotify;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.dao.UserAchievementDao;
import com.fantingame.game.mywar.logic.achievement.dao.cache.SystemAchievementDaoCache;
import com.fantingame.game.mywar.logic.achievement.model.SystemAchievement;
import com.fantingame.game.mywar.logic.achievement.model.UserAchievement;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.task.helper.TaskHelper;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.google.common.collect.Lists;

public class AchievementService implements ModuleEventHandler {

	@Autowired
	private SystemAchievementDaoCache systemAchievementDao;
	@Autowired
	private UserAchievementDao userAchievementDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private GoodsDealService goodsDealService;
	
	private final static int HAS_NOT_FINISH = 0;
	private final static int HAS_FINISH = 1;
	private final static int HAS_RECEIVE = 2;
	
	/**
	 * 获取用户成就信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAchievementBO> getUserAchievementInfo(String userId) {
		User user = this.userService.getByUserId(userId);
		int openLevel = this.configDataDao.getInt(ConfigKey.achievement_open_level, 5);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到开放等级哦.....");
		
		List<UserAchievementBO> list = Lists.newArrayList();		
		List<SystemAchievement> systemAchievementList = this.systemAchievementDao.getSystemAchievementList();
		if (systemAchievementList == null || systemAchievementList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "没有成就系统的数据哦！！");
		
		List<UserAchievement> achievementList = this.userAchievementDao.getUserAchievementList(userId);
		// TODO 后续可能会有增加
		if (achievementList == null || systemAchievementList.size() != achievementList.size()) {
			List<UserAchievement> addList = Lists.newArrayList();
			Map<String, UserAchievement> map = this.userAchievementDao.getUserAchievementMap(userId);
			for (SystemAchievement systemAchievement : systemAchievementList) {
				if (!map.containsKey(systemAchievement.getAchievementId() + "")) {
					addList.add(createUserAchievement(userId, systemAchievement));
				}
			}
			
			this.userAchievementDao.addList(userId, addList);
			achievementList = this.userAchievementDao.getUserAchievementList(userId);
		}
		
		for (UserAchievement achievement : achievementList) {
			list.add(createUserAchievementBO(achievement));
		}		
			
		return list;	
	}	
	
	private UserAchievement createUserAchievement(String userId, SystemAchievement systemAchievement) {
		UserAchievement userAchievement = new UserAchievement();
		userAchievement.setUserId(userId);
		userAchievement.setAchievementId(systemAchievement.getAchievementId());
		userAchievement.setFinishTimes(0);
		userAchievement.setStatus(HAS_NOT_FINISH);
		userAchievement.setCreatedTime(new Date());
		userAchievement.setUpdatedTime(new Date());
		
		return userAchievement;
	}
	
	private UserAchievementBO createUserAchievementBO(UserAchievement userAchievement) {
		UserAchievementBO bo = new UserAchievementBO();
		bo.setAchievementId(userAchievement.getAchievementId());
		bo.setFinishTimes(userAchievement.getFinishTimes());
		bo.setStatus(userAchievement.getStatus());
		bo.setTime(userAchievement.getUpdatedTime().getTime());
		
		return bo;
	}
	
	private final static int ERROR_HAS_NOT_FINISH = 2001;
	private final static int ERROR_HAS_RECEIVE = 2002;
	/**
	 * 领取成就奖励
	 * 
	 * @param userId
	 * @param achievementId
	 * @return
	 */
	public CommonGoodsBeanBO receiveAchievementReward(String userId, int achievementId) {
		UserAchievement userAchievement = this.userAchievementDao.getUserAchievement(userId, achievementId);
		SystemAchievement systemAchievement = this.systemAchievementDao.getSystemAchievement(achievementId);
		
		if (userAchievement.getFinishTimes() < systemAchievement.getTimes() || userAchievement.getStatus() == HAS_NOT_FINISH)
			throw new ServiceException(ERROR_HAS_NOT_FINISH, "该成就未完成，userId=" + userId + ", achievementId=" + achievementId);
		
		if (userAchievement.getStatus() == HAS_RECEIVE)
			throw new ServiceException(ERROR_HAS_RECEIVE, "该成就的奖励已经领取了哦，userId=" + userId + ", achievementId=" + achievementId);
		
		List<GoodsBeanBO> goodsList = GoodsHelper.parseDropGoods(systemAchievement.getRewards());
		this.userService.checkBag(userId, goodsList);
		
		this.userAchievementDao.updateUserAchievement(userId, achievementId, userAchievement.getFinishTimes(), HAS_RECEIVE);
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, goodsList, GoodsUseType.ADD_RECEIVE_ACHIEVEMENT_REWARD);
		return drop;
	}
	
	/**
	 * 更新已完成
	 * 
	 * @param userId
	 * @param times
	 * @param type
	 * @param achievementChecker
	 */
	public void updateAchievementFinish(String userId, int times, int type, IAchievementChecker achievementChecker) {
		List<UserAchievement> userAchievementList = this.userAchievementDao.getUserAchievementList(userId, HAS_NOT_FINISH);
		List<UserAchievementBO> notifyList = Lists.newArrayList();
	
		for (UserAchievement userAchievement : userAchievementList) {
			int achievementId = userAchievement.getAchievementId();
			SystemAchievement systemAchievement = this.systemAchievementDao.getSystemAchievement(achievementId);
			if (systemAchievement == null)
				continue;
			
			if (systemAchievement.getType() != type)
				continue;
			
			Map<String, String> params = TaskHelper.parse(systemAchievement.getParam());			
			boolean isTrue = achievementChecker.isHit(achievementId, params);
			if (!isTrue)
				continue;
			
			int needFinishTimes = systemAchievement.getTimes();
			int finishTimes = userAchievement.getFinishTimes();
			int status = userAchievement.getStatus();
			
			finishTimes = finishTimes + times;
			if (finishTimes >= needFinishTimes) {
				finishTimes = needFinishTimes;
				status = HAS_FINISH;
			}
			
			// 更新成就
			if (this.userAchievementDao.updateUserAchievement(userId, achievementId, finishTimes, status)) {
				UserAchievementBO userAchievementBO = new UserAchievementBO();
				userAchievementBO.setFinishTimes(finishTimes);
				userAchievementBO.setStatus(status);
				userAchievementBO.setAchievementId(achievementId);
				
				notifyList.add(userAchievementBO);
			}	
		}
	
		if (notifyList.size() > 0 )
			notifyAchievementUpdate(userId, notifyList);
	}
	
	/**
	 * 更新客户端成就状态
	 * 
	 * @param userId
	 * @param list
	 */
	private void notifyAchievementUpdate(String userId, List<UserAchievementBO> list) {
		Achievement_updateNotify notify = new Achievement_updateNotify();
		notify.setUpdateUserAchievementList(list);
		MsgDispatchCenter.disPatchUserMsg("Achievement_update", userId, notify);		
	}

	private void openAchievement(String userId) {
		List<SystemAchievement> systemAchievementList = this.systemAchievementDao.getSystemAchievementList();
		if (systemAchievementList == null || systemAchievementList.size() == 0)
			return;
		
		List<UserAchievement> achievementList = this.userAchievementDao.getUserAchievementList(userId);
		// TODO 后续可能会有增加
		if (achievementList == null || systemAchievementList.size() != achievementList.size()) {
			List<UserAchievement> addList = Lists.newArrayList();
			Map<String, UserAchievement> map = this.userAchievementDao.getUserAchievementMap(userId);
			for (SystemAchievement systemAchievement : systemAchievementList) {
				if (!map.containsKey(systemAchievement.getAchievementId() + "")) {
					addList.add(createUserAchievement(userId, systemAchievement));
				}
			}
			
			this.userAchievementDao.addList(userId, addList);
		}
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if (handlerType.equals(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT) 
				|| handlerType.equals(ModuleEventConstant.USER_LOGIN_EVENT)) {
			//开启成就系统
			String userId = baseModuleEvent.getStringValue("userId", "");
			if (StringUtils.isNotBlank(userId))
				openAchievement(userId);
		}
	}

	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.AFTER_USER_CREAT_ROLE_EVENT);
		list.add(ModuleEventConstant.USER_LOGIN_EVENT);
		return list;
	}

	@Override
	public int order() {
		return 0;
	}
	
	/**
	 * 更新客户端成就状态
	 * 
	 * @param userId
	 * @param userAchievementBO
	 */
//	private void notifyAchievementUpdate(String userId, UserAchievementBO userAchievementBO) {
//		List<UserAchievementBO> list = Lists.newArrayList();
//		list.add(userAchievementBO);
//		notifyAchievementUpdate(userId, list);
//	}
}

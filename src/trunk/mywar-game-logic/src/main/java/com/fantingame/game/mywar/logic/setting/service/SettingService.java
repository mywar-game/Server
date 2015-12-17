package com.fantingame.game.mywar.logic.setting.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.setting.dao.UserSettingInfoDao;
import com.fantingame.game.mywar.logic.setting.dao.mysql.UserAdviceInfoDaoMysql;
import com.fantingame.game.mywar.logic.setting.model.UserAdviceInfo;
import com.fantingame.game.mywar.logic.setting.model.UserSettingInfo;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Maps;

public class SettingService{

	@Autowired
	private UserSettingInfoDao userSettingInfoDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserAdviceInfoDaoMysql userAdviceInfoDaoMysql;
	
	/**
	 * 设置同屏显示人数
	 * 
	 * @param userId
	 * @param num
	 */
	public int setDisplayNum(String userId, int num) {
		int displayLowerNum = this.configDataDao.getInt(ConfigKey.screen_display_lower_num, 10);
		int displayUpperNum = this.configDataDao.getInt(ConfigKey.screen_display_upper_num, 30);
		
		if (num < displayLowerNum || num > displayUpperNum)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，num:" + num);
		
		if ((num % 2) != 0)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，num:" + num);
		
		UserSettingInfo userSettingInfo = this.userSettingInfoDao.getUserSettingInfo(userId);		
		if (userSettingInfo == null) {
			userSettingInfo = new UserSettingInfo();
			userSettingInfo.setUserId(userId);
			userSettingInfo.setDisplayNum(num);
			userSettingInfo.setCreatedTime(new Date());
			userSettingInfo.setUpdatedTime(new Date());
			
			this.userSettingInfoDao.addUserSettingInfo(userSettingInfo);
		}
		
		this.userSettingInfoDao.updateUserSettingInfo(userId, num);		
		return num;
	}
	
	/**
	 * 获取用户同屏显示的人数
	 * 
	 * @param userId
	 * @return
	 */
	public int getDisplayNum(String userId) {
		UserSettingInfo info = this.userSettingInfoDao.getUserSettingInfo(userId);
		if (info == null)
			return -1;
		
		return info.getDisplayNum();
	}
	
	public UserSettingInfo getUserSettingInfo(String userId) {
		return this.userSettingInfoDao.getUserSettingInfo(userId);
	}
	
	private static final int TITLE_LENGTH_LIMIT = 2001;
	private static final int CONTENT_LENGTH_LIMIT = 2002;
	private static final int INTERNAL_NOT_ENOUGH = 2003;
	/**
	 * 提交建议
	 * 
	 * @param userId
	 * @param title
	 * @param content
	 */
	public void commitAdvice(String userId, String title, String content) {
		if (StringUtils.isBlank(title) || StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，有字数为空");
		
		int titleLimit = this.configDataDao.getInt(ConfigKey.advice_title_limit, 15);
		if (title.length() > titleLimit)
			throw new ServiceException(TITLE_LENGTH_LIMIT, "标题长度越界:" + title.length());
			
		int contentLimit = this.configDataDao.getInt(ConfigKey.advice_content_limit, 100);
		if (content.length() > contentLimit)
			throw new ServiceException(CONTENT_LENGTH_LIMIT, "内容长度越界:" + content.length());

		Date now = new Date();
		UserAdviceInfo userAdviceInfo = userAdviceInfoDaoMysql.getLastUserAdviceInfo(userId);
		if (userAdviceInfo != null) {
			long internal = now.getTime() - userAdviceInfo.getUpdatedTime().getTime();
			if (internal < (10 * 60 * 1000))
				throw new ServiceException(INTERNAL_NOT_ENOUGH, "隔十分钟才能才一次哦！！");			
		}
		
		userAdviceInfo = new UserAdviceInfo();
		userAdviceInfo.setUserId(userId);
		userAdviceInfo.setTitle(title);
		userAdviceInfo.setContent(content);
		userAdviceInfo.setCreatedTime(now);
		userAdviceInfo.setUpdatedTime(now);
		this.userAdviceInfoDaoMysql.addUserAdviceInfo(userAdviceInfo);
	}

	/**
	 * 设置提示
	 * 
	 * @param userId
	 * @param tip
	 */
	public void setDailyTips(String userId, int tip) {
		UserSettingInfo info = this.userSettingInfoDao.getUserSettingInfo(userId);
		if (info == null) {
			info = new UserSettingInfo();
			info.setUserId(userId);
			info.setDailyTips(tip + "");
			info.setDisplayNum(0);
			info.setCreatedTime(new Date());
			info.setUpdatedTime(new Date());
			
			this.userSettingInfoDao.addUserSettingInfo(info);
			return;
		}
		
		String tips = info.getDailyTips();
		String[] tipArr = tips.split(",");
		Map<Integer, Integer> map = Maps.newConcurrentMap();
		for (String tipStr : tipArr) {
			map.put(Integer.parseInt(tipStr), Integer.parseInt(tipStr));
		}
		
		if (map.containsKey(tip))
			return;
		
		tips = tips + "," + tip;
		this.userSettingInfoDao.updateUserDailyTips(userId, tips);
	}
	
	/**
	 * 刷新每日提示
	 */
	public void refreshDailyTips(String userId) {
		UserSettingInfo info = this.userSettingInfoDao.getUserSettingInfo(userId);
		if (info == null)
			return;
		
		if (!DateUtils.isSameDay(new Date(), info.getUpdatedTime())) {
			this.userSettingInfoDao.updateUserDailyTips(userId, "");
		}
	}

}

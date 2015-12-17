package com.fantingame.game.mywar.logic.friend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.msgbody.client.friend.BattleUserInfoBO;
import com.fantingame.game.msgbody.client.friend.FriendAction_auditApplyRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_deleteBlackRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getUserBlackListRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getUserFriendListRes;
import com.fantingame.game.msgbody.client.friend.FriendAction_getUserFriendRankListRes;
import com.fantingame.game.msgbody.client.friend.UserFriendInfoBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.mywar.logic.achievement.check.IAchievementChecker;
import com.fantingame.game.mywar.logic.achievement.constant.AchievementConstant;
import com.fantingame.game.mywar.logic.achievement.service.AchievementService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.friend.dao.UserBlackInfoDao;
import com.fantingame.game.mywar.logic.friend.dao.UserFriendInfoDao;
import com.fantingame.game.mywar.logic.friend.dao.UserInviteFightInfoDao;
import com.fantingame.game.mywar.logic.friend.model.UserBlackInfo;
import com.fantingame.game.mywar.logic.friend.model.UserFriendInfo;
import com.fantingame.game.mywar.logic.friend.model.UserInviteFightInfo;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.mail.constant.SystemMailConstant;
import com.fantingame.game.mywar.logic.mail.model.UserMail;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 好友聊天系统
 * 
 * @author yezp
 */
public class FriendService {
	
	@Autowired
	private UserFriendInfoDao userFriendInfoDao;
	@Autowired
	private UserBlackInfoDao userBlackInfoDao;
	@Autowired 
	private ConfigDataDaoCacheImpl configDataDao;	
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	@Autowired
	private HeroService heroService;
	@Autowired
	private UserInviteFightInfoDao userInviteFightInfoDao;
	@Autowired
	private AchievementService achievementService;
	
	// 申请好友
	private static final int APPLYING_FRIEND = 0;
	// 成功加为好友
	private static final int SUCCESS_IN_FRIEND = 1;
	
	/**
	 * 获取好友列表
	 * 
	 * @param userId
	 * @return
	 */
	public FriendAction_getUserFriendListRes getUserFriendList(String userId) {
		int openLevel = this.configDataDao.getInt(ConfigKey.friend_open_level, 10);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到好友开放等级");
		
		List<UserFriendInfo> infoList = this.userFriendInfoDao.getUserFriendList(userId, SUCCESS_IN_FRIEND);
		List<UserFriendInfoBO> infoBOList = new ArrayList<UserFriendInfoBO>();
		for (UserFriendInfo info : infoList) {
			User friendUser = this.userService.getByUserId(info.getUserFriendId());
			UserExtInfo userExtInfo = this.userService.getUserExtInfo(info.getUserFriendId());
			int isOnline = 0;
			if (this.userService.isOnline(info.getUserFriendId()))
				isOnline = 1;			
			
			UserFriendInfoBO infoBO = new UserFriendInfoBO();
			infoBO.setUserId(friendUser.getUserId());
			infoBO.setName(friendUser.getName());
			infoBO.setLevel(friendUser.getLevel());
			infoBO.setEffective(userExtInfo.getEffective());
			infoBO.setCamp(friendUser.getCamp());
			infoBO.setIsOnline(isOnline);
			UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(info.getUserFriendId());
			infoBO.setSystemHeroId(userHeroBO.getSystemHeroId());
			infoBOList.add(infoBO);
		}
		
		FriendAction_getUserFriendListRes res = new FriendAction_getUserFriendListRes();
		res.setUserFriendInfoBOList(infoBOList);
		return res;
	}

	/**
	 * 获取好友排行榜
	 * 
	 * @param userId
	 * @return
	 */
	public FriendAction_getUserFriendRankListRes getUserFriendRankList(String userId) {
		List<UserFriendInfo> list = this.userFriendInfoDao.getUserFriendList(userId, SUCCESS_IN_FRIEND);
		List<UserFriendInfo> infoList = sortRankByEffective(list);
		
		List<UserFriendInfoBO> infoBOList = new ArrayList<UserFriendInfoBO>();
		for (UserFriendInfo info : infoList) {
			User friendUser = this.userService.getByUserId(info.getUserFriendId());
			UserExtInfo userExtInfo = this.userService.getUserExtInfo(info.getUserFriendId());
			
			UserFriendInfoBO infoBO = new UserFriendInfoBO();
			infoBO.setUserId(friendUser.getUserId());
			infoBO.setName(friendUser.getName());
			infoBO.setLevel(friendUser.getLevel());
			infoBO.setEffective(userExtInfo.getEffective());
			infoBO.setCamp(friendUser.getCamp());
			UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(info.getUserFriendId());
			infoBO.setSystemHeroId(userHeroBO.getSystemHeroId());
			infoBOList.add(infoBO);
		}
		FriendAction_getUserFriendRankListRes res = new FriendAction_getUserFriendRankListRes();
		res.setUserFriendInfoBOList(infoBOList);
		return res;
	}
	
	/**
	 * 获取用户黑名单列表
	 * 
	 * @param userId
	 * @return
	 */
	public FriendAction_getUserBlackListRes getUserBlackList(String userId) {
		List<UserBlackInfo> infoList = this.userBlackInfoDao.getUserBlackInfoList(userId);
		List<UserFriendInfoBO> infoBOList = new ArrayList<UserFriendInfoBO>();
		
		for (UserBlackInfo info : infoList) {
			User blackUser = this.userService.getByUserId(info.getUserBlackId());
			UserExtInfo userExtInfo = this.userService.getUserExtInfo(info.getUserBlackId());
			
			UserFriendInfoBO infoBO = new UserFriendInfoBO();
			infoBO.setUserId(blackUser.getUserId());
			infoBO.setName(blackUser.getName());
			infoBO.setLevel(blackUser.getLevel());
			infoBO.setEffective(userExtInfo.getEffective());
			
			UserHeroBO blackUserHero = this.heroService.getUserTeamLeader(info.getUserBlackId());
			infoBO.setSystemHeroId(blackUserHero.getSystemHeroId());
			infoBO.setCamp(blackUser.getCamp());
			infoBOList.add(infoBO);
		}		
		
		FriendAction_getUserBlackListRes res = new FriendAction_getUserBlackListRes();
		res.setUserBlackInfoBOList(infoBOList);
		return res;
	}
	
	private static final int NOT_EXIST_USER = 2001;
	private static final int SELF_FRIEND_NUM_OF_FULL = 2002;
	private static final int TARGET_FRIEND_NUM_OF_FULL = 2003;
	private static final int HAS_APPLYING = 2004;
	private static final int HAS_SUCCESS_IN_FRIEND = 2005;
	private static final int HAS_ADD_BLACK = 2006;
	private static final int IN_TARGET_BLACKLIST = 2007;
	private static final int CHAT_TO_SELF = 2008;
	/**
	 * 申请添加好友
	 * 
	 * @param userId
	 * @param targetUserId
	 * @param name
	 */
	public void applyFriend(String userId, String targetUserId, String name) {
		int openLevel = this.configDataDao.getInt(ConfigKey.friend_open_level, 10);
		User user = this.userService.getByUserId(userId);
		if (user.getLevel() < openLevel)
			throw new ServiceException(SystemErrorCode.LEVEL_NOT_ENOUGH, "未到好友开放等级");
			
		if (StringUtils.isBlank(targetUserId) && StringUtils.isBlank(name))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误");
		
		// 判断用户是否存在
		if (StringUtils.isBlank(targetUserId)) {
			User targetUser = this.userService.getByUserName(name);
			if (targetUser == null)
				throw new ServiceException(NOT_EXIST_USER, "没有该用户");
			
			targetUserId = targetUser.getUserId();
		}	
		
		if (userId.equals(targetUserId))
			throw new ServiceException(CHAT_TO_SELF, "自己跟自己聊？？真无聊...");
		
		// 自己与对方好友是否上限
		int selfCount = this.userFriendInfoDao.getUserFriendCount(userId);		
		int maxNum = this.configDataDao.getInt(ConfigKey.friend_max_num, 50);
		if (selfCount >= maxNum) 
			throw new ServiceException(SELF_FRIEND_NUM_OF_FULL, "自己的好友上限已满");
		
		int targetCount = this.userFriendInfoDao.getUserFriendCount(targetUserId);
		if (targetCount > maxNum)
			throw new ServiceException(TARGET_FRIEND_NUM_OF_FULL, "对方好友已达上限");
		
		// 有什么仇什么怨
		UserBlackInfo userBlackInfo = this.userBlackInfoDao.getUserBlackInfo(userId, targetUserId);
		if (userBlackInfo != null) 
			throw new ServiceException(HAS_ADD_BLACK, "你已经把对方拉黑");
		
		userBlackInfo = this.userBlackInfoDao.getUserBlackInfo(targetUserId, userId);
		if (userBlackInfo != null) 
			throw new ServiceException(IN_TARGET_BLACKLIST, "你被对方拉黑...");
		
		UserFriendInfo userFriendInfo = this.userFriendInfoDao.getUserFriendInfo(userId, targetUserId);
		if (userFriendInfo != null && userFriendInfo.getStatus() == APPLYING_FRIEND)
			throw new ServiceException(HAS_APPLYING, "你已经申请");
		
		if (userFriendInfo != null && userFriendInfo.getStatus() == SUCCESS_IN_FRIEND)
			throw new ServiceException(HAS_SUCCESS_IN_FRIEND, "你们已经是好友");
		
		userFriendInfo = new UserFriendInfo();
		userFriendInfo.setUserId(userId);
		userFriendInfo.setUserFriendId(targetUserId);
		userFriendInfo.setStatus(APPLYING_FRIEND);
		userFriendInfo.setCreatedTime(new Date());
		userFriendInfo.setUpdatedTime(new Date());
		this.userFriendInfoDao.add(userFriendInfo);
		
		// 发送邮件通知对方
		List<String> param = new ArrayList<String>();
		param.add(user.getName());
		this.mailService.sendApplyFriend(targetUserId, param, SystemMailConstant.APPLY_MAILID, userId);
	}
	
	private static final int BLACK_SELF = 2001;
	private static final int HAS_IN_BLACKLIST = 2002;
	private static final int BLACKLIST_FULL_OF_NUM = 2003;
	/**
	 * 添加黑名单
	 * 
	 * @param userId
	 * @param targetUserId
	 */
	public void addBlack(String userId, String targetUserId) {
		if (StringUtils.isBlank(targetUserId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，参数为空");
		
		if (userId.equals(targetUserId))
			throw new ServiceException(BLACK_SELF, "亲，不要自黑哦...");
		
		UserBlackInfo userBlackInfo = this.userBlackInfoDao.getUserBlackInfo(userId, targetUserId);
		if (userBlackInfo != null)
			throw new ServiceException(HAS_IN_BLACKLIST, "你已经把人家拉黑了...");
		
		int blackNum = this.userBlackInfoDao.getUserBlackListCount(userId);
		int maxNum = this.configDataDao.getInt(ConfigKey.black_max_num, 50);
		if (blackNum >= maxNum)
			throw new ServiceException(BLACKLIST_FULL_OF_NUM, "黑名单已达上限");
		
		userBlackInfo = new UserBlackInfo();
		userBlackInfo.setUserId(userId);
		userBlackInfo.setUserBlackId(targetUserId);
		userBlackInfo.setCreatedTime(new Date());
		userBlackInfo.setUpdatedTime(new Date());
		this.userBlackInfoDao.add(userBlackInfo);
		
		// 删除好友关系
		this.userFriendInfoDao.deleteFriend(userId, targetUserId);
	}
	
	/**
	 * 根据战斗力排序
	 * 
	 * @param list
	 * @return
	 */
	private List<UserFriendInfo> sortRankByEffective(List<UserFriendInfo> list) {
		List<UserFriendInfo> collection = new ArrayList<UserFriendInfo>();
		collection.addAll(list);
		
		Collections.sort(collection, new Comparator<UserFriendInfo>() {
			@Override
			public int compare(UserFriendInfo infoI, UserFriendInfo infoX) {
				UserExtInfo userExtInfoI = userService.getUserExtInfo(infoI.getUserFriendId());
				UserExtInfo userExtInfoX = userService.getUserExtInfo(infoX.getUserFriendId());
				
				if (userExtInfoI.getEffective() > userExtInfoX.getEffective())
					return -1;
				else if (userExtInfoI.getEffective() < userExtInfoX.getEffective())
					return 1;
				return 0;
			}			
		});
		return collection;
	}

	
	
	// 1  同意 0 拒绝
	public final static int APPLY_AGREE = 1;
	public final static int APPLY_REJECT = 0;
	
	// 普通邮件类型
	public final static int NORMAL_MAIL_TYPE = 1;
	
	private final static int EXCEPTION_ALEADY_FRIEND = 2001;
	private final static int EXCEPTION_ALEADY_BLACK = 2002;
	private final static int EXCEPTION_YOU_IN_BLACK = 2003;
	private final static int EXCEPTION_SELF_FRIEND_NUM_FULL = 2004;
	private final static int EXCEPTION_FRIEND_NUM_FULL = 2005;
	/**
	 * 审核好友申请
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public FriendAction_auditApplyRes auditApply(String userId, int type, int userMailId) {
		if (type != APPLY_AGREE && type != APPLY_REJECT)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，type[" + type + "]");

		UserMail userMail = this.mailService.getUserMail(userId, userMailId);
		if (userMail == null)
			throw new ServiceException(SystemConstant.FAIL_CODE, "不存在该用户邮件，userId[" + userId + ", userMailId[" + userMailId + "]");
		
		String userFriendId = userMail.getFromUserId();		
		FriendAction_auditApplyRes res = new FriendAction_auditApplyRes();	
		User user = this.userService.getByUserId(userId);
		List<String> param = new ArrayList<String>();
		param.add(user.getName());
		
		// 小伙纸，你被拒绝啦
		if (type == APPLY_REJECT) {
			this.userFriendInfoDao.deleteFriend(userId, userFriendId);
			this.mailService.send(userFriendId, param, SystemMailConstant.REJECT_APPLY_MAILID);
			this.mailService.updateUserMail(userId, userMailId, NORMAL_MAIL_TYPE);
			return res;
		}
		
		// 同意
		UserFriendInfo userFriendInfo = this.userFriendInfoDao.getUserFriendInfo(userId, userFriendId);
		if (userFriendInfo != null && userFriendInfo.getStatus() == SUCCESS_IN_FRIEND)
			throw new ServiceException(EXCEPTION_ALEADY_FRIEND, "你们已是好基友");
		
		UserBlackInfo blackInfo = this.userBlackInfoDao.getUserBlackInfo(userId, userFriendId);
		if (blackInfo != null) {
			this.userFriendInfoDao.deleteFriend(userId, userFriendId);
			throw new ServiceException(EXCEPTION_ALEADY_BLACK, "你已将对方加黑名单");
		}
		
		blackInfo = this.userBlackInfoDao.getUserBlackInfo(userFriendId, userId);
		if (blackInfo != null) {
			this.userFriendInfoDao.deleteFriend(userId, userFriendId);
			throw new ServiceException(EXCEPTION_YOU_IN_BLACK, "你已被对方拉黑名单");
		}
		
		int maxNum = this.configDataDao.getInt(ConfigKey.friend_max_num, 50);
		int selfCount = this.userFriendInfoDao.getUserFriendCount(userId);
		if (selfCount >= maxNum) {
			this.userFriendInfoDao.deleteFriend(userId, userFriendId);
			throw new ServiceException(EXCEPTION_SELF_FRIEND_NUM_FULL, "用户的好友数量已满");
		}
		
		int targetCount = this.userFriendInfoDao.getUserFriendCount(userFriendId);
		if (targetCount >= maxNum) {
			this.userFriendInfoDao.deleteFriend(userId, userFriendId);
			throw new ServiceException(EXCEPTION_FRIEND_NUM_FULL, "对方的好友数量已满");
		}
		
		if (userFriendInfo != null) {
			this.userFriendInfoDao.updateUserFriendStatus(userFriendInfo, SUCCESS_IN_FRIEND);
		} else {
			userFriendInfo = new UserFriendInfo();
			userFriendInfo.setUserId(userId);
			userFriendInfo.setUserFriendId(userFriendId);
			userFriendInfo.setStatus(SUCCESS_IN_FRIEND);
			userFriendInfo.setCreatedTime(new Date());
			userFriendInfo.setUpdatedTime(new Date());
			this.userFriendInfoDao.add(userFriendInfo);
		}
		
		UserFriendInfo friendInfo = this.userFriendInfoDao.getUserFriendInfo(userFriendId, userId);
		this.userFriendInfoDao.updateUserFriendStatus(friendInfo, SUCCESS_IN_FRIEND);
		this.mailService.send(userFriendId, param, SystemMailConstant.AGREE_APPLY_MAILID);
		this.mailService.updateUserMail(userId, userMailId, NORMAL_MAIL_TYPE);
		
		// 自己的
		achievementService.updateAchievementFinish(userId, 1, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
			@Override
			public boolean isHit(int achievementId, Map<String, String> params) {
				boolean isHit = false;
				if (params.containsKey("friendNum"))
					isHit = true;
				
				return isHit;
			}
		});
		
		// 别人的
		achievementService.updateAchievementFinish(userFriendId, 1, AchievementConstant.TYPE_LIFE, new IAchievementChecker() {
			@Override
			public boolean isHit(int achievementId, Map<String, String> params) {
				boolean isHit = false;
				if (params.containsKey("friendNum"))
					isHit = true;
				
				return isHit;
			}
		});
		
		return res;
	}
	
	/**
	 * 删除黑名单
	 * 
	 * @param userId
	 * @param userBlackId
	 * @return
	 */
	public FriendAction_deleteBlackRes deleteBlack(String userId, String userBlackId) {
		this.userBlackInfoDao.deleteBlackInfo(userId, userBlackId);
		
		FriendAction_deleteBlackRes res = new FriendAction_deleteBlackRes();
		return res;
	}
	
	/**
	 * 获取用户的黑名单列表
	 * 
	 * @param userId
	 * @return
	 */
	private Map<String, String> getUserBlackInfoMap(String userId) {
		List<UserBlackInfo> infoList = this.userBlackInfoDao.getUserBlackInfoList(userId);
		Map<String, String> map = Maps.newConcurrentMap();
		for (UserBlackInfo info : infoList) {
			map.put(info.getUserBlackId(), info.getUserBlackId());
		}
	
		return map;
	}
	
	/**
	 * 获取用户黑名单
	 * 
	 * @param userId
	 * @param userBlackId
	 * @return
	 */
	public UserBlackInfo getUserBlackInfo(String userId, String userBlackId) {
		return this.userBlackInfoDao.getUserBlackInfo(userId, userBlackId);
	}
	
	/**
	 * 获取好友用户信息
	 * 
	 * @param userId
	 * @param userFriendId
	 * @return
	 */
	public UserFriendInfo getUserFriendInfo(String userId, String userFriendId) {
		return this.userFriendInfoDao.getUserFriendInfo(userId, userFriendId);
	}
	
	/**
	 * 你被谁拉黑了
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, String> getInTargetBlackMap(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		List<UserBlackInfo> list = this.userBlackInfoDao.getInTargetBlackList(userId);
		for (UserBlackInfo userBlackInfo : list) {
			map.put(userBlackInfo.getUserId(), userBlackInfo.getUserId());
		}
		
		return map;
	}
	
	/**
	 * 获取你被谁拉黑列表
	 * 
	 * @param userId
	 * @return
	 */
	public String[] getInUserBlackIds(String userId) {
		// List<String> list = this.userBlackInfoDao.getInTargetBlackIds(userId);
		List<UserBlackInfo> infoList = this.userBlackInfoDao.getInTargetBlackList(userId);
		String userIds = userId;
		for (UserBlackInfo info : infoList) {			
			userIds = userIds + "," + info.getUserId();
		}
		
		if (userIds == null)
			return null;
		
		return userIds.split(",");
	}
	
	/**
	 * 获取可参战用户列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<BattleUserInfoBO> getJoinBattleUserList(String userId) {
		List<BattleUserInfoBO> battleList = Lists.newArrayList();
		User user = this.userService.getByUserId(userId);
		
		List<UserFriendInfo> infoList = this.userFriendInfoDao.getUserFriendList(userId, SUCCESS_IN_FRIEND);
		if (infoList != null && infoList.size() > 0) {
			Map<String, String> map = getUserInviteFightInfoMap(userId);
			for (UserFriendInfo info : infoList) {				
				BattleUserInfoBO bo = createBattleUserInfoBO(info.getUserFriendId(), 1, user.getCamp());
				if (bo == null)
					continue;
				
				if (map.containsKey(info.getUserFriendId()))
					continue;
				
				battleList.add(bo);
				if (battleList.size() >= 20)
					return battleList;
			}
		}
		
		List<User> userList = this.userService.getJoinBattleUserList(userId, user.getCamp());
		if (userList == null || userList.size() == 0)
			return battleList;
		
		// userList = this.userService.sortRankByEffective(userList);
		Map<String, String> blackUserIdMap = getUserBlackInfoMap(userId);
		for (User userI : userList) {
			// 过滤自己
			if (userI.getUserId().equals(userId))
				continue;
			
			// 过滤黑名单
			if (blackUserIdMap.containsKey(userI.getUserId()))
				continue;
			
			BattleUserInfoBO bo = createBattleUserInfoBO(userI.getUserId(), 0, user.getCamp());
			if (bo == null)
				continue;
			
			battleList.add(bo);			
			if (battleList.size() >= 20)
				return battleList;
		}		
		
		return battleList;
	}
	
	private BattleUserInfoBO createBattleUserInfoBO(String userId, int isFriend, int camp) {
		BattleUserInfoBO infoBO = new BattleUserInfoBO();
		User user = this.userService.getByUserId(userId);
		UserExtInfo userExtInfo = this.userService.getUserExtInfo(userId);
		UserHeroBO userHeroBO = heroService.getUserTeamLeader(userId);
		if (userHeroBO == null)
			return null;
		
		if (user.getCamp() != camp)
			return null;
		
		infoBO.setUserId(userId);
		infoBO.setIsFriend(isFriend);
		infoBO.setName(user.getName());
		infoBO.setEffective(userExtInfo.getEffective());
		infoBO.setSystemHeroId(userHeroBO.getSystemHeroId());
		infoBO.setLevel(user.getLevel());
		return infoBO;
	}

	private Map<String, String> getUserInviteFightInfoMap(String userId) {
		List<UserInviteFightInfo> infoList = this.userInviteFightInfoDao.getUserInviteFightInfoList(userId);
		Map<String, String> map = Maps.newConcurrentMap();
		
		for (UserInviteFightInfo info : infoList) {
			map.put(info.getInviteUserId(), info.getInviteUserId());
		}
		return map;
	}
	
	public boolean addInviteUser(String userId, String userFriendId) {
		UserFriendInfo userFriendInfo = this.getUserFriendInfo(userId, userFriendId);
		if (userFriendInfo == null)
			return true;
		
		Date now = new Date();
		List<UserInviteFightInfo> inviteList = this.userInviteFightInfoDao.getUserInviteFightInfoList(userId);
		if (inviteList != null && inviteList.size() > 0) {
			// 每天清除一次记录
			UserInviteFightInfo inviteInfo = inviteList.get(0);
			if (!DateUtils.isSameDay(now , inviteInfo.getUpdatedTime()))
				userInviteFightInfoDao.deleteInviteFightList(userId);			
		}
		
		UserInviteFightInfo inviteInfo = this.userInviteFightInfoDao.getUserInviteFightInfo(userId, userFriendId);
		if (inviteInfo != null)
			return true;
		
		inviteInfo = new UserInviteFightInfo();
		inviteInfo.setUserId(userId);
		inviteInfo.setInviteUserId(userFriendId);
		inviteInfo.setCreatedTime(now);
		inviteInfo.setUpdatedTime(now);		
		return this.userInviteFightInfoDao.addUserInviteFightInfo(inviteInfo);
	}
	
}

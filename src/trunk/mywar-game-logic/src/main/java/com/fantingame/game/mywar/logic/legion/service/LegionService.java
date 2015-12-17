package com.fantingame.game.mywar.logic.legion.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.Constant;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.common.utils.IllegalWordUtills;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.legion.LegionAction_appointLegionDeputyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_auditingApplyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_commitMessageRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_createLegionRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_deposeLegionDeputyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_editDeclarationRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_editNoticeRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_fireLegionMemberRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getApplyListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getLegionInfoRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getLegionListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getLegionMemberListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getMessageListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getUserInvestInfoRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_transferLegionLeaderRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_userInvestRes;
import com.fantingame.game.msgbody.client.legion.LegionInfoBO;
import com.fantingame.game.msgbody.client.legion.LegionMemberBO;
import com.fantingame.game.msgbody.client.legion.UserApplyLegionBO;
import com.fantingame.game.msgbody.client.legion.UserLegionInfoBO;
import com.fantingame.game.msgbody.client.legion.UserMessageInfoBO;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.chat.service.ChatService;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.ModuleEventConstant;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.hero.service.HeroService;
import com.fantingame.game.mywar.logic.legion.dao.LegionInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.UserApplyLegionInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.UserInvestInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.UserLegionInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.UserMessageInfoDao;
import com.fantingame.game.mywar.logic.legion.dao.cache.SystemLegionInvestDaoCache;
import com.fantingame.game.mywar.logic.legion.dao.cache.SystemLegionLevelDaoCache;
import com.fantingame.game.mywar.logic.legion.model.LegionInfo;
import com.fantingame.game.mywar.logic.legion.model.SystemLegionInvest;
import com.fantingame.game.mywar.logic.legion.model.SystemLegionLevel;
import com.fantingame.game.mywar.logic.legion.model.UserApplyLegionInfo;
import com.fantingame.game.mywar.logic.legion.model.UserInvestInfo;
import com.fantingame.game.mywar.logic.legion.model.UserLegionInfo;
import com.fantingame.game.mywar.logic.legion.model.UserMessageInfo;
import com.fantingame.game.mywar.logic.mail.constant.SystemMailConstant;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.model.UserExtInfo;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class LegionService implements ModuleEventHandler {

	@Autowired
	private LegionInfoDao legionInfoDao;
	@Autowired
	private UserLegionInfoDao userLegionInfoDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserService userService;
	@Autowired
	private SystemLegionLevelDaoCache systemLegionLevelDaoCache;
	@Autowired
	private UserApplyLegionInfoDao userApplyLegionInfoDao;
	@Autowired
	private MailService mailService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private UserMessageInfoDao userMessageInfoDao;
	@Autowired
	private HeroService heroService;
	@Autowired
	private UserInvestInfoDao userInvestInfoDao;
	@Autowired
	private SystemLegionInvestDaoCache systemLegionInvestDaoCache;
	
	// 1 军团成员 2 副军团长 3 军团长
	private final static int IDENTITY_LEGION_MEMBER = 1;
	private final static int IDENTITY_LEGION_DEPUTY = 2;
	private final static int IDENTITY_LEGION_LEADER = 3;
	
	// 1 在军团内 2 离开军团 3 被开除 4 解散军团
	private final static int USER_IN_LEGION = 1;
	private final static int USER_LEAVE_LEGION = 2;
	private final static int USER_BE_FIRE_LEGION = 3;
	private final static int USER_DISMISS_LEGION  = 4;
	
	private final static int ERROR_HAS_IN_LEGION = 2001;
	private final static int ERROR_LEGIONNAME_INVAILD = 2002;
	private final static int ERROR_LEGIONNAME_TOO_LOOG = 2003;
	private final static int ERROR_LEGIONNAME_EXIST = 2004;
	
	/**
	 * 创建军团
	 * 
	 * @param userId
	 * @param legionName
	 * @param type 1 钻石 2 金币
	 */
	public LegionAction_createLegionRes createLegion(String userId, String legionName, int type) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo != null && userLegionInfo.getStatus() == USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_IN_LEGION, "已经在军团内，userId=" + userId);
		
		if (StringUtils.isBlank(legionName))
			throw new ServiceException(ERROR_LEGIONNAME_INVAILD, "军团名称不能为空");
		
		int maxCharNum = this.configDataDao.getInt(ConfigKey.legion_name_max_char, 12);
		if (Constant.getBytes(legionName) > maxCharNum)
			throw new ServiceException(ERROR_LEGIONNAME_TOO_LOOG, "军团名称太长了");
			
		legionName = IllegalWordUtills.replaceWords(legionName);
		if (this.legionInfoDao.isExitLegionName(legionName))
			throw new ServiceException(ERROR_LEGIONNAME_EXIST, "军团名称已存在,legionName=" + legionName);
		
		User user = userService.getByUserId(userId);
		// 检查钻石、金币，并扣除
		if (type == 1) {
			int diamond = this.configDataDao.getInt(ConfigKey.legion_create_diamond_cost, 300);
			if (user.getMoney() < diamond)
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "没钱学人家建什么公会！！！");
			
			if (this.userService.reduceMoney(userId, diamond, GoodsUseType.REDUCE_LEGION_CREATE))
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "没钱学人家建什么公会！！！");			
		} else {
			int gold = this.configDataDao.getInt(ConfigKey.legion_create_gold_cost, 1000000);
			if (user.getGold() < gold)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "连金币都没有，学人家建什么公会！！！");
			
			if (this.userService.reduceGold(userId, gold, GoodsUseType.REDUCE_LEGION_CREATE))
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "连金币都没有，学人家建什么公会！！！");
		}
		
		// 创建军团
		UserExtInfo extInfo = this.userService.getUserExtInfo(userId);
		LegionInfo legionInfo = createLegionInfo(userId, legionName, extInfo.getEffective());
		this.legionInfoDao.addLegionInfo(legionInfo);
		
		SystemLegionLevel systemLegionLevel = this.systemLegionLevelDaoCache.getSystemLegionLevel(legionInfo.getLevel());
		int maxNum = 10;
		if (systemLegionLevel != null)
			maxNum = systemLegionLevel.getPersonNum();
		
		LegionInfoBO legionInfoBO = createLegionInfoBO(legionInfo, user.getName(), maxNum);		
		// 生成自己的军团信息
		if (userLegionInfo == null) {		
			userLegionInfo = createUserLegionInfo(userId, legionInfo.getLegionId(), IDENTITY_LEGION_LEADER);		
			this.userLegionInfoDao.addUserLegionInfo(userLegionInfo);
		} else {
			this.userApplyLegionInfoDao.deleteUserApplyLegionInfo(userId);
			
			// TODO 7天内加入军团 扣除30%的贡献值
			int contribution = returnUserContribution(userLegionInfo);
			this.userLegionInfoDao.updateUserlegionInfo(userId, legionInfo.getLegionId(), USER_IN_LEGION, IDENTITY_LEGION_LEADER, contribution);
			userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		}
		
		UserLegionInfoBO userLegionInfoBO = createUserLegionInfoBO(userLegionInfo);		
		LegionAction_createLegionRes res = new LegionAction_createLegionRes();
		res.setLegionInfo(legionInfoBO);
		res.setUserLegionInfo(userLegionInfoBO);
		return res;
	}
	
	/**
	 * 返回用户的贡献值
	 * 
	 * @param userLegionInfo
	 * @return
	 */
	private int returnUserContribution(UserLegionInfo userLegionInfo) {
		int validDay = this.configDataDao.getInt(ConfigKey.legion_join_again_valid, 7);
		int returnValue = this.configDataDao.getInt(ConfigKey.legion_return_contribution, 70);
		
		Date now = new Date();
		Date endTime = new Date(now.getTime() - validDay * 24 * 3600 * 1000);
		if (userLegionInfo.getUpdatedTime().before(endTime))
			return 0;
		
		int contribution = userLegionInfo.getContribution() * (returnValue / 100);
		return contribution;
	}
	
	private final static int ERROR_APPLY_NUM_OVER = 2002;
	/**
	 * 申请加入公会
	 * 
	 * @param userId
	 * @param legionId
	 */
	public void applyJoinLegion(String userId, String legionId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo != null && userLegionInfo.getStatus() == USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_IN_LEGION, "你已经加入了公会了丫，还请求个毛毛");
		
		List<UserApplyLegionInfo> applyList = this.userApplyLegionInfoDao.getUserApplyLegionInfoList(userId);
		int applyMax = this.configDataDao.getInt(ConfigKey.legion_apply_max, 3);
		if (applyList.size() >= applyMax)
			throw new ServiceException(ERROR_APPLY_NUM_OVER, "申请数量超了");
		
		UserApplyLegionInfo applyInfo = new UserApplyLegionInfo();
		applyInfo.setUserId(userId);
		applyInfo.setLegionId(legionId);
		applyInfo.setCreatedTime(new Date());
		applyInfo.setUpdatedTime(new Date());
		
		this.userApplyLegionInfoDao.addUserApplyLegionInfo(applyInfo);
	}
	
	/**
	 * 取消申请
	 * 
	 * @param userId
	 * @param legionId
	 */
	public void cancleApply(String userId, String legionId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo != null && userLegionInfo.getStatus() == USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_IN_LEGION, "你已经加入了公会了丫，还取消个毛毛");
		
		UserApplyLegionInfo applyInfo = this.userApplyLegionInfoDao.getUserApplyLegionInfo(userId, legionId);
		if (applyInfo == null)
			return;
		
		this.userApplyLegionInfoDao.deleteUserApplyLegionInfo(userId, legionId);		
	}
	
	/**
	 * 查看公会列表
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_getLegionListRes getLegionList(String userId) {
		List<LegionInfo> legionInfoList = this.legionInfoDao.getLegionInfoList();
		List<LegionInfoBO> rankList = Lists.newArrayList();
		List<LegionInfoBO> recommendList = Lists.newArrayList();
		
		for (LegionInfo legionInfo : legionInfoList) {
			SystemLegionLevel systemLegionLevel = this.systemLegionLevelDaoCache.getSystemLegionLevel(legionInfo.getLevel());
			int maxNum = 30;
			if (systemLegionLevel != null)
				maxNum = systemLegionLevel.getPersonNum();
			
			User user = this.userService.getByUserId(userId);
			LegionInfoBO infoBO = createLegionInfoBO(legionInfo, user.getName(), maxNum);
			rankList.add(infoBO);
			
			if (infoBO.getCurrentNum() < infoBO.getMaxNum())
				recommendList.add(infoBO);
		}
		
		LegionAction_getLegionListRes res = new LegionAction_getLegionListRes();
		res.setLegionRankList(sortLegionInfoByPower(rankList));
		res.setRecommendLegionList(sortLegionInfoByPower(recommendList));
		return res;
	}
	
	/**
	 * 根据战斗力排行
	 * 
	 * @param legionInfoList
	 * @return
	 */
	private List<LegionInfoBO> sortLegionInfoByPower(List<LegionInfoBO> legionInfoList) {
		Collections.sort(legionInfoList, new Comparator<LegionInfoBO>() {
			@Override
			public int compare(LegionInfoBO o1, LegionInfoBO o2) {
				if (o1.getPower() > o2.getPower())
					return -1;
				else if (o1.getPower() < o2.getPower())
					return 1;	
				return 0;
			}
		});
		
		return legionInfoList;
	}
	
	private final static int ERROR_NOT_IN_LEGION = 2001;
	private final static int ERROR_NO_POWER = 2004;
	private final static int ERROR_LEGION_DISMISS = 2005;
	/**
	 * 编辑公告
	 * 
	 * @param userId
	 * @param notice
	 * @return
	 */
	public LegionAction_editNoticeRes editNotice(String userId, String notice) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "你已被开除");
			
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_MEMBER)
			throw new ServiceException(ERROR_NO_POWER, "没有这个权限");
		
		if (StringUtils.isBlank(notice))
			throw new ServiceException(ERROR_LEGIONNAME_INVAILD, "内容不能为空");
		
		int maxCharNum = this.configDataDao.getInt(ConfigKey.legion_notice_max, 30) * 2;
		if (Constant.getBytes(notice) > maxCharNum)
			throw new ServiceException(ERROR_LEGIONNAME_TOO_LOOG, "内容超出");		
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		notice = IllegalWordUtills.replaceWords(notice);
		this.legionInfoDao.updateLegionNotice(userLegionInfo.getLegionId(), notice);
		
		LegionAction_editNoticeRes res = new LegionAction_editNoticeRes();
		res.setNotice(notice);
		return res;
	}
	
	/**
	 * 修改军团宣言
	 * 
	 * @param userId
	 * @param declaration
	 * @return
	 */
	public LegionAction_editDeclarationRes editDeclaration(String userId, String declaration) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "你已被开除");
			
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_MEMBER)
			throw new ServiceException(ERROR_NO_POWER, "没有这个权限");
		
		if (StringUtils.isBlank(declaration))
			throw new ServiceException(ERROR_LEGIONNAME_INVAILD, "内容不能为空");
		
		int maxCharNum = this.configDataDao.getInt(ConfigKey.legion_declaration_max, 30) * 2;
		if (Constant.getBytes(declaration) > maxCharNum)
			throw new ServiceException(ERROR_LEGIONNAME_TOO_LOOG, "内容超出");		
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		declaration = IllegalWordUtills.replaceWords(declaration);
		this.legionInfoDao.updateLegionDeclaration(userLegionInfo.getLegionId(), declaration);
		
		LegionAction_editDeclarationRes res = new LegionAction_editDeclarationRes();
		res.setDeclaration(declaration);
		return res;		
	}
	
	/**
	 * 获取用户军团信息
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_getLegionInfoRes getLegionInfo(String userId) {
		UserLegionInfoBO userLegionInfoBO = new UserLegionInfoBO();
		LegionInfoBO legionInfoBO = new LegionInfoBO();
		
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		LegionAction_getLegionInfoRes res = new LegionAction_getLegionInfoRes();
		List<String> applyLegionIdList = Lists.newArrayList();
		
		// 尚未加入军团
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION) {
			List<UserApplyLegionInfo> applyList = this.userApplyLegionInfoDao.getUserApplyLegionInfoList(userId);
			for (UserApplyLegionInfo applyInfo : applyList) {
				applyLegionIdList.add(applyInfo.getLegionId());
			}
			
			res.setApplyLegionIdList(applyLegionIdList);
			res.setLegionInfo(legionInfoBO);
			res.setUserLegionInfoBO(userLegionInfoBO);
			return res;
		}			
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		User leader = this.userService.getByUserId(legionInfo.getLegionLeader());
		
		SystemLegionLevel systemLegionLevel = this.systemLegionLevelDaoCache.getSystemLegionLevel(legionInfo.getLevel());
		int maxNum = 30;
		if (systemLegionLevel != null)
			maxNum = systemLegionLevel.getPersonNum();
		legionInfoBO = createLegionInfoBO(legionInfo, leader.getName(), maxNum);
		
		res.setUserLegionInfoBO(createUserLegionInfoBO(userLegionInfo));
		res.setApplyLegionIdList(applyLegionIdList);
		res.setLegionInfo(legionInfoBO);
		return res;
	}

	/**
	 * 获取用户申请加入军团的列表
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_getApplyListRes getApplyList(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_MEMBER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		int validDay = this.configDataDao.getInt(ConfigKey.legion_apply_valid, 3);
		Date now = new Date();
		Date endTime = new Date(now.getTime() - validDay * 24 * 3600 * 1000);
		List<UserApplyLegionInfo> applyList = this.userApplyLegionInfoDao.getUserApplyLegionInfoList(userLegionInfo.getLegionId(), endTime);
		
		List<UserApplyLegionBO> applyLegionList = Lists.newArrayList();
		for (UserApplyLegionInfo applyInfo : applyList) {
			UserApplyLegionBO applyBO = new UserApplyLegionBO();
			User user = this.userService.getByUserId(applyInfo.getUserId());
			UserExtInfo extInfo = this.userService.getUserExtInfo(applyInfo.getUserId());
			
			applyBO.setUserId(applyInfo.getUserId());
			applyBO.setUserName(user.getName());
			applyBO.setLevel(user.getLevel());
			applyBO.setEffective(extInfo.getEffective());
			applyLegionList.add(applyBO);
		}
		
		LegionAction_getApplyListRes res = new LegionAction_getApplyListRes();
		res.setUserApplyList(applyLegionList);
		return res;
	}
	
	/**
	 * 一键拒绝
	 * 
	 * @param userId
	 */
	public void oneClickReject(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_MEMBER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		List<UserApplyLegionInfo> applyList = this.userApplyLegionInfoDao.getListByLegionId(userLegionInfo.getLegionId());
		this.userApplyLegionInfoDao.deleteUserApplyLegionInfo(userLegionInfo.getLegionId(), applyList);		
		
		int validDay = this.configDataDao.getInt(ConfigKey.legion_apply_valid, 3);
		Date now = new Date();
		Date endTime = new Date(now.getTime() - validDay * 24 * 3600 * 1000);
		
		List<String> userIds = Lists.newArrayList();
		for (UserApplyLegionInfo applyInfo : applyList) {
			if (applyInfo.getUpdatedTime().before(endTime))
				continue;
			
			userIds.add(applyInfo.getUserId());
		}
		
		if (userIds.size() > 0) {
			List<String> paramList = Lists.newArrayList();
			paramList.add(legionInfo.getLegionName());
			mailService.send(userIds, paramList, SystemMailConstant.APPLY_JOIN_LEGION_BEREJECT);
		}
	}
	
	// 1 同意  2 拒绝
	private final static int AUDITING_TYPE_REJECT = 2;
	
	private final static int ERROR_HAS_CANCLE_APPLY = 2006;
	private final static int ERROR_HAS_IN_OTHER_LEGION = 2007;
	/**
	 * 审核申请
	 * 
	 * @param userId
	 * @param type
	 * @param auditingUserId
	 * @return
	 */
	public LegionAction_auditingApplyRes auditingApply(String userId, int type, String auditingUserId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_MEMBER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		LegionAction_auditingApplyRes res = new LegionAction_auditingApplyRes();
		res.setAuditingUserId(auditingUserId);
		String legionId = userLegionInfo.getLegionId();
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		List<String> paramList = Lists.newArrayList();
		paramList.add(legionInfo.getLegionName());
		
		// 拒绝
		if (type == AUDITING_TYPE_REJECT) {			
			this.userApplyLegionInfoDao.deleteUserApplyLegionInfo(auditingUserId, legionId);
			this.mailService.send(auditingUserId, paramList, SystemMailConstant.APPLY_JOIN_LEGION_BEREJECT);
			return res;
		}

		// 同意
		int count = this.userLegionInfoDao.getLegionCurrentNum(legionId);		
		SystemLegionLevel legionLevel = this.systemLegionLevelDaoCache.getSystemLegionLevel(legionInfo.getLevel());
		if (legionLevel.getPersonNum() <= count)
			throw new ServiceException(ERROR_APPLY_NUM_OVER, "人数已满");
		
		UserApplyLegionInfo applyInfo = this.userApplyLegionInfoDao.getUserApplyLegionInfo(auditingUserId, legionId);
		if (applyInfo == null)
			throw new ServiceException(ERROR_HAS_CANCLE_APPLY, "这个家伙已取消申请");
		
		// 加入军团，更新军团战斗力
		UserLegionInfo auditingUserInfo = this.userLegionInfoDao.getUserLegionInfo(auditingUserId);
		if (auditingUserInfo != null && auditingUserInfo.getStatus() == USER_IN_LEGION && !auditingUserInfo.getLegionId().equals(legionId))
			throw new ServiceException(ERROR_HAS_IN_OTHER_LEGION, "已经加入了另一个军团");
		
		if (auditingUserInfo != null && auditingUserInfo.getStatus() == USER_IN_LEGION && auditingUserInfo.getLegionId().equals(legionId)) {
			LogSystem.warn("auditingUserId=" + auditingUserId + "已经加入了军团");
			return res;
		}
		
		if (auditingUserInfo == null) {
			auditingUserInfo = createUserLegionInfo(auditingUserId, legionId, IDENTITY_LEGION_MEMBER);
			this.userLegionInfoDao.addUserLegionInfo(auditingUserInfo);				
		} else {
			// TODO 7天内加入军团 扣除30%的贡献值			
			int contribution = returnUserContribution(userLegionInfo);
			this.userLegionInfoDao.updateUserlegionInfo(auditingUserId, legionId, USER_IN_LEGION, IDENTITY_LEGION_MEMBER, contribution);
		}
		
		// 删掉该用户的所有申请
		this.userApplyLegionInfoDao.deleteUserApplyLegionInfo(auditingUserId);		
		// 更新军团战斗力
		UserExtInfo auditingExtInfo = this.userService.getUserExtInfo(auditingUserId);		
		int totalPower = legionInfo.getPower() + auditingExtInfo.getEffective();
		this.legionInfoDao.updateLegionPower(legionId, totalPower);		
		this.mailService.send(auditingUserId, paramList, SystemMailConstant.APPLY_JOIN_LEGION_SUCCESS);
		
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("userName", this.userService.getByUserId(auditingUserId).getName());
		this.chatService.legionOfChat(ChatConstant.WELCOME_JOIN_LEGION_CHATID, legionId, params);
		return res;
	}
	
	private final static int ERROR_HAS_LEAVE_LEGION = 2008;
	/**
	 * 开除会员
	 * 
	 * @param userId
	 * @param fireUserId
	 * @return
	 */
	public LegionAction_fireLegionMemberRes fireLegionMember(String userId, String fireUserId){
		if (userId.equals(fireUserId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "自己开除自己？？");
		
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_MEMBER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		LegionAction_fireLegionMemberRes res = new LegionAction_fireLegionMemberRes();
		res.setFireUserId(fireUserId);
		String legionId = legionInfo.getLegionId();
		
		// 判断权限
		UserLegionInfo fireUserInfo = this.userLegionInfoDao.getUserLegionInfo(fireUserId);
		if (fireUserInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_LEAVE_LEGION, "玩家已经离开了军团");
		
		if (fireUserInfo.getStatus() == USER_IN_LEGION && !fireUserInfo.getLegionId().equals(legionId))
			throw new ServiceException(ERROR_HAS_IN_OTHER_LEGION, "已经加入了另一个军团");
		
		if (userLegionInfo.getIdentity() == fireUserInfo.getIdentity())
			throw new ServiceException(ERROR_NO_POWER, "同级之间不能相互开除");
		 
		// 更新信息
		this.userLegionInfoDao.updateUserlegionInfo(fireUserId, legionId, USER_BE_FIRE_LEGION, userLegionInfo.getIdentity(), userLegionInfo.getContribution());
		
		// 更新军团战斗力
		UserExtInfo fireUserExtInfo = this.userService.getUserExtInfo(fireUserId);
		int totalPower = legionInfo.getPower() - fireUserExtInfo.getEffective();
		this.legionInfoDao.updateLegionPower(legionId, totalPower);
		
		// 发送邮件
		List<String> paramList = Lists.newArrayList();
		paramList.add(legionInfo.getLegionName());
		this.mailService.send(fireUserId, paramList, SystemMailConstant.BE_FIRE_MAILID);
		
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("userName", this.userService.getByUserId(fireUserId).getName());
		this.chatService.legionOfChat(ChatConstant.BE_FIRE_CHATID, legionId, params);
		return res;
	}
	
	private final static int ONLINE = 1;
	private final static int OFFLINE = 0;
	/**
	 * 查看公会成员列表
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_getLegionMemberListRes getLegionMemberList(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		String legionId = userLegionInfo.getLegionId();
		List<LegionMemberBO> memberList = Lists.newArrayList();
		List<UserLegionInfo> legionInfoList = this.userLegionInfoDao.getUserLegionInfoList(legionId);
		for (UserLegionInfo info : legionInfoList) {
			LegionMemberBO bo = new LegionMemberBO();
			bo.setUserId(info.getUserId());
			
			User user = this.userService.getByUserId(userId);
			bo.setUserName(user.getName());
			bo.setLevel(user.getLevel());
			bo.setIdentity(info.getIdentity());
			
			if (this.userService.isOnline(userId))
				bo.setStatus(ONLINE);
			else 
				bo.setStatus(OFFLINE);
			
			memberList.add(bo);
		}
		
		LegionAction_getLegionMemberListRes res = new LegionAction_getLegionMemberListRes();
		res.setLegionMemberList(memberList);
		return res;
	}
	
	private final static int ERROR_LEVEL_ZERO_NO_DEPUTY = 2009;
	private final static int ERROR_DEPUTY_HAS_ENOUGH = 2010;
	/**
	 * 任命副军团长
	 * 
	 * @param userId
	 * @param deputyUserId
	 * @return
	 */
	public LegionAction_appointLegionDeputyRes appointLegionDeputy(String userId, String deputyUserId) {
		if (userId.equals(deputyUserId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "自己任命自己？？");
		
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() != IDENTITY_LEGION_LEADER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		UserLegionInfo deputyUserInfo = this.userLegionInfoDao.getUserLegionInfo(deputyUserId);
		if (deputyUserInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_LEAVE_LEGION, "该用户已经离开了军团");
		
		if (deputyUserInfo.getStatus() == USER_IN_LEGION && !deputyUserInfo.getLegionId().equals(legionId))
			throw new ServiceException(ERROR_HAS_IN_OTHER_LEGION, "已经加入了另一个军团");
		
		if (legionInfo.getLevel() == 0)
			throw new ServiceException(ERROR_LEVEL_ZERO_NO_DEPUTY, "0级的公会没有副军团长");
		
		int deputyNum = this.configDataDao.getInt(ConfigKey.legion_deputy_num, 2);		
		int currentDeputyNum = this.userLegionInfoDao.getLegionCurrentIdentityNum(legionId, IDENTITY_LEGION_DEPUTY);
		if (currentDeputyNum >= deputyNum)
			throw new ServiceException(ERROR_DEPUTY_HAS_ENOUGH, "副会长已满员");
		
		this.userLegionInfoDao.updateUserlegionInfo(deputyUserId, IDENTITY_LEGION_DEPUTY);		
		LegionAction_appointLegionDeputyRes res = new LegionAction_appointLegionDeputyRes();
		
		this.mailService.send(deputyUserId, null, SystemMailConstant.BE_DEPUTY_MAILID);
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("userName", this.userService.getByUserId(deputyUserId).getName());
		this.chatService.legionOfChat(ChatConstant.BE_DEPUTY_CHATID, legionId, params);
		return res;		
	}
	
	private final static int ERROR_IS_NOT_DEPUTY = 2010;
	/**
	 * 罢免副军团长
	 * 
	 * @param userId
	 * @param deputyUserId
	 * @return
	 */
	public LegionAction_deposeLegionDeputyRes deposeLegionDeputy(String userId, String deputyUserId) {
		if (userId.equals(deputyUserId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "自己罢免自己？？");
		
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() != IDENTITY_LEGION_LEADER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		UserLegionInfo deputyUserInfo = this.userLegionInfoDao.getUserLegionInfo(deputyUserId);
		if (deputyUserInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_LEAVE_LEGION, "该用户已经离开了军团");
		
		if (deputyUserInfo.getStatus() == USER_IN_LEGION && !deputyUserInfo.getLegionId().equals(legionId))
			throw new ServiceException(ERROR_HAS_IN_OTHER_LEGION, "已经加入了另一个军团");
		
		if (deputyUserInfo.getIdentity() != IDENTITY_LEGION_DEPUTY)
			throw new ServiceException(ERROR_IS_NOT_DEPUTY, "这货不是副军团长呀！！！！");
		
		this.userLegionInfoDao.updateUserlegionInfo(deputyUserId, IDENTITY_LEGION_MEMBER);		
		this.mailService.send(deputyUserId, null, SystemMailConstant.BE_DEPOSE_MAILID);
		
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("userName", this.userService.getByUserId(deputyUserId).getName());
		this.chatService.legionOfChat(ChatConstant.BE_DEPOSE_CHATID, legionId, params);
		LegionAction_deposeLegionDeputyRes res = new LegionAction_deposeLegionDeputyRes();
		return res;
	}
	
	/**
	 * 转让会长
	 * 
	 * @param userId
	 * @param beLeaderUserId
	 * @return
	 */
	public LegionAction_transferLegionLeaderRes transferLegionLeader(String userId, String beLeaderUserId){
		if (userId.equals(beLeaderUserId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "自己转让自己？？");
		
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() != IDENTITY_LEGION_LEADER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		UserLegionInfo beLeaderUserInfo = this.userLegionInfoDao.getUserLegionInfo(beLeaderUserId);
		if (beLeaderUserInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_HAS_LEAVE_LEGION, "该用户已经离开了军团");
		
		if (beLeaderUserInfo.getStatus() == USER_IN_LEGION && !beLeaderUserInfo.getLegionId().equals(legionId))
			throw new ServiceException(ERROR_HAS_IN_OTHER_LEGION, "已经加入了另一个军团");
		
		// 互换身份
		this.userLegionInfoDao.updateUserlegionInfo(userId, IDENTITY_LEGION_MEMBER);
		this.userLegionInfoDao.updateUserlegionInfo(beLeaderUserId, IDENTITY_LEGION_LEADER);
		
		this.mailService.send(beLeaderUserId, null, SystemMailConstant.BE_LEGION_LEADER);
		LegionAction_transferLegionLeaderRes res = new LegionAction_transferLegionLeaderRes();
		res.setBeLeaderUserId(beLeaderUserId);
		
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("userName", this.userService.getByUserId(userId).getName());
		params.put("leaderName", this.userService.getByUserId(beLeaderUserId).getName());
		this.chatService.legionOfChat(ChatConstant.TRANSFER_LEADER_CHATID, userLegionInfo.getLegionId(), params);
		return res;
	}
	
	private final static int ERROR_LEADER_CAN_NOT_LEAVE = 2001;
	/**
	 * 离开公会
	 * 
	 * @param userId
	 */
	public void leaveLegion(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			return;
		
		if (userLegionInfo.getIdentity() == IDENTITY_LEGION_LEADER)
			throw new ServiceException(ERROR_LEADER_CAN_NOT_LEAVE, "军团长只能解散军团");
		
		// TODO 
		this.userLegionInfoDao.updateUserlegionInfoStatus(userId, USER_LEAVE_LEGION);
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(userLegionInfo.getLegionId());
		if (legionInfo == null)
			return;
	
		// 更新战斗力
		UserExtInfo extInfo = this.userService.getUserExtInfo(userId);
		int totalPower = legionInfo.getPower() - extInfo.getEffective();
		if (totalPower < 0)
			totalPower = 0;
		this.legionInfoDao.updateLegionPower(legionInfo.getLegionId(), totalPower);
		
		Map<String, String> params = Maps.newConcurrentMap();
		params.put("userName", this.userService.getByUserId(userId).getName());
		this.chatService.legionOfChat(ChatConstant.LEAVE_LEGION_CHATID, userLegionInfo.getLegionId(), params);
	}
	
	/**
	 * 解散公会
	 * 
	 * @param userId
	 */
	public void dismissLegion(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		if (userLegionInfo.getIdentity() != IDENTITY_LEGION_LEADER)
			throw new ServiceException(ERROR_NO_POWER, "没有该权限");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		// 更新公会内用户的状态 TODO
		List<UserLegionInfo> userLegionInfoList = this.userLegionInfoDao.getUserLegionInfoList(legionId);
		this.userLegionInfoDao.updateUserlegionInfoStatus(legionId, USER_DISMISS_LEGION, userLegionInfoList);	
		
		// 删除军团信息
		this.legionInfoDao.deleteLegionInfo(legionId);
		// 删除留言板
		this.userMessageInfoDao.deleteUserMessageInfo(legionId);
		
		for (UserLegionInfo info : userLegionInfoList) {
			if (info.getUserId().equals(userId))
				continue;
			
			this.mailService.send(info.getUserId(), null, SystemMailConstant.LEGION_BE_DISMISS);
		}		
	}
	
	private Map<String, List<UserMessageInfo>> messageMap = Maps.newConcurrentMap();
	private final static int MESSAGE_TOO_LONG = 2002;
	private final static int MESSAGE_SAME = 2003;
	/**
	 * 提交留言
	 * 
	 * @param userId
	 * @param message
	 * @return
	 */
	public LegionAction_commitMessageRes commitMessage(String userId, String message) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		if (StringUtils.isBlank(message))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，发送内容为空");
	
		int maxNum = this.configDataDao.getInt(ConfigKey.chat_char_num_max, 50);
		if (message.length() > maxNum)
			throw new ServiceException(MESSAGE_TOO_LONG, "字数超出限制");
		
		message = IllegalWordUtills.replaceWords(message);
		if (messageMap.containsKey(userId) && isSameMessage(messageMap.get(userId), message)) 
			throw new ServiceException(MESSAGE_SAME, "与上一条内容相同，请10s后再发送");
		
		UserMessageInfo userMessageInfo = new UserMessageInfo();
		userMessageInfo.setLegionId(legionId);
		userMessageInfo.setUserId(userId);
		userMessageInfo.setCreatedTime(new Date());
		userMessageInfo.setUpdatedTime(new Date());
		this.userMessageInfoDao.addUserMessageInfo(userMessageInfo);
		
		if (messageMap.containsKey(userId)) {
			messageMap.get(userId).add(userMessageInfo);
		} else {
			List<UserMessageInfo> infoList = Lists.newArrayList();
			infoList.add(userMessageInfo);
			messageMap.put(userId, infoList);
		}
		
		LegionAction_commitMessageRes res = new LegionAction_commitMessageRes();
		res.setUserMessageInfoBO(createUserMessageInfoBO(userMessageInfo));
		return res;
	}
	
	/**
	 * 获取留言板列表
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_getMessageListRes getMessageList(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		int saveDay = this.configDataDao.getInt(ConfigKey.legion_message_save_day, 7);
		Date now = new Date();
		Date endTime = new Date(now.getTime() - saveDay * 24 * 3600 * 1000);
		
		List<UserMessageInfo> infoList = this.userMessageInfoDao.getUserMessageInfoList(legionId, endTime);
		List<UserMessageInfoBO> infoBOList = Lists.newArrayList();
		for (UserMessageInfo info : infoList) {
			UserMessageInfoBO bo = createUserMessageInfoBO(info);
			infoBOList.add(bo);
		}
		
		LegionAction_getMessageListRes res = new LegionAction_getMessageListRes();
		res.setUserMessageInfoList(infoBOList);
		return res;
	}
	
	private boolean isSameMessage(List<UserMessageInfo> infoList, String message) {
		boolean isSame = false;
		Date now = new Date();
		for (UserMessageInfo info : infoList) {
			if (info.getMessage().equals(message) && now.getTime() - info.getCreatedTime().getTime() < 10000)
				return true;
		}
		
		return isSame;
	}
	
	private UserMessageInfoBO createUserMessageInfoBO(UserMessageInfo userMessageInfo) {
		UserMessageInfoBO infoBO = new UserMessageInfoBO();
		infoBO.setUserId(userMessageInfo.getUserId());
		infoBO.setContent(userMessageInfo.getMessage());
		infoBO.setTime(userMessageInfo.getCreatedTime().getTime());
		
		User user = this.userService.getByUserId(userMessageInfo.getUserId());
		infoBO.setUserName(user.getName());
		UserHeroBO userHeroBO = this.heroService.getUserTeamLeader(userMessageInfo.getUserId());
		infoBO.setSystemHeroId(userHeroBO.getSystemHeroId());
		
		return infoBO;
	}
	
	private final static int HAS_INVEST = 1;
	private final static int NOT_INVEST = 0; 
	/**
	 * 获取用户投资信息
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_getUserInvestInfoRes getUserInvestInfo(String userId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		LegionAction_getUserInvestInfoRes res = new LegionAction_getUserInvestInfoRes();
		res.setStatus(NOT_INVEST);
		UserInvestInfo userInvestInfo = this.userInvestInfoDao.getUserInvestInfo(userId);
		if (userInvestInfo == null || DateUtils.isSameDay(new Date(), userInvestInfo.getUpdatedTime()))
			return res;
		
		res.setStatus(HAS_INVEST);
		return res;		
	}
	
	private final static int ERROR_HAS_INVEST = 2006;
	private final static int TYPE_MONEY = 1;
	/**
	 * 用户捐献
	 * 
	 * @param userId
	 * @return
	 */
	public LegionAction_userInvestRes userInvest(String userId, int investId) {
		UserLegionInfo userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != USER_IN_LEGION)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "没有在军团里面");
		
		String legionId = userLegionInfo.getLegionId();
		LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已经被解散了");
		
		UserInvestInfo userInvestInfo = this.userInvestInfoDao.getUserInvestInfo(userId);
		if (userInvestInfo != null && DateUtils.isSameDay(new Date(), userInvestInfo.getUpdatedTime()))
			throw new ServiceException(ERROR_HAS_INVEST, "今天已经捐献过了");
		
		SystemLegionInvest systemLegionInvest = this.systemLegionInvestDaoCache.getSystemLegionInvest(investId);
		if (systemLegionInvest == null)
			throw new ServiceException(SystemErrorCode.DATA_NULL, "数据缺失，没有捐献的数据");
		
		int amount = systemLegionInvest.getAmount();
		User user = this.userService.getByUserId(userId);
		if (systemLegionInvest.getType() == TYPE_MONEY) {
			if (user.getMoney() < amount)
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
				
			if (!this.userService.reduceMoney(userId, amount, GoodsUseType.REDUCE_USER_LEGION_INVEST))
				throw new ServiceException(SystemErrorCode.MONEY_NOT_ENOUGH, "钻石不足");
		} else {
			if (user.getGold() < amount)
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
				
			if (!this.userService.reduceGold(userId, amount, GoodsUseType.REDUCE_USER_LEGION_INVEST))
				throw new ServiceException(SystemErrorCode.GOLD_NOT_ENOUGH, "金币不足");
		}
		
		// 自己加贡献值
		int contribution = userLegionInfo.getContribution() + systemLegionInvest.getContribution();
		this.userLegionInfoDao.updateUserContribution(userId, contribution);
		
		// 军团加经验
		updateLegionExpAndLevel(legionInfo, systemLegionInvest.getLegionExp());		
		LegionAction_userInvestRes res = new LegionAction_userInvestRes();	
		userLegionInfo = this.userLegionInfoDao.getUserLegionInfo(userId);
		legionInfo = this.legionInfoDao.getLegionInfo(legionId);
		
		res.setUserLegionInfo(createUserLegionInfoBO(userLegionInfo));
		SystemLegionLevel systemLegionLevel = this.systemLegionLevelDaoCache.getSystemLegionLevel(legionInfo.getLevel());
		int maxNum = 30;
		if (systemLegionLevel != null)
			maxNum = systemLegionLevel.getPersonNum();
		res.setLegionInfo(createLegionInfoBO(legionInfo, legionInfo.getLegionName(), maxNum));
		return res;
	}
	
	private void updateLegionExpAndLevel(LegionInfo legionInfo, int addExp) {
		// 如果到了最高级，则不再加经验
		if (legionInfo.getLevel() >= systemLegionLevelDaoCache.getMaxLevel()) {
			LogSystem.info("legionId=" + legionInfo.getLegionId() + ",公会已经到达最高等级 不再加经验");
			return;
		}
		
		SystemLegionLevel systemLegionLevel = this.systemLegionLevelDaoCache.getSystemLegionLevelByExp(legionInfo.getExp() + addExp);
		if (systemLegionLevel == null) 
			throw new ServiceException(SystemErrorCode.DATA_NULL, "数据缺失，没有公会等级经验的数据");
		
		this.legionInfoDao.updateLevelAndExp(legionInfo.getLegionId(), systemLegionLevel.getLevel(), addExp);	
	}
	
	/**
	 * 获取公会内的用户 (用户公会聊天频道)
	 * 
	 * @param legionId
	 * @return
	 */
	public String[] getLegionUserIds(String userId, String legionId) {
		List<UserLegionInfo> list = this.userLegionInfoDao.getUserLegionInfoList(legionId);
		String userIds = "";
		for (UserLegionInfo info : list) {
			if (info.getUserId().equals(userId))
				continue;
			
			if (userIds.length() > 0)
				userIds = info.getUserId() + "," + userIds;
			else 
				userIds = info.getUserId();
		}
		
		return userIds.split(",");
	}
	
	private UserLegionInfoBO createUserLegionInfoBO(UserLegionInfo userLegionInfo) {
		UserLegionInfoBO userLegionInfoBO = new UserLegionInfoBO();
		userLegionInfoBO.setLegionId(userLegionInfo.getLegionId());
		userLegionInfoBO.setIdentity(userLegionInfo.getIdentity());
		userLegionInfoBO.setContribution(userLegionInfo.getContribution());		
		
		return userLegionInfoBO;
	}
	
	private LegionInfoBO createLegionInfoBO(LegionInfo legionInfo, String leaderName, int maxNum) {
		LegionInfoBO legionInfoBO = new LegionInfoBO();
		legionInfoBO.setLegionId(legionInfo.getLegionId());
		legionInfoBO.setLegionName(legionInfo.getLegionName());
		legionInfoBO.setExp(legionInfo.getExp());
		legionInfoBO.setLevel(legionInfo.getLevel());
		legionInfoBO.setPower(legionInfo.getPower());
		legionInfoBO.setNotice(legionInfo.getNotice());
		legionInfoBO.setDeclaration(legionInfo.getDeclaration());
		legionInfoBO.setLegionLeaderName(leaderName);
		legionInfoBO.setMaxNum(maxNum);
		
		int currentNum = this.userLegionInfoDao.getLegionCurrentNum(legionInfo.getLegionId());
		legionInfoBO.setCurrentNum(currentNum);
		
		return legionInfoBO;
	}
	
	private UserLegionInfo createUserLegionInfo(String userId, String legionId, int identity) {
		UserLegionInfo userLegionInfo = new UserLegionInfo();
		userLegionInfo.setUserId(userId);
		userLegionInfo.setContribution(0);
		userLegionInfo.setLegionId(legionId);
		userLegionInfo.setIdentity(identity);
		userLegionInfo.setStatus(USER_IN_LEGION);
		userLegionInfo.setCreatedTime(new Date());
		userLegionInfo.setUpdatedTime(new Date());
		
		return userLegionInfo;
	}
	
	private LegionInfo createLegionInfo(String userId, String legionName, int power) {
		LegionInfo legionInfo = new LegionInfo();
		legionInfo.setLegionId(IDGenerator.getID());
		legionInfo.setLegionLeader(userId);
		legionInfo.setLegionName(legionName);
		legionInfo.setExp(0);
		legionInfo.setLevel(0);
		legionInfo.setPower(power);
		legionInfo.setCreatedTime(new Date());
		legionInfo.setUpdatedTime(new Date());
		
		return legionInfo;
	}
	
	public UserLegionInfo getUserLegionInfo(String userId) {
		return this.userLegionInfoDao.getUserLegionInfo(userId);
	}
	
	public LegionInfo getLegionInfoBy(String legionId) {
		return this.legionInfoDao.getLegionInfo(legionId);
	}
	
	/**
	 * 3天未审核的自动消除
	 * 
	 * @param userId
	 */
	private void checkUserApply(String userId) {
		int validDay = this.configDataDao.getInt(ConfigKey.legion_apply_valid, 3);
		Date now = new Date();
		Date endTime = new Date(now.getTime() - validDay * 24 * 3600 * 1000);
		
		List<UserApplyLegionInfo> applyList = this.userApplyLegionInfoDao.getUserApplyLegionInfoListByUserId(userId, endTime);
		if (applyList.size() > 0) {
			this.userApplyLegionInfoDao.deleteUserApplyLegionInfo(userId, endTime);
			
			for (UserApplyLegionInfo applyInfo : applyList) {
				List<String> paramList = Lists.newArrayList();
				LegionInfo legionInfo = this.legionInfoDao.getLegionInfo(applyInfo.getLegionId());
				paramList.add(legionInfo.getLegionName());
				
				this.mailService.send(userId, paramList, SystemMailConstant.APPLY_JOIN_LEGION_LOSE_EFFICACY);
			}
		}
	}
	
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		if (handlerType.equals(ModuleEventConstant.USER_LOGIN_EVENT)) {
			//开启成就系统
			String userId = baseModuleEvent.getStringValue("userId", "");
			if (StringUtils.isNotBlank(userId))
				checkUserApply(userId);
		}
	}

	@Override
	public List<String> getHandlerType() {
		List<String> list = Lists.newArrayList();
		list.add(ModuleEventConstant.USER_LOGIN_EVENT);
		return list;
	}

	@Override
	public int order() {
		return 0;
	}
	
}

package com.fantingame.game.mywar.logic.mail.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.DateUtils;
import com.fantingame.game.common.utils.IDGenerator;
import com.fantingame.game.cuser.model.UserMapper;
import com.fantingame.game.cuser.service.UserMapperService;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.mail.MailAction_deleteRes;
import com.fantingame.game.msgbody.client.mail.MailAction_getMailListRes;
import com.fantingame.game.msgbody.client.mail.MailAction_oneClickDeleteRes;
import com.fantingame.game.msgbody.client.mail.MailAction_oneClickReceiveRes;
import com.fantingame.game.msgbody.client.mail.MailAction_readRes;
import com.fantingame.game.msgbody.client.mail.MailAction_receiveRes;
import com.fantingame.game.msgbody.client.mail.UserMailBO;
import com.fantingame.game.msgbody.notify.mail.Mail_pushNewEmailNotify;
import com.fantingame.game.msgbody.server.admin.ResUserMailBO;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.friend.model.UserBlackInfo;
import com.fantingame.game.mywar.logic.friend.service.FriendService;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.goods.service.GoodsDealService;
import com.fantingame.game.mywar.logic.mail.constant.MailStatus;
import com.fantingame.game.mywar.logic.mail.constant.MailTarget;
import com.fantingame.game.mywar.logic.mail.dao.UserMailDao;
import com.fantingame.game.mywar.logic.mail.dao.impl.cache.SystemMailDaoCacheImpl;
import com.fantingame.game.mywar.logic.mail.dao.impl.cache.SystemMailInternalDaoCache;
import com.fantingame.game.mywar.logic.mail.model.SystemMail;
import com.fantingame.game.mywar.logic.mail.model.SystemMailInternal;
import com.fantingame.game.mywar.logic.mail.model.UserMail;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.constant.SystemConstant;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.google.common.collect.Lists;

/**
 * 邮件系统业务逻辑
 * 
 * @author yezp
 */
public class MailService {

	//0 管理后台发送的邮件  大于0  为系统内部邮件 -1 用户之间的邮件
    public static final int ADMIN_MAIL = 0;
    public static final int USER_MAIL = -1;
	
	@Autowired
	private UserMailDao userMailDao;
	@Autowired
	private SystemMailDaoCacheImpl systemMailDao;
	@Autowired
	private UserMapperService userMapperService;
	@Autowired
	private SystemMailInternalDaoCache systemMailInternalDao;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsDealService goodsDealService;
	@Autowired
	private FriendService friendService;
	
	/**
	 * 获取用户邮件列表
	 * 
	 * @param userId
	 * @return
	 */
	public MailAction_getMailListRes getMailList(String userId, int lastId) {
		Date lastReceiveTime = this.userMailDao.getLastReceiveTime(userId);
		User user = this.userService.getByUserId(userId);
		if (lastReceiveTime == null)
			lastReceiveTime = user.getRegTime();
		
		Date now = new Date();
		this.userMailDao.setLastReceiveTime(userId, now);
		List<SystemMail> systemMailList = this.systemMailDao.getSystemMailByTime(lastReceiveTime);
		if (!systemMailList.isEmpty()) {
			List<UserMail> list = new ArrayList<UserMail>();
			int maxUserMailId = this.userMailDao.getMaxUserMailId(userId);
			for (SystemMail systemMail : systemMailList) {
				if (!needAdd(user, systemMail)) {
					continue;
				}
				
				if (this.userMailDao.getBySystemMailId(userId, systemMail.getSystemMailId()) != null) {
					continue;
				}
				
				maxUserMailId = maxUserMailId + 1;
				UserMail userMail = new UserMail();
				userMail.setUserMailId(maxUserMailId);
				userMail.setStatus(0);
				userMail.setSystemMailId(systemMail.getSystemMailId());
				userMail.setUpdatedTime(now);
				userMail.setUserId(userId);
				userMail.setReceiveStatus(0);
				userMail.setCreatedTime(now);
				list.add(userMail);
			}
			this.userMailDao.add(userId, list);
		}
		
		List<UserMail> userMailList = this.userMailDao.getList(userId);
		List<UserMailBO> userMailBOList = new ArrayList<UserMailBO>();
		for (UserMail userMail : userMailList) {
			if (userMail.getUserMailId() <= lastId && userMail.getStatus() == MailStatus.STATUS_DEL) {
				continue;
			}
			
			UserMailBO userMailBO = new UserMailBO();
			String systemMailId = userMail.getSystemMailId();
			SystemMailInternal systemMailInternal = null;
			SystemMail systemMail = null;
			if (userMail.getEmailType() != ADMIN_MAIL) {
				int mailId = NumberUtils.toInt(userMail.getSystemMailId());
				systemMailInternal = this.systemMailInternalDao.getMail(mailId);
			} else {
				systemMail = this.systemMailDao.getSystemMail(systemMailId);
			}
			
//			if(systemMail == null && systemMailInternal == null){
//				continue;
//			}
			
			Date expiredDate = null;
			int expiredDay = this.configDataDao.getInt(ConfigKey.mail_expire_time, 30);
			int expireTime = expiredDay;
			
			if (systemMail != null) {
				expiredDate = DateUtils.addDays(systemMail.getCreatedTime(), expiredDay);
				expireTime = DateUtils.getDayDiff(systemMail.getCreatedTime(), now);
			} else {
				expiredDate = DateUtils.addDays(userMail.getCreatedTime(), expiredDay);
				expireTime = DateUtils.getDayDiff(userMail.getCreatedTime(), now);
			}
			
			if (expiredDate.before(now)) {// 已经过期
				continue;
			}
			userMailBO.setUserMailId(userMail.getUserMailId());
			userMailBO.setStatus(userMail.getStatus());
			userMailBO.setReceiveStatus(userMail.getReceiveStatus());
			userMailBO.setMailType(userMail.getEmailType());
			userMailBO.setShowType(userMail.getEmailShowType());
			userMailBO.setExpiredTime(expiredDay - expireTime);
			
			// 用户发的邮件
			if (systemMail == null && systemMailInternal == null) {
				// userMailBO.setTitle(userMail.getTitle());
				User fromUser = this.userService.getByUserId(userMail.getFromUserId());
				userMailBO.setTitle(fromUser.getName());
				userMailBO.setContent(userMail.getContent());
				userMailBO.setCreatedTime(userMail.getCreatedTime().getTime());
				userMailBO.setFromUserId(userMail.getFromUserId());
			}
			
			if(systemMail != null){
				userMailBO.setTitle(systemMail.getTitle());
				userMailBO.setContent(systemMail.getContent());
				userMailBO.setCreatedTime(systemMail.getCreatedTime().getTime());
				userMailBO.setGoodsBeanBOList(GoodsHelper.parseDropGoods(systemMail.getToolIds()));				
			}
			
			if(systemMailInternal != null){
				String[] strArray = null;
				String content = systemMailInternal.getMailContent();
				String title = systemMailInternal.getMailTitle();
				if(!StringUtils.isBlank(userMail.getParam())){
					strArray = userMail.getParam().split(",");
					for(int i = 0; i < strArray.length; i++){
						String replaceStr = "#p" + ( i + 1 ) + "#";
						try {
							content = content.replaceFirst(replaceStr, strArray[i]);
						} catch (Exception e) {
							LogSystem.warn("非法字符");
							content = content.replaceFirst(replaceStr, "*****");
						}
					}
				}
				
				// BOSS战的邮件
				if (systemMailInternal.getLogicType() == MailStatus.LOGIC_TYPE_BOSS) {
					userMailBO.setSign(systemMailInternal.getDescription());
				}
				
				userMailBO.setContent(content);
				userMailBO.setTitle(title);
				userMailBO.setGoodsBeanBOList(GoodsHelper.parseDropGoods(systemMailInternal.getTools()));
			}
			
			//邮件自身带的附件内容权重大于模板中所带的内容
			if(!StringUtils.isBlank(userMail.getRewards())){
				userMailBO.setGoodsBeanBOList(GoodsHelper.parseDropGoods(userMail.getRewards()));
			}
			userMailBOList.add(userMailBO);
		}		
		
		MailAction_getMailListRes res = new MailAction_getMailListRes();
		res.setUserMailList(userMailBOList);
		return res;
	}
	
	/**
	 * 是否满足收邮件的条件
	 * 
	 * @param userId
	 * @param systemMail
	 * @return
	 */
	private boolean needAdd(User user, SystemMail systemMail) {
		int target = systemMail.getTarget();

		if (target == MailTarget.USERS) {
			return false;
		} else if (target == MailTarget.ALL) {
			String lodoIds = systemMail.getLodoIds();
			Date mailTime = systemMail.getMailTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				// lodoIds 数据可能不正常
				Date date = sdf.parse(lodoIds);
				if (user.getRegTime().before(mailTime) && user.getRegTime().after(date)) {
					// 成功转换，并且在这个时间范围内注册用户
					return true;
				} else {
					// 成功转换，但是不在这个时间范围内注册的用户
					return false;
				}
			} catch (Exception e) {
				// 抛出异常的情况包括，lodoId为空，lodoId数据（lodoId + minRegTime)，兼容原来的设计
				if (user.getRegTime().before(mailTime)) {
					return true;
				} else {
					return false;
				}	
			}
		// 这一天登陆的	
		} else if (target == MailTarget.LOGIN) {
			if (this.userService.isLogin(user.getUserId(), systemMail.getMailTime())) {
				return true;
			}
		// 这一天充值的
		} else if (target == MailTarget.PAYMENT) {			
			if (this.userService.getPaymentTotalByTime(user.getUserId(), systemMail.getMailTime()) > 0) {
				return true;
			}
		} else if (target == MailTarget.PARTNER) {
			UserMapper userMapper = userMapperService.getUserMapperByUserId(user.getUserId());
			if (userMapper.getPartnerId().equals(systemMail.getPartner())) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 读取邮件
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public MailAction_readRes read(String userId, int userMailId) {
		// 更新邮件状态
		boolean success = this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_READ);
		if (!success)
			throw new ServiceException(SystemConstant.FAIL_CODE, "更新失败");
		
		MailAction_readRes res = new MailAction_readRes();
		res.setUserMailId(userMailId);
		return res;
	}
	
	// 邮件不存在
	private static final int RECEIVE_MAIL_ERROR_MAIL_NOT_EXISTS = 2001;
	// 附件已领取
	private static final int RECEIVE_MAIL_ERROR_MAIL_HAD_RECEIVE = 2002;
	// 该邮件没有附件
	private static final int RECEIVE_MAIL_ERROR_MAIL_NOT_REWARD = 2003;
	/**
	 * 领取邮件附件
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public MailAction_receiveRes receive(String userId, int userMailId) {
		UserMail userMail = this.userMailDao.get(userId, userMailId);
		if (userMail == null) {
			String message = "领取邮件附件错误，邮件不存在.userId[" + userId + "], userMailId[" + userMailId + "]";
			LogSystem.info(message);
			throw new ServiceException(RECEIVE_MAIL_ERROR_MAIL_NOT_EXISTS, message);
		}
		
		SystemMail systemMail = null;
		SystemMailInternal systemMailInternal = null;
        if (userMail.getEmailType() == ADMIN_MAIL) {
        	systemMail = this.systemMailDao.getSystemMail(userMail.getSystemMailId());
        } else {
        	systemMailInternal = this.systemMailInternalDao.getMail(NumberUtils.toInt(userMail.getSystemMailId()));
        }
        
		if (systemMail == null && systemMailInternal == null) {
			String message = "领取邮件附件错误，邮件对应的系统邮件不存在.userId[" + userId + "], userMailId[" + userMailId + "], systemMailId[" + userMail.getSystemMailId() + "]";
			LogSystem.info(message);
			throw new ServiceException(RECEIVE_MAIL_ERROR_MAIL_NOT_EXISTS, message);
		}

		if (userMail.getReceiveStatus() != MailStatus.STATUS_NOT_RECEIVE) {
			String message = "领取邮件附件错误，邮件已经领取.userId[" + userId + "], userMailId[" + userMailId + "]";
			LogSystem.info(message);
			throw new ServiceException(RECEIVE_MAIL_ERROR_MAIL_HAD_RECEIVE, message);
		}
		
        String tools = "";
        if(systemMail != null)
        	tools = systemMail.getToolIds();
        
        if(systemMailInternal !=null)
        	tools = systemMailInternal.getTools();
        
        //邮件本身的附件内容大于模板中存在的内容
        if(!StringUtils.isBlank(userMail.getRewards()))
        	tools = userMail.getRewards();
        
		List<GoodsBeanBO> dropToolBOList = GoodsHelper.parseDropGoods(tools);
		if (dropToolBOList.isEmpty()) {
			String message = "领取邮件附件错误，邮件没有任何附件.userId[" + userId + "], userMailId[" + userMailId + "]";
			LogSystem.info(message);
			throw new ServiceException(RECEIVE_MAIL_ERROR_MAIL_NOT_REWARD, message);
		}
        //检测背包
		userService.checkBag(userId, dropToolBOList);
		if (!this.userMailDao.updateReceiveStatus(userId, userMailId, MailStatus.STATUS_RECEIVE)) {
			String message = "领取邮件附件错误，邮件已经领取.userId[" + userId + "], userMailId[" + userMailId + "]";
			LogSystem.info(message);
			throw new ServiceException(RECEIVE_MAIL_ERROR_MAIL_HAD_RECEIVE, message);
		}
		
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, dropToolBOList, GoodsUseType.ADD_BY_EMAIL);
		MailAction_receiveRes res = new MailAction_receiveRes();
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 删除邮件
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public MailAction_deleteRes delete(String userId, String userMailIds) {
		if (StringUtils.isBlank(userMailIds))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "删除邮件，参数错误，为传入删除邮件的id");
		
		String[] userMailIdArr = userMailIds.split(",");
		for (String userMailIdStr : userMailIdArr) {
			int userMailId = Integer.parseInt(userMailIdStr);
			UserMail userMail = userMailDao.get(userId, userMailId);
			
			if (userMail == null)
				continue;
			
			// 用户间的邮件
			if (userMail.getEmailType() == USER_MAIL) {
				userMailDao.delete(userId, userMailId);
			
			// 非管理后台发出得邮件都删除	TODO 策划说：不管领不领取都删除
			} else if (userMail.getEmailType() != ADMIN_MAIL){
				SystemMailInternal systemMailInternal = this.systemMailInternalDao.getMail(NumberUtils.toInt(userMail.getSystemMailId()));
				if(systemMailInternal != null && StringUtils.isBlank(systemMailInternal.getTools())){
					userMailDao.delete(userId, userMailId);
				} else {
					this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
				}
			} else {
				this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
			}
		}		
		
		MailAction_deleteRes res = new MailAction_deleteRes();
		return res;
	}
	
	/**
	 * 旧的邮件删除方法：奖励未领取是不删除的
	 * 
	 * @param userId
	 * @param userMailId
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void oldDeleteFun(UserMail userMail) {
		String userId = userMail.getUserId();
		int userMailId = userMail.getUserMailId();
		
		// 用户间的邮件
		if (userMail.getEmailType() == USER_MAIL) {
			userMailDao.delete(userId, userMailId);
					
			// 非管理后台发出得邮件都删除	
		} else if (userMail.getEmailType() != ADMIN_MAIL){
			//没有奖励的邮件都删除
			SystemMailInternal systemMailInternal = this.systemMailInternalDao.getMail(NumberUtils.toInt(userMail.getSystemMailId()));
			if(systemMailInternal != null && StringUtils.isBlank(systemMailInternal.getTools())){
				userMailDao.delete(userId, userMailId);
			} else {
				if (userMail.getReceiveStatus() == MailStatus.STATUS_RECEIVE)
					// 已经领取
					this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
			}
		} else {
			if (StringUtils.isBlank(userMail.getRewards())) {
				this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
			} else {
				if (userMail.getReceiveStatus() == MailStatus.STATUS_RECEIVE)
					this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
			}
		}
	}
	
	// 无邮件可删除
	private static final int NO_MAIL_CAN_DELETE = 2001;
	/**
	 * 一键删除邮件
	 * 
	 * @param userId
	 * @return
	 */
	public MailAction_oneClickDeleteRes oneClickDelete(String userId) {
		List<UserMail> mailList = userMailDao.getList(userId);
		List<Integer> userMailIdList = new ArrayList<Integer>();
		if (mailList == null || mailList.size() == 0)
			throw new ServiceException(NO_MAIL_CAN_DELETE, "无邮件可删除");

		for (UserMail userMail : mailList) {
			int userMailId = userMail.getUserMailId();
			
			// 未领取
			if (!StringUtils.isBlank(userMail.getRewards()) && userMail.getReceiveStatus() == MailStatus.STATUS_NOT_RECEIVE) {
				continue;
			}			

			// 用户间的邮件
			if (userMail.getEmailType() == USER_MAIL) {
				userMailDao.delete(userId, userMailId);
			
			// 非管理后台发出得邮件都删除
			} else if (userMail.getEmailType() != ADMIN_MAIL) {			
				SystemMailInternal systemMailInternal = this.systemMailInternalDao.getMail(NumberUtils.toInt(userMail.getSystemMailId()));
				if (!StringUtils.isBlank(systemMailInternal.getTools()) && userMail.getReceiveStatus() == MailStatus.STATUS_NOT_RECEIVE) {
					continue;
				}
				
				//没有奖励的邮件都删除
				if (systemMailInternal != null && StringUtils.isBlank(systemMailInternal.getTools())) {
					userMailDao.delete(userId, userMailId);
				} else {
					this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
				}
			} else {
				SystemMail sysMail = this.systemMailDao.getSystemMail(userMail.getSystemMailId());
				if (sysMail != null && !StringUtils.isBlank(sysMail.getToolIds())
						&& userMail.getReceiveStatus() == MailStatus.STATUS_NOT_RECEIVE) {
					continue;
				}
				
				this.userMailDao.updateStatus(userId, userMailId, MailStatus.STATUS_DEL);
			}
			
			userMailIdList.add(userMailId);
		}		
		
		if (userMailIdList.size() == 0) {
			throw new ServiceException(NO_MAIL_CAN_DELETE, "无邮件可删除");
		}
		
		MailAction_oneClickDeleteRes res = new MailAction_oneClickDeleteRes();
		res.setUserMailIdList(userMailIdList);
		return res;
	}
	
	private static final int USER_NOT_EXIST = 2001;
	private static final int SEND_TO_SELF = 2002;
	private static final int CHAR_TOO_LONG = 2003;
	private static final int HAS_ADD_BLACK = 2006;
	private static final int IN_TARGET_BLACKLIST = 2007;
	/**
	 * 发送邮件
	 * 
	 * @param userId
	 * @param toUserId
	 * @param name
	 * @param content
	 */
	public void sendEmail(String userId, String toUserId, String name, String content) {
		if ((StringUtils.isBlank(toUserId) && StringUtils.isBlank(name)) || StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误");
		
		int maxNum = this.configDataDao.getInt(ConfigKey.email_char_max, 50);
		if (content.length() > maxNum)
			throw new ServiceException(CHAR_TOO_LONG, "字数太多");
		
		if (StringUtils.isBlank(toUserId)) {
			User targetUser = this.userService.getByUserName(name);
			if (targetUser == null)
				throw new ServiceException(USER_NOT_EXIST, "不存在该用户");
		
			toUserId = targetUser.getUserId();
		}
		
		if (userId.equals(toUserId))
			throw new ServiceException(SEND_TO_SELF, "自己给自己发邮件，就不给你发。");
		
		UserBlackInfo userBlackInfo = this.friendService.getUserBlackInfo(userId, toUserId);
		if (userBlackInfo != null)
			throw new ServiceException(HAS_ADD_BLACK, "你把对方拉黑了");
		
		userBlackInfo = this.friendService.getUserBlackInfo(toUserId, userId);
		if (userBlackInfo != null)
			throw new ServiceException(IN_TARGET_BLACKLIST, "对方把你拉黑了");
		
		int maxUserMailId = this.userMailDao.getMaxUserMailId(toUserId);
		UserMail userMail = new UserMail();
		userMail.setUserMailId(maxUserMailId + 1);
		userMail.setContent(content);
		userMail.setUserId(toUserId);
		userMail.setFromUserId(userId);
		userMail.setStatus(MailStatus.STATUS_NEW);
		
		userMail.setEmailType(USER_MAIL);
		userMail.setEmailShowType(0);
		userMail.setCreatedTime(new Date());
		userMail.setUpdatedTime(new Date());
		this.userMailDao.add(userMail);
		
		List<String> userIdList = Lists.newArrayList();
		userIdList.add(toUserId);
		notifyNewMail(userIdList);
	}

	public void send(String title, String content, String toolIds, int target, String userLodoIds, int sourceId, Date date, String partner) {
		Date now = new Date();
		SystemMail systemMail = new SystemMail();

		String systemMailId = IDGenerator.getID();
		systemMail.setSystemMailId(systemMailId);
		systemMail.setContent(content);
		systemMail.setTarget(target);
		systemMail.setTitle(title);
		systemMail.setSourceId(sourceId);
		systemMail.setToolIds(toolIds);
		if (StringUtils.isBlank(userLodoIds)) {
			systemMail.setLodoIds("");
		} else {
			systemMail.setLodoIds(userLodoIds);
		}
		
		systemMail.setMailTime(date);
		systemMail.setCreatedTime(now);
		systemMail.setUpdatedTime(now);
		systemMail.setPartner(partner);
		
		this.systemMailDao.add(systemMail);
		if (target == MailTarget.ALL) {
			// 开始时间-结束时间
			notifyNewMail(MailTarget.ALL, systemMail.getLodoIds(), systemMail.getMailTime());
		}
		
		if (target == MailTarget.LOGIN) {
			 notifyNewMail(MailTarget.LOGIN, "", systemMail.getMailTime());
		}
		
		if (target == MailTarget.PAYMENT) {
		 	notifyNewMail(MailTarget.PAYMENT, "", systemMail.getMailTime());
		}
		
		if (target == MailTarget.PARTNER) {	
			notifyNewMail(MailTarget.PARTNER, systemMail.getPartner(), null);
		} else {
			List<String> userIdList = new ArrayList<String>();

			String[] lodoIds = StringUtils.split(userLodoIds, ",");
			for (String ftId : lodoIds) {
				User user = this.userService.getByFtId(ftId);
				if (user != null) {
					int maxUserMailId = this.userMailDao.getMaxUserMailId(user.getUserId());
					
					UserMail userMail = new UserMail();
					userMail.setUserMailId(maxUserMailId + 1);
					userMail.setUserId(user.getUserId());
					userMail.setSystemMailId(systemMailId);
					userMail.setStatus(MailStatus.STATUS_NEW);
					userMail.setEmailType(ADMIN_MAIL);
					userMail.setReceiveStatus(MailStatus.STATUS_NOT_RECEIVE);
					userMail.setCreatedTime(now);
					userMail.setUpdatedTime(now);

					List<UserMail> userMailList = new ArrayList<UserMail>();
					userMailList.add(userMail);					
					this.userMailDao.add(user.getUserId(), userMailList);
					
					userIdList.add(user.getUserId());
				}
			}

			notifyNewMail(userIdList);
		}
	}
	
	public boolean sendApplyFriend(String userId, List<String> paramList, int mailId, String fromUserId) {
		User user = this.userService.getByUserId(userId);
		List<String> userIdList = new ArrayList<String>();
		SystemMailInternal internal = systemMailInternalDao.getMail(mailId);
		
		if(internal == null){
			LogSystem.warn("系统内部邮件不存在"+mailId);
			return false;
		}
		
		if(paramList == null){
			paramList = new ArrayList<String>();
		}
		
		if (user != null) {
			int maxUserMailId = this.userMailDao.getMaxUserMailId(user.getUserId());
			
			Date now = new Date();
			UserMail userMail = new UserMail();
			userMail.setUserMailId(maxUserMailId + 1);
			userMail.setUserId(user.getUserId());
			userMail.setEmailType(internal.getLogicType());
			userMail.setEmailShowType(internal.getShowType());
			userMail.setSystemMailId(mailId+"");
			userMail.setStatus(MailStatus.STATUS_NEW);
			userMail.setReceiveStatus(MailStatus.STATUS_NOT_RECEIVE);
			userMail.setCreatedTime(now);
			userMail.setUpdatedTime(now);
			userMail.setParam(StringUtils.join(paramList, ","));
			userMail.setFromUserId(fromUserId);
			List<UserMail> userMailList = new ArrayList<UserMail>();
			if(!StringUtils.isBlank(internal.getTools()) ){
				userMail.setRewards(internal.getTools());
			}
			userMailList.add(userMail);
			this.userMailDao.add(user.getUserId(), userMailList);
			
			userIdList.add(user.getUserId());
			notifyNewMail(userIdList);
			return true;
		}else{
			LogSystem.warn("发送内部邮件找不到用户" + userId);
		}
		return false;
	}
	
	public boolean send(String userId, List<String> paramList, int mailId) {
		User user = this.userService.getByUserId(userId);
		List<String> userIdList = new ArrayList<String>();
		SystemMailInternal internal = systemMailInternalDao.getMail(mailId);
		
		if(internal == null){
			LogSystem.warn("系统内部邮件不存在"+mailId);
			return false;
		}
		
		if(paramList == null){
			paramList = new ArrayList<String>();
		}
		
		if (user != null) {
			int maxUserMailId = this.userMailDao.getMaxUserMailId(user.getUserId());
			
			Date now = new Date();
			UserMail userMail = new UserMail();
			userMail.setUserMailId(maxUserMailId + 1);
			userMail.setUserId(user.getUserId());
			userMail.setEmailType(internal.getLogicType());
			userMail.setEmailShowType(internal.getShowType());
			userMail.setSystemMailId(mailId+"");
			userMail.setStatus(MailStatus.STATUS_NEW);
			userMail.setReceiveStatus(MailStatus.STATUS_NOT_RECEIVE);
			userMail.setCreatedTime(now);
			userMail.setUpdatedTime(now);
			userMail.setParam(StringUtils.join(paramList, ","));
			List<UserMail> userMailList = new ArrayList<UserMail>();
			if(!StringUtils.isBlank(internal.getTools()) ){
				userMail.setRewards(internal.getTools());
			}
			userMailList.add(userMail);
			this.userMailDao.add(user.getUserId(), userMailList);

			userIdList.add(user.getUserId());
			notifyNewMail(userIdList);
			return true;
		}else{
			LogSystem.warn("发送内部邮件找不到用户" + userId);
		}
		return false;
	}
	
	public boolean send(List<String> userIds, List<String> paramList, int mailId) {
		if(userIds == null || userIds.size() == 0){
			return false;
		}
		
		SystemMailInternal internal = systemMailInternalDao.getMail(mailId);
		if(internal == null){
			LogSystem.warn("系统内部邮件不存在"+mailId);
			return false;
		}
		
		if(paramList == null){
			paramList = new ArrayList<String>();
		}
		
		for(String userId : userIds){
			int maxUserMailId = this.userMailDao.getMaxUserMailId(userId);
			
			Date now = new Date();
			UserMail userMail = new UserMail();
			userMail.setUserMailId(maxUserMailId + 1);
			userMail.setUserId(userId);
			userMail.setSystemMailId(mailId + "");
			userMail.setEmailType(internal.getLogicType());
			userMail.setEmailShowType(internal.getShowType());
			userMail.setStatus(MailStatus.STATUS_NEW);
			userMail.setReceiveStatus(MailStatus.STATUS_NOT_RECEIVE);
			userMail.setCreatedTime(now);
			userMail.setUpdatedTime(now);
			userMail.setParam(StringUtils.join(paramList, ","));
			if(!StringUtils.isBlank(internal.getTools()) ){
				userMail.setRewards(internal.getTools());
			}
			List<UserMail> userMailList = new ArrayList<UserMail>();
			userMailList.add(userMail);
			this.userMailDao.add(userId, userMailList);
	    }
		
		notifyNewMail(userIds);
		return true;
	}
	
	public boolean send(String userId, List<String> paramList, int mailId, String rewards) {
		User user = this.userService.getByUserId(userId);
		List<String> userIdList = new ArrayList<String>();
		SystemMailInternal internal = systemMailInternalDao.getMail(mailId);
		
		if(internal == null){
			LogSystem.warn("系统内部邮件不存在"+mailId);
			return false;
		}
		
		if(paramList == null){
			paramList = new ArrayList<String>();
		}
		
		if (user != null) {
			int maxUserMailId = this.userMailDao.getMaxUserMailId(user.getUserId());
			
			Date now = new Date();
			UserMail userMail = new UserMail();
			userMail.setUserId(user.getUserId());
			userMail.setUserMailId(maxUserMailId + 1);
			userMail.setEmailType(internal.getLogicType());
			userMail.setEmailShowType(internal.getShowType());
			userMail.setSystemMailId(mailId + "");
			userMail.setStatus(MailStatus.STATUS_NEW);
			userMail.setReceiveStatus(MailStatus.STATUS_NOT_RECEIVE);
			userMail.setCreatedTime(now);
			userMail.setUpdatedTime(now);
			userMail.setParam(StringUtils.join(paramList, ","));
			userMail.setRewards(rewards);
			List<UserMail> userMailList = new ArrayList<UserMail>();
			userMailList.add(userMail);
			this.userMailDao.add(user.getUserId(), userMailList);
			
			userIdList.add(user.getUserId());
			notifyNewMail(userIdList);
			return true;
		}else{
			LogSystem.warn("发送内部邮件找不到用户" + userId);
		}
		return false;
	}
	
	private static final int NO_REWARD_RECEIVE = 2001;
	/**
	 * 一键领取
	 * 
	 * @param userId
	 * @param userMailIds
	 * @return
	 */
	public MailAction_oneClickReceiveRes oneClickReceive(String userId) {
		List<UserMail> userMailList = this.userMailDao.getList(userId, MailStatus.STATUS_NOT_RECEIVE);
		if (userMailList == null || userMailList.size() == 0)
			throw new ServiceException(NO_REWARD_RECEIVE, "无附件可领取");
		
		List<GoodsBeanBO> dropList = new ArrayList<GoodsBeanBO>();
		Map<Integer, List<GoodsBeanBO>> canReceiveMap = new HashMap<Integer, List<GoodsBeanBO>>();
		
		for (UserMail userMail : userMailList) {
			SystemMail systemMail = null;
			SystemMailInternal systemMailInternal = null;
	        if (userMail.getEmailType() == ADMIN_MAIL) {
	        	systemMail = this.systemMailDao.getSystemMail(userMail.getSystemMailId());
	        } else {
	        	systemMailInternal = this.systemMailInternalDao.getMail(NumberUtils.toInt(userMail.getSystemMailId()));
	        }
	        
			if (systemMail == null && systemMailInternal == null) 
				continue;

			if (userMail.getReceiveStatus() == MailStatus.STATUS_RECEIVE) 
				continue;
			
	        String tools = "";
	        if(systemMail != null){
	        	tools = systemMail.getToolIds();
	        }
	        
	        if(systemMailInternal !=null){
	        	tools = systemMailInternal.getTools();
	        }
	        
	        //邮件本身的附件内容大于模板中存在的内容
	        if(!StringUtils.isBlank(userMail.getRewards())){
	        	tools = userMail.getRewards();
	        }
	        
			List<GoodsBeanBO> dropToolBOList = GoodsHelper.parseDropGoods(tools);
			if (dropToolBOList.size() == 0) 
				continue;
			
			dropList.addAll(dropToolBOList);
			canReceiveMap.put(userMail.getUserMailId(), dropToolBOList);
		}		
		
		if (dropList.size() == 0)
			throw new ServiceException(NO_REWARD_RECEIVE, "无附件可领取");
		
		//检测背包
		userService.checkBag(userId, dropList);
		List<GoodsBeanBO> receiveList = new ArrayList<GoodsBeanBO>();
		for (Integer userMailId : canReceiveMap.keySet()) {
			if (!this.userMailDao.updateReceiveStatus(userId, userMailId, MailStatus.STATUS_RECEIVE)) 
				continue;
			
			receiveList.addAll(canReceiveMap.get(userMailId));
		}
		
		if (receiveList.size() == 0)
			throw new ServiceException(SystemConstant.FAIL_CODE, "更新邮件信息失败");
		
		CommonGoodsBeanBO drop = goodsDealService.sendGoods(userId, receiveList, GoodsUseType.ADD_BY_EMAIL);		
		MailAction_oneClickReceiveRes res = new MailAction_oneClickReceiveRes();
		res.setDrop(drop);
		return res;
	}
	
	/**
	 * 获取用户邮件
	 * 
	 * @param userId
	 * @param userMailId
	 * @return
	 */
	public UserMail getUserMail(String userId, int userMailId) {
		return this.userMailDao.get(userId, userMailId);
	}
	
	/**
	 * 改变用户邮件状态
	 * 
	 * @param userId
	 * @param userMailId
	 * @param emailType
	 * @return
	 */
	public boolean updateUserMail(String userId, int userMailId, int emailType) {
		return this.userMailDao.updateUserMail(userId, userMailId, emailType);
	}
	
	/**
	 * 判断是否有新邮件
	 * 
	 * @param userId
	 * @return
	 */
	public boolean hasNewMail(String userId) {
		MailAction_getMailListRes res = this.getMailList(userId, 0);
		for (UserMailBO userMailBO : res.getUserMailList()) {
			if (userMailBO.getStatus() == MailStatus.STATUS_NEW) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 搜索邮件
	 * 
	 * @param userId
	 * @return
	 */
	public List<ResUserMailBO> searchMail(String userId) {
		List<UserMail> userMailList = userMailDao.getList(userId);
		List<ResUserMailBO> resBOList = Lists.newArrayList();
		
		for (UserMail u : userMailList) {
			if (u.getEmailType() == ADMIN_MAIL) { // 系统邮件
				ResUserMailBO res = new ResUserMailBO();
				res.setUserMailId(u.getUserMailId());
				res.setUserId(u.getUserId());
				res.setEmailType(u.getEmailType());
				res.setEmailShowType(u.getEmailShowType());
				res.setSystemMailId(u.getSystemMailId());
				res.setStatus(u.getStatus());
				res.setReceiveStatus(u.getReceiveStatus());
				res.setParam(u.getParam());
				res.setCreatedTime(u.getCreatedTime().getTime());
				res.setUpdatedTime(u.getUpdatedTime().getTime());
				
				resBOList.add(res);
			}
		}
		
		for (ResUserMailBO u : resBOList) {
			SystemMail systemMail = this.systemMailDao.getSystemMail(u.getSystemMailId());
			u.setSourceId(systemMail.getSourceId());
		}
		return resBOList;
	}
	
	/**
	 * 邮件通知
	 * 
	 * @param target
	 * @param startTime
	 * @param endTime
	 */
	private void notifyNewMail(int target, String startTimeStr, Date mailTime) {
		Date startTime = null;
		List<String> userIdList = null;
		
		// 只发送给时间段内注册的所有用户
		if (target == MailTarget.ALL) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				startTime = sdf.parse(startTimeStr);
			} catch (Exception e) {
				startTimeStr = "";
			}
		
			// 所有用户
			if (startTime == null) {
				notifyNewMailToAll();
			} else {
				userIdList = this.userService.getBetweenRegTimeList(startTime, mailTime);				
			}
		// 只发送给这个时间段
		} else if (target == MailTarget.LOGIN) {
			userIdList = this.userService.getLoginTimeUserIdList(mailTime);
		// 时间段内充值的人
		} else if (target == MailTarget.PAYMENT) {
			userIdList = this.userService.getHasPaymentUserIdList(mailTime);
		// 某个渠道
		} else if (target == MailTarget.PARTNER) {
			userIdList = this.userMapperService.getUserIdListByPartner(startTimeStr);
		}
		                                                                                                                             
		if (userIdList == null || userIdList.size() == 0)
			return;
		
		notifyNewMail(userIdList);
	}
	
	/**
	 * 推送新邮件
	 * 
	 * @param userIdList
	 */
	public void notifyNewMail(List<String> userIdList) {
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_TO_THIS_PERSON);
		
		String[] userIdArr = {};
		userIdArr = userIdList.toArray(userIdArr);
		restrictionsRule.addUserBath(userIdArr);
		
		Mail_pushNewEmailNotify notify = new Mail_pushNewEmailNotify();
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Mail_pushNewEmail", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
	}
	
	/**
	 * 给当前所有用户推送新邮件
	 * 
	 * @param userIdList
	 */
	public void notifyNewMailToAll() {
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
		
		Mail_pushNewEmailNotify notify = new Mail_pushNewEmailNotify();
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Mail_pushNewEmail", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
	}
}

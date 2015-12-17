package com.fantingame.game.mywar.logic.chat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.IllegalWordUtills;
import com.fantingame.game.msgbody.client.chat.ChatAction_campOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_getChatRecordRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_legionOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_nearbyOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_privateOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_worldOfChatRes;
import com.fantingame.game.msgbody.client.chat.UserChatRecordBO;
import com.fantingame.game.msgbody.notify.chat.Chat_pushChatInfoNotify;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.chat.dao.impl.cache.SystemChatDaoCache;
import com.fantingame.game.mywar.logic.chat.dao.impl.mysql.UserChatRecordDaoMysql;
import com.fantingame.game.mywar.logic.chat.model.SystemChat;
import com.fantingame.game.mywar.logic.chat.model.UserChatRecord;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.friend.model.UserBlackInfo;
import com.fantingame.game.mywar.logic.friend.service.FriendService;
import com.fantingame.game.mywar.logic.legion.model.LegionInfo;
import com.fantingame.game.mywar.logic.legion.model.UserLegionInfo;
import com.fantingame.game.mywar.logic.legion.service.LegionService;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.mywar.logic.message.factory.MessageFactory;
import com.fantingame.game.mywar.logic.scene.service.SceneService;
import com.fantingame.game.mywar.logic.user.constant.UserConstant;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.google.common.collect.Maps;

public class ChatService {	

	@Autowired
	private UserChatRecordDaoMysql userChatRecordDao;
	@Autowired
	private FriendService friendService;
	@Autowired 
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	@Autowired
	private SceneService sceneService;
	@Autowired
	private LegionService legionService;
	@Autowired
	private SystemChatDaoCache systemChatDaoCache;
	
	/**
	 * 存储记录
	 */
	private static Map<String, Map<Integer, List<UserChatRecord>>> chatRecordMap = new HashMap<String, Map<Integer, List<UserChatRecord>>>();
	

	private final static int CHAT_TYPE_WORLD = 1;
	private final static int CHAT_TYPE_CAMP = 2;
	private final static int CHAT_TYPE_LEGION = 3;
	private final static int CHAT_TYPE_FRIEND = 4;
	private final static int CHAT_TYPE_NEARBY = 5;
	
	private static final int CHAT_CONTENT_TOO_LONG = 2001;
	private static final int CHAT_CONTENT_SAME = 2002;
	/**
	 * 世界聊天
	 * 
	 * @param userId
	 * @param content
	 * @return
	 */
	public ChatAction_worldOfChatRes worldOfChat(String userId, String content) {
		if (StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，发送内容为空");
	
		int maxNum = this.configDataDao.getInt(ConfigKey.chat_char_num_max, 50);
		if (content.length() > maxNum)
			throw new ServiceException(CHAT_CONTENT_TOO_LONG, "字数超出限制");
		
		content = IllegalWordUtills.replaceWords(content);		
		UserChatRecord userChatRecord = createdUserChatRecord(userId, "", content, CHAT_TYPE_WORLD);
		
		// 判断是否相同内容
		if (chatRecordMap.containsKey(userId)) {
			List<UserChatRecord> chatList = chatRecordMap.get(userId).get(CHAT_TYPE_WORLD);
			if (chatList != null && chatList.size() > 0 && isSameContent(content, CHAT_TYPE_WORLD, chatList))
				throw new ServiceException(CHAT_CONTENT_SAME, "与上一条内容相同，请10s后再发送");
		}
		
//		boolean success = this.userChatRecordDao.add(userChatRecord);
//		if (!success)
//			throw new ServiceException(SystemConstant.FAIL_CODE, "添加世界聊天，添加记录失败");		
		
		UserChatRecordBO userChatRecordBO = createUserChatRecordBO(userChatRecord);
		ChatAction_worldOfChatRes res = new ChatAction_worldOfChatRes();
		res.setUserChatRecordBO(userChatRecordBO);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
		String[] userIds = this.friendService.getInUserBlackIds(userId);
		if (userIds != null && userIds.length > 0)
			restrictionsRule.addUserBath(userIds);
		
		Chat_pushChatInfoNotify notify = new Chat_pushChatInfoNotify();
		notify.setUserChatRecordBO(userChatRecordBO);
		
		// 世界聊天的房间id
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Chat_pushChatInfo", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
		putInChatRewardList(CHAT_TYPE_WORLD, userChatRecord);
		return res;
	}
	
	/**
	 * 判断10s内是否有相同的内容
	 * 
	 * @param content
	 * @param userChatRecord
	 * @return
	 */
	private boolean isSameContent(String content, int type, List<UserChatRecord> recordList) {
		List<UserChatRecord> tempList = new ArrayList<UserChatRecord>();		
		Date now = new Date();
		boolean isSame = false;		
		String userId = "";
		for (UserChatRecord userChatRecord : recordList) {
			userId = userChatRecord.getUserId();
			if ((now.getTime() - userChatRecord.getCreatedTime().getTime() < 10000)) {
				tempList.add(userChatRecord);
				if (userChatRecord.getContent().equals(content))
					isSame = true;
			}
		}		
		
		Map<Integer, List<UserChatRecord>> map = Maps.newConcurrentMap();
		map.put(type, tempList);
		chatRecordMap.put(userId, map);
		return isSame;
	}
	
	/**
	 * 阵营聊天
	 * 
	 * @param userId
	 * @param content
	 * @return
	 */
	public ChatAction_campOfChatRes campOfChat(String userId, String content) {
		if (StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，发送内容为空");
	
		int maxNum = this.configDataDao.getInt(ConfigKey.chat_char_num_max, 50);
		if (content.length() > maxNum)
			throw new ServiceException(CHAT_CONTENT_TOO_LONG, "字数超出限制");

		User user = this.userService.getByUserId(userId);
		content = IllegalWordUtills.replaceWords(content);		
		UserChatRecord userChatRecord = createdUserChatRecord(userId, "", content, CHAT_TYPE_CAMP);
		userChatRecord.setCamp(user.getCamp());
		
		// 判断是否相同内容
		if (chatRecordMap.containsKey(userId)) {
			List<UserChatRecord> chatList = chatRecordMap.get(userId).get(CHAT_TYPE_CAMP);
			if (chatList != null && chatList.size() > 0 && isSameContent(content, CHAT_TYPE_CAMP, chatList))
				throw new ServiceException(CHAT_CONTENT_SAME, "与上一条内容相同，请10s后再发送");
		}
		
//		boolean success = this.userChatRecordDao.add(userChatRecord);
//		if (!success)
//			throw new ServiceException(SystemConstant.FAIL_CODE, "阵营聊天，添加记录失败");
		
		UserChatRecordBO userChatRecordBO = createUserChatRecordBO(userChatRecord);
		ChatAction_campOfChatRes res = new ChatAction_campOfChatRes();
		res.setUserChatRecordBO(userChatRecordBO);
		
		String roomId = "";
		if (user.getCamp() == UserConstant.ALLIANCE) {
			roomId = "cr_" + ChatConstant.ALLIANCE_CHAT_ROOMID;
		} else if (user.getCamp() == UserConstant.HORDE){
			roomId = "cr_" + ChatConstant.HORDE_CHAT_ROOMID;
		}		
	
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
		restrictionsRule.addUser(userId);
		String[] userIds = this.friendService.getInUserBlackIds(userId);
		if (userIds != null && userIds.length > 0)
			restrictionsRule.addUserBath(userIds);
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Chat_pushChatInfo", roomId, userChatRecordBO, restrictionsRule);
		putInChatRewardList(CHAT_TYPE_CAMP, userChatRecord);
		return res;
	}	
	
	private void putInChatRewardList(int type, UserChatRecord userChatRecord) {
		String userId = userChatRecord.getUserId();
		if (chatRecordMap.containsKey(userId)) {
			if (chatRecordMap.get(userId).containsKey(type)) {
				chatRecordMap.get(userId).get(type).add(userChatRecord);
			} else {			
				List<UserChatRecord> list = new ArrayList<UserChatRecord>();
				list.add(userChatRecord);
				
				chatRecordMap.get(userId).put(type, list);
			}
		} else {
			Map<Integer, List<UserChatRecord>> map = Maps.newConcurrentMap();			
			List<UserChatRecord> list = new ArrayList<UserChatRecord>();
			list.add(userChatRecord);
			
			map.put(type, list);
			chatRecordMap.put(userId, map);
		}
	}
	
	private static final int NOT_THIS_USER = 2003;
	private static final int HAS_ADD_BLACK = 2006;
	private static final int IN_TARGET_BLACKLIST = 2007;
	private static final int CHAT_TO_SELF = 2008;
	/**
	 * 私聊
	 * 
	 * @param userId
	 * @param toUserId
	 * @param name
	 * @param content
	 * @return
	 */
	public ChatAction_privateOfChatRes privateOfChat(String userId, String toUserId, String name, String content) {
		if (StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，发送内容为空");
		
		if (StringUtils.isBlank(name) && StringUtils.isBlank(toUserId))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，用户id和用户名都是空的.....发个蛋丫");
		
		int maxNum = this.configDataDao.getInt(ConfigKey.chat_char_num_max, 50);
		if (content.length() > maxNum)
			throw new ServiceException(CHAT_CONTENT_TOO_LONG, "字数超出限制");
		
		// 判断用户是否存在
		if (StringUtils.isBlank(toUserId)) {
			User targetUser = this.userService.getByUserName(name);
			if (targetUser == null)
				throw new ServiceException(NOT_THIS_USER, "没有该用户");
					
			toUserId = targetUser.getUserId();
		}
		
		if (userId.equals(toUserId))
			throw new ServiceException(CHAT_TO_SELF, "不要自己和自己聊天哦");
		
		UserBlackInfo userBlackInfo = this.friendService.getUserBlackInfo(userId, toUserId);
		if (userBlackInfo != null)
			throw new ServiceException(HAS_ADD_BLACK, "你已经把对方拉黑");
		
		userBlackInfo = this.friendService.getUserBlackInfo(toUserId, userId);
		if (userBlackInfo != null)
			throw new ServiceException(IN_TARGET_BLACKLIST, "对方已经把你拉黑");
		
		content = IllegalWordUtills.replaceWords(content);		
		// 判断是否相同内容
		if (chatRecordMap.containsKey(userId)) {
			List<UserChatRecord> chatList = chatRecordMap.get(userId).get(CHAT_TYPE_FRIEND);
			if (chatList != null && chatList.size() >0 && isSameContent(content, CHAT_TYPE_FRIEND, chatList))
				throw new ServiceException(CHAT_CONTENT_SAME, "与上一条内容相同，请10s后再发送");
		}
		
		UserChatRecord userChatRecord = createdUserChatRecord(userId, toUserId, content, CHAT_TYPE_FRIEND);		
//		boolean success = this.userChatRecordDao.add(userChatRecord);
//		if (!success)
//			throw new ServiceException(SystemConstant.FAIL_CODE, "添加好友私聊记录，添加失败");

		UserChatRecordBO userChatRecordBO = createUserChatRecordBO(userChatRecord);
		// 推送给用户   不在线则留言，留言以邮件形式发送
		if (userService.isOnline(toUserId)) {
			MsgDispatchCenter.disPatchUserMsg("Chat_pushChatInfo", toUserId, userChatRecordBO);
		} else {
			this.mailService.sendEmail(userId, toUserId, name, content);
		}		
		
		putInChatRewardList(CHAT_TYPE_FRIEND, userChatRecord);		
		ChatAction_privateOfChatRes res = new ChatAction_privateOfChatRes();
		res.setUserChatRecordBO(userChatRecordBO);
		return res;
	}
	
	/**
	 * 附近聊天频道
	 * 
	 * @param userId
	 * @param content
	 * @return
	 */
	public ChatAction_nearbyOfChatRes nearbyOfChat(String userId, String content) {
		if (StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，发送内容为空");
	
		int maxNum = this.configDataDao.getInt(ConfigKey.chat_char_num_max, 50);
		if (content.length() > maxNum)
			throw new ServiceException(CHAT_CONTENT_TOO_LONG, "字数超出限制");
		
		content = IllegalWordUtills.replaceWords(content);		
		UserChatRecord userChatRecord = createdUserChatRecord(userId, "", content, CHAT_TYPE_NEARBY);
		
		// 判断是否相同内容
		if (chatRecordMap.containsKey(userId)) {
			List<UserChatRecord> chatList = chatRecordMap.get(userId).get(CHAT_TYPE_NEARBY);
			if (chatList != null && chatList.size() > 0 && isSameContent(content, CHAT_TYPE_NEARBY, chatList))
				throw new ServiceException(CHAT_CONTENT_SAME, "与上一条内容相同，请10s后再发送");
		}	
		
		UserChatRecordBO userChatRecordBO = createUserChatRecordBO(userChatRecord);
		ChatAction_nearbyOfChatRes res = new ChatAction_nearbyOfChatRes();
		res.setUserChatRecordBO(userChatRecordBO);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
		String[] userIds = this.friendService.getInUserBlackIds(userId);
		if (userIds != null && userIds.length > 0)
			restrictionsRule.addUserBath(userIds);
		
		Chat_pushChatInfoNotify notify = new Chat_pushChatInfoNotify();
		notify.setUserChatRecordBO(userChatRecordBO);
		
		// 附近聊天的房间id
		String roomId = this.sceneService.getUserSceneRoomId(userId);
		if (StringUtils.isNotBlank(roomId))
			MsgDispatchCenter.disPatchUserLowerRoomMsg("Chat_pushChatInfo", roomId, notify, restrictionsRule);
		
		putInChatRewardList(CHAT_TYPE_NEARBY, userChatRecord);
		return res;
	} 
	
	/**
	 * 构造聊天记录
	 * 
	 * @param userId
	 * @param toUserId
	 * @param content
	 * @param type
	 * @return
	 */
	private UserChatRecord createdUserChatRecord(String userId, String toUserId, String content, int type) {
		UserChatRecord userChatRecord = new UserChatRecord();
		userChatRecord.setUserId(userId);
		userChatRecord.setContent(content);
		userChatRecord.setType(type);
		userChatRecord.setCreatedTime(new Date());
		userChatRecord.setUpdatedTime(new Date());

		if (type == CHAT_TYPE_FRIEND)
			userChatRecord.setTargetUserId(toUserId);
		
		return userChatRecord;
	}
	
	/**
	 * 获取聊天记录（暂不存储聊天记录）
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public ChatAction_getChatRecordRes getChatRecord(String userId, int type) {
		if (type != CHAT_TYPE_WORLD || type != CHAT_TYPE_CAMP || 
				type != CHAT_TYPE_LEGION || type != CHAT_TYPE_FRIEND)
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数不对，获取聊天记录的type[" + type + "]");
		
		User user = this.userService.getByUserId(userId);
		List<UserChatRecord> recordList = new ArrayList<UserChatRecord>();
		if (type == CHAT_TYPE_WORLD) {
			recordList = this.userChatRecordDao.getUserChatRecord(type, 2);
		} else if (type == CHAT_TYPE_CAMP) {
			recordList = this.userChatRecordDao.getCampChatRecord(user.getCamp(), type, 2);
		} else if (type == CHAT_TYPE_FRIEND) {
			recordList = this.userChatRecordDao.getUserChatRecord(type, 2);
		// 军团聊天
		} else {
			
		}		
		
		List<UserChatRecordBO> list = new ArrayList<UserChatRecordBO>();
		for (UserChatRecord userChatRecord : recordList) {
			UserChatRecordBO bo = createUserChatRecordBO(userChatRecord);
			list.add(bo);
		}
		
		ChatAction_getChatRecordRes res = new ChatAction_getChatRecordRes();
		res.setUserChatRecordList(list);
		return res;
	}
	
	private final static int ERROR_NOT_IN_LEGION = 2003;
	private final static int ERROR_LEGION_DISMISS = 2004;
	/**
	 * 公会频道
	 * 
	 * @param userId
	 * @param content
	 * @return
	 */
	public ChatAction_legionOfChatRes legionOfChat(String userId, String content) {
		UserLegionInfo userLegionInfo = this.legionService.getUserLegionInfo(userId);
		if (userLegionInfo == null || userLegionInfo.getStatus() != 1)
			throw new ServiceException(ERROR_NOT_IN_LEGION, "不在公会里");
		
		LegionInfo legionInfo = this.legionService.getLegionInfoBy(userLegionInfo.getLegionId());
		if (legionInfo == null)
			throw new ServiceException(ERROR_LEGION_DISMISS, "军团已解散了");
		
		if (StringUtils.isBlank(content))
			throw new ServiceException(SystemErrorCode.PARAM_ERROR, "参数错误，发送内容为空");
	
		int maxNum = this.configDataDao.getInt(ConfigKey.chat_char_num_max, 50);
		if (content.length() > maxNum)
			throw new ServiceException(CHAT_CONTENT_TOO_LONG, "字数超出限制");
		
		content = IllegalWordUtills.replaceWords(content);		
		UserChatRecord userChatRecord = createdUserChatRecord(userId, "", content, CHAT_TYPE_LEGION);
		
		// 判断是否相同内容
		if (chatRecordMap.containsKey(userId)) {
			List<UserChatRecord> chatList = chatRecordMap.get(userId).get(CHAT_TYPE_LEGION);
			if (chatList != null && chatList.size() > 0 && isSameContent(content, CHAT_TYPE_LEGION, chatList))
				throw new ServiceException(CHAT_CONTENT_SAME, "与上一条内容相同，请10s后再发送");
		}	
		
		UserChatRecordBO userChatRecordBO = createUserChatRecordBO(userChatRecord);
		ChatAction_legionOfChatRes res = new ChatAction_legionOfChatRes();
		res.setUserChatRecordBO(userChatRecordBO);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_TO_THIS_PERSON);
		Chat_pushChatInfoNotify notify = new Chat_pushChatInfoNotify();
		notify.setUserChatRecordBO(userChatRecordBO);
		
		String[] userIds = this.legionService.getLegionUserIds(userId, legionInfo.getLegionId());
		if (userIds != null && userIds.length > 0)
			restrictionsRule.addUserBath(userIds);
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Chat_pushChatInfo", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, userChatRecordBO, restrictionsRule);
		
		putInChatRewardList(CHAT_TYPE_LEGION, userChatRecord);
		return res;
	}
	
	public void legionOfChat(int chatId, String legionId, Map<String, String> params) {
		SystemChat systemChat = systemChatDaoCache.getSystemChat(chatId);
		UserChatRecordBO userChatRecordBO = new UserChatRecordBO();
		userChatRecordBO.setUserId("");
		userChatRecordBO.setUserName(systemChat.getUserName());
		userChatRecordBO.setContent(MessageFactory.getChatContent(params, systemChat.getChatContent()));		
		userChatRecordBO.setType(CHAT_TYPE_LEGION);
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_TO_THIS_PERSON);
		Chat_pushChatInfoNotify notify = new Chat_pushChatInfoNotify();
		notify.setUserChatRecordBO(userChatRecordBO);
		
		String[] userIds = this.legionService.getLegionUserIds("", legionId);
		if (userIds != null && userIds.length > 0)
			restrictionsRule.addUserBath(userIds);
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Chat_pushChatInfo", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, userChatRecordBO, restrictionsRule);
	}
	
	/**
	 * 构造聊天信息BO
	 * 
	 * @param userChatRecord
	 * @return
	 */
	private UserChatRecordBO createUserChatRecordBO(UserChatRecord userChatRecord) {
		UserChatRecordBO userChatRecordBO = new UserChatRecordBO();
		User user = this.userService.getByUserId(userChatRecord.getUserId());

		userChatRecordBO.setUserId(user.getUserId());
		userChatRecordBO.setUserName(user.getName());
		userChatRecordBO.setContent(userChatRecord.getContent());
		
		if (userChatRecord.getType() == CHAT_TYPE_FRIEND) {
			User targetUser = this.userService.getByUserId(userChatRecord.getTargetUserId());
			userChatRecordBO.setTargetUserId(targetUser.getUserId());
			userChatRecordBO.setTargetUserName(targetUser.getName());
		}
		
		userChatRecordBO.setType(userChatRecord.getType());
		return userChatRecordBO;
	}
	
}

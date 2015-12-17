package com.fantingame.game.mywar.logic.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.cache.ConfigDataDaoCacheImpl;
import com.fantingame.game.common.utils.Constant;
import com.fantingame.game.common.utils.IllegalWordUtills;
import com.fantingame.game.msgbody.client.message.MessageAction_sendMsgRes;
import com.fantingame.game.msgbody.client.tool.UserToolBO;
import com.fantingame.game.msgbody.notify.message.MessageBO;
import com.fantingame.game.msgbody.notify.message.Message_pushMessageNotify;
import com.fantingame.game.mywar.logic.chat.constant.ChatConstant;
import com.fantingame.game.mywar.logic.common.constant.ConfigKey;
import com.fantingame.game.mywar.logic.common.constant.SystemErrorCode;
import com.fantingame.game.mywar.logic.goods.constant.GoodsType;
import com.fantingame.game.mywar.logic.goods.constant.GoodsUseType;
import com.fantingame.game.mywar.logic.goods.help.GoodsHelper;
import com.fantingame.game.mywar.logic.message.constant.MessageType;
import com.fantingame.game.mywar.logic.message.dao.cache.SystemMessageTemplateDaoCache;
import com.fantingame.game.mywar.logic.message.factory.MessageFactory;
import com.fantingame.game.mywar.logic.message.model.SystemMessageTemplate;
import com.fantingame.game.mywar.logic.tool.constant.ToolConstant;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.mywar.logic.user.bean.UserObject;
import com.fantingame.game.mywar.logic.user.model.User;
import com.fantingame.game.mywar.logic.user.service.UserService;
import com.fantingame.game.server.exception.ServiceException;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.google.common.collect.Maps;

/**
 * 跑马灯业务逻辑
 * 
 * @author yezp
 */
public class MessageService {

	@Autowired
	private SystemMessageTemplateDaoCache systemMessageTemplateDaoCache;
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigDataDaoCacheImpl configDataDao;
	@Autowired
	private ToolService toolService;

	private Map<String, Long> userMsgMap = Maps.newConcurrentMap();
	
	/**
	 * 发送系统跑马灯
	 * 
	 * @param content
	 * @param partnerIds
	 */
	public void sendSystemMsg(String content, String partnerIds) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", content);
		
		sendMsg(params, partnerIds, MessageType.MESSAGE_TYPE_SYSTEM_MSG);
	}
	
	private final static int ERROR_CONTENT_INVAILD = 2001;
	private final static int ERROR_CONTENT_TOO_LONG = 2002;
	private final static int ERROR_SEND_TIME = 2003;
	/**
	 * 发送跑马灯
	 * 
	 * @param userId
	 * @param content
	 */
	public MessageAction_sendMsgRes userSendSystemMsg(String userId, String content) {
		if (StringUtils.isBlank(content))
			throw new ServiceException(ERROR_CONTENT_INVAILD, "内容不能为空");
		
		int maxCharNum = this.configDataDao.getInt(ConfigKey.message_content_max, 30) * 2;
		if (Constant.getBytes(content) > maxCharNum)
			throw new ServiceException(ERROR_CONTENT_TOO_LONG, "内容超出");
		
		Date now = new Date();
		content = IllegalWordUtills.replaceWords(content);
		if (userMsgMap.containsKey(userId)) {
			long time = userMsgMap.get(userId);
			int min = this.configDataDao.getInt(ConfigKey.message_waitting_time, 1) * 60 * 1000;
			if (now.getTime() - time < min)
				throw new ServiceException(ERROR_SEND_TIME, "每隔一分钟才能发一次跑马灯");
		}
		
		UserToolBO userTool = this.toolService.getUserToolBO(userId, ToolConstant.SEND_MSG_TOOL_ID);
		if (userTool == null || userTool.getToolNum() < 0)
			throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");
		
		if (!this.toolService.reduceTool(userId, userTool.getToolId(), 1, GoodsUseType.REDUCE_SEND_MESSGE))
			throw new ServiceException(SystemErrorCode.TOOL_NOT_ENOUGH, "道具不足");
		
		User user = this.userService.getByUserId(userId);
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", user.getName());
		params.put("content", content);
		
		sendMsg(params, "all", MessageType.MESSAGE_TYPE_USER_SEND_MSG);
		userMsgMap.put(userId, now.getTime());
		MessageAction_sendMsgRes res = new MessageAction_sendMsgRes();
		res.setGoodsBeanBO(GoodsHelper.parseDropGood(GoodsType.tool.intValue, userTool.getToolId(), 1));
		return res;
	}
	
	/**
	 * 发送跑马灯
	 * 
	 * @param content
	 * @param partnerIds
	 */
	public void sendMsg(Map<String, String> params, String partnerIds, int msgType) {
		SystemMessageTemplate template = this.systemMessageTemplateDaoCache.getSystemMessageTemplate(msgType);
		List<MessageBO> messageList = MessageFactory.getMessage(params, template.getContent());
		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
		Set<String> userIds = this.userService.getAllOnlineUserId();
		if (userIds.size() == 0)
			return;
		
		String[] userIdArr = {};
		if (!partnerIds.equals("all")) {			
			String[] partnerIdArr = partnerIds.split(",");
			Map<String, String> partnerIdMap = new HashMap<String, String>();
			for (String partnerId : partnerIdArr) {
				partnerIdMap.put(partnerId, partnerId);
			}			
			
			List<String> userIdList = new ArrayList<String>();
			for (String userId : userIds) {
				UserObject userObject = this.userService.getAllOnlineUserObject(userId);
				if (!partnerIdMap.containsKey(userObject.getPartnerId()))
					userIdList.add(userId);				
			}
			
			if (userIdList.size() > 0) {
				userIdArr = userIdList.toArray(userIdArr);
				restrictionsRule.addUserBath(userIdArr);
			}			
		}
		
		Message_pushMessageNotify notify = new Message_pushMessageNotify();
		notify.setMessageList(messageList);
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Message_pushMessage", "cr_" + ChatConstant.WORLD_CHAT_ROOMID, notify, restrictionsRule);
	}
	
}

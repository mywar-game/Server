package com.fantingame.game.mywar.logic.chat.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.chat.ChatAction_campOfChatReq;
import com.fantingame.game.msgbody.client.chat.ChatAction_campOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_getChatRecordReq;
import com.fantingame.game.msgbody.client.chat.ChatAction_getChatRecordRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_legionOfChatReq;
import com.fantingame.game.msgbody.client.chat.ChatAction_legionOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_nearbyOfChatReq;
import com.fantingame.game.msgbody.client.chat.ChatAction_nearbyOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_privateOfChatReq;
import com.fantingame.game.msgbody.client.chat.ChatAction_privateOfChatRes;
import com.fantingame.game.msgbody.client.chat.ChatAction_worldOfChatReq;
import com.fantingame.game.msgbody.client.chat.ChatAction_worldOfChatRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.chat.service.ChatService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

/**
 * 聊天系统
 * 
 * @author yezp
 */
@GameAction
public class ChatAction {

	@Autowired
	private ChatService chatService;
	
	/**
	 * 世界聊天
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble worldOfChat(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ChatAction_worldOfChatReq req = msg.decodeBody(ChatAction_worldOfChatReq.class);
		
		ChatAction_worldOfChatRes res = this.chatService.worldOfChat(userId, req.getContent());
		return res;
	}
	
	/**
	 * 阵营聊天
	 * 
	 * @param mag
	 * @param channel
	 * @return
	 */
	public ICodeAble campOfChat(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ChatAction_campOfChatReq req = msg.decodeBody(ChatAction_campOfChatReq.class);
		
		ChatAction_campOfChatRes res = this.chatService.campOfChat(userId, req.getContent());
		return res;
	}
	
	/**
	 * 私聊
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble privateOfChat(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ChatAction_privateOfChatReq req = msg.decodeBody(ChatAction_privateOfChatReq.class);
		
		ChatAction_privateOfChatRes res = this.chatService.privateOfChat(userId, req.getToUserId(), req.getUserName(), req.getContent());
		return res;
	}

	/**
	 * 附近聊天
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble nearbyOfChat(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		ChatAction_nearbyOfChatReq req = msg.decodeBody(ChatAction_nearbyOfChatReq.class);
		ChatAction_nearbyOfChatRes res = this.chatService.nearbyOfChat(userId, req.getContent());
		return res;
	}
	
	/**
	 * 获取聊天记录
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getChatRecord(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		ChatAction_getChatRecordReq req = msg.decodeBody(ChatAction_getChatRecordReq.class);
		
		ChatAction_getChatRecordRes res = this.chatService.getChatRecord(userId, req.getType());
		return res;
	}
	
	/**
	 * 公会聊天
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble legionOfChat(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		ChatAction_legionOfChatReq req = msg.decodeBody(ChatAction_legionOfChatReq.class);
		ChatAction_legionOfChatRes res = this.chatService.legionOfChat(userId, req.getContent()); 
		return res;
	}
}

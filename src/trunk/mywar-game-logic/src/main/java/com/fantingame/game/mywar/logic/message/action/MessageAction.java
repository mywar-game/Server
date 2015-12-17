package com.fantingame.game.mywar.logic.message.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.message.MessageAction_sendMsgReq;
import com.fantingame.game.msgbody.client.message.MessageAction_sendMsgRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.message.service.MessageService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class MessageAction {

	@Autowired
	private MessageService messageService;
	
	/**
	 * 发送跑马灯
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble sendMsg(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		MessageAction_sendMsgReq req = msg.decodeBody(MessageAction_sendMsgReq.class);
		MessageAction_sendMsgRes res = this.messageService.userSendSystemMsg(userId, req.getContent());
		return res;
	}
	
}

package com.fantingame.game.mywar.logic.mail.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.mail.MailAction_deleteReq;
import com.fantingame.game.msgbody.client.mail.MailAction_deleteRes;
import com.fantingame.game.msgbody.client.mail.MailAction_getMailListReq;
import com.fantingame.game.msgbody.client.mail.MailAction_getMailListRes;
import com.fantingame.game.msgbody.client.mail.MailAction_oneClickDeleteRes;
import com.fantingame.game.msgbody.client.mail.MailAction_oneClickReceiveRes;
import com.fantingame.game.msgbody.client.mail.MailAction_readReq;
import com.fantingame.game.msgbody.client.mail.MailAction_readRes;
import com.fantingame.game.msgbody.client.mail.MailAction_receiveReq;
import com.fantingame.game.msgbody.client.mail.MailAction_receiveRes;
import com.fantingame.game.msgbody.client.mail.MailAction_sendEmailReq;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.mail.service.MailService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

/**
 * 邮件系统
 * 
 * @author yezp
 */
@GameAction
public class MailAction {

	@Autowired
	private MailService mailService;
	
	/**
	 * 获取邮件列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getMailList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MailAction_getMailListReq req = msg.decodeBody(MailAction_getMailListReq.class);
		
		MailAction_getMailListRes res = this.mailService.getMailList(userId, req.getMailId());		
		return res;
	}
	
	/**
	 * 读取邮件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble read(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MailAction_readReq req = msg.decodeBody(MailAction_readReq.class);
		
		MailAction_readRes res = this.mailService.read(userId, req.getUserMailId());
		return res;
	}
	
	/**
	 * 领取邮件附件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receive(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MailAction_receiveReq req = msg.decodeBody(MailAction_receiveReq.class);
		
		MailAction_receiveRes res = this.mailService.receive(userId, req.getUserMailId());
		return res;
	}
	
	/**
	 * 删除邮件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble delete(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MailAction_deleteReq req = msg.decodeBody(MailAction_deleteReq.class);
		
		MailAction_deleteRes res = this.mailService.delete(userId, req.getUserMailIds());
		return res;
	}
	
	/**
	 * 一键删除邮件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble oneClickDelete(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		MailAction_oneClickDeleteRes res = this.mailService.oneClickDelete(userId);
		return res;
	}
	
	/**
	 * 用户发送邮件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble sendEmail(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		MailAction_sendEmailReq req = msg.decodeBody(MailAction_sendEmailReq.class);
		
		this.mailService.sendEmail(userId, req.getToUserId(), req.getName(), req.getContent());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble oneClickReceive(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		MailAction_oneClickReceiveRes res = this.mailService.oneClickReceive(userId);
		return res;
	}
	
}

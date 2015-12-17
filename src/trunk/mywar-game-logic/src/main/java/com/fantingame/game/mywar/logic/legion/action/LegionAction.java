package com.fantingame.game.mywar.logic.legion.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.legion.LegionAction_applyJoinLegionReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_applyJoinLegionRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_appointLegionDeputyReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_appointLegionDeputyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_auditingApplyReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_auditingApplyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_cancleApplyReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_cancleApplyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_commitMessageReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_commitMessageRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_createLegionReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_createLegionRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_deposeLegionDeputyReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_deposeLegionDeputyRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_dismissLegionRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_editDeclarationReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_editDeclarationRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_editNoticeReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_editNoticeRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_fireLegionMemberReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_fireLegionMemberRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getApplyListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getLegionInfoRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getLegionListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getLegionMemberListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getMessageListRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_getUserInvestInfoRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_leaveLegionRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_oneClickRejectRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_transferLegionLeaderReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_transferLegionLeaderRes;
import com.fantingame.game.msgbody.client.legion.LegionAction_userInvestReq;
import com.fantingame.game.msgbody.client.legion.LegionAction_userInvestRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.legion.service.LegionService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class LegionAction {

	@Autowired
	private LegionService legionService;
	
	/**
	 * 创建军团
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble createLegion(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_createLegionReq req = msg.decodeBody(LegionAction_createLegionReq.class);		
		LegionAction_createLegionRes res = this.legionService.createLegion(userId, req.getLegionName(), req.getType());
		return res;
	}
	
	/**
	 * 查看公会列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getLegionList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_getLegionListRes res = this.legionService.getLegionList(userId);
		return res;
	}
	
	/**
	 * 编辑公告
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble editNotice(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_editNoticeReq req = msg.decodeBody(LegionAction_editNoticeReq.class);
		LegionAction_editNoticeRes res = this.legionService.editNotice(userId, req.getNotice());
		return res;
	}
	
	/**
	 * 修改军团宣言
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble editDeclaration(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_editDeclarationReq req = msg.decodeBody(LegionAction_editDeclarationReq.class);
		LegionAction_editDeclarationRes res = this.legionService.editDeclaration(userId, req.getDeclaration());
		return res;
	}
	
	/**
	 * 查看自己的公会信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getLegionInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_getLegionInfoRes res = this.legionService.getLegionInfo(userId);
		return res;
	}
	
	/**
	 * 申请加入军团
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble applyJoinLegion(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_applyJoinLegionReq req = msg.decodeBody(LegionAction_applyJoinLegionReq.class);
		this.legionService.applyJoinLegion(userId, req.getLegionId());
		LegionAction_applyJoinLegionRes res = new LegionAction_applyJoinLegionRes();
		return res;
	}
	
	/**
	 * 取消申请
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble cancleApply(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_cancleApplyReq req = msg.decodeBody(LegionAction_cancleApplyReq.class);
		this.legionService.cancleApply(userId, req.getLegionId());
		LegionAction_cancleApplyRes res = new LegionAction_cancleApplyRes();
		return res;
	}
	
	/**
	 * 获取审核列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getApplyList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_getApplyListRes res = this.legionService.getApplyList(userId);
		return res;
	}
	
	/**
	 * 一键拒绝
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble oneClickReject(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.legionService.oneClickReject(userId);
		LegionAction_oneClickRejectRes res = new LegionAction_oneClickRejectRes();
		return res;
	}
	
	/**
	 * 审核申请
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble auditingApply(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_auditingApplyReq req = msg.decodeBody(LegionAction_auditingApplyReq.class);
		LegionAction_auditingApplyRes res = this.legionService.auditingApply(userId, req.getType(), req.getAuditingUserId());
		return res;
	}
	
	/**
	 * 开除会员
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble fireLegionMember(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_fireLegionMemberReq req = msg.decodeBody(LegionAction_fireLegionMemberReq.class);
		LegionAction_fireLegionMemberRes res = this.legionService.fireLegionMember(userId, req.getFireUserId());
		return res;
	}
	
	/**
	 * 查看公会成员
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getLegionMemberList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_getLegionMemberListRes res = this.legionService.getLegionMemberList(userId);
		return res;
	}
	
	/**
	 * 任命副军团长
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble appointLegionDeputy(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_appointLegionDeputyReq req = msg.decodeBody(LegionAction_appointLegionDeputyReq.class);
		LegionAction_appointLegionDeputyRes res = this.legionService.appointLegionDeputy(userId, req.getDeputyUserId());
		return res;
	}
	
	/**
	 * 罢免副军团长
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble deposeLegionDeputy(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_deposeLegionDeputyReq req = msg.decodeBody(LegionAction_deposeLegionDeputyReq.class);
		LegionAction_deposeLegionDeputyRes res = this.legionService.deposeLegionDeputy(userId, req.getDeputyUserId());
		return res;
	}
	
	/**
	 * 转让会长
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble transferLegionLeader(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_transferLegionLeaderReq req = msg.decodeBody(LegionAction_transferLegionLeaderReq.class);
		LegionAction_transferLegionLeaderRes res = this.legionService.transferLegionLeader(userId, req.getBeLeaderUserId());
		return res;
	}
	
	/**
	 * 离开公会
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble leaveLegion(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.legionService.leaveLegion(userId);
		LegionAction_leaveLegionRes res = new LegionAction_leaveLegionRes();
		return res;
	}
	
	/**
	 * 解散公会
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble dismissLegion(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		this.legionService.dismissLegion(userId);
		LegionAction_dismissLegionRes res = new LegionAction_dismissLegionRes();
		return res;		
	}
	
	/**
	 * 提交留言信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble commitMessage(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_commitMessageReq req = msg.decodeBody(LegionAction_commitMessageReq.class);
		LegionAction_commitMessageRes res = this.legionService.commitMessage(userId, req.getContent());
		return res;
	}
	
	/**
	 * 获取留言列表
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getMessageList(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_getMessageListRes res = this.legionService.getMessageList(userId);
		return res;
	}
	
	/**
	 * 获取用户投资的信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserInvestInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_getUserInvestInfoRes res = this.legionService.getUserInvestInfo(userId);
		return res;
	} 
	
	/**
	 * 用户投资
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble userInvest(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		LegionAction_userInvestReq req = msg.decodeBody(LegionAction_userInvestReq.class);
		LegionAction_userInvestRes res = this.legionService.userInvest(userId, req.getId());
		return res;
	}
}

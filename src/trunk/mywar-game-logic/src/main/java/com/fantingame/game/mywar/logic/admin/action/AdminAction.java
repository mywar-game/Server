package com.fantingame.game.mywar.logic.admin.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.admin.AdminAction_addEquipmentOrToolsReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_addEquipmentOrToolsRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_addHerosReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_addHerosRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_addUserMoneyReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_addUserMoneyRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_getUserInfoReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_getUserInfoRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_recordGuideStepReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_reloadStaticDataReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_searchMailReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_searchMailRes;
import com.fantingame.game.msgbody.server.admin.AdminAction_sendMailReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_sendSystemMsgReq;
import com.fantingame.game.msgbody.server.admin.AdminAction_updateUserLevelReq;
import com.fantingame.game.msgbody.server.admin.ResUserMailBO;
import com.fantingame.game.mywar.logic.admin.service.AdminService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;
/**
 * 游戏管理接口
 * @author Administrator
 *
 */
@GameAction
public class AdminAction {
	
	@Autowired
	private AdminService adminService;
	
    /**
     * 重载静态数据
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble reloadStaticData(Msg msg,Channel channel){
		AdminAction_reloadStaticDataReq req = msg.decodeBody(AdminAction_reloadStaticDataReq.class);
		adminService.reloadStaticData(req.getClassName());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 发送道具或装备
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble addEquipmentOrTools(Msg msg, Channel channel) {
		AdminAction_addEquipmentOrToolsReq req = msg.decodeBody(AdminAction_addEquipmentOrToolsReq.class);
		AdminAction_addEquipmentOrToolsRes res = adminService.addEquipmentOrTools(req.getSendAttachList(), req.getUserIdStr(), req.getIsSendAll());
		
		return res;
	}
	
	/**
	 * 发送英雄
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble addHeros(Msg msg, Channel channel) {
		AdminAction_addHerosReq req = msg.decodeBody(AdminAction_addHerosReq.class);
		AdminAction_addHerosRes res = adminService.addHeros(req.getSendAttachList(), req.getUserIdStr(), req.getIsSendAll());
	
		return res;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble getUserInfo(Msg msg, Channel channel) {
		AdminAction_getUserInfoReq req = msg.decodeBody(AdminAction_getUserInfoReq.class);
		AdminAction_getUserInfoRes res = adminService.getUserInfo(req.getUserId());
		
		return res;
	}
	
	/**
	 * 修改用户等级
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble updateUserLevel(Msg msg, Channel channel) {
		AdminAction_updateUserLevelReq req = msg.decodeBody(AdminAction_updateUserLevelReq.class);
		adminService.updateUserLevel(req.getUserId(), req.getTargetLevel());
		
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 给用户发钻石
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble addUserMoney(Msg msg, Channel channel) {
		AdminAction_addUserMoneyReq req = msg.decodeBody(AdminAction_addUserMoneyReq.class);
		AdminAction_addUserMoneyRes res = adminService.addUserMoney(req.getIsSendAll(), req.getUserIds(), req.getDiamond());
		
		return res;
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble sendMail(Msg msg, Channel channel) {
		AdminAction_sendMailReq req = msg.decodeBody(AdminAction_sendMailReq.class);
		adminService.sendMail(req);
		
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 发送跑马灯
	 * 
	 * @param mag
	 * @param channel
	 * @return
	 */
	public ICodeAble sendSystemMsg(Msg msg, Channel channel) {
		AdminAction_sendSystemMsgReq req = msg.decodeBody(AdminAction_sendSystemMsgReq.class);
		adminService.sendSystemMsg(req.getContent(), req.getPartnerId());
		
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 跳过新手引导
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble recordGuideStep(Msg msg, Channel channel) {
		AdminAction_recordGuideStepReq req = msg.decodeBody(AdminAction_recordGuideStepReq.class);
		adminService.recordGuideStep(req.getUserId(), req.getGuideStep(), req.getIp());
		
		return MsgBuilder.EMPTY_BODY;		
	}
	
	/**
	 * 搜索邮件
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble searchMail(Msg msg, Channel channel) {
		AdminAction_searchMailReq req = msg.decodeBody(AdminAction_searchMailReq.class);
		List<ResUserMailBO> list = adminService.searchMail(req.getUserId());
		
		AdminAction_searchMailRes res = new AdminAction_searchMailRes();
		res.setResUserMailBOList(list);
		return res;
	}
	
}

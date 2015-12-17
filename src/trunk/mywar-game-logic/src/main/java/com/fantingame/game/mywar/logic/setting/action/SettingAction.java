package com.fantingame.game.mywar.logic.setting.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.setting.SettingAction_commitAdviceReq;
import com.fantingame.game.msgbody.client.setting.SettingAction_setDailyTipsReq;
import com.fantingame.game.msgbody.client.setting.SettingAction_setDisplayNumReq;
import com.fantingame.game.msgbody.client.setting.SettingAction_setDisplayNumRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.setting.service.SettingService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;

@GameAction
public class SettingAction {
	
	@Autowired
	private SettingService settingService;
	
	/**
	 * 设置同屏显示人数
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble setDisplayNum(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		SettingAction_setDisplayNumReq req = msg.decodeBody(SettingAction_setDisplayNumReq.class);
		
		int num = this.settingService.setDisplayNum(userId, req.getNum());
		SettingAction_setDisplayNumRes res = new SettingAction_setDisplayNumRes();
		res.setNum(num);
		return res;
	}

	/**
	 * 设置提示
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble setDailyTips(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		SettingAction_setDailyTipsReq req = msg.decodeBody(SettingAction_setDailyTipsReq.class);
		
		this.settingService.setDailyTips(userId, req.getTip());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 提交建议
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble commitAdvice(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		SettingAction_commitAdviceReq req = msg.decodeBody(SettingAction_commitAdviceReq.class);
		this.settingService.commitAdvice(userId, req.getTitle(), req.getContent());
		
		return MsgBuilder.EMPTY_BODY;
	}
	
}

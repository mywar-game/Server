package com.fantingame.game.mywar.logic.tool.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.tool.ToolAction_openBoxReq;
import com.fantingame.game.msgbody.client.tool.ToolAction_openBoxRes;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.tool.service.ToolService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class ToolAction {
	
	@Autowired
	private ToolService toolService;
	
    /**
     * 开宝箱
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble openBox(Msg msg,Channel channel){
		String userId = msg.getMsgHead().getFromID();
		ToolAction_openBoxReq req = msg.decodeBody(ToolAction_openBoxReq.class);
		
		ToolAction_openBoxRes res = toolService.openGiftBox(userId, req.getToolId());
		return res;
	}
	
	
	
}

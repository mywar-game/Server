package com.fantingame.game.gateway.server.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.gateway.server.service.ServerActionService;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.CommomMsgBody;
import com.fantingame.game.msgbody.server.ReqRegisterActionNameMsgBody;
import com.fantingame.game.msgbody.server.ReqRegisterChannelMsgBody;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.ChannelType;
import com.fantingame.game.server.msg.ServerChannelManager;
import com.fantingame.game.server.msg.ServerType;

@GameAction
public class GateWayAction {
	@Autowired
	private ServerActionService serverActionService;
    private int i=0;
    /**
     * 注册接口
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble registerActionName(Msg msg,Channel channel){
    	ReqRegisterActionNameMsgBody req = msg.decodeBody(ReqRegisterActionNameMsgBody.class);
    	for(String actionName:req.getActionNameList()){
        	serverActionService.addActionName(actionName, ServerType.getByName(req.getServerType()));
    	}
    	LogSystem.info("serverType="+req.getServerType()+",添加action数量"+req.getActionNameList().size());
    	return null;
	}
	/**
	 * 注册链接
	 * @param msg
	 * @param channel
	 * @return
	 */
    public ICodeAble registerChannel(Msg msg,Channel channel){
    	ReqRegisterChannelMsgBody msgbody = msg.decodeBody(ReqRegisterChannelMsgBody.class);
		ServerChannelManager.getInstance().addChannel(ServerType.getByName(msgbody.getServerType()),ChannelType.getByName(msgbody.getChannelType()), channel);
		CommomMsgBody body = new CommomMsgBody();
		body.setErrorDescription("恭喜你-"+msgbody.getServerType()+"-注册成功了一条通道");
		i++;
		LogSystem.info("网关服务器收到注册通道的消息 channeltype="+msgbody.getChannelType()+",serverType="+msgbody.getServerType()+"收到消息条数i="+i);
		return body;
    }
}

package com.fantingame.game.mywar.logic.client.bridge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.common.model.MsgHead;
import com.fantingame.game.mywar.logic.client.service.ThreadService;
import com.fantingame.game.server.actionmanager.ActionExcutor;
import com.fantingame.game.server.actionmanager.ActionManager;
import com.fantingame.game.server.annotation.ISingleThreadRuler;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.concurrent.DisruptorExecutor;
import com.fantingame.game.server.monitor.MonitorService;
import com.fantingame.game.server.msg.ByteToMsg;
import com.fantingame.game.server.msg.ChannelType;
import com.fantingame.game.server.msg.ServerMsgManager;

public class LogicChannelBridgeEntry implements IBridgeEntry {
	@Autowired
    private ThreadService threadService;
	public void channelException(Channel channel) {

	}
	public void receivedData(Channel channel, byte[] datas) {
		ByteToMsg byteToMsg = new ByteToMsg(datas);
		//记录请求数
		MonitorService.getInstance().markOneServerRequest();
		if(byteToMsg.parseByteServer(channel)){
			List<Msg> msgVector = byteToMsg.getMsgVector();
			DisruptorExecutor exec = null;
			for(Msg msg:msgVector){
				ISingleThreadRuler singlerThread = ActionManager.isSingleThread(msg.getMsgHead().getCmdCode());
				if(singlerThread!=null){
					long key = singlerThread.getRulerKey(msg);
					exec = threadService.actionGlobThreadPool(msg.getMsgHead().getCmdCode(),key);
				}else{
					if(msg.getMsgHead().getMsgType()==MsgHead.TYPEOFREQUEST&&msg.getMsgHead().getFromType()==MsgHead.TO_OR_FROM_TYPE_USER){//用户的请求消息
						LogSystem.debug("用户请求消息");
						exec = threadService.getUserThreadPool(msg.getMsgHead().getFromID());
					}else if(StringUtils.hasText(msg.getMsgHead().getUserSequense())){//处理服务器消息 需要在 用户的线程中 执行的
						LogSystem.debug("服务器代理用户请求消息");
						exec = threadService.getUserThreadPool(msg.getMsgHead().getUserSequense());
					}else{
						LogSystem.debug("其他服务器消息");
						exec = threadService.getOtherThreadPool();
					}
				}
				dealMsg(exec,msg,channel);
			}
	  }
	}
	private void dealMsg(DisruptorExecutor exec,final Msg msg,final Channel channel){
		exec.execute(new Runnable() {
			@Override
			public void run() {
				Msg result = ActionExcutor.getInstance().excutorAction(msg,channel);
				if(result!=null){
					ServerMsgManager.getInstance().tryPublishEvent(ChannelType.logicChannel, result);
				}
			}
		});
	}
}

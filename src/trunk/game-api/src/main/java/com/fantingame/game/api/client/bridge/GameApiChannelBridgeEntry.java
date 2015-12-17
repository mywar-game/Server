package com.fantingame.game.api.client.bridge;

import java.util.List;



import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.server.actionmanager.ActionExcutor;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.monitor.MonitorService;
import com.fantingame.game.server.msg.ByteToMsg;
import com.fantingame.game.server.msg.ChannelType;
import com.fantingame.game.server.msg.ServerMsgManager;

public class GameApiChannelBridgeEntry implements IBridgeEntry {
	
	public void channelException(Channel channel) {

	}
	public void receivedData(Channel channel, byte[] datas) {
		ByteToMsg byteToMsg = new ByteToMsg(datas);
		MonitorService.getInstance().markOneServerRequest();
		if(byteToMsg.parseByteServer(channel)){
			List<Msg> msgVector = byteToMsg.getMsgVector();
			for(Msg msg:msgVector){
				Msg result = ActionExcutor.getInstance().excutorAction(msg,channel);
				if(result!=null){
					ServerMsgManager.getInstance().tryPublishEvent(ChannelType.apiChannel, result);
				}
			}
	  }
	}
}

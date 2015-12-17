package com.fantingame.game.battle.client.bridge;

import java.util.List;

import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.server.actionmanager.ActionExcutor;
import com.fantingame.game.server.bridge.IBridgeEntry;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.ByteToMsg;
import com.fantingame.game.server.msg.ChannelType;
import com.fantingame.game.server.msg.ServerMsgManager;

public class BattleChannelBridgeEntry implements IBridgeEntry {
	
	public void receivedData(Channel channel, byte[] datas) {
		ByteToMsg byteToMsg = new ByteToMsg(datas);
		if(byteToMsg.parseByteServer(channel)){
			List<Msg> msgVector = byteToMsg.getMsgVector();
			List<Msg> resultList = ActionExcutor.getInstance().excutorAction(msgVector,channel);
	        if(resultList!=null&&resultList.size()>0){
	    	    ServerMsgManager.getInstance().tryPublishEvent(ChannelType.battleChannel, resultList);
	        }
	}
	}
}

package com.fantingame.game.gateway;

import com.alibaba.fastjson.JSON;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.common.model.MsgGroup;
import com.fantingame.game.msgbody.server.CommomMsgBody;
import com.fantingame.game.server.msg.MsgBuilder;
import com.fantingame.game.server.msg.ServerType;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MsgGroup group = new MsgGroup();
		
		CommomMsgBody body = new CommomMsgBody();
		
		Msg msg = MsgBuilder.buildUserMsg(ServerType.BATTLE_SERVER, "2", 3+"", body);
		
		group.addMsg(msg);
		
		String g = JSON.toJSONString(group);
		System.out.println(g);
	}

}

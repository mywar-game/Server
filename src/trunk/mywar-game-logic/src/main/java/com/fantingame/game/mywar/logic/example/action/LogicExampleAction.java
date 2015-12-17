package com.fantingame.game.mywar.logic.example.action;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.msgbody.server.CommomMsgBody;
import com.fantingame.game.msgbody.test.LogicExampleAction_testReq;
import com.fantingame.game.msgbody.test.LogicExampleAction_testRes;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;

@GameAction
public class LogicExampleAction {
	
     public ICodeAble test(Msg msg ,Channel channel){
    	 LogicExampleAction_testReq testAllStruct = msg.decodeBody(LogicExampleAction_testReq.class);
    	 LogSystem.info("收到以下内容"+testAllStruct.toString());
    	 testAllStruct.setStringValue1("我收到了你的消息，这是响应消息");
    	 LogicExampleAction_testRes res = new LogicExampleAction_testRes();
    	 return res;
     }
     
	 public ICodeAble battleSayHelloToMe(Msg msg, Channel channel) throws Exception {
	     CommomMsgBody body = msg.decodeBody(CommomMsgBody.class);
	     LogSystem.info("hi battle SERVER I 收到了你的消息 你对我说:="+body.getErrorDescription());
	     body.setErrorDescription("hi battle SERVER I 收到了你的消息");
	     return body;
	}
}

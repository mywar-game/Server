package com.framework.server.msg.manager;

import java.io.IOException;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.manager.Session;
import com.framework.manager.SessionManager;
import com.framework.server.bridge.BridgeExit;
import com.framework.server.channle.AbstractChnnel;
import com.framework.server.channle.Channel;
import com.framework.server.msg.Msg;
import com.framework.server.msg.model.SynList;
import com.framework.task.TaskExcutor;



/**
 * 消息处理�?
 * 
 * @author mengchao
 * 
 */
public class MsgManagerImp implements MsgManager {

	private BridgeExit bridgeExit = null;

	public MsgManagerImp() {

	}

	public MsgManagerImp(BridgeExit bridgeExit) {
		this.bridgeExit = bridgeExit;
	}

	public void msgManage(Channel channel, ByteToMsg byteToMsg) {
		SynList<Msg> msgVector = byteToMsg.getMsgVector();

		List<Msg> resultList = TaskExcutor.getInstance().excutorTask(msgVector, channel.getClientType());

		byte[] resDatas = null;
		
		boolean isSucess = true;
		try {
			String userSequece = (String) channel.getAttribute(AbstractChnnel.USER_SEQUENSE);
			Session session = SessionManager.getInstance().getSession(userSequece);
			if (session != null) {
				//如果session不为空 则返回所有消息列表中的数据
				resDatas = session.getResponseDate(resultList, channel
						.getClientType());
			} else {
				resDatas = MsgManageTool.saveResponseMsgs(resultList, channel
						.getClientType());
			}
		} catch (IOException e) {
			isSucess = false;
			LogSystem.error(e, "");
		}
		if (isSucess) {
			bridgeExit.sendData(channel, resDatas, false);
		} else {
			bridgeExit.sendData(channel, resDatas, true);
		}
	}

	public BridgeExit getBridgeExit() {
		return bridgeExit;
	}

	public void setBridgeExit(BridgeExit bridgeExit) {
		this.bridgeExit = bridgeExit;
	}
}

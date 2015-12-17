package com.framework.server.bridge;

import com.framework.server.channle.Channel;
import com.framework.server.msg.manager.ByteToMsg;
import com.framework.server.msg.manager.MsgManager;


public class ChannelBridgeEntry implements BridgeEntry {


	private MsgManager msgManager;

	public ChannelBridgeEntry() {
		
	}
	
	public ChannelBridgeEntry(
			MsgManager msgManager) {
		this.msgManager = msgManager;
	}

	/**
	 * 接受到数据，交付给消息处理器处理
	 */
	public void receivedData(Channel channel, byte[] datas) {
		msgManager.msgManage(channel, new ByteToMsg(datas));
	}

	public void channelException(Channel channel) {
		channel.close();

//		Object userSequenseObject = channel
//				.getAttribute(AbstractChnnel.USER_SEQUENSE);
//		Object userIdObject = channel.getAttribute(AbstractChnnel.USER_ID);
//
//		long userSequense = 0;
//		if (userSequenseObject != null) {
//			Long userSequenseLong = (Long) userSequenseObject;
//			userSequense = userSequenseLong;
//		}
//
//		String userId = "";
//		if (userIdObject != null) {
//			userId = (String) userIdObject;
//		}
//		logoutOperation.notifyLogout(userSequense, userId);
	}


	public MsgManager getMsgManager() {
		return msgManager;
	}

	public void setMsgManager(MsgManager msgManager) {
		this.msgManager = msgManager;
	}

}

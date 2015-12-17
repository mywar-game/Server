package com.framework.manager;


import java.io.IOException;
import java.util.List;

import com.framework.log.LogSystem;
import com.framework.server.channle.Channel;
import com.framework.server.msg.Msg;
import com.framework.server.msg.manager.MsgManageTool;



/**
 * 一个会话
 * 
 * @author mengc
 * 
 */
public class Session {

	// 用户序列号
	private final String sessionSequense;

	// 用户id
	private final String userId;

	// http用户会话该值是null
	private Channel channel;

	//用户消息列表
	private Messager message;

	// 最后访问时间
	private long lastRecordTime = System.currentTimeMillis();

	// 登录时间
	private long loginTime = System.currentTimeMillis();
	
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public Session(String sessionSequense, String userId, Channel channel, Messager message) {
		this.sessionSequense = sessionSequense;
		this.userId = userId;
		this.channel = channel;
		this.message = message;
	}
	
	public Messager getMessage() {
		return message;
	}
	public void setMessage(Messager message) {
		this.message = message;
	}
	/**
	 * 获取相应消息
	 * @param list
	 * @param type
	 * @return
	 */
	public byte[] getResponseDate(List<Msg> list, int type) {
		message.pushMsgList(list);
		List<Msg> msgList = message.popAllMsgList();
		
		byte[] responseCache = null;
		try {
			responseCache = MsgManageTool.saveResponseMsgs(msgList, type);
		} catch (IOException e) {
			LogSystem.error(e, "");
		}
		//修改最后一次操作时间
		lastRecordTime = System.currentTimeMillis();
		return responseCache;
	}

	public Channel getChannel() {
		return channel;
	}
	/**
	 * 该会话的channel是否关闭
	 * 
	 * @return
	 */
	public boolean isChannelClosed() {
		if (channel == null) {
			return false;
		}
		return channel.isClosed();
	}

	public void closeChannel() {
		if (channel != null && !channel.isClosed()) {
			channel.close();
		}
	}

	public long getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(long lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

	public String getSessionSequense() {
		return sessionSequense;
	}

	public String getUserId() {
		return userId;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}

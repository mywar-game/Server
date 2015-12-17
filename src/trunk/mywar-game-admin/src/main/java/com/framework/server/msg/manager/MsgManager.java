package com.framework.server.msg.manager;

import com.framework.server.channle.Channel;


/**
 * 消息处理接口
 * 
 * @author mengchao
 *
 */
public interface MsgManager {
	
	public void msgManage(Channel channel, ByteToMsg byteToMsg);
	
}

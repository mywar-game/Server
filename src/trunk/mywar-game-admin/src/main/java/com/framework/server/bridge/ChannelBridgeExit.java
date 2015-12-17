package com.framework.server.bridge;

import com.framework.server.channle.Channel;


/**
 * 网络数据出口
 * 
 * @author mengchao
 * 
 */
public class ChannelBridgeExit implements BridgeExit {

	public void sendData(Channel channel, byte[] datas, boolean isCloseChannel) {
		if (datas != null) {
			channel.write(datas);
		}
		if (isCloseChannel) {
			channel.close();
		}
	}

}

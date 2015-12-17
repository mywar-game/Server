package com.framework.server.bridge;

import com.framework.server.channle.Channel;

public interface BridgeEntry {

	public void channelException(Channel channel);

	public void receivedData(Channel channel, byte[] datas);

}

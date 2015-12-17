package com.framework.server.bridge;

import com.framework.server.channle.Channel;

public interface BridgeExit {

	public void sendData(Channel channel, byte[] datas, boolean isCloseChannel);

}

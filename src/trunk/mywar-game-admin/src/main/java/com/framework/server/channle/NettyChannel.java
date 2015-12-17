package com.framework.server.channle;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import com.framework.log.LogSystem;

public class NettyChannel extends AbstractChnnel {

	private Channel channel;
	private boolean isClosed = false;
	
	public NettyChannel(Channel channel) {
		this.channel = channel;
	}
	public synchronized void close() {
		if (isClosed) {
			return;
		}
		isClosed = true;
		channel.close();
		LogSystem.info("netty channel 被关闭！");
	}

	public boolean isClosed() {
		return isClosed;
	}

	public synchronized void write(byte[] datas) {
		if (datas == null  || datas.length == 0) {
			return;
		}
		if (!isClosed) {
			ChannelBuffer bufOut = ChannelBuffers.buffer(datas.length);
			bufOut.writeBytes(datas);
			channel.write(bufOut);
		}
	}

}

package com.framework.server.socket.netty.codefactory;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class Encoder extends SimpleChannelHandler {

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		ChannelBuffer buf = (ChannelBuffer) (e.getMessage());
		byte[] bytes = buf.array();
		ChannelBuffer buffer = ChannelBuffers.buffer(bytes.length + 4);
		buffer.writeInt(bytes.length);
		buffer.writeBytes(bytes);
		Channels.write(ctx, e.getFuture(), buffer);
	}
}

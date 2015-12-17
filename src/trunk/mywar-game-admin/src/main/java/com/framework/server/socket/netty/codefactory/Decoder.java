package com.framework.server.socket.netty.codefactory;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;


public class Decoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		// TODO Auto-generated method stub
		if (buffer.readableBytes() < 4) {
			return null;
		}
		buffer.markReaderIndex();
		int length = buffer.readInt();
        
		if (buffer.readableBytes() < length) {
			buffer.resetReaderIndex();
			return null;
		} else {
//			LogSystem.info("处理的消息大小为:"+length);
			return buffer.readBytes(length);
		}
	}

}

package com.framework.server.socket.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.bridge.BridgeEntry;
import com.framework.server.channle.NettyChannel;

public class NettyHandler extends SimpleChannelHandler {
	private BridgeEntry bridgeEntry;
	public static final String CHANNEL = "CHANNEL";

	public static final String IS_FIRST_EXCEPTION = "IS_FIRST_EXCEPTION";
	public BridgeEntry getBridgeEntry() {
		return bridgeEntry;
	}

	public void setBridgeEntry(BridgeEntry bridgeEntry) {
		this.bridgeEntry = bridgeEntry;
	}
	public NettyHandler() {
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		ChannelBuffer buf = (ChannelBuffer) (e.getMessage());
		byte[] bytes = buf.array();
		NettyChannel channel = (NettyChannel) ctx.getAttachment();
		bridgeEntry.receivedData(channel, bytes);
	}
	
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		NettyChannel nettyChannel = new NettyChannel(e.getChannel());
		nettyChannel.setClientType(SystemConstant.JAVA_CLIENT);
		nettyChannel.setProtocolType(SystemConstant.SOCKET_CONNECT);
		ctx.setAttachment(nettyChannel);
		LogSystem.info("netty channel opened!");
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.error(new Exception(e.getCause()), "netty error" + e.getCause().getMessage());
		NettyChannel channel = (NettyChannel) ctx.getAttachment();
		channel.close();
	}
}

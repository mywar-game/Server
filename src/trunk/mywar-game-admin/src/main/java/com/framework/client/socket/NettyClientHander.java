package com.framework.client.socket;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.bridge.BridgeEntry;
import com.framework.server.channle.NettyChannel;

public class NettyClientHander extends SimpleChannelHandler {
	
	private BridgeEntry bridgeEntry;

	

	public NettyClientHander(BridgeEntry bridgeEntry) {
		if (bridgeEntry == null) {
//			throw new NullPointerException("bridgeEntry不能为空");
		}
		this.bridgeEntry = bridgeEntry;
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		ChannelBuffer buf = (ChannelBuffer) (e.getMessage());
		byte[] bytes = buf.array();
//		NettyChannel channel = (NettyChannel)ctx.getAttachment();
		if (bytes != null && bridgeEntry != null) {
//			bridgeEntry.receivedData(channel, bytes);
		}
	}
	
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		NettyChannel nettyChannel = new NettyChannel(e.getChannel());
		nettyChannel.setClientType(SystemConstant.JAVA_CLIENT);
		nettyChannel.setProtocolType(SystemConstant.SOCKET_CONNECT);
		ctx.setAttachment(nettyChannel);
		LogSystem.info("nettyClient channel opened!");
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		
		LogSystem.error(new Exception(e.getCause()), "netty error" + e.getCause().getMessage());
        Channel ch = ctx.getChannel();
        ch.close();
	}
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("nettyClient channel is connected! id = " + ctx.getChannel().getId());
	}
	
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		LogSystem.info("channel is be closed" + ctx.getChannel().getId());
	}
}
